<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改座位</title>
</head>
<body>
	<%
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
	%>
	<div align="center">
		<font size="5" color="red">修改座位</font>
	</div>
	<form action="SeatSlt?method=modify" method="post">
		座位id: <input type="text" name="id" /><br>
		演出厅: <input type="text" name="studid" /><br>
		座位行号: <input type="text" name="row" /><br> 
		座位列号: <input type="text" name="col" /><br>  
		座位状态: <input type="text" name="status" /><br> 
		<input type="submit" value="Submit" />
	</form>
</body>
</html>