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
<title>予約</title>
</head>
<body>
<div id="wrapper">
	<div id="header">
		<h1>
			<font color="0055CC">薬局予約</font>
		</h1>
	</div>

	<div id="main">
		<h3 style="text-align: center; margin-top: 200px;">予約が完了しました。</h3>
	</div>
</div>
<script type="text/javascript">
//5秒後にTOPページに戻る
setTimeout("timepursue()", 5000);
function timepursue(){
	location.href = "index.jsp";
}
</script>
</body>
</html>