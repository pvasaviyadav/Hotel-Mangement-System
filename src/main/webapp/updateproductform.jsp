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
    <form:form action="updateproductinfo" modelAttribute="existingProductInfo">
       Id : <form:input path="id" />
       Enter name : <form:input path="id" readonly="true" />
       Enter type : <form:input path="name" />
       Enter cost : <form:input path="type" />
       Enter discount : <form:input path="discount" />
       <input type="submit">
    </form:form>
</body>
</html>
