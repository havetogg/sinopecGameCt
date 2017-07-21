<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
	<c:set var="contentPath" value="<%=request.getContextPath()%>"></c:set>
	 <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=0.5,maximum-scale=0.5,minimum-scale=0.5, width=640, target-densitydpi=device-dpi">
    <meta http-eqiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>排行榜</title>
    <link type="text/css" href="${contentPath }/jsp/weixinMng/mallMng/css/common/common.css" rel="stylesheet">
    <link type="text/css" href="${contentPath }/jsp/weixinMng/mallMng/css/app.css" rel="stylesheet">
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jQuery-1.11.3.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/common.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/index.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jWeChat-Adaptive.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/fastclick.min.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/m.tool.juxinbox.com.js"></script> 
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jWeChat-1.0.0.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/share.js"></script>
    <script>
    
        function gotorankDetail(game_id){
        	window.location=getRootPath() + "/weixinMng/ManageC/rankDetail.htm?GAME_ID="+game_id;
        }
    
    </script>
</head>
<body>
<div class="zoomer">
    <div class="gameRank">
        <ul>
            <c:forEach items="${RANKLIST}" var="obj" varStatus="status">
	            <li onclick="gotorankDetail(${obj.ID })">
	                <div class="title">
	                    ${obj.GAME_NAME }
	                </div>
	                <div>
	                    <img src="${contentPath }/${obj.GAME_IMG_URL }" alt="" class="banner1">
	                </div>
	                <div class="title1">
	                    ${obj.GAME_DETAIL }
	                </div>
	                
					<c:choose>
					    <c:when test="${obj.USER_RANK_FLAG==1}">
					       <img src="${contentPath }/jsp/weixinMng/mallMng/img/ranktop.png" alt="" class="rankTip">
					    </c:when>
					    <c:otherwise>
					    </c:otherwise>
					</c:choose>
                </li>
             </c:forEach>   
            
<%--             <li>
                <div class="title">
                    加油超限赛
                </div>
                <div>
                    <img src="${contentPath }/jsp/weixinMng/mallMng/img/rank2.png" alt="" class="banner1">
                </div>
                <div class="title1">
                    加油超限赛一句话说明文字
                </div>
            </li>
            <li>
                <div class="title">
                    加油达人
                </div>
                <div>
                    <img src="${contentPath }/jsp/weixinMng/mallMng/img/rank1.png" alt="" class="banner1">
                </div>
                <div class="title1">
                    加油机操作员的日常
                </div>
            </li>
            <li>
                <div class="title">
                    加油达人
                </div>
                <div>
                    <img src="${contentPath }/jsp/weixinMng/mallMng/img/rank1.png" alt="" class="banner1">
                </div>
                <div class="title1">
                    加油机操作员的日常
                </div>
            </li> --%>
        </ul>
    </div>
</div>
</body>
</html>