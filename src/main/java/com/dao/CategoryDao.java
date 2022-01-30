package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bean.Category;
import com.interfaces.Category_Interface;


public class CategoryDao implements Category_Interface{

	Connection con = null;

	public CategoryDao(){
		
		DBConnection obj = new DBConnection();
		con = obj.getConnection();
	}
	
	
	//getting the previous category id
	@Override
	public int getPreviousId() {
		
		final String p = "select * from category";
		try {
			
			PreparedStatement ps = con.prepareStatement(p);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next() == true) {
				
				int x = 0;
				
				while(rs.next()) {
					x = rs.getInt(1);
				}
				ps.close();
				//con.close();
				return x;
			}
			
		}
		
		catch(Exception e) {
			System.out.println("Error in getting id from db");
		}
		return 0;
	}

	
	//setting the id for category
	@Override
	public int setCategory(String cName, String cDescription) {
		
		int status = 0;
		
		//generating id for category
		int id = 0;
		
		int x = getPreviousId();
		
		if (x == 0) {
			id = Category.generateCatId();
		}

		// adding one to previous category id
		else {
			x = x + 1;
			id = x;
		}
		
		//first one is id, second is name, third is description
		final String p = "insert into category values(?,?,?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(p);
			
			ps.setInt(1, id);
			ps.setString(2, cName);
			ps.setString(3, cDescription);
			
			status = ps.executeUpdate();
			
			ps.close();
			con.close();
		}
		catch(Exception e) {
			System.out.println("Error in insert category method");
		}
		return status;
	}
	
	
	//fetching all the categories for admin
	@Override
	public List<Category> getAllCategories(){
		
		List<Category> al = new ArrayList<Category>();
		
		final String p = "select * from category";
		
		try {
			
			PreparedStatement ps = con.prepareStatement(p);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Category c = new Category();
				
				c.setId(rs.getInt("c_id"));
				c.setCategoryTitle(rs.getString("c_name"));
				c.setCategoryDescription(rs.getString("c_des"));
				
				al.add(c);
				Collections.sort(al);;
			}
			ps.close();
			con.close();
		}
		catch(Exception e) {
			
		}
		
		return al;
	}
	

	//getting category by id for update
	@Override
	public List<Category> getById(String id){
		
		List<Category> al = new ArrayList<Category>();
	
		//converting string type id to int
		int x = Integer.parseInt(id);
		
		final String p = "select * from category where c_id=?";
		try {
			
			PreparedStatement ps = con.prepareStatement(p);
			
			ps.setInt(1, x);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Category c = new Category();
				
				
				c.setId(rs.getInt("c_id"));
				c.setCategoryTitle(rs.getString("c_name"));
				c.setCategoryDescription(rs.getString("c_des"));
				
				al.add(c);
			}
			
			ps.close();
			con.close();
			
		}
		catch(Exception e) {
			System.out.println("Error in getById method of category update");
		}
		
		return al;
	}
	

	//updating category by id
	@Override
	public int updateCategory(int id, String name, String des) {
		
		int status = 0;
		//int x = Integer.parseInt(id);
		final String p = "update category set c_name=?, c_des=? where c_id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(p);
			
			ps.setString(1, name);
			ps.setString(2, des);
			
			ps.setInt(3, id);
			
			status = ps.executeUpdate();
			
			ps.close();
			con.close();
		}
		catch(Exception e) {
			System.out.println("Error in update category method");
		}
		
		return status;
	}
	
	
	
	//deleting a category by id
	@Override
	public void deleteCategory(String id) {
		
		
		int x = Integer.parseInt(id);
		
		final String p = "delete from category where c_id=?";
		
		try {
			
			PreparedStatement ps = con.prepareStatement(p);
			
			ps.setInt(1, x);
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
		}
		catch(Exception e) {
			
			System.out.println("Error in delete category method");
		}
		
	}
}
