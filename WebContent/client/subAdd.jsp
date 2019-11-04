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

<title>预约</title>
</head>
<body>
<div class="clearfix" id="add_picture">
 <div id="scrollsidebar" class="left_Treeview" style="text-align: center;">
    <div class="show_btn" id="rightArrow"><span></span></div>
    <div class="widget-box side_content" >
     <div class="side_list">
      <div class="widget-header header-color-green2">
          <h4 class="lighter smaller">咨询师</h4>
      </div>
       <div class="product_info clearfix">
       
       	 <c:if test="${ empty doctor.img }">
          <img src="${pageContext.request.contextPath }/mutualResource/images/image.png"  width="210px" height="200px"/>
          </c:if>
          <c:if test="${not empty doctor.img }">

          <img src="/mind_upload/${doctor.img}"  width="210px" height="200px"/>
          </c:if>
       
       
     <%-- <a href="#" class="img_link"><img src="/mind_upload/${doctor.img}"  width="210" height="200"/></a> --%>

      <span>
     
      <b>${doctor.name }</b>
      <p>性别：${doctor.sex eq 1 ? "男":"女" }</p>
      <p>年龄：${doctor.age }</p>
      <p>等级:${doctor.level }</p>
      <p>擅长方向：${doctor.skill }</p>
      <p> 一般咨询地点： ${doctor.place } </p>
      <p>电话：${doctor.phone }</p>
      <p>电子邮件：${doctor.email }</p>
      <a style="text-decoration:none" onClick="sendMessage(this,'${doctor.doctorId}','${doctor.name}','doctor')"
											href="javascript:;" title="预约"
											class="btn btn-xs btn-success">发送消息</a>
      </span>
    </div>
  </div>
  </div>  
  </div>
   <div class="page_right_style">
   <div class="type_title">预约</div>
	<form action="" method="post" class="form form-horizontal" id="form-article-add">
		<div class="clearfix cl">
         <label class="form-label col-2">咨询时间：</label>
		 <div class="formControls col-10"><input type="text" class="input-text" value="" placeholder="您期望的咨询时间，如：2019年10月20日-2019年10月22日的下午...等等" id="expectTime" name="expectTime"></div>
		<div class="formControls col-10"></div>
		</div>
		
		<div class=" clearfix cl">
         <label class="form-label col-2">咨询地点：</label>
	     <div class="formControls col-10"><input type="text" class="input-text" value="" placeholder="您期望的咨询地点,用，隔开" id="expectPlace" name="expectPlace"></div>
		 <div class="formControls col-10"></div>
		</div>
		<div class=" clearfix cl">
		<div class="formControls col-10"><span style="color:green">为了了解您的心理状况，我们将对您进行简单的问卷调查，请如实填写</span></div>
		</div>
		
		<div class="clearfix cl">
			<label class="form-label col-2">主观描述</label>
			<div class="formControls col-10">
				<textarea id="context" name="clientDescription" cols="" rows="" class="textarea"  placeholder="说点什么...对您自身目前状况的描述，如：我现在感觉如何？遇到的问题是什么？" datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="textarealength(this,300)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/300</p>
				
			</div>
			<div  class="form-label col-2"></div>
		</div>
		<div class="clearfix cl"><label class="form-label col-2">请选择：</label></div>

		<c:set var="questionIds" value=""></c:set>

		<c:forEach items="${questionList }" var="question" varStatus="statu">
		
		<c:set var="questionIds" value="${questionIds }${question.questionId },"></c:set>
		<input type="hidden" name="answerYesScore${question.questionId }" value="${question.answerYesScore }"/>
		<input type="hidden" name="answerNoScore${question.questionId }" value="${question.answerNoScore }"/>
		
		<div class="clearfix cl" >
		<label class="form-label col-2">&nbsp;</label>
			<div class="formControls col-10" id="question">
				
						 <span class="cont_style"> 
						
						<span class="lbl">${statu.count}.${question.context }</span> 
						<input type="hidden" name="context${question.questionId }" value="${statu.count}.${question.context }"/>
						&nbsp;&nbsp;
						
						<input name="${question.questionId }" type="radio" class="ace" value="${question.answerYesScore }">
						
						
						<span class="lbl">是</span>
						
						&nbsp;&nbsp;&nbsp; 
						
						<input name="${question.questionId }" type="radio" class="ace" value="${question.answerNoScore }">
						
						<span class="lbl">否</span>
						
						</span>
						
					<input type="hidden" name="doctorId" value="${doctor.doctorId}">
								
			</div>
			<div  class="form-label col-2"></div>
			
		</div>
		
		</c:forEach>
		
		<input type="hidden" name="doctorId" value="${doctor.doctorId}">
		<input type="hidden" name="questionIds" value="${questionIds}">

				<div class="clearfix cl">
			<div class="Button_operation">
				<button onClick="article_save_submit();" class="btn btn-primary radius" type="button"><i class="icon-save "></i>保存并提交</button>
				<button  class="btn btn-default radius" type="button" onclick="javascript:window.location='${pageContext.request.contextPath }/client/ClientSubServlet?m=subDoctorList'">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
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
  
    $("#form-article-add :input").each(function(){
		 
    	//绑定验证事件
    	//当改变的时候，触发检查函数
		$(this).change(function(){ 
			
			isAbleCheckOne($(this));

		});	
		 
		 
	 });
  
  
  
  
});
		
