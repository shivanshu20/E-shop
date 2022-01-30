package com.servlets;

import com.bean.User;
import com.dao.UserDao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LoginServlet extends HttpServlet {
	
    public LoginServlet() {
        super();
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		
		//getting values from login page
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		
		UserDao ud = new UserDao();
		
		
		User user =  ud.authenticateUser(email, pass);
		HttpSession session = request.getSession();
		
		if(user == null) {
			request.setAttribute("msg", "Invalid username or password!");
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.include(request, response);		
			return;
		}
		else {
			
			session.setAttribute("user", user);
			
			//admin
			if(user.getUserType().equals("admin")) {
				
				response.sendRedirect("admin.jsp");
			}
			
			//customer
			else if(user.getUserType().equals("normal")) {
				response.sendRedirect("normal.jsp");
			}
			
			else {
				out.println("Sorry we haven't identified your user type");
				
			}
		}
	}

}
