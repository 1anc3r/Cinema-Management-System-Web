<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>影院管理系统主页</title>
<jsp:useBean id="SeatSrv" scope="page"
	class="me.lancer.cms.service.SeatSrv"></jsp:useBean>
</head>
<body>
	<%
		List<Seat> seatList = SeatSrv.FetchAll();
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
			<th colspan="11" >座位列表</th>
		</tr>
		<tr align="center">
			<td>座位id</td>
			<td>演出厅</td>
			<td>座位行号</td>
			<td>座位列号</td>
			<td>座位状态</td>
		</tr>
		<%
			for (int i = 0; i < seatList.size(); i++) {
		%>
		<tr align="center">
			<td><%=seatList.get(i).getId()%></td>
			<td><%=seatList.get(i).getStudioId()%></td>
			<td><%=seatList.get(i).getRow()%></td>
			<td><%=seatList.get(i).getColumn()%></td>
			<td><%=seatList.get(i).getSeatStatus()%></td>
		</tr>
		<%
			}
			if (seatList.size() == 0) {
		%>
		<tr>
			<th colspan="11">无座位信息！</th>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>