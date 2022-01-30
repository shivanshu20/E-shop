<%@page import="com.dao.CategoryDao"%>
<%@page import="com.bean.Category"%>
<%@page import="com.bean.Product"%>
<%@page import="com.dao.ProductDao"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit product</title>

<!-- protecting page with normal user-->


<%@include file="components/protection.jsp"%>


<!-- protecting page with normal user-->


<%@include file="components/common_css_js.jsp"%>
</head>

<body>


	<!-- java code -->

	<%
	String id = request.getParameter("id");
	//System.out.println(id);

	//protecting through direct access
	if (id == null) {
		response.sendRedirect("components/error.jsp");
		return;
	}

	//for dropdown
	Category c = new Category();
	CategoryDao cd = new CategoryDao();

	List<Category> al = cd.getAllCategories();

	//for setting the products value in the form
	Product p = new Product();

	ProductDao pd = new ProductDao();
	List<Product> productList = pd.getById(id);

	//System.out.println(al.size());

	p = (Product) productList.get(0);
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
						<h4>Update Product details</h4>
					</div>

					<!-- card body -->
					<div class="card-body">

						<!-- form starts -->

						<!-- put action name in url of web.xml -->
						<form action="Edit" method="post" enctype="multipart/form-data">


							<input type="hidden" name="page" value="product"> <input
								type="hidden" name="id" value=<%=id%>>

							<!-- product name -->


							<div class="form-group">
								<label for="name">Product name</label> <input type="text"
									name="pName" class="form-control" id="cname"
									placeholder="Enter name" aria-describedby="emailHelp"
									value=<%=p.getpName()%> required="true">
							</div>
							<!-- product name ends -->


							<!-- form grid -->

							<div class="row">



								<!-- product price -->

								<div class="col">

									<div class="form-group">
										<label for="name">Product price</label> <input type="number"
											name="pPrice" class="form-control" id="cname"
											placeholder="Enter price" aria-describedby="emailHelp"
											value=<%=p.getpPrice()%> required="true">
									</div>

								</div>
								<!-- price ends -->


								<!-- product quantity -->
								<div class="col">
									<div class="form-group">
										<label for="name">Product quantity</label> <input
											type="number" name="pQuantity" class="form-control"
											id="cname" placeholder="Enter quantity"
											aria-describedby="emailHelp" value=<%=p.getpQuantity()%>
											required="true">
									</div>
								</div>

								<!-- quantity ends -->


							</div>
							<!-- form grid ends -->


							<!-- product description -->

							<div class="form-group">
								<label for="name">Product description</label>
								<textarea name="pDescription" class="form-control"
									id="exampleFormControlTextarea1" rows="2"
									placeholder="Description" required="true">
								<%=p.getpDescription()%></textarea>
							</div>

							<!-- description ends -->


							<!-- product category -->

							<div class="form-group">

								<label for="name">Product Category</label> <select
									class="form-control" name="categoryId">


									<!-- categories -->
									<%
									for (int i = 0; i < al.size(); i++) {

										c = (Category) al.get(i);
									%>

									<option required="true" value=<%=c.getId()%>><%=c.getCategoryTitle()%></option>
									<%
									}
									%>

								</select> <small>Please select category again</small>
							</div>


							<!-- category ends -->



							<!-- product image-file -->


							<div class="form-group my-3">
								<label for="exampleFormControlFile1">Product image</label> <input
									type="file" name="pic" class="form-control"
									id="exampleFormControlFile1" required="true"> <small>Please
									upload picture again</small>
							</div>


							<!-- product file ends -->

							<button type="submit" class="btn btn-outline-success">Update</button>
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