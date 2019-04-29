package com.test.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.Product;
import com.test.dao.ApplicationDao;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String searchItem = request.getParameter("search");
		
		request.getSession().setAttribute("search", searchItem); // search term to session, so can generate results through ProductsServlet
		
		// Send search item to search in DB
		ApplicationDao dao = new ApplicationDao();
		List<Product> products = dao.searchProducts(searchItem);
		
//		// show the result from db on the page using servlet
//		String page = getHTMLString(request.getServletContext().getRealPath("/html/searchResults.html"),products);
		
		// setting attribute so that it can be passed with products
		request.setAttribute("products", products);
		
		// printing on page using jsp
		request.getRequestDispatcher("/jsp/searchResults.jsp").forward(request, response);
				
	}

	
	public String getHTMLString(String filepath, List<Product> products) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filepath));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		String page = buffer.toString();
		page = MessageFormat.format(page, 
				products.get(0).getProductImgPath(),products.get(1).getProductImgPath(),products.get(2).getProductImgPath(),
				products.get(0).getProductName(),products.get(1).getProductName(),products.get(2).getProductName(),
				0);
		return page;
	}

}
