
<%@page import="com.dao.EncryptionDecryption"%>
<%@page import="com.dao.Helper"%>
<%@page import="com.bean.User"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.CategoryDao"%>
<%@page import="com.bean.Category"%>
<%@page import="com.bean.Product"%>
<%@page import="com.dao.ProductDao"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title>Customer home page</title>


<%@include file="components/common_css_js.jsp"%>
</head>
<body>


	<%
	//checking if user if logged in or not

	User user = (User) session.getAttribute("user");
	//System.out.println(user);

	CategoryDao cd = new CategoryDao();
	List<Category> list1 = cd.getAllCategories();

	ProductDao pd = new ProductDao();

	String id = request.getParameter("id");
	if (id == null)
		id = "all";

	//out.println(id);
	//loading category wise products
	List<Product> list2 = null;

	if (id.trim().equals("all")) {

		list2 = pd.getAllProducts();
	} 
	else {
		
		list2 = pd.getByCategoryId(id);
	}
	%>



	<div class="row mt-4 ml-5">


		<!-- categories -->
		<div class="col-md-2">



			<div class="list-group mt-2">


				<a type="hidden" href="index.jsp?id=all"
					class="list-group-item list-group-item-action active"> All
					products </a>


				<%
				for (Category c : list1) {
				%>
				
				<%
					String s = String.valueOf(c.getId());
				%>
				<a href="normal.jsp?id=<%=s%>"
					class="list-group-item list-group-item-action"><%=c.getCategoryTitle()%></a>
				<%
				}
				%>

			</div>

			<!-- list group -->

		</div>



		<!-- products -->
		<div class="col-md-8 ">

			<!-- row -->
			<div class="row mt-2">


				<!-- col -->
				<div class="col-md-12">


					<!-- card column -->

					<div class="card-columns">


						<%
						for (Product p : list2) {
						%>

						<!-- product card -->

						<div class="card">

							<%
							int f = 0;

							if (p.getpQuantity() == 0) {

								f = 1;
							%>
							<div class="card-header">

								<h4 class="text-left text-danger">Out of stock!</h4>

							</div>
							<%
							}
							%>

							<!-- for image -->

							<div class="container text-center">

								<img src="img/products/<%=p.getpImg()%>"
									class="card-img-top mt-3" alt="Product image"
									style="max-height: 180px; max-width: 100%; width: auto;">

							</div>


							<!-- image ends -->



							<div class="card-body">

								<h5 class="card-title"><%=p.getpName()%></h5>

								<p class="cart-text"><%=Helper.getWords(p.getpDescription())%>

									<a href="showProduct.jsp?id=<%=p.getpId()%>">more..</a>
								</p>



							</div>
							<!-- card body ends -->


							<!-- card footer -->
							<div class="card-footer">

								<button class="btn btn-primary btn-sm" disabled="disabled">
									&#8377;
									<%=p.getpPrice()%></button>


								<%
								if (user == null) {
								%>
								&nbsp;
								<button type="button" class="btn btn-success btn-sm">
									<a href="login.jsp?id=true" class="text-white">Add to Cart
									</a>
								</button>

								<%
								}

								else {

								if (f == 1) {
								%>

								<button class="btn btn-success btn-sm" disabled="disabled">
									Add to Cart</button>
								<%
								}

								else {
								%>


								<button class="btn btn-success btn-sm">

									
									<a href="Cart?id=<%=p.getpId()%>" class="text-white">Add
										to cart</a>
								</button>


								<%
								}
								}
								%>


							</div>


						</div>
						<!-- card ends -->

									<%
						}
						session.setAttribute("message", "Please login first!");
						%>


					</div>



				</div>


			</div>


		</div>



	</div>

</body>
</html>