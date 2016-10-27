<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>影院管理系统主页</title>
<jsp:useBean id="ScheduleSrv" scope="page"
	class="me.lancer.cms.service.ScheduleSrv"></jsp:useBean>
</head>
<body>
	<%
		List<Schedule> schedList = ScheduleSrv.FetchAll();
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
			<th colspan="11" >演出计划列表</th>
		</tr>
		<tr align="center">
			<td>演出计划id</td>
			<td>演出厅</td>
			<td>剧目</td>
			<td>演出时间</td>
			<td>演出价格</td>
		</tr>
		<%
			for (int i = 0; i < schedList.size(); i++) {
		%>
		<tr align="center">
			<td><%=schedList.get(i).getId()%></td>
			<td><%=schedList.get(i).getStudioId()%></td>
			<td><%=schedList.get(i).getPlayId()%></td>
			<td><%=schedList.get(i).getTime()%></td>
			<td><%=schedList.get(i).getPrice()%></td>
		</tr>
		<%
			}
			if (schedList.size() == 0) {
		%>
		<tr>
			<th colspan="11">无演出计划信息！</th>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>