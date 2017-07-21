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
		<span class="h_class_name">USER_ID</span>
		<span><input id="USER_ID" name="USER_ID" value="${bean.USER_ID}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">NEWS_ID</span>
		<span><input id="NEWS_ID" name="NEWS_ID" value="${bean.NEWS_ID}" type="text" placeholder="" autocomplete="off"></span>
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
		<span class="h_class_name">AUDIT_FLAG</span>
		<span><input id="AUDIT_FLAG" name="AUDIT_FLAG" value="${bean.AUDIT_FLAG}" type="text" placeholder="" autocomplete="off"></span>
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
				var USER_ID=$("#USER_ID").val();
				if($.trim(USER_ID).length==0){
					TipShow('请输入USER_ID',1200);
					return ;
				}
				
				var NEWS_ID=$("#NEWS_ID").val();
				if($.trim(NEWS_ID).length==0){
					TipShow('请输入NEWS_ID',1200);
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
				
				var AUDIT_FLAG=$("#AUDIT_FLAG").val();
				if($.trim(AUDIT_FLAG).length==0){
					TipShow('请输入AUDIT_FLAG',1200);
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
						data:{'USER_ID':USER_ID,'NEWS_ID':NEWS_ID,'CONTENT':CONTENT,'CREATE_TIME':CREATE_TIME,'AUDIT_FLAG':AUDIT_FLAG,'DELETE_FLAG':DELETE_FLAG},
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