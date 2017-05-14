<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加影片</title>
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
<body style="background-color:#FEFEFE">
	<br>
	<form name="myform" action="FileUploadSlt" method="post"
		enctype="multipart/form-data">
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa"> 影片类型: </span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="play_type" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa"> 影片语种:</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="play_lang" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa"> 影片名字:</span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="play_name" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa"> 影片描述: </span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="play_introduction" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa"> 影片时长: </span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="play_length" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa"> 影片价格: </span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="text" class="form-control" name="play_ticket_price" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-2 item">
				<span class="aaa">影片海报: </span>
			</div>
			<div class="col-md-7">
				<div class="input-group input-group-md">
					<input type="file" class="form-control" name="play_image" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-offset-5 col-md-3 item-botton">
				<div class="btn-group btn-group-justified">
					<div class="btn-group">
						<button type="submit" class="btn btn-info"
							onclick="return isPic()">提交</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 		<input type="submit" value="Submit" onclick="return isPic()"><br> -->
		
	</form>
</body>
</html>
