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
    <title>我的消息</title>
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
    function getReward(obj) {
    	//sysmsgid="${obj.ID }" msgtype="${obj.TYPE }" msgmoney="${obj.MONEY }" onclick="getReward(this)"
	  	  var MOBILE = '${WxloginUser.MOBILE}';
		  if(jxTool.isNull(MOBILE)){
			  alerw("请先登录,再领取您的福利!");return;
		  }
    	  var sysmsgid=$(obj).attr("sysmsgid");
    	  var msgtype=$(obj).attr("msgtype");
    	  var msgmoney=$(obj).attr("msgmoney");
    	  var msggold=$(obj).attr("msggold");
    	  
    	   // 领取
    	   $.ajax({
	     		type : 'post',
	     		url : getRootPath() + "/weixinMng/ManageC/get_reward.htm",
                   data: {
                      "sysmsgid": sysmsgid
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
	     				$(obj).attr("onclick","");
	     				$(obj).empty();
	     				sessionStorage.setItem("need-refresh", true);
	     				
     					$(".tip_contentDiv2").empty();
	     				if(1==msgtype){
	     					//钻石
                            $(".tip_contentDiv2").append('<label class="diamond_bg"><label class="coin_diamond_value">'+msgmoney+'</label></label>');
	     				}else if (2==msgtype) {
	     					$(".tip_contentDiv2").append('<label class="coin_bg"><label class="coin_diamond_value">'+msggold+'</label></label>');
						}else{
                            $(".tip_contentDiv2").append('<label class="diamond_bg"><label class="coin_diamond_value">'+msgmoney+'</label></label>');
							$(".tip_contentDiv2").append('<label class="coin_bg"><label class="coin_diamond_value">'+msggold+'</label></label>');
	     				}
	     				
	     				$('#tip1').show();
	     				
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
    function closeTip() {
        $('#tip1').hide();
    }
    </script>
</head>
<body>
<div id="tip1" class="game_block" style="display: none">
    <div class="recharge_block">
        <div class="tip_contentText2">
            领取成功
        </div>
        <div class="tip_contentDiv2">
                <label class="diamond_bg">
                    <label class="coin_diamond_value">30</label>
                </label>
                <label class="coin_bg">
                    <label class="coin_diamond_value">300</label>
                </label>
        </div>
        <div>
            <img src="${contentPath }/jsp/weixinMng/mallMng/img/recharge_btn.png" alt="" onclick="closeTip()">
        </div>
    </div>
</div>
<div class="zoomer">
    <div class="gameCenter">
        <div class="systemMessages">
            <ul>
            <c:forEach items="${MSG_LIST}" var="obj" varStatus="status">
                <li>
                    <div class="flex">
                        <div class="flex-1">
                            <div>
                                <span class="tittle">${obj.MSG_TITLE }</span>
                            </div>
                            <div>
                                <span class="content">${obj.MSG_DETAIL }</span>
                            </div>
                        </div>
                        <div sysmsgid="${obj.ID }" msgtype="${obj.TYPE }" msgmoney="${obj.DIAMOND }" msggold="${obj.GOLD }" onclick="getReward(this)">
                           <c:if test="${empty obj.GET_TIME }">
                               <img src="${contentPath }/jsp/weixinMng/mallMng/img/getBtn.png" alt="" >
                           </c:if>
                        </div>
                    </div>
                    <div class="timeParent">
                        <span class="time">${obj.CREAT_TIME }</span>
                    </div>
                </li>
            </c:forEach>

            </ul>
            <!--没有消息-->
		<c:choose>
		    <c:when test="${MSG_LIST_SIZE==0}">
		        <div class="noMessages" style="display: block;">
		    </c:when>
		    <c:otherwise>
		       <div class="noMessages" style="display: none;">
		    </c:otherwise>
		</c:choose>
                <div class="jmt_center">
                    <img src="${contentPath }/jsp/weixinMng/mallMng/img/messageIcon.png" alt="">
                </div>

                <div class="noMessageDiv">
                    您还没有收到消息哦
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>