<%@page import="com.dao.CheckoutDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkout</title>


<%@include file="components/common_css_js.jsp"%>
</head>
<body>

	<%
	//checking for order is placed or not
	String msg = (String) request.getAttribute("msg");

	User user = (User) session.getAttribute("user");

	if (user == null) {
		response.sendRedirect("components/error.jsp");
		return;
	}
	
	CheckoutDao ob = new CheckoutDao();

	List<User> userList = ob.getDetails(user.getUserEmail());
	User us = (User) userList.get(0);

	//getting cart items
	CartDao cobj = new CartDao();
	List<Cart> cartItems = null;

	if (cobj != null) {

		cartItems = cobj.getCartById(user.getUserEmail());
	}
	%>


	<%@include file="components/navbar.jsp"%>

	<div class="container">

		<!-- printing message if order is not placed -->

		<div class="text-center text-danger mt-2">

			<!--  -->
		</div>

		<div class="row">

			<!-- cart table -->
			<div class="col-md-6 mt-5">

				<div class="card">

					<div class="card-body">

						<h3>Your Cart</h3>

						<%@include file="components/cartTable.jsp"%>
					</div>

				</div>

			</div>

			<!-- user details -->
			<div class="col-md-6 mt-5">

				<div class="card">

					<div class="card-body">

						<h3>Your details</h3>
						<hr>

						<form action="ConfirmOrder" method="post">
							<div class="form-group">

								<label for="name">Your name</label> <input type="text"
									required="true" name="name" class="form-control" id="name"
									aria-describedby="emailHelp" value=<%=us.getUserName()%>
									placeholder="Enter name">
							</div>



							<div class="form-group">

								<label for="email">Your email</label> <input type="email"
									required="true" name="email" class="form-control" id="email"
									aria-describedby="emailHelp" value=<%=us.getUserEmail()%>
									placeholder="Enter email">
							</div>


							<div class="form-group">

								<label for="name">Your phone no</label> <input type="number"
									required="true" name="mobile" class="form-control" id="mobile"
									aria-describedby="emailHelp" value=<%=us.getUserPhone()%>
									placeholder="Enter mobile no">
							</div>

							<div class="form-group">

								<label for="textare">Your shipping address</label>
								<textarea class="form-control" id="address" rows="3"
									name="address" placeholder="Enter shipping address" required="true"></textarea>
							</div>

							<button type="button" class="btn btn-success btn-sm">

								<a href="normal.jsp" class="text-white">Continue shopping</a>
							</button>


							<%
							if (cartItems.size() == 0) {
							%>
							
							<!-- disabling confirm order button if cart is empty -->
							<button type="submit" class="btn btn-primary btn-sm ml-2"
								disabled="disabled">Confirm
								order</button>


							<%
							} else {
							%>
							<!-- enabling cart button if cart has items -->
							<button type="submit" class="btn btn-primary btn-sm ml-2"
								>Confirm order</button>


							<%
							}
							
							//checking if order is placed is successfully or not
							if(msg != null && msg.equals("true")){
								
								%>
								<script>
									confirmOrder();
								</script>
								<%
							}
							else if(msg != null && msg.equals("false")){
								
								%>
								<script>
									orderFailed();
								</script>
								<%
							}
							
							//redirecting page after alert message
							if(msg != null)
								response.sendRedirect("normal.jsp");
							%>


						</form>
					</div>

				</div>

			</div>

		</div>

	</div>


</body>
</html>