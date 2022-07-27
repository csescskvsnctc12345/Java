<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/top.css">
<script src="js/jquery-1.11.3.min.js"></script>
<title>予約</title>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h1>
				<font color="0055CC">薬局予約</font>
			</h1>
			<a href="cancell.jsp" class="logo" style="text-align: right;">
				<h3><font color="0055CC">予約キャンセル</font></h3>
			</a>
		</div>

		<div id="main">
			<h3 style="text-align: center; margin-top: 140px;">薬局店舗予約サービス</h3>
			<div style="margin-top: 20px;">
				<button type="button" class="btn btn-primary btn-lg center-block" onclick="location.href='store.jsp'" style="width: 190px; height: 150px;">
					<i class="glyphicon glyphicon-search"></i> 予約を開始する
				</button>
			</div>

		</div>
	</div>




	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>