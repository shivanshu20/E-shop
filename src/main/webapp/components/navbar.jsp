<%@page import="com.bean.Cart"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.CartDao"%>
<%@page import="com.bean.User"%>

<%
User u = (User) session.getAttribute("user");
%>


<nav class="navbar navbar-expand-lg navbar-dark custom-bg">

	<div class="container">
		
		<strong><a class="navbar-brand text-white">E-shop</a></strong>
		
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">



				<%
				//cart  items value

				CartDao obj = new CartDao();
				List<Cart> list = null;

				if (u != null) {
					list = obj.getCartById(u.getUserEmail());
				}

				if (u == null) {
				%>



				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">Home </a></li>


				<%
				} else {

				if (u.getUserType().equals("admin")) {
				%>

				<li class="nav-item active"><a class="nav-link"
					href="admin.jsp">Home </a></li>

				<%
				} else {
				%>

				<li class="nav-item active"><a class="nav-link"
					href="normal.jsp">Home </a></li>

				<%
				}
				}

				//for user orders
				if (u != null && u.getUserType().equals("normal")) {
				%>

				<li class="nav-item active"><a class="nav-link"
					href="userOrders.jsp">My orders</a></li>

				<%
				}
				%>



			</ul>

			<ul class="navbar-nav ml-auto">


				<!-- condition -->
				<%
				if (u == null) {
				%>

				<li class="nav-item active"><a class="nav-link"
					href="login.jsp">Sign in</a></li>

				<li class="nav-item active"><a class="nav-link"
					href="register.jsp">Sign up</a></li>


				<%
				}

				else {

				if (u.getUserType().equals("normal")) {
				%>



				<li class="nav-item active mr-3"><a class="nav-link" href="#"
					data-toggle="modal" data-target="#cart"> My cart <span
						class="ml-1"><%="(" + list.size() + ")"%></span>
				</a></li>

				<%
				}
				%>


				<li class="nav-item active mr-3"><a class="nav-link" href="#"><%=u.getUserName()%></a></li>



				<li class="nav-item active"><a class="nav-link"
					href="Logout">Logout</a></li>

				<%
				}
				%>


			</ul>

		</div>
	</div>
</nav>