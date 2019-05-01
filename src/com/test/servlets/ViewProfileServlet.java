package com.test.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.User;
import com.test.dao.ApplicationDao;

/**
 * Servlet implementation class ViewProfileServlet
 */
@WebServlet(name = "ViewProfile", urlPatterns = { "/getProfileDetails" })
public class ViewProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = (String) request.getSession().getAttribute("username");
		// get username from session
		System.out.println("Username in profile servlet: " + request.getSession().getAttribute("username"));
		
		// call dao
		ApplicationDao dao = new ApplicationDao();
		User user = dao.getProfileDetails(username);
		
		request.setAttribute("user", user);
		
		// adding weight attribute to request object
		Map<String , Double> weightSummary = new HashMap<>();
		weightSummary.put("January", 75.0);
		weightSummary.put("February", 73.0);
		weightSummary.put("March", 74.0);
		weightSummary.put("April", 73.75);
		
		request.setAttribute("weightSummary", weightSummary);
		
		// forward control to profile.jsp
		request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
	}

}
