<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查找座位</title>
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
<style type="text/css">
.msg {
	margin-top: 20px;
	margin-bottom: 24px;
	font-size: 18px;
	line-height: 28px;
	color: #333;
}

.aaa {
	text-align: right;
	font-size: 18px;
	color: #333;
	line-height: 45px;
}

.item {
	text-align: right;
}

.input-group {
	width: 320px;
}

.item-input {
	margin-bottom: 11px;
}

.item-select {
	margin-top: 11px;
}

.item-botton {
	margin-top: 11px;
	margin-bottom: 50px;
}
</style>
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
	<br>
	<form action="http://localhost:8080/Cinema-Management-System-Web/SeatSlt?method=fetch" method="post">
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa">座位id:</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="id" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa">影厅id:</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="studid" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa">座位行号:</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="row" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa">座位列号:</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="col" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa">座位状态:</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="status" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-5 col-md-3 item-botton">
				<div class="btn-group btn-group-justified">
					<div class="btn-group">
						<button type="submit" class="btn btn-info"
							>提交</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 影厅id: <input type="text" name="id" /><br> 
		影厅名: <input type="text" name="name" /><br> 
		影厅行数: <input type="text" name="rows" /><br> 
		影厅列数: <input type="text" name="cols" /><br>
		影厅描述: <input type="text" name="introduction" /><br> 
		影厅状态: <input type="text" name="flag" /><br> 
		<input type="submit"  value="Submit" /> -->
	</form>
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
	<table border="1" cellspacing="0" cellpadding="0" class="hovertable" width=100%
		align="center">
		<%
			if (seatList != null) {
		%>
		<tr align="center">
			<td>座位id</td>
			<td>影厅id</td>
			<td>座位行号</td>
			<td>座位列号</td>
			<td>座位状态</td>
		</tr>
		<%
				for (int i = 0; i < seatList.size(); i++) {
		%>
		<tr onmouseover="this.style.backgroundColor='#ffff66';"
			onmouseout="this.style.backgroundColor='#d4e3e5';">
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