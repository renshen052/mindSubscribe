package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigProperties {
	
	private static Properties properties = new Properties();
	
	static{
		
		try {
			properties.load(ConfigProperties.class.getClassLoader().getResourceAsStream("config.properties"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * 获取上传目录
	 * @return
	 */
	public static String getUploadPath() {
		return properties.getProperty("upload_path");
	}
	
	/**
	 * 允许上传图片的类型
	 * @return
	 */
	public static String getUploadImgType() {
		return properties.getProperty("upload_img_type");
	}
	
	/**
	 * 允许上传图片的大小
	 * @return
	 */
	public static String getUploadImgSize() {
		return properties.getProperty("upload_img_size");
	}
	
	/**
	 * 允许上传附件的类型
	 * @return
	 */
	public static String getUploadFileType() {
		return properties.getProperty("upload_file_type");
	}
	
	/**
	 * 允许上传附件的大小
	 * @return
	 */
	public static String getUploadFileSize() {
		return properties.getProperty("upload_file_size");
	}
	
}

