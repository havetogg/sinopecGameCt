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
		<span class="h_class_name">标题</span>
		<span>${bean.NAME}</span>
	</div>
	<div>
		<span class="h_class_name">备注</span>
		<span>${bean.REMARK}</span>
	</div>
	<div>
		<span class="h_class_name">第三方url</span>
		<span>${bean.NEWSURL}</span>
	</div>
	<div>
		<span class="h_class_name">作者名</span>
		<span>${bean.AUTHOR_NAME}</span>
	</div>
	<div>
		<span class="h_class_name">作者linK</span>
		<span>${bean.AUTHOR_LINK}</span>
	</div>
	<div>
		<span class="h_class_name">阅读基数</span>
		<span>${bean.CLICK_BASE}</span>
	</div>
	<div>
		<span class="h_class_name">阅读实际</span>
		<span>${bean.CLICK_RATE}</span>
	</div>
	<div>
		<span class="h_class_name">点赞基数</span>
		<span>${bean.DIANZ_BASE}</span>
	</div>
	<div>
		<span class="h_class_name">点赞实际</span>
		<span>${bean.DIANZ_NUM}</span>
	</div>
	
	
	<div>
		<span class="h_class_name">启用状态</span>
		<span>${bean.IN_USE_FLAG}</span>
	</div>
	<div>
		<span class="h_class_name">发布时间</span>
		<span>${bean.PUBLISH_TIME}</span>
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
		<span class="h_class_name">展示顺</span>
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