package com.servlets;

import com.bean.User;
import com.dao.RegisterDao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class RegisterServlet extends HttpServlet {
	
    public RegisterServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		if(name == null || email == null) {
			
			response.sendRedirect("components/error.jsp");
			return;
		}
		
		long mobNo = Long.parseLong(request.getParameter("phone"));
		String pass = request.getParameter("pass");
		
		User u = new User();
		
		u.setUserName(name);
		u.setUserEmail(email);	//primary key
		u.setUserPhone(mobNo);
		u.setUserPass(pass);
		
		RegisterDao rd = new RegisterDao();
		
		int status = rd.addUser(u);
		
		
		if(status >= 1)
			request.setAttribute("msg", "Registered successfully");
		else
			request.setAttribute("msg", "Could not register");
		
		
		RequestDispatcher r = getServletContext().getRequestDispatcher("/register.jsp");
		r.include(request, response);
	}
}
