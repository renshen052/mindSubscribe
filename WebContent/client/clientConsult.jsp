<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
     
     <!-- 表格 -->
     <div class="table_menu_list">
       <table class="table table-striped table-bordered table-hover" id="sample-table">
		<thead>
		 <tr>
 				<th >咨询师</th>
				<th>申请时间</th>
				<th>咨询开始时间</th>
				<th>咨询结束时间</th>
				<th>咨询记录文档</th>
				<th>回访评价</th>
				<th >操作</th>
			</tr>
		</thead>
	<tbody>
	<c:forEach items="${clientArchiveList }" var="clientArchive">
	
		<tr>
           
          <td>${clientArchive.doctor.name }</td>
          
          <td><fmt:formatDate value="${clientArchive.applyTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          
          <td><fmt:formatDate value="${clientArchive.startDatetime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          
          <td><fmt:formatDate value="${clientArchive.endDatetime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          
          <td>
          <c:if test="${not empty clientArchive.docPath}">
          <a href="/upload/document/${clientArchive.docPath}" >${clientArchive.docPath}</a>
          </c:if>
          <c:if test="${empty clientArchive.docPath }">
          未上传，请联系咨询师！
          </c:if>
          </td>
          
          <td>
          	${clientArchive.secondQuestionContext}
          </td>
          
          <td class="td-manage">
          <a style="text-decoration:none" class="btn btn-xs btn-success" onclick="sendMessage(this,'${clientArchive.doctor.doctorId}','${clientArchive.doctor.name}','doctor')">联系咨询师</a>
          <a style="text-decoration:none" class="btn btn-xs btn-success" onclick="evaluateSub(${clientArchive.archivesId})">评价一下</a>
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

<!--评价的弹出层 -->
<div class="add_menber" id="evaluateSubDiv" style="display:none">
<form id="evaluateSubForm">
	<ul class=" page-content">

		<li><label class="label_name">评价内容:</label> <textarea
				name="context" id="context" class="textarea"
				onKeyUp="textarealength(this,200)" cols="50" rows="8"></textarea>
			<p class="textarea-numberbar">
				<em class="textarea-length">0</em>/200
			</p>
			<div style="color:red" class="prompt r_f" id="evaluateSubFormDiv"></div></li>
	</ul>

</form>
</div>

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
				
				
				$('table th input:checkbox').on('click' , function(){
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox')
					.each(function(){
						this.checked = that.checked;
						$(this).closest('tr').toggleClass('selected');
					});
						
				});
			
			
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
				
				
			});
function evaluateSub(archivesId){
	
	layer.open({
        type: 1,
        title: '对本次咨询评价',
		maxmin: true, 
		shadeClose: false, //点击遮罩关闭层
        area : ['430px' , '390px'],
        content:$('#evaluateSubDiv'),
		btn:['确认','取消'],
		yes:function(index,layero){	
			
			var msg = "";
			
			if(isAble()){
				//如果数据合法
				
				//ajax上传
			     $.ajax({  
			          url: '${pageContext.request.contextPath }/client/ClientSubServlet?m=evaluateSub&archivesId='+archivesId ,  
			          type: 'POST',  
			          dataType:'json', 
			          data: $("#evaluateSubForm").serialize(),  
			          cache: false,  
			          success: function (data) { 
			        	  
			        	  if(data.isSuccess){
			        		  
			        		  msg = "评价成功！";
			        		  
			        		  $('#evaluateSubFormDiv').val("");
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
	
	var text = $.trim($("#evaluateSubForm").find("#context").val());

	if(text.length > 200 || text ==""){
		isOk = false;
		$("#evaluateSubFormDiv").html("消息长度为1-200字符");
	}
	 
	 return isOk;
	
}

</script>