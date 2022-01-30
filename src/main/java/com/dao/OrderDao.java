package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.Cart;
import com.interfaces.Order_Interface;


public class OrderDao implements Order_Interface{

	Connection con = null;

	public OrderDao() {

		DBConnection db = new DBConnection();
		con = db.getConnection();
	}
	
	
	//fetching all order for admin
	@Override
	public List<Cart> getAllOrders() {

		List<Cart> al = new ArrayList<Cart>();

		final String p = "select * from orders ";

		PreparedStatement ps = null;
		try {

			ps = con.prepareStatement(p);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Cart c = new Cart();

				c.setpId(rs.getInt("p_id"));
				c.setpName(rs.getString("p_name"));
				c.setpPrice(rs.getInt("p_price"));
				c.setpQuantity(rs.getInt("p_quantity"));
				
				c.setCusName(rs.getString("c_name"));
				c.setCusEmail(rs.getString("c_email"));
				c.setCusMobile(rs.getLong("c_mobile"));
				c.setCusAddress(rs.getString("c_address"));
				
				c.setcDate(rs.getString("date"));
				
				c.setPImage(rs.getString("p_image"));
				al.add(c);

			}
		} catch (Exception e) {
			System.out.println("Error in getting orders of user for admin");
		}

		finally {

			try {

				if (ps != null)
					ps.close();
			}

			catch (Exception e) {
				System.out.println("Error in closing prepared statement");
			}

			try {

				if (con != null)
					con.close();
			}

			catch (Exception e) {
				System.out.println("Error in closing connection");
			}

		}

		return al;
	}

	
	// getting user order for particular customer
	@Override
	public List<Cart> userOrders(String email) {

		List<Cart> al = new ArrayList<Cart>();

		final String p = "select * from orders where c_email=?";
		PreparedStatement ps = null;

		try {

			ps = con.prepareStatement(p);

			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				
				Cart c = new Cart();
				
				c.setpName(rs.getString("p_name"));
				c.setpPrice(rs.getInt("p_price"));
				c.setpQuantity(rs.getInt("p_quantity"));
				c.setPImage(rs.getString("p_image"));
				c.setcDate(rs.getString("date"));
				c.setPImage(rs.getString("p_image"));
				
				
				al.add(c);

			}
		} catch (Exception e) {
			System.out.println("Error in getting orders of user");
		}

		finally {

			try {

				if (ps != null)
					ps.close();
			}

			catch (Exception e) {
				System.out.println("Error in closing prepared statement");
			}

			try {

				if (con != null)
					con.close();
			}

			catch (Exception e) {
				System.out.println("Error in closing connection");
			}

		}

		return al;
	}

	
	// removing order after delivered
	@Override
	public void orderDelivered(int pid, String cid) {

		final String p = "delete from orders where p_id=? and c_email=?";
		PreparedStatement ps = null;
		try {

			ps = con.prepareStatement(p);

			ps.setInt(1, pid);
			ps.setString(2, cid);

			ps.executeUpdate();
		}

		catch (Exception e) {
			System.out.println("Error in order delivered method");
		}

		finally {

			try {

				if (ps != null)
					ps.close();
			}

			catch (Exception e) {
				System.out.println("Error in closing prepared statement");
			}

			try {

				if (con != null)
					con.close();
			}

			catch (Exception e) {
				System.out.println("Error in closing connection");
			}

		}
	}
}
