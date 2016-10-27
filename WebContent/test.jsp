<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>空白页</title>
</head>
<body>
<br>StudioSlt<br>
<br>add<br>
<form action="StudioSlt?method=add" method="post">
  name: <input type="text" name="name" /><br>
  rows: <input type="text" name="rows" /><br>
  cols: <input type="text" name="cols" /><br>
  introduction: <input type="text" name="introduction" /><br>
  <input type="submit" value="Submit" />
</form>
<br>fetch<br>
<form action="StudioSlt?method=fetch" method="post">
  id: <input type="text" name="id" /><br>
  name: <input type="text" name="name" /><br>
  rows: <input type="text" name="rows" /><br>
  cols: <input type="text" name="cols" /><br>
  introduction: <input type="text" name="introduction" /><br>
  flag: <input type="text" name="flag" /><br>
  <input type="submit" value="Submit" />
</form>
<br>modify<br>
<form action="StudioSlt?method=modify" method="post">
  id: <input type="text" name="id" /><br>
  name: <input type="text" name="name" /><br>
  rows: <input type="text" name="rows" /><br>
  cols: <input type="text" name="cols" /><br>
  introduction: <input type="text" name="introduction" /><br>
  flag: <input type="text" name="flag" /><br>
  <input type="submit" value="Submit" />
</form>
<br>delete<br>
<form action="StudioSlt?method=delete" method="post">
  id: <input type="text" name="id" /><br>
  <input type="submit" value="Submit" />
</form>
==========================================================
<br>SeatSlt<br>
<br>fetch<br>
<form action="SeatSlt?method=fetch" method="post">
  id: <input type="text" name="id" /><br>
  studid: <input type="text" name="studid" /><br>
  rows: <input type="text" name="rows" /><br>
  cols: <input type="text" name="cols" /><br>
  status: <input type="text" name="status" /><br>
  <input type="submit" value="Submit" />
</form>
<br>modify<br>
<form action="SeatSlt?method=modify" method="post">
  id: <input type="text" name="id" /><br>
  studid: <input type="text" name="studid" /><br>
  rows: <input type="text" name="rows" /><br>
  cols: <input type="text" name="cols" /><br>
  status: <input type="text" name="status" /><br>
  <input type="submit" value="Submit" />
</form>
==========================================================
<br>PlaySlt<br>
<br>add<br>
<form action="PlaySlt?method=add" method="post">
  type: <input type="text" name="type" /><br>
  lang: <input type="text" name="lang" /><br>
  name: <input type="text" name="name" /><br>
  introduction: <input type="text" name="introduction" /><br>
  length: <input type="text" name="length" /><br>
  price: <input type="text" name="playPrice" /><br>
  <input type="submit" value="Submit" />
</form>
<br>fetch<br>
<form action="PlaySlt?method=fetch" method="post">
  id: <input type="text" name="id" /><br>
  type: <input type="text" name="type" /><br>
  lang: <input type="text" name="lang" /><br>
  name: <input type="text" name="name" /><br>
  introduction: <input type="text" name="introduction" /><br>
  length: <input type="text" name="length" /><br>
  price: <input type="text" name="playPrice" /><br>
  <input type="submit" value="Submit" />
</form>
<br>modify<br>
<form action="PlaySlt?method=modify" method="post">
  id: <input type="text" name="id" />
  type: <input type="text" name="type" /><br>
  lang: <input type="text" name="lang" /><br>
  name: <input type="text" name="name" /><br>
  introduction: <input type="text" name="introduction" /><br>
  length: <input type="text" name="length" /><br>
  price: <input type="text" name="playPrice" /><br>
  <input type="submit" value="Submit" />
</form>
<br>delete<br>
<form action="PlaySlt?method=delete" method="post">
  id: <input type="text" name="id" />
  <input type="submit" value="Submit" />
</form>
==========================================================
<br>ScheduleSlt<br>
<br>add<br>
<form action="ScheduleSlt?method=add" method="post">
  studid: <input type="text" name="studid" /><br>
  playid: <input type="text" name="playid" /><br>
  time: <input type="text" name="time" /><br>
  ticketprice: <input type="text" name="ticketprice" /><br>
  <input type="submit" value="Submit" />
</form>
<br>fetch<br>
<form action="ScheduleSlt?method=fetch" method="post">
  id: <input type="text" name="id" /><br>
  studid: <input type="text" name="studid" /><br>
  playid: <input type="text" name="playid" /><br>
  time: <input type="text" name="time" /><br>
  ticketprice: <input type="text" name="ticketprice" /><br>
  <input type="submit" value="Submit" />
</form>
<br>modify<br>
<form action="ScheduleSlt?method=modify" method="post">
  id: <input type="text" name="id" /><br>
  studid: <input type="text" name="studid" /><br>
  playid: <input type="text" name="playid" /><br>
  time: <input type="text" name="time" /><br>
  ticketprice: <input type="text" name="ticketprice" /><br>
  <input type="submit" value="Submit" />
</form>
<br>delete<br>
<form action="ScheduleSlt?method=delete" method="post">
  id: <input type="text" name="id" />
  <input type="submit" value="Submit" />
</form>
==========================================================
</body>
</html>