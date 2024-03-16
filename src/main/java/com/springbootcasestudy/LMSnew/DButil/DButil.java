package com.springbootcasestudy.LMSnew.DButil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException {
		
		if(connection != null) {
			return connection;
		}else {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/employee_lms?useSSL=false";
			String username = "root";
			String password = "";
			
			try {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, username, password);
			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}
}
