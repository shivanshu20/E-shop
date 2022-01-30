<%@page import="com.dao.CategoryDao"%>
<%@page import="com.bean.Category"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customers list</title>


<!-- protecting admin page -->

<%@include file="components/protection.jsp"%>

<!-- protection ends -->



<%@include file="components/common_css_js.jsp"%>


</head>
<body>
	<%@include file="components/navbar.jsp"%>


	<div class="container mt-4" class="users">
		<h3>List of categories</h3>

		<table class="table table-hover " style="width: 100%">
			<thead class="thead-light head">


				<!-- col names -->
				<tr>

					<th scope="col">S.No</th>
					<th scope="col">Category id</th>
					<th scope="col">Category name</th>
					<th scope="col">Category description</th>
					<th scope="col">Update</th>
					<th scope="col">Delete</th>

				</tr>

			</thead>
			<tbody>

				<%
				int count = 1;

				CategoryDao cg = new CategoryDao();

				List<Category> al = cg.getAllCategories();
				Category c = new Category();
				String op = "category";

				for (int i = 0; i < al.size(); i++) {
					c = (Category) al.get(i);
					String id = String.valueOf(c.getId());
				%>

				<!-- table data -->

				<tr>

					<td><%=count%></td>
					<td><%=id%></td>
					<td><%=c.getCategoryTitle()%></td>
					<td><%=c.getCategoryDescription()%></td>


					<td>
					<button type="button" class="btn btn-warning btn-sm">
					<a href="editCategory.jsp?id=<%=id%>&page=<%=op%>" class="text-white">Edit</a>
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
	</div>




</body>
</html>