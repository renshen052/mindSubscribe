package servlet.doctor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Client;
import bean.ClientArchive;
import bean.Doctor;
import bean.Question;
import model.service.ClientArchiveService;
import model.service.DoctorService;
import model.service.QuestionService;
import servlet.client.ClientLoginServlet;

/**
 * Servlet implementation class DoctorSubServlet
 */
public class DoctorSubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	DoctorService doctorService = new DoctorService();

	ClientArchiveService clientArchiveService = new ClientArchiveService();
	
	QuestionService questionService = new QuestionService();

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String m = request.getParameter("m");

		// 当前登录用户
		Doctor doctorNow = (Doctor) request.getSession().getAttribute(DoctorLoginServlet.LOGIN_DOCTOR);

		
		if ("subClientList".equals(m)) {
			// 咨询申请
			

			//查询所有咨询申请
			List<ClientArchive> list = clientArchiveService.getAllSubFromClient(doctorNow.getDoctorId());
			
			
			request.setAttribute("subClientList", list);

			request.getRequestDispatcher("/doctor/subClientList.jsp").forward(request, response);

		} else if ("subOnList".equals(m)) {
			//咨询中的
			
			// 显示正在咨询中的

			List<ClientArchive> list = clientArchiveService.subOnList(doctorNow.getDoctorId());
			
			request.setAttribute("clientArchiveList", list);

			request.getRequestDispatcher("/doctor/subOnList.jsp").forward(request, response);

		}else if ("doctorConsult".equals(m)) {
			//咨询记录

			// 已经完成的咨询记录

			List<ClientArchive> list = clientArchiveService.getSubOk(doctorNow.getDoctorId());
			
			request.setAttribute("clientArchiveList", list);
			
			request.getRequestDispatcher("/doctor/doctorConsult.jsp").forward(request, response);

		}

	}

}
