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
<link rel="stylesheet" type="text/css" href="css/store.css">
<script src="js/jquery-1.11.3.min.js"></script>
<title>予約</title>
</head>
<body>
<div id="wrapper">
      <div id="header">
        <a href="index.jsp" class="logo">
			<h1><font color="0055CC">薬局予約</font></h1>
		</a>

        <div class="form-group">
	        <ol class="breadcrumb">
		        <li><font color="0055CC">店舗選択</font></li>
				<li>時間予約</li>
				<li>バーコード読み取り</li>
				<li>確認</li>
			</ol>
        </div>
      </div>

      <div id="main">
        <h3>薬局をさがす</h3>
        <hr>
        <div id="tiiki_button_box">
          <button type="button" class="btn btn-primary btn-large tiiki_button" style="width: 150px; height:60px;">三重県</button>
          <button type="button" class="btn btn-primary btn-large tiiki_button" style="width: 150px; height:60px;">滋賀</button>
          <button type="button" class="btn btn-primary btn-large tiiki_button" style="width: 150px; height:60px;">京都府</button>
          <button type="button" class="btn btn-primary btn-large tiiki_button" onclick="location.href='store_select.jsp'" style="width: 150px; height:60px;">大阪府</button>
          <button type="button" class="btn btn-primary btn-large tiiki_button" style="width: 150px; height:60px;">兵庫県</button>
          <button type="button" class="btn btn-primary btn-large tiiki_button" style="width: 150px; height:60px;">奈良県</button>
          <button type="button" class="btn btn-primary btn-large tiiki_button" style="width: 150px; height:60px;">和歌山県</button>
        </div>
      </div>
    </div>




    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>