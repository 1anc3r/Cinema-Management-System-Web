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
<body style="background-color: #FEFEFE">
	<%
		int intCurPage = 1;
		int pageSize = 5;
		int itemCount = StudioSrv.FetchAll().size();
		int pageCount = (itemCount % pageSize == 0) ? (itemCount / pageSize) : (itemCount / pageSize + 1);
		String strCurPage = request.getParameter("currentPage");
		if (strCurPage != null) {
			intCurPage = Integer.parseInt(strCurPage);
		}
		List<Studio> studList = StudioSrv.FetchByPage(pageSize * (intCurPage - 1), pageSize);
	%>
	<style>
		table.hovertable {
	font-family: verdana,arial,sans-serif;
	font-size:18px;
	color:#333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}
table.hovertable th {
	background-color:#c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.hovertable tr {
	background-color:#d4e3e5;
	font-size:18px;
}
table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
	font-size:18px;
}
</style>
	<table border="1" cellspacing="0" cellpadding="0" class="hovertable"
		width=100% align="center">
		<tr align="center">
			<th colspan="11">影厅列表</th>
		</tr>
		<tr align="center">
			<td>影厅id</td>
			<td>影厅名称</td>
			<td>影厅行数</td>
			<td>影厅列数</td>
			<td>影厅描述</td>
			<td>影厅状态</td>
		</tr>
		<%
			for (int i = 0; i < studList.size(); i++) {
		%>
		<tr onmouseover="this.style.backgroundColor='#ffff66';"
			onmouseout="this.style.backgroundColor='#d4e3e5';">
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
			<th colspan="11">无影厅信息！</th>
		</tr>
		<%
			}
		%>
	</table>
	<div align="center">
		<a href="studio_list.jsp?currentPage=1">首页</a> <a
			href="studio_list.jsp?currentPage=<%=(intCurPage - 1 < 1) ? 1 : (intCurPage - 1)%>">上一页</a>
		<a
			href="studio_list.jsp?currentPage=<%=(intCurPage + 1 > pageCount) ? pageCount : (intCurPage + 1)%>">下一页</a>
		<a href="studio_list.jsp?currentPage=<%=pageCount%>">尾页</a> 第<%=intCurPage%>页/共<%=pageCount%>页
	</div>
</body>
</html>