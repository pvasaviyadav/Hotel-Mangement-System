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
     <form:form action="saveadmin" modelAttribute="adminobj">
         Enter name : <form:input path="name" /><br><br>
         Enter email : <form:input path="email" /><br><br>
         Enter password : <form:input path="password" /><br><br>
         <input type="submit">
     </form:form>
</body>
</html>
