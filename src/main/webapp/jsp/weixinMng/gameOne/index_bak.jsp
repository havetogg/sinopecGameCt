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
		<script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameOne/js/game.js" ></script>
		<script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameOne/js/common/common.js"></script>
	    <link type="text/css" href="${contentPath }/jsp/weixinMng/gameOne/css/common/common.css" rel="stylesheet">
	    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameOne/js/common/m.tool.juxinbox.com.js"></script>
		
		<title>加油达人</title>
	</head>
	<script type="text/javascript">
	
	
	
	</script>
	<body>
		<div class="wrapper">
			<!--游戏开始页-->
			<div class="index">
				<!--购买游戏机会弹窗-->
				<div class="pop buyTime">
					<div class="centerDiv">
						<a class="close" href="javascript:;"></a>
						<img class="logo" src="${contentPath }/jsp/weixinMng/gameOne/img/diamond2.png" />
						<p>购买一次挑战机会将消耗</p>
						<p id="buytimesmoneyid">100枚钻石，确认购买吗？</p>
						<a class="buy" onclick="buytimesmoneypay()" href="javascript:;"></a>
					</div>
				</div>
				
				<!--购买游戏机会弹窗-->
				<div class="pop chart_list">
					<div class="centerDiv">
						<a class="close" href="javascript:;"></a>
						<img class="logo" src="${contentPath }/jsp/weixinMng/gameOne/img/chart_list.png" />
						<table>
							<colgroup>
								<col width="15%" />
								<col width="40%" />
								<col width="22%" />
								<col width="23%" />
							</colgroup>
							<thead>
								<tr>
									<td>排名</td>
									<td>玩家</td>
									<td>分数</td>
									<td>奖励</td>
								</tr>
							</thead>
							<tbody>
 <c:forEach items="${GAMEONERANDLIST}" var="obj" varStatus="status">
								<tr>
									<td>NO.${ status.index + 1}</td>
									<td>
										<img src="${obj.MALLUSERMODE.HEAD_IMG }" />
										<span>${obj.MALLUSERMODE.NICK_NAME }</span>
									</td>
									<td>${obj.WEEK_SCORE }</td>
									<td>${obj.WEEK_GET_DIAMOND }</td>
								</tr>
</c:forEach>
				
							</tbody>
						</table>
					</div>
				</div>
				
				<div class="head">
					<!--动画-->
					<div class="talk">
						<img src="${contentPath }/jsp/weixinMng/gameOne/img/talk.png" />
					</div>
					<!--排行榜-->
					<a class="chart" href="javascript:;"></a>
					<!--金币-->
					<div class="gold">${USERBEAN.REMAIN_GOLD }</div>
					<!--钻石-->
					<div class="diamond">${USERBEAN.REMAIN_DIAMOND }</div>
					<dl class="info">
						<dd>今日剩余</dd>
						<dt>
							<label>${GAMEONEBEAN.USER_GAMEMNGMODE.REMAIN_GAME_TIMES }次</label>
							<div class="game_add" href="javascript:;"></div>
						</dt>
						<dd>挑战机会</dd>
					</dl>
				</div>
				<div class="btn">
					<!--开始游戏-->
					<a class="toPlay" href="javascript:;"></a>
					<!--游戏说明-->
					<a class="toExplain" href="javascript:;"></a>
					<!--返回大厅-->
					<a class="toHome" href="javascript:;"></a>
				</div>
				
			</div>
			<!--游戏开始页 end-->
			<div class="game">
				<!--购买车位弹窗-->
				<div class="pop buyPark">
					<div class="centerDiv">
						<a class="close" href="javascript:;"></a>
						<p>车位可以减缓车辆拥堵</p>
						<p>是否购买？</p>
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
						<p>加油机可以减缓车辆拥堵</p>
						<p>是否购买？</p>
						<div class="img"></div>
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
						<div class="card"></div>
						<!--释放静电-->
						<a class="touch">释放静电</a>
						<!--键盘-->
						<table class="keyboard">
							<tr>
								<td data-num="1"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_1.png" /></td>
								<td data-num="2"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_2.png" /></td>
								<td data-num="3"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_3.png" /></td>
								<td class="toMoney"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_money.png" /></td>
							</tr>
							<tr>
								<td data-num="4"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_4.png" /></td>
								<td data-num="5"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_5.png" /></td>
								<td data-num="6"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_6.png" /></td>
								<td class="toBulk"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_bulk.png" /></td>
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
								<td class="collate"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_enter.png" /></td>
								<td class="toBack"><img src="${contentPath }/jsp/weixinMng/gameOne/img/key_back.png" /></td>
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
						</ul>
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
</html>











