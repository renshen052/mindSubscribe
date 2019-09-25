<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${pageContext.request.contextPath }/mutualResource/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/assets/css/font-awesome.min.css" />
		<!--[if IE 7]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/assets/css/ace-skins.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/css/style.css"/>
		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/assets/css/ace-ie.min.css" />
		<![endif]-->
		<script src="${pageContext.request.contextPath }/mutualResource/assets/js/ace-extra.min.js"></script>
		<!--[if lt IE 9]>
		<script src="${pageContext.request.contextPath }/mutualResource/assets/js/html5shiv.js"></script>
		<script src="${pageContext.request.contextPath }/mutualResource/assets/js/respond.min.js"></script>
		<![endif]-->
		<script src="${pageContext.request.contextPath }/mutualResource/js/jquery-1.9.1.min.js"></script>        
        <script src="${pageContext.request.contextPath }/mutualResource/assets/layer/layer.js" type="text/javascript"></script>
<title>登陆</title>
</head>

<body class="login-layout">
<div class="logintop">    
    <span>欢迎后台管理界面平台</span>    
    <ul>
    <li><a href="#">返回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    <div class="loginbody">
<div class="login-container">
	<div class="center">
	<h1>
									<i class="icon-leaf green"></i>
									<span class="orange">心理咨询预约</span>
									<span class="white">后台管理系统</span>
								</h1>
								<h4 class="white">Background Management System</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box widget-box no-border visible">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
												管理员登陆
											</h4>

											<div class="login_icon"><img src="${pageContext.request.contextPath }/mutualResource/images/login.png" /></div>

											<form action="${pageContext.request.contextPath }/admin/login?m=saveLogin" method="post" >
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="登录名"  name="adminName">
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" name="adminPwd">
															<i class="icon-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" class="ace">
															<span class="lbl">保存密码</span>
														</label>

														<button type="submit" class="width-35 pull-right btn btn-sm btn-primary" id="login_btn">
															<i class="icon-key"></i>
															登陆
														</button>
													</div>

													<div class="space-4"><font style="color:red">${msg }</font></div>
												</fieldset>
											</form>

											<div class="social-or-login center">
												<span class="bigger-110">通知</span>
											</div>

											<div class="social-login center">
											本网站系统不再对IE8以下浏览器支持，请见谅。
											</div>
										</div><!-- /widget-main -->

										<div class="toolbar clearfix">
											

											
										</div>
									</div><!-- /widget-body -->
								</div><!-- /login-box -->
							</div><!-- /position-relative -->
						</div>
                        </div>
<!--                         <div class="loginbm">版权所有  2016  <a href="">南京思美软件系统有限公司</a> </div><strong></strong>
 --></body>
</html>
<script>


$(document).ready(function(){
	
	$("#login_btn").on('click' ,function(){
		
		var adminName = $("input[name=adminName]");
		  
		var adminPwd = $("input[name=adminPwd]");
		
		if(adminName.val() == ""){
			
			layer.alert(adminName.attr("placeholder")+"不能为空！\r\n",{
                title: '提示框',				
				icon:0,								
          	}); 
			
			return false;
		}else if(adminPwd.val() == ""){
			
			layer.alert(adminPwd.attr("placeholder")+"不能为空！\r\n",{
                title: '提示框',				
				icon:0,								
          	}); 
			
			return false;
			
		}
		$("#login_btn").submit();
		
	});
	
	
});

</script>