package servlet.admin;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import bean.Doctor;
import model.service.AnnouncmentService;
import utils.ResultDate;
import utils.UploadResult;
import utils.Util;

/**
 * Servlet implementation class AnnouncmentServlet 公告的控制器，查看，创建，修改公告
 */
public class AnnouncmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AnnouncmentService announcmentService = new AnnouncmentService();

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String m = request.getParameter("m");

		if ("listAnnouncment".equals(m)) {

			// 接受查询条件
			String context = request.getParameter("context");
			String creater = request.getParameter("creater");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			

			// 将条件封装到search中
			Map<String, String> search = new HashMap<String, String>();
			search.put("context", context);
			search.put("creater", creater);
			search.put("startTime", startTime);
			search.put("endTime", endTime);

			// 查询符合条件的公告
			List<Doctor> list = announcmentService.listSearch(search);

			request.setAttribute("search", search);

			request.setAttribute("announcmentList", list);

			request.setAttribute("listSize", list.size());

			request.getRequestDispatcher("/admin/announcmentList.jsp").forward(request, response);

		} else if ("updateActive".equals(m)) {

			// 停用和启用切换

			String doctorId = request.getParameter("id");

			String action = request.getParameter("action");

			announcmentService.toggleDoctorActive(doctorId, action, response);

		} else if ("selecteDoctor".equals(m)) {// ajax

			// 查看详情

			// 要查看的人
			String doctorId = request.getParameter("id");

			// 查询，并且将数据返回（JSON格式）
			announcmentService.getAnnouncmentToResponse(Integer.parseInt(doctorId), response);

		} else if ("updateDoctor".equals(m)) {

			// 增加

			// 取得表单里的值
			String name = request.getParameter("name");
			String age = request.getParameter("age");
			String sex = request.getParameter("sex");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String level = request.getParameter("level");
			String place = request.getParameter("place");
			String skill = request.getParameter("skill");
			String isActive = request.getParameter("isActive");




			// 将表单对象封装为Announcment对象


		}

	}

}
