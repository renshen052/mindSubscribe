<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="${pageContext.request.contextPath }/admin/assets/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/style.css"/>       
        <link href="${pageContext.request.contextPath }/admin/assets/css/codemirror.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath }/admin/assets/css/ace.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath }/admin/assets/css/font-awesome.min.css" />
		<!--[if IE 7]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath }/admin/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
        <!--[if lte IE 8]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath }/admin/assets/css/ace-ie.min.css" />
		<![endif]-->
			<script src="${pageContext.request.contextPath }/admin/assets/js/jquery.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='${pageContext.request.contextPath }/admin/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${pageContext.request.contextPath }/admin/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${pageContext.request.contextPath }/admin/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${pageContext.request.contextPath }/admin/assets/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath }/admin/assets/js/typeahead-bs2.min.js"></script>
		<!-- page specific plugin scripts -->
		<script src="${pageContext.request.contextPath }/admin/assets/js/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath }/admin/assets/js/jquery.dataTables.bootstrap.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/H-ui.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/H-ui.admin.js"></script> 
        <script src="${pageContext.request.contextPath }/admin/assets/layer/layer.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath }/admin/assets/laydate/laydate.js" type="text/javascript"></script>



</head>

<body>
<div class="page-content clearfix">
    <div id="Member_Ratings">
      <div class="d_Confirm_Order_style">
    <div class="search_style">
      <div class="title_names">搜索查询</div>
      <ul class="search_content clearfix">
      <form action="${pageContext.request.contextPath }/client/ClientServlet?m=listClient" method="post">
      
            <li><label class="l_f">姓名</label><input style="width:70px;" name="name" type="text"  class="text_add" placeholder="来访者的" value="${search.name }" /></li>
            
            <li><label class="l_f">性&nbsp;&nbsp;别&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <input name="sex" type="radio"  class="ace" value="1" ${search.sex eq 1 ? "checked='checked'":"" }/><span class="lbl">男</span>
            <input name="sex" type="radio"  class="ace" value="0" ${search.sex eq 0 ? "checked='checked'":""}/><span class="lbl">女</span>
            </li>
            
           
            <li><label class="l_f">年龄：从</label><input style="width:70px;" name="startAge" type="number" step="1" min="0"  class="text_add" placeholder="最小年龄" value="${search.startAge }" /></li>
            <li><label class="l_f">到</label><input style="width:70px;" name="endAge" type="number" step="1" min="0"  class="text_add" placeholder="最大年龄"  value="${search.endAge }"/>止</li>
            
            <li></li>
            <li></li>
            
            <li><label class="l_f">电话</label><input name="phone" type="text"  class="text_add" placeholder="电话"  value="${search.phone }"/></li>
            <li><label class="l_f">邮箱</label><input name="email" type="text"  class="text_add" placeholder="邮箱"  value="${search.email }"/></li>
       		
       		<li><label class="l_f">注册时间：从</label><input style="width:120px;" id="startRegionTime" name="startRegionTime" type="text"  class="inline laydate-icon" placeholder="从何时起" value="${search.startRegionTime }" /></li>
            <li><label class="l_f">到</label><input style="width:120px;" id="endRegionTime" name="endRegionTime" type="text"  class="inline laydate-icon" placeholder="到何时"  value="${search.endRegionTime }"/>止</li>
       		
       		
       		
       		<li ><button type="submit" class="btn_search"><i class="icon-search"></i>查询</button></li>
      
      </form>

      </ul>
    </div>
     
     <!-- 表格 -->
     <div class="table_menu_list">
       <table class="table table-striped table-bordered table-hover" id="sample-table">
		<thead>
		 <tr>
				<th><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
				<th >登录账号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>手机</th>
				<th>邮箱</th>
				<th >注册时间</th>
				<th >状态</th>                
				<th >操作</th>
			</tr>
		</thead>
	<tbody>
	<c:forEach items="${clientList }" var="client">
	
		<tr>
          <td><label><input type="checkbox" class="ace" value="${client.clientId }" checkedName="${client.name }"><span class="lbl"></span></label></td>
          
          <td>${client.clientName }</td>
          
          <td><u style="cursor:pointer" class="text-primary" onclick="member_show(${client.clientId })">${client.name }</u></td>
          
          <td>${client.sex eq 1 ? "男":"女" }</td>
          
          <td>${client.age }</td>
          
          <td>${client.phone }</td>
          
          <td>${client.email }</td>
          
          <td class="text-l">${client.regionTime }</td>
          
          
          <td class="td-status">
          
          <c:if test="${client.isActive eq 1}">
          	<span class="label label-success radius">已启用</span>
          	</td>
          
          	<td class="td-manage">
          		<a onClick="member_stop(this,${client.clientId })"  href="javascript:;" title="停用"  class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></a> 
          	</td>
          	
          </c:if>
          <c:if test="${client.isActive eq 0}">
          
          	<span class="label label-defaunt radius">已停用</span>
         	 
         	 <td class="td-manage">
          		<a style="text-decoration:none" onClick="member_start(this,${client.clientId })"  href="javascript:;" title="启用"  class="btn btn-xs btn"><i class="icon-ok bigger-120"></i></a> 
          	</td>
          </c:if>
          
		</tr>
	
	
	</c:forEach>
	
        
         
      </tbody>
	</table>
   </div>
  </div>
 </div>
</div>

</body>
</html>
<script>
jQuery(function($) {
	
	
				var oTable1 = $('#sample-table').dataTable( {
				"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
		] } );
				
				
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
  

/*用户-停用*/
function member_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		
		//ajax
		$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/client/ClientServlet?m=updateActive&action=0&id="+id,
		dataType : "json",
		success : function(data) {
			
			if (data['isSuccess'] == true) {
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,' + id +')" href="javascript:;" title="启用"><i class="icon-ok bigger-120"></i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
				$(obj).remove();
				layer.msg('已停用!',{icon: 5,time:1000});
			}else{
				layer.msg('停用失败! '+data['msg'],{icon: 0,time:1000});
			}
		}
	});
		
	});
}

/*用户-启用*/
function member_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		
		//ajax
		$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/client/ClientServlet?m=updateActive&action=1&id="+id,
		dataType : "json",
		success : function(data) {
			
			if (data['isSuccess'] == true) {
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="member_stop(this,' + id +')" href="javascript:;" title="停用"><i class="icon-ok bigger-120"></i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
				$(obj).remove();
				layer.msg('已启用!',{icon: 6,time:1000});
			}else{
				layer.msg('启用失败! '+data['msg'],{icon: 0,time:1000});
			}
		}
	});
		
	});
}

/**
 * 请求选中的Doctor内容
 */
function selectedDoctor(id){
	
	//ajax
	$.ajax({
	type : "GET",
	url : "${pageContext.request.contextPath}/client/ClientServlet?m=selecteClient&id="+id,
	dataType : "json",
	success : function(data) {
		
		//成功查到
		if (data['isSuccess'] == true) {
			
			addDate(data.dataList[0]);
		
		
		}else{
			layer.msg(data['msg'],{icon: 0,time:1000});
		}
	}
	});
	
	
}

 /**
 * 	切换是否可编辑
 */
function toggleEdit(isAble){
	
	
	$("#doctorEdit :input").each(function(){
		
		if(isAble=="1"){
			//可编辑，去掉disabled=
			$(this).removeAttr("disabled");
		}else{
			
			$(this).attr("disabled","disabled");
		}

	});
	 
	
}




 laydate({
    elem: '#startRegionTime',
    event: 'focus' 
}); 
 laydate({
	    elem: '#endRegionTime',
	    event: 'focus' 
	}); 

</script>