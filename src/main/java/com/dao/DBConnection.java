package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	

	Connection con = null;
	private static String driver = "com.mysql.jdbc.Driver";
	private static String conUrl = "jdbc:mysql://localhost:3306/shop?user=root"
			+ "&password=priyanshi";
	
	public DBConnection() {
	
	}
	
	
	public Connection getConnection() {

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(conUrl);
		} 
		catch (Exception e) {
			System.out.println("Error in loading drivers or in making connection");
		}
		return con;
	}
}
