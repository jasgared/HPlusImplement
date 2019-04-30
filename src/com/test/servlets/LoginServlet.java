package com.test.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.ApplicationDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("entered the get method");
		String html = "<html><h3>Please Login</h3></html>";
		response.getWriter().write(html);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
		// to just forward the control can user forward method
		// dispatcher.forward(request, response);
		
		// to also include the response from the current servlet and that of from the forwarded resource
		dispatcher.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get username from login form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// call doa method for validation
		ApplicationDao dao = new ApplicationDao();
		boolean isValidUser = dao.validateUser(username, password);
		
		if(isValidUser) {
			System.out.println("entered valid user");
			// set up the HTTP Session
			HttpSession session = request.getSession();
			
			System.out.println("session created");
			
			// set username as attribute
			session.setAttribute("username", username);
			
			// forward to home.jsp
			request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);			
		}
		else {
			String errorMessage = "Invalid Credentials, Please login Again";
			request.setAttribute("error", errorMessage);
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}
		
		
	}

}
