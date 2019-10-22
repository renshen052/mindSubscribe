<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>注册</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<meta name="keywords" content="" />
<link href="${pageContext.request.contextPath }/client/reg/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
<link href="${pageContext.request.contextPath }/client/reg/css/style.css" rel="stylesheet" type="text/css" media="all"/>
<link href="http://fonts.googleapis.com/css?family=Raleway:400,500,600,700,800,900" rel="stylesheet">
<script src="${pageContext.request.contextPath }/mutualResource/js/jquery-1.9.1.min.js"></script>
</head>

<body>
<c:if test="${!empty msg }">
			<script>
			$(document).ready(function(){
				alert("${msg }");
			})
			</script>
</c:if>
<div class="signupform">
<h1>来访者注册</h1>
	<div class="container">
		
		<div class="agile_info">
			<div class="w3l_form">
				<div class="left_grid_info">
					<h3>欢迎注册 !</h3>
					<h4>心理咨询预约平台</h4>
					<p> 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						如果您需要心理咨询师帮助自己，在这里我们可以为您提供咨询预约的途径。
					</p>
					<p> 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						在这里，可以预约您心仪的咨询师寻求帮助。
					</p>
					<p> 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						请您在右侧留下一些必要信息方便我们安排咨询后，及时的联系您。
					</p>
					<p> 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						注册成功后，请尽快完善您的个人信息。
					</p>
				</div>
			</div>
			<div class="w3_info">
				<h2>必要信息</h2>
				<p>包括您的姓名，电话(作为登录账户)，电子邮件和密码</p>
						<form action="${pageContext.request.contextPath }/client/reg?m=addClient" method="post" id="regForm">
						<div class="input-group">
							<input name="name" type="text" placeholder="姓名" required=""> 
							
						</div>
						
						<span id="phoneSpan" style="color:red;font-size: 12px;"></span>
						<div class="input-group">
							<input name="phone" id="phone" type="text" placeholder="电话" required=""> 
						</div>
						
						<div class="input-group">
							<input name="email" type="email" placeholder="电子邮件" required=""> 
						</div>
						
						<span id="pwdSpan" style="color:red;font-size: 12px;"></span>
						<div class="input-group">
							<span><i class="fa fa-lock" aria-hidden="true"></i></span>
							<input id="pwd" name="pwd" type="Password" placeholder="密码" required="">
						</div>
						
						<span id="confirmPwdSpan" style="color:red;font-size: 12px;"></span>
						<div class="input-group">
							<span><i class="fa fa-lock" aria-hidden="true"></i></span>
							<input id="confirmPwd"  name="confirmPwd" type="Password" placeholder="确认密码" required="">
						</div>
							<button  class="btn btn-danger btn-block" type="sbumit">注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册</button >                
					</form>
			</div>
			<div class="clear"></div>
			</div>
			
		</div>
		<div class="footer">

 </div>
	</div>
	<script>

	$(document).ready(function(){
		
		$("form").submit( function () {
			
			if( checkPhone() && checkPwd() ){
				
				//$("#regForm").submit();
				alert("注册成功！")
				return true;
			}else{
				return false;
			}
			
			} );
		
	});
	
	function checkPwd(){
		
		var pwd = $.trim($("#pwd").val());
		
		var confirmPwd = $.trim($("#confirmPwd").val());
		
		if(pwd!="" && confirmPwd!="" &&  pwd != confirmPwd){
			
			
			$("#confirmPwdSpan").html("两次密码输入不一致！");
			
			return false;
			
		}
		else {
			return true;
		}
		
	}
	
	
	
	function checkPhone(){ 
		
		var result=false;
		
	    var phone = document.getElementById('phone').value;
	    if(!(/^1[3456789]\d{9}$/.test(phone))){ 
	    	document.getElementById('phone').value="";
	    	 $("#phoneSpan").html("手机号码有误，请重填")
	        return false; 
	    }else{
	    	
	    	//ajax上传
		    result=  $.ajax({  
		          url: '${pageContext.request.contextPath }/client/reg?m=testPhone&phone='+phone,
		          type: 'GET',  
		          dataType:'json', 
		          cache: false,  
		          success: function (data) { 
		        	  
		        	  if(data.isSuccess){
		        		  //可用
		        		  $("#phoneSpan").html("")
		        		  return true;
		        	  }else{
		        		  //不可用
		        		 $("#phoneSpan").html("该电话已经被注册过!");
		        	  }
		          }  
		     });
	    	
	    }
	    
	    return result;
	}
	
	
	</script>
	
	</body>
</html>