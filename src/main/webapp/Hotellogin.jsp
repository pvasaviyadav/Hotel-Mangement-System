<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>${message}</h1>
   <form action="hotelloginvalidate" method="post">
       Enter email : <input type="text" name="email"><br>
       Enter password : <input type="number" name="password"><br>
       <input type="submit">
    </form>
</body>
</html>
