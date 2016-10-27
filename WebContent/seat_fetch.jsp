<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查找座位</title>
</head>
<body>
	<%
		List<Seat> seatList = null;
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
		if (request.getAttribute("list") != null) {
			seatList = (List<Seat>) request.getAttribute("list");
		}
	%>
	<div align="center">
		<font size="5" color="red">查找座位</font>
	</div>
	<form action="SeatSlt?method=fetch" method="post">
		座位id: <input type="text" name="id" /><br>
		演出厅: <input type="text" name="studid" /><br>
		座位行号: <input type="text" name="row" /><br> 
		座位列号: <input type="text" name="col" /><br>  
		座位状态: <input type="text" name="status" /><br> 
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
			if (seatList != null) {
		%>
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
			}
		%>
	</table>
</body>
</html>