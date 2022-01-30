package com.bean;

public class Cart {

	private int pId;
	private String pName;
	private String pDes;
	private int pQuantity;
	private int pPrice;
	private String pImage;

	private String cusName;
	private String cusEmail;
	private long cusMobile;
	private String cusAddress;

	private String cDate;

	public Cart() {
		super();
	}
	
	public String getPImage() {
		return pImage;
	}
	public void setPImage(String pImage) {
		this.pImage = pImage;
	}
	
	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public String getCusEmail() {
		return cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public long getCusMobile() {
		return cusMobile;
	}

	public void setCusMobile(long cusMobile) {
		this.cusMobile = cusMobile;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
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

	public String getpDes() {
		return pDes;
	}

	public void setpDes(String pDes) {
		this.pDes = pDes;
	}

	public int getpQuantity() {
		return pQuantity;
	}

	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

}
