package com.dao;

import com.bean.Cart;

import com.bean.Product;
import com.interfaces.Product_Interface;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.Part;

public class ProductDao implements Product_Interface{

	Connection con = null;

	/// taking connection
	public ProductDao() {

		DBConnection db = new DBConnection();
		con = db.getConnection();
	}

	
	// getting the previous product id
	@Override
	public int getPreviousId() {

		final String p = "select * from products";
		try {

			PreparedStatement ps = con.prepareStatement(p);
			ResultSet rs = ps.executeQuery();

			// System.out.println("ResultSet"+rs.next());
			if (rs.next() == true) {

				int x = 0;

				while (rs.next()) {
					x = rs.getInt(1);
				}
				ps.close();
				// con.close();
				return x;
			}

		}

		catch (Exception e) {
			System.out.println("Error in getting id from db");
		}
		return 0;
	}

	//adding the new product
	@Override
	public int addProduct(Product pd) {

		int status = 0;

		// generating id for category
		int id = 0;

		int x = getPreviousId();

		if (x == 0) {
			id = Product.generateId();
		}

		// adding one to previous product id
		else {
			x = x + 1;
			id = x;
		}

		final String p = "insert into products values(?,?,?,?,?,?,?)";

		try {

			PreparedStatement ps = con.prepareStatement(p);

			ps.setInt(1, id); // product id
			ps.setString(2, pd.getpName()); // product name
			ps.setString(3, pd.getpDescription()); // product description
			ps.setInt(4, pd.getpPrice()); // product price
			ps.setInt(5, pd.getpQuantity()); // product quantity
			ps.setString(6, pd.getpImg()); // product image name
			ps.setInt(7, pd.getcId()); // category of product

			status = ps.executeUpdate();

			ps.close();

		} catch (Exception e) {
			System.out.println("Error in addProduct method");
		}

		return status;
	}

	// uploading image to server or folder
	@Override
	public boolean uploadImage(String path, Part part) {

		boolean valid = true;
		try {

			FileOutputStream fos = new FileOutputStream(path);

			InputStream input = part.getInputStream();

			// reading data

			byte data[] = new byte[input.available()];

			input.read(data);

			// writing data
			fos.write(data);

			fos.close();
			input.close();

			return valid;

		} catch (Exception e) {
			System.out.println("Error in uploadImage method");

			valid = false;
		}

		return valid;
	}

	// getting all products from database for admin
	@Override
	public List<Product> getAllProducts() {

		List<Product> al = new ArrayList<Product>();

		final String p = "select * from products";

		try {

			PreparedStatement ps = con.prepareStatement(p);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Product pd = new Product();

				pd.setpId(rs.getInt("p_id"));
				pd.setpName(rs.getString("p_name"));
				pd.setpDescription(rs.getString("p_des"));
				pd.setpPrice(rs.getInt("p_price"));
				pd.setpQuantity(rs.getInt("p_quantity"));
				pd.setcId(rs.getInt("c_id"));
				pd.setpImg(rs.getString("p_image"));

				al.add(pd);
				Collections.sort(al);
			}
			ps.close();

		} catch (Exception e) {
			System.out.println("Error in getAllProducts method");
		}

		return al;
	}

	// fetching product by id for update
	@Override
	public List<Product> getById(String id) {

		List<Product> al = new ArrayList<Product>();

		// converting string type id to int
		int x = Integer.parseInt(id);

		final String p = "select * from products where p_id=?";
		try {

			PreparedStatement ps = con.prepareStatement(p);

			ps.setInt(1, x);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Product pd = new Product();

				pd.setpName(rs.getString("p_name"));
				pd.setpDescription(rs.getString("p_des"));
				pd.setpPrice(rs.getInt("p_price"));
				pd.setpQuantity(rs.getInt("p_quantity"));
				pd.setpImg(rs.getString("p_image"));

				al.add(pd);
			}

			ps.close();
			con.close();

		} catch (Exception e) {
			System.out.println("Error in getById method of category update");
		}

		return al;
	}

	// updating product details
	@Override
	public int updateProduct(Product pd) {

		int status = 0;
		final String p = "update products set p_name=?, p_des=?, p_price=?, p_quantity=?,"
				+ "p_image=?, c_id=? where p_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(p);

			ps.setString(1, pd.getpName());
			ps.setString(2, pd.getpDescription());
			ps.setInt(3, pd.getpPrice());
			ps.setInt(4, pd.getpQuantity());
			ps.setString(5, pd.getpImg());
			ps.setInt(6, pd.getcId());

			ps.setInt(7, pd.getpId());

			status = ps.executeUpdate();

			ps.close();
			con.close();
		} catch (Exception e) {

			System.out.println("Error in updateProduct method");
		}
		return status;
	}

	// deleting product by id
	@Override
	public void deleteProduct(String id) {

		int x = Integer.parseInt(id);

		final String p = "delete from products where p_id=?";

		try {

			PreparedStatement ps = con.prepareStatement(p);

			ps.setInt(1, x);

			ps.executeUpdate();

			ps.close();
			con.close();
		} catch (Exception e) {

			System.out.println("Error in delete category method");
		}

	}

	// for loading category wise product on index page
	@Override
	public List<Product> getByCategoryId(String id) {

		List<Product> al = new ArrayList<Product>();
		int cId = Integer.parseInt(id);

		final String p = "select * from products where c_id=?";

		try {
			PreparedStatement ps = con.prepareStatement(p);

			ps.setInt(1, cId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Product pd = new Product();

				pd.setpId(rs.getInt("p_id"));
				pd.setpName(rs.getString("p_name"));
				pd.setpDescription(rs.getString("p_des"));
				pd.setpPrice(rs.getInt("p_price"));
				pd.setpQuantity(rs.getInt("p_quantity"));
				pd.setcId(rs.getInt("c_id"));
				pd.setpImg(rs.getString("p_image"));

				al.add(pd);
				Collections.sort(al);
			}
			ps.close();

		} catch (Exception e) {
			System.out.println("Error in getByCategory method");
		}

		return al;
	}

	// updating product quantity after order
	@Override
	public void updateQuantity(List<Cart> cartItems) {

		PreparedStatement ps = null;

		for (int i = 0; i < cartItems.size(); i++) {

			Cart c = (Cart) cartItems.get(i);
			//System.out.println(c.getpId() +" "+ c.getpQuantity());
			
			final String p = "update products set p_quantity=p_quantity-? where p_id=?";

			try {
				
				ps = con.prepareStatement(p);
				
				ps.setInt(1, c.getpQuantity());
				ps.setInt(2, c.getpId());

				ps.executeUpdate();

			}

			catch (Exception e) {
				System.out.println("Error in updating quantity method");
			}
		}

		try {
			ps.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Error in closing statement or connection");
		}
	}

}
