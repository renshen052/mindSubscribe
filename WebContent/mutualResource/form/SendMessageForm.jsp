<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--发送消息的弹出层 -->
<div class="add_menber" id="SendMessageDiv" style="display:none">
<form id="SendMessageForm">
	<ul class=" page-content">

		<li><label class="label_name">消息内容:</label> <textarea
				name="context" id="context" class="textarea"
				onKeyUp="textarealength(this,200)" cols="50" rows="8"></textarea>
			<p class="textarea-numberbar">
				<em class="textarea-length">0</em>/200
			</p>
			<div style="color:red" class="prompt r_f" id="contextDiv"></div></li>
	</ul>

</form>
</div>
<script type="text/javascript">
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
        area : ['430px' , '390px'],
        content:$('#SendMessageDiv'),
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
		$("#contextDiv").html("消息长度为1-200字符");
	}
	 
	 return isOk;
	
}

</script>