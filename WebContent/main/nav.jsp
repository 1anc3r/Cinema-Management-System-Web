<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<style type="text/css">
  #menu{margin-top: 40px;padding:0;}
  #menu li{display:inline;}
  #menu li a{display:inline-block; padding:0 30px; height:36px; line-height:36px;
  			background:#4489FE;border-radius:5px;
            color:black; font-family:"\5FAE\8F6F\96C5\9ED1"; font-size:18px ;text-decoration: none}
  #menu li a:hover{background:#BDBDBD}
</style>
<title>导航栏</title>
</head>
<body style="background-color:#03A8F3" align="center">
<h1 align="center"><font color="#FEFEFE">影院管理系统主页</font></h3>
<ul id="menu" align="center">
  <li><a href="studio/studio_main.jsp" target="frame_bottom"><font color="#FEFEFE">影厅管理</font></a></li>
  <li><a href="seat/seat_main.jsp" target="frame_bottom"><font color="#FEFEFE">座位管理</font></a></li>
  <li><a href="play/play_main.jsp" target="frame_bottom"><font color="#FEFEFE">影片管理</font></a></li>
  <li><a href="schedule/schedule_main.jsp" target="frame_bottom"><font color="#FEFEFE">场次管理</font></a></li>
  <li><a href="sale/sale_main.jsp" target="frame_bottom"><font color="#FEFEFE">订单管理</font></a></li>
  <li><a href="employee/employee_main.jsp" target="frame_bottom"><font color="#FEFEFE">员工管理</font></a></li>
  <li><a href="datadict/datadict_main.jsp" target="frame_bottom"><font color="#FEFEFE">数据字典管理</font></a></li>
</ul>
</body>
</html>