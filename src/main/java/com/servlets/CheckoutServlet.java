package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Cart;
import com.dao.CartDao;
import com.dao.CheckoutDao;
import com.dao.ProductDao;

public class CheckoutServlet extends HttpServlet {

	public CheckoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		long mobile = Long.parseLong(request.getParameter("mobile"));
		String address = request.getParameter("address");

		// System.out.println(name + " "+ email +" "+ mobile + " "+ address);
		Cart c = new Cart();

		c.setCusName(name);
		c.setCusEmail(email);
		c.setCusMobile(mobile);
		c.setCusAddress(address);

		// getting the cart items of user
		CartDao cd = new CartDao();
		List<Cart> cartItems = cd.getCartById(email);
		
		
		
		// inserting order into table
		CheckoutDao obj = new CheckoutDao();

		int x = obj.confirmOrder(c, cartItems);

		// System.out.println(x);

		
	
		//System.out.println("Cart: "+cartItems);
		
		if (x >= 1) {

			// removing product from cart after confirm order
			obj.removeProduct(email, cartItems);

			
			//updating product quantity after order
			ProductDao pd = new ProductDao();
			pd.updateQuantity(cartItems);
			request.setAttribute("msg", "true");
		}

		else {
			
			
			request.setAttribute("msg", "false");
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/checkout.jsp");
		rd.include(request, response);
	}
}
