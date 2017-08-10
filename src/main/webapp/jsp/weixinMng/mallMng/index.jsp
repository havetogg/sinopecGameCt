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
    <title>游戏中心</title>
    <link type="text/css" href="${contentPath }/jsp/weixinMng/mallMng/css/common/common.css" rel="stylesheet">
    <link type="text/css" href="${contentPath }/jsp/weixinMng/mallMng/css/app.css" rel="stylesheet">
    <link type="text/css" href="${contentPath }/jsp/weixinMng/mallMng/css/liMarquee.css" rel="stylesheet">
    <link type="text/css" href="${contentPath }/jsp/weixinMng/mallMng/css/flexslider.css" rel="stylesheet">

    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jQuery-1.11.3.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/jquery.flexslider-min.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/common.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/index.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jWeChat-Adaptive.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameOne/js/common/m.tool.juxinbox.com.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jWeChat-1.0.0.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/share.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/jquery.liMarquee.js"></script>
    <script src="https://s22.cnzz.com/z_stat.php?id=1263124033&web_id=1263124033" language="JavaScript"></script>
    <style>
        ::-webkit-input-placeholder{
            color: #68b7e9;
        }
    </style>
    <script>
	    $(function () {
            var MOBILE = '${WxloginUser.MOBILE}';
            if(jxTool.isNull(MOBILE)){
                denglu();      // 提示登录
            }
            var isPageHide = false;
            window.addEventListener('pageshow', function () {
                if (isPageHide) {
                    window.location.reload();
                }
            });
            window.addEventListener('pagehide', function () {
                isPageHide = true;
            });
            //中奖信息滚动
            $('.index_lunboText').liMarquee({
                hoverstop:false,
                drag: false,
            });
            $(".flexslider").flexslider({
                animation: "slide", //String: Select your animation type, "fade" or "slide"图片变换方式：淡入淡出或者滑动
                slideshowSpeed: 4000, //展示时间间隔ms
                animationSpeed: 1100, //滚动时间ms
                touch: true //是否支持触屏滑动
            });
	    });
        var num=0;
        var num1=0;
        function personInfo() {
           if(num==0){
               $('.index_tip1').show();
               num++;
           }
           else if(num==1){
               $('.index_tip1').hide();
               num--;
           }

        }
        function personInfoHide() {
            $('.index_tip1').hide();
        }
        function tip2() {
            if(num==0){
                $('.index_tip2').show();
                num++;
            }
            else if(num==1){
                $('.index_tip2').hide();
                num--;
            }
        }
        var countdown=60;
        function sendCode() {
          var tel =  $('#tel').val();
          if(!tel){
              TipShow('请输入手机号',1000);
          }
          else if(!jxTool.isMobile(tel)){
              TipShow('请输入正确的手机号',1000);
          }
          else{
              TipShow('发送短信',1000);
              settime();
		      $("#code").find(".card-txt").val("")
         	  loading("start");
	          $.ajax({                        //发送验证码
	          url: getRootPath() + "/weixinMng/ManageC/sendRegistCode.htm",             
	          data: {"tel":tel},
	          dataType: "json",
	          type: "post",
	          success: function (data) {
                if(-1==data.timeout){
              		// session已经过期了
              		window.location=getRootPath() + "/weixinMng/ManageC/userIn.htm"; 
              		return;
                }
	          	loading("stop");
		        if(data.code==1){
		        	
	          	}else{
	          		alert(data.msg);
	          		
	          	}
	          }
	         });
          }
        }
        function settime() {
            if (countdown == 0) {
                countdown = 60;
                $('#codeBtn').show();
                $('#code').hide();
            } else {
                $('#codeBtn').hide();
                $('#code').show();
                $('#code').html("重新发送(" + countdown + ")");
                countdown--;
                setTimeout(function() {
                    settime()
                },1000)
            }
        }
        function submitInfo() {
            var tel=$('#tel').val();
            var code=$('#codeNumber').val();
            if(!tel){
                TipShow('请输入手机号',1000);
            }
            else if(!jxTool.isMobile(tel)){
                TipShow('请输入正确的手机号',1000);
            }
             else if(!code){
                TipShow('请输入验证码',1000);
            }
            else{
   				loading("start");
   				$.ajax({
   					//zanwu
   					url : getRootPath() + "/weixinMng/ManageC/userLogin.htm",
   					data : {
   						"tel" : tel,
   						"code" : code
   					},
   					dataType : "json",
   					type : "post",
   					success : function(data) {
   						loading("stop")
   						if (data.code==1) {
                        var url=getRootPath() + "/weixinMng/ManageC/userIn.htm";
               		 	var form = $('<form id="submitForm" action="" method="POST">');
            			form.attr("action",url);
            			form.submit();
   						} else {
   							alerw(data.msg);
   						}
   					}
   				});
                
            }
        }
        function closeTipLogin() {
            $('#tip1').hide();
        }
        // 弹出登录
        function denglu(){
        	$("#tip1").css("display","block");
        }
        
        // 去充值
        function gotochange(){
        	  var MOBILE = '${WxloginUser.MOBILE}';
        	  if(jxTool.isNull(MOBILE)){
        		  denglu();      // 提示登录
        		  return;
        	  }
        	  window.location=getRootPath() + "/weixinMng/ManageC/rechargeIn.htm";
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
	     				$(obj).attr("src",'${contentPath }/jsp/weixinMng/mallMng/img/haveCollect.png');
	     				$(obj).attr("onclick","collectCommand_del(this)");
	     				// 收藏数加1
	     				var shoucNumb=$("#shoucNumb").html();
	     				$("#shoucNumb").html(Number(shoucNumb)+1);
	     				
	     				sessionStorage.setItem("need-refresh", true);
	     				//alerw(data.msg);
	     				
	     			}else{
	     				alerw(data.msg);
	     			}
	     		},
	             //调用出错执行的函数
	             error: function(XMLHttpRequest, textStatus, errorThrown){
	                   //请求出错处理
                     alerw("系统异常!"+textStatus);
	             } 
	     	});
        	
        }
        
        // 删除收藏
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
	     				$(obj).attr("src",'${contentPath }/jsp/weixinMng/mallMng/img/collectbtn.png');
	     				$(obj).attr("onclick","collectCommand(this)");
	     				// 收藏数加1
	     				var shoucNumb=$("#shoucNumb").html();
	     				$("#shoucNumb").html(Number(shoucNumb)-1);
	     				
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
        
        // 去看游戏
        function gotomyGame(){
            var MOBILE = '${WxloginUser.MOBILE}';
            if(jxTool.isNull(MOBILE)){
                denglu();      // 提示登录
                return;
            }
        	  window.location=getRootPath() + "/weixinMng/ManageC/myGame.htm";
        }
        
        // 查看排行
        function gotorank(){
            var MOBILE = '${WxloginUser.MOBILE}';
            if(jxTool.isNull(MOBILE)){
                denglu();      // 提示登录
                return;
            }
        	  window.location=getRootPath() + "/weixinMng/ManageC/rank.htm";
        }
        
        // 消息记录
        function gomsgList(){
        	var msgsize = '${MSGLIST_SIZE}';
        	/*if(msgsize>0){
        		sessionStorage.setItem("need-refresh", true);
        	}*/
        	window.location=getRootPath() + "/weixinMng/ManageC/msgList.htm";
        }
        
        // 去玩游戏
        function gotoGame(gameurl,id){
            var MOBILE = '${WxloginUser.MOBILE}';
            if(jxTool.isNull(MOBILE)){
                denglu();      // 提示登录
                return;
            }
            if(id == 3){
                window.location=gameurl;
            }else{
                window.location=getRootPath() + gameurl;
            }
        }
        //钻石商城
        function diamodMall() {
            var MOBILE = '${WxloginUser.MOBILE}';
            if(jxTool.isNull(MOBILE)){
                denglu();      // 提示登录
                return;
            }
        window.location.href='https://prodone.juxinbox.com/sinopecGameCt/weixinMng/activity/getOilDropReward.htm';
            <%--$('.mall_block_bg').show();--%>
        }
        //钻石商城隐藏
        function hideDiamodMall() {
        $('.mall_block_bg').hide();
        }
        //我的奖品
        function myPrize() {
            var MOBILE = '${WxloginUser.MOBILE}';
            if(jxTool.isNull(MOBILE)){
                denglu();      // 提示登录
                return;
            }
            window.location.href=getRootPath() + "/weixinMng/ManageC/prizeList.htm";
        }
    </script>
    <script type="text/javascript">
        var websocket = null;
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            var socketUrl = "wss:"+getRootPath().slice(7)+"/websocket"
            websocket = new WebSocket(socketUrl);
        }
        else {
            alert('当前浏览器 Not support websocket')
        }

        //连接发生错误的回调方法
        websocket.onerror = function () {
            console.log("WebSocket连接发生错误");
        };

        //连接成功建立的回调方法
        websocket.onopen = function () {
            console.log("WebSocket连接成功");
        }

        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            setMessageInnerHTML(event.data);
        }

        //连接关闭的回调方法
        websocket.onclose = function () {
            console.log("WebSocket连接关闭");
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            closeWebSocket();
        }

        //将消息显示在网页上
        function setMessageInnerHTML(innerHTML) {
            appendPrize(innerHTML);
        }

        //关闭WebSocket连接
        function closeWebSocket() {
            websocket.close();
        }
        $(function() {
            ad_tip2('show');
        });
    </script>
