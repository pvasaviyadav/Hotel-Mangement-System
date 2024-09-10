<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    ${message}
   <form action="adminloginvalidate" method="post">
      Enter name : <input type="text" name="name"><br><br> 
      Enter password : <input type="number" name="password"><br><br> 
      <input type="submit" value="Login">
     </form>
</body>
</html>
