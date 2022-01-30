<%@page import="com.bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>





<!-- protecting admin page -->
<%
User user1 = (User) session.getAttribute("user");

//no user logged in
if (user1 == null) {

	request.setAttribute("msg", "You are not logged in !! Please login first!");
	RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
	rd.include(request, response);

	session.removeAttribute("user");
	return;
}

//if logged in, then check for admin

else if (user1.getUserType().equals("normal")) {

	request.setAttribute("msg", "You are not admin!");

	RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
	rd.include(request, response);

	session.removeAttribute("user");
	return;
}


%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
</body>
</html>