package com.bean;

public class Product implements Comparable<Product>{
	
	private int pId;
	private static int id=1000;
	private String pName;
	private String pDescription;
	private String pImg;
	private int pPrice;
	private int pDiscount;
	private int pQuantity;
	private int cId;
	private String pCatName;
	
	public Product() {
		super();
	}

	//getter and setters
	
	
	public static int generateId() {
		
		return ++id;
	}
	
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}

	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}

	
	public String getpDescription() {
		return pDescription;
	}
	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}

	
	public String getpImg() {
		return pImg;
	}
	public void setpImg(String pImg) {
		this.pImg = pImg;
	}

	
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	
	public int getpDiscount() {
		return pDiscount;
	}
	public void setpDiscount(int pDiscount) {
		this.pDiscount = pDiscount;
	}

	
	public int getpQuantity() {
		return pQuantity;
	}
	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}

	
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	
	
	public String getCatName() {
		return pCatName;
	}
	public void setPCatName(String pCatName) {
		this.pCatName = pCatName;
	}
	
	@Override
	public String toString() {
		
		return pId + " " + pName +" " + pDescription +" "+ pPrice +" " + pDiscount + " "+pQuantity+""
				+ ""+cId ; 
	}
	
	
	//sorting the object with id
	@Override
	public int compareTo(Product that) {
	
		return this.pId - that.pId;
	}
	
	
}
