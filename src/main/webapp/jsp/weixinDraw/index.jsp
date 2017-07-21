<%@ include file="cs.jsp" %>
<%CS cs = new CS(1261675996);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
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
    <title>中石化合作伙伴专区</title>
    <link type="text/css" href="${contentPath }/jsp/weixinDraw/css/common/common.css" rel="stylesheet">
    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/jQuery-1.11.3.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/jWeChat-Adaptive.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/m.tool.juxinbox.com.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/jWeChat-1.0.0.js"></script>
<%--     <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/123.js"></script> --%>
    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/common.js"></script>

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
    		width: 170px;
    		height: 140px;
    		margin-top: 20px;
    		float: left;
    	}
    	.li-pic img{
    		width: 170px;
    		height: 140px;
    	}
    	.li-title{
    		width: 390px;
    		height: 57px;
    		float: left;
    		text-align: left;
    		margin-left: 20px;
    		font-size: 24px;
			font-weight:700;
    		margin-top: 20px;
    		line-height: 30px;
    		overflow: auto;
    	}
    	.li-con{
    		width: 390px;
    		float: left;
    		text-align: left;
    		margin-left: 20px;
    		font-size: 20px;
    		margin-top:10px;
    		color: #888888;
    		line-height: 30px;
    		overflow: hidden;
    		text-overflow: ellipsis;
    		white-space: nowrap;
    	}
    	.li-foot{
    		width: 390px;
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
			font-weight:700;
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
    		background: url(${contentPath }/jsp/weixinDraw/img/2_02.png) !important;
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
		.gif{
    		width: 140px;
    		height: 140px;
    		position: absolute;
    		margin-top: -70px;
    		margin-left: 500px;
    	}
    </style>
    <script type="text/javascript">
    
    function goDetailPage(id,url){
    	
    	if(!jxTool.isNull(url)){
    		window.location =url;
    	}else{
    		 window.location = getRootPath()+"/weixinMng/WxNewsMng/newsDetail.htm?NEWSID="+id;
    	}
    	var root=getRootPath();
    	
			$.ajax({
				
				url:root+'/weixinMng/WxNewsMng/updateClickRate.htm?NEWSID='+id,
				type:'post',
				dataType:'json',
				success:function(){}
				
			});
		
	}
    	
   	$(function(){
   		$(".list").click(function(){
   			
   			window.location=getRootPath()+"/weixinMng/DrawMng/gotoDrawPage.htm";
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
					<div class="list-time">限量红包和礼品等你來抢！</div>
					<div class="gif"><img src="${contentPath }/jsp/weixinDraw/img/gif.gif"></div>
				</div>
				<div class="cont">
			    <c:forEach items="${NEWSLIST}" var="obj" varStatus="status">
				     <c:if test="${obj.WXNEWS_IMGMNGMODELIST.size()==1}">
						<div class="con" onclick="goDetailPage('${obj.ID}','${obj.NEWSURL}')">
							<div class="li">
								<div class="li-pic"><img src="${contentPath }/${obj.WXNEWS_IMGMNGMODELIST[0].CUTTED_PATH }"></div>
								<div class="li-title">${obj.NAME }</div>
								<div class="li-con">${obj.REMARK }</div>
								<div class="li-foot"><fmt:formatDate pattern="yyyy-MM-dd" value="${obj.CREAT_TIME}" /></div>
							</div>
						</div>
					 </c:if>
					 <c:if test="${obj.WXNEWS_IMGMNGMODELIST.size()>1}">
						<div class="con_" onclick="goDetailPage('${obj.ID}','${obj.NEWSURL}')">
							<div class="li_">
								<div class="li-title_">${obj.NAME }</div>
								<div class="li-pic_">
								 <c:forEach items="${obj.WXNEWS_IMGMNGMODELIST}" var="objimg" varStatus="statusimg">
									<img src="${contentPath }/${objimg.CUTTED_PATH}">
							     </c:forEach>
								</div>
								<div class="li-foot"><fmt:formatDate pattern="yyyy-MM-dd" value="${obj.CREAT_TIME}" /></div>
							</div>
						</div>
	                  </c:if>
				</c:forEach>
					
				</div>
			</div>
		</div>
	</body>
<script type="text/javascript">
var openID = '${WxloginUser.OPEN_ID}';
</script>
<script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/share.js"></script>
</html>
