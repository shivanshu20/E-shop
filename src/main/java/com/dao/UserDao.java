package com.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bean.User;

public class UserDao implements com.interfaces.User_Interface{

	Connection con = null;
	/*
	 * private static String driver = "com.mysql.jdbc.Driver"; private static String
	 * conUrl = "jdbc:mysql://localhost:3306/shop?user=root" +
	 * "&password=priyanshi";
	 * 
	 * public UserDao() {
	 * 
	 * try {
	 * 
	 * Class.forName(driver); con = DriverManager.getConnection(conUrl); }
	 * catch(Exception e) {
	 * System.out.println("Error in loading drivers or in making connection"); } }
	 */

	// taking connection from DBConnection dao class
	public UserDao() {

		DBConnection obj = new DBConnection();
		con = obj.getConnection();
	}
	
	//authenticating the user for login
	@Override
	public User authenticateUser(String email, String pass) {

		final String p = "select * from user where email=? and password=?";
		
		User u = null;
		try {

			PreparedStatement ps = con.prepareStatement(p);

			ps.setString(1, email);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				u = new User();

				u.setUserName(rs.getString("name"));
				u.setUserEmail(rs.getString("email"));
				u.setUserType(rs.getString("type"));

				ps.close();
				con.close();
				return u;
			}

		} catch (Exception e) {
			System.out.println("Error in authentication of user");
		}

		return u;
	}
	
	
	//getting all user details for admin
	@Override
	public List<User> getAllUsers() {

		List<User> al = new ArrayList<User>();
		String type = "normal";

		final String p = "select * from user where type=?";
		try {
			PreparedStatement ps = con.prepareStatement(p);

			ps.setString(1, type);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User u = new User();
				u.setUserName(rs.getString("name"));
				u.setUserEmail(rs.getString("email"));
				u.setUserPhone(rs.getLong("mobile"));

				al.add(u);
				Collections.sort(al);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Error in get all users method");
		}
		return al;
	}

	// forget password method
	@Override
	public int forgetPassword(String email, String password, long mobile) {

		int status = 0;
		PreparedStatement ps = null;

		final String p = "update user set password=? where email=? and mobile=?";

		try {

			ps = con.prepareStatement(p);

			ps.setString(1, password);

			ps.setString(2, email);
			ps.setLong(3, mobile);

			status = ps.executeUpdate();
		} 
		catch (Exception e) {
			System.out.println("Error in forget password method");
			return -1;
		}
		
		
		finally {
			
			//closing the statement
			try {

				if (ps != null)
					ps.close();
			} 
			
			catch (Exception e) {
				System.out.println("Error in closing statement");
			}
			
			
			//closing the connection
			try {

				if (con != null)
					con.close();
			} 
			catch (Exception e) {
				System.out.println("Error in closing connection");
			}
		}

		return status;
	}
}
