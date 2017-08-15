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
    <title>充值大厅</title>
    <link type="text/css" href="${contentPath }/jsp/weixinMng/mallMng/css/common/common.css" rel="stylesheet">
    <link type="text/css" href="${contentPath }/jsp/weixinMng/mallMng/css/app.css" rel="stylesheet">
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jQuery-1.11.3.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/common.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/index.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jWeChat-Adaptive.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/m.tool.juxinbox.com.js"></script>
       <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jWeChat-1.0.0.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/share.js"></script>
    <script>
        $(function () {
            $.ajax({
                url : "https://prod1.juxinbox.com/zsh.integral/api/v1/user/integral/1.htm",
                data : {
                    "token" : "jIY+H6bxNGDgilpzdK5KGBhG3tFesCc0mf8rvg0rJZsR1aBg6VxvjRJJLjuWJcU3C8dmxXRK84a6Qjs1CzqA/kBDtx/VFWVpuK5tL1nTkNM0B9fWvDT45YxvcpyF9EyMGeEl5RcarE08o7xtYRIFezu0jqA2pucErdCh15V+mAw="
                },
                dataType : "jsonp",
                type : "post",
                success : function(data) {
                    console.log(data);
                }
            });
        })
        function switchTab(type) {
            if(type==1){
                $('#tab1').attr('class','flex-1 selected');
                $('#tab2').attr('class','flex-1 noSelect');
                $('#diamonds').show();
                $('#coins').hide();
            }else if(type==2){
                $('#tab1').attr('class','flex-1 noSelect');
                $('#tab2').attr('class','flex-1 selected');
                $('#diamonds').hide();
                $('#coins').show();
            }
        }
        function closeTip1() {
            $('#tip1').hide();
        }
        function closeTip2() {
            $('#tip2').hide();
        }

        // 兑换金币
        function exchangeGoldBtn(id) {
			loading("start");
  				$.ajax({
  					//zanwu
  					url : getRootPath() + "/weixinMng/ManageC/rechargeGold.htm",
  					data : {
  						"gold_id" : id
  					},
  					dataType : "json",
  					type : "post",
  					success : function(data) {
  						loading("stop")
  						if (data.code==1) {
  							sessionStorage.setItem("need-refresh", true);
  							$('#tip1').show();
  						}else if(data.code==2){
  							$('#tip2').show();
  						}else {
  							alerw(data.msg);
  						}
  					}
  				});
        }
        
        function showRight() {
            $('#vipRight').show();
        }
        function closeRight() {
            $('#vipRight').hide();
        }
        
        
        // 钻石充值
        function exchangeBtn(id) {
        	window.location = getRootPath() + "/weixinMng/ManageC/toPayPage.htm?diamond_id="+id;
        }
    </script>
</head>
<body>

