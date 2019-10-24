package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

/**
 * @author h w j
 * @instruction
 * 工具类
 */
public class Util {

	/**
	 * 不限类型
	 */
	public static final int UPLOAD_TYPE_ALL = 0;

	/**
	 * 图片
	 */
	public static final int UPLOAD_TYPE_IMG = 1;

	/**
	 * 文件
	 */
	public static final int UPLOAD_TYPE_ATTACHMENT = 2;

	/**
	 * @description: 生成随机密码
	 */
	public static String generateRandomPwd(int length) {

		String[] chars = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "B", "C", "D", "E", "F", "G",
				"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b",
				"c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"x", "y", "z", "_", "!", "@", "#", "%", "^", "&", "*", "(", ")" };// [0,length - 1]

		String pwd = "";

		for (int i = 0; i < length; i++) {

			int randNum = (int) (Math.random() * (chars.length));// [0.0,1.0) => [0,length)

			pwd += chars[randNum];

		}

		return pwd;
	}

	/**
	 * 生成随机账号
	 */
	public static String generateRandomNum(int length) {

		String[] chars = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "B", "C", "D", "E", "F", "G",
				"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b",
				"c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"x", "y", "z" };// [0,length - 1]

		String pwd = "";

		for (int i = 0; i < length; i++) {

			int randNum = (int) (Math.random() * (chars.length));// [0.0,1.0) => [0,length)

			pwd += chars[randNum];

		}

		return pwd;

	}

	/**
	 * @description: 判断字符串是否不为空
	 */

	public static boolean isNotEmpty(String str) {

		return str != null && !"".equals(str.trim());
	}

	/**
	 * @description: 判断字符串是否为空
	 *
	 */

	public static boolean isEmpty(String str) {

		return !isNotEmpty(str);
	}

	/**
	 * 生成随机文件名：年月日时分秒20位随机数，扩展名为
	 * 
	 * @param expandName 扩展名
	 */
	public static String generateRandFileName(String expandName) {

		System.out.println("expandName:" + expandName);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssms");

		String nowDatestr = sdf.format(new Date());

		String str = nowDatestr;
		for (int i = 0; i < 20; i++) {

			str += (int) (Math.random() * 10);

		}

		if (Util.isNotEmpty(expandName)) {
			str += "." + expandName;
		}

		return str;
	}

	/**
	 * 根据 源文件名获取 扩展名
	 * 
	 * @param sourceFileName
	 * @return
	 */
	public static String getExpandName(String sourceFileName) {

		String expandName = "";

		int pointPosition = sourceFileName.lastIndexOf(".");

		if (pointPosition != -1) {
			expandName = sourceFileName.substring(pointPosition + 1, sourceFileName.length());
		}

		return expandName;
	}

	/**
	 * 根据 contentDisposition获取源文件的文件名
	 * 
	 * @param contentDisposition
	 */
	public static String getSourceFileName(String contentDisposition) {

		return contentDisposition.substring(contentDisposition.indexOf("filename=\"") + 10,
				contentDisposition.length() - 1);

	}

