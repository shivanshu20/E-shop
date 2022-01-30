<%@page import="com.bean.Product"%>
<%@page import="com.dao.OrderDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My orders</title>

<%@include file="components/common_css_js.jsp"%>
</head>
<body>
	<%@include file="components/navbar.jsp"%>


	<%
	User user = (User) session.getAttribute("user");

	if (user == null) {

		response.sendRedirect("components/error.jsp");
		return;
	}

	OrderDao order = new OrderDao();

	List<Cart> al = order.userOrders(user.getUserEmail());

	if (al.size() == 0) {
	%>

	<h3 class="text-center mt-3">You haven't order any anything yet!</h3>
	<%
	return;
	}
	%>





	<div class="container mt-4" class="users">

		<h3>Your orders</h3>


		<table class="table table-hover" style="width: 100%">
			<thead class="thead-light head">


				<!-- col names -->
				<tr>

					<th scope="col">S.No</th>
					<th scope="col" class="text-center">Product image</th>
					<th scope="col">Product name</th>
					<th scope="col">Product price</th>
					<th scope="col">Product quantity</th>
					<th scope="col">Order date</th>

				</tr>

			</thead>
			<tbody>

				<%
				int count = 1;

				Cart cartobj = new Cart();

				for (int i = 0; i < al.size(); i++) {
					cartobj = (Cart) al.get(i);
				%>

				<!-- table data -->

				<tr>
					
					<td><%=count %></td>
					
					
					<!-- product image -->
					<td class="p-3 text-center"><img
						src="img/products/<%=cartobj.getPImage()%>" alt="product image"
						class="img-fluid" style="max-height:140px; max-width:100$; width:auto;"></img>
						
					</td>
					<!-- ends -->
						
					<td class="p-3"><%=cartobj.getpName()%></td>
					<td><%=cartobj.getpPrice()%></td>
					<td><%=cartobj.getpQuantity()%></td>
					<td><%=cartobj.getcDate()%></td>
				</tr>

				<%
				count++;
				}
				%>

			</tbody>
		</table>
	</div>

	<%@include file="components/common_modals.jsp"%>

</body>
</html>