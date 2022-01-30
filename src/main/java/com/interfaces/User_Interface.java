package com.interfaces;

import java.util.List;

import com.bean.User;

public interface User_Interface {
	
	public User authenticateUser(String email, String password);
	public List<User> getAllUsers();
	public int forgetPassword(String email, String password, long mobileNo);
}
