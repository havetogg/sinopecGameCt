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
		<span class="h_class_name">广告图名称</span>
		<span>${bean.NAME}</span>
	</div>
	<div>
		<span class="h_class_name">广告图类型</span>
		<span>${bean.BANNER_TYPE==1?'微信端':'PC端'}</span>
	</div>
	<div>
            <span class="h_class_name">广告图</span>
            <img src="${contentPath }/${bean.IMAGE_URL}"  style="width: 100px;height: 100px;" alt="">
     </div>
	<div>
            <span class="h_class_name">广告图linkUrl</span>
            <span>${bean.LINK_URL}</span>
     </div>
	<div>
            <span class="h_class_name">启用状态</span>
            <span>${bean.IN_USE_FLAG==1?'是':'否'}</span>
     </div>
	<div>
		<span class="h_class_name">显示顺序</span>
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