<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>请修改密码</title>
</head>
<body>
	<%
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
	%>
	<div align="center">
		<font size="5" color="red">请修改密码</font>
	</div>
	<form action="http://localhost:8080/Cinema-Management-System-Web/EmployeeSlt?method=password" method="post">
		密码: <input type="text" name="password" /><br>
		<input type="submit" value="Submit" />
	</form>
</body>
</html>