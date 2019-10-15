package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import utils.ConfigProperties;

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
			
			System.out.println(receiver+"  receiver");
			
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
			String senderName = request.getParameter("senderName");// 发送者姓名
			String startSendTime = request.getParameter("startSendTime");// 发送时间,开始
			String endSendTime = request.getParameter("endSendTime");// 发送时间,止
			String isRead = request.getParameter("isRead");// 是否已读
			String context = request.getParameter("context");// 消息内容

			// 将条件封装到search中
			Map<String, String> search = new HashMap<String, String>();
			search.put("sender", sender);
			search.put("senderName", senderName);
			search.put("startSendTime", startSendTime);
			search.put("endSendTime", endSendTime);
			search.put("isRead", isRead);
			search.put("context", context);
			
			//得到当前登录的用户
			Map<String, Object> currentUser = getCurrentUser(request);

			// 查询接收到的
			List<Message> list = MessageService.listReceivMessage(search,(String)currentUser.get("reqeustUser"),(Integer)currentUser.get("reqeustUserId"));

			request.setAttribute("search", search);

			request.setAttribute("messageList", list);

			request.setAttribute("listSize", list.size());

			request.getRequestDispatcher("/admin/messageReceiveList.jsp").forward(request, response);

		}else if("updateIsRead".equals(m)) {
			//切换为已读
			
			//要切换消息的Id
			String messageId = request.getParameter("messageId");
			
			MessageService.toggleIsRead(Integer.parseInt(messageId),response);
		}else if ("newMessage".equals(m)) {

			// 查询新消息的数量
			
			//得到用户类型（admin,client,doctor）
			String user = request.getParameter("user");
			
			//得到当前登录的用户
			Map<String, Object> currentUser = getCurrentUser(request,user);
			
			//查询未读消息数量
			MessageService.newMessageNumResponse(currentUser,response);

		}

	}

	/**
	 * 得到当前登录的用户身份和用户Id(知道是什么类型的用户)
	 * @param request
	 * @return
	 */
	private Map<String, Object> getCurrentUser(HttpServletRequest request, String user) {

		Map<String, Object> currentUser = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		
		if("admin".equals(user)) {

			Admin admin = (Admin) session.getAttribute(AdminLoginServlet.LOGIN_ADMIN);
			currentUser.put("reqeustUser", "admin");
			currentUser.put("reqeustUserId", admin.getAdminId());
			currentUser.put("reqeustUserName", admin.getName());
			
		}
		else if("doctor".equals(user)) {

			Doctor doctor = (Doctor) session.getAttribute(DoctorLoginServlet.LOGIN_DOCTOR);
			currentUser.put("reqeustUser", "doctor");
			currentUser.put("reqeustUserId", doctor.getDoctorId());
			currentUser.put("reqeustUserName", doctor.getName());
			
		}
		else if("client".equals(user)) {

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

	/**
	 * 得到当前登录的用户身份和用户Id（不知道是什么类型的用户）
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
