<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>删除剧目</title>
</head>
<body>
	<%
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
	%>
	<div align="center">
		<font size="5" color="red">删除剧目</font>
	</div>
	<form action="PlaySlt?method=delete" method="post">
		剧目id: <input type="text" name="id" /><br> <input type="submit"
			value="Submit" />
	</form>
</body>
</html>