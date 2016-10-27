<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查找员工</title>
</head>
<body>
	<%
		List<Employee> empList = null;
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
		if (request.getAttribute("list") != null) {
			empList = (List<Employee>) request.getAttribute("list");
		}
	%>
	<div align="center">
		<font size="5" color="red">查找员工</font>
	</div>
	<form action="EmployeeSlt?method=fetch" method="post">
		员工id: <input type="text" name="id" /><br>
		员工权限: <input type="text" name="access" /><br> 
		员工工号: <input type="text" name="number" /><br> 
		员工名字: <input type="text" name="name" /><br> 
		员工住址: <input type="text" name="address" /><br> 
		员工电话: <input type="text" name="tel" /><br> 
		员工email: <input type="text" name="email" /><br> 
		<input type="submit" value="Submit" />
	</form>
	<style>
.table {
	width: 100%;
}
</style>
	<table border="1" cellspacing="0" cellpadding="0" class="table"
		align="center">
		<%
			if (empList != null) {
		%>
		<tr align="center">
			<td>员工id</td>
			<td>员工权限</td>
			<td>员工工号</td>
			<td>员工名字</td>
			<td>员工住址</td>
			<td>员工电话</td>
			<td>员工email</td>
		</tr>
		<%
				for (int i = 0; i < empList.size(); i++) {
		%>
		<tr align="center">
			<td><%=empList.get(i).getId()%></td>
			<td><%=empList.get(i).getAccess()%></td>
			<td><%=empList.get(i).getNo()%></td>
			<td><%=empList.get(i).getName()%></td>
			<td><%=empList.get(i).getAddr()%></td>
			<td><%=empList.get(i).getTel()%></td>
			<td><%=empList.get(i).getEmail()%></td>
		</tr>
		<%
			}
				if (empList.size() == 0) {
		%>
		<tr>
			<th colspan="11">无员工信息！</th>
		</tr>
		<%
			}
			}
		%>
	</table>
</body>
</html>