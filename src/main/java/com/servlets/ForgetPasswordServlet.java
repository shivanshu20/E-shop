package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;



public class ForgetPasswordServlet extends HttpServlet {
	
       
 
    public ForgetPasswordServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		
		if(email == null) {
			
			response.sendRedirect("components/error.jsp");
			return;
		}
		
		long mobile = Long.parseLong(request.getParameter("phone"));	
		String pass = request.getParameter("pass");
		
		UserDao ud = new UserDao();
		int x = ud.forgetPassword(email, pass, mobile);
		
		if(x == -1)
			request.setAttribute("msg", "Something went wrong!");
		
		else if(x >= 1)
			request.setAttribute("msg", "Password updated successfully!");
		
		else
			request.setAttribute("msg", "Invalid username or phone number");
		
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/forgetPassword.jsp");
		rd.include(request, response);
	}

}
