package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LogoutServlet() {
        super();
   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		
		response.setHeader("Cache-control","no-cache, no-store, must-revalidate");
		response.setHeader("Pragma","no-cache");
		
		
		request.setAttribute("msg", "Logged out successfully!");
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
		rd.include(request, response);
	}

}