</head>
<body>
    <div id="tip1" class="game_block" style="display:none">
        <div class="index_login_block">
            <img src="${contentPath }/jsp/weixinMng/mallMng/img/close.png" alt="" class="index_login_close" onclick="closeTipLogin()">
            <div class="index_login_tip1">登录</div>
            <div class="index_login_tip2">登录即送10钻石</div>
            <div>
                <input id="tel" type="tel" class="index_login_input1" placeholder="请输入手机号" maxlength="11">
            </div>
            <div style="margin: 20px 0;">
                <input id="codeNumber" type="text" placeholder="请输入验证码" class="index_login_input2" maxlength="6">
                <span id="codeBtn" class="index_login_getCode_block" onclick="sendCode()">获取验证码</span>
                <span id="code" style="display: none;" class="index_login_getCode_block">获取验证码</span>
            </div>
            <div style="margin: 20px 0;">
                <img src="${contentPath }/jsp/weixinMng/mallMng/img/submitt.png" alt="" onclick="submitInfo()">
            </div>
        </div>
    </div>
    <div class="mall_block_bg">
        <div class="mall_block">
        <div class="mall_none">即将上线，敬请期待</div>
        <img src="${contentPath }/jsp/weixinMng/mallMng/img/recharge_btn.png" alt="" onclick="hideDiamodMall()">
        </div>
    </div>
