<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>影院管理系统主页</title>
<jsp:useBean id="EmployeeSrv" scope="page"
	class="me.lancer.cms.service.EmployeeSrv"></jsp:useBean>
</head>
<body>
	<%
		List<Employee> empList = EmployeeSrv.FetchAll();
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
	%>
	<style>
.table {
	width: 100%;
}
</style>
	<table border="1" cellspacing="0" cellpadding="0" class="table"
		align="center">
		<tr align="center">
			<th colspan="11" >剧目列表</th>
		</tr>
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
			<th colspan="11">无剧目信息！</th>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>