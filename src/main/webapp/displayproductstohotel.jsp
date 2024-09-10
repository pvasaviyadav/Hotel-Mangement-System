<%@page import="com.jsp.hotelmanagement.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%List<Product> products= (List)request.getAttribute("products"); %>
   <table cellpadding="20px" border="2px">
     <th>id</th>
     <th>cost</th>
     <th>name</th>
     <th>type</th>
     <th>discount</th>
     <th>update</th>
     <th>delete</th>
     
     <% for(Product p:products){ 
       %>
       <tr>
         <td><%= p.getId() %></td>
         <td><%= p.getCost() %></td>
         <td><%= p.getName() %></td>
         <td><%= p.getType() %></td>
         <td><%= p.getDiscount() %></td>
         <td><a href="updateproduct?id=<%=p.getId() %>">update</a></td>
         <td><a href="delete?id=<%=p.getId() %>">delete</a></td>
      </tr>
      <%
      }
     %>
     
     <a href="HotelHome.jsp">Back to main menu</a>    <br>       <a href="hotellogout">Logout</a>
     </table>
</body>
</html>
