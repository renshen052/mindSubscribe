package servlet.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Client;
import bean.Doctor;
import model.service.ClientService;

/**
 * @author h w j
 * @instruction
 * 管理员端，管理来访者控制器
 */
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ClientService clientService = new ClientService();

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String m = request.getParameter("m");

		if ("listClient".equals(m)) {

			// 接受查询条件
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String startAge = request.getParameter("startAge");
			String endAge = request.getParameter("endAge");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String startRegionTime = request.getParameter("startRegionTime");
			String endRegionTime = request.getParameter("endRegionTime");

			// 将条件封装到search中
			Map<String, String> search = new HashMap<String, String>();
			search.put("name", name);
			search.put("startAge", startAge);
			search.put("endAge", endAge);
			search.put("phone", phone);
			search.put("email", email);
			search.put("sex", sex);
			search.put("startRegionTime", startRegionTime);
			search.put("endRegionTime", endRegionTime);
			

			
			// 查询符合条件的来访者
			List<Client> list = clientService .listSearch(search);

			request.setAttribute("search", search);

			request.setAttribute("clientList", list);

			request.getRequestDispatcher("/admin/clientList.jsp").forward(request, response);

		}else if("updateActive".equals(m)){
			
			//停用和启用切换
			
			String clientId = request.getParameter("id");
			
			String action = request.getParameter("action");
			
			clientService.toggleClientActive(Integer.parseInt(clientId),Integer.parseInt(action),response);
			
			
		}

	}

}
