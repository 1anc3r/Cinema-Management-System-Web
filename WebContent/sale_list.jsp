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
<body>
	<%
		List<Sale> saleList = SaleSrv.FetchAll();
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
		<tr align="center">
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