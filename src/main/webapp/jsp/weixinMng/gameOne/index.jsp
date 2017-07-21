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
		<script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameOne/js/index.js" ></script>
		<script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameOne/js/common/common.js"></script>
	    <link type="text/css" href="${contentPath }/jsp/weixinMng/gameOne/css/common/common.css" rel="stylesheet">
	    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameOne/js/common/m.tool.juxinbox.com.js"></script>
		<script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jWeChat-1.0.0.js"></script>
    	<script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/share.js"></script>
		
		<title>加油达人</title>
	</head>
	<body>
		<div class="wrapper">
			<!--游戏开始页-->
			<div class="index">
				<!--钻石不足弹窗-->
				<div class="pop noDiamond">
					<div class="centerDiv">
						<a class="close" href="javascript:;"></a>
						<p>您的钻石不够啦</p>
						<p>请去大厅充值</p>
						<a onclick="noDiamondOk()" class="buy" href="javascript:;">确定</a>
					</div>
				</div>
				
				<!--游戏次数不足弹窗-->
				<div class="pop noTimes">
					<div class="centerDiv">
						<a class="close" href="javascript:;"></a>
						<p>今天的机会用完啦</p>
						<p>明天再来挑战吧</p>
						<a onclick="noTimesOk()" class="buy" href="javascript:;">确定</a>
					</div>
				</div>
				
				<!--购买成功弹窗-->
				<div class="pop success">
					<div class="centerDiv">
						<a class="close" href="javascript:;"></a>
						<p>购买成功!</p>
						<p>请尽情挑战吧!</p>
						<a onclick="buysucessOk()" class="buy" href="javascript:;">确定</a>
					</div>
				</div>
				
				<!--购买游戏机会弹窗-->
				<div class="pop buyTime">
					<div class="centerDiv">
						<a class="close" href="javascript:;"></a>
						<img class="logo" src="${contentPath }/jsp/weixinMng/gameOne/img/diamond2.png" />
						<p>购买一次挑战机会将消耗</p>
						<p id="buytimesmoneyid">100枚钻石，确认购买吗？</p>
						<a id="buytimesmoneypayid" class="buy" onclick="" href="javascript:;"></a>
					</div>
				</div>
				
				<!--排行榜弹窗-->
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

								<c:choose>
								<c:when test="${ status.index ==0}">
								   <td><img src="${contentPath }/jsp/weixinMng/gameOne/img/no1.png" /></td>
								</c:when>
								<c:when test="${ status.index ==1}">
								   <td><img src="${contentPath }/jsp/weixinMng/gameOne/img/no2.png" /></td>
								</c:when>
								<c:when test="${ status.index ==2}">
								   <td><img src="${contentPath }/jsp/weixinMng/gameOne/img/no3.png" /></td>
								</c:when>
								<c:otherwise>
								   <td>NO.${ status.index + 1}</td>
								</c:otherwise>
								</c:choose>
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
						<!--<dd>今日剩余</dd>
						<dt>
							<label>2次</label>
							<div class="game_add"></div>
						</dt>
						<dd>挑战机会</dd>-->
					</dl>
				</div>
				<div class="btn">
					<!--开始游戏-->
					<div class="tip">
						<a class="toGame" onclick="startGame(4)">
							<label>剩余<span>${GAMEONEBEAN.USERGAMETYPEMODE4.REMAIN_GAME_TIMES }</span>次</label>
						</a>
						<div class="game_add" onclick="buytimesmoneypay_qinag(4)" ></div>
					</div>
					
					<!--游戏说明-->
					<div class="tip">
						<a class="toExplain" onclick="startGame(5)">
							<label>剩余<span>${GAMEONEBEAN.USERGAMETYPEMODE5.REMAIN_GAME_TIMES }</span>次</label>
						</a>
						<div class="game_add" onclick="buytimesmoneypay_qinag(5)"></div>
					</div>
					
					<!--返回大厅-->
					<a class="toHome" onclick="gotoGameIndex()"></a>
				</div>
			</div>
		</div>
	</body>
</html>











