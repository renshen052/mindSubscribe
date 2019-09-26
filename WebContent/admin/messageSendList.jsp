<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link
	href="${pageContext.request.contextPath }/mutualResource/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/mutualResource/css/style.css" />
<link
	href="${pageContext.request.contextPath }/mutualResource/assets/css/codemirror.css"
	rel="stylesheet">
	<link rel="stylesheet"
		href="${pageContext.request.contextPath }/mutualResource/assets/css/ace.min.css" />
	<link rel="stylesheet"
		href="${pageContext.request.contextPath }/mutualResource/assets/css/font-awesome.min.css" />
	<!--[if IE 7]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
	<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath }/mutualResource/assets/css/ace-ie.min.css" />
		<![endif]-->
	<script
		src="${pageContext.request.contextPath }/mutualResource/assets/js/jquery.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

	<!--[if !IE]> -->

	<script type="text/javascript">
			window.jQuery || document.write("<script src='${pageContext.request.contextPath }/mutualResource/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${pageContext.request.contextPath }/mutualResource/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

	<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${pageContext.request.contextPath }/mutualResource/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
	<script
		src="${pageContext.request.contextPath }/mutualResource/assets/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/mutualResource/assets/js/typeahead-bs2.min.js"></script>
	<!-- page specific plugin scripts -->
	<script
		src="${pageContext.request.contextPath }/mutualResource/assets/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/mutualResource/assets/js/jquery.dataTables.bootstrap.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/mutualResource/js/H-ui.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/mutualResource/js/H-ui.admin.js"></script>
	<script
		src="${pageContext.request.contextPath }/mutualResource/assets/layer/layer.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath }/mutualResource/assets/laydate/laydate.js"
		type="text/javascript"></script>
</head>

<body>
	<div class="page-content clearfix">
		<div id="Member_Ratings">
			<div class="d_Confirm_Order_style">
				<div class="search_style">
					<div class="title_names">搜索发送过的消息${search.receiver}</div>
					<ul class="search_content clearfix">
						<form
							action="${pageContext.request.contextPath }/message/MessageServlet?m=listSendMessage&reqeustUser=admin"
							method="post">

							<li><label class="l_f">接收人身份</label>
							<select name="receiver" value="${search.receiver}">
								<option value="">所有</option>
								<option value="admin" ${search.receiver eq "admin" ? "selected":""}>管理员</option>
								<option value="doctor" ${search.receiver eq "doctor" ? "selected":""}>咨询师</option>
								<option value="client" ${search.receiver eq "client" ? "selected":""}>来访者</option>
							</select>
							</li>
							
							<li><label class="l_f">姓名</label><input name="receiverName"
								type="text" class="text_add" placeholder="接受者姓名"
								value="${search.receiverName }" /></li>

							<li><label class="l_f">内容</label><input name="context"
								type="text" class="text_add" placeholder="消息内容"
								value="${search.context }" /></li>
								
								
							<li>
								<label class="l_f">是否已读</label>
								<select name="isRead">
								<option value="">全部</option>
								<option value="1" ${search.isRead eq 1 ? "selected" : "" }>已读</option>
								<option value="0" ${search.isRead eq 0 ? "selected" : "" }>未读</option>
							</select>                                     
							</li>	
							<li><label class="l_f">发送时间：从</label><input
								 id="startSendTime" name="startSendTime"
								type="text" class="inline laydate-icon" placeholder="从何时开始"
								value="${search.startSendTime }" /></li>
							<li><label class="l_f">到</label><input 
								id="endSendTime" name="endSendTime" type="text"
								class="inline laydate-icon" placeholder="到何时止"
								value="${search.endSendTime }" />止</li>


							<li><button type="submit" class="btn_search">
									<i class="icon-search"></i>查询
								</button></li>

						</form>

					</ul>
				</div>
				<!---->
				<%-- <div class="border clearfix">
					<span class="l_f"> <a href="javascript:void()"
						id="member_add" class="btn btn-warning"><i class="icon-plus"></i>发送消息</a>
					</span> <span class="r_f">共：<b>${listSize }</b>条
					</span>
				</div> --%>
				<!---->
				<div class="table_menu_list">
					<table class="table table-striped table-bordered table-hover"
						id="sample-table">

						<thead>
							<tr>
								<th width="100">发送给</th>
								<th>内容</th>
								<th width="110">发送时间</th>
								<th width="70">是否已读</th>
								<th width="250">操作</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${messageList }" var="message">

								<tr>

									<td>${message.receiverName}</td>

									<td class="text-l">${message.context }</td>

									<td>
									<fmt:formatDate value="${message.sendTime }" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>

									<c:if test="${message.isRead eq 0}">
										<td class="td-status"><span
											class="label label-success radius">未读</span>
										</td>

									</c:if>
									<c:if test="${message.isRead eq 1}">
										<td class="td-status"><span
											class="label label-defaunt radius">已读</span></td>
										
									</c:if>
									
									<td>
									<a style="text-decoration:none"
											onClick="sendMessage(this,'${message.receiverId }','${message.receiverName }','${message.receiver }')"
											href="javascript:;" title="继续发送"
											class="btn btn-xs btn-success">继续发送</a>
									</td>

								</tr>

							</c:forEach>



						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
<%@include file="/mutualResource/form/SendMessageForm.jsp"%>

</body>
</html>
<script>
jQuery(function($) {
	
	
				
	var oTable1 = $('#sample-table').dataTable( {
		 "bSort" : true, //是否启动各个字段的排序功能  
		"bStateSave": true,//状态保存
		"bPaginate" : true, 
		 "bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
		"searching": true//搜索输入框显示
		
	} );
			
			
				$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
				
				
				
				/**
				*检查表单元素合法性
				*/
				$("#announcementForm :input").each(function(){
					
					var thisElement = $(this);
					
					//当改变的时候，触发检查函数
					$(this).change(function(){ 
						
						isAbleCheckOne(thisElement);

					});	
				})
				
			});
 
/*
 * 
 *发送消息
 * 代表触发oclick的当前对象
 receiverId 接受者id
 receiverName 接受者姓名
 receiver 接受者身份
 */
function sendMessage(an,receiverId,receiverName,receiver){
	
	 var receiverParm = "&receiverId=" + receiverId;
	receiverParm += "&receiverName=" + receiverName;
	receiverParm += "&receiver=" + receiver; 
	
	$("#receiverName").text(receiverName);
	
	
	//显示发送消息界面
	layer.open({
        type: 1,
        title: '发送消息给:' + receiverName,
		maxmin: true, 
		shadeClose: false, //点击遮罩关闭层
        area : ['430px' , '350px'],
        content:$('#add_menber_style'),
		btn:['发送','取消'],
		yes:function(index,layero){	
			
			var msg = "";
			
			if(isAble()){
				//如果数据合法
				
				//ajax上传
			     $.ajax({  
			          url: '${pageContext.request.contextPath }/message/MessageServlet?m=addMessage'+receiverParm ,  
			          type: 'POST',  
			          dataType:'json', 
			          data: $("#SendMessageForm").serialize(),  
			          cache: false,  
			          success: function (data) { 
			        	  
			        	  if(data.isSuccess){
			        		  
			        		  msg = "发送成功！";
			        		  
			        		  //清空校验
			        		  $('#contextDiv').text("");
			        		  $('#context').val("");
			        		  layer.alert(msg,
						    		  
			   	 	               {title: '提示框',				
			   	 					icon:1,	}	,
			   	 					function(){
			   	 						location.reload();
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
				
			     layer.close(index);
			     
				
			}else{
				
				layer.alert("请填写正确的数据！",{
	 	               title: '提示框',				
	 				icon:1,		
	 				  });
				
			}
		     
		}
    });
}




/**
 * 对表单验证合法性
 */
 function isAble(){
	
	var isOk = true;
	
	var text = $.trim($("#SendMessageForm").find("#context").val());

	if(text.length > 200 || text ==""){
		isOk = false;
	}
	 
	 return isOk;
	
}


 laydate({
    elem: '#startSendTime',
    event: 'focus' 
}); 
 laydate({
	    elem: '#endSendTime',
	    event: 'focus' 
	}); 

</script>