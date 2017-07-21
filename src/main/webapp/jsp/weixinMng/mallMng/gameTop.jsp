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

    </script>
</head>
<body>
<div class="zoomer">
    <div class="gameTop_banner">
        <img src="${contentPath }/${GAMEMNGMODE_BEAN.GAME_IMG_URL}" alt="" width="100%">
        <div class="text1">
            <label>
                ${GAMEMNGMODE_BEAN.GAME_NAME}上周排行
            </label>
        </div>
    </div>
    <div class="gameTopContent">
        <table>
            <thead>
            <tr>
                <td>排名</td>
                <td>玩家</td>
                <td>分数</td>
                <td>奖励</td>
            </tr>
            <tbody>
				 <c:forEach items="${RANKDETAILLIST}" var="obj" varStatus="status">
				       <tr>
				           <td>NO.${ status.index + 1}</td>
				           <td style="text-align: left;!important;    padding-left: 20px;">
				               <img src="${obj.MALLUSERMODE.HEAD_IMG}" class="icon11_img" alt="">
				               <label>${obj.MALLUSERMODE.NICK_NAME }</label>
				           </td>
				           <td>${obj.WEEK_SCORE }</td>
				           <td>
				               <img src="${contentPath }/jsp/weixinMng/mallMng/img/diamond.png" alt="">
				               <label>${obj.WEEK_GET_DIAMOND }</label>
				           </td>
				       </tr>
				</c:forEach>
            </tbody>
            </thead>
        </table>
    </div>
</div>
</body>
</html>