package servlet.client;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Client;
import model.service.ClientService;

/**
 * Servlet implementation class ClientRegister
 * 来访者注册
 */
public class ClientRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ClientService clientService = new ClientService();

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String m = request.getParameter("m");
		
		if("addClient".equals(m)) {
			
			//注册
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			String confirmPwd = request.getParameter("confirmPwd");
			
			if(pwd.trim() == "" || confirmPwd.trim() == "") {
				
				//重定向到注册界面
				response.sendRedirect(request.getContextPath() + "/client/reg/reg.jsp");
				return;
			}
			
			if(pwd.trim().equals(confirmPwd.trim())) {
				//两次密码一致
				
				Client client = new Client();
				client.setName(name);
				client.setClientName(phone);
				client.setClientPwd(pwd);
				client.setPhone(phone);
				client.setEmail(email);
				client.setRegionTime(new Date());
				client.setIsActive(1);
				
				//添加用户
				clientService.addClient(client);
				
				//重定向到登录界面
				response.sendRedirect(request.getContextPath() + "/client/login.jsp");
				return;				
			}else {
				
				//重定向到注册界面
				response.sendRedirect(request.getContextPath() + "/client/reg/reg.jsp");
				return;
			}
			
			
			
		}else if("testPhone".equals(m)) {
			
			//验证电话是否可用
			String phone = request.getParameter("phone");
			
			clientService.checkPhoneResponse(phone,response);
			
		}else {
			
			request.getRequestDispatcher("/client/reg/reg.jsp").forward(request, response);
			
		}
	
	}

}
