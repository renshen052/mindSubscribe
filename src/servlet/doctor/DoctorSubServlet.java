package servlet.doctor;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import utils.UploadResult;
import utils.Util;
import model.service.ClientService;

/**
 * Servlet implementation class DoctorSubServlet
 */
@MultipartConfig
public class DoctorSubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClientService clientService = new ClientService();

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

			// 查询所有咨询申请
			List<ClientArchive> list = clientArchiveService.getAllSubFromClient(doctorNow.getDoctorId());

			request.setAttribute("clientArchiveList", list);

			request.getRequestDispatcher("/doctor/subClientList.jsp").forward(request, response);

		} else if ("subOnList".equals(m)) {
			// 咨询中的

			// 显示正在咨询中的

			List<ClientArchive> list = clientArchiveService.subOnList(doctorNow.getDoctorId());

			request.setAttribute("clientArchiveList", list);

			request.getRequestDispatcher("/doctor/subOnList.jsp").forward(request, response);

		} else if ("doctorConsult".equals(m)) {
			// 咨询记录

			// 已经完成的咨询记录

			List<ClientArchive> list = clientArchiveService.getSubOk(doctorNow.getDoctorId());

			request.setAttribute("clientArchiveList", list);

			request.getRequestDispatcher("/doctor/doctorConsult.jsp").forward(request, response);

		} else if ("subShow".equals(m)) {
			// 查看申请详情

			String archives_id = request.getParameter("archivesId");

			String clientId = request.getParameter("clientId");

			// 通过Id拿到ClientArchive对象
			ClientArchive clientArchive = clientArchiveService.getClientArchiveById(Integer.parseInt(archives_id));

			request.setAttribute("clientArchive", clientArchive);

			// 通过clientId拿到Client对象
			Client client = clientService.getClientByClientId(Integer.parseInt(clientId));

			request.setAttribute("client", client);

			request.getRequestDispatcher("/doctor/subShow.jsp").forward(request, response);

		} else if ("updateStatusFalse".equals(m)) {
			// 驳回申请

			// 切换status为失败状态（-1）

			String archivesId = request.getParameter("archivesId");

			String clientId = request.getParameter("clientId");

			String applyTime = request.getParameter("applyTime");

			clientArchiveService.updateStatusFalseResponse(Integer.parseInt(archivesId), Integer.parseInt(clientId),
					applyTime, response, doctorNow);

			// 这里发邮件，站内消息（以咨询师的名义） 给用户，通知结果，
		} else if ("planSub".equals(m)) {
			// 安排咨询，即设置咨询时间地点，status改为 1 通过申请但未完成

			// 取得参数

			String archivesId = request.getParameter("archivesId");

			String clientId = request.getParameter("clientId");

			String startDatetime = request.getParameter("startDatetime");

			String endDatetime = request.getParameter("endDatetime");

			String subPlace = request.getParameter("subPlace");

			// 封装对象
			ClientArchive clientArchive = new ClientArchive();
			clientArchive.setArchivesId(Integer.parseInt(archivesId));
			clientArchive.setClientId(Integer.parseInt(clientId));
			clientArchive.setSubPlace(subPlace);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			try {
				clientArchive.setEndDatetime(sdf.parse(endDatetime));
				clientArchive.setStartDatetime(sdf.parse(startDatetime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			clientArchiveService.planSubResponse(clientArchive, response, doctorNow);

		} else if ("updateStatusFinish".equals(m)) {
			// 完成咨询

			// 切换status为完成状态（3）

			String archivesId = request.getParameter("archivesId");

			String clientId = request.getParameter("clientId");

			clientArchiveService.updateStatusFinishResponse(Integer.parseInt(archivesId), Integer.parseInt(clientId),
					response, doctorNow);

		} else if ("uploadSubDoc".equals(m)) {
			// 上传咨询文档

			//传上来的文档

			UploadResult uploadResult = Util.upload("subDoc", request, Util.UPLOAD_TYPE_ATTACHMENT);
			
			
			//目标咨询记录
			String archivesId = request.getParameter("archivesId");
			
			//咨询记录中的文档更新
			clientArchiveService.uploadSubDoc(archivesId,uploadResult,response);
			
			

		}

	}

}
