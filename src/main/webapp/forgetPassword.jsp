<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget password</title>

<%@include file="components/common_css_js.jsp"%>
</head>
<body>

	<%@include file="components/navbar.jsp"%>


	<div class="container-fluid">

		<div class="row mt-5 pt-4">

			<div class="col-md-4 offset-md-4">


				<!-- Printing message -->
				<%
				String msg = (String) request.getAttribute("msg");

				if (msg != null) {
					out.println("<h5>" + msg + "</h5>");
				}
				request.removeAttribute("msg");
				%>


				<div class="card">

					<div class="card-header custom-bg text-white">
						<h4>Enter details!</h4>
					</div>

					<!-- card body -->
					<div class="card-body">

						<!-- put action name in url of web.xml -->
						<form action="Forget" method="post">


							<div class="form-group">
								<label for="email">Email</label> <input type="email"
									name="email" class="form-control" id="email"
									placeholder="Enter email" aria-describedby="email"
									required="true">
							</div>

							<div class="form-group">
								<label for="phone">Mobile number</label> <input type="number"
									name="phone" class="form-control" id="phone"
									placeholder="Enter phone_no" aria-describedby="number"
									required="true">
							</div>
							
							<div class="form-group">
								<label for="phone">New password</label> <input type="password"
									name="pass" class="form-control" id="pass"
									placeholder="Enter new password" aria-describedby="password"
									required="true">
							</div>

							<button types="submit" class="btn btn-primary custom-bg">Create</button>
							<button type="reset" class="btn btn-primary custom-bg">Reset</button>


						</form>

					</div>

				</div>

			</div>

		</div>

	</div>


</body>
</html>