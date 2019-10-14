package utils.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.Doctor;
import servlet.admin.AdminLoginServlet;
import servlet.doctor.DoctorLoginServlet;

/**
 * Servlet Filter implementation class DoctorLoginFilter
 */
public class DoctorLoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public DoctorLoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;

		HttpServletResponse response = (HttpServletResponse) res;

		HttpSession session = request.getSession();

		String path = request.getRequestURI();


		Doctor doctor = (Doctor) session.getAttribute(DoctorLoginServlet.LOGIN_DOCTOR);

		if (doctor == null) {

			System.out.println("DoctorLoginFilter:咨询师未登录，被拦截：" + path);
			
			// 未登录
			// 重定向到登录页面
			java.io.PrintWriter writer = response.getWriter();
			
			writer.println("<html><script>");
			writer.println("window.open('" + request.getContextPath() + "/doctor/DoctorLoginServlet','_top')");
			writer.println("</script></html>");
			writer.close();
			return;
		}

		// 过滤器链
		chain.doFilter(req, res);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
