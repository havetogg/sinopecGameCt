<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<c:set var="contentPath" value="<%=request.getContextPath()%>"></c:set>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<script type="text/javascript" src="${contentPath }/js/visitStat.js"></script>
</head>
<body>
	<a class="h_categoryAdd_close" onclick="hideBg()">X</a>

	<div>
		<span class="h_class_name">NAME</span>
		<span><input id="NAME" name="NAME" value="${bean.NAME}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">IN_USE_FLAG</span>
		<span><input id="IN_USE_FLAG" name="IN_USE_FLAG" value="${bean.IN_USE_FLAG}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">PUBLISH_TIME</span>
		<span><input id="PUBLISH_TIME" name="PUBLISH_TIME" value="${bean.PUBLISH_TIME}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">CONTENT</span>
		<span><input id="CONTENT" name="CONTENT" value="${bean.CONTENT}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">ORIGINAL_URL</span>
		<span><input id="ORIGINAL_URL" name="ORIGINAL_URL" value="${bean.ORIGINAL_URL}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">CUTTED_PATH</span>
		<span><input id="CUTTED_PATH" name="CUTTED_PATH" value="${bean.CUTTED_PATH}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">REPLY_NUM</span>
		<span><input id="REPLY_NUM" name="REPLY_NUM" value="${bean.REPLY_NUM}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">PRAISE_NUM</span>
		<span><input id="PRAISE_NUM" name="PRAISE_NUM" value="${bean.PRAISE_NUM}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">CONCERN_NUM</span>
		<span><input id="CONCERN_NUM" name="CONCERN_NUM" value="${bean.CONCERN_NUM}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">ONLINE_START_TIME</span>
		<span><input id="ONLINE_START_TIME" name="ONLINE_START_TIME" value="${bean.ONLINE_START_TIME}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">ONLINE_END_TIME</span>
		<span><input id="ONLINE_END_TIME" name="ONLINE_END_TIME" value="${bean.ONLINE_END_TIME}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">CREATE_TIME</span>
		<span><input id="CREATE_TIME" name="CREATE_TIME" value="${bean.CREATE_TIME}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">LAST_MODIFY_TIME</span>
		<span><input id="LAST_MODIFY_TIME" name="LAST_MODIFY_TIME" value="${bean.LAST_MODIFY_TIME}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">SHOW_ORDER</span>
		<span><input id="SHOW_ORDER" name="SHOW_ORDER" value="${bean.SHOW_ORDER}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">DELETE_FLAG</span>
		<span><input id="DELETE_FLAG" name="DELETE_FLAG" value="${bean.DELETE_FLAG}" type="text" placeholder="" autocomplete="off"></span>
	</div>

	<div style="text-align: center;">
		<input id="save" class="h_category_save" type="button" value="保存">
	</div>

	<script type="text/javascript">
		$(function(){
			
			$("#save").click(function(){
				var NAME=$("#NAME").val();
				if($.trim(NAME).length==0){
					TipShow('请输入NAME',1200);
					return ;
				}
				
				var IN_USE_FLAG=$("#IN_USE_FLAG").val();
				if($.trim(IN_USE_FLAG).length==0){
					TipShow('请输入IN_USE_FLAG',1200);
					return ;
				}
				
				var PUBLISH_TIME=$("#PUBLISH_TIME").val();
				if($.trim(PUBLISH_TIME).length==0){
					TipShow('请输入PUBLISH_TIME',1200);
					return ;
				}
				
				var CONTENT=$("#CONTENT").val();
				if($.trim(CONTENT).length==0){
					TipShow('请输入CONTENT',1200);
					return ;
				}
				
				var ORIGINAL_URL=$("#ORIGINAL_URL").val();
				if($.trim(ORIGINAL_URL).length==0){
					TipShow('请输入ORIGINAL_URL',1200);
					return ;
				}
				
				var CUTTED_PATH=$("#CUTTED_PATH").val();
				if($.trim(CUTTED_PATH).length==0){
					TipShow('请输入CUTTED_PATH',1200);
					return ;
				}
				
				var REPLY_NUM=$("#REPLY_NUM").val();
				if($.trim(REPLY_NUM).length==0){
					TipShow('请输入REPLY_NUM',1200);
					return ;
				}
				
				var PRAISE_NUM=$("#PRAISE_NUM").val();
				if($.trim(PRAISE_NUM).length==0){
					TipShow('请输入PRAISE_NUM',1200);
					return ;
				}
				
				var CONCERN_NUM=$("#CONCERN_NUM").val();
				if($.trim(CONCERN_NUM).length==0){
					TipShow('请输入CONCERN_NUM',1200);
					return ;
				}
				
				var ONLINE_START_TIME=$("#ONLINE_START_TIME").val();
				if($.trim(ONLINE_START_TIME).length==0){
					TipShow('请输入ONLINE_START_TIME',1200);
					return ;
				}
				
				var ONLINE_END_TIME=$("#ONLINE_END_TIME").val();
				if($.trim(ONLINE_END_TIME).length==0){
					TipShow('请输入ONLINE_END_TIME',1200);
					return ;
				}
				
				var CREATE_TIME=$("#CREATE_TIME").val();
				if($.trim(CREATE_TIME).length==0){
					TipShow('请输入CREATE_TIME',1200);
					return ;
				}
				
				var LAST_MODIFY_TIME=$("#LAST_MODIFY_TIME").val();
				if($.trim(LAST_MODIFY_TIME).length==0){
					TipShow('请输入LAST_MODIFY_TIME',1200);
					return ;
				}
				
				var SHOW_ORDER=$("#SHOW_ORDER").val();
				if($.trim(SHOW_ORDER).length==0){
					TipShow('请输入SHOW_ORDER',1200);
					return ;
				}
				
				var DELETE_FLAG=$("#DELETE_FLAG").val();
				if($.trim(DELETE_FLAG).length==0){
					TipShow('请输入DELETE_FLAG',1200);
					return ;
				}
				
								
				if(confirm('确实要提交吗？')){
					$.ajax({
						type : 'post',
						url : 'save.htm',
						data:{'NAME':NAME,'IN_USE_FLAG':IN_USE_FLAG,'PUBLISH_TIME':PUBLISH_TIME,'CONTENT':CONTENT,'ORIGINAL_URL':ORIGINAL_URL,'CUTTED_PATH':CUTTED_PATH,'REPLY_NUM':REPLY_NUM,'PRAISE_NUM':PRAISE_NUM,'CONCERN_NUM':CONCERN_NUM,'ONLINE_START_TIME':ONLINE_START_TIME,'ONLINE_END_TIME':ONLINE_END_TIME,'CREATE_TIME':CREATE_TIME,'LAST_MODIFY_TIME':LAST_MODIFY_TIME,'SHOW_ORDER':SHOW_ORDER,'DELETE_FLAG':DELETE_FLAG},
						dataType : 'json',
						success : function(data, textStatus) {
							if(data.success){
								hideBg();
								query();
							}else{
								alert(data.message);
							}
						},
						error : function(data, textStatus) {
							alert('保存失败');
						}

					});
				}
			});
		});
	</script>
</body>
</html>