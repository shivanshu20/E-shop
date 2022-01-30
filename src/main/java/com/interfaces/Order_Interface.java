package com.interfaces;

import java.util.List;

import com.bean.Cart;

public interface Order_Interface {
	
	//fetching all orders for Admin
	public List<Cart> getAllOrders();
	
	//fetching all orders for particular user
	public List<Cart> userOrders(String email);
	

	// removing order after delivered
	public void orderDelivered(int pid, String cid);
}