<!--vip特权-->
<div id="vipRight" class="game_block" style="display: none">

    <div class="vipRight_block">
        <div style="height: 60px;line-height: 60px;">
            <img src="${contentPath }/jsp/weixinMng/mallMng/img/vipTip.png" alt="" class="vipRight_tip_left">
            <img src="${contentPath }/jsp/weixinMng/mallMng/img/close.png" alt="" class="vipRight_tip_right" onclick="closeRight()">
        </div>
        <ul class="flex">
            <li class="flex-1 vipBlockBg">
                <div class="name">VIP LV1</div>
                <div class="tip_text">累计充值</div>
                <div class="values">60</div>
                <div class="tip_text">钻石</div>
            </li>
            <li class="vipBlock_rightli">
                <img src="${contentPath }/jsp/weixinMng/mallMng/img/blue_arrow.png" alt="">
            </li>
            <li class="flex-1 vipBlockBg">
                <div class="name">VIP LV2</div>
                <div class="tip_text">累计充值</div>
                <div class="values">300</div>
                <div class="tip_text">钻石</div>
            </li>
            <li class="vipBlock_rightli">
                <img src="${contentPath }/jsp/weixinMng/mallMng/img/blue_arrow.png" alt="">
            </li>
            <li class="flex-1 vipBlockBg">
                <div class="name">VIP LV3</div>
                <div class="tip_text">累计充值</div>
                <div class="values">600</div>
                <div class="tip_text">钻石</div>
            </li>
            <li class="vipBlock_rightli">
                <img src="${contentPath }/jsp/weixinMng/mallMng/img/blue_arrow.png" alt="">
            </li>
            <li class="flex-1 vipBlockBg">
                <div class="name">VIP LV4</div>
                <div class="tip_text">累计充值</div>
                <div class="values">1000</div>
                <div class="tip_text">钻石</div>
            </li>
            <li class="vipBlock_rightli">
                <img src="${contentPath }/jsp/weixinMng/mallMng/img/blue_arrow.png" alt="">
            </li>
            <li class="flex-1 vipBlockBg">
                <div class="name">VIP LV5</div>
                <div class="tip_text">累计充值</div>
                <div class="values">2500</div>
                <div class="tip_text">钻石</div>
            </li>
        </ul>
        <ul class="flex vipRight_ul">
            <li class="flex-1">
                <div class="flex">
                    <div class="vipIconImg">
                        <img src="${contentPath }/jsp/weixinMng/mallMng/img/vip1.png" alt="">
                    </div>
                    <div class="flex-1">
                        <div class="vipIconImg_text">每周返还</div>
                        <div>
                            <img src="${contentPath }/jsp/weixinMng/mallMng/img/coin.png" alt="" class="small_icon"><span class="small_iconText">10</span>
                            <img src="${contentPath }/jsp/weixinMng/mallMng/img/diamond.png" alt="" class="small_icon1"><span class="small_iconText">1</span>
                        </div>
                    </div>
                </div>
            </li>
            <li class="tip_shu"></li>
            <li class="flex-1">
                <div class="flex">
                    <div class="vipIconImg">
                        <img src="${contentPath }/jsp/weixinMng/mallMng/img/vip2.png" alt="">
                    </div>
                    <div class="flex-1">
                        <div class="vipIconImg_text">每周返还</div>
                        <div>
                            <img src="${contentPath }/jsp/weixinMng/mallMng/img/coin.png" alt="" class="small_icon"><span class="small_iconText">20</span>
                            <img src="${contentPath }/jsp/weixinMng/mallMng/img/diamond.png" alt="" class="small_icon1"><span class="small_iconText">2</span>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
        <ul class="flex vipRight_ul">
            <li class="flex-1">
                <div class="flex">
                    <div class="vipIconImg">
                        <img src="${contentPath }/jsp/weixinMng/mallMng/img/vip3.png" alt="">
                    </div>
                    <div class="flex-1">
                        <div class="vipIconImg_text">每周返还</div>
                        <div>
                            <img src="${contentPath }/jsp/weixinMng/mallMng/img/coin.png" alt="" class="small_icon"><span class="small_iconText">30</span>
                            <img src="${contentPath }/jsp/weixinMng/mallMng/img/diamond.png" alt="" class="small_icon1"><span class="small_iconText">5</span>
                        </div>
                    </div>
                </div>
            </li>
            <li class="tip_shu"></li>
            <li class="flex-1">
                <div class="flex">
                    <div class="vipIconImg">
                        <img src="${contentPath }/jsp/weixinMng/mallMng/img/vip4.png" alt="">
                    </div>
                    <div class="flex-1">
                        <div class="vipIconImg_text">每周返还</div>
                        <div>
                            <img src="${contentPath }/jsp/weixinMng/mallMng/img/coin.png" alt="" class="small_icon"><span class="small_iconText">40</span>
                            <img src="${contentPath }/jsp/weixinMng/mallMng/img/diamond.png" alt="" class="small_icon1"><span class="small_iconText">10</span>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
        <ul class="flex vipRight_ul">
            <li class="flex-1">
                <div class="flex">
                    <div class="vipIconImg">
                        <img src="${contentPath }/jsp/weixinMng/mallMng/img/vip5.png" alt="">
                    </div>
                    <div class="flex-1">
                        <div class="vipIconImg_text">每周返还</div>
                        <div>
                            <img src="${contentPath }/jsp/weixinMng/mallMng/img/coin.png" alt="" class="small_icon"><span class="small_iconText">50</span>
                            <img src="${contentPath }/jsp/weixinMng/mallMng/img/diamond.png" alt="" class="small_icon1"><span class="small_iconText">20</span>
                        </div>
                    </div>
                </div>
            </li>
            <li class="flex-1">

            </li>
        </ul>
    </div>
</div>
<div id="tip1" class="game_block" style="display: none">
    <div class="recharge_block">
        <div class="tip_contentDiv">
            <img src="${contentPath }/jsp/weixinMng/mallMng/img/nikeBlue.png" alt="">
        </div>
        <div class="tip_contentText">
            兑换成功！
        </div>
        <div>
            <img src="${contentPath }/jsp/weixinMng/mallMng/img/recharge_btn.png" alt="" onclick="closeTip1()">
        </div>
    </div>
</div>

