<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>影院管理系统主页</title>
<jsp:useBean id="StudioSrv" scope="page"
	class="me.lancer.cms.service.StudioSrv"></jsp:useBean>
</head>
<body>
	<%
		List<Studio> studList = StudioSrv.FetchAll();
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
			<th colspan="11" >演出厅列表</th>
		</tr>
		<tr align="center">
			<td>演出厅id</td>
			<td>演出厅名</td>
			<td>演出厅行数</td>
			<td>演出厅列数</td>
			<td>演出厅描述</td>
			<td>演出厅状态</td>
		</tr>
		<%
			for (int i = 0; i < studList.size(); i++) {
		%>
		<tr align="center">
			<td><%=studList.get(i).getID()%></td>
			<td><%=studList.get(i).getName()%></td>
			<td><%=studList.get(i).getRowCount()%></td>
			<td><%=studList.get(i).getColCount()%></td>
			<td><%=studList.get(i).getIntroduction()%></td>
			<td><%=studList.get(i).getStudioFlag()%></td>
		</tr>
		<%
			}
			if (studList.size() == 0) {
		%>
		<tr>
			<th colspan="11">无演出厅信息！</th>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>