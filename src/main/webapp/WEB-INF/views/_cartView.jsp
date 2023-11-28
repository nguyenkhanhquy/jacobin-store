<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart Page</title>
    <link rel="stylesheet" href="resources/css/ch09.css" type="text/css"/>
</head>
<body>
	<h1>Your cart</h1>
	
	<table class="cart-list">
	  <tr>
	    <th>Quantity</th>
	    <th>Description</th>
	    <th>Price</th>
	    <th>Amount</th>
	    <th></th>
	  </tr>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:forEach var="item" items="${cart.items}">
	  <tr>
	    <td>
	      <form action="cart" method="post">
	      	<input type="hidden" name="action" value="update">
	        <input type="hidden" name="productId" value="<c:out value='${item.product.productId}'/>">
	        <input type=text name="quantity" value="<c:out value='${item.quantity}'/>" id="quantity">
	        <input class="btn" type="submit" value="Update">
	      </form>
	    </td>
	    <td><c:out value='${item.product.description}'/></td>
	    <td>${item.product.priceCurrencyFormat}</td>
	    <td>${item.totalCurrencyFormat}</td>
	    <td>
	      <form action="cart" method="post">
	      	<input type="hidden" name="action" value="update">
	        <input type="hidden" name="productId" value="<c:out value='${item.product.productId}'/>">
	        <input type="hidden" name="quantity" value="0">
	        <input class="btn" type="submit" value="Remove Item">
	      </form>
	    </td>
	  </tr>
	</c:forEach>
	</table>
	
	<p><b>To change the quantity</b>, enter the new quantity 
	      and click on the Update button.</p>
	  
	<form action="cart" method="post">
	  <input type="hidden" name="action" value="home">
	  <input class="btn margin_bottom" type="submit" value="Continue Shopping">
	</form>
	
	<form action="cart" method="post">
	  <input type="hidden" name="action" value="checkout">
	  <input class="btn" type="submit" value="Checkout">
	</form>
	
</body>
</html>