<div id="tip2" class="game_block" style="display: none">
    <div class="recharge_block">
        <div class="tip_contentDiv1">

        </div>
        <div class="tip_contentText1">
            钻石不足，无法兑换
        </div>
        <div>
            <img src="${contentPath }/jsp/weixinMng/mallMng/img/recharge_btn.png" alt="" onclick="closeTip2()">
        </div>
    </div>
</div>
<div class="zoomer">
    <ul class="collections_bar flex">
       <li id="tab1" class="flex-1 selected" onclick="switchTab(1)">钻石充值</li>
        <li class="shu"></li>
       <li id='tab2' class="flex-1 noSelect" onclick="switchTab(2)">金币兑换</li>
    </ul>
    <div class="rechargeList">
        <ul id="diamonds">
            <div class="recharge_top_banner flex" onclick="showRight()">
                <div class="recharge_topBanner_level">LV${WxloginUser.USER_RANK_ID}</div>
                <div class="recharge_topBanner_levelContent">只要一次性充值60钻石，即可恢复到之前的最高等级</div>
                <div>
                    <img src="${contentPath }/jsp/weixinMng/mallMng/img/seeRight.png" alt="" class="recharge_topBanner_right">
                </div>
            </div>
            <div class="jmt_center" style="display:none">
                <img src="${contentPath }/jsp/weixinMng/mallMng/img/diamond_btn.png" alt="" width="95%">
            </div>
		    <c:forEach items="${CHANGEMNGLIST}" var="obj" varStatus="status">
				<c:choose>
				    <c:when test="${obj.YH_FLAG==1}">
                    <li class="flex rechargeList_bg">
                        <div class="flex-3 verticalCenter">
                            <img src="${contentPath }/jsp/weixinMng/mallMng/img/diamond.png" alt="" class="DiamondssssImg">
                            <label class="numbers">X${obj.DIAMOND_NUMB}</label>
                        </div>
                        <div class="shu"></div>
                        <div class="flex-5 center">
                            <div class="Diamondssss12">
                                <div class="Diamondssss_cut">优惠</div>
                                ￥${obj.PAY_MONEY}</div>
                            <img src="${contentPath }/jsp/weixinMng/mallMng/img/exchangeBtn.png" alt="" onclick="exchangeBtn('${obj.ID}')">
                        </div>
                        <c:if test="${status.index == 5}">
                            <div class="rechargeTip">
                                充值满100元，即送2张50元加油红包
                            </div>
                        </c:if>
                        <c:if test="${status.index == 6}">
                            <div class="rechargeTip">
                                充值满190元，即送3张50元加油红包
                            </div>
                        </c:if>
                        <c:if test="${status.index == 7}">
                            <div class="rechargeTip">
                                充值满300元，即送6张50元加油红包
                            </div>
                        </c:if>
                    </li>
                </c:when>
				    <c:otherwise>
                       <li class="flex rechargeList_bg">
			                <div class="flex-3 verticalCenter">
			                    <img src="${contentPath }/jsp/weixinMng/mallMng/img/diamond.png" alt="" class="DiamondssssImg">
			                    <label class="numbers">X${obj.DIAMOND_NUMB}</label>
			                </div>
			                <div class="shu"></div>
			                <div class="flex-5 center">
			                    <div class="Diamondssss1">￥${obj.PAY_MONEY}</div>
			                    <img src="${contentPath }/jsp/weixinMng/mallMng/img/exchangeBtn.png" alt="" onclick="exchangeBtn('${obj.ID}')">
			                </div>
			            </li>
				    </c:otherwise>
				</c:choose>

			</c:forEach>

        </ul>
        <ul id="coins" style="display: none;">
        
        	<c:forEach items="${CHANGEMNGLIST_GOLD}" var="obj" varStatus="status">
	            <li class="flex rechargeList_bg">
	                <div class="flex-3">
	                    <img src="${contentPath }/jsp/weixinMng/mallMng/img/coin.png" alt="" class="coinsss">
	                    <label class="numbers">X${obj.GOLD }</label>
	                </div>
	                <div class="shu"></div>
	                <div class="flex-5 center">
	                    <img src="${contentPath }/jsp/weixinMng/mallMng/img/diamond.png" alt="" class="DiamondssssImg">
	                    <div class="Diamondssss">${obj.PAY_DIAMOND_NUMB }</div>
	                    <img src="${contentPath }/jsp/weixinMng/mallMng/img/exchangeBtn.png" alt="" onclick="exchangeGoldBtn('${obj.ID}')">
	                </div>
	            </li>
	         </c:forEach>

        </ul>
        <div style="height: 30px;">

        </div>
    </div>
</div>
</body>
</html>