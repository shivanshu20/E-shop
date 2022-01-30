package com.interfaces;

import java.util.List;

import com.bean.Category;

public interface Category_Interface {

	// getting the previous category id
	public int getPreviousId();

	// setting the id for category
	public int setCategory(String cName, String cDescription);

	// fetching all the categories for admin
	public List<Category> getAllCategories();

	// getting category by id for update
	public List<Category> getById(String id);

	// updating category by id
	public int updateCategory(int id, String name, String des);

	// deleting a category by id
	public void deleteCategory(String id);
}
