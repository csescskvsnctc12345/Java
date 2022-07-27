<%@page import="java.util.ArrayList"%>
<%@page import="databasebeans.SQLManager"%>
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
<title>予約</title>
</head>
<%
ArrayList<ArrayList<String>> AryTbl = new ArrayList<ArrayList<String>>();
	SQLManager sqlm = new SQLManager();
	String sql = "SELECT pharmacy_id, pharmacy_name FROM pharmacy;";
	sqlm.establishConnection();
	AryTbl = sqlm.SelectSql(sql);
	sqlm.terminateConnection();

%>
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
		<div class="tabbable">
			<h3>薬局選択</h3>
			<hr>
			<div id="tiiki_button_box">
			<!-- 薬局の名前生成 -->
			<form method="get" action="./TimeServlet" id="store_form">
			<%if(AryTbl != null && !AryTbl.isEmpty()){%>
				<%for(ArrayList<String> rec:AryTbl){%>
				<button type="button" id="store_id_botton" onClick="control('<%=rec.get(0)%>')" class="btn btn-primary btn-large tiiki_button" style="width: 150px; height:60px;"><%=rec.get(1)%></button>
				<%} %>
			<%}%>
				<input type="hidden" id="store_id" name="store_id" value=""/>
			</form>
			</div>
		</div>
	</div>


</div>
<script type="text/javascript">
//選択した薬局のIDをServletに送る為の関数
function control(storeid_control){
	var storeid = storeid_control;
	$("#store_id").val(storeid);
	console.log(storeid);
	$('#store_form').submit();
}
</script>
</body>
</html>