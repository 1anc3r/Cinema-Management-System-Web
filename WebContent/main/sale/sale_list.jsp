<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>影院管理系统主页</title>
<jsp:useBean id="SaleSrv" scope="page"
	class="me.lancer.cms.service.SaleSrv"></jsp:useBean>
</head>
<body style="background-color: #FEFEFE">
	<%
		List<Sale> saleList = SaleSrv.FetchAll();
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
	%>
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
		<tr align="center">
			<th colspan="11" >订单列表</th>
		</tr>
		<tr align="center">
			<td>订单id</td>
			<td>员工</td>
			<td>下单时间</td>
			<td>付款</td>
			<td>找零</td>
			<td>订单类型</td>
			<td>订单状态</td>
		</tr>
		<%
				for (int i = 0; i < saleList.size(); i++) {
		%>
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
			<td><%=saleList.get(i).getId()%></td>
			<td><%=saleList.get(i).getEmpId()%></td>
			<td><%=saleList.get(i).getTime()%></td>
			<td><%=saleList.get(i).getPayment()%></td>
			<td><%=saleList.get(i).getChange()%></td>
			<td><%=saleList.get(i).getType()%></td>
			<td><%=saleList.get(i).getStatus()%></td>
		</tr>
		<%
			}
				if (saleList.size() == 0) {
		%>
		<tr>
			<th colspan="11">无订单信息！</th>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>