<div class="zoomer">
    <ul class="flex index_banner_top">
        <li class="index_banner_top_personDiv" onclick="personInfo()">
                <div class="flex">
                    <div  style="width: 70px">
                        <img src="${WxloginUser.HEAD_IMG}" alt="" class="icon11_img">
                    </div>

				<c:choose>
				    <c:when test="${WxloginUser.USER_RANK_ID>0}">
				        <span class="flex-1 personInfo1">
				             <span class="index_banner_top_personDivVIPlevel">vip${WxloginUser.USER_RANK_ID}</span>
				             <span class="index_banner_top_personDivName">${WxloginUser.NICK_NAME}</span>
				        </span>
				    </c:when>
				    <c:otherwise>
				       <span class="flex-1 personInfo2">
				           <span class="index_banner_top_personDivName">${WxloginUser.NICK_NAME}</span>
				       </span>
				    </c:otherwise>
				</c:choose>

                </div>
                <div class="flex index_rangeDiv">
                    <div class="levelNumber">
                        LV${WxloginUser.USER_LEVEL_ID}
                    </div>
                    <div class="flex-1">
                        <div class="index_range_amount">${WxloginUser.USER_LEVEL_SCORE}/${WxloginUser.CURRENT_LEVEL_MAXSCORE}</div>
                        <div class="index_range">
                            <div class="index_rangeV" style="width: ${WxloginUser.USER_LEVEL_SCORE/WxloginUser.CURRENT_LEVEL_MAXSCORE*100}%"></div>
                        </div>
                    </div>

                </div>
                <div class="index_tip1">
                    <div class="index_tip_arrow"></div>
