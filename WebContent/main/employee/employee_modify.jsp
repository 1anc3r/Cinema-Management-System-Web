<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改员工</title>
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
<script type="text/javascript">
	function isPic() {
		var pic = myform.play_image.value;
		var ext = pic.substring(pic.lastIndexOf(".") + 1);
		//可以再添加允许类型
		if (ext.toLowerCase() == "jpg" || ext.toLowerCase() == "png"
				|| ext.toLowerCase() == "gif")
			return true;
		else {
			alert("只能上传jpg、png、gif图像!");
			return false;
		}
	}
</script>
</head>
<body style="background-color: #FEFEFE">
	<%
	    if (request.getAttribute("error") != null)
	    {
	        out.print("<script>alert('" + request.getAttribute("error") + "');</script>");
	    }
	%>
	<br>
	<form name="myform"
		action="http://localhost:8080/Cinema-Management-System-Web/EmployeeSlt?method=modify"
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
				<span class="aaa">员工密码：</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="password" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa">员工姓名：</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="name" />
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
				<span class="aaa">员工头像: </span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="file" class="form-control" name="image" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-5 col-md-3 item-botton">
				<div class="btn-group btn-group-justified">
					<div class="btn-group">
						<input type="submit" class="btn btn-info" 
							onclick="return isPic()" value="提交" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>