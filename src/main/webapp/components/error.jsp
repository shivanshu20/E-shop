<%@page import="com.bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error page</title>


<%@include file="common_css_js.jsp"%>

</head>
<body>


	<%
	User userType = (User) session.getAttribute("user");
	%>


	<div class="container mt-5 pt-5">
		<h1>
			
			<%
			
			if(userType == null){
				
				%>
				
				Please don't try to access this page!!! &nbsp;
				<a href = "../index.jsp">Home</a>
				<%
				return;
			}
			
			if (userType.getUserType().equals("admin")) {
			%>
			Please don't try to access this page directly!!! &nbsp; <a
				href="../admin.jsp">Home</a>
			<%
			}
			else if(userType.getUserType().equals("normal")){
				
				%>
				Please don't try to access this page directly!!! &nbsp; <a
				href="../normal.jsp">Home</a>
				
				<%
			}
			%>
		</h1>
	</div>
</body>
</html>