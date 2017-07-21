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
		<span class="h_class_name">NAME</span>
		<span>${bean.NAME}</span>
	</div>
	<div>
		<span class="h_class_name">IN_USE_FLAG</span>
		<span>${bean.IN_USE_FLAG}</span>
	</div>
	<div>
		<span class="h_class_name">PUBLISH_TIME</span>
		<span>${bean.PUBLISH_TIME}</span>
	</div>
	<div>
		<span class="h_class_name">CONTENT</span>
		<span>${bean.CONTENT}</span>
	</div>
	<div>
		<span class="h_class_name">ORIGINAL_URL</span>
		<span>${bean.ORIGINAL_URL}</span>
	</div>
	<div>
		<span class="h_class_name">CUTTED_PATH</span>
		<span>${bean.CUTTED_PATH}</span>
	</div>
	<div>
		<span class="h_class_name">REPLY_NUM</span>
		<span>${bean.REPLY_NUM}</span>
	</div>
	<div>
		<span class="h_class_name">PRAISE_NUM</span>
		<span>${bean.PRAISE_NUM}</span>
	</div>
	<div>
		<span class="h_class_name">CONCERN_NUM</span>
		<span>${bean.CONCERN_NUM}</span>
	</div>
	<div>
		<span class="h_class_name">ONLINE_START_TIME</span>
		<span>${bean.ONLINE_START_TIME}</span>
	</div>
	<div>
		<span class="h_class_name">ONLINE_END_TIME</span>
		<span>${bean.ONLINE_END_TIME}</span>
	</div>
	<div>
		<span class="h_class_name">CREATE_TIME</span>
		<span>${bean.CREATE_TIME}</span>
	</div>
	<div>
		<span class="h_class_name">LAST_MODIFY_TIME</span>
		<span>${bean.LAST_MODIFY_TIME}</span>
	</div>
	<div>
		<span class="h_class_name">SHOW_ORDER</span>
		<span>${bean.SHOW_ORDER}</span>
	</div>
	<div>
		<span class="h_class_name">DELETE_FLAG</span>
		<span>${bean.DELETE_FLAG}</span>
	</div>

	<div style="text-align: center;">
		<input id="viewToEdit" class="h_category_save" type="button" value="编辑">
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