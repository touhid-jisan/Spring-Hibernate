package com.touhid.jdbc;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
		String user = "hbstudent";
		String pass = "hbstudent";
		
		try {
			Connection myConn = (Connection) DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection Successful..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
