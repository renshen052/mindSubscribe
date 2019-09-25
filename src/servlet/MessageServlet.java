package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.service.MessageService;

/**
 * Servlet implementation class MessageServlet
 */
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	MessageService MessageService = new MessageService();
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String m = request.getParameter("m");

		if("addMessage".equals(m)) {
			
			//发送一条消息
			
			//接受参数
			
			
			
			
		}else if("getUserSendMessage".equals(m)) {
			
			//查询一个用户发送的所有消息
			
			
			
		}else if("getUserReceivMessage".equals(m)) {
			
			//查询一个用户接收到的所有消息
			
			
		}else if("getTalk".equals(m)) {
			
			//查询对话上下文
			
		}
		
	
	
	
	}

}
