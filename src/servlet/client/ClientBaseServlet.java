package servlet.client;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Admin;
import bean.Announcement;
import bean.Client;
import bean.Message;
import bean.MessageBoard;
import model.service.AnnouncmentService;
import model.service.ClientService;
import model.service.MessageBoardService;
import model.service.MessageService;
import servlet.admin.AdminLoginServlet;

/**
 * Servlet implementation class ClientBaseServlet 来访者登录后的操作
 */
public class ClientBaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ClientService clientService = new ClientService();

	MessageBoardService messageBoardService = new MessageBoardService();

	MessageService messageService = new MessageService();

	AnnouncmentService announcmentService = new AnnouncmentService();

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String m = request.getParameter("m");
		
		// 当前登录用户
		Client clientNow = (Client) request.getSession().getAttribute(ClientLoginServlet.LOGIN_CLIENT);

		if ("updatePwd".equals(m)) {

			// 修改密码

			String newPwd = request.getParameter("newPwd");

			String password = request.getParameter("password");

			String msg = "";
			if (clientNow.getClientPwd().equals(password)) {

				// 成功
				clientService.updateClientPwd(clientNow.getClientId(), newPwd);

				msg = "{\"result\":\"true\",\"msg\":\"修改成功,请重新登录！\"}";

				request.getSession().removeAttribute(ClientLoginServlet.LOGIN_CLIENT);

			} else if (!clientNow.getClientPwd().equals(password)) {
				msg = "{\"result\":\"false\",\"msg\":\"修改失败，原始密码错误！\"}";
			} else {
				msg = "{\"result\":\"false\",\"msg\":\"修改失败，请重试！\"}";
			}

			response.setContentType("application/json; charset=utf-8");

			Writer writer = response.getWriter();

			writer.write(msg);

			writer.close();

		} else if ("clientIndex".equals(m)) {

			// 查数据（首页用到的）

			// 查询所有显示的留言(最新的十条）
			ArrayList<MessageBoard> newMessageBoardList = messageBoardService.getMessageBoardNum(10);

			request.setAttribute("newMessageBoardList", newMessageBoardList);

			// 查询所有未读的消息(最新的十条）
			ArrayList<Message> messageList = messageService.getMessageNum(10, clientNow.getClientId(), "client");

			request.setAttribute("messageList", messageList);

			// 查询所有显示的公告(最新的十条)
			ArrayList<Announcement> announcmentList = announcmentService.getAnnouncmentNum(10);

			request.setAttribute("announcmentList", announcmentList);

			// 转发到首页
			request.getRequestDispatcher("/client/home.jsp").forward(request, response);

		} else if ("clientInfo".equals(m)) {

			// 查看个人信息

			request.getRequestDispatcher("/client/client_info.jsp").forward(request, response);
		}else if("updateBase".equals(m)){
			
			//修改基本信息
			
			Client client = new Client();
			client.setName(request.getParameter("name"));
			client.setSex( Integer.parseInt( request.getParameter("sex")));
			client.setAge(Integer.parseInt(request.getParameter("age")));
			client.setPhone(request.getParameter("phone"));
			client.setEmail(request.getParameter("email"));
			
			
			//更新
			int i = clientService.updateClientBase(client,clientNow.getClientId());
			
			Writer writer = response.getWriter();
			
			//设置响应类型
			response.setContentType("application/json; charset=utf-8");
			
			String msg = "";
			if(i > 0) {
				//成功
				msg = "{\"result\":\"true\",\"msg\":\"修改成功,请刷新！\"}";
				
				
				//重新在查询一遍管理员信息
				Client clientNew = clientService.getClient(clientNow.getClientName());
				
				request.getSession().setAttribute(ClientLoginServlet.LOGIN_CLIENT,clientNew);
				
			}else {
				//失败
				msg = "{\"result\":\"false\",\"msg\":\"修改失败，请刷新页面后重试\"}";
			}
			writer.write(msg);
			writer.close();
		
		}

	}

}
