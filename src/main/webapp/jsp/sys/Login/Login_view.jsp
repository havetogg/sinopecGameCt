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
</head>
<body>
	<div class="fade_b"></div>
	<div class="fade_cc">
		<div class="qdxq1">查看t_user</div>
		<div class="top"></div>
		<p class="qdxx">
			ID：${bean.ID}
		</p>
		<p class="qdxx">
			NAME：${bean.NAME}
		</p>
		<div class="bj1">
			<div id="viewToEdit" class="bjleft fl">编&nbsp;&nbsp;&nbsp;&nbsp;辑</div>
			<div id="viewCloseDiv" class="bjright fr">关&nbsp;&nbsp;&nbsp;&nbsp;闭</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			
			$("#viewCloseDiv").click(function(){
				$(".fadebj").hide();
			});
			

			$("#viewToEdit").click(function(){
				$(".fadebj").load("edit.htm?ID=${param.ID}");
			});
		});
	</script>
</body>
</html>