<%@page import="com.bean.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product page</title>

<%@include file="components/common_css_js.jsp"%>
</head>
<body>

	<%@include file="components/navbar.jsp"%>

	<%
	User user = (User) session.getAttribute("user");

	String id = request.getParameter("id");
	
	//protection
	if(id == null){
		
		response.sendRedirect("components/error.jsp");
		return;
	}
	
	
	ProductDao pd = new ProductDao();

	List<Product> al = pd.getById(id);

	Product p = new Product();

	p = (Product) al.get(0);
	%>


	<div class="container mt-5 pt-3">




		<%
		int f = 0;

		if (p.getpQuantity() == 0) {

			f = 1;
		%>

		<h4 class="text-left text-danger">Out of stock!</h4>

		<%
		}
		%>

		<div class="card">



			<div class="row no-gutters">


				<div class="col-md-5 mt-1 text-center">
					
					<div class="container">
	
					<img src="img/products/<%=p.getpImg()%>" alt="Product image" class="img-fluid w-50 m-2">

					</div>
				
				</div>


				<div class="col-md-7">

					<div class="card-body">

						<h3 class="card-title">
							<b><%=p.getpName()%></b>
						</h3>

						<p class="card-text mt-3"><%=p.getpDescription()%></p>

						<button class="btn btn-primary" disabled="disabled"><%=p.getpPrice()%></button>




						<%
						if (user == null) {
						%>
						&nbsp;
						<button type="button" class="btn btn-success">
							<a href="login.jsp?id=true" class="text-white">Add to Cart </a>
						</button>

						<%
						}

						else {

						if (f == 1) {
						%>

						<button class="btn btn-success" disabled="disabled"
							onclick="addToCart(<%=p.getpId()%>, '<%=p.getpName()%>', <%=p.getpPrice()%>)">
							Add to Cart</button>
						<%
						}

						else {
						%>


						<button class="btn btn-success">

							<a href="Cart?id=<%=id%>" class="text-white">Add to cart</a>
						</button>


						<%
						}
						}
						%>

					</div>

				</div>

			</div>

		</div>

	</div>

<%@include file = "components/common_modals.jsp" %>

</body>
</html>