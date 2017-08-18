<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
	<c:set var="contentPath" value="<%=request.getContextPath()%>"></c:set>
	    <meta charset="UTF-8">
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
		<meta name="viewport" content="initial-scale=0.5,maximum-scale=0.5,minimum-scale=0.5, user-scalable=no,width=640">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" href="${contentPath }/jsp/weixinMng/gameOne/css/game.css" />
		<script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameOne/js/jQuery-1.11.3.js" ></script>
		<script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameOne/js/layout.js" ></script>
		<script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameOne/js/common/common.js"></script>
	    <link type="text/css" href="${contentPath }/jsp/weixinMng/gameOne/css/common/common.css" rel="stylesheet">
	    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameOne/js/common/m.tool.juxinbox.com.js"></script>
		    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jWeChat-1.0.0.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/share.js"></script>
		<title>训练营-高手模式</title>
<script type="text/javascript">
var needRefresh = sessionStorage.getItem("need-refresh-game1");
if(needRefresh){
    //如果页面是刷新过来的,就强制返回首页
    window.location=getRootPath() + "/weixinMng/GameOneC/gameOneIn.htm";
}

//获取当前用户是否关闭 游戏操作提示页面
var GAME1KNOWFLAG ='${GAMEONEBEAN.GAME1KNOWFLAG}';

