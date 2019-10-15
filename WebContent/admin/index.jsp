<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>后台管理系统  </title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
        <!--[if !IE]> -->
		<script src="${pageContext.request.contextPath }/mutualResource/js/jquery-1.9.1.min.js"></script>        
		<!-- <![endif]-->
		<!--[if IE]>
         <script type="text/javascript">window.jQuery || document.write("<script src='${pageContext.request.contextPath }/mutualResource/assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");</script>
        <![endif]-->
		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${pageContext.request.contextPath }/mutualResource/assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
		</script>
		<script src="${pageContext.request.contextPath }/mutualResource/assets/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath }/mutualResource/assets/js/typeahead-bs2.min.js"></script>
		<!--[if lte IE 8]>
		  <script src="${pageContext.request.contextPath }/mutualResource/assets/js/excanvas.min.js"></script>
		<![endif]-->
		<script src="${pageContext.request.contextPath }/mutualResource/assets/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath }/mutualResource/assets/js/ace.min.js"></script>
        <script src="${pageContext.request.contextPath }/mutualResource/assets/layer/layer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/mutualResource/assets/laydate/laydate.js" type="text/javascript"></script>
        
        
<script type="text/javascript">





</script>
<script type="text/javascript">	
 $(function(){ 
 var cid = $('#nav_list> li>.submenu');
	  cid.each(function(i){ 
       $(this).attr('id',"Sort_link_"+i);
   
    })  
 })
 jQuery(document).ready(function(){ 	
    $.each($(".submenu"),function(){
	var $aobjs=$(this).children("li");
	var rowCount=$aobjs.size();
	var divHeigth=$(this).height();
    $aobjs.height(divHeigth/rowCount);	  	
  }); 
    //初始化宽度、高度    
    $("#main-container").height($(window).height()-76); 
	$("#iframe").height($(window).height()-140); 
	 
	$(".sidebar").height($(window).height()-99); 
    var thisHeight = $("#nav_list").height($(window).outerHeight()-173); 
	$(".submenu").height();
	$("#nav_list").children(".submenu").css("height",thisHeight);
	
    //当文档窗口发生改变时 触发  
    $(window).resize(function(){
	$("#main-container").height($(window).height()-76); 
	$("#iframe").height($(window).height()-140);
	$(".sidebar").height($(window).height()-99); 
	
	var thisHeight = $("#nav_list").height($(window).outerHeight()-173); 
	$(".submenu").height();
	$("#nav_list").children(".submenu").css("height",thisHeight);
  });
    $(".iframeurl").click(function(){
                var cid = $(this).attr("name");
				var cname = $(this).attr("title");
                $("#iframe").attr("src",cid).ready();
				$("#Bcrumbs").attr("href",cid).ready();
				$(".Current_page a").attr('href',cid).ready();	
                $(".Current_page").attr('name',cid);
				$(".Current_page").html(cname).css({"color":"#333333","cursor":"default"}).ready();	
				$("#parentIframe").html('<span class="parentIframe iframeurl"> </span>').css("display","none").ready();	
				$("#parentIfour").html(''). css("display","none").ready();		
      });
    
		
});
 

/*********************点击事件*********************/
$( document).ready(function(){
  $('#nav_list').find('li.home').click(function(){
	$('#nav_list').find('li.home').removeClass('active');
	$(this).addClass('active');
  });	
												

//时间设置
  function currentTime(){ 
    var d=new Date(),str=''; 
    str+=d.getFullYear()+'年'; 
    str+=d.getMonth() + 1+'月'; 
    str+=d.getDate()+'日'; 
    str+=d.getHours()+'时'; 
    str+=d.getMinutes()+'分'; 
    str+= d.getSeconds()+'秒'; 
    return str; 
} 
setInterval(function(){$('#time').html(currentTime)},1000); 

//设置消息
setInterval(function(){
	
	 $.ajax({  
         url: '${pageContext.request.contextPath }/message/MessageServlet?m=newMessage&user=admin' ,  
         type: 'GET',  
         dataType:'json', 
         success: function (data) { 
       	  
       	  if(data.isSuccess){
       		  
       		  if(data.dataList[0]==0){
       			  
       			$('#newMessage').attr("style","color:green");
       			
       		  }else{
       			  
       			$('#newMessage').attr("style","color:red");
       			
       		  }
       		$('#newMessage').html("<i class=\"icon-bell-alt\"></i>[" + data.dataList[0] + "]");
       		
       		  
       	  }else{
       		 console.log(data.msg);
       	  }
         } 
    }); 
	
	},3000);



  $('#Exit_system').on('click', function(){
      layer.confirm('是否确定退出系统？', {
     btn: ['是','否'] ,//按钮
	 icon:2,
    }, 
	function(){
	  location.href="${pageContext.request.contextPath}/admin/login?m=logOutAdmin";
        
    });
});
})
</script>	
	</head>
	<body>
		<div class="navbar navbar-default" id="navbar">
        <script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>
			<c:if test="${not empty LOGIN_ADMIN}">
			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					
						<small>					
						<img src="${pageContext.request.contextPath }/mutualResource/images/logo.png">
						</small>
					
				</div><!-- /.navbar-header -->
			   <div class="navbar-header pull-right" role="navigation">
               <ul class="nav ace-nav">	
                <li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<span  class="time"><em id="time"></em></span><span class="user-info"><small>欢迎光临,</small>${LOGIN_ADMIN.name }	</span>
							</a>
								<li><a href="javascript:void(0)" id="Exit_system"><i class="icon-off"></i>退出</a></li>
				</ul>
                </div>
			</div>
			
			</c:if>
			<c:if test="${empty LOGIN_ADMIN}">
			<div class="navbar-header pull-right" role="navigation">
               <ul class="nav ace-nav">	
                <li class="light-blue">
					<a  href="${pageContext.request.contextPath }/admin/AdminLoginServlet">
						请登录
					</a>	
				</li>	
			   </ul>

            </div>
			
			</c:if>
			
		</div>
		<div class="main-container" id="main-container">
        <script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				<div class="sidebar" id="sidebar">
