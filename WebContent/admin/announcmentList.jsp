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
      <div class="title_names">搜索公告</div>
      <ul class="search_content clearfix">
      <form action="${pageContext.request.contextPath }/doctor/DoctorServlet?m=listDoctor" method="post">
      
            <li><label class="l_f">创建者</label><input name="name" type="text"  class="text_add" placeholder="创建者" value="${search.creater }" /></li>
            <li><label class="l_f">内容</label><input name="name" type="text"  class="text_add" placeholder="公告内容" value="${search.context }" /></li>
            <li><label class="l_f">创建时间：从</label><input style="width:120px;" id="startTime" name="startTime" type="text"  class="inline laydate-icon" placeholder="从何时创建" value="${search.startTime }" /></li>
            <li><label class="l_f">到</label><input style="width:120px;" id="endTime" name="endTime" type="text"  class="inline laydate-icon" placeholder="到何时"  value="${search.endTime }"/>止</li>
           
           
       		<li ><button type="submit" class="btn_search"><i class="icon-search"></i>查询</button></li>
      
      </form>

      </ul>
    </div>
     <!---->
     <div class="border clearfix">
       <span class="l_f">
        <a href="javascript:void()" id="member_add" class="btn btn-warning"><i class="icon-plus"></i>添加公告</a>
       </span>
       <span class="r_f">共：<b>${listSize }</b>条</span>
     </div>
     <!---->
     <div class="table_menu_list">
       <table class="table table-striped table-bordered table-hover" id="sample-table">
		<thead>
		 <tr>
				<th width="100">创建者</th>
				<th width="100">标题</th>
				<th>内容</th>
				<th width="100">创建时间</th>
				<th width="70">状态</th>                
				<th width="250">操作</th>
			</tr>
		</thead>
	<tbody>
	<c:forEach items="${announcmentList }" var="announcment">
	
		<tr>
          
          <td>${creater }</td>
          
          
          <td>${announcment.title }</td>
          
          
          <td class="text-l">${announcment.context }</td>
          
          <td>${announcment.createTime }</td>
          
          <td class="td-status">
          
          <c:if test="${announcment.isActive eq 1}">
          	<span class="label label-success radius">显示</span>
          	</td>
          
          	<td class="td-manage">
          		<a onClick="member_stop(this,${announcment.announcmentId })"  href="javascript:;" title="隐藏"  class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></a> 
          	</td>
          	
          </c:if>
          <c:if test="${announcment.isActive eq 0}">
          
          	<span class="label label-defaunt radius">隐藏</span>
         	 
         	 <td class="td-manage">
          		<a style="text-decoration:none" onClick="member_start(this,${announcment.announcmentId })"  href="javascript:;" title="显示"  class="btn btn-xs btn"><i class="icon-ok bigger-120"></i></a> 
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
<!--添加用户图层-->
<div class="add_menber" id="add_menber_style" style="display:none">
  <form action="" method="post">
    <ul class=" page-content">
    
     <li class="adderss"><label class="label_name">公告标题：</label><span class="add_name"><input name="title" type="text" id="title"  class="text_add"/></span><div class="prompt r_f"></div></li>
     
     <li class="adderss"><label class="label_name">公告内容:</label>
     
     <textarea  name="context" id="context"  class="textarea" onKeyUp="textarealength(this,200)"></textarea>
     <p class="textarea-numberbar"><em class="textarea-length">0</em>/1000</p>
     
     <div class="prompt r_f"></div></li>
     
     
     <li><label class="label_name">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label><span class="add_name">
     <label><input name="isActive" value="1" type="radio" id="isActive1" class="ace"><span class="lbl">显示</span></label>&nbsp;&nbsp;&nbsp;
     <label><input name="isActive" value="0" type="radio" id="isActive0"  class="ace"><span class="lbl">隐藏</span></label></span><div class="prompt r_f" id="isActiveDiv"></div></li>
    </ul>
    
   </form>
 </div>
