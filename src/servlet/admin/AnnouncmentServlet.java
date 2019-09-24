package servlet.admin;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import bean.Admin;
import bean.Announcement;
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

	AnnouncmentService announcementService = new AnnouncmentService();

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String m = request.getParameter("m");

		if ("listAnnouncment".equals(m)) {

			// 接受查询条件
			
			String creater = request.getParameter("creater");
			String title = request.getParameter("title");
			String context = request.getParameter("context");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			

			// 将条件封装到search中
			Map<String, String> search = new HashMap<String, String>();
			search.put("creater", creater);
			search.put("title", title);
			search.put("context", context);
			search.put("startTime", startTime);
			search.put("endTime", endTime);

			// 查询符合条件的公告
			List<Announcement> list = announcementService.listSearch(search);

			request.setAttribute("search", search);

			request.setAttribute("announcementList", list);

			request.setAttribute("listSize", list.size());

			request.getRequestDispatcher("/admin/announcementList.jsp").forward(request, response);

		} else if ("updateActive".equals(m)) {

			// 停用和启用切换

			String announcementId = request.getParameter("id");

			String action = request.getParameter("action");

			announcementService.toggleDoctorActive(announcementId, action, response);

		} else if ("selecteAnnouncement".equals(m)) {// ajax

			// 查看公告

			String announcementId = request.getParameter("id");

			// 查询，并且将数据返回（JSON格式）
			announcementService.getAnnouncementToResponse(Integer.parseInt(announcementId), response);

		} else if ("addAnnouncement".equals(m)) {
			
			// 增加

			// 取得表单里的值
			
			String title = request.getParameter("title");
			String context = request.getParameter("context");
			
			String isActive = request.getParameter("isActive");

			//创建者
			Admin admin =  (Admin) request.getSession().getAttribute(AdminLoginServlet.LOGIN_ADMIN);


			// 将表单对象封装为Announcement对象
			Announcement announcement = new Announcement();
			announcement.setTitle(title);
			announcement.setContext(context);
			announcement.setCreateTime(new Date());
			announcement.setIsActive(Integer.parseInt(isActive));
			announcement.setCreaterId(admin.getAdminId());
			
			//增加一个公告
			announcementService.addAnnouncement(announcement,response);
			
		}

	}

}
