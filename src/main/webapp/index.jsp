<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>井控产品质量监督系统</title>
	<!-- 告诉浏览器使用该网页是响应式布局网页 -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

	<!-- 引入bootstrap依赖 -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap-select.min.css'/>">
	<!-- 字体和图表 -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/font-awesome.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/dataTables.bootstrap.min.css'/>">
	<!-- 样式 -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/AdminLTE.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/skin-purple.min.css'/>">
    <!-- dataTables选中表格某一行的插件 -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/select.bootstrap.min.css'/>">
    <!-- dataTables的按钮插件 -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/buttons.bootstrap.min.css'/>">
    <!-- dataTables的Editor插件 -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/editor.bootstrap.min.css'/>">
    <!-- dataTables插件colRereorder -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/colReorder.bootstrap.min.css'/>">

 	<style type="text/css">
		table{
		  text-align: center;
		}
	</style>  
</head>

<body class="hold-transition skin-purple sidebar-mini">
<div class="wrapper">
  <header class="main-header">
    <!-- Logo -->
    <a href="<c:url value='/index.jsp'/>" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>井控</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>井控产品</b>质量监督系统</span>
    </a>
    <!-- 顶部导航栏 -->
    <nav class="navbar navbar-static-top">
      <!-- 侧边栏 按钮-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="images/user.jpg" class="user-image" alt="用户头像">
              <span class="hidden-xs">
              	<c:out value="${sessionScope.user.name }"/>
			  </span>
            </a>

            <ul class="dropdown-menu">
              <li class="user-header">
                <img src="images/user.jpg" class="img-circle" alt="用户头像">
                <h3>
                  <!-- 48是0的ASCII码 -->
                  	<c:choose>
                  		<c:when test="${sessionScope.user.privilege == '48'}">
                  			<c:out value='管理员'/>
                  		</c:when>
                  		<c:otherwise>
                  			<c:out value="普通用户"/>
                  		</c:otherwise>
                  	</c:choose>
                </h3>
              </li>
              <!-- Menu Footer-->
              <li class="user-footer" id="loginOut">
                  <a href="<c:url value='/loginOut.action'/>" class="btn btn-lg btn-default">退出登录</a>
              </li>
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
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="images/user.jpg" class="img-circle" alt="用户头像">
        </div>
        <div class="pull-left info">
          <p>
          	<c:out value='${sessionScope.user.name }'/>
          </p>
          <i class="fa fa-circle text-success"></i> 在线
        </div>
      </div>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">功能列表</li>
        <!-- 功能一 -->
        <li id="planTable">
          <a href="#">
            <i class="fa fa-tasks"></i> <span>监督抽查计划</span>
          </a>
        </li>
        <!-- 功能二 -->
        <li id="productTable">
          <a href="#">
            <i class="fa fa-edit"></i> <span>产品信息管理</span>
          </a>
        </li>
        <!--功能三-->
        <li class="treeview" id="reportTable">
          <a href="#">
            <i class="fa fa-info"></i> <span>抽查结果管理</span>
          </a>
        </li>
        <!--功能四-->
        <li id="graphicsTable">
          <a href="#">
            <i class="fa fa-thermometer-half"></i> <span>质量指数测算及质量预警</span>
          </a>
        </li>
        <!--功能五-->
        <li id="standardTable">
          <a href="#">
            <i class="fa fa-certificate"></i> <span>检验标准查询</span>
          </a>
        </li>
        <!-- 功能六 -->
        <li class="treeview" id="deviceTable">
          <a href="#">
            <i class="fa fa-cubes"></i> <span>设备管理</span>
          </a>
        </li>
        <!-- 功能七 -->
        <li class="treeview" id="userTable">
          <a href="#">
            <i class="fa fa-cubes"></i> <span>用户管理</span>
          </a>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        监督抽查计划
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
            <div class="box">
              <div class="box-body">
               
              </div>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <!-- 定义模态框 -->
  <div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	          &times;
	        </button>
	        <h4 class="modal-title">
	          权限不足
	        </h4>
	      </div>
	      <div class="modal-body">
	        该功能只有管理员才能使用
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">确认
	        </button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal -->
  </div>

  <footer class="main-footer">
    <strong>Copyright &copy; 2018 <a href="#">中石油井控产品质量监督系统</a>.</strong> 保留所有权限
  </footer>
</div>
<script src="<c:url value='/js/jquery.min.js'/>"></script>
<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/js/jquery.dataTables.min.js'/>"></script>
<script src="<c:url value='/js/dataTables.bootstrap.min.js'/>"></script>
<script src="<c:url value='js/bootstrap-select.js'/>"></script>
<!-- 为任何区域添加滚动条 -->
<script src="<c:url value='/js/jquery.slimscroll.min.js'/>"></script>
<!-- 解决移动端点击延迟问题 -->
<script src="<c:url value='/js/fastclick.js'/>"></script>
<script src="<c:url value='/js/adminlte.min.js'/>"></script>
<!-- dataTables的按钮插件 -->
<script src="<c:url value='/js/dataTables.buttons.min.js'/>"></script>
<script src="<c:url value='/js/buttons.bootstrap.js'/>"></script>
<!-- dataTables的按钮插件中的 导出excel 的依赖 -->
<script src="<c:url value='/js/jszip.min.js'/>"></script>
<script src="<c:url value='/js/buttons.html5.min.js'/>"></script>
<!-- dataTables选中表格某一行的插件 -->
<script src="<c:url value='js/dataTables.select.min.js'/>"></script>
<!-- dataTables Editor插件 -->
<script src="<c:url value='/js/dataTables.editor.min.js'/>"></script>
<script src="<c:url value='/js/editor.bootstrap.min.js'/>"></script>  
<!-- dataTables插件colReorder -->
<script src="<c:url value='/js/dataTables.colReorder.min.js'/>"></script>
<!-- highcharts -->
<script src="<c:url value='/js/highcharts.js'/>"></script>

<script type="text/javascript">
    $(function(){
      //默认的首页是监督抽查计划
      $(".box-body").load("<c:url value='/pages/planForm.jsp'/>");

      //加载监督抽查计划页面
      $("#planTable").on("click",function(){
        $("section.content-header h1").text($("#planTable a span").text());
        $(".box-body").load("<c:url value='/pages/planForm.jsp'/>");
      });

      //加载产品信息管理页面
      $("#productTable").on("click",function(){
        $("section.content-header h1").text($("#productTable a span").text());
        $(".box-body").load("<c:url value='/pages/productForm.jsp'/>");
      });

      //加载抽查结果管理页面
      $("#reportTable").on("click",function(){
        $("section.content-header h1").text($("#reportTable a span").text());
        $(".box-body").load("<c:url value='/pages/reportForm.jsp'/>");
      }); 

      //加载质量指数测算及质量预警页面
      $("#graphicsTable").on("click",function(){
        $("section.content-header h1").text($("#graphicsTable a span").text());  
      	$(".box-body").load("<c:url value='/pages/graphicsForm.jsp'/>");
      });

      //加载检验标准页面
      $("#standardTable").on("click",function(){
        $("section.content-header h1").text($("#standardTable a span").text());
        $(".box-body").load("<c:url value='/pages/standardForm.jsp'/>");
      }); 

      //加载设备管理页面
      $("#deviceTable").on("click",function(){
        $("section.content-header h1").text($("#deviceTable a span").text());
        $(".box-body").load("<c:url value='/pages/deviceForm.jsp'/>");
      });
      
      //加载用户管理界面
      $("#userTable").on("click",function(){
        if('${sessionScope.user.privilege}' == '0'){
        	$("section.content-header h1").text($("#userTable a span").text());
        	$(".box-body").load("<c:url value='/pages/userForm.jsp'/>");
        }else{
            $("#userModal").modal('show');
        }
      });
      
    });//加载jquery
</script>
