package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet Filter implementation class CheckLogin
 */
 @WebFilter("/User/*")
public class CheckLogin implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			chain.doFilter(request, response);
		}else{
			((HttpServletResponse) response).sendRedirect("/myAchievementList/");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}
}
