<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
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
<script src="js/bootstrap.min.js"></script>

<link href="css/index.css" type="text/css" rel="stylesheet">
<link href="css/time.css" type="text/css" rel="stylesheet">

<title>予約</title>
</head>
<%
	ArrayList<HashMap<String,String>> aryHash = (ArrayList<HashMap<String,String>>) request.getAttribute("data");

	int count = 0;//時間カウント用
	String strTime = "";

	int counttime = 0;
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
				<li><font color="0055CC">時間予約</font></li>
				<li>バーコード読み取り</li>
				<li>確認</li>
			</ol>
		</div>
	</div>

	<div id="main">
		<h3>受け取り時間を指定してください</h3>
		<hr>

		<div id="calendar" class="cal-context" style="width: 100%;">
			<div id="cal-day-box">
			<form method="get" action="./BarcodeServlet" id="time_form">
				<table class="table table-condensed table-striped">
					<tr>
						<th colspan="2">時間</th>
						<th>指定</th>
					</tr>
					<%for(int i = 8; i<22; i++){ %>
						<%strTime = String.valueOf(i);%>
						<%for(int cnt = strTime.length();cnt<2;cnt++){%>
							<% strTime = "0" + strTime;%>
						<%}%>
						<tr>
						<%if(aryHash.get(count).get("counttime").equals("unknown")){ %>
							<th colspan="5" onClick="control('<%=strTime%>:00')" onmouseover="this.style.backgroundColor='#80FFFF';" onmouseout="this.style.backgroundColor=''" STYLE="cursor:hand;"><%=strTime %>:00</th>
						<%}else{ %>
							<!-- 5件以上予約されていたら赤で警告を出す -->
							<%counttime =  Integer.parseInt(aryHash.get(count).get("counttime"));%>
							<%if(counttime >= 5){ %>
								<th colspan="5" onClick="control('<%=strTime%>:00')" bgcolor="#FF0000" onmouseover="this.style.backgroundColor='#80FFFF';" onmouseout="this.style.backgroundColor=''" STYLE="cursor:hand;"><%=strTime %>:00<span style="padding-left:400px"><%=aryHash.get(count).get("counttime") %>件予約されています。</span></th>
							<%}else{ %>
								<th colspan="5" onClick="control('<%=strTime%>:00')" bgcolor="#ffd700" onmouseover="this.style.backgroundColor='#80FFFF';" onmouseout="this.style.backgroundColor=''" STYLE="cursor:hand;"><%=strTime %>:00<span style="padding-left:400px"><%=aryHash.get(count).get("counttime") %>件予約されています。</span></th>
							<%} %>
						<%} %>
						</tr>
						<% count++;%>
					<%} %>
				</table>
				<input type="hidden" id="time_id" name="time_id" value=""/>
			</form>
			</div>
		</div>

	</div>
</div>
<script type="text/javascript">
//選択した時間をServletに送る為の関数
function control(time_control){
	var time = time_control;
	$("#time_id").val(time);
	console.log(time);
	$('#time_form').submit();
}
</script>
</body>
</html>