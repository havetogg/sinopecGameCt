<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
	<c:set var="contentPath" value="<%=request.getContextPath()%>"></c:set>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>错误信息</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<link rel="stylesheet" type="text/css" href="${contentPath }/csswx/css/sinopecGameCt-m.min.css">
	<link rel="stylesheet" type="text/css" href="${contentPath }/csswx/css/icon-font.css">

	<script type="text/javascript" src="${contentPath }/js/common/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="${contentPath }/js/My97DatePicker/WdatePicker.js"></script>
</head>
<style>
	.pic img{
		width:190px;
		height:133px;
		margin:30px 84px 0px;
	}
	p{
		font-size:15px;
		color:#888888;
		text-align: center;
		    font-weight: 700;
		    margin-top: 25px;
	}
</style>
<body>
	<div class="page-group">
		<div class="page">

			<!-- 这里是页面内容区 -->
			<div class="content">
			    <div class="pic"><img src="img/18.png"></div>
				<div class="content-block">
					<p>该功能需要从微信端进入!</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>