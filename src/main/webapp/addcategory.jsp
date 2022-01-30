<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add category</title>


<!-- protecting page with normal user-->


<%@include file="components/protection.jsp"%>


<!-- protecting page with normal user-->



<%@include file="components/common_css_js.jsp"%>
</head>
<body>

	<%@include file="components/navbar.jsp"%>

	<div class="container mt-5 pt-5">

		<div class="row">


			<div class="col-md-6 offset-md-3 my-3">


				<%
				String msg = (String) request.getAttribute("msg");

				if (msg != null)
					out.println("<h5>" + msg + "</h5>");
				%>


				<div class="card">

					<div class="card-header custom-bg text-white">
						<h4>Category details</h4>
					</div>

					<!-- card body -->
					<div class="card-body">

						<!-- put action name in url of web.xml -->
						<form action="Category" method="post">

							<div class="form-group">
								<label for="name">Category name</label> <input type="text"
									name="cname" class="form-control" id="cname"
									placeholder="Enter name" aria-describedby="emailHelp"
									required="true">
							</div>

							<div class="form-group">
								<label for="name">Category description</label>
								<textarea name="cdescription" class="form-control"
									id="exampleFormControlTextarea1" rows="3"
									placeholder="Description" required="true"></textarea>
							</div>



							<button type="submit" class="btn btn-outline-success">Add</button>
							<button type="reset" class="btn btn-outline-warning">Reset</button>


						</form>

					</div>

				</div>





			</div>

		</div>

	</div>
</body>
</html>