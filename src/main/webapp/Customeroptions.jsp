<%@page import="com.jsp.hotelmanagement.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
      <% 
     Integer u=(Integer) session.getAttribute("customerinfo"); 
     %>
     
     <%if(u!=null)  {
     %>
    <h1>${message}</h1> <br><br>
    <a href="readhotelname.jsp">view product by hotel</a><br><br>
    <a href="fetchallproducts">Buy products</a><br><br>
    <a href="readpricerange.jsp">View products between price Range</a><br><br>
    <a href="">View previce orders</a>
    <%
    } else { 
     %>
    <a href="customerlogin.jsp"><h1>Please Login first</h1></a>
    
    <% } %>
</body>
</html>
