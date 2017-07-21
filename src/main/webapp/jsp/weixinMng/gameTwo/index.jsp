<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <c:set var="contentPath" value="<%=request.getContextPath()%>"></c:set>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1, user-scalable=no,width=320">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <!--基础js-->
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameTwo/js/jQuery-1.11.3.js" ></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameTwo/js/layout.js" ></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameTwo/js/common/common.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameTwo/js/common/m.tool.juxinbox.com.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jWeChat-1.0.0.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/share.js"></script>
    <!--滚动js-->
    <link rel="stylesheet" href="${contentPath }/jsp/weixinMng/gameTwo/css/liMarquee.css" />
    <script src="${contentPath }/jsp/weixinMng/gameTwo/js/jquery.liMarquee.js"></script>
    <!--页面js-->
    <link rel="stylesheet" href="${contentPath }/jsp/weixinMng/gameTwo/css/shake.css" />
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameTwo/js/shake.js" ></script>
    <title>中石化扭蛋</title>
</head>
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

</script>
<c:if test="${GAMETWOBEAN.know != 1}">
<!--引导页-->
<div class="guide">
	<img class="img" src="${contentPath }/jsp/weixinMng/gameTwo/img/guide.png" />
	<div class="driver">
		<input id="know" type="checkbox"/>
		<label>我是老司机，不再显示</label>
	</div>
	<div class="know" id="iknow">我知道了</div>
</div>
</c:if>
<!--loading-->
<div class="pop loading">
    <img class="show" src="${contentPath }/jsp/weixinMng/gameTwo/img/loading.png" />
</div>
<!--暴击了-->
<div class="pop impact">
    <div class="centerDiv">
        <h1>暴击啦</h1>
        <div class="bg">
            <label><span id="impact"></span>钻</label>
            <a class="btn close" href="javascript:;">返回</a>
        </div>
    </div>
</div>
<!--中奖了-->
<div class="pop win">
    <div class="centerDiv">
        <h1>中奖啦</h1>
        <div class="bg">
            <label class="prizeName"></label>
            <p>恭喜您抽得<span class="prizeName"></span>,请前往</p>
            <p>游戏中心“我的奖品”查看</p>
            <a class="btn again" href="javascript:;">再抽一次</a>
            <a class="btn close" href="javascript:;">返回</a>
        </div>
    </div>
</div>

<!--未中奖-->
<div class="pop lost">
    <div class="centerDiv">
        <h1>很遗憾</h1>
        <div class="bg">
            <label>谢谢参与</label>
            <p>未能抽中奖品，请再接再厉</p>
            <p>丰厚奖品等着您</p>
            <a class="btn again" href="javascript:;">再抽一次</a>
            <a class="btn close" href="javascript:;">返回</a>
        </div>
    </div>
</div>

<!--游戏规则-->
<div class="pop rule">
    <div class="centerDiv">
        <h1>规则说明</h1>
        <div class="bg">
			<div class="h420">
				<p>1.用户每天免费抽奖1次（当免费抽奖的冷却时间结束后，第一次抽奖不消耗钻石）</p>
				<p>2.每次抽奖需要消耗用户10钻</p>
				<p>连续抽奖5次不中，暴击能量满格点击<i></i>按钮，随机奖励钻石</p>
				<p>4.实物类奖品需要您配合填写邮寄信息，奖品将在中奖后10个工作日内寄出，邮费由有礼付承担</p>
				<p>5.中奖物品可以到我的奖品查看</p>
				<h2>游戏奖项</h2>
				<p>iphone 7s、10元加油红包，50元加油红包、随机钻石奖励</p>
				<label>以上抽奖规则最终解释权归有礼付所有</label>
			</div>
			<a class="btn close" href="javascript:;">我知道了</a>
		</div>
    </div>
</div>

<!--钻石不足-->
<div class="pop no_money">
    <div class="centerDiv">
        
        <h1>钻石不足</h1>
        <div class="bg">
        	<a class="close" href="javascript:;"></a>
            <img class="img" src="${contentPath }/jsp/weixinMng/gameTwo/img/no_money.png" />
            <p>钻石不足，可以充值后再来哟</p>
            <a class="btn" onclick="gotoCharge()">去充值</a>
        </div>
    </div>
</div>

<!--充值钻石-->
<div class="pop recharge">
    <div class="centerDiv">
        
        <h1>钻石充值</h1>
        <div class="bg">
        	<a class="close" href="javascript:;"></a>
            <img class="img" src="${contentPath }/jsp/weixinMng/gameTwo/img/recharge.png" />
            <p>是否立即前往充值中心？</p>
            <a class="btn" onclick="gotoCharge()">去充值</a>
        </div>
    </div>
</div>

