package com.test.dao;

import com.test.beans.Product;
import java.util.*;
import java.sql.*;

public class ApplicationDao {
	
	public List<Product> searchProducts(String searchTerm){
		List<Product> products = new ArrayList<>();
		try {
			Connection myConn = DBConnection.getConnectionToDatabase();
			Product product = null;
			Statement stmt = myConn.createStatement();
			String query = "SELECT * FROM products where product_name LIKE '%" + searchTerm + "%'";
			ResultSet res = stmt.executeQuery(query);
			while(res.next()) {
				product = new Product();
				product.setProductId(res.getInt("product_id"));
				product.setProductImgPath(res.getString("image_path"));
				product.setProductName(res.getString("product_name"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
}