</body>
</html>
<script>
jQuery(function($) {
	
	
				var oTable1 = $('#sample-table').dataTable( {
				"aaSorting": [[ 4, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  //{"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
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
				
				
				
				/**
				*检查表单元素合法性
				*/
				$("#doctorEdit :input").each(function(){
					
					var thisElement = $(this);
					
					/* $(this).blur(function(){ 
					    //失去焦点处理函数
						isAbleCheckOne(thisElement);

					});	 */
					//当改变的时候，触发检查函数
					$(this).change(function(){ 
						
						isAbleCheckOne(thisElement);

					});	
				})
				
			});
 
			
/*用户-添加*/
 $('#member_add').on('click', function(){
	 
	 //可编辑
	 toggleEdit(true);
	 
	 //清空上次的
	 removeForm();
	  
	 
    layer.open({
        type: 1,
        title: '创建公告',
		maxmin: true, 
		shadeClose: false, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#add_menber_style'),
		btn:['提交','取消'],
		yes:function(index,layero){	
			
			var msg = "";
			
			if(isAble(true)){
				//如果数据合法
				
				//ajax上传
				var formData = new FormData($(layero).find('form')[0]);
			     $.ajax({  
			          url: '${pageContext.request.contextPath }/admin/AnnouncmentServlet?m=updateAnnouncment' ,  
			          type: 'POST',  
			          data: formData,  
			          async: false,  
			          cache: false,  
			          contentType: false,  
			          processData: false,  
			          success: function (data) { 
			        	  
			        	  if(data.isSuccess){
			        		  msg = "创建成功！";
			        	  }else{
			        		  msg = data.msg;
			        	  }
			          },  
			          error: function (returndata) {  
			        	  msg = "失败请刷新后重试";  
			          }  
			     }); 
			     
			     layer.alert(msg,{
	 	               title: '提示框',				
	 				icon:1,		
	 				  },function(){
	 					 window.location.reload();
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
});


/*用户-查看*/
function member_show(id){
	
	//清空提示
	$('#add_menber_style').find("div[class='prompt r_f']").text("");
	
	//先查到用户数据
	selectedDoctor(id);
	
	//禁止编辑
	toggleEdit(false);
	
	
	//显示查看界面
	  layer.open({
      type: 1,
      title: '查看咨询师信息',
		maxmin: true, 
		shadeClose:false, //点击遮罩关闭层
      area : ['800px' , ''],
      content:$('#add_menber_style'),
		btn:['查看完毕'],
		yes:function(index,layero){	
			layer.close(index);				
		}
  });
}
/*用户-停用*/
function member_stop(obj,id){
	layer.confirm('确认要隐藏吗？',function(index){
		
		//ajax
		$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/admin/AnnouncmentServlet?m=updateActive&action=0&id="+id,
		dataType : "json",
		success : function(data) {
			
			if (data['isSuccess'] == true) {
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,' + id +')" href="javascript:;" title="显示"><i class="icon-ok bigger-120"></i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">隐藏</span>');
				$(obj).remove();
				layer.msg('已隐藏!',{icon: 5,time:1000});
			}else{
				layer.msg('隐藏失败! '+data['msg'],{icon: 0,time:1000});
			}
		}
	});
		
	});
}

/*公告显示*/
function member_start(obj,id){
	layer.confirm('确认要显示吗？',function(index){
		
		//ajax
		$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/admin/AnnouncmentServlet?m=updateActive&action=1&id="+id,
		dataType : "json",
		success : function(data) {
			
			if (data['isSuccess'] == true) {
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="member_stop(this,' + id +')" href="javascript:;" title="隐藏"><i class="icon-ok bigger-120"></i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">显示</span>');
				$(obj).remove();
				layer.msg('已显示!',{icon: 6,time:1000});
			}else{
				layer.msg('显示失败! '+data['msg'],{icon: 0,time:1000});
			}
		}
	});
		
	});
}





/**
 * 请求选中的内容
 */
function selectedDoctor(id){
	
	//ajax
	$.ajax({
	type : "GET",
	url : "${pageContext.request.contextPath}/doctor/DoctorServlet?m=selecteDoctor&id="+id,
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
 * 给Doctor表单赋值
 */
function addDate(doctor){
	
}

/**
 * 对表单验证合法性
 */
 function isAble(){
	
	var isOk = true;
	
	 $("#doctorEdit :input").each(function(){
		 
		 //如果有一项不正确
		 if( !isAbleCheckOne($(this))){
			 isOk = false;
		 }
		 
	 });
	 
	 return isOk;
	
}

 function isAbleCheckOne(thisElement){
	 
	 var isOk = true;
	 
	//验证姓名
	 if($(thisElement).is("input[name='name']")){
		 
		 var nameVal = $.trim($(thisElement).val()); 
         var regName = /[~#^$@%&!*()<>:;'"{}【】  ]/;
         if(nameVal == "" || nameVal.length > 6 || regName.test(nameVal)){
        	 
             var errorMsg = " 姓名非空，，且不多于6个字符，不能包含特殊字符！";
             
             $(thisElement).parent().next("div").attr("style","color:red");
             
             $(thisElement).parent().next("div").html(errorMsg);
             
             isOk = false;
             
         }else{
        	 
        	 $(thisElement).parent().next("div").attr("style","color:green");
        	 
        	 $(thisElement).parent().next("div").html("通过");
         }
         
	 }
	 
	 
		//验证公告状态（不为空即可）
		if($(thisElement).is("input[name='sex']")){
			
			var msg = "";
			
			if( $("input[name='sex']:checked").val() == null){
				
				msg = "请选择！";
				$("#sexDiv").attr("style","color:red");
				
				isOk = false;
				
			}else{
				
				$("#sexDiv").attr("style","color:green");
				msg = "通过";
				
			}
			
			//填写内容
			$("#sexDiv").html(msg);
			
		}
		
	 
		
		
		
	 
		return isOk;
 }




 laydate({
    elem: '#startTime',
    event: 'focus' 
}); 
 laydate({
	    elem: '#endTime',
	    event: 'focus' 
	}); 

</script>