<%@page import="com.bean.Product"%>
<%@page import="com.dao.OrderDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My orders</title>


<!-- protecting admin page -->


<%@include file="components/protection.jsp"%>


<!-- protection ends -->



<%@include file="components/common_css_js.jsp"%>
</head>
<body>
	<%@include file="components/navbar.jsp"%>



	<div class="mt-3 ml-5 mr-5 pl-3 pr-3">

		<h3>List of orders</h3>

		<table class="table table-hover" style="width: 100%">
			<thead class="thead-light head">


				<!-- col names -->
				<tr>

					<th scope="col">S.No</th>
					<th scope="col">Product image</th>
					<th scope="col">Product id</th>
					<th scope="col">Product name</th>
					<th scope="col">Product price</th>
					<th scope="col">Product quantity</th>
					<th scope="col">Customer name</th>
					<th scope="col">Customer id</th>
					<th scope="col">Customer phoneNo</th>
					<th scope="col">Customer address</th>
					<th scope="col">Order date</th>
					<th scope="col">Action</th>
				</tr>

			</thead>
			<tbody>

				<%
				int count = 1;

				OrderDao order = new OrderDao();

				List<Cart> al = order.getAllOrders();
				Cart c = new Cart();

				for (int i = 0; i < al.size(); i++) {
					c = (Cart) al.get(i);
				%>

				<!-- table data -->

				<tr>

					<td><%=count%></td>

					<!-- product image -->
					<td class="p-2 text-center"><img
						src="img/products/<%=c.getPImage()%>" alt="product image"
						class="img-fluid"
						style="max-height: 140px; max-width:100% width:auto;"></td>

					<!-- ends -->

					<td><%=c.getpId()%></td>
					<td><%=c.getpName()%></td>
					<td><%=c.getpPrice()%></td>
					<td><%=c.getpQuantity()%></td>
					<td><%=c.getCusName()%></td>
					<td><%=c.getCusEmail()%></td>
					<td><%=c.getCusMobile()%></td>
					<td><%=c.getCusAddress()%></td>
					<td><%=c.getcDate()%></td>
					<td>
						<!-- deliver button -->
						<button type="button" class="btn btn-success btn-sm">
							<a href="Delivered?pid=<%=c.getpId()%>&cid=<%=c.getCusEmail()%>"
								class="text-white">Delivered</a>
						</button> <!-- ends -->

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