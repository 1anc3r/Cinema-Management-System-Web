<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改演出计划</title>
</head>
<body>
	<%
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
	%>
	<div align="center">
		<font size="5" color="red">修改演出计划</font>
	</div>
	<form action="ScheduleSlt?method=modify" method="post">
		演出计划id: <input type="text" name="id" /><br>
		演出厅: <input type="text" name="studid" /><br>
		剧目: <input type="text" name="playid" /><br> 
		演出时间: <input type="text" name="time" /><br> 
		演出计划价格: <input type="text" name="price" /><br> 
		<input type="submit" value="Submit" />
	</form>
</body>
</html>