<%@page import="com.jsp.hotelmanagement.Hotel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <% Integer hotel=(Integer)session.getAttribute("hotelinfo"); 
      if(hotel != null) {
      %>
     <a href="addproduct">Add Product</a><br><br>
     <a href="fetchallhotelproducts">View All Products</a><br><br>
     <a href="updateproduct">Update Product By Id</a><br><br>
     <a href="delete">Delete Product By Id</a><br><br>
     <a href="">Provide discount</a><br><br>
     <%
     } else {
     %>
         <a href="Hotellogin.jsp">Login First</a>
         <%
     }
         %>
</body>
</html>
