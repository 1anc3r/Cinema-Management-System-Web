<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查找影片</title>
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
<body style="background-color:#FEFEFE">
	<%
		List<Play> playList = null;
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
		if (request.getAttribute("list") != null) {
			playList = (List<Play>) request.getAttribute("list");
		}
	%>
	<br>
	<form action="http://localhost:8080/Cinema-Management-System-Web/PlaySlt?method=fetch" method="post">
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa"> 影片id: </span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="id" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa"> 影片类型: </span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="type" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa"> 影片语种: </span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="lang" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa"> 影片名字: </span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="name" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa"> 影片描述: </span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="introduction" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa"> 影片时长: </span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="length" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa"> 影片价格: </span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="price" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa"> 影片状态: </span>
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
						<input type="submit" class="btn btn-info" value="提交" />
					</div>
				</div>
			</div>
		</div>
		
	</form>
	<style>
table.hovertable {
	font-family: verdana,arial,sans-serif;
	font-size:18px;
	color:#333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
	table-layout:fixed;
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
	word-break:keep-all;/* 不换行 */  
    white-space:nowrap;/* 不换行 */  
    overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */  
    text-overflow:ellipsis;/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用*/  
}
</style>
	<table border="1" cellspacing="0" cellpadding="0" class="hovertable" width=100%
		align="center">
		<%
			if (playList != null) {
		%>
		<tr align="center">
			<td>影片id</td>
			<td>影片类型</td>
			<td>影片语种</td>
			<td>影片名字</td>
			<td>影片描述</td>
			<td>影片时长</td>
			<td>影片价格</td>
			<td>影片状态</td>
		</tr>
		<%
				for (int i = 0; i < playList.size(); i++) {
		%>
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
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
			}
		%>
	</table>
</body>
</html>