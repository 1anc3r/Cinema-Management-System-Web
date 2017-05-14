<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
<script type="text/javascript">
function isPic()
{
    var pic=myform.play_image.value;
    var ext=pic.substring(pic.lastIndexOf(".")+1);
    //可以再添加允许类型
    if(ext.toLowerCase()=="jpg" || ext.toLowerCase()=="png" || ext.toLowerCase()=="gif")
        return true;
    else
    {
    	alert("只能上传jpg、png、gif图像!");
        return false;
    }
}
</script>
</head>
<body>
	<div align="center">
		<font size="5" color="red">上传图片</font>
	</div>
	<form name="myform" action="FileUploadSlt" method="post" enctype="multipart/form-data">
	 剧目类型: <input type="text" name="play_type"><br>
	 剧目语种: <input type="text" name="play_lang"><br>
	 剧目名字: <input type="text" name="play_name"><br>
	 剧目描述: <input type="text" name="play_introduction"><br>
 	 剧目时长: <input type="text" name="play_length">分钟<br>
 	 剧目价格: <input type="text" name="play_ticket_price"><br>
	 剧目海报: <input type="file" name="play_image"><br>
 	 <input type="submit" value="Submit" onclick="return isPic()"><br>
	</form>
</body>
</html>
