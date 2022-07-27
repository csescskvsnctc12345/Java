<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

<title>プリント</title>
<style type="text/css">
*{
	margin: 0px;
	padding: 0px;
}
#wrapper{
	width: 990px;
	margin: 0 auto;
}
#header{
	width: 990px;
	height: 80px;
}
#main{
	width: 990px;
	height: 700px;
	padding: 5px;
	background-color: #fff;
}
body{
	background-color: #dcdcdc;
}
</style>
</head>
<%
	String customer_name = (String)request.getAttribute("customer_name");
	String barcode = (String)request.getAttribute("barcode");
	ArrayList<HashMap<String,String>> aryHash = (ArrayList<HashMap<String,String>>) request.getAttribute("data");

	int num = aryHash.size();//薬個数
%>
<body>
<div id="wrapper">
	<div id="header">
		<h1 style="text-align: center; margin-top: 100px;">処方箋</h1>
	</div>
	<div id="main">
		<h2 style="text-align: center; margin-bottom: 50px;"><%=customer_name%>さん</h2>

		<table class="table table-condensed table-striped">
			<thead>
				<tr>
					<th>お薬</th>
					<th>分量</th>
					<th>メッセージ</th>
				</tr>
			</thead>
			<tbody>
			<%for (int count = 0; count < num; count++) {%>
				<tr>
					<td><%=aryHash.get(count).get("medicine") %></td>
					<td><%=aryHash.get(count).get("quantity") %></td>
					<td><%=aryHash.get(count).get("message") %></td>
				</tr>
			<%}%>
			</tbody>
		</table>

		<div style="text-align: center; margin-top: 90px;">
			<img src="img/barcode00000000000001.png">
		</div>
		<div style="text-align: right; margin-top: 150px;">
			<img src="img/qrcode00000000000065.png">
		</div>
	</div>
</div>
</body>
</html>