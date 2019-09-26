<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${pageContext.request.contextPath }/mutualResource/assets/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/css/style.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/assets/css/font-awesome.min.css" />
        <link href="${pageContext.request.contextPath }/mutualResource/assets/css/codemirror.css" rel="stylesheet">
		<!--[if IE 7]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
        <!--[if lte IE 8]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/assets/css/ace-ie.min.css" />
		<![endif]-->
		<script src="${pageContext.request.contextPath }/mutualResource/assets/js/ace-extra.min.js"></script>
		<!--[if lt IE 9]>
		<script src="${pageContext.request.contextPath }/mutualResource/assets/js/html5shiv.js"></script>
		<script src="${pageContext.request.contextPath }/mutualResource/assets/js/respond.min.js"></script>
		<![endif]-->
        		<!--[if !IE]> -->
		<script src="${pageContext.request.contextPath }/mutualResource/assets/js/jquery.min.js"></script>        
		<!-- <![endif]-->
           	<script src="${pageContext.request.contextPath }/mutualResource/assets/dist/echarts.js"></script>
        <script src="${pageContext.request.contextPath }/mutualResource/assets/js/bootstrap.min.js"></script>
        
      
       <title>无标题文档</title>
       </head>
		
<body>
<div class="page-content clearfix">
 <div class="alert alert-block alert-success">
  <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>
  <i class="icon-ok green"></i>欢迎使用<strong class="green">后台管理系统</strong>	
 </div>
 <div class="state-overview clearfix">
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                      <a href="#" title="商城会员">
                          <div class="symbol terques">
                             <i class="icon-user"></i>
                          </div>
                          <div class="value">
                              <h1>34522</h1>
                              <p>商城用户</p>
                          </div>
                          </a>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol red">
                              <i class="icon-tags"></i>
                          </div>
                          <div class="value">
                              <h1>140</h1>
                              <p>分销记录</p>
                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol yellow">
                              <i class="icon-shopping-cart"></i>
                          </div>
                          <div class="value">
                              <h1>345</h1>
                              <p>商城订单</p>
                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol blue">
                              <i class="icon-bar-chart"></i>
                          </div>
                          <div class="value">
                              <h1>￥34,500</h1>
                              <p>交易记录</p>
                          </div>
                      </section>
                  </div>
              </div>
             <!--实时交易记录-->
             <div class="clearfix">
             <div class="t_Record">
               <div id="main" style="height:300px; overflow:hidden; width:100%; overflow:auto" ></div>     
              </div> 
         <div class="news_style">
          <div class="title_name">最新消息</div>
          <ul class="list">
           <li><i class="icon-bell red"></i><a href="#">后台系统找那个是开通了。</a></li>
           <li><i class="icon-bell red"></i><a href="#">6月共处理订单3451比，作废为...</a></li>
           <li><i class="icon-bell red"></i><a href="#">后台系统找那个是开通了。</a></li>
           <li><i class="icon-bell red"></i><a href="#">后台系统找那个是开通了。</a></li>
           <li><i class="icon-bell red"></i><a href="#">后台系统找那个是开通了。</a></li>
          </ul>
         </div> 
         </div>
 
<script type="text/javascript">
     $(document).ready(function(){
		 
		  $(".t_Record").width($(window).width()-320);
		  //当文档窗口发生改变时 触发  
    $(window).resize(function(){
		 $(".t_Record").width($(window).width()-320);
		});
 });
     </div>
</body>
</html>
