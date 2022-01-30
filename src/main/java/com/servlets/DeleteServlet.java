package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CategoryDao;
import com.dao.ProductDao;


public class DeleteServlet extends HttpServlet {

   
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		if(request.getParameter("id") == null) {
			
			response.sendRedirect("components/error.jsp");
			return;
		}
		
		String page = request.getParameter("page");
		
		//for category page
		if(page.equals("category")) {
			
			String id = request.getParameter("id");
			
			CategoryDao cd = new CategoryDao();
			cd.deleteCategory(id);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewCategories.jsp");
			rd.include(request, response);
		}
		
		//for product page
		else if(page.equals("product")){
			
			String id = request.getParameter("id");
			//System.out.println(id);
			
			ProductDao pd = new ProductDao();
			
			pd.deleteProduct(id);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewProducts.jsp");
			rd.include(request, response);
		}
	}

}
