package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.Cart;
import com.bean.Product;
import com.interfaces.Cart_Interface;

public class CartDao implements Cart_Interface{
	
	Connection con = null;
	
	public CartDao() {
		
		DBConnection db = new DBConnection();
		con = db.getConnection();
		
	}
	
	
	//adding product to cart
	@Override
	public void addToCart(String id, String cusEmail) {
		
		//product details
		
		ProductDao pd = new ProductDao();
		List<Product> al = pd.getById(id);
		Product pr = (Product)al.get(0);
		
		int pId = Integer.parseInt(id);
		int q = 1;
		
		final String p = "insert into cart values(?,?,?,?,?,?,?)";
		
		try {
			
			PreparedStatement ps = con.prepareStatement(p);
			
			ps.setInt(1, pId);
			ps.setString(2, pr.getpName());
			ps.setString(3, pr.getpDescription());
			ps.setInt(4, pr.getpPrice());
			ps.setInt(5, q);
			ps.setString(6, cusEmail);
			ps.setString(7, pr.getpImg());			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			
		}
		catch(Exception e) {
			System.out.println("Error in addToCart method");
		}
	}
	
	
	//showing cart products and getting for confirm order
	@Override
	public List<Cart> getCartById(String cId){
		
		List<Cart> al = new ArrayList<Cart>();
		
		final String  p = "select * from cart where customer=?";
		
		try {
			
			PreparedStatement ps = con.prepareStatement(p);
			ps.setString(1, cId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Cart c = new Cart();
				c.setpId(rs.getInt("p_id"));
				c.setpName(rs.getString("p_name"));
				c.setpDes(rs.getString("p_des"));
				c.setpQuantity(rs.getInt("p_quantity"));
				c.setpPrice(rs.getInt("p_price"));
				c.setPImage(rs.getString("p_image"));
				
				al.add(c);
				
			}
			ps.close();
			con.close();

		}
		catch(Exception e) {
			System.out.println("Error in getting values of cart by id");
		}
		
		return al;
	}
	
	
	//updating cart i.e increasing the quantity of product
	@Override
	public void updateCart(String pId, String email, int quantity) {
		
		
		final String p = "update cart set p_quantity=? where p_id=? and customer=?";
		int id = Integer.parseInt(pId);
		
		try {
			
			PreparedStatement ps = con.prepareStatement(p);
			
			ps.setInt(1, quantity);
			ps.setInt(2, id);
			ps.setString(3, email);
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			
			
		}
		catch(Exception e) {
			System.out.println("Error in updating cart product quantity");
		}
	}
	
	
	//checking if product is already in cart or not
	@Override
	public int check(String pId, String email) {
		
		final String p = "select * from cart where p_id=? and customer=?";
		
		int id = Integer.parseInt(pId);
		
		try {
			
			PreparedStatement ps = con.prepareStatement(p);
			
			ps.setInt(1, id);
			ps.setString(2, email);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				return rs.getInt("p_quantity");
			}
		}
		catch(Exception e) {
			System.out.println("Error in checking product method");
		}
		
		return 0;
	}
	
	
	//deleting product from cart
	@Override
	public void removeProductCart(String pId, String email) {
		
		final String p = "delete from cart where p_id=? and customer=?";
		
		int id = Integer.parseInt(pId);
		
		
		try {
			
			PreparedStatement ps = con.prepareStatement(p);
			
			
			ps.setInt(1, id);
			
			ps.setString(2, email);
			
			
			
			ps.executeUpdate();
			
			
			ps.close();
			con.close();
			
			
		}
		catch(Exception e) {
			System.out.println("Error in removing product from cart");
		}
	}
}
