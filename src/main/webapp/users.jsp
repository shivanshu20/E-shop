<%@page import="java.util.List"%>
<%@page import="com.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customers list</title>


<!-- protecting admin page -->

<%@include file = "components/protection.jsp" %>

<!-- protection ends -->



<%@include file = "components/common_css_js.jsp" %>


<style>


</style>

</head>
<body>
<%@include file = "components/navbar.jsp" %>




<div class="container mt-4" class="users">
<h3>List of users</h3>

<table class="table table-hover" style="width:100%">
  <thead class="thead-light head">
    
    
    <!-- col names -->
    <tr>
      
      <th scope="col">S.No</th>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Mobile</th>
     
    </tr>
    
  </thead>
  <tbody>

<%
int count = 1;

UserDao ud = new UserDao();
List<User> al = ud.getAllUsers();
User u1 = new User();

for(int i=0; i<al.size(); i++)	
{
	u = (User)al.get(i);
		
%>
	
<!-- table data -->
<tr>

<td><%=count %></td>
<td><%=u.getUserName()%></td>
<td><%=u.getUserEmail() %></td>
<td><%=u.getUserPhone()%></td>

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