package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import bean.Admin;
import bean.Client;
import bean.Doctor;
import bean.Message;
import model.service.MessageService;
import servlet.admin.AdminLoginServlet;
import servlet.client.ClientLoginServlet;
import servlet.doctor.DoctorLoginServlet;

/**
 * Servlet implementation class MessageServlet
 */
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MessageService MessageService = new MessageService();

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String m = request.getParameter("m");

		if ("addMessage".equals(m)) {
			
			// 发送一条消息
			
			// 接受参数
			String receiver = request.getParameter("receiver");// 身份
			String receiverId = request.getParameter("receiverId");//接受者id
			String receiverName = request.getParameter("receiverName");// 接受者名字
			String context = request.getParameter("context");// 消息内容
			
			
			//得到当前登录的用户
			Map<String, Object> currentUser = getCurrentUser(request);
			
			//封装对象
			Message message = new Message();
			message.setSender( (String)currentUser.get("reqeustUser"));
			message.setSenderId( (Integer)currentUser.get("reqeustUserId"));
			message.setSenderName((String)currentUser.get("reqeustUserName"));
			message.setReceiver(receiver);
			message.setReceiverId(Integer.parseInt(receiverId));
			message.setReceiverName(receiverName);
			message.setContext(context);
			message.setSendTime(new Date());
			message.setIsRead(0);
			
			//发送消息
			MessageService.sendMessage(message,response);
			
			

		} else if ("listSendMessage".equals(m)) {

			// 查询一个用户发送的所有消息

			// 接受查询条件
			String receiver = request.getParameter("receiver");// 身份
			String receiverName = request.getParameter("receiverName");// 接受者名字
			String startSendTime = request.getParameter("startSendTime");// 发送时间,开始
			String endSendTime = request.getParameter("endSendTime");// 发送时间,止
			String isRead = request.getParameter("isRead");// 是否已读
			String context = request.getParameter("context");// 消息内容
			
			//得到当前登录的用户
			Map<String, Object> currentUser = getCurrentUser(request);
			
			/*//请求查询的人 ：admin client doctor
			String reqeustUser = request.getParameter("reqeustUser");
			
			
			//得到请求查询的人 的id
			Integer reqeustUserId = -1;
			if("admin".equals(reqeustUser)) {
				//当前登录用户 是管理员
				
				Admin admin = (Admin)request.getSession().getAttribute(AdminLoginServlet.LOGIN_ADMIN);
				reqeustUserId = admin.getAdminId();
				
			}if("doctor".equals(reqeustUser)) {
				//当前登录用户是 咨询师
				Doctor doctor = (Doctor)request.getSession().getAttribute(DoctorLoginServlet.LOGIN_DOCTOR);
				reqeustUserId = doctor.getDoctorId();
				
			}if("client".equals(reqeustUser)) {
				//当前登录用户是 来访者
				Client client = (Client)request.getSession().getAttribute(ClientLoginServlet.LOGIN_CLIENT);
				reqeustUserId = client.getClientId();
			}*/
			
			

			// 将条件封装到search中
			Map<String, String> search = new HashMap<String, String>();
			search.put("receiver", receiver);
			search.put("receiverName", receiverName);
			search.put("startSendTime", startSendTime);
			search.put("endSendTime", endSendTime);
			search.put("isRead", isRead);
			search.put("context", context);
			

			// 查询发送的消息
			List<Message> list = MessageService.listSendMessage(search,(String)currentUser.get("reqeustUser"),(Integer)currentUser.get("reqeustUserId"));

			request.setAttribute("search", search);

			request.setAttribute("messageList", list);


			request.getRequestDispatcher("/admin/messageSendList.jsp").forward(request, response);

		} else if ("listReceivMessage".equals(m)) {

			// 查询一个用户接收到的所有消息

			// 接受查询条件
			String sender = request.getParameter("sender");// 身份
			String sender_id = request.getParameter("sender_id");// 发送者id
			String startSendTime = request.getParameter("startSendTime");// 发送时间,开始
			String endSendTime = request.getParameter("endSendTime");// 发送时间,止
			String isRead = request.getParameter("isRead");// 是否已读
			String context = request.getParameter("context");// 消息内容

			// 将条件封装到search中
			Map<String, String> search = new HashMap<String, String>();
			search.put("sender", sender);
			search.put("sender_id", sender_id);
			search.put("startSendTime", startSendTime);
			search.put("endSendTime", endSendTime);
			search.put("isRead", isRead);
			search.put("context", context);

			// 查询发送的消息
			List<Message> list = MessageService.listReceivMessage(search);

			request.setAttribute("search", search);

			request.setAttribute("messageList", list);

			request.setAttribute("listSize", list.size());

			request.getRequestDispatcher("/admin/messageReceivList.jsp").forward(request, response);

		} else if ("getTalk".equals(m)) {

			// 查询对话上下文

		}

	}

	/**
	 * 得到当前登录的用户身份和用户Id
	 * @param request
	 * @return
	 */
	public static  Map<String, Object> getCurrentUser(HttpServletRequest request) {

		Map<String, Object> currentUser = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(AdminLoginServlet.LOGIN_ADMIN) != null) {

			Admin admin = (Admin) session.getAttribute(AdminLoginServlet.LOGIN_ADMIN);
			currentUser.put("reqeustUser", "admin");
			currentUser.put("reqeustUserId", admin.getAdminId());
			currentUser.put("reqeustUserName", admin.getName());
			
		}
		else if(session.getAttribute(DoctorLoginServlet.LOGIN_DOCTOR) != null) {

			Doctor doctor = (Doctor) session.getAttribute(DoctorLoginServlet.LOGIN_DOCTOR);
			currentUser.put("reqeustUser", "doctor");
			currentUser.put("reqeustUserId", doctor.getDoctorId());
			currentUser.put("reqeustUserName", doctor.getName());
			
		}
		else if(session.getAttribute(ClientLoginServlet.LOGIN_CLIENT) != null) {

			Client client = (Client) session.getAttribute(ClientLoginServlet.LOGIN_CLIENT);
			currentUser.put("reqeustUser", "client");
			currentUser.put("reqeustUserId", client.getClientId());
			currentUser.put("reqeustUserName", client.getName());
			
		}
		else {
			currentUser.put("reqeustUser", "no");
			currentUser.put("reqeustUserId", "0");
			currentUser.put("reqeustUserName", "noUser");
		}
		
		return currentUser;
	
	}

}
