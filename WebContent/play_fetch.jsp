<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查找剧目</title>
</head>
<body>
	<%
		List<Play> playList = null;
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
		if (request.getAttribute("list") != null) {
			playList = (List<Play>) request.getAttribute("list");
		}
	%>
	<div align="center">
		<font size="5" color="red">查找剧目</font>
	</div>
	<form action="PlaySlt?method=fetch" method="post">
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
	<style>
.table {
	width: 100%;
}
</style>
	<table border="1" cellspacing="0" cellpadding="0" class="table"
		align="center">
		<%
			if (playList != null) {
		%>
		<tr align="center">
			<td>剧目id</td>
			<td>剧目类型</td>
			<td>剧目语种</td>
			<td>剧目名字</td>
			<td>剧目描述</td>
			<td>剧目时长</td>
			<td>剧目价格</td>
			<td>剧目状态</td>
		</tr>
		<%
				for (int i = 0; i < playList.size(); i++) {
		%>
		<tr align="center">
			<td><%=playList.get(i).getId()%></td>
			<td><%=playList.get(i).getTypeId()%></td>
			<td><%=playList.get(i).getLangId()%></td>
			<td><%=playList.get(i).getName()%></td>
			<td><%=playList.get(i).getIntroduction()%></td>
			<td><%=playList.get(i).getLength()%></td>
			<td><%=playList.get(i).getPrice()%></td>
			<td><%=playList.get(i).getStatus()%></td>
		</tr>
		<%
			}
				if (playList.size() == 0) {
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