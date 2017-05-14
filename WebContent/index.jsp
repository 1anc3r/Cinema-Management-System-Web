<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>欢迎使用1anc3r影院管理系统</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src='js/index.js'></script>
</head>
<body>
	<%
		if (session.getAttribute("error") != null) {
			out.print("<script>alert('" + session.getAttribute("error") + "');</script>");
			request.getSession().invalidate();
		}
	%>
	<div class="header"></div>
	<div class="container">
		<div class="subTitle">
			<p>欢迎使用</p>
		</div>
		<div class="mainTitle">
			<p>1anc3r影院管理系统</p>
		</div>
		<div id="buttonBox">
			<div class="button" name='login'>登录</div>
		</div>
	</div>
	<div id="shadow">
		<div class="modelBox">
			<div class="model_title">登录</div>
			<div class="model_input">
				<div>
					<input type="text" placeholder="用户名" class="inputUsername">
				</div>
				<div>
					<input type="password" placeholder="密码" class="inputPassword">
				</div>
			</div>
			<div class="errText" value='123'></div>
			<div id="login" class="bigButton">立即登录</div>
		</div>
	</div>
</body>
</html>