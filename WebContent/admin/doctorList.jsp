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
<%--        <script src="${pageContext.request.contextPath }/admin/assets/laydate/laydate.js" type="text/javascript"></script>
 --%>


</head>

<body>
<div class="page-content clearfix">
    <div id="Member_Ratings">
      <div class="d_Confirm_Order_style">
    <div class="search_style">
      <div class="title_names">搜索查询</div>
      <ul class="search_content clearfix">
      <form action="${pageContext.request.contextPath }/doctor/DoctorServlet?m=listDoctor" method="post">
      
            <li><label class="l_f">咨询师姓名</label><input name="name" type="text"  class="text_add" placeholder="输入咨询师姓名" value="${search.name }" /></li>
            
            <li><label class="l_f">性别</label>
            <input name="sex" type="radio"  class="ace" value="1" ${search.sex eq 1 ? "checked='checked'":"" }/><span class="lbl">男</span>
            <input name="sex" type="radio"  class="ace" value="0" ${search.sex eq 0 ? "checked='checked'":""}/><span class="lbl">女</span>
            </li>
            
           
            <li><label class="l_f">年龄：从</label><input style="width:90px;" name="startAge" type="text"  class="text_add" placeholder="最小年龄" value="${search.startAge }" /></li>
            <li><label class="l_f">到</label><input style="width:90px;" name="endAge" type="text"  class="text_add" placeholder="最大年龄"  value="${search.endAge }"/>止</li>
            <li><label class="l_f">电话</label><input name="phone" type="text"  class="text_add" placeholder="电话"  value="${search.phone }"/></li>
            <li><label class="l_f">邮箱</label><input name="email" type="text"  class="text_add" placeholder="邮箱"  value="${search.email }"/></li>
       		<li ><button type="submit" class="btn_search"><i class="icon-search"></i>查询</button></li>
      
      </form>

      </ul>
    </div>
     <!---->
     <div class="border clearfix">
       <span class="l_f">
        <a href="javascript:void()" id="member_add" class="btn btn-warning"><i class="icon-plus"></i>添加咨询师</a>
        <a href="javascript:void()" id="deleteList"class="btn btn-danger"><i class="icon-trash"></i>批量删除</a>
       </span>
       <span class="r_f">共：<b>${listSize }</b>条</span>
     </div>
     <!---->
     <div class="table_menu_list">
       <table class="table table-striped table-bordered table-hover" id="sample-table">
		<thead>
		 <tr>
				<th width="25"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
				<th width="100">登录账号</th>
				<th width="100">个人照片</th>
				<th width="100">姓名</th>
				<th width="80">性别</th>
				<th width="80">年龄</th>
				<th width="120">手机</th>
				<th width="150">邮箱</th>
				<th width="">一般咨询地点</th>
                <th width="100">等级</th>
				<th width="70">状态</th>                
				<th width="250">操作</th>
			</tr>
		</thead>
	<tbody>
	<c:forEach items="${doctorList }" var="doctor">
	
		<tr>
          <td><label><input type="checkbox" class="ace" value="${doctor.doctorId }" checkedName="${doctor.name }"><span class="lbl"></span></label></td>
          
          <td>${doctor.doctorName }</td>
          
          <td>
          <img src="/upload/${doctor.img}"  width="77.7px" height="77.7px"/>
          </td>
          
          <td><u style="cursor:pointer" class="text-primary" onclick="member_show(${doctor.doctorId })">${doctor.name }</u></td>
          
          <td>${doctor.sex eq 1 ? "男":"女" }</td>
          
          <td>${doctor.age }</td>
          
          <td>${doctor.phone }</td>
          
          <td>${doctor.email }</td>
          
          <td class="text-l">${doctor.place }</td>
          
          
          <td>${doctor.level }</td>
          
          <td class="td-status">
          
          <c:if test="${doctor.isActive eq 1}">
          	<span class="label label-success radius">已启用</span>
          	</td>
          
          	<td class="td-manage">
          		<a onClick="member_stop(this,${doctor.doctorId })"  href="javascript:;" title="停用"  class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></a> 
          		<a title="编辑" onclick="member_edit(${doctor.doctorId })" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a> 
          		<a title="删除" href="javascript:;"  onclick="member_del(this,${doctor.doctorId })" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
          	</td>
          	
          </c:if>
          <c:if test="${doctor.isActive eq 0}">
          
          	<span class="label label-defaunt radius">已停用</span>
         	 
         	 <td class="td-manage">
          		<a style="text-decoration:none" onClick="member_start(this,${doctor.doctorId })"  href="javascript:;" title="启用"  class="btn btn-xs btn"><i class="icon-ok bigger-120"></i></a> 
          		<a title="编辑" onclick="member_edit(${doctor.doctorId })" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a> 
          		<a title="删除" href="javascript:;"  onclick="member_del(this,${doctor.doctorId })" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
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
  <form action="${pageContext.request.contextPath }/doctor/DoctorServlet?m=updateDoctor" method="post" enctype="multipart/form-data" id="doctorEdit">
    <ul class=" page-content">
     <li><label class="label_name">姓名：</label><span class="add_name"><input name="name" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
     <li><label class="label_name">年龄</label><span class="add_name"><input  name="age" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
     
     <li><label class="label_name">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label><span class="add_name">
     <label><input name="sex" type="radio" value="1"  class="ace"><span class="lbl">男</span></label>&nbsp;&nbsp;&nbsp;
     <label><input name="sex" type="radio" value="0"  class="ace"><span class="lbl">女</span></label>&nbsp;&nbsp;&nbsp;
     </span>
     <div class="prompt r_f"></div>
     </li>
     <li><label class="label_name">邮箱：</label><span class="add_name"><input name="email" type="text"  class="text_add" "/></span><div class="prompt r_f"></div></li>
     <li><label class="label_name">电话：</label><span class="add_name"><input name="phone" type="text"  class="text_add" "/></span><div class="prompt r_f"></div></li>
     
     
     <li><label class="label_name">等级：</label>
     <span class="add_name">
     <select name="level">
     	<option value="未选择" >未选择</option>
     	<option value="一级咨询师"  >一级咨询师</option>
     	<option value="二级级咨询师" >二级咨询师</option>
     	<option value="三级咨询师" >三级咨询师</option>
     </select>
     </span>
     <div class="prompt r_f"></div>
     </li>
     
     
     <li ><label class="label_name">咨询地址：</label><span class="add_name"><input name="place" type="text"  class="text_add"  /></span> <div class="prompt r_f"></div> </li>
     
     <li ><label class="label_name">擅长方向</label><span class="add_name"><input name="skill" type="text"  class="text_add" /></span> <div class="prompt r_f"></div> </li>
     
     <!-- 图片 -->
     <li >
     <label class="label_name">个人图片</label>
     <span class="add_name" id="img">
     <input name="imgPath" type="hidden"/>
     <img src="${pageContext.request.contextPath }/admin/images/icon_error_s.png" width="77.7" height="77.7" />
     </span>
     </li>
     <li>
     <input name="img" type="file"  class="text_add" style=" width:350px"/>
     </span>
     </li>
    
     
     <li class="adderss"></li>
     
     <li class="adderss"><label class="label_name">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label><span class="add_name">
     <label><input name="isActive" value="1" type="radio"  class="ace"><span class="lbl">启用</span></label>&nbsp;&nbsp;&nbsp;
     <label><input name="isActive" value="0" type="radio"   class="ace"><span class="lbl">停用</span></label></span><div class="prompt r_f"></div></li>
    </ul>
    
   </form>
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
/*批量删除*/
$("#deleteList").on('click',function(){

    var checkeds = "";
    
    var checkedsName= "";
    
    $("#sample-table").find("input[type='checkbox']:checked").each(function(){
		
    	checkeds += $(this).val() + ",";
    	checkedsName += $(this).attr("checkedName") + ",";
		
	});
    
    checkeds = checkeds.slice(0, checkeds.length - 1);
    
    layer.confirm('即将删除:' + checkedsName + '   请确认！',function(index){
    	
    	$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/doctor/DoctorServlet?m=deletDoctor&checkeds=" + checkeds,
			dataType : "json",
			success : function(data) {
				
				if (data['isSuccess'] == true) {
					
					//删除页面上的
					var objs = $("#sample-table").find("input[type='checkbox']:checked");
					for(var o = 0 ; o < objs.length; o++){
						
						$(objs[o]).parents("tr").remove();
						
					}
					
					layer.msg('已删除!',{icon:1,time:1000});
					
				}else{
					layer.msg(data['msg'],{icon: 0,time:1000});
				}
			}
		});
    	
    	layer.close(index);
    	
    });
    
 });  
			
