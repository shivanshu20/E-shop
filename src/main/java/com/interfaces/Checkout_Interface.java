package com.interfaces;

import java.util.List;

import com.bean.Cart;
import com.bean.User;

public interface Checkout_Interface {

	// fetching user details for checkout form
	public List<User> getDetails(String id);

	// inserting order details into order table i.e order is placed
	public int confirmOrder(Cart c, List<Cart> cartItems);

	// removing product from cart after checkout
	public void removeProduct(String email, List<Cart> cartItems);
}
