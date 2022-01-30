<%@page import="com.bean.Cart"%>
<%@page import="java.util.List"%>
<%@page import="com.bean.User"%>
<%@page import="com.dao.CartDao"%>


<%

User u2 = (User) session.getAttribute("user");

CartDao cdao = new CartDao();

List<Cart> aList = null;

if(u2 != null)
	aList = cdao.getCartById(u2.getUserEmail());

Cart c = new Cart();

%>


<!-- Modal -->
<div class="modal fade" id="cart" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Your cart</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        
        
        <div class="container">
        	
        	<%
        	
        		if(aList == null){
        			%>
        			
        			<h4>Cart is empty</h4>
        			<%
        		}
        		
        		else{
     				%>
     				
     				<%@include file="cartTable.jsp" %>
     				
     				<%
        		}
        	
        	%>
        
       
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        
        <%
        	if(u2 != null && aList.size() == 0){
        %>
        	<button type="button" class="btn btn-primary checkout-btn" disabled="disabled">Checkout</button>
        <%
        
        	}
        	else{
        		%>
        		
        		
        		<button type="button" class="btn btn-primary checkout-btn">
        		<a href="checkout.jsp" class="text-white">Checkout</a>
        		</button>
        		
        		
        		<%
        	}
        
        %>
      </div>
    </div>
  </div>
</div>