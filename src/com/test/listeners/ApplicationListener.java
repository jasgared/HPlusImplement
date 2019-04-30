package com.test.listeners;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.test.dao.DBConnection;

/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
public class ApplicationListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("in contextDestroy method");
    	Connection myConn = (Connection) sce.getServletContext().getAttribute("dbconnection");
    	try {
			myConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("in contextInit method");
    	Connection myConn = DBConnection.getConnectionToDatabase();
    	sce.getServletContext().setAttribute("dbconnection", myConn);
    }
	
}
