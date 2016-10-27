<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改剧目</title>
</head>
<body>
	<%
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
	%>
	<div align="center">
		<font size="5" color="red">修改剧目</font>
	</div>
	<form action="PlaySlt?method=modify" method="post">
		剧目id: <input type="text" name="id" /><br>
		剧目类型: <input type="text" name="type" /><br>
		剧目语种: <input type="text" name="lang" /><br> 
		剧目名字: <input type="text" name="name" /><br> 
		剧目描述: <input type="text" name="introduction" /><br> 
		剧目时长: <input type="text" name="length" /><br> 
		剧目价格: <input type="text" name="price" /><br> 
		剧目状态: <input type="text" name="status" /><br> 
		<input type="submit" value="Submit" />
	</form>
</body>
</html>