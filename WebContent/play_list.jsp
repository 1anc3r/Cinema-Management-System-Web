<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>影院管理系统主页</title>
<jsp:useBean id="PlaySrv" scope="page"
	class="me.lancer.cms.service.PlaySrv"></jsp:useBean>
</head>
<body>
	<%
		List<Play> playList = PlaySrv.FetchAll();
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
			<th colspan="11" >剧目列表</th>
		</tr>
		<tr align="center">
			<td>剧目id</td>
			<td>剧目类型</td>
			<td>剧目语种</td>
			<td>剧目名字</td>
			<td>剧目介绍</td>
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
		%>
	</table>
</body>
</html>