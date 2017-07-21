
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:set var="contentPath" value="<%=request.getContextPath() %>"></c:set>

    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=0.5,maximum-scale=0.5,minimum-scale=0.5, width=640, target-densitydpi=device-dpi">

    <meta http-eqiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <!--如果IE用户安装了Chrome Frame插件，则使用这个插件渲染页面，否则用IE最新的、最好的引擎来渲染页面-->
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>抽奖</title>
    <link type="text/css" href="${contentPath }/jsp/weixinDraw/css/common/common.css" rel="stylesheet">
    <!--<link type="text/css" href="css/index.css" rel="stylesheet">-->
    <!--<script type="text/javascript" src="js/common/jquery-1.7.2.min.js"></script>-->
    <!--<script type="text/javascript" src="js/common/m.tool.juxinbox.com.js"></script>-->

    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/jQuery-1.11.3.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/jWeChat-Adaptive.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/m.tool.juxinbox.com.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/jWeChat-1.0.0.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/123.js"></script>

    <!--<script type="text/javascript" src="js/index.js"></script>-->
    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/common.js"></script>
    <style>
    	*{
    		margin: 0;
    		padding: 0;
    	}
    	.contents{
    		width: 640px;
    		min-height: 1065px;
    		position: absolute;
    		background:url(${contentPath }/jsp/weixinDraw/img/succ-bg_02.png)no-repeat #c92a41;
    	}
    	.con{
    		width: 214px;
    		height: 214px;
    		margin: 290px auto 0px;
    	}
    	.btn{
    		width: 430px;
    		height: 60px;
    		margin: 210px auto;
    	}
    	.zhao img{
    		width: 640px;
    		height: 600px;
    		position: absolute;
    		margin-top: -426px;
    		opacity: 0;
    	}
    </style>
    
    <script type="text/javascript">
    	$(function(){
    		$(".btn").click(function(){
    			
    			
    			location.href=getRootPath()+"/weixinMng/ManageC/userIn.htm";
    		});
    		
    		
    	});
    	
    </script>
	</head>
	<body>
		<div class="zoomer">
			<div class="contents">
				<div class="con"><img src="${contentPath }/jsp/weixinDraw/img/17.png"></div>
				<div class="zhao"><img src="${contentPath}/jsp/weixinDraw/img/17.png"></div>
				<div class="btn"><img src="${contentPath }/jsp/weixinDraw/img/16.png"></div>
			</div>
		</div>
	</body>
</html>
