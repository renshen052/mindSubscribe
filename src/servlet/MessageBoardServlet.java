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

import bean.Client;
import bean.MessageBoard;
import model.service.MessageBoardService;
import servlet.client.ClientLoginServlet;

/**
 * Servlet implementation class MessageBoardServlet
 * 来访者留言
 */
public class MessageBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MessageBoardService messageBoardService = new MessageBoardService();
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String m = request.getParameter("m");

		if ("listMessageBoard".equals(m)) {

			// 接受查询条件
			
			String creater = request.getParameter("creater");
			String context = request.getParameter("context");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			

			// 将条件封装到search中
			Map<String, String> search = new HashMap<String, String>();
			search.put("creater", creater);
			search.put("context", context);
			search.put("startTime", startTime);
			search.put("endTime", endTime);

			// 查询符合条件的留言
			List<MessageBoard> list = messageBoardService.listSearch(search);

			request.setAttribute("search", search);

			request.setAttribute("messageBoardList", list);

			request.setAttribute("listSize", list.size());

			request.getRequestDispatcher("/admin/messageBoard.jsp").forward(request, response);

		} else if ("updateActive".equals(m)) {

			// 显示和隐藏切换

			String messageBoardId = request.getParameter("id");
			

			String action = request.getParameter("action");
			
			System.out.println(messageBoardId + "---" + action);


			messageBoardService.toggleMessageBoardActive(messageBoardId, action, response);

		} else if ("selecteMessageBoard".equals(m)) {// ajax

			// 查看留言

			String messageBoardId = request.getParameter("id");

			// 查询，并且将数据返回（JSON格式）
			messageBoardService.getMessageBoardToResponse(Integer.parseInt(messageBoardId), response);

		} else if ("addMessageBoard".equals(m)) {
			
			// 增加

			// 取得表单里的值
			
			String context = request.getParameter("context");
			
			String isActive = request.getParameter("isActive");

			//创建者
			Client client =  (Client) request.getSession().getAttribute(ClientLoginServlet.LOGIN_CLIENT);


			// 将表单对象封装为MessageBoard对象
			MessageBoard messageBoard = new MessageBoard();
			messageBoard.setContext(context);
			messageBoard.setCreateTime(new Date());
			messageBoard.setIsActive(Integer.parseInt(isActive));
			messageBoard.setCreaterId(client.getClientId());
			
			//增加一个公告
			messageBoardService.addMessageBoard(messageBoard,response);
			
		}

	}

}
