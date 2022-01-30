package com.servlets;

import com.dao.CategoryDao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class AddCategoryServlet extends HttpServlet {
	

    public AddCategoryServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cName = request.getParameter("cname");
		String cDescription = request.getParameter("cdescription");
		
		if(cName == null || cDescription == null) {
			
			response.sendRedirect("components/error.jsp");
			return;
		}
			
			
		CategoryDao obj = new CategoryDao();
		
		int x = obj.setCategory(cName, cDescription);
		
		if(x >= 1) {
			request.setAttribute("msg", "Category added successfully");
		}
		else {
			request.setAttribute("msg", "Could not be added");
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/addcategory.jsp");
		rd.include(request, response);
	}

}