</script>

	<%--myr--%>
	<script >

		//当前游戏中是否参与秒杀
		var isExits = 0;
		//钻石
        var jeWelAll = 0;
		//红包
		var redAll = 0;
	</script>

	</head>


	<body>
		<%--<audio id="audio" src="http://tdev.juxinbox.com/sinopecGameCt/jsp/weixinMng/gameOne/css/music.mp3" loop="loop" autoplay="autoplay"></audio>--%>
		<audio id="audio" src="${contentPath }/jsp/weixinMng/gameOne/css/music.mp3" loop="loop" autoplay="autoplay"></audio>
		<div class="wrapper">
			<div class="game">
				<!--金币不足弹窗-->
				<div class="pop noGold">
					<div class="centerDiv">
						<a class="close" href="javascript:;"></a>
						<p>您的金币不够啦</p>
						<p>Tip: 本局收益将在游戏结束后结算</p>
						<a onclick="noGoldOk()" class="buy" href="javascript:;">确定</a>
					</div>
				</div>
				
				<!--开始动画-->
				<div class="pop Countdown">
					<!--倒计时-->
					<div class="centerDiv">
						<div class="num">3</div>
						<img src="${contentPath }/jsp/weixinMng/gameOne/img/go.png" />
					</div>
					<!--游戏引导-->
					<div class="introduce">
						<h1>玩法简介</h1>
						<div class="tip">
							<p>1.车辆会定时驶入加油站，停靠对应油号的加油机，完成加油操作后驶离并产生收益</p>
							<p>2.加油过程中出错将扣除一格血槽，血量为0时游戏结束；</p>
							<p>3.待加油车辆将在车位排队，如果造成加油站拥堵，游戏也就结束啦！</p>
						</div>
						<img class="img1" src="${contentPath }/jsp/weixinMng/gameOne/img/introduce1.png" />
						<img class="img2" src="${contentPath }/jsp/weixinMng/gameOne/img/introduce2.png" />
						<div class="next">下一步</div>
					</div>
					<div class="guide">
						<img class="tip1" src="${contentPath }/jsp/weixinMng/gameOne/img/tip1.png" />
						<img class="tip2" src="${contentPath }/jsp/weixinMng/gameOne/img/tip2.png" />
						<img class="tip3" src="${contentPath }/jsp/weixinMng/gameOne/img/tip3.png" />
						<img class="tip4" src="${contentPath }/jsp/weixinMng/gameOne/img/tip4.png" />
						<img class="tip5" src="${contentPath }/jsp/weixinMng/gameOne/img/tip5.png" />
						<img class="tip6" src="${contentPath }/jsp/weixinMng/gameOne/img/tip6.png" />
						<img class="tip7" src="${contentPath }/jsp/weixinMng/gameOne/img/tip7.png" />
						<div class="driver">
							<input type="checkbox"  id="know_checkid" />
							<label>我是老司机，不再显示</label>
						</div>
						<div class="know" id="IKnow_tip" >我知道了</div>
					</div>
				</div>
				
				<!--金币动画-->
				<div class="getGold">
					<div class="face">
						<img src="${contentPath }/jsp/weixinMng/gameOne/img/face.png" />
						<img src="${contentPath }/jsp/weixinMng/gameOne/img/face2.png" />
						<img class="on" src="${contentPath }/jsp/weixinMng/gameOne/img/face3.png" />
					</div>
					<img src="${contentPath }/jsp/weixinMng/gameOne/img/gold3.png" />
					<span id="goldNum">+100</span>
				</div>

				<!--钻石动画-->
				<div class="getJewel">
					<img src="${contentPath }/jsp/weixinMng/gameOne/img/jewel.png" />
				</div>

				<!--红包动画-->
				<div class="getRed">
					<img src="${contentPath }/jsp/weixinMng/gameOne/img/red_paper.png" />
						<%--<img src="${contentPath }/jsp/weixinMng/gameOne/img/gold2.png"/>--%>
				</div>

				<!--加油动画-->
				<!--<div class="pop guide">
					<div class="guide">
						<img class="tip1" src="${contentPath }/jsp/weixinMng/gameOne/img/tip1.png" />
						<img class="tip2" src="${contentPath }/jsp/weixinMng/gameOne/img/tip2.png" />
						<img class="tip3" src="${contentPath }/jsp/weixinMng/gameOne/img/tip3.png" />
						<img class="tip4" src="${contentPath }/jsp/weixinMng/gameOne/img/tip4.png" />
						<img class="tip5" src="${contentPath }/jsp/weixinMng/gameOne/img/tip5.png" />
						<img class="tip6" src="${contentPath }/jsp/weixinMng/gameOne/img/tip6.png" />
						<img class="tip7" src="${contentPath }/jsp/weixinMng/gameOne/img/tip7.png" />
						<div class="driver">
							<input id="know2" type="checkbox" />
							<label>我是老司机，不再显示</label>
						</div>
						<div class="know">我知道了</div>
					</div>
				</div>-->
				
				<!--购买车位弹窗-->
				<div class="pop buyPark">
					<div class="centerDiv">
						<a class="close" href="javascript:;"></a>
						<p>车位可以减缓车辆拥堵,是否购买？</p>
						<p>Tip: 本局收益将在游戏结束后结算</p>
						<div class="img">5</div>
						<a class="buy" href="javascript:;">
							<img src="${contentPath }/jsp/weixinMng/gameOne/img/gold2.png"/>
							<span>1000</span>
						</a>
					</div>
				</div>
				
				<!--购买加油机-->
				<div class="pop buyTank">
					<div class="centerDiv">
						<a class="close" href="javascript:;"></a>
						<p>加油机可以减缓车辆拥堵,是否购买？</p>
						<p>Tip: 本局收益将在游戏结束后结算</p>
						<div class="img">92#</div>
						<a class="buy" href="javascript:;">
							<img src="${contentPath }/jsp/weixinMng/gameOne/img/gold2.png"/>
							<span>1000</span>
						</a>
					</div>
				</div>
				
				<!--加油弹窗-->
				<div class="pop petrol">
					<div class="centerDiv">
						<!--关闭-->
						<!--<a class="close" href="javascript:;"></a>-->
						<!--游戏时间-->
						<div class="time"><span id="time">60</span>s</div>
						<!--游戏要求-->
						<div class="demand">
							<p>
								本次需加油
								<label>
									<span id="n1">50</span>
									<span id="n2">L</span>
								</label>
							</p>
							<!--定金额-->
							<div class="money">
								<label>金额</label>
								<div class="input"></div>
								<label>元</label>
							</div>
							<!--定升数-->
							<div class="bulk">
								<label>加油</label>
								<div class="input"></div>
								<label>升</label>
							</div>
						</div>
						<!--插卡取-->
						<div class="card">
							<div class="hint"></div>
						</div>
						<!--释放静电-->
						<a class="touch">释放静电</a>
						<!--键盘-->
						<table class="keyboard">
							<tr>
								<td data-num="1"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_1.png" /></td>
								<td data-num="2"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_2.png" /></td>
								<td data-num="3"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_3.png" /></td>
								<td class="toMoney">
									<img src="${contentPath }/jsp/weixinMng/gameOne/img/key_money.png" />
									<div class="hint"></div>
								</td>
							</tr>
							<tr>
								<td data-num="4"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_4.png" /></td>
								<td data-num="5"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_5.png" /></td>
								<td data-num="6"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_6.png" /></td>
								<td class="toBulk">
									<img src="${contentPath }/jsp/weixinMng/gameOne/img/key_bulk.png" />
									<div class="hint"></div>
								</td>
							</tr>
							<tr>
								<td data-num="7"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_7.png" /></td>
								<td data-num="8"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_8.png" /></td>
								<td data-num="9"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_9.png" /></td>
								<td><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_card.png" /></td>
							</tr>
							<tr>
								<td><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_whole.png" /></td>
								<td data-num="0"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_0.png" /></td>
								<td class="collate">
									<img src="${contentPath }/jsp/weixinMng/gameOne/img/key_enter.png" />
									<div class="hint"></div>
								</td>
								<td class="toBack">
									<img src="${contentPath }/jsp/weixinMng/gameOne/img/key_back.png" />
								</td>
							</tr>
						</table>
						<!--油表-->
						<div class="meter"><i></i></div>
						<!--长按加油-->
						<div class="press"></div>
					</div>
				</div>



				<!--游戏结束-->
				<div class="pop gameOver">
					<div class="centerDiv">
						<ul>
							<li>
								<label>获得收益</label>
								<span id="income">100</span>
							</li>
							<li>
								<label>加油车次</label>
								<span id="times">13</span>
							</li>
							<li>
								<label>完美加油</label>
								<span id="perfect">9</span>
							</li>
							<li>
								<label>步骤失误</label>
								<span id="fault">3</span>
							</li>
							<li>
								<label>得分</label>
								<span id="score">88100</span>
							</li>
							<li>
								<label>获得经验</label>
								<span id="exp">233</span>
							</li>
						</ul>

						<!--钻石/红包收益-->
						<div class="profit">
							<label>获得</label>
							<div class="jewel">
								<img src="${contentPath }/jsp/weixinMng/gameOne/img/jewel.png" />x
								<span id="jewel_span">0</span>
							</div>
							<div class="red">
								<img src="${contentPath }/jsp/weixinMng/gameOne/img/red_paper.png" />x
								<span id="red_span">0</span>
							</div>
						</div>

						<!--返回游戏首页-->
						<a class="playAgain" onclick="gotoGameOne()">
							<img src="${contentPath }/jsp/weixinMng/gameOne/img/playAgain.png" />
						</a>
						<!--返回游戏大厅-->
						<a class="toIndex" onclick="gotoGameIndex()">
							<img src="${contentPath }/jsp/weixinMng/gameOne/img/toIndex.png" />
						</a>
					</div>
				</div>



				<!--金币-->
				<div class="gold">${USERBEAN.REMAIN_GOLD }</div>
				<!--HP-->
				<div class="hp">
					<img src="${contentPath }/jsp/weixinMng/gameOne/img/hp.png" />
					<img src="${contentPath }/jsp/weixinMng/gameOne/img/hp.png" />
					<img src="${contentPath }/jsp/weixinMng/gameOne/img/hp.png" />
				</div>
				<!--建筑-->
				<div class="building">
					<img src="${contentPath }/jsp/weixinMng/gameOne/img/building1.png" />
					<img src="${contentPath }/jsp/weixinMng/gameOne/img/building2.png" />
				</div>
				<!--停车位-->
				<div class="park">
					<!--6号停车位/禁止停车-->
					<div class="tip stop">
						<div class="car">
							<div class="car92">92#</div>
							<div class="car95">95#</div>
							<div class="car98">98#</div>
						</div>
					</div>
					<!--5号停车位-->
					<c:choose>
						<c:when test="${GAMEONEBEAN.PARK_BUY>=3 }">
						  	<div id="park5" class="tip">
						</c:when>
						<c:otherwise>
						  <div id="park5" class="tip not_pay">
						</c:otherwise>
					</c:choose>
						<!--车/未购买车位不显示-->
						<div class="car">
							<div class="car92">92#</div>
							<div class="car95">95#</div>
							<div class="car98">98#</div>
						</div>
						<dl>
							<dt>车位</dt>
							<dd>
								<img class="add" src="${contentPath }/jsp/weixinMng/gameOne/img/park_add.png" />
								<span>5</span>
							</dd>
						</dl>
					</div>
					<!--4号停车位-->
					<c:choose>
						<c:when test="${GAMEONEBEAN.PARK_BUY>=2 }">
						  	<div id="park4" class="tip">
						</c:when>
						<c:otherwise>
						  <div id="park4" class="tip not_pay">
						</c:otherwise>
					</c:choose>
						<!--车/未购买车位不显示-->
						<div class="car">
							<div class="car92">92#</div>
							<div class="car95">95#</div>
							<div class="car98">98#</div>
						</div>
						<dl>
							<dt>车位</dt>
							<dd>
								<img class="add" src="${contentPath }/jsp/weixinMng/gameOne/img/park_add.png" />
								<span>4</span>
							</dd>
						</dl>
					</div>
					<!--3号停车位-->
					<c:choose>
						<c:when test="${GAMEONEBEAN.PARK_BUY>=1 }">
						  <div id="park3" class="tip">
						</c:when>
						<c:otherwise>
						  <div id="park3" class="tip not_pay">
						</c:otherwise>
					</c:choose>
						<!--车/未购买车位不显示-->
						<div class="car">
							<div class="car92">92#</div>
							<div class="car95">95#</div>
							<div class="car98">98#</div>
						</div>
						<dl>
							<dt>车位</dt>
							<dd>
								<img class="add" src="${contentPath }/jsp/weixinMng/gameOne/img/park_add.png" />
								<span>3</span>
							</dd>
						</dl>
					</div>
					<!--2号停车位-->
					<div id="park2" class="tip">
						<!--车-->
						<div class="car">
							<div class="car92">92#</div>
							<div class="car95">95#</div>
							<div class="car98">98#</div>
						</div>
						<dl>
							<dt>车位</dt>
							<dd>
								<img class="add" src="${contentPath }/jsp/weixinMng/gameOne/img/park_add.png" />
								2
							</dd>
						</dl>
					</div>
					<!--1号停车位-->
					<div id="park1" class="tip">
						<!--车-->
						<div class="car">
							<div class="car92">92#</div>
							<div class="car95">95#</div>
							<div class="car98">98#</div>
						</div>
						<dl>
							<dt>车位</dt>
							<dd>
								<img class="add" src="${contentPath }/jsp/weixinMng/gameOne/img/park_add.png" />
								1
							</dd>
						</dl>
					</div>
				</div>
				<!--油箱-->
				<div class="tanks">
					<div class="left">
						<div class="tank">
							<label>92#</label>
							<img class="game_begin" src="${contentPath }/jsp/weixinMng/gameOne/img/game_begin.png" />
							<img class="game_end" src="${contentPath }/jsp/weixinMng/gameOne/img/game_end.png" />
							<!--车-->
							<div class="car">
								<div class="car92"></div>
							</div>
						</div>
						<div class="tank">
							<label>95#</label>
							<img class="game_begin" src="${contentPath }/jsp/weixinMng/gameOne/img/game_begin.png" />
							<img class="game_end" src="${contentPath }/jsp/weixinMng/gameOne/img/game_end.png" />
							<!--车-->
							<div class="car">
								<div class="car95"></div>
							</div>
						</div>
						<div class="tank">
							<label>98#</label>
							<img class="game_begin" src="${contentPath }/jsp/weixinMng/gameOne/img/game_begin.png" />
							<img class="game_end" src="${contentPath }/jsp/weixinMng/gameOne/img/game_end.png" />
							<!--车-->
							<div class="car">
								<div class="car98"></div>
							</div>
						</div>
					</div>
					<div class="right">
					<c:choose>
						<c:when test="${GAMEONEBEAN.OIL_MACHINE_BUY>=1 }">
						  <div class="tank">
						</c:when>
						<c:otherwise>
						  <div class="tank  not_pay">
						</c:otherwise>
					</c:choose>
							<!--购买按钮-->
							<a class="add" href="javascript:;"></a>
							<label>92#</label>
							<img class="game_begin" src="${contentPath }/jsp/weixinMng/gameOne/img/game_begin.png" />
							<img class="game_end" src="${contentPath }/jsp/weixinMng/gameOne/img/game_end.png" />
							<!--车-->
							<div class="car">
								<div class="car92"></div>
							</div>
						</div>
					<c:choose>
						<c:when test="${GAMEONEBEAN.OIL_MACHINE_BUY>=2 }">
						  <div class="tank">
						</c:when>
						<c:otherwise>
						  <div class="tank  not_pay">
						</c:otherwise>
					</c:choose>
							<!--购买按钮-->
							<a class="add" href="javascript:;"></a>
							<label>95#</label>
							<img class="game_begin" src="${contentPath }/jsp/weixinMng/gameOne/img/game_begin.png" />
							<img class="game_end" src="${contentPath }/jsp/weixinMng/gameOne/img/game_end.png" />
							<!--车-->
							<div class="car">
								<div class="car95"></div>
							</div>
						</div>
					<c:choose>
						<c:when test="${GAMEONEBEAN.OIL_MACHINE_BUY>=3 }">
						  <div class="tank">
						</c:when>
						<c:otherwise>
						  <div class="tank  not_pay">
						</c:otherwise>
					</c:choose>
							<!--购买按钮-->
							<a class="add" href="javascript:;"></a>
							<label>98#</label>
							<img class="game_begin" src="${contentPath }/jsp/weixinMng/gameOne/img/game_begin.png" />
							<img class="game_end" src="${contentPath }/jsp/weixinMng/gameOne/img/game_end.png" />
							<!--车-->
							<div class="car">
								<div class="car98"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
<script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameOne/js/game.js" ></script>
</html>