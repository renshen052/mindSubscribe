<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="js/html5.js"></script>
<script type="text/javascript" src="js/respond.min.js"></script>
<script type="text/javascript" src="js/PIE_IE678.js"></script>
<![endif]-->
<link href="${pageContext.request.contextPath }/mutualResource/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/css/style.css"/>       
<link href="${pageContext.request.contextPath }/mutualResource/assets/css/codemirror.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/assets/css/ace.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/assets/css/font-awesome.min.css" />
<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
<link href="${pageContext.request.contextPath }/mutualResource/Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />

<title>查看申请详情</title>
</head>
<body>
<div class="clearfix" id="add_picture">
 <div id="scrollsidebar" class="left_Treeview" style="text-align: center;">
    <div class="show_btn" id="rightArrow"><span></span></div>
    <div class="widget-box side_content" >
     <div class="side_list">
      <div class="widget-header header-color-green2">
          <h4 class="lighter smaller">申请者</h4>
      </div>
       <div class="product_info clearfix">
      <span>
     
      <b>${client.name }</b>
      <p>性别：${client.sex eq 1 ? "男":"女" }</p>
      <p>年龄：${client.age }</p>
      <p>电话：${client.phone }</p>
      <p>电子邮件：${client.email }</p>
      <a style="text-decoration:none" onClick="sendMessage(this,'${client.clientId}','${client.name}','doctor')"
											href="javascript:;" title="发送消息"
											class="btn btn-xs btn-success">发送消息</a>
      </span>
    </div>
  </div>
  </div>  
  </div>
   <div class="page_right_style"  >
   		<div class="type_title" >申请详情</div>
		<div class="clearfix cl">
         <div class="formControls col-10"><b>期望时间：</b>${clientArchive.expectTime}</div>
		</div>
		
		<div class=" clearfix cl">
         <div class="formControls col-10"><b>期望地点：</b>${clientArchive.expectPlace}</div>
		</div>
		<div class=" clearfix cl">
		<div class="formControls col-10"><span style="color:green">下面是申请者所做的问卷</span></div>
		</div>
		
		<div class="clearfix cl"></div>
		
		<div class="clearfix cl">
			<label class="formControls col-10"><b>主观描述:</b></label>
			<div style="margin: 40px;">
			<p>${clientArchive.clientDescription}
			</div>
			
		</div>
		<div class="clearfix cl"><label class="formControls col-10">&nbsp;</div>
		
		<div class="clearfix cl"><label class="form-label col-2"><b>题目：</b></label></div>

		<div class="clearfix cl" id="question">
			<div class="Button_operation">
				<button  class="btn btn-default radius" type="button" onclick="javascript:window.location='${pageContext.request.contextPath }/doctor/DoctorSubServlet?m=subClientList'">&nbsp;&nbsp;查看完毕&nbsp;&nbsp;</button>
			</div>
		</div>
    </div>
    
    
</div>
</div>
<script src="${pageContext.request.contextPath }/mutualResource/js/jquery-1.9.1.min.js"></script>   
<script src="${pageContext.request.contextPath }/mutualResource/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/mutualResource/assets/js/typeahead-bs2.min.js"></script>
<script src="${pageContext.request.contextPath }/mutualResource/assets/layer/layer.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath }/mutualResource/assets/laydate/laydate.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/mutualResource/Widget/icheck/jquery.icheck.min.js"></script> 
<script src="${pageContext.request.contextPath }/mutualResource/js/lrtk.js" type="text/javascript" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/mutualResource/js/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/mutualResource/js/H-ui.admin.js"></script> 
<script>
$( document).ready(function(){
//初始化宽度、高度
  
   $(".widget-box").height($(window).height()); 
   $(".page_right_style").height($(window).height()); 
   $(".page_right_style").width($(window).width()-220); 
  //当文档窗口发生改变时 触发  
    $(window).resize(function(){

	 $(".widget-box").height($(window).height()); 
	 $(".page_right_style").height($(window).height()); 
	 $(".page_right_style").width($(window).width()-220); 
	});	
  
  
  
  /**
  *题目
  */
    var questionJson = jQuery.parseJSON('${clientArchive.questionContext}');
    
    $.each(questionJson,function(n1, value){
    	
    	
		var question = "<div class=\"clearfix cl\" >";
    	
    	question += "<label class=\"form-label col-2\">&nbsp;</label>";
    	
    	question += "<div class=\"formControls col-30\">";
    	
    	question += "<span class=\"lbl\">" + value.context + "</span> ";

    	if(value.clientSelected == value.answer_yes_score){
    		question += "&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"lbl\" style='color:green'>是</span>";
    	}else {
    		question += "&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"lbl\" style='color:green'>否</span>";
    	}
    	
    	question += "</div></div>";
      	
      	$("#question").before(question);
    	
    });
  
  
});
		






</script>

<%@include file="/mutualResource/form/SendMessageForm.jsp"%>
</body>
</html>