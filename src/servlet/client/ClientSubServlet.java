package servlet.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.json.JSONUtil;

import bean.Client;
import bean.ClientArchive;
import bean.Doctor;
import bean.Question;
import model.service.ClientArchiveService;
import model.service.DoctorService;
import model.service.QuestionService;

/**
 * Servlet implementation class ClientSubServlet 普通来访者的预约咨询业务
 */
public class ClientSubServlet extends HttpServlet {
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
		Client clientNow = (Client) request.getSession().getAttribute(ClientLoginServlet.LOGIN_CLIENT);

		
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
			
			//查询预约的咨询师
			
			String doctorId = request.getParameter("doctorId");
			
			Doctor doctor = doctorService.getDoctorById(Integer.parseInt(doctorId));
			
			request.setAttribute("doctor", doctor);
			
			//查询问卷内容
			ArrayList<Question> list = questionService.listQuestion("");
			
			request.setAttribute("questionList", list);
			
			
			request.getRequestDispatcher("/client/subAdd.jsp").forward(request, response);


		} else if ("subStep2".equals(m)) {

			// 保存
			
			//取得各个数据
			String expectTime = request.getParameter("expectTime");
			
			String expectPlace = request.getParameter("expectPlace");
			
			String doctorId = request.getParameter("doctorId");
			
			
			
			//返回questionId，，用"，"隔开
			String questionIds = request.getParameter("questionIds");
			
			//解析questionIds,返回json字符串，和分值
			HashMap<String,String> mapJsonLevel = questionService.getJSON(questionIds,request);
			
			
			ClientArchive clientArchive = new ClientArchive();
			clientArchive.setClientId(clientNow.getClientId());
			clientArchive.setDoctorId(Integer.parseInt(doctorId));
			clientArchive.setQuestionContext(mapJsonLevel.get("JSON"));
			clientArchive.setLevel(Integer.parseInt(mapJsonLevel.get("level")));
			clientArchive.setApplyTime(new Date());
			clientArchive.setExpectPlace(expectPlace);
			clientArchive.setExpectTime(expectTime);
			
			clientArchiveService.addClientArchive(clientArchive,response);
			

		} else if ("clientConsult".equals(m)) {//我的咨询

			// 已经完成的预约列表

			Client client = (Client) request.getSession().getAttribute(ClientLoginServlet.LOGIN_CLIENT);

			List<ClientArchive> list = clientArchiveService.clientConsult(client.getClientId());
			
			request.setAttribute("clientArchiveList", list);
			
			request.getRequestDispatcher("/client/clientConsult.jsp").forward(request, response);

		}

	}

}
