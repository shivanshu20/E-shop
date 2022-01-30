package com.servlets;


import com.bean.Product;
import com.bean.User;
import com.dao.CartDao;
import com.dao.EncryptionDecryption;
import com.dao.ProductDao;
import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class AddToCart extends HttpServlet {
	
    public AddToCart() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		
		//encrypted id getting
		String pid = request.getParameter("id");
		
		if(u == null || pid == null) {
			
			response.sendRedirect("components/error.jsp");
			return;
		}
		
		
//		ProductDao pd = new ProductDao();
//		List<Product> al =  pd.getById(pid);
		
		
		CartDao cd = new CartDao();
		String email = u.getUserEmail();
				
		int quantity = cd.check(pid, email);
		
		if(quantity == 0)
			cd.addToCart(pid, email);
		
		else if(quantity >= 1)
			cd.updateCart(pid, email, ++quantity);
		
		response.sendRedirect("normal.jsp");
	}

}
