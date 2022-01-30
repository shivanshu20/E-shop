package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.bean.Cart;
import com.bean.User;
import com.interfaces.Checkout_Interface;

public class CheckoutDao implements Checkout_Interface{

	Connection con = null;

	public CheckoutDao() {

		DBConnection db = new DBConnection();
		con = db.getConnection();
	}

	// fetching user details for checkout form
	@Override
	public List<User> getDetails(String id) {

		List<User> list = new ArrayList<User>();

		final String p = "select * from user where email=?";

		try {

			PreparedStatement ps = con.prepareStatement(p);

			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				User u = new User();

				u.setUserName(rs.getString("name"));
				u.setUserEmail(rs.getString("email"));
				u.setUserPhone(rs.getLong("mobile"));

				list.add(u);
			}
			ps.close();

		} catch (Exception e) {
			System.out.println("Error in getting details of user");
		}

		return list;
	}

	// inserting order details into order table i.e order is placed
	@Override
	public int confirmOrder(Cart c, List<Cart> cartItems) {

		// getting current date of order

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String date = sdf.format(cal.getTime());

		final String p = "insert into orders values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		int status = 0;

		for (int i = 0; i < cartItems.size(); i++) {

			Cart cart = cartItems.get(i);

			try {

				ps = con.prepareStatement(p);

				// customer details
				ps.setString(1, c.getCusName());
				ps.setString(2, c.getCusEmail());
				ps.setLong(3, c.getCusMobile());
				ps.setString(4, c.getCusAddress());

				// product details
				ps.setInt(5, cart.getpId()); // product id of purchased product
				ps.setString(6, cart.getpName());
				ps.setInt(7, cart.getpPrice());
				ps.setInt(8, cart.getpQuantity());
				
				
				//setting current date
				ps.setString(9, date);
				
				//setting product image
				ps.setString(10, cart.getPImage());
				
				
				status = ps.executeUpdate();
			}

			catch (Exception e) {
				System.out.println("Error in cofirm order method");
			}
		}

		try {
			ps.close();
		} catch (Exception e) {
			System.out.println("Error in closing ps and con in confirm order");
		}

		return status;
	}

	// removing product from cart after checkout
	@Override
	public void removeProduct(String email, List<Cart> cartItems) {

		final String p = "delete from cart where p_id=? and customer=?";
		PreparedStatement ps = null;

		for (int i = 0; i < cartItems.size(); i++) {

			Cart cart = (Cart) cartItems.get(i);
			int pId = cart.getpId();

			try {

				ps = con.prepareStatement(p);

				ps.setInt(1, pId);
				ps.setString(2, email);

				ps.executeUpdate();

			}

			catch (Exception e) {
				System.out.println("Error in removing product");
			}
		}

		try {
			ps.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Error in closing ps and con in removing product");
		}
	}

}
