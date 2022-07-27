<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>ログイン</title>
</head>
<%
String msg = (String)request.getAttribute("massage");
%>
<body>
<div class = "container">
	<div class="wrapper">
		<form method="get" action="./LoginServlet" name="Login_Form" class="form-signin">
			<%if(null != msg){%>
				<p><font color="FF0000"><%=msg%></font></p>
			<% }%>
		    <h3 class="form-signin-heading">ログイン</h3>
			  <hr class="colorgraph"><br>

			  <input type="text" class="form-control" name="StoreId" placeholder="StoreId" required="" autofocus="" />
			  <input type="password" class="form-control" name="Password" placeholder="Password" required=""/>

			  <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="Login" type="Submit">Login</button>
		</form>
	</div>
</div>

</body>
</html>