<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>
					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
                     <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						管理员  
						</div>
						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>
							<span class="btn btn-info"></span>
							<span class="btn btn-warning"></span>
							<span class="btn btn-danger"></span>
						</div>
					</div>
					
					<!-- #sidebar-shortcuts -->
				<ul class="nav nav-list" id="nav_list">
					<li class="home"><a href="javascript:void(0)" name="${pageContext.request.contextPath }/admin/base?m=adminIndex"
						class="iframeurl" title=""><i class="icon-dashboard"></i><span
							class="menu-text"> 系统首页 </span></a></li>
							
					<li><a href="javascript:void(0)" name="${pageContext.request.contextPath }/doctor/DoctorServlet?m=listDoctor" title="咨询师管理" 
						class="iframeurl"><i class="icon-user"></i><span
							class="menu-text"> 咨询师管理 </span></a></li>
							
					<li><a href="javascript:void(0)" name="${pageContext.request.contextPath }/client/ClientServlet?m=listClient"  title="来访者管理" 
						class="iframeurl"><i class="icon-user"></i><span
							class="menu-text"> 来访者管理 </span></a></li>
							
					<li><a href="javascript:void(0)" name="${pageContext.request.contextPath }/question/QuestionServlet?m=listQuestion" title="问卷管理" 
						class="iframeurl"><i class="icon-list"></i><span
							class="menu-text"> 问卷管理 </span></a></li>

					<li>
					<a href="#" class="dropdown-toggle"><i
							class="icon-edit"></i><span class="menu-text"> 消息 </span>
							<span id="newMessage" style="color:red"></span>
							<b
							class="arrow icon-angle-down"></b></a>
						<ul class="submenu">
							<li class="home"><a href="javascript:void(0)"
								name="${pageContext.request.contextPath }/message/MessageServlet?m=listReceivMessage&reqeustUser=admin" title="消息查看" class="iframeurl"><i
									class="icon-double-angle-right"></i>收信箱</a></li>
							<li class="home"><a href="javascript:void(0)"
								name="${pageContext.request.contextPath }/message/MessageServlet?m=listSendMessage&reqeustUser=admin" title="发送消息" class="iframeurl"><i
									class="icon-double-angle-right"></i>发信箱</a></li>
						</ul>
					</li>
					
						
						<li><a href="javascript:void(0)" name="${pageContext.request.contextPath }/announcment/AnnouncmentServlet?m=listAnnouncment"  title="公告管理" 
						class="iframeurl"><i class="icon-edit"></i><span
							class="menu-text"> 公告管理 </span></a></li>
						
						
						<li><a href="javascript:void(0)" name="${pageContext.request.contextPath }/board/MessageBoardServlet?m=listMessageBoard"  title="留言管理" 
						class="iframeurl"><i class="icon-edit"></i><span
							class="menu-text"> 留言管理 </span></a></li>
						
						

					<li><a href="javascript:void(0)"
						name="${pageContext.request.contextPath }/admin/base?m=adminInfo" title="个人信息"
						class="iframeurl"><i class="icon-group"></i><span
							class="menu-text"> 个人信息 </span></a></li>
				</ul>
				
				
				<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>
                    <script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>
				<div class="main-content">
                <script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
					<div class="breadcrumbs" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="${pageContext.request.contextPath }/admin/base?m=adminIndex">首页</a>
							</li>
							<li class="active"><span class="Current_page iframeurl"></span></li>
                            <li class="active" id="parentIframe"><span class="parentIframe iframeurl"></span></li>
							<li class="active" id="parentIfour"><span class="parentIfour iframeurl"></span></li>
						</ul>
					</div>
                    
                 <iframe id="iframe" style="border:0; width:100%; background-color:#FFF;"name="iframe" frameborder="0" src="${pageContext.request.contextPath }/admin/base?m=adminIndex">  </iframe>
				 

<!-- /.page-content -->
				</div><!-- /.main-content -->	
                
                  <div class="ace-settings-container" id="ace-settings-container">
                      <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                          <i class="icon-cog bigger-150"></i>
                      </div>
  
                      <div class="ace-settings-box" id="ace-settings-box">
                          <div>
                              <div class="pull-left">
                                  <select id="skin-colorpicker" class="hide">
                                      <option data-skin="default" value="#438EB9">#438EB9</option>
                                      <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                                      <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                                      <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                                  </select>
                              </div>
                              <span>&nbsp; 选择皮肤</span>
                          </div>
  
                          <div>
                              <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
                              <label class="lbl" for="ace-settings-sidebar"> 固定滑动条</label>
                          </div>
  
                          <div>
                              <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
                              <label class="lbl" for="ace-settings-rtl">切换到左边</label>
                          </div>
  
                          <div>
                              <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
                              <label class="lbl" for="ace-settings-add-container">
                                  切换窄屏
                                  <b></b>
                              </label>
                          </div>
                      </div>
                  </div><!-- /#ace-settings-container -->		
	</div><!-- /.main-container-inner -->
			
		</div>
         <!--底部样式-->
       
         <div class="footer_style" id="footerstyle">  
          <p class="l_f">版权所有：XXXX</p>
         </div>
		
</body>
</html>

