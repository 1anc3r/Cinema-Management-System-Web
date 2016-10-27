<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查找演出厅</title>
</head>
<body>
	<%
		List<Studio> studList = null;
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
		if (request.getAttribute("list") != null) {
			studList = (List<Studio>) request.getAttribute("list");
		}
	%>
	<div align="center">
		<font size="5" color="red">查找演出厅</font>
	</div>
	<form action="StudioSlt?method=fetch" method="post">
		演出厅id: <input type="text" name="id" /><br> 演出厅名: <input
			type="text" name="name" /><br> 演出厅行数: <input type="text"
			name="rows" /><br> 演出厅列数: <input type="text" name="cols" /><br>
		演出厅描述: <input type="text" name="introduction" /><br> 演出厅状态: <input
			type="text" name="flag" /><br> <input type="submit"
			value="Submit" />
	</form>
	<style>
.table {
	width: 100%;
}
</style>
	<table border="1" cellspacing="0" cellpadding="0" class="table"
		align="center">
		<%
			if (studList != null) {
		%>
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
			}
		%>
	</table>
</body>
</html>