<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--发送消息的弹出层 -->
<div class="add_menber" id="add_menber_style" style="display:none">
<form id="SendMessageForm">
	<ul class=" page-content">

		<li><label class="label_name">消息内容:</label> <textarea
				name="context" id="context" class="textarea"
				onKeyUp="textarealength(this,200)" cols="50" rows="8"></textarea>
			<p class="textarea-numberbar">
				<em class="textarea-length">0</em>/200
			</p>
			<div class="prompt r_f" id="contextDiv"></div></li>
	</ul>

</form>
</div>