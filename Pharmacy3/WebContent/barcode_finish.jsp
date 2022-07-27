<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" media="screen" href="Lib/jquery-ui-1.9.2.custom/css/redmond/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" />
<link type="text/css" media="screen" href="Lib/jquery.jqGrid-5.0.0/css/ui.jqgrid.css" rel="stylesheet" />
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="Lib/jquery-ui-1.9.2.custom/js/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="Lib/jquery.jqGrid-5.0.0/js/jquery.jqGrid.min.js" ></script>
<script type="text/javascript" src="Lib/jquery.jqGrid-5.0.0/js/i18n/grid.locale-ja.js" ></script>

<link rel="stylesheet" type="text/css" href="css/index.css">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<title>薬局画面</title>
</head>
<body>
<%
String msg = (String)request.getAttribute("msg");
String store_name = (String)session.getAttribute("store_name");

%>
<div id="wrapper">
	<div id="header">
		<h1><a href="login.jsp"><%=store_name %></a></h1>
		<h2>
			<font color="00ff00">予約完了画面</font>
		</h2>
	</div>
	<div class="nav">
		<ul class="nl clearFix">
			<li><a href="index.jsp">予約一覧 <span class="en">Reservation</span></a></li>
			<li><a href="state_change.jsp">処方完了一覧 <span class="en">Completion</span></a></li>
			<li id="select"><a href="barcode_finish.jsp">予約完了 <span class="en">Barcode</span></a></li>
		</ul>
	</div>
	<div id="main">
		<%if(null != msg){%>
			<h3><font color="ff0000"><%=msg%></font></h3>
		 <% }%>
        <div style="text-align: center; margin-top: 200px; margin-bottom: 50px;">
			<img src="img/barcode00000000000001.png">
		</div>
		<h3 style="text-align: center;">処方箋のバーコードを読み取ってください</h3>

		<!-- バーコードのIDを送る用 -->
		<form method="get" action="./BarcodeServlet" id="barcode_form">
			<input type="hidden" id="barcode_id" name="barcode_id" value=""/>
		</form>
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