/**
 * 提交预约
 */
function article_save_submit(){
	
	var msg = "";
	
	if(isAbleOk()){
		//如果数据合法
		
		//ajax上传
	     $.ajax({  
	          url: '${pageContext.request.contextPath }/client/ClientSubServlet?m=subStep2' ,  
	          type: 'POST',  
	          dataType:'json', 
	          data: $("#form-article-add").serialize(),  
	          cache: false,  
	          success: function (data) { 
	        	  
	        	  if(data.isSuccess){
	        		  
	        		  msg = "提交成功！";
	        		  
	        		  layer.alert(msg,
				    		  
	   	 	               {title: '提示框',				
	   	 					icon:1	},function(){
	   	 					window.location="${pageContext.request.contextPath }/client/ClientSubServlet?m=subClientList";
	   	 					}); 
	        		  
	        	  }else{
	        		  msg = data.msg;
	        		  layer.alert(msg,
	   	 	               {title: '提示框',				
	   	 					icon:1,	}
	   	 					);
	        	  }
	          },  
	          error: function (returndata) {  
	        	  msg = "失败请刷新后重试"; 
	        	  layer.alert(msg,
		 	               {title: '提示框',				
		 					icon:1,	}
		 					);
	        	  
	          }  
	     }); 
		
	}else{
		
		layer.alert("请填写数据！",{
	               title: '提示框',				
				icon:1,		
				  });
		
	}
	
}

/**
 * 验证表单内容是否正确
 */
function isAbleOk(){
	
	var isOk = true;
	
	 $("#form-article-add :input").each(function(){
		 
		 //如果有一项不正确
		 if( !isAbleCheckOne($(this))){
			 isOk = false;
		 }
		 
	 });
	 
	 return isOk;
	
}

function isAbleCheckOne(thisElement){
	
	var isOk = true;
	
	//验证期望地址和期望时间
	 if($(thisElement).is("#expectTime")|| $(thisElement).is("#expectPlace")){
		 
		 var text = $.trim($(thisElement).val());
		 
		 if(text == "" || text.length >100){
			 
			 $(thisElement).parent().next().html("<span style='color:red'>1-100个字符！</span>");
			 
			 isOk = false;
			 
		 }else{
			 $(thisElement).parent().next().html("");
		 }
	 }
	
	 
	//验证主观描述
	 if($(thisElement).is("#context")){
		 
		 var text = $.trim($(thisElement).val());
		 
		 if(text == "" || text.length >300){
			 
			 $(thisElement).parent().next().html("<span style='color:red'>1-300个字符！</span>");
			 
			 isOk = false;
			 
		 }else{
			 $(thisElement).parent().next().html("");
		 }
	 }
	
	//验证问卷题目
	if($(thisElement).is("input[type='radio']")){
		
		var radio = $(thisElement).next().next();
		
		//如果没有选中的
		if(! $(thisElement).parent().find("input[type='radio']:checked").val()){
			
			 $(thisElement).parent().parent().next().html("<span style='color:red'>请选择！</span>");
			 
			 isOk = false;
			 
		}else{
			$(thisElement).parent().parent().next().html("");
		}
		
	}
	
	return isOk;
}

</script>

<%@include file="/mutualResource/form/SendMessageForm.jsp"%>
</body>
</html>