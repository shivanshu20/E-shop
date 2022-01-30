

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

<%@include file="components/common_css_js.jsp"%>
</head>


<body>
	<%@include file="components/navbar.jsp"%>



	<div class="container-fluid">

		<div class="row mt-5 pt-4">

			<div class="col-md-4 offset-4">


				<%
				//for user login to add product
				String id = request.getParameter("id");
				String message = (String) session.getAttribute("message");

				if (id != null && message != null)
					out.println("<h5>" + message + "</h5>");

				session.removeAttribute("message");

				//normal message
				String msg = (String) request.getAttribute("msg");

				if (msg != null)
					out.println("<h5>" + msg + "</h5>");

				request.removeAttribute("msg");
				%>

				<!-- card -->
				<div class="card">

					<!-- card header -->
					<div class="card-header custom-bg text-white">

						<h4>Login here!</h4>
					</div>

					<!-- card body -->
					<div class="card-body">

						<!-- put in web.xml url = action -->
						<form action="LoginServlet" method="post">
						
						
							<div class="form-group">
								<label for="email">Email address</label> <input type="email"
									name="email" class="form-control" id="email"
									aria-describedby="emailHelp" placeholder="Enter email"
									required="true">

							</div>
							<div class="form-group">
								<label for="password">Password</label> <input type="password"
									name="pass" class="form-control" id="password"
									placeholder="Enter password" required="true">
							</div>


							<div class="container-fluid">

								<button type="submit" class="btn btn-primary custom-bg">Login</button>
								<button type="reset" class="btn btn-primary custom-bg mx-2">Reset</button>
								Or <a href="forgetPassword.jsp" class="text-dark mx-2"><strong>Forgot
										password?</strong></a>
							</div>

						</form>
					</div>

				</div>

			</div>

		</div>
	</div>
	


</body>
</html>