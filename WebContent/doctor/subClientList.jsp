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
 				<th >申请者</th>
				<th>申请时间</th>
				<th>期望时间</th>
				<th>期望地点</th>
				<th>主观描述</th>
				<th >问卷分数</th>                
				<th >操作</th>
			</tr>
		</thead>
	<tbody>
	<c:forEach items="${clientArchiveList }" var="clientArchive">
	
		<tr>
           
          <td>${clientArchive.client.name }</td>
          
          <td class="text-l"><fmt:formatDate value="${clientArchive.applyTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          
          <td>${clientArchive.expectTime }</td>
          
          <td>${clientArchive.expectPlace }</td>
          
          <td height="10px">
          <span title="${clientArchive.clientDescription }" name="descrip" onclick="javascript:showD(this,'${clientArchive.clientDescription }')"> 
			</span>
          
          </td>
          
          <td>${clientArchive.level }</td>
          
          <td class="td-manage">
          <a style="text-decoration:none" class="btn btn-xs btn-success" onclick="sendMessage(this,'${clientArchive.client.clientId}','${clientArchive.client.name}','client')">联系申请者</a>
          <a style="text-decoration:none" class="btn btn-xs btn-success" onclick="showSub('${clientArchive.archivesId}','${clientArchive.clientId}')">查看详情</a>
          <a style="text-decoration:none" class="btn btn-xs btn-success" onclick="consult(this,'${clientArchive.archivesId}','${clientArchive.client.clientId}')">安排咨询</a>
          <a style="text-decoration:none" class="btn btn-xs btn-success" onclick="cancelSub(this,'${clientArchive.archivesId}','${clientArchive.client.clientId}','${clientArchive.applyTime }')">驳回申请</a>
          </td>
          
		</tr>
	
	</c:forEach>
	
        
         
      </tbody>
	</table>
	<b>安排咨询前请与来访者协商具体的时间地点</b>
   </div>
  </div>
 </div>
</div>
<%@include file="/mutualResource/form/SendMessageForm.jsp"%>
<!--添加用户图层-->
<div class="add_menber" id="add_menber_style" style="display:none">
  <form action="" method="post" id="sub">
    <ul class=" page-content">
    <li class="adderss">
    <label class="label_name">咨询：</label>
    
    <input style="width: 200px;" id="startDatetime" name="startDatetime" type="text" class="inline laydate-icon" placeholder="开始时间" />
    到
    <input  style="width: 200px;" id="endDatetime" name="endDatetime" type="text" class="inline laydate-icon" placeholder="结束时间" />
    <span id="dateTime"></span>
    </li>
     
     <li class="adderss"><label class="label_name">咨询地址：</label><span class="add_name"><input name="subPlace" style="width: 300px;" id="subPlace" type="text"  class="text_add"  /></span> <div class="prompt r_f"></div> 
     <span id="place"></span>
     </li>
     
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
				
				$("span[name='descrip']").each(function(){
					this.click();
				});
				
			});
			
			
function showD(obj,clientDescription ){
	
	var part = "";
	if(clientDescription.length <= 5){
		part = clientDescription;
	}else{
		part = clientDescription.substring(0,6);
		part += "...";
	}
	obj.innerHTML = part;
	
}

function showSub(archivesId,clientId){
	
	window.location = "${pageContext.request.contextPath }/doctor/DoctorSubServlet?m=subShow&archivesId="+archivesId+"&clientId="+clientId;
	
}

/**
 * 驳回申请
 */
function cancelSub(obj,archivesId,clientId,applyTime){
	
layer.confirm('确认要驳回吗？',function(index){
		
		//ajax
		$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/doctor/DoctorSubServlet?m=updateStatusFalse&archivesId="+archivesId+"&clientId="+clientId+"&applyTime="+applyTime,
		dataType : "json",
		success : function(data) {
			
			if (data['isSuccess'] == true) {
				
				layer.msg('已驳回!',{icon: 5,time:1000});
				
				//删除页面上的
				$(obj).parents("tr").remove();
				
				
			}else{
				layer.msg('驳回失败! '+data['msg'],{icon: 0,time:1000});
			}
		}
	});
		
	});
	
	
}

/**
 * 安排咨询
 */
 function consult(obj,archivesId,clientId){
		
	 //清空上次的
	// removeForm();
	 
    layer.open({
        type: 1,
        title: '安排咨询',
		maxmin: true, 
        area : ['800px' , ''],
        content:$('#add_menber_style'),
		btn:['安排','取消'],
		yes:function(index,layero){	
			
			var msg = "";
			
			if(isAble()){
				//如果数据合法
				
				//ajax上传$("#xx").serialize()
				var formData = $($(layero).find('form')[0]).serialize();
			     $.ajax({  
			    	 type : "GET",
			 		 url : "${pageContext.request.contextPath}/doctor/DoctorSubServlet?m=planSub&archivesId="+archivesId+"&clientId="+clientId,
			 		 dataType : "json",
			          data: formData,  
			          success: function (data) { 
			        	  
			        	  if(data.isSuccess){
			        		  
			        		  msg = "安排成功，请在\"咨询中\"查看！";
			        		  
			        		  layer.alert(msg,{
				 	               title: '提示框',				
				 				icon:1,		
				 				  });
			        		  
			        		//删除页面上的
			  				$(obj).parents("tr").remove();
			        		
			        		  
			        	  }else{
			        		  msg = data.msg;
			        		  layer.alert(msg,{
				 	               title: '提示框',				
				 				icon:1,		
				 				  });
			        	  }
			          },  
			          error: function (returndata) {  
			        	  msg = "失败请刷新后重试";  
			        	  layer.alert(msg,{
			 	               title: '提示框',				
			 				icon:1,		
			 				  });
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

function isAble(){
	
	
	var isOk = true;
	
	if($("#startDatetime").val()=="" || $("#endDatetime").val()==""){
		
		$("#dateTime").html("<span class=\"msgErr\" style=\"color:red\">请选择咨询时间！</span>")
		
		isOk = false;
	
	}
	if($.trim($("#subPlace").val())==""){
		
		$("#place").html("<span class=\"msgErr\" style=\"color:red\">请填写咨询地点！</span>");
		
		isOk = false;

	}
	return isOk;
	
}

	 laydate({
		    elem: '#startDatetime',
		    event: 'focus' ,
		   format: 'YYYY-MM-DD hh:mm:ss',
		    type: 'datetime',
		    istime: true, //必须填入时间 
		    min: laydate.now(0,"YYYY-MM-DD hh:mm:ss") //设定最小日期为当前日期
		}); 
		 laydate({
			    elem: '#endDatetime',
			    event: 'focus', 
			   format: 'YYYY-MM-DD hh:mm:ss',
			   istime: true, //必须填入时间 
			    type: 'datetime',
			    min: laydate.now(0,"YYYY-MM-DD hh:mm:ss") //设定最小日期为当前日期
			}); 

</script>