<!--                     <label>VIP每周有 钻石收益哦</label> -->
                    <label>当前经验${WxloginUser.USER_LEVEL_SCORE}/${WxloginUser.CURRENT_LEVEL_MAXSCORE}</label>
                    <label>玩游戏可以获取经验升级哟</label>
                </div>
        </li>
        <li style="width: 20px;"></li>
        <li class="index_diamond1">
            <img src="${contentPath }/jsp/weixinMng/mallMng/img/coin.png" alt="" class="img1">
            <label>${WxloginUser.ALL_GOLD-WxloginUser.USED_GOLD}</label>
        </li>
        <li style="width: 20px;"></li>
        <li class="index_diamond1">
            <img src="${contentPath }/jsp/weixinMng/mallMng/img/diamond.png" alt="" class="img2">
            <label>${WxloginUser.ALL_DIAMOND-WxloginUser.USED_DIAMOND}</label>
        </li>
        <li style="width: 20px;"></li>
    <li class="index_diamond" style="    font-size: 15px;line-height: 28px;width: 90px;" onclick="tip2()">
            个人中心
             <c:if test="${MSGLIST_SIZE>0||PRIZELIST_SIZE>0}">
                  <label></label>
			 </c:if>
            
            <div class="index_tip2">
                <div class="index_tip2_arrow"></div>
				<c:if test="${empty WxloginUser.MOBILE}">
				    <div style="border-bottom: 1px solid #cccccc;" onclick="denglu()">登陆送积分</div>
				</c:if>
                <div style="position: relative;" onclick="gomsgList()">
                	<c:if test="${MSGLIST_SIZE>0}">
                		 <span class="red_number_">${MSGLIST_SIZE}</span> 
				    </c:if>
                    我的消息
                </div>
                <div style="position: relative;border-top: 1px solid #cccccc;" onclick="myPrize()">
                    <c:if test="${PRIZELIST_SIZE>0}">
                        <span class="red_number_">${PRIZELIST_SIZE}</span>
                    </c:if>
                    我的奖品
                </div>
                <div style="border-top: 1px solid #cccccc;text-align: center;position: relative;" onclick="javascript:window.location.href='https://sms.linkgift.cn/giftpay_socket/loginIndexBase.htm'">
                    我的油库
                </div>
            </div>
        </li>
    </ul>
    <div class="gameTop_banner">
        <div class="flexslider">
            <ul class="slides">
                <li>
                    <img  src="${contentPath }/jsp/weixinMng/mallMng/img/index_banner2.png" alt="1" width="100%"/>
                </li>
                <li>
                    <img  src="${contentPath }/jsp/weixinMng/mallMng/img/index_banner3.png" alt="1" width="100%"/>
                </li>
                <li>
                    <img  src="${contentPath }/jsp/weixinMng/mallMng/img/index_banner.png" alt="1" width="100%"/>
                </li>
            </ul>
        </div>
    </div>
    <div class="gameCenterIndex">
        <ul class="index_lunboText">
            <c:forEach items="${prizeJSONArray}" var="obj" varStatus="status">
                <li>恭喜 ${obj.mobile} 获得${obj.prizeName}</li>
            </c:forEach>
        </ul>
        <ul class="flex" style="padding-top: 20px;">
            <li class="flex-1 jmt_center" onclick="gotochange()">
                <div><img src="${contentPath }/jsp/weixinMng/mallMng/img/index_icon4.png" alt="" class="icons_index"></div>
                <div class="icon_bg">
                    <div class="indexIcon_text1">充值大厅</div>
                    <div class="indexIcon_text2">限时优惠</div>
                </div>
            </li>
            <li class="flex-1 jmt_center" onclick="diamodMall()">
                <div><img src="${contentPath }/jsp/weixinMng/mallMng/img/index_icon3.png" alt="" class="icons_index"></div>
                <div class="icon_bg">
                <div class="indexIcon_text1">领油滴</div>
                <div class="indexIcon_text2">领100油滴</div>
                </div>
            </li>
            <li class="flex-1 jmt_center" onclick="gotomyGame()">
                <div><img src="${contentPath }/jsp/weixinMng/mallMng/img/index_icon2.png" alt="" class="icons_index"></div>
                <div class="icon_bg">
                    <div class="indexIcon_text1">我的游戏</div>
                    <div class="indexIcon_text2"><a id="shoucNumb">${GAMEMNGMODELIST_CONNECT_SIZE }</a>收藏</div>
                </div>
            </li>
            <li class="flex-1 jmt_center" onclick="gotorank()">
                <div><img src="${contentPath }/jsp/weixinMng/mallMng/img/index_icon1.png" alt="" class="icons_index"></div>
                <div class="icon_bg">
                    <div class="indexIcon_text1">排行榜</div>
                    <div class="indexIcon_text2"><a>${USERINRANK_SIZE }</a>入榜</div>
                </div>
            </li>
        </ul>
        <div class="gameRank">
            <ul>
             <c:forEach items="${GAMEMNGMODELIST}" var="obj" varStatus="status">
                <li>
                    <div class="title">
                        ${obj.GAME_NAME }
                    </div>
                    <div onclick="gotoGame('${obj.GAME_URL }','${obj.ID }')">
                        <img src="${contentPath }/${obj.GAME_IMG_URL }" alt="" class="banner1">
                    </div>
                    <div class="title1">
                          ${obj.GAME_DETAIL }
                    </div>
				<c:choose>
				    <c:when test="${obj.USER_COLLECTION_FLAG==1}">
				        <img src="${contentPath }/jsp/weixinMng/mallMng/img/haveCollect.png" alt=""  gameid="${obj.ID }" class="rankTip1" onclick="collectCommand_del(this)">
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
</div>
</body>
</html>