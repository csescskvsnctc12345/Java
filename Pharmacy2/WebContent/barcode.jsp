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
	String store_id = (String)request.getAttribute("store_id");
	String time = (String)request.getAttribute("time");
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
				<li><font color="0055CC">バーコード読み取り</font></li>
				<li>確認</li>
			</ol>
        </div>
      </div>

      <div id="main">
        <h3 style="text-align: center; margin-top: 140px;">バーコード読み取り</h3>
        <hr>
        <div style="text-align: center; margin-top: 90px;">
			<img src="img/barcode00000000000001.png">
		</div>
		<h3 style="text-align: center;">処方箋のバーコードを読み取ってください</h3>
		<div style="text-align: center;">
			<form method="get" action="./RegistrationServlet" id="barcode_form">
				<input type="hidden" id="barcode_id" name="barcode_id" value=""/>
				<input type="hidden" id="store_id" name="store_id" value="<%=store_id%>"/>
				<input type="hidden" id="time" name="time" value="<%=time%>"/>
			</form>
		</div>
      </div>
</div>

<script type="text/javascript">
$(document).ready(function(){

	$(window).keydown(function(e){
		var ndata = $("#barcode_id").val();
		var data = e.keyCode;
		var flg = true;
		switch(data){
				case 48 : data=0; break;
				case 49 : data=1; break;
				case 50 : data=2; break;
				case 51 : data=3; break;
				case 52 : data=4; break;
				case 53 : data=5; break;
				case 54 : data=6; break;
				case 55 : data=7; break;
				case 56 : data=8; break;
				case 57 : data=9; break;
				case 13 : flg=false; $('#barcode_form').submit();break;
		}
		if (flg) {
			var idata = ndata+data;
			$("#barcode_id").val(idata);
			return false;
		}
	});
});
</script>
</body>
</html>