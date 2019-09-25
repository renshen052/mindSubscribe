<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<div class="title_names">搜索公告</div>
					<ul class="search_content clearfix">
						<form
							action="${pageContext.request.contextPath }/admin/AnnouncmentServlet?m=listAnnouncment"
							method="post">

							<li><label class="l_f">创建者</label><input name="creater"
								type="text" class="text_add" placeholder="创建者"
								value="${search.creater }" /></li>
							<li><label class="l_f">标题</label><input name="title"
								type="text" class="text_add" placeholder="公告标题"
								value="${search.title }" /></li>

							<li><label class="l_f">内容</label><input name="name"
								type="text" class="text_add" placeholder="公告内容"
								value="${search.context }" /></li>
							<li><label class="l_f">创建时间：从</label><input
								style="width: 120px;" id="startTime" name="startTime"
								type="text" class="inline laydate-icon" placeholder="从何时创建"
								value="${search.startTime }" /></li>
							<li><label class="l_f">到</label><input style="width: 120px;"
								id="endTime" name="endTime" type="text"
								class="inline laydate-icon" placeholder="到何时"
								value="${search.endTime }" />止</li>


							<li><button type="submit" class="btn_search">
									<i class="icon-search"></i>查询
								</button></li>

						</form>

					</ul>
				</div>
				<!---->
				<div class="border clearfix">
					<span class="l_f"> <a href="javascript:void()"
						id="member_add" class="btn btn-warning"><i class="icon-plus"></i>添加公告</a>
					</span> <span class="r_f">共：<b>${listSize }</b>条
					</span>
				</div>
				<!---->
				<div class="table_menu_list">
					<table class="table table-striped table-bordered table-hover"
						id="sample-table">

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

							<c:forEach items="${announcementList }" var="announcement">

								<tr>

									<td>${announcement.admin.name }</td>


									<td>${announcement.title }</td>


									<td class="text-l">${announcement.context }</td>

									<td>
									<fmt:formatDate value="${announcement.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>

									<c:if test="${announcement.isActive eq 1}">
										<td class="td-status"><span
											class="label label-success radius">显示</span></td>

										<td class="td-manage"><a
											onClick="member_stop(this,${announcement.announcementId })"
											href="javascript:;" title="隐藏" class="btn btn-xs btn-success"><i
												class="icon-ok bigger-120"></i></a> <a
											style="text-decoration: none"
											onClick="member_show(this,${announcement.announcementId })"
											href="javascript:;" title="查看公告"
											class="btn btn-xs btn-success">查看公告</a></td>

									</c:if>
									<c:if test="${announcement.isActive eq 0}">
										<td class="td-status"><span
											class="label label-defaunt radius">隐藏</span></td>
										<td><a style="text-decoration: none"
											onClick="member_start(this,${announcement.announcementId })"
											href="javascript:;" title="显示" class="btn btn-xs btn"><i
												class="icon-ok bigger-120"></i></a> <a
											style="text-decoration: none"
											onClick="member_show(this,${announcement.announcementId })"
											href="javascript:;" title="查看公告"
											class="btn btn-xs btn-success">查看公告</a></td>
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
	<div class="add_menber" id="add_menber_style" style="display: none">
		<form id="announcementForm">
			<ul class=" page-content">

				<li><label class="label_name">公告标题：</label><span
					class="add_name"><input name="title" type="text" id="title"
						class="text_add" /></span>
				<div class="prompt r_f"></div></li>

				<li><label class="label_name">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label><span
					class="add_name"> <label><input name="isActive"
							value="1" type="radio" id="isActive1" class="ace"><span
								class="lbl">显示</span></label>&nbsp;&nbsp;&nbsp; <label><input
							name="isActive" value="0" type="radio" id="isActive0" class="ace"><span
								class="lbl">隐藏</span></label></span>
				<div class="prompt r_f" id="isActiveDiv"></div></li>

				<li><label class="label_name">公告内容:</label> <textarea
						name="context" id="context" class="textarea"
						onKeyUp="textarealength(this,1000)" cols="100" rows="10"></textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/1000
					</p>
					<div class="prompt r_f" id="contextDiv"></div></li>

			</ul>

		</form>
	</div>
	<div id=announcementSee class="add_menber" style="display: none">
		<h1 id="titleSee">标题</h1>
		<hr />
		<div id="contextSee">公告内容</div>
		<hr />
		<font style="color:green">
		由:<span id="createrSee">创建者</span>于<span id="createTimeSee">日期</span> 创建
		</font>
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
 
			
/*用户-添加*/
 $('#member_add').on('click', function(){
	 
    layer.open({
        type: 1,
        title: '创建公告&nbsp;&nbsp;<font style="color:red">*注意:公告一经发布不可修改*</font>',
		maxmin: true, 
		shadeClose: false, //点击遮罩关闭层
        area : ['800px' , '500px'],
        content:$('#add_menber_style'),
		btn:['创建','取消'],
		yes:function(index,layero){	
			
			var msg = "";
			
			if(isAble()){
				//如果数据合法
				
				//ajax上传
			     $.ajax({  
			          url: '${pageContext.request.contextPath }/admin/AnnouncmentServlet?m=addAnnouncement' ,  
			          type: 'POST',  
			          dataType:'json', 
			          data: $("#announcementForm").serialize(),  
			          cache: false,  
			          success: function (data) { 
			        	  
			        	  if(data.isSuccess){
			        		  
			        		  msg = "创建成功！";
			        		  
			        		  //清空
			        		  $("#tilte,#context").val("");
			        		  $("#tilte,#context").text("");
			        		  $("#announcementForm").find("input:radio:checked").removeAttr('checked');
			        		  $('#add_menber_style').find("div[class='prompt r_f']").text("");
			        		  
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
			   	 					icon:1,	}	,
			   	 					);
			        	  }
			          },  
			          error: function (returndata) {  
			        	  msg = "失败请刷新后重试"; 
			        	  layer.alert(msg,
					    		  
				 	               {title: '提示框',				
				 					icon:1,	}	,
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
});


/*用户-查看
 * 代表触发oclick的当前对象
 */
function member_show(an,id){
	
	//先查到用户数据
	selectedDoctor(id);
	
	//显示查看界面
	  layer.open({
      type: 1,
      title: '查看公告',
		maxmin: true, 
		shadeClose:false, //点击遮罩关闭层
      area : ['500px' , '500px'],
      content:$('#announcementSee'),
		btn:['查看完毕'],
		yes:function(index,layero){	
			layer.close(index);				
		}
  });
}
/*公告隐藏*/
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
	url : "${pageContext.request.contextPath}/admin/AnnouncmentServlet?m=selecteAnnouncement&id="+id,
	dataType : "json",
	success : function(data) {
		
		//成功查到
		if (data['isSuccess'] == true) {
			
			
			//给表单赋值
			
			//标题
			$("#titleSee").text(data.dataList[0].title);
			console.log("data.dataList[0].title-> " + data.dataList[0].title);
			
			//内容
			$("#contextSee").text(data.dataList[0].context);
			
			//创建日期
			$("#createTimeSee").text(data.dataList[0].createTime);
			
			//创建者
			$("#createrSee").text(data.dataList[0].admin.name);
			/* 
			$("#announcementSee").each(function(){
				
				if($(this).is("h1")){
					
					console.log("data.dataList[0].title-> " + data.dataList[0].title);
					$(this).val(data.dataList[0].title);
					return;
				}
				
				if($(this).is("div")){
					
					console.log("data.dataList[0].context-> " + data.dataList[0].context);
					
					$(this).val(data.dataList[0].context);
					return;
				}
				
				if($(this).is("#createTime")){
					
					console.log("data.dataList[0].createTime-> " + data.dataList[0].createTime);
					
					$(this).val(data.dataList[0].createTime);
					return;
				}
				
				if($(this).is("#creater")){
					
					console.log("data.dataList[0].admin.name-> " + data.dataList[0].admin.name);
					
					$(this).val(data.dataList[0].admin.name);
					return;
				}
				
			}); */
			
		
		
		}else{
			layer.msg(data['msg'],{icon: 0,time:1000});
		}
	}
	});
	
	
}



/**
 * 对表单验证合法性
 */
 function isAble(){
	
	var isOk = true;
	
	 $("#announcementForm :input").each(function(){
		 
		 //如果有一项不正确
		 if( !isAbleCheckOne($(this))){
			 isOk = false;
		 }
		 
	 });
	 
	 return isOk;
	
}

 function isAbleCheckOne(thisElement){
	 
	 var isOk = true;
	 
	//验证标题
	 if($(thisElement).is("#title")){
		 
		 var len = $(thisElement).val().length;
		 
         if(len > 10 || len < 1){
        	 
             var errorMsg = "标题为1-10个字符！";
             
             $(thisElement).parent().next("div").attr("style","color:red");
             
             $(thisElement).parent().next("div").html(errorMsg);
             
             isOk = false;
             
         }else{
        	 
        	 $(thisElement).parent().next("div").attr("style","color:green");
        	 
        	 $(thisElement).parent().next("div").html("通过");
         }
         
	 }
	
	//验证内容
	 if($(thisElement).is("#context")){
		 
		 var len = $(thisElement).val().length;
		 
         if(len > 1000 || len < 1){
        	 
             var errorMsg = "标题为1-1000个字符！";
             
             $("#contextDiv").attr("style","color:red");
             
             $("#contextDiv").html(errorMsg);
             
             isOk = false;
             
         }else{
        	 
        	 $("#contextDiv").attr("style","color:green");
        	 
        	 $("#contextDiv").html("通过");
         }
         
	 }
	 
	 
		//验证公告状态（不为空即可）
		if($(thisElement).is("input[name='isActive']")){
			
			var msg = "";
			
			if( $("input[name='isActive']:checked").val() == null){
				
				msg = "请选择！";
				$("#isActiveDiv").attr("style","color:red");
				
				isOk = false;
				
			}else{
				
				$("#isActiveDiv").attr("style","color:green");
				msg = "通过";
				
			}
			
			//填写内容
			$("#isActiveDiv").html(msg);
			
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