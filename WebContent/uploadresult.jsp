<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
	<h1>提交结果</h1><br>
	 影片类型:${play.typeId}<br>
	 影片语言:${play.langId}<br>
	 影片名称:${play.name}<br>
	 影片介绍:${play.introduction}<br>
 	 影片时长:${play.length}分钟<br>
 	 影片票价:${play.price} 元<br>
	 影片海报:<img src="${play.image}" width="200px"><br>
	 影片海报:${play.image}<br>
</body>
</html>
