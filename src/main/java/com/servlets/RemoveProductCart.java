package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CartDao;


public class RemoveProductCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RemoveProductCart() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String id = request.getParameter("id");
		String cid = request.getParameter("cid");
		
		
		if(id == null || cid == null) {
			
			response.sendRedirect("components/error.jsp");
			return;
		}
		
		//System.out.println(id + " " + cid);
		
		CartDao cd = new CartDao();
		cd.removeProductCart(id, cid);
		
		response.sendRedirect("normal.jsp");
	}

}
