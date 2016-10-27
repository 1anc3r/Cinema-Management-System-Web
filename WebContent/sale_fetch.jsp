<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="me.lancer.cms.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查找订单</title>
</head>
<body>
	<%
		List<Sale> saleList = null;
		if (request.getAttribute("error") != null) {
			out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
		}
		if (request.getAttribute("list") != null) {
			saleList = (List<Sale>) request.getAttribute("list");
		}
	%>
	<div align="center">
		<font size="5" color="red">查找订单</font>
	</div>
	<form action="PlaySlt?method=fetch" method="post">
		订单id: <input type="text" name="id" /><br>
		员工: <input type="text" name="emp" /><br>
		下单时间: <input type="text" name="time" /><br> 
		付款: <input type="text" name="payment" /><br> 
		找零: <input type="text" name="change" /><br> 
		订单类型: <input type="text" name="type" /><br> 
		订单状态: <input type="text" name="status" /><br> 
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
			if (saleList != null) {
		%>
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
			}
		%>
	</table>
</body>
</html>