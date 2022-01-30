<%@page import="com.dao.OrderDao"%>
<%@page import="com.bean.Category"%>
<%@page import="com.dao.CategoryDao"%>
<%@page import="com.dao.UserDao"%>
<%@page import="com.bean.Product"%>
<%@page import="com.dao.ProductDao"%>
<%@page import="com.bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Admin panel</title>



<!-- protecting admin page -->


<%@include file = "components/protection.jsp" %>


<!-- protection ends -->


<%@include file="components/common_css_js.jsp"%>

<style>

.card:hover{

	background:#e2e2e2;
}

.card{
	border:1px solid #e8eaf6;
}



</style>

</head>
<body>


<!-- dynamic value of users, products etc -->
<%

//no of products
ProductDao pd = new ProductDao();
List<Product> productList = pd.getAllProducts();


//no of users
UserDao ud = new UserDao();
List<User> userList = ud.getAllUsers();


//no of categories
CategoryDao cd = new CategoryDao();
List<Category> categoryList = cd.getAllCategories();


//no of pending orders
OrderDao od = new OrderDao();
List<Cart> orderList = od.getAllOrders();
%>



	<%@include file="components/navbar.jsp"%>


	<div class="container">
	
		<h4 class="mt-2">Pending orders : <%=orderList.size() %></h4>
		
		
		<div class="row pt-3 mt-5 text-center">


			<!-- FIRST COL START-->
			<div class="col-md-4">


				<!-- CARD START-->
				<div class="card">



					<!-- CARD BODY START -->
					
					<div class="card-body">
						<!-- IMG CONTAINER -->
						
						<div class="container">
							
							
							<img style="max-width: 120px"
								class="img-fluid rounded-circle  mb-2" src="img/group.png"
								alt="Users image">
								
						</div>
						<!-- IMG ENDS -->


						<h3><%=userList.size()%></h3>
						<a href="users.jsp">
						<h3 class="text-uppercase text-muted">User</h3>
						</a>

					</div>
					<!-- CARD BODY ENDS -->


				</div>
				<!-- CARD ENDS -->


			</div>
			<!-- FIRST COL ENDS -->



			<!-- SECOND COL START-->
			<div class="col-md-4">


				<!-- CARD START-->
				<div class="card">


					<!-- CARD BODY START -->
					<div class="card-body">


						<!-- IMG CONTAINER -->
						<div class="container">

							<img style="max-width: 119px"
								class="img-fluid  mb-2" src="img/list.png"
								alt="Users image">
						</div>
						<!-- IMG ENDS -->


						<h3><%=categoryList.size() %></h3>
						<a href="viewCategories.jsp">
						<h3 class="text-uppercase text-muted">Categories</h3>
						</a>

					</div>
					<!-- CARD BODY ENDS -->



				</div>
				<!-- CARD ENDS -->


			</div>
			<!-- SECOND COL ENDS -->



			<!-- THIRD COL START-->
			<div class="col-md-4">


				<!-- CARD START-->
				<div class="card">


					<!-- CARD BODY START -->
					<div class="card-body">


						<!-- IMG CONTAINER -->
						<div class="container">

							<img style="max-width: 120px"
								class="img-fluid rounded-circle mb-2" src="img/product.png"
								alt="Users image">
						</div>
						<!-- IMG ENDS -->


						<h3><%=productList.size() %></h3>
						<a href="viewProducts.jsp">
						<h3 class="text-uppercase text-muted">Products</h3>
						</a>

					</div>
					<!-- CARD BODY ENDS -->


				</div>
				<!-- CARD ENDS -->


			</div>
			<!-- THIRD COL ENDS -->


		</div>
		<!-- FIRST ROW ENDS -->






		<!-- SECOND ROW START-->
		<div class="row pt-3 text-center">

			<!-- SECOND ROW - FIRST COL START-->

			<div class="col-md-4">



				<!-- CARD START-->
				<div class="card">



					<!-- CARD BODY START -->
					<div class="card-body">


						<!-- IMG CONTAINER -->
						<div class="container">
							
							<img style="max-width: 127px"
								class="img-fluid rounded-circle mt-1 mb-3" src="img/purchase.png"
								alt="Add category image">
						</div>
						<!-- IMG ENDS -->

						<a href="addcategory.jsp">
						<h3 class="text-uppercase text-muted">Add category</h3>
						</a>

					</div>
					<!-- CARD BODY ENDS -->


				</div>
				<!-- CARD ENDS -->



			</div>
			<!-- SECOND ROW - FIRST COL ENDS-->




			<!-- SECOND ROW - SECOND COL START-->

			<div class="col-md-4">




				<!-- CARD START-->
				<div class="card">



					<!-- CARD BODY START -->
					<div class="card-body">


						<!-- IMG CONTAINER -->
						<div class="container">

							<img style="max-width: 116px"
								class="img-fluid rounded-circle mt-3  mb-3"
								src="img/plus.png" alt="Add product image">
						</div>
						<!-- IMG ENDS -->

						<a href="addProduct.jsp">
						<h3 class="text-uppercase text-muted">Add product</h3>
						</a>
						
					</div>
					<!-- CARD BODY ENDS -->


				</div>
				<!-- CARD ENDS -->


			</div>
			<!-- SECOND ROW - SECOND COL ENDS-->



			<!-- SECOND ROW - THIRD COL START-->

			<div class="col-md-4">



				<!-- CARD START-->
				<div class="card">



					<!-- CARD BODY START -->
					<div class="card-body">


						<!-- IMG CONTAINER -->
						<div class="container">

							<img style="max-width: 90px" class="img-fluid mt-2 mb-2"
								src="img/vieworders.png" alt="Users image">
						</div>
						<!-- IMG ENDS -->

						<p class="text-muted">Pending orders: <%=orderList.size() %></p>
						<a href="viewOrders.jsp">
						<h3 class="text-uppercase text-muted">View orders</h3>
						</a>
						
						
					</div>
					<!-- CARD BODY ENDS -->


				</div>
				<!-- CARD ENDS -->



			</div>
			<!-- SECOND ROW - THIRD COL ENDS-->



		</div>
		<!-- SECOND ROW ENDS -->



	</div>
	<!-- CONTAINER ENDS -->





</body>
</html>