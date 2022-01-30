package com.interfaces;

import java.util.List;

import javax.servlet.http.Part;

import com.bean.Cart;
import com.bean.Product;

public interface Product_Interface {

	// getting the previous product id
	public int getPreviousId();

	// adding the new product
	public int addProduct(Product pd);

	// uploading image to server or folder
	public boolean uploadImage(String path, Part part);

	// getting all products from database for admin
	public List<Product> getAllProducts();

	// fetching product by id for update
	public List<Product> getById(String id);

	// updating product details
	public int updateProduct(Product pd);

	// deleting product by id
	public void deleteProduct(String id);

	// for loading category wise product on index page
	public List<Product> getByCategoryId(String id);

	// updating product quantity after order
	public void updateQuantity(List<Cart> cartItems);
}