<!--游戏主界面-->
<div class="wrapper shake">
    <!--活动说明按钮-->
    <div class="ruleButton"><img src="${contentPath }/jsp/weixinMng/gameTwo/img/ruleButton.png" /></div>
    <!--钻石-->
    <div class="jewel">
        <img class="diamond" src="${contentPath }/jsp/weixinMng/gameTwo/img/jewel.png" />
        <label id="remainDiamond">${USERBEAN.REMAIN_DIAMOND}</label>
        <img class="add" src="${contentPath }/jsp/weixinMng/gameTwo/img/add.png" />
    </div>
    <!--titile-->
    <img class="title" src="${contentPath }/jsp/weixinMng/gameTwo/img/title.png" />
    <!--中奖信息-->
    <ul class="prize">
        <c:forEach items="${prizeJSONArray}" var="obj" varStatus="status">
        <li>恭喜 ${obj.mobile} 获得${obj.prizeName}</li>
        </c:forEach>
    </ul>

    <!--奖品左-->
    <div class="prize_left">
        <div class="prizes1">
            <div class="tip">
                <img src="${contentPath }/jsp/weixinMng/gameTwo/img/prize1.png" />
                <p>iphone7</p>
            </div>
            <div class="tip">
                <img src="${contentPath }/jsp/weixinMng/gameTwo/img/prize2.png" />
                <p>加油红包</p>
            </div>
            <div class="tip">
                <img src="${contentPath }/jsp/weixinMng/gameTwo/img/prize3.png" />
                <p>加油红包</p>
            </div>
        </div>
    </div>

    <!--奖品右-->
    <div class="prize_right">
        <div class="prizes2">
            <div class="tip">
                <img src="${contentPath }/jsp/weixinMng/gameTwo/img/prize1.png" />
                <p>iphone7</p>
            </div>
            <div class="tip">
                <img src="${contentPath }/jsp/weixinMng/gameTwo/img/prize2.png" />
                <p>加油红包</p>
            </div>
            <div class="tip">
                <img src="${contentPath }/jsp/weixinMng/gameTwo/img/prize3.png" />
                <p>加油红包</p>
            </div>
        </div>
    </div>

    <!--机器-->
    <div class="machine">
        <!--机器玻璃-->
        <div class="top">
            <img class="glass" src="${contentPath }/jsp/weixinMng/gameTwo/img/glass.png" />
            <!--球-->
            <div class="ball">
                <img class="ball1" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball1.png" />
                <img class="ball2" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball1.png" />
                <img class="ball3" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball1.png" />
                <img class="ball4" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball1.png" />
                <img class="ball5" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball2.png" />
                <img class="ball6" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball2.png" />
                <img class="ball7" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball2.png" />
                <img class="ball8" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball2.png" />
                <img class="ball9" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball3.png" />
                <img class="ball10" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball3.png" />
                <img class="ball11" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball3.png" />
                <img class="ball12" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball3.png" />
                <img class="ball13" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball3.png" />
                <img class="ball14" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball4.png" />
                <img class="ball15" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball4.png" />
                <img class="ball16" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball4.png" />
                <img class="ball17" src="${contentPath }/jsp/weixinMng/gameTwo/img/ball4.png" />
            </div>
        </div>
        <!--机器身体-->
        <div class="middle">
        	<!--指针-->
			<img class="finger" src="${contentPath }/jsp/weixinMng/gameTwo/img/finger.png" />
            <!--能量条-->
            <div class="energy">
                <div class="energys">
                    <img src="${contentPath }/jsp/weixinMng/gameTwo/img/energy1.png" />
                    <img src="${contentPath }/jsp/weixinMng/gameTwo/img/energy2.png" />
                    <img src="${contentPath }/jsp/weixinMng/gameTwo/img/energy3.png" />
                    <img src="${contentPath }/jsp/weixinMng/gameTwo/img/energy4.png" />
                    <img src="${contentPath }/jsp/weixinMng/gameTwo/img/energy5.png" />
                </div>
                <!--暴击按钮-->
                <a class="hit" id="hit" href="javascript:;"></a>
            </div>
            <!--开始按钮-->
            <div class="start">
                <img src="${contentPath }/jsp/weixinMng/gameTwo/img/start.gif" />

                <%--<audio id="audio">
                    <source src="${contentPath }/jsp/weixinMng/gameTwo/css/music.mp3" type="audio/mpeg">
                </audio>--%>
                <audio id="audio" src="${contentPath }/jsp/weixinMng/gameTwo/css/music.mp3"></audio>
            </div>
            <!--装饰旋钮-->
            <img class="ornament" src="${contentPath }/jsp/weixinMng/gameTwo/img/ornament.png" />
            <!--球-->
            <!--<img class="ball" src="img/ball1.png" />-->
            <div class="ball"></div>
        </div>
    </div>
</div>
<input type="hidden" value="${GAMETWOBEAN.energyNum}" id="energyNum"/>
</body>
</html>

