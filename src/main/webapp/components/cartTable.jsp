<%@page import="com.bean.Cart"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.CartDao"%>
<%@page import="com.bean.User"%>



<%
User userObj = (User) session.getAttribute("user");

CartDao cartobject = new CartDao();

List<Cart> cartList = null;

if(userObj != null)
	cartList = cartobject.getCartById(userObj.getUserEmail());

Cart cart = new Cart();

%>


<table class="table" style="width: 100%">

	<thead>

		<tr>

			<th scope="col">Item Name</th>
			<th scope="col">Item price</th>
			<th scope="col">Item quantity</th>
			<th scope="col">Total price</th>
			<th scope="col">Action</th>
		</tr>

	</thead>

	<tbody class="ml-3">
		<%
		int totalPrice = 0;
		
		for (int i = 0; i < cartList.size(); i++) {

			cart = cartList.get(i);
		%>

		<tr>

			<td><%=cart.getpName()%></td>
			<td class="text-center"><%=cart.getpPrice()%></td>
			<td class="text-center"><%=cart.getpQuantity()%></td>
			<td class="text-center"><%=cart.getpPrice() * cart.getpQuantity()%></td>

			<td>
			<a	href="Remove?id=<%=cart.getpId()%>&cid=<%=userObj.getUserEmail()%>"
				class="text-center text-danger">Remove</a></td>

			
		</tr>
		
		<%
			//calculating total price
			totalPrice += cart.getpQuantity() * cart.getpPrice();
		}
		%>
		
		<!-- showing total amount -->
		<tr>
		<td colspan="5" class="text-right"><b>Total price : <%=totalPrice %></b></td>
		</tr>
		
		
	</tbody>
	
	
	
</table>