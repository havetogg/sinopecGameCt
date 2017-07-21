
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=0.5,maximum-scale=0.5,minimum-scale=0.5, width=640, target-densitydpi=device-dpi">
<c:set var="contentPath" value="<%=request.getContextPath() %>"></c:set>
    <meta http-eqiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <!--如果IE用户安装了Chrome Frame插件，则使用这个插件渲染页面，否则用IE最新的、最好的引擎来渲染页面-->
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>中国石化</title>
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
    <script type="text/javascript"></script>
    <style>
    	*{
    		margin: 0;
    		padding: 0;
    	}
    	.contents{
    		width: 640px;
    		background: #FFFFFF;
    		position: absolute;
    		min-height: 1065px;
    	}
    	.banner{
    		width: 640px;
    		height: 266px;
    		background: url(${contentPath }/jsp/weixinDraw/img/1_02.png);
    	}
    	.cont{
    		width: 640px;
    		position: absolute;
    	}
    	.con{
    		width: 640px;
    		height: 180px;
    		border-bottom: solid #cccccc 1px;
    	}
    	.li{
    		width: 580px;
    		height: 180px;
    		margin: 0 auto;
    	}
    	.li-pic{
    		width: 140px;
    		height: 140px;
    		margin-top: 20px;
    		float: left;
    	}
    	.li-pic img{
    		width: 140px;
    		height: 140px;
    	}
    	.li-title{
    		width: 420px;
    		height: 57px;
    		float: left;
    		text-align: left;
    		margin-left: 20px;
    		font-size: 24px;
    		margin-top: 20px;
    		line-height: 30px;
    		overflow: auto;
    	}
    	.li-con{
    		width: 420px;
    		float: left;
    		text-align: left;
    		margin-left: 20px;
    		font-size: 24px;
    		margin-top:10px;
    		color: #888888;
    		line-height: 30px;
    		overflow: hidden;
    		text-overflow: ellipsis;
    		white-space: nowrap;
    	}
    	.li-foot{
    		width: 420px;
    		float: left;
    		text-align: right;
    		margin-left: 20px;
    		font-size: 20px;
    		margin-top:20px;
    		color: #ffaa24;
    		letter-spacing: 1px;
    	}
    	.con_{
    		width: 640px;
    		height: 210px;
    		border-bottom: solid #cccccc 1px;
    	}
    	.li_{
    		width: 580px;
    		height: 180px;
    		margin: 0 auto;
    	}
    	.li-pic_{
    		width: 600px;
    		height: 100px;
    		margin-top: 20px;
    	}
    	.li-pic_ img{
    		width: 180px;
    		height: 100px;
    		padding-right: 17px;
    	}
    	
    	.li-title_{
    		width: 580px;
    		height: 37px;
    		font-size: 24px;
    		margin-top: 20px;
    		line-height: 30px;
    		overflow: auto;
    	}
    	
    	.li-foot_{
    		width: 580px;
    		text-align: right;
    		font-size: 20px;
    		margin-top:16px;
    		color: #ffaa24;
    		letter-spacing: 1px;
    	}
    	.list{
    		width: 640px;
    		height: 140px;
    		background: url(${contentPath }/jsp/weixinDraw/img/2_02.png);
    		border-bottom: solid #CCCCCC 1px;
    	}
    	.list-con{
    		width: 260px;
    		height: 70px;
    		color: #ff5224;
    		font-size: 30px;
    		margin-left: 27px;
    		font-weight: 900;
    		line-height: 95px;
    	}
    	.list-time{
    		height: 35px;
    		width: 250px;
    		border-radius: 5px;
    		line-height: 37px;
    		font-size: 20px;
    		color: #ffffff;
    		margin-left: 27px;
    		margin-top: 10px;
    		text-indent: 7px;
    		float: left;
    		background: #FFAA24;
    	}
    </style>
    <script type="text/javascript">
    
    function goDetailPage(id){
		
		 window.location = getRootPath()+"/weixinMng/WxNewsMng/newsDetail.htm?NEWSID="+id;
	}
    	
    	$(function(){
//     		$(".con").click(function(){
//     			location.href=getRootPath()+"/weixinMng/DrawMng/goDetailPage.htm";
//     		})
//     		$(".con_").click(function(){
//     			location.href=getRootPath()+"/weixinMng/DrawMng/goDetailPage.htm";
//     		});
    		
    		$(".list").click(function(){
    			location.href=getRootPath()+"/weixinMng/DrawMng/gotoDrawPage.htm";
    			
    		});
    		
    	});
    </script>
	</head>
	<body>
		<div class="zoomer">
			<div class="contents">
				<div class="banner"></div>
				<div class="list">
					<div class="list-con">加油红包&nbsp;&nbsp;每日必抢</div>
					<div class="list-time">每天10000份红包等你来拿</div>
				</div>
				<div class="cont">
					<c:forEach items="${NEWSLIST}"  var="obj" >
						<c:if test="${obj.WXNEWS_IMGMNGMODELIST.size()==1}">
						<div class="con" onclick="goDetailPage(${obj.ID})">
						<div class="li">
							<div class="li-pic"><img src="${contentPath }/${obj.WXNEWS_IMGMNGMODELIST[0].CUTTED_PATH }"></div>
							<div class="li-title">${obj.NAME }</div>
							<div class="li-con">${obj.REMARK }</div>
							<div class="li-foot">有礼付</div>
						</div>
					</div>
						</c:if>
					<c:if test="${obj.WXNEWS_IMGMNGMODELIST.size()==0}">
						<div class="con" onclick="goDetailPage(${obj.ID})">
						<div class="li">
