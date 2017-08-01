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
    <title>我的游戏</title>
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
        function switchTab(type) {
            if(type==1){
                $('#tab1').attr('class','flex-1 selected');
                $('#tab2').attr('class','flex-1 noSelect');
                $('#myCollection').show();
                $('#havePlay').hide();
            }else if(type==2){
                $('#tab1').attr('class','flex-1 noSelect');
                $('#tab2').attr('class','flex-1 selected');
                $('#myCollection').hide();
                $('#havePlay').show();
            }
        }
        function collectCommand(self) {
            TipShow('收藏！',1000);
        }
        
        // 添加收藏
        function collectCommand(obj){
      	     var gameid=$(obj).attr("gameid");
      	
             $.ajax({
	     		type : 'post',
	     		url : getRootPath() + "/weixinMng/ManageC/collect_add.htm",
                   data: {
                      "gameid": gameid
                   },
	     		dataType : 'json',
	     		timeout : 10000000, 
	             beforeSend: function () {
	                 loading("start");
	             },
	             complete: function (XMLHttpRequest) {
	                 loading("stop");
	             },
	     		success : function(data, textStatus) {
	               	if(-1==data.timeout){
	               		// session已经过期了
	               		alert("ajax-timeout");
	               		return;
	               	}
	     			if(data.code==1){
	     	        	var li_obj=$(obj).parent("li");
	     	        	$(li_obj).find("img").eq(1).attr("src","${contentPath }/jsp/weixinMng/mallMng/img/haveCollect.png").attr("onclick","collectCommand_del(this)");
	     	        	var mycollectionnew=li_obj.clone();
	     	        	$(mycollectionnew).find("img").eq(1).attr("onclick","collectdelete(this)");
	     	        	
	     	        	$("#myCollection").append(mycollectionnew);
	     				
	     				sessionStorage.setItem("need-refresh", true);
	     				//alerw(data.msg);
	     				
	     			}else{
	     				alerw(data.msg);
	     			}
	     		},
	             //调用出错执行的函数
	             error: function(XMLHttpRequest, textStatus, errorThrown){
	                   //请求出错处理
	                 alert("系统异常!"+textStatus);
	             } 
	     	});
        }
        
        // 已经收藏的那,点删除收藏
        function collectdelete(obj){
     	     var gameid=$(obj).attr("gameid");
             $.ajax({
	     		type : 'post',
	     		url : getRootPath() + "/weixinMng/ManageC/collect_del.htm",
                   data: {
                      "gameid": gameid
                   },
	     		dataType : 'json',
	     		timeout : 10000000, 
	             beforeSend: function () {
	                 loading("start");
	             },
	             complete: function (XMLHttpRequest) {
	                 loading("stop");
	             },
	     		success : function(data, textStatus) {
	               	if(-1==data.timeout){
	               		// session已经过期了
	               		alert("ajax-timeout");
	               		return;
	               	}
	     			if(data.code==1){
	     				$(obj).parent("li").remove();
	     				
	     				$("img[gameid="+gameid+"]").attr("src",'${contentPath }/jsp/weixinMng/mallMng/img/collectbtn.png').attr("onclick","collectCommand(this)");
	     				
	     				sessionStorage.setItem("need-refresh", true);
	     				//alerw(data.msg);
	     				
	     			}else{
	     				alerw(data.msg);
	     			}
	     		},
	             //调用出错执行的函数
	             error: function(XMLHttpRequest, textStatus, errorThrown){
	                   //请求出错处理
	                 alert("系统异常!"+textStatus);
	             } 
	     	});
        }

        // 我的游戏那点删除收藏
        function collectCommand_del(obj){
     	     var gameid=$(obj).attr("gameid");
             $.ajax({
	     		type : 'post',
	     		url : getRootPath() + "/weixinMng/ManageC/collect_del.htm",
                   data: {
                      "gameid": gameid
                   },
	     		dataType : 'json',
	     		timeout : 10000000, 
	             beforeSend: function () {
	                 loading("start");
	             },
	             complete: function (XMLHttpRequest) {
	                 loading("stop");
	             },
	     		success : function(data, textStatus) {
	               	if(-1==data.timeout){
	               		// session已经过期了
	               		alert("ajax-timeout");
	               		return;
	               	}
	     			if(data.code==1){
	     		
	     				$("img[gameid="+gameid+"]").not(obj).parent("li").remove();
	     				
	     				$(obj).attr("src",'${contentPath }/jsp/weixinMng/mallMng/img/collectbtn.png');
	     				$(obj).attr("onclick","collectCommand(this)");
	     				
	     				sessionStorage.setItem("need-refresh", true);
	     				//alerw(data.msg);
	     				
	     			}else{
	     				alerw(data.msg);
	     			}
	     		},
	             //调用出错执行的函数
	             error: function(XMLHttpRequest, textStatus, errorThrown){
	                   //请求出错处理
	                 alert("系统异常!"+textStatus);
	             } 
	     	});
        	
        }
        
        // 去玩游戏
        function gotoGame(gameurl){
        	window.location=getRootPath() + gameurl;
        }
        
    </script>
