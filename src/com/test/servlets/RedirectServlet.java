package com.test.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedirectServlet
 */
@WebServlet(
			name = "redirectServlet",
			urlPatterns = {"/linkedIn"},
			initParams = {
				@WebInitParam(				
						name = "linkedin",
						value = "https://www.linkedin.com"
						),
				@WebInitParam(				
						name = "weather",
						value = "https://www.weatherservice.com"
						)
			}
)
public class RedirectServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// This is another API call, using the ServletConfig API
		// Every servlet has one ServletConfig object created
		ServletConfig config = getServletConfig();
		System.out.println("weather url: " + config.getInitParameter("weather"));
		System.out.println("---------");
		// The context API makes the parameter available through out the application
		// to all the servlets
		ServletContext context = getServletContext();
		System.out.println(context.getInitParameter("DbURL"));
		response.sendRedirect("http://www.linkedin.com");
	}

}
