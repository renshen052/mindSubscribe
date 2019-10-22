<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="refresh" content="10;" />
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
  <i class="icon-ok green"></i>欢迎使用<strong class="green">心理咨询预约系统</strong>	
 </div>
             <!-- <div class="t_Record">
               <div id="main" style="height:300px; overflow:hidden; width:100%; overflow:auto" ></div>     
              </div> --> 
          <div class="news_style">
          <div class="title_name">最新消息</div>
          
          <ul class="list">
          <c:if test="${empty messageList }">无</c:if>
          	<c:forEach items="${messageList }" var="message">
          	
          		<li><i class="icon-bell red"></i><a title="查看消息" href="${pageContext.request.contextPath }/message/MessageServlet?m=listReceivMessage&reqeustUser=admin">
          	 	<fmt:formatDate value="${message.sendTime}" pattern="yyyy-MM-dd HH:mm:ss" />
          		&nbsp;
          		来自${message.senderName}的消息 &nbsp;${message.context }
          		</a></li>
          	
          	</c:forEach>
          </ul>
         </div>  
        <div class="news_style">
          <div class="title_name">最新公告</div>
          <ul class="list">
          
          	<c:forEach items="${announcmentList }" var="announcment">
          	
          		<li><i class="icon-bell red"></i>
          		<a title="公告管理" href="${pageContext.request.contextPath }/admin/AnnouncmentServlet?m=listAnnouncment">
          			<b>${announcment.title}:</b>&nbsp;
          			${announcment.context}&nbsp;---${announcment.admin.name}&nbsp;
          	 		<fmt:formatDate value="${message.sendTime}" pattern="yyyy-MM-dd HH:mm:ss" />
          		</a>
          		</li>
          	
          	</c:forEach>
          </ul>
         </div>  
         
         <div class="news_style">
          <div class="title_name">最新留言</div>
          <ul class="list">
          
          	<c:forEach items="${newMessageBoardList }" var="messageBoard">
          	
          		<li><i class="icon-bell red"></i><a title="留言管理" href="${pageContext.request.contextPath }/board/MessageBoardServlet?m=listMessageBoard">
          	 	
          	 	${messageBoard.context }&nbsp;&nbsp;
          	 	${messageBoard.client.name }
          		
          		<fmt:formatDate value="${messageBoard.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
          		</a></li>
          	
          	</c:forEach>
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
