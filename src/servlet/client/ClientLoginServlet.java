package servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Admin;
import bean.Client;
import model.service.ClientService;

/**
 * Servlet implementation class ClientLoginServlet
 */
public class ClientLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 当前登录的来访者
	 */
	public final static String LOGIN_CLIENT = "LOGIN_CLIENT";
	
	ClientService clientService = new ClientService();
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String m = request.getParameter("m");

		if ("saveLogin".equals(m)) {

			String clientName = request.getParameter("clientName");

			String clientPwd = request.getParameter("clientPwd");

			// 向数据库查询来访者
			Client client = clientService.getClient(clientName);

			if (client == null || !(client.getClientPwd().equals(clientPwd))) {

				// 登录失败

				request.setAttribute("msg", "用户名或密码错误!");
				

				request.getRequestDispatcher("/client/login.jsp").forward(request, response);

			}else if(client.getIsActive() == 0) {
				
				request.setAttribute("msg", "当前账户不可用!");

				request.getRequestDispatcher("/client/login.jsp").forward(request, response);
			}
			else {

				// 登录成功

				request.getSession().setAttribute(LOGIN_CLIENT, client);

				response.sendRedirect(request.getContextPath() + "/client/index.jsp");

			}

		}else if("logOutClient".equals(m)){
			
			request.getSession().removeAttribute(LOGIN_CLIENT);
			
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
			
		}else {
			
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
		}

	}

}
