<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>删除剧目</title>
</head>
<body style="background-color:#FEFEFE">
	<%
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
	%>
	<br>
	<form action="http://localhost:8080/Cinema-Management-System-Web/PlaySlt?method=delete" method="post">
		剧目id: <input type="text" name="id" /><br> <input type="submit"
			value="Submit" />
	</form>
</body>
</html>