	/**
	 * 文件上传
	 * 
	 * @param type 0不限类型，1 图像 2 附件
	 */
	public static UploadResult upload(String partName, HttpServletRequest request, int type) {

		UploadResult uploadResult = new UploadResult();

		// 逻辑名
		String logicFileName = "";

		Part part = null;
		try {
			part = request.getPart(partName);
		} catch (IOException | ServletException e1) {
			e1.printStackTrace();
		}

		if (part != null && part.getSize() > 0) {// 判断用户是否有选择文件

			// 得到请求中的头
			String contentDisposition = part.getHeader("content-disposition");

			// 截取文件的源文件名
			String sourceFileName = Util.getSourceFileName(contentDisposition);

			// 根据源文件名 获得扩展名
			String expandName = Util.getExpandName(sourceFileName);

			if (!isAllowedType(expandName, type)) {

				uploadResult.setRefuse(true);

				uploadResult.setResult(false);

				// 设置文件大小
				uploadResult.setSize(part.getSize());

				// 设置源文件名
				uploadResult.setSourceFileName(sourceFileName);

				// 消息
				uploadResult.setMsg("失败！ 文件的类型超出了允许的类型");

				return uploadResult;
			}

			if (!isAllowedSize(part.getSize(), type)) {

				uploadResult.setRefuse(true);

				uploadResult.setResult(false);

				// 设置文件大小
				uploadResult.setSize(part.getSize());

				// 设置源文件名
				uploadResult.setSourceFileName(sourceFileName);

				// 消息
				uploadResult.setMsg("失败！ 文件的大小超出了限制");

				return uploadResult;
			}

			// 生成随机文件名
			logicFileName = Util.generateRandFileName(expandName);

			File file = new File(ConfigProperties.getUploadPath() + "\\" + logicFileName);

			while (file.exists()) {// 此文件已经存在

				// 重新生成随机文件名
				logicFileName = Util.generateRandFileName(expandName);

				file = new File(ConfigProperties.getUploadPath() + "\\" + logicFileName);
			}

			InputStream is = null;
			OutputStream os = null;

			try {

				/**
				 * 拷贝文件到服务器
				 */
				is = part.getInputStream();

				os = new FileOutputStream(ConfigProperties.getUploadPath() + "\\" + logicFileName);

				/**
				 * 每次读写一批字节
				 */
				byte[] bytes = new byte[2048];

				int length;

				while ((length = is.read(bytes)) != -1) {
					os.write(bytes, 0, length);
				}

				// 设置逻辑文件名
				uploadResult.setLogicFileName(logicFileName);

				// 设置源文件名
				uploadResult.setSourceFileName(sourceFileName);

				// 设置文件大小
				uploadResult.setSize(part.getSize());

				// 设置状态
				uploadResult.setResult(true);
				uploadResult.setMsg("成功！");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				uploadResult.setResult(false);
				uploadResult.setMsg("失败！");

			} finally {
				if (os != null) {
					try {
						os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		return uploadResult;
	}

	/**
	 * 校验上传类型是否合法
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isAllowedType(String expandName, int type) {

		String alloweTypeStr = "";

		if (type == UPLOAD_TYPE_IMG) {

			// 读取config.properties当中记载的允许的类型
			alloweTypeStr = ConfigProperties.getUploadImgType();
		} else if (type == UPLOAD_TYPE_ATTACHMENT) {

			// 读取config.properties当中记载的允许的类型
			alloweTypeStr = ConfigProperties.getUploadFileType();

		}
		// 拆分字符串数组
		String[] alloweTypes = alloweTypeStr.split(",");

		// 遍历数组中的每一个元素，判断是否与源文件的扩展名一致
		for (String allowedType : alloweTypes) {

			// 忽略大小写比较
			if (allowedType.equalsIgnoreCase(expandName)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 检查文件大小是否合法
	 * 
	 * @param size
	 * @param type
	 * @return
	 */
	public static boolean isAllowedSize(long size, int type) {

		String alloweSizeStr = "";

		if (type == UPLOAD_TYPE_IMG) {

			// 读取config.properties当中记载的允许的类型
			alloweSizeStr = ConfigProperties.getUploadImgSize();
		} else if (type == UPLOAD_TYPE_ATTACHMENT) {

			// 读取config.properties当中记载的允许的类型
			alloweSizeStr = ConfigProperties.getUploadFileSize();
		}

		// 如果文件的大小超出了允许的大小
		if (size > Long.parseLong(alloweSizeStr)) {

			return false;
		}

		// 合法
		return true;
	}

	/**
	 * 随机生成n为数字
	 */
	public static String randomNum(int n) {

		String str = "";
		for (int i = 0; i < n; i++) {

			str += (int) (Math.random() * 10);

		}

		return str;
	}

	public static void main(String[] args) {
		System.out.println(isAllowedType("BMP", 1));
	}

	/**
	 * 响应JSON格式数据
	 * @param rd
	 * @param response
	 */
	public static void responseJson(ResultDate rd, HttpServletResponse response) {

		// 设置响应格式
		response.setContentType("application/json; charset=utf-8");

		try (Writer witer = response.getWriter();) {

			JSONUtil.serialize(witer, rd);

		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
