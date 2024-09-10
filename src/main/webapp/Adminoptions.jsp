<%@page import="com.jsp.hotelmanagement.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <% 
     Admin u=(Admin) session.getAttribute("admininfo"); 
     %>
     
     <%if(u!=null)  {
     %>
    <h1>${message}</h1> <br><br>
    <a href="fetchunapprovedhotels">Approve Hotel</a><br><br>
    <a href="fetchunblockedhotels">Block Hotel</a><br><br>
    <a href="fetchblockedhotels">Unblock Hotel</a><br><br>
    <a href="ProvideDiscount">Provide Discount</a><br><br>
    <%
    } else { 
     %>
    <a href="AdminLogin.jsp"><h1>Please Login first</h1></a>
    
    <% } %>
    
</body>
</html>
