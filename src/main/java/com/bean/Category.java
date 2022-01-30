package com.bean;


import java.util.concurrent.atomic.AtomicInteger;



public class Category implements Comparable<Category>{

	
	private static int cId = 100;
	private String cTitle;
	private String cDescription;
	private int id;
	
	//private static int min = 101;
	//private static int max = 501;
	
	
	static final AtomicInteger at = new AtomicInteger(100);
	public Category(){
		super();
	}
	
	//getter and setters
	
	public int getId() {
		
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public static int generateCatId() {
		//int id1 = (int)(Math.random()*(max - min + 1) + min);
		
		//int id1 = at.incrementAndGet();
		
		return ++cId;
	}
	
	
	public String getCategoryTitle() {
		return cTitle;
	}
	public void setCategoryTitle(String cTitle) {
		this.cTitle = cTitle;
	}
	
	
	public String getCategoryDescription() {
		return cDescription;
	}

	public void setCategoryDescription(String cDescription) {
		this.cDescription = cDescription;
	}
	
	
	@Override
	public String toString() {
		
		return cId+" "+cTitle+" "+cDescription;
	}
	
	//sorting the user defined list
	
	@Override
	public int compareTo(Category that) {
		
		return this.id - that.id;
	}
	
}
