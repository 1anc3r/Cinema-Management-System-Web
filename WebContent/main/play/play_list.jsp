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
<body style="background-color:#FEFEFE">
	<%
		int intCurPage = 1;
		int pageSize = 5;
		int itemCount = PlaySrv.FetchAll().size();
		int pageCount = (itemCount % pageSize == 0) ? (itemCount / pageSize) : (itemCount / pageSize + 1);
		String strCurPage = request.getParameter("currentPage");
		if (strCurPage != null) {
			intCurPage = Integer.parseInt(strCurPage);
		}
		List<Play> playList = PlaySrv.FetchByPage(pageSize * (intCurPage - 1), pageSize);
	%>
	<style>
table.hovertable {
	font-family: verdana, arial, sans-serif;
	font-size: 18px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
	table-layout: fixed;
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
	word-break: keep-all; /* 不换行 */
	white-space: nowrap; /* 不换行 */
	overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
	text-overflow: ellipsis;
	/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用*/
}
</style>
	<table border="1" cellspacing="0" cellpadding="0" class="hovertable" width=100%
		width=100% align="center">
		
		<tr align="center" width=100%>
			<td>影片海报</td>
			<td>影片id</td>
			<td>影片类型</td>
			<td>影片语种</td>
			<td>影片名字</td>
			<td>影片介绍</td>
			<td>影片时长</td>
			<td>影片价格</td>
			<td>影片状态</td>
		</tr>
		<%
			for (int i = 0; i < playList.size(); i++) {
		%>
		<tr onmouseover="this.style.backgroundColor='#ffff66';"
			onmouseout="this.style.backgroundColor='#d4e3e5';" width=100%>
			<td><img src="<%=playList.get(i).getImage()%>" width="120px"
				height="160px"></td>
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
			<th colspan="11">无影片信息！</th>
		</tr>
		<%
			}
		%>
	</table>
	<div align="center">
		<a href="play_list.jsp?currentPage=1">首页</a> <a
			href="play_list.jsp?currentPage=<%=(intCurPage - 1 < 1) ? 1 : (intCurPage - 1)%>">上一页</a>
		<a
			href="play_list.jsp?currentPage=<%=(intCurPage + 1 > pageCount) ? pageCount : (intCurPage + 1)%>">下一页</a>
		<a href="play_list.jsp?currentPage=<%=pageCount%>">尾页</a> 第<%=intCurPage%>页/共<%=pageCount%>页
	</div>
</body>
</html>