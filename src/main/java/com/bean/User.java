package com.bean;


public class User implements Comparable<User>{
	
	private int userId;
	private String userName;
	private String userEmail;
	private String userPass;
	private long userPhone;
	private String userAddress;
	private String userType;
	

	public User() {
		super();
	}
	
	
	//getter and setters 
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	public long getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}
	
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Override
	public String toString() {
		
		return userId+" "+userName+" "+userEmail+" "+userPass+" "+userPhone+" "+userAddress;
	}
	

	//sorting the user defined list or sorting the list of users
	@Override
	public int compareTo(User that) {
		
		return this.userName.compareToIgnoreCase(that.userName);
	}
}
