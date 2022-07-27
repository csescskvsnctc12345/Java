<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>管理画面</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<!-- Bootstrap 3.3.5 -->
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="ionicons-2.0.1/css/ionicons.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
	folder instead of downloading all of them to reduce the load. -->
	<link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
	<!-- iCheck -->
	<link rel="stylesheet" href="plugins/iCheck/flat/blue.css">
	<!-- Morris chart -->
	<link rel="stylesheet" href="plugins/morris/morris.css">
	<!-- jvectormap -->
	<link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
	<!-- bootstrap wysihtml5 - text editor -->
	<link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<%
	String result = (String)request.getAttribute("massage");
%>
	<div class="wrapper">

		<header class="main-header">
			<!-- Logo -->
			<a href="index.jsp" class="logo">
				<!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>A</b>dmin</span>
				<!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><b>A</b>dmin</span>
			</a>
			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
					<span class="sr-only">Toggle navigation</span>
				</a>
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- Messages: style can be found in dropdown.less-->
						<li class="dropdown messages-menu">

							<ul class="dropdown-menu">
								<li class="header">You have 4 messages</li>
								<li>
									<!-- inner menu: contains the actual data -->
									<ul class="menu">
										<li><!-- start message -->
											<a href="#">
												<div class="pull-left">
													<img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
												</div>
												<h4>
													Support Team
													<small><i class="fa fa-clock-o"></i> 5 mins</small>
												</h4>
												<p>Why not buy a new awesome theme?</p>
											</a>
										</li><!-- end message -->
										<li>
											<a href="#">
												<div class="pull-left">
													<img src="dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
												</div>
												<h4>
													AdminLTE Design Team
													<small><i class="fa fa-clock-o"></i> 2 hours</small>
												</h4>
												<p>Why not buy a new awesome theme?</p>
											</a>
										</li>
										<li>
											<a href="#">
												<div class="pull-left">
													<img src="dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
												</div>
												<h4>
													Developers
													<small><i class="fa fa-clock-o"></i> Today</small>
												</h4>
												<p>Why not buy a new awesome theme?</p>
											</a>
										</li>
										<li>
											<a href="#">
												<div class="pull-left">
													<img src="dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
												</div>
												<h4>
													Sales Department
													<small><i class="fa fa-clock-o"></i> Yesterday</small>
												</h4>
												<p>Why not buy a new awesome theme?</p>
											</a>
										</li>
										<li>
											<a href="#">
												<div class="pull-left">
													<img src="dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
												</div>
												<h4>
													Reviewers
													<small><i class="fa fa-clock-o"></i> 2 days</small>
												</h4>
												<p>Why not buy a new awesome theme?</p>
											</a>
										</li>
									</ul>
								</li>
								<li class="footer"><a href="#">See All Messages</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<ul class="sidebar-menu">
					<li class="header">MAIN NAVIGATION</li>
					<li class="active treeview">
						<a href="#">
							<i class="glyphicon glyphicon-user"></i> <span>患者リスト</span>
						</a>
						<ul class="treeview-menu">
							<li class="active"><a href="index.jsp"><i class="fa fa-circle-o"></i>患者登録</a></li>
							<!--  <li><a href="index2.html"><i class="fa fa-circle-o"></i>患者一覧</a></li>-->
						</ul>
					</li>
					<li class="treeview">
						<a href="#">
							<i class="glyphicon glyphicon-user"></i>
							<span>医者管理</span>
							<i class="fa fa-angle-left pull-right"></i>
						</a>
						<ul class="treeview-menu">
							<li><a href="doctor.jsp"><i class="fa fa-circle-o"></i>医者登録</a></li>
							<!-- <li><a href="pages/zaiko/zaiko_info.html"><i class="fa fa-circle-o"></i>医者一覧</a></li>-->
						</ul>
					</li>
					<li class="treeview">
						<a href="#">
							<i class="fa fa-pie-chart"></i>
							<span>薬管理</span>
							<i class="fa fa-angle-left pull-right"></i>
						</a>
						<ul class="treeview-menu">
							<li><a href="medicine.jsp"><i class="fa fa-circle-o"></i>薬登録</a></li>
							<!-- <li><a href="pages/huroa/haiti.html"><i class="fa fa-circle-o"></i>薬一覧</a></li>-->
						</ul>
					</li>
					<li class="treeview">
						<a href="#">
							<i class="fa fa-edit"></i> <span>処方箋</span>
							<i class="fa fa-angle-left pull-right"></i>
						</a>
						<ul class="treeview-menu">
							<li><a href="medicine_detail.jsp"><i class="fa fa-circle-o"></i>処方箋作成</a></li>
						</ul>
					</li>
					</section>
					<!-- /.sidebar -->
				</aside>

				<!-- Content Wrapper. Contains page content -->
				<div class="content-wrapper">
					<!-- Content Header (Page header) -->
					<section class="content-header">
						<h1>患者登録</h1>
						<%if(null != result){%>
							<p class="text-danger"><%out.println(result);%></p>
						<% }%>
					</section>

					<!-- Main content -->
					<section class="content">
						<div class="row">
							<div class="col-xs-10">
								<!-- AREA CHART -->
								<div class="box box-primary"><!-- 上の青い線とFormの背景白-->
									<div class="box-header with-border">
										<form method="get" action="./CustomerServlet">
											<div class="box-body">
												<div class="form-group">
													<label>患者名</label>
													<input type="text" name="customer_name" class="form-control"/>
												</div>
												<div class="form-group">
													<label>都道府県</label>
													<select name="address_1" class="form-control">
														<option value="">選択して下さい</option>
														<option value="北海道">北海道</option>
														<option value="青森県">青森県</option>
														<option value="岩手県">岩手県</option>
														<option value="宮城県">宮城県</option>
														<option value="秋田県">秋田県</option>
														<option value="山形県">山形県</option>
														<option value="福島県">福島県</option>
														<option value="茨城県">茨城県</option>
														<option value="栃木県">栃木県</option>
														<option value="群馬県">群馬県</option>
														<option value="埼玉県">埼玉県</option>
														<option value="千葉県">千葉県</option>
														<option value="東京都">東京都</option>
														<option value="神奈川県">神奈川県</option>
														<option value="新潟県">新潟県</option>
														<option value="富山県">富山県</option>
														<option value="石川県">石川県</option>
														<option value="福井県">福井県</option>
														<option value="山梨県">山梨県</option>
														<option value="長野県">長野県</option>
														<option value="岐阜県">岐阜県</option>
														<option value="静岡県">静岡県</option>
														<option value="愛知県">愛知県</option>
														<option value="三重県">三重県</option>
														<option value="滋賀県">滋賀県</option>
														<option value="京都府">京都府</option>
														<option value="大阪府">大阪府</option>
														<option value="兵庫県">兵庫県</option>
														<option value="奈良県">奈良県</option>
														<option value="和歌山県">和歌山県</option>
														<option value="鳥取県">鳥取県</option>
														<option value="島根県">島根県</option>
														<option value="岡山県">岡山県</option>
														<option value="広島県">広島県</option>
														<option value="山口県">山口県</option>
														<option value="徳島県">徳島県</option>
														<option value="香川県">香川県</option>
														<option value="愛媛県">愛媛県</option>
														<option value="高知県">高知県</option>
														<option value="福岡県">福岡県</option>
														<option value="佐賀県">佐賀県</option>
														<option value="長崎県">長崎県</option>
														<option value="熊本県">熊本県</option>
														<option value="大分県">大分県</option>
														<option value="宮崎県">宮崎県</option>
														<option value="鹿児島県">鹿児島県</option>
														<option value="沖縄県">沖縄県</option>
													</select>
												</div>
												<div class="form-group">
													<label>市区町村 住所</label>
													<input type="text" name="address_2" class="form-control"/>
												</div>
												<div class="form-group">
													<label>電話番号</label>
													<input type="text" name="tel" class="form-control"/>
												</div>
												<input type="submit" class="btn btn-primary btn-large" value="登録"/>
											</div><!-- /.box-body -->
										</form>
									</div><!-- /.box-body -->
								</div><!-- /.box -->
							</div>
						</div>
					</section><!-- /.content -->
				</div><!-- /.content-wrapper -->
				<footer class="main-footer">
					<strong>Copyright &copy; 2015 Hal</strong>
				</footer>


<!-- Add the sidebar's background. This div must be placed
	immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>
</div><!-- ./wrapper -->

<!-- jQuery 2.1.4 -->
<script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
	$.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.5 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- Morris.js charts -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="plugins/morris/morris.min.js"></script>
<!-- Sparkline -->
<script src="plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="plugins/fastclick/fastclick.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
</body>
</html>
