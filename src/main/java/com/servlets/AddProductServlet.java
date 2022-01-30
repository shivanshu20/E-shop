package com.servlets;


import com.bean.Product;
import com.dao.ProductDao;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



@MultipartConfig
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddProductServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		
		String pName = request.getParameter("pName");
		String pDes = request.getParameter("pDescription");
		
		
		//validations
		if(pName == null || pDes == null ) {
			
			response.sendRedirect("components/error.jsp");
			return;
		}
		

		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("pQuantity"));
		
		int cId = Integer.parseInt(request.getParameter("categoryId"));
		
		//for image file
		Part part = request.getPart("pic");
				
		//System.out.println(pName + " "+ pDes + " "+ price+" "+quantity+" "+cId+" "+part);
		//out.println(pName + " "+ pDes + " "+ price+" "+quantity+" "+cId+" "+img.getSubmittedFileName());
		
		
		Product p = new Product();
		
		p.setpName(pName);
		p.setpDescription(pDes);
		p.setpPrice(price);
		p.setpQuantity(quantity);
		
		//getting image name
		p.setpImg(part.getSubmittedFileName());
		p.setcId(cId);
		
		ProductDao pd = new ProductDao();
		int x = pd.addProduct(p);
		
		
		if(x >= 1) {	
			
			//finding the path of image
			String path = request.getRealPath("img")+File.separator+"products"+File.separator
					+part.getSubmittedFileName();
		
			//System.out.println(path);
		
			boolean valid = pd.uploadImage(path, part);
			System.out.println(valid);
			
			request.setAttribute("msg", "Product added successfully");
		}
		
		else {
			request.setAttribute("msg", "Product could not be added");
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/addProduct.jsp");
		rd.include(request, response);
	}

}
