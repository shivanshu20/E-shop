package com.interfaces;

import java.util.List;

import com.bean.Cart;

public interface Cart_Interface {

	// adding product to cart
	public void addToCart(String id, String cusEmail);

	// showing cart products and getting for confirm order
	public List<Cart> getCartById(String cId);

	// updating cart i.e increasing the quantity of product
	public void updateCart(String pId, String email, int quantity);

	// checking if product is already in cart or not
	public int check(String pId, String email);

	// deleting product from cart
	public void removeProductCart(String pId, String email);

}
