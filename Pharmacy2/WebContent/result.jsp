<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>予約</title>
</head>
<%
	//SELECTした名前を取得
	String store_name = (String)request.getAttribute("store_name");
	String user_name = (String)request.getAttribute("user_name");
	String time = (String)request.getAttribute("time");
	//IDを取得
	String barcode_id = (String)request.getAttribute("barcode_id");
	String store_id = (String)request.getAttribute("store_id");
%>
<body>
<div id="wrapper">
	<div id="header">
		<a href="index.jsp" class="logo">
			<h1><font color="0055CC">薬局予約</font></h1>
		</a>

		<div class="form-group">
			<ol class="breadcrumb">
		        <li>店舗選択</li>
				<li>時間予約</li>
				<li>バーコード読み取り</li>
				<li><font color="0055CC">確認</font></li>
			</ol>
		</div>
	</div>

	<div id="main">
		<h3>確認</h3>
		<hr>
		<h3>薬局名</h3>
		<p><%=store_name%></p>
		<h3 style="margin-top: 50px;">氏名</h3>
		<p><%=user_name%>さん</p>
		<h3 style="margin-top: 50px;">予約時間</h3>
		<p><%=time%></p>

		<div style="text-align: center;">
			<form method="get" action="./ResultServlet" id="reservation_result_form">
				<input type="hidden" id="store_id" name="store_id" value="<%=store_id%>"/>
				<input type="hidden" id="barcode_id" name="barcode_id" value="<%=barcode_id%>"/>
				<input type="hidden" id="time" name="time" value="<%=time%>"/>
				<input style="width: 150px; height:60px; margin-top: 30px;" class="btn btn-primary btn-large" type="submit" value="予約を決定する"/>
			</form>
		</div>
	</div>
</div>
</body>
</html>