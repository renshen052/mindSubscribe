package servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Admin;
import model.service.AdminService;

/**
 * Servlet implementation class AdminLoginServlet 管理员登录，退出
 */
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 当前登录的管理员
	 */
	public static final String LOGIN_ADMIN = "LOGIN_ADMIN";

	AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String m = request.getParameter("m");

		if ("saveLogin".equals(m)) {

			String adminName = request.getParameter("adminName");

			String adminPwd = request.getParameter("adminPwd");

			// 向数据库查询管理员
			Admin admin = adminService.getAdmin(adminName);

			if (admin == null || !(admin.getAdminPwd().equals(adminPwd))) {

				// 登录失败

				request.setAttribute("msg", "用户名或密码错误!");
				

				request.getRequestDispatcher("/admin/login.jsp").forward(request, response);

			} else {

				// 登录成功

				request.getSession().setAttribute(LOGIN_ADMIN, admin);

				response.sendRedirect(request.getContextPath() + "/admin/index.jsp");

			}

		}else {
			
			request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
		}

	}

}
