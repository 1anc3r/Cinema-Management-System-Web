<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查找员工</title>
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
<body style="background-color: #FEFEFE">
	<%
	    List<Employee> empList = null;
	    if (request.getAttribute("error") != null)
	    {
	        out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
	    }
	    if (request.getAttribute("list") != null)
	    {
	        empList = (List<Employee>) request.getAttribute("list");
	    }
	%>
	<br>
	<form name="myform"
		action="http://localhost:8080/Cinema-Management-System-Web/EmployeeSlt?method=fetch"
		method="post" enctype="multipart/form-data">
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa">员工id：</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="id" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa">员工权限：</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="accsee" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa">员工工号：</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="number" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa">员工名字：</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="name" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa">员工住址：</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="address" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa">员工电话：</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="tel" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa">员工email：</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="email" />
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
		<!--<input type="submit" value="Submit" />-->
	</form>
	<style>
table.hovertable {
	font-family: verdana,arial,sans-serif;
	font-size:18px;
	color:#333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
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
}
</style>
	<table border="1" cellspacing="0" cellpadding="0" class="hovertable" width=100%
		align="center">
		<%
		    if (empList != null)
		    {
		%>
		<tr align="center">
			<td>员工id</td>
			<td>员工权限</td>
			<td>员工工号</td>
			<td>员工名字</td>
			<td>员工住址</td>
			<td>员工电话</td>
			<td>员工email</td>
		</tr>
		<%
		    for (int i = 0; i < empList.size(); i++)
		        {
		%>
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
			<td><%=empList.get(i).getId()%></td>
			<td><%=empList.get(i).getAccess()%></td>
			<td><%=empList.get(i).getNo()%></td>
			<td><%=empList.get(i).getName()%></td>
			<td><%=empList.get(i).getAddr()%></td>
			<td><%=empList.get(i).getTel()%></td>
			<td><%=empList.get(i).getEmail()%></td>
		</tr>
		<%
		    }
		        if (empList.size() == 0)
		        {
		%>
		<tr>
			<th colspan="11">无员工信息！</th>
		</tr>
		<%
		    }
		    }
		%>
	</table>
</body>
</html>