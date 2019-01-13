<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>登录</title>

	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0,user-scalable=no">

	<link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrapValidator.min.css'/>">
	
	<script src="<c:url value='/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrapValidator.min.js'/>"></script>

	<style type="text/css">
		.login_pannel{
			width: 30%;
			margin: 10% auto;
			padding: 1% 0px 3% 3%;
			background-color: rgba(255,255,255,0.3);
		}
		@keyframes background_transfer{/*使用 规则@keyframes 创建动画*/
			0% {background: url(<c:url value='/images/landview1.jpg'/>) no-repeat;}
			25% {background: url(<c:url value='/images/landview2.jpg'/>) no-repeat;}
			50% {background: url(<c:url value='/images/landview3.jpg'/>) no-repeat;}
			75% {background: url(<c:url value='/images/landview4.jpg'/>) no-repeat;}
			100% {background: url(<c:url value='/images/landview1.jpg'/>) no-repeat;}
		}
		@-webkit-keyframes background_transfer{/*兼容chrome与safari*/
			0% {background: url(<c:url value='/images/landview1.jpg'/>) no-repeat;}
			25% {background: url(<c:url value='/images/landview2.jpg'/>) no-repeat;}
			50% {background: url(<c:url value='/images/landview3.jpg'/>) no-repeat;}
			75% {background: url(<c:url value='/images/landview4.jpg'/>) no-repeat;}
			100% {background: url(<c:url value='/images/landview1.jpg'/>) no-repeat;}
		}
		body{
			background: url(<c:url value='/images/landview1.jpg'/>) no-repeat;
			animation: background_transfer 35s infinite;
			-webkit-animation:background_transfer 35s infinite;
		}
		}
	</style>
</head>
<body>
	<div class="container login_pannel">
		<form role="form" 
			class="form-horizontal"
			method="post"
			action="<c:url value='/login.action'/>">
			<!--把登录专门列为一行-->
			<div class="row">
				<div class="form-group">
					<div class="col-md-7 col-md-offset-2 col-xs-9 col-xs-offset-1">
						<h3>登录</h3>
					</div>
				</div>
			</div>
			<!-- 用户名行 -->
			<div class="row">
				<div class="form-group">
					<div class="col-md-7 col-md-offset-2 col-xs-9 col-xs-offset-1">
						<div class="input-group">
							<!--在 用户名 输入框前添加 图标-->
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i> </span>
							<!-- 用户名 输入框 -->
							<input type="text" 
								class="form-control" 
								name="name" 
								id="name"
								value="${requestScope.user_name }"
								placeholder="用户名"
								autofocus="true">
						</div>
					</div>
				</div>
			</div>
			<!-- 密码行 -->
			<div class="row">
				<div class="form-group">
					<div class="col-md-7 col-md-offset-2 col-xs-9 col-xs-offset-1">
						<div class="input-group">
							<!--在 密码 输入框前添加 图标-->
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i> </span>
							<!-- 密码 输入框 -->
							<input type="password" 
								class="form-control" 
								id="password"
								name="password" 
								placeholder="密  码">
						</div>
					</div>
				</div>
			</div>
			<!--记住密码行-->
			<!-- 
				<div class="row">
					<div class="form-group">
						<div class="col-md-7 col-md-offset-2 col-xs-9 col-xs-offset-1">
							<div class="checkbox">
								<label>
									<input type="checkbox" 
										name="remember"> 记住密码
								</label>
							</div>
						</div>
					</div>
				</div>
			 -->
			<!--登录按钮-->
			<div class="row">
				<div class="form-group">
					<div class="col-md-7 col-md-offset-2 col-xs-9 col-xs-offset-1">
						<input type="submit" 
							class="btn btn-primary btn-md btn-block"
							value="登录">
					</div>
				</div>
			</div>
		</form>
		
	</div>
</body>
</html>

<!-- 使用Bootstrap-validator进行校验 -->
<script type="text/javascript">
	var validator;
	
	$(function(){
		validator = $('form').bootstrapValidator({
			message:'This value is not valid',
			/*以悬浮框的形式展示message中的信息*/
			container:'tooltip',
			feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
        	},
			fields: {
				name:{
					message:'用户名不正确',
					validators:{
						notEmpty:{
							message:'用户名不能为空'
						}
					}
				},
                password:{
                	validators:{
                		notEmpty:{
                			message:'密码不能为空'
                		}	
                	}
                }
            }//字段集合
		}).on("success.form.bv",function(e){//使用 success.form.bv事件，实现使用ajax提交表单
			//阻止form的默认提交
			e.preventDefault();
			//获取表单示例
			var $form = $(e.target);
			//获取bootstrap-validator实例
			var $bv = $form.data('bootstrapValidator');
			$.ajax({
				url:$form.attr("action"),
				type:'POST',
				data:{
					name:$("#name").val(),
					password:$("#password").val()
				},
				dataType:'json',//期待server返回json格式的数据
				success:function(data){
					if(typeof(data.nextPath) != "undefined"){
						window.location.href = data.nextPath;
					}else{
						if(typeof(data.mismatch) != "undefined"){
							//弹出提示框说明用户名和密码不匹配
							$("#password").popover({
								content:data.mismatch,
								placement:'left'
							}).popover('show');
							
							setTimeout("$('#password').popover('destroy')",2000);
						}else if(typeof(data.userError) != "undefined"){
							//弹出提示框说明该用户不存在
							$("#name").popover({
								content:data.userError,
								placement:'left'
							}).popover('show');
							
							setTimeout("$('#name').popover('destroy')",2000);
						}
						//重置表单中校验过的内容
						$bv.resetForm(true);
						//设置刚才输入到用户栏中的用户名
						$("#name").val(data.user_name);
						//更新用户名栏的校验状态并重新校验
						$bv.updateStatus('name','NOT_VALIDATED').validateField('name');
						//启用提交按钮
						$bv.disableSubmitButtons(false);
						//启用对密码的bootstrapValidator校验
						$bv.enableFieldValidators("password",true);
					}//用户登录失败
				}//Ajax的success函数
			});//使用Ajax对表达进行提交
			
		});//初始化bootstrap validator
		
		
	});//加载jquery
</script>