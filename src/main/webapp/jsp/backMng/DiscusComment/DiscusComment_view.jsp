<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
		<span class="h_class_name">话题ID</span>
		<span>${bean.DISCUSS_ID}</span>
	</div>
	<div>
		<span class="h_class_name">用户ID</span>
		<span>${bean.USER_ID}</span>
	</div>
	<div>
		<span class="h_class_name">评论内容</span>
		<span>${bean.CONTENT}</span>
	</div>
	<div>
		<span class="h_class_name">日期</span>
		<span>${bean.CREATE_TIME}</span>
	</div>
	<div>
		<span class="h_class_name">点赞数</span>
		<span>${bean.PRAISE_NUM}</span>
	</div>

	<div style="text-align: center;">
		<!-- <input id="viewToEdit" class="h_category_save" type="button" value="编辑"> -->
	</div>
	
	<script type="text/javascript">
		$(function(){

			$("#viewToEdit").click(function(){
				$('.h_bg').show();
				$('.h_categoryAdd').load("edit.htm?ID=${param.ID}");
				$('.h_categoryAdd').show();
				funShowDivCenter();
			});
		});
	</script>
</body>
</html>