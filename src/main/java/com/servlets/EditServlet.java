package com.servlets;

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

import com.bean.Product;
import com.dao.CategoryDao;
import com.dao.ProductDao;


@MultipartConfig
public class EditServlet extends HttpServlet {
	
	       
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();
		
		String page = request.getParameter("page");
		
		if(page == null) {
			
			response.sendRedirect("components/error.jsp");
			return;
		}
		
		
		// checking the page from it gets requested

		if (page.equals("category")) {

			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("cname");
			String des = request.getParameter("cdescription");

			CategoryDao cd = new CategoryDao();
			int x = cd.updateCategory(id, name, des);

			if (x >= 1) {
				request.setAttribute("msg", "Category updated successfully");
			} else {
				request.setAttribute("msg", "Could not be updated");
			}

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/editCategory.jsp");
			rd.include(request, response);
		}
		
		else if(page.equals("product")) {
			
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			String pName = request.getParameter("pName");
			String pDes = request.getParameter("pDescription");
			
			int pPrice = Integer.parseInt(request.getParameter("pPrice"));
			
			int pQuantity = Integer.parseInt(request.getParameter("pQuantity"));
			
			int cId = Integer.parseInt(request.getParameter("categoryId"));
			
			
			
			Part part = request.getPart("pic");
			
			
			Product p = new Product();
			
			p.setpId(id);
			p.setpName(pName);
			p.setpDescription(pDes);
			p.setpPrice(pPrice);
			p.setpQuantity(pQuantity);
			
			//getting image name
			p.setpImg(part.getSubmittedFileName());
			p.setcId(cId);
			
			
			ProductDao pd = new ProductDao();
			int x = pd.updateProduct(p);
			
			if(x >= 1) {	
				
				//finding the path of image or url
				String path = request.getRealPath("img")+File.separator+"products"+File.separator
						+part.getSubmittedFileName();
			
				//System.out.println(path);
			
				boolean valid = pd.uploadImage(path, part);
				System.out.println(valid);
				
				request.setAttribute("msg", "Product updated successfully");
				
			}
			
			else {
				request.setAttribute("msg", "Product could not be updated");
			}
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/editProduct.jsp");
			rd.include(request, response);
			
			
		}

	}

}
