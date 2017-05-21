<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>演出厅-座位</title>

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
	<form
		action="http://localhost:8080/Cinema-Management-System-Web/SeatSlt?method=fetch"
		method="post">
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
			<div class="col-md-offset-5 col-md-3 item-botton">
				<div class="btn-group btn-group-justified">
					<div class="btn-group">
						<button type="submit" class="btn btn-info">提交</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<style>
table.hovertable {
	font-family: verdana, arial, sans-serif;
	font-size: 18px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.hovertable th {
	background-color: #c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

table.hovertable tr {
	background-color: #d4e3e5;
	font-size: 18px;
}

table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
	font-size: 18px;
}
</style>
	<table border="1" cellspacing="0" cellpadding="0" class="hovertable"
		width=100% align="center">
		<%
			if (seatList != null) {
				if (seatList.size() > 0) {
					int size = seatList.size() - 1;
					int rows = seatList.get(size).getRow();
					int cols = seatList.get(size).getColumn();
					int id = 0;
					for (int i = 0; i < rows; i++) {
		%>
		<tr>
			<%
				for (int j = 0; j < cols; j++) {
								if (seatList.get(id).getSeatStatus() == -1) {
			%>
			<td><img
				src="http://localhost:8080/Cinema-Management-System-Web/img/seat_broken.png" height="48px" width="48px"/></td>
			<%
				} else if (seatList.get(id).getSeatStatus() == 1) {
			%>
			<td><img
				src="http://localhost:8080/Cinema-Management-System-Web/img/seat_sale.png" height="48px" width="48px"/></td>
			<%
				} else if (seatList.get(id).getSeatStatus() == 0) {
					%>
					<td><img
						src="" height="10px" width="10px"/></td>
					<%
						} else if (seatList.get(id).getSeatStatus() == 1) {
							%>
							<td><img
								src="http://localhost:8080/Cinema-Management-System-Web/img/seat_selected.png" height="10px" width="10px"/></td>
							<%
								} else if (seatList.get(id).getSeatStatus() == 9) {
									%>
									<td><img
										src="http://localhost:8080/Cinema-Management-System-Web/img/seat_sold.png" height="10px" width="10px"/></td>
									<%
										}
								id++;
							}
			%>
		</tr>
		<%
			}
				}
			}
		%>
	</table>
</body>
</html>