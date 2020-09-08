package com.touhid.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.*;

import com.mysql.jdbc.Connection;
/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String user = "hbstudent";
		String pass = "hbstudent";
		String driver = "com.sql.jdbc.Driver";
		
		try {
			PrintWriter out = response.getWriter();
			Class.forName(driver);
			Connection myConn = (Connection) DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection Successful..");
			myConn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