/*用户-添加*/
 $('#member_add').on('click', function(){
	 
	 
	 //清空上次的
	 addDate("");
	 
	 $("input:radio[name='sex']").removeAttr('checked');
	 
	 $("input:radio[name='isActive']").removeAttr('checked');
	 
	 $("select[name='level']").find("option").removeAttr("selected");
	 $("select[name='level']").find("option[value='未选择']").attr("selected",true);
	 
    layer.open({
        type: 1,
        title: '添加用户',
		maxmin: true, 
		shadeClose: false, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#add_menber_style'),
		btn:['提交','取消'],
		yes:function(index,layero){	
			
			var msg = "";
			
			//ajax上传
			var formData = new FormData($(layero).find('form')[0]);
		     $.ajax({  
		          url: '${pageContext.request.contextPath }/doctor/DoctorServlet?m=updateDoctor' ,  
		          type: 'POST',  
		          data: formData,  
		          async: false,  
		          cache: false,  
		          contentType: false,  
		          processData: false,  
		          success: function (data) { 
		        	  
		        	  if(data.isSuccess){
		        		  msg = "添加成功，初始密码为123456！";
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
		     
		}
    });
});


/*用户-查看*/
function member_show(id){
	
	//先查到用户数据
	selectedDoctor(id);
	
	//显示查看界面
	  layer.open({
      type: 1,
      title: '修改用户信息',
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
	layer.confirm('确认要停用吗？',function(index){
		
		//ajax
		$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/doctor/DoctorServlet?m=updateActive&action=0&id="+id,
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
		url : "${pageContext.request.contextPath}/doctor/DoctorServlet?m=updateActive&action=1&id="+id,
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
/*用户-编辑*/
function member_edit(id){
	
	
	//先查到用户数据
	selectedDoctor(id);
	
	//显示编辑界面
	  layer.open({
        type: 1,
        title: '修改用户信息',
		maxmin: true, 
		shadeClose:false, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#add_menber_style'),
		btn:['提交','取消'],
		yes:function(index,layero){	
			
			var msg = "";
			
			//ajax上传
			var formData = new FormData($(layero).find('form')[0]);
		     $.ajax({  
		          url: "${pageContext.request.contextPath }/doctor/DoctorServlet?m=updateDoctor&id="+id ,  
		          type: 'POST',  
		          data: formData,  
		          async: false,  
		          cache: false,  
		          contentType: false,  
		          processData: false,  
		          success: function (data) { 
		        	  
		        	  if(data.isSuccess){
		        		  msg = "修改成功！";
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
		}
    });
}
/*用户-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/doctor/DoctorServlet?m=deletDoctor&id="+id,
			dataType : "json",
			success : function(data) {
				
				if (data['isSuccess'] == true) {
					
					//删除页面上的
					$(obj).parents("tr").remove();
					
					layer.msg('已删除!',{icon:1,time:1000});
				}else{
					layer.msg(data['msg'],{icon: 0,time:1000});
				}
			}
		});
		
		//关闭确认框
		layer.close(index);
		
	});
}



/**
 * 请求选中的Doctor内容
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
	
	$("input[name='name']").val(doctor.name);
	$("input[name='age']").val(doctor.age);
	
	$("input[name='skill']").val(doctor.skill);
	
		
	$("input:radio[name='sex'][value='" + doctor.sex + "']").attr('checked','checked');
	
	
	$("input[name='email']").val(doctor.email);
	$("input[name='phone']").val(doctor.phone);
	
	if(doctor.level){
		$("select[name='level']").find("option[value='" + doctor.level + "']").attr("selected",true);
		
	}else{//如果没有填级别
		$("select[name='level']").find("option[value='未选择']").attr("selected",true);
	}
	 
	
	$("input[name='place']").val(doctor.place);
	
	$("input:radio[name='isActive'][value='" + doctor.isActive + "']").attr('checked','checked');
	
	var imgPath = $("input[name='imgPath']");
	$(imgPath).val(doctor.img);
	if(doctor.img){
		$(imgPath).next().attr("src","/upload/"+doctor.img);
	}else{
		$(imgPath).next().attr("src","${pageContext.request.contextPath }/admin/images/icon_error_s.png");
	}
}


/* laydate({
    elem: '#start',
    event: 'focus' 
}); */

</script>