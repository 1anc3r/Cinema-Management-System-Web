<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加员工</title>
</head>
<body>
	<%
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
	%>
	<div align="center">
		<font size="5" color="red">添加员工</font>
	</div>
	<form action="EmployeeSlt?method=add" method="post">
		员工权限: <input type="text" name="access" /><br> 
		员工工号: <input type="text" name="number" /><br> 
		员工名字: <input type="text" name="name" /><br> 
		员工住址: <input type="text" name="address" /><br> 
		员工电话: <input type="text" name="tel" /><br> 
		员工email: <input type="text" name="email" /><br> 
		<input type="submit" value="Submit" />
	</form>
</body>
</html>