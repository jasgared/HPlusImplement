package com.test.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.User;
import com.test.dao.ApplicationDao;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet("/registerUser")
public class RegisterUserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the data
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String activity = request.getParameter("activity");
		int age = Integer.parseInt(request.getParameter("age"));
		
		// put the data in bean
		User user = new User(username, password, fname, lname, age, activity); 
		
		
		// send it dao
		ApplicationDao dao = new ApplicationDao();
		int rows = dao.registerUser(user);
		
		String message = null;
		// write to page
		if(rows == 1) {
			message = "User registered successfully";
		}
		else {
			message = "User not registered";
		}
		String page = getHTMLString(request.getServletContext().getRealPath("/html/register.html"),message);
		response.getWriter().write(page);
		
	}
	
	public String getHTMLString(String filepath, String message) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filepath));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		String page = buffer.toString();
		page = MessageFormat.format(page, message);
		return page;
	}
	
	// doGet method for the hyperlink newUser in form
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = getHTMLString(request.getServletContext().getRealPath("/html/register.html"), "");
		response.getWriter().write(page);
	}

}
