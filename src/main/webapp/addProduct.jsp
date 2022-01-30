<%@page import="java.util.List"%>
<%@page import="com.bean.Category"%>
<%@page import="com.dao.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add product</title>

<!-- protecting page with normal user-->


<%@include file="components/protection.jsp"%>


<!-- protecting page with normal user-->


<%@include file="components/common_css_js.jsp"%>
</head>

<body>




	<!-- java code -->

	<%
	Category c = new Category();
	CategoryDao cd = new CategoryDao();

	//gettting categories for dropdown
	List<Category> al = cd.getAllCategories();
	%>


	<!-- code ends -->



	<%@include file="components/navbar.jsp"%>




	<div class="container mt-4">

		<div class="row">


			<div class="col-md-6 offset-md-3 ">


				<%
				String msg = (String) request.getAttribute("msg");

				if (msg != null)
					out.println("<h5>" + msg + "</h5>");
				%>


				<div class="card">

					<div class="card-header custom-bg text-white">
						<h4>Product details</h4>
					</div>

					<!-- card body -->
					<div class="card-body">

						<!-- form starts -->

						<!-- put action name in url of web.xml -->
						<form action="Product" method="post" enctype="multipart/form-data">


							<!-- product name -->


							<div class="form-group">
								<label for="name">Product name</label> <input type="text"
									name="pName" class="form-control" id="cname"
									placeholder="Enter name" aria-describedby="emailHelp"
									required="true">
							</div>
							<!-- product name ends -->



							<!-- form grid -->
							<div class="row">


								<!-- product price -->
								<div class="col">

									<div class="form-group">
										<label for="name">Product price</label> <input type="number"
											name="price" class="form-control" id="cname"
											placeholder="Enter price" aria-describedby="emailHelp"
											required="true">
									</div>
								</div>
								<!-- price ends -->


								<!-- product quantity -->
								<div class="col">
									<div class="form-group">
										<label for="name">Product quantity</label> <input
											type="number" name="pQuantity" class="form-control"
											id="cname" placeholder="Enter quantity"
											aria-describedby="emailHelp" required="true">
									</div>
								</div>
							</div>
							<!-- quantity ends -->
							<!-- form grid ends -->


							<!-- product description -->

							<div class="form-group">
								<label for="name">Product description</label>
								<textarea name="pDescription" class="form-control"
									id="exampleFormControlTextarea1" rows="2"
									placeholder="Description" required="true"></textarea>
							</div>

							<!-- description ends -->


							<!-- product category -->

							<div class="form-group">

								<label for="name">Product category</label> <select
									class="form-control" name="categoryId">


									<!-- categories -->
									<%
									for (int i = 0; i < al.size(); i++) {

										c = (Category) al.get(i);
									%>

									<option value=<%=c.getId()%>><%=c.getCategoryTitle()%></option>
									<%
									}
									if (al.size() == 0) {
									%>
									<small>Please add category first</small>
									<%
									}
									%>

								</select>


							</div>


							<!-- category ends -->



							<!-- product image-file -->


							<div class="form-group my-3">
								<label for="exampleFormControlFile1">Product image</label> <input
									type="file" name="pic" class="form-control"
									id="exampleFormControlFile1" required="true">
							</div>


							<!-- product file ends -->

							<button type="submit" class="btn btn-outline-success">Add</button>
							<button type="reset" class="btn btn-outline-warning">Reset</button>


						</form>
						<!-- form ends -->


					</div>

				</div>





			</div>

		</div>

	</div>



</body>
</html>