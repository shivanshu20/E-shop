package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.OrderDao;


public class DeliverServlet extends HttpServlet {
	
    public DeliverServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pid = request.getParameter("pid");
		String cid = request.getParameter("cid");
		
		if(pid == null || cid == null) {
			
			response.sendRedirect("components/error.jsp");
			return;
		}
		
		int pId = Integer.parseInt(pid);
		
		
		//removing order history from order table after delivery
		OrderDao obj = new OrderDao();
		obj.orderDelivered(pId, cid);
		
		response.sendRedirect("viewOrders.jsp");
		
	}

}
