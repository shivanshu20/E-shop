<%@page import="com.bean.Product"%>
<%@page import="com.dao.ProductDao"%>
<%@page import="com.dao.CategoryDao"%>
<%@page import="com.bean.Category"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products list</title>


<!-- protecting admin page -->

<%@include file="components/protection.jsp"%>

<!-- protection ends -->



<%@include file="components/common_css_js.jsp"%>


</head>
<body>
	<%@include file="components/navbar.jsp"%>
	
	
	<h3 class="mx-5 mt-3">List of products</h3>

	<table class="table table-hover mx-5 mt-3" style="width: 95%">
		<thead class="thead-light head">


			<!-- col names -->
			<tr>

				<th scope="col">S.No</th>
				<th scope="col">Product image</th>
				<th scope="col">Product id</th>
				<th scope="col">Product name</th>
				<th scope="col">Product description</th>
				<th scope="col">Product price</th>
				<th scope="col">Product quantity</th>
				<th scope="col">Category id</th>
				<th scope="col">Update</th>
				<th scope="col">Delete</th>

			</tr>

		</thead>
		<tbody>

			<%
			int count = 1;
			ProductDao pd = new ProductDao();

			List<Product> al = pd.getAllProducts();
			Product p = new Product();

			String op = "product";

			for (int i = 0; i < al.size(); i++) {
				p = (Product) al.get(i);
				String id = String.valueOf(p.getpId());
			%>

			<!-- table data -->

			<tr>

				<td><%=count%></td>
				
				<!-- product image -->
				
				<td class="p-2 text-center">
				<img src="img/products/<%=p.getpImg() %>" alt="product image" class="img-fluid" style="max-height:200px; max-width:100% width:auto;">
				</td>
				
				<!-- ends -->
				
				<td><%=id%></td>
				<td><%=p.getpName()%></td>
				<td><%=p.getpDescription()%></td>
				<td><%=p.getpPrice()%></td>
				<td><%=p.getpQuantity()%></td>
				<td><%=p.getcId()%></td>

				<td>
					<button type="button" class="btn btn-warning btn-sm">
						<a href="editProduct.jsp?id=<%=id%>&page=<%=op%>"
							class="text-white">Edit</a>
					</button>
				</td>

				<td>
					<button type="button" class="btn btn-danger btn-sm">
						<a href="Delete?id=<%=id%>&page=<%=op%>" class="text-white">Delete</a>
					</button>
				</td>

			</tr>

			<%
			count++;
			}
			%>

		</tbody>
	</table>






</body>
</html>