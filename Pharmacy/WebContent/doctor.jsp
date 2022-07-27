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
            </nav>
          </header>
          <!-- Left side column. contains the logo and sidebar -->
          <aside class="main-sidebar">
            <!-- sidebar: style can be found in sidebar.less -->
            <section class="sidebar">
              <ul class="sidebar-menu">
                <li class="header">MAIN NAVIGATION</li>
                <li class="">
                  <a href="#">
                    <i class="glyphicon glyphicon-user"></i> <span>患者リスト</span>
                  </a>
                  <ul class="treeview-menu">
                    <li class="active"><a href="index.jsp"><i class="fa fa-circle-o"></i>患者登録</a></li>
                    <!-- <li><a href="../../index2.html"><i class="fa fa-circle-o"></i>患者一覧</a></li>-->
                  </ul>
                </li>
                <li class="treeview">
                 <li class="active treeview">
                  <a href="#">
                    <i class="glyphicon glyphicon-user"></i>
                    <span>医者管理</span>
                  </a>
                  <ul class="treeview-menu">
                    <li><a href="doctor.jsp"><i class="fa fa-circle-o"></i>医者登録</a></li>
                    <!-- <li><a href="../zaiko/zaiko_info.html"><i class="fa fa-circle-o"></i>医者一覧</a></li>-->
                  </ul>
                 </li>
                </li>
                <li class="treeview">
                    <a href="#">
                      <i class="fa fa-pie-chart"></i>
                      <span>薬管理</span>
                      <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                      <li><a href="medicine.jsp"><i class="fa fa-circle-o"></i>薬登録</a></li>
                      <!-- <li><a href="../huroa/haiti.html"><i class="fa fa-circle-o"></i>薬一覧</a></li>-->
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
                  <h1>医者登録</h1>
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
                          <form method="get" action="./DoctorServlet">
                            <div class="box-body">
                              <div class="form-group">
                                <label>医者名</label>
                                <input type="text" name="doctor_name" class="form-control"/>
                              </div>
                              <input class="btn btn-primary btn-large" type="submit" value="登録"/>
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
    <!-- Bootstrap 3.3.5 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="dist/js/demo.js"></script>
  </body>
  </html>
