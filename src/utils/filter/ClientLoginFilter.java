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
import bean.Client;
import servlet.admin.AdminLoginServlet;
import servlet.client.ClientLoginServlet;

/**
 * Servlet Filter implementation class ClientLoginFilter
 */
public class ClientLoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ClientLoginFilter() {
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

		
		

		Client client = (Client) session.getAttribute(ClientLoginServlet.LOGIN_CLIENT);

		if (client == null) {

			System.out.println("ClientLoginFilter:来访者未登录，被拦截：" + path);
			
			// 未登录
			// 重定向到登录页面
			java.io.PrintWriter writer = response.getWriter();
			
			writer.println("<html><script>");
			writer.println("window.open('" + request.getContextPath() + "/client/ClientLoginServlet','_top')");
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
