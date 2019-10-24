package utils.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.MessageServlet;

/**
 * @author h w j
 * @instruction
 * 留言模块过滤器（必须登录）
 */
public class MessageBoardFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MessageBoardFilter() {
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
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;

		HttpServletResponse response = (HttpServletResponse) res;

		//得到当前登录的用户
		Map<String, Object> currentUser = MessageServlet.getCurrentUser(request);

		String str = "<html><script>";
		
		if (currentUser.get("reqeustUser").equals("no")) {

			str +="alert('请先登录!');window.location.history.back(-1)";
			str += "</script></html>";
			java.io.PrintWriter writer = response.getWriter();
			writer.println(str);
			writer.close();
			return ;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
