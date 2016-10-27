<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查找演出计划</title>
</head>
<body>
	<%
		List<Schedule> schedList = null;
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
		if (request.getAttribute("list") != null) {
			schedList = (List<Schedule>) request.getAttribute("list");
		}
	%>
	<div align="center">
		<font size="5" color="red">查找演出计划</font>
	</div>
	<form action="ScheduleSlt?method=fetch" method="post">
		演出计划id: <input type="text" name="id" /><br>
		演出厅: <input type="text" name="studid" /><br>
		剧目: <input type="text" name="playid" /><br> 
		演出时间: <input type="text" name="time" /><br> 
		演出计划价格: <input type="text" name="price" /><br> 
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
			if (schedList != null) {
		%>
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
			<th colspan="11">无剧目信息！</th>
		</tr>
		<%
			}
			}
		%>
	</table>
</body>
</html>