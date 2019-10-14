package servlet.doctor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Client;
import bean.Doctor;
import model.service.DoctorService;

/**
 * Servlet implementation class DoctorLoginServlet
 */
public class DoctorLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public final static String LOGIN_DOCTOR = "LOGIN_DOCTOR";
	
	DoctorService doctorService = new DoctorService();
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String m = request.getParameter("m");

		if ("saveLogin".equals(m)) {

			String doctorName = request.getParameter("doctorName");

			String doctorPwd = request.getParameter("doctorPwd");

			// 向数据库查询来访者
			Doctor doctor = doctorService.getDoctor(doctorName);

			if (doctor == null || !(doctor.getDoctorPwd().equals(doctorPwd))) {

				// 登录失败

				request.setAttribute("msg", "用户名或密码错误!");

				request.getRequestDispatcher("/doctor/login.jsp").forward(request, response);

			}else if(doctor.getIsActive() == 0) {
				
				// 登录失败

				request.setAttribute("msg", "当前账户不可用!");

				request.getRequestDispatcher("/doctor/login.jsp").forward(request, response);
				
			}
			else {

				// 登录成功

				request.getSession().setAttribute(LOGIN_DOCTOR, doctor);

				response.sendRedirect(request.getContextPath() + "/doctor/index.jsp");

			}

		}else if("logOutClient".equals(m)){
			
			request.getSession().removeAttribute(LOGIN_DOCTOR);
			
			request.getRequestDispatcher("/doctor/login.jsp").forward(request, response);
			
		}else {
			
			request.getRequestDispatcher("/doctor/login.jsp").forward(request, response);
		}

	}

}
