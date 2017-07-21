<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<c:set var="contentPath" value="<%=request.getContextPath()%>"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<div class="fade_b"></div>
	<div class="fade_cc">
		<div class="qdxq1">新增t_user</div>
		<div class="top"></div>
		<p class="qdxx">
			ID：<input type="text" class="ztbj" name="ID" id="ID" value="${bean.ID}" autocomplete="off">
		</p>
		<p class="qdxx">
			NAME：<input type="text" class="ztbj" name="NAME" id="NAME" value="${bean.NAME}" autocomplete="off">
		</p>
		<div class="bj1">
			<div id="closeDiv" class="bj1left fl">取&nbsp;&nbsp;&nbsp;&nbsp;消</div>
			<div id="save" class="bj1melld fl">确&nbsp;&nbsp;&nbsp;&nbsp;认</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			
			$("#closeDiv").click(function(){
				$(".fadebj").hide();
			});
			
			$("#save").click(function(){
				var ID=$("#ID").val();
//				if($.trim(ID).length==0){
//					alert('请输入ID');
//					return ;
//				}
				
				var NAME=$("#NAME").val();
//				if($.trim(NAME).length==0){
//					alert('请输入NAME');
//					return ;
//				}
				
								
				if(confirm('确实要提交吗？')){
					$.ajax({
						type : 'post',
						url : 'save.htm',
						data:{'ID':ID,'NAME':NAME},
						dataType : 'json',
						success : function(data, textStatus) {
							if(data.success){
								$(".fadebj").hide();
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