package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ConfigProperties;

/**
 * Servlet implementation class UploadFile
 */
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String m = request.getParameter("m");
		if("downloadSubDoc".equals(m)) {

			// 接受参数
			String subDocPath = request.getParameter("subDocPath");
			String fileName = request.getParameter("filename");
			
			fileName += "_" + subDocPath.substring(subDocPath.length()-10, subDocPath.length());
			
			response.setHeader("content-disposition","attachment;filename=\"" + new String(fileName.getBytes("GBK"),"ISO8859-1") + "\"");
			
			

			InputStream is = null;
			OutputStream os = null;

			try {

				is = new FileInputStream(ConfigProperties.getUploadPath() + "\\" + subDocPath);
				os = response.getOutputStream();

				byte[] bytes = new byte[2048];
				int len = -1;
				while ((len = is.read(bytes)) != -1) {
					os.write(bytes);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(os != null) {
					os.close();
				}
				if(is != null) {
					is.close();
				}
			}
			
		}

		
		
		
	}

}
