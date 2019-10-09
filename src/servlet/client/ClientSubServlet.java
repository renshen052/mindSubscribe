package servlet.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Client;
import bean.ClientArchive;
import bean.Doctor;
import model.service.ClientArchiveService;
import model.service.DoctorService;

/**
 * Servlet implementation class ClientSubServlet 普通来访者的预约咨询业务
 */
public class ClientSubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DoctorService doctorService = new DoctorService();

	ClientArchiveService clientArchiveService = new ClientArchiveService();

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String m = request.getParameter("m");

		if ("subDoctorList".equals(m)) {//可预约咨询师

			// 显示可供预约的咨询师列表

			List<Doctor> list = doctorService.listSearch(new HashMap<String, String>());

			request.setAttribute("doctorList", list);

			request.getRequestDispatcher("/client/submind.jsp").forward(request, response);

		} else if ("subClientList".equals(m)) {//我的预约

			// 显示正在预约的申请

			Client client = (Client) request.getSession().getAttribute(ClientLoginServlet.LOGIN_CLIENT);

			List<ClientArchive> list = clientArchiveService.onSubList(client.getClientId());
			
			request.setAttribute("clientArchiveList", list);

			request.getRequestDispatcher("/client/onSubList.jsp").forward(request, response);

		} else if ("subStep1".equals(m)) {

			// 预约第一步，跳转到预约界面

			// 填写信息

		} else if ("subStep2".equals(m)) {

			// 预约第二步，填写问卷

		} else if ("clientConsult".equals(m)) {//我的咨询

			// 已经完成的预约列表

			Client client = (Client) request.getSession().getAttribute(ClientLoginServlet.LOGIN_CLIENT);

			List<ClientArchive> list = clientArchiveService.clientConsult(client.getClientId());
			
			request.setAttribute("clientArchiveList", list);
			
			request.getRequestDispatcher("/client/clientConsult.jsp").forward(request, response);

		}

	}

}
