package com.touhid.jdbc;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
		String user = "hbstudent";
		String pass = "hbstudent";
		try {
			System.out.println("Connectiong to database : " + jdbcUrl);
			Connection myConn = (Connection) DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection Successful");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