</head>
<body>
<div class="zoomer">
    <ul class="collections_bar flex">
       <li id="tab1" class="flex-1 selected" onclick="switchTab(1)">我收藏的</li>
        <li class="shu"></li>
       <li id='tab2' class="flex-1 noSelect" onclick="switchTab(2)">我玩过的</li>
    </ul>
    <div class="gameRank">
        <ul id="myCollection">
            <c:forEach items="${MYCOLLECTIONLIST}" var="obj" varStatus="status">
                <li>
                    <div class="title">
                        ${obj.GAME_NAME }
                    </div>
                    <div onclick="gotoGame('${obj.GAME_URL }')">
                        <img src="${contentPath }/${obj.GAME_IMG_URL }" alt="" class="banner1">
                    </div>
                    <div class="title1">
                          ${obj.GAME_DETAIL }
                    </div>
				<c:choose>
				    <c:when test="${obj.USER_COLLECTION_FLAG==1}">
				        <img src="${contentPath }/jsp/weixinMng/mallMng/img/haveCollect.png" alt="" gameid="${obj.ID }" class="rankTip1" onclick="collectdelete(this)">
				    </c:when>
				    <c:otherwise>
				       <img src="${contentPath }/jsp/weixinMng/mallMng/img/collectbtn.png" alt="" gameid="${obj.ID }" class="rankTip1" onclick="collectCommand(this)">
				    </c:otherwise>
				</c:choose>
                </li>
             </c:forEach>   
        </ul>

        
        <ul id="havePlay">

            <c:forEach items="${USERGAMEDLIST}" var="obj" varStatus="status">
                <li>
                    <div class="title">
                        ${obj.GAME_NAME }
                    </div>
                    <div onclick="gotoGame('${obj.GAME_URL }')">
                        <img src="${contentPath }/${obj.GAME_IMG_URL }" alt="" class="banner1">
                    </div>
                    <div class="title1">
                          ${obj.GAME_DETAIL }
                    </div>
				<c:choose>
				    <c:when test="${obj.USER_COLLECTION_FLAG==1}">
				        <img src="${contentPath }/jsp/weixinMng/mallMng/img/haveCollect.png" alt="" gameid="${obj.ID }" class="rankTip1" onclick="collectCommand_del(this)">
				    </c:when>
				    <c:otherwise>
				       <img src="${contentPath }/jsp/weixinMng/mallMng/img/collectbtn.png" alt="" gameid="${obj.ID }" class="rankTip1" onclick="collectCommand(this)">
				    </c:otherwise>
				</c:choose>
                </li>
             </c:forEach>   
        </ul>
        <div style="height: 30px;">

        </div>
    </div>
</div>
</body>
</html>