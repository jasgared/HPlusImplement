package com.test.dao;

import com.test.beans.Product;
import com.test.beans.User;
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
	
	public int registerUser(User user) {
		int rowsAffected = 0;
		try {
			Connection myConn = DBConnection.getConnectionToDatabase();
			// sql
			String sqlQuery = "insert into users values(?,?,?,?,?,?)";
			PreparedStatement statement = myConn.prepareStatement(sqlQuery);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setInt(5, user.getAge());
			statement.setString(6, user.getActivity());
			// execute
			rowsAffected = statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}
}
