package com.dao;

import com.bean.User;
import com.interfaces.Register_Interface;

import java.sql.Connection;

import java.sql.PreparedStatement;



public class RegisterDao implements Register_Interface{
	
	
	Connection con = null;
	/*
	private static String driver = "com.mysql.jdbc.Driver";
	private static String conUrl = "jdbc:mysql://localhost:3306/shop?user=root"
			+ "&password=priyanshi";
	
	public RegisterDao() {
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(conUrl);
		}
		catch(Exception e) {
			System.out.println("Error in loading drivers or in making connection");
		}
	}
	*/
	
	//taking connection from DBConnection dao class
	public RegisterDao() {
		
		DBConnection obj = new DBConnection();
		con = obj.getConnection();
	}
	
	
	//creating new user or sign up
	@Override
	public int addUser(User u) {
		
		int status = 0;
		
		final String p = "insert into user values(?,?,?,?,?)";
		
		try {
			
			PreparedStatement ps = con.prepareStatement(p);
			
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getUserEmail());
			ps.setLong(3, u.getUserPhone());
			ps.setString(4, u.getUserPass());
			ps.setString(5, "normal");
			
			status = ps.executeUpdate();
			
			ps.close();
			con.close();
			
		}	
		catch(Exception e) {
			System.out.println("Error in addUser method");
		}
		
		return status;
	}

}
