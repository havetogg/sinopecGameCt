<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<c:set var="contentPath" value="<%=request.getContextPath()%>"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="/js/visitStat.js"></script>
</head>
<body>
	<a class="h_categoryAdd_close" onclick="hideBg()">X</a>
	
	<div>
		<span class="h_class_name">DISCUSS_ID</span>
		<span><input id="DISCUSS_ID" name="DISCUSS_ID" value="${bean.DISCUSS_ID}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">USER_ID</span>
		<span><input id="USER_ID" name="USER_ID" value="${bean.USER_ID}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">CONTENT</span>
		<span><input id="CONTENT" name="CONTENT" value="${bean.CONTENT}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">CREATE_TIME</span>
		<span><input id="CREATE_TIME" name="CREATE_TIME" value="${bean.CREATE_TIME}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">PRAISE_NUM</span>
		<span><input id="PRAISE_NUM" name="PRAISE_NUM" value="${bean.PRAISE_NUM}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">REPLY_NUM</span>
		<span><input id="REPLY_NUM" name="REPLY_NUM" value="${bean.REPLY_NUM}" type="text" placeholder="" autocomplete="off"></span>
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
			
			$("#closeDiv").click(function(){
				$(".fadebj").hide();
			});
			
			$("#save").click(function(){
				var DISCUSS_ID=$("#DISCUSS_ID").val();
				if($.trim(DISCUSS_ID).length==0){
					TipShow('请输入DISCUSS_ID',1200);
					return ;
				}
				
				var USER_ID=$("#USER_ID").val();
				if($.trim(USER_ID).length==0){
					TipShow('请输入USER_ID',1200);
					return ;
				}
				
				var CONTENT=$("#CONTENT").val();
				if($.trim(CONTENT).length==0){
					TipShow('请输入CONTENT',1200);
					return ;
				}
				
				var CREATE_TIME=$("#CREATE_TIME").val();
				if($.trim(CREATE_TIME).length==0){
					TipShow('请输入CREATE_TIME',1200);
					return ;
				}
				
				var PRAISE_NUM=$("#PRAISE_NUM").val();
				if($.trim(PRAISE_NUM).length==0){
					TipShow('请输入PRAISE_NUM',1200);
					return ;
				}
				
				var REPLY_NUM=$("#REPLY_NUM").val();
				if($.trim(REPLY_NUM).length==0){
					TipShow('请输入REPLY_NUM',1200);
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
						url : 'update.htm',
						data:{'ID':'${param.ID}','DISCUSS_ID':DISCUSS_ID,'USER_ID':USER_ID,'CONTENT':CONTENT,'CREATE_TIME':CREATE_TIME,'PRAISE_NUM':PRAISE_NUM,'REPLY_NUM':REPLY_NUM,'DELETE_FLAG':DELETE_FLAG},
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