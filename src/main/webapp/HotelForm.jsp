<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form:form action="savehotel" modelAttribute="hotelobj">
         Enter Hotel name : <form:input path="name" /><br><br>
         Enter contact num : <form:input path="mobilenumber" /><br><br>
         Enter email : <form:input path="email" /><br><br>
         Enter password : <form:input path="password" /><br><br>
         Enter address : <form:input path="address" /><br><br>
         <input type="submit">
     </form:form>
</body>
</html>
