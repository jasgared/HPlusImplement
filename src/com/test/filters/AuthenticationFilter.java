package com.test.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy phase");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		// cast the generic response to HttpResponse
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		// get the session check what pages are being accessed
		if(req.getRequestURI().startsWith("/HPlusImplement/getProfileDetails") ||
				req.getRequestURI().startsWith("/HplusImplement/orderHistory")) {
			HttpSession session = req.getSession();
			if(session.getAttribute("username") == null) {
				req.getRequestDispatcher("/jsp/login.jsp").forward(req, response);
			}
		}

		// pass the request along the filter chain
		chain.doFilter(req, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Init phase");
	}

}