<%-- 							<div class="li-pic"><img src="${contentPath }/${obj.WXNEWS_IMGMNGMODELIST[0].CUTTED_PATH }"></div> --%>
							<div class="li-title">${obj.NAME }</div>
							<div class="li-con">${obj.REMARK }</div>
							<div class="li-foot">有礼付</div>
						</div>
					</div>
						</c:if>
					<c:if test="${obj.WXNEWS_IMGMNGMODELIST.size()>1}">
						<div class="con" onclick="goDetailPage(${obj.ID})">
						<div class="li">
								<c:forEach items="${obj.WXNEWS_IMGMNGMODELIST}" var="objimg" varStatus="statusimg">
								<div class="li-pic_">
								<img class="con-l2-pic_" width="181" height="112" src="${contentPath }/${objimg.CUTTED_PATH}">
							</div>
						      </c:forEach>
							
							<div class="li-title">${obj.NAME }</div>
							<div class="li-con">${obj.REMARK }</div>
							<div class="li-foot">有礼付</div>
						</div>
						</div>
						</c:if>
					</c:forEach>
<!-- 					<div class="con"> -->
<!-- 						<div class="li"> -->
<%-- 							<div class="li-pic"><img src="${contentPath }/jsp/weixinDraw/img/4.png"></div> --%>
<!-- 							<div class="li-title">元宵猜灯谜，因一分钱洗车券！</div> -->
<!-- 							<div class="li-con">40000张随机送出！</div> -->
<!-- 							<div class="li-foot">汽车超人</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="con_"> -->
<!-- 						<div class="li_"> -->
<!-- 							<div class="li-title_">假期出游，享迪士尼等独家特惠约不约?</div> -->
<!-- 							<div class="li-pic_"> -->
<%-- 								<img src="${contentPath }/jsp/weixinDraw/img/4_03.png"> --%>
<%-- 								<img src="${contentPath }/jsp/weixinDraw/img/5_03.png"> --%>
<%-- 								<img src="${contentPath }/jsp/weixinDraw/img/3_03.png"> --%>
<!-- 							</div> -->
<!-- 							<div class="li-foot_">汽车超人</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="con"> -->
<!-- 						<div class="li"> -->
<%-- 							<div class="li-pic"><img src="${contentPath }/${obj.WXNEWS_IMGMNGMODELIST[0].CUTTED_PATH }"></div> --%>
<!-- 							<div class="li-title">人间秘境美cry！南京银杏湖乐园免费游</div> -->
<!-- 							<div class="li-con">200张免费门票等你来拿</div> -->
<!-- 							<div class="li-foot">中国石化</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					
				</div>
			</div>
		</div>
	</body>
</html>
