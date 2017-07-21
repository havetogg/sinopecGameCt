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
		<span class="h_class_name">话题名</span>
		<span>${bean.NAME}</span>
	</div>
	<div>
            <span class="h_class_name">启用状态</span>
            <span>${bean.IN_USE_FLAG==1?'是':'否'}</span>
     </div>
	<div>
		<span class="h_class_name">发布时间</span>
		<span>${bean.PUBLISH_TIME}</span>
	</div>
	<div>
		<span class="h_class_name">内容</span>
		<span>${bean.CONTENT}</span>
	</div>
	<div>
		<span class="h_class_name">图片</span>
		  <img src="${contentPath }/${bean.CUTTED_PATH}"  style="width: 100px;height: 100px;" alt="">
	</div>
	<div>
		<span class="h_class_name">回复数</span>
		<span>${bean.REPLY_NUM}</span>
	</div>
	<div>
		<span class="h_class_name">点赞数</span>
		<span>${bean.PRAISE_NUM}</span>
	</div>
	<div>
		<span class="h_class_name">关注数</span>
		<span>${bean.CONCERN_NUM}</span>
	</div>
	<div>
		<span class="h_class_name">开始时间</span>
		<span>${bean.ONLINE_START_TIME}</span>
	</div>
	<div>
		<span class="h_class_name">结束时间</span>
		<span>${bean.ONLINE_END_TIME}</span>
	</div>
	<div>
		<span class="h_class_name">表示顺</span>
		<span>${bean.SHOW_ORDER}</span>
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