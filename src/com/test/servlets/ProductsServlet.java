package com.test.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.beans.Product;
import com.test.dao.ApplicationDao;

/**
 * Servlet implementation class ProductsServlet
 */
@WebServlet("/addProducts")
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// session management for maintaining the cart
		
		// obtaining the session object
		HttpSession session = request.getSession();
		
		// a cart for putting the product names
		List<String> cart = (ArrayList<String>)session.getAttribute("cart");
		
		if(cart == null) {
			cart = new ArrayList<>(); // case when the user is coming to the cart for the first time
		}
		
		// add the product to cart
		if(request.getParameter("product")!=null) {
			cart.add(request.getParameter("product")); // this product parameter is form element in searchResult.jsp
		}
		
		// maintaining cart in session
		session.setAttribute("cart", cart); // to track the cart for entire user session.
		
		// getting the search term from the session to regenerate the searchResults
		String search = (String) session.getAttribute("search");
		
		ApplicationDao dao = new ApplicationDao();
		List<Product> products = dao.searchProducts(search);
		
		// sending the products through request object to searchResults.jsp
		request.setAttribute("products", products);
		
		// forwarding the control to searchResults.jsp
		
		request.getRequestDispatcher("/jsp/searchResults.jsp").forward(request, response);

	}

}
