package com.test.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewProfileServlet
 */
@WebServlet(name = "ViewProfile", urlPatterns = { "/getProfileDetails" })
public class ViewProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get username from session
		System.out.println("Username in profile servlet: " + request.getSession().getAttribute("username"));
		
		// forward control to profile.jsp
		request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
	}

}
