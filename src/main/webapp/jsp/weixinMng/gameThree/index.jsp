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
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameThree/js/jQuery-1.11.3.js" ></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameThree/js/layout.js" ></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameThree/js/common/common.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameThree/js/common/m.tool.juxinbox.com.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jWeChat-1.0.0.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/share.js"></script>
    <!--滚动js-->
    <link rel="stylesheet" href="${contentPath }/jsp/weixinMng/gameThree/css/liMarquee.css" />
    <script src="${contentPath }/jsp/weixinMng/gameThree/js/jquery.liMarquee.js"></script>
    <!--页面js-->
    <link rel="stylesheet" href="${contentPath }/jsp/weixinMng/gameThree/css/claw.css" />
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/gameThree/js/claw.js" ></script>
    <title>中石化抓娃娃</title>
</head>
<body>
    <c:if test="${T3USERGAMEMODE.know != 1}">
    <!--引导页-->
    <div class="guide">
        <h1>玩法简介</h1>
        <div class="text">
            <p>1.奖品成滚动状态，您可根据滚动位置判断能否成功抓取</p>
            <p>2.按下<i></i>进行夹娃娃游戏</p>
            <p>3.若连续5次没有抓取成功，左上角指针指向绿色区域，将获得随机补偿奖励</p>
            <p>4.夹娃娃游戏分为10、30、50钻场次，奖品价值依次递增</p>
        </div>
        <div class="driver">
            <input id="know" type="checkbox"/>
            <label>我是老司机，不再显示</label>
        </div>
        <div class="know" id="iknow">我知道了</div>
    </div>
    </c:if>
    <!--loading-->
    <div class="pop loading">
        <img class="show" src="${contentPath }/jsp/weixinMng/gameThree/img/loading.png" />
    </div>

    <!--暴击了-->
    <div class="pop impact">
        <div class="centerDiv">
            <h1>暴击啦</h1>
            <h2>恭喜您获得以下奖品</h2>
            <div class="text">
                <label><span>50</span>颗</label>
                <p>钻石</p>
            </div>
            <a class="btn close" href="javascript:;">返回</a>
        </div>
    </div>

    <!--中奖了-->
    <div class="pop win">
        <div class="centerDiv">
            <h1>中奖啦</h1>
            <h2>恭喜您获得以下奖品,请前往游戏中心“我的奖品”查看</h2>
            <div class="text">
                <label>50元</label>
                <p>加油红包</p>
            </div>
            <a class="btn close" href="javascript:;">返回</a>
        </div>
    </div>

    <!--未中奖-->
    <div class="pop lost">
        <div class="centerDiv">
            <h1>真遗憾</h1>
            <h2>未能抓中，非常感谢您的参与再接再厉</h2>
            <div class="text">
            </div>
            <a class="btn close" href="javascript:;">返回</a>
        </div>
    </div>

    <!--钻石不足-->
    <div class="pop no_money">
        <div class="centerDiv">
            <h1>钻石不足</h1>
            <h2>您的钻石不足<br>是否前往充值中心？</h2>
            <div class="text">
            </div>

            <a class="btn" href="javascript:gotoCharge();">去充值</a>
            <a class="btn close" href="javascript:;">返回</a>
        </div>
    </div>

    <!--钻石充值-->
    <div class="pop recharge">
        <div class="centerDiv">
            <h1>钻石充值</h1>
            <h2>是否立即前往充值中心？</h2>
            <div class="text">
            </div>

            <a class="btn" href="javascript:;">去充值</a>
            <a class="btn close" href="javascript:;">返回</a>
        </div>
    </div>

    <!--游戏规则-->
    <div class="pop rule">
        <div class="centerDiv">
            <h1>规则说明</h1>
            <p>1.夹娃娃每次抽奖消耗10、30、50钻石，每天不限抽奖次数</p>
            <p>2.连续抽奖5次不中，左上角油表满格，点击该区域，随机奖励钻石</p>
            <p>3.实物类奖品需要您配合填写邮寄信息，奖品将在中奖后10个工作日内寄出</p>
            <p>4.中奖物品可以到我的奖品查看</p>
            <label>游戏奖项：iphone 7s、10元加油红包，50元加油红包、随机钻石等奖励</label>
            <a class="btn close" href="javascript:;">我知道了</a>
        </div>
    </div>

    <div class="claw">
        <!--活动说明按钮-->
        <div class="ruleButton"><img src="${contentPath }/jsp/weixinMng/gameThree/img/ruleButton.png" /></div>
        <!--钻石-->
        <div class="jewel">
            <img class="diamond" src="${contentPath }/jsp/weixinMng/gameThree/img/jewel.png" />
            <label>${USERBEAN.REMAIN_DIAMOND}</label>
            <img class="add" src="${contentPath }/jsp/weixinMng/gameThree/img/add.png" />
        </div>
        <!--header-->
        <header>
            <!--初级-->
            <img class="junior" src="${contentPath }/jsp/weixinMng/gameThree/img/head1.png" />
            <!--中级-->
            <img class="medium" src="${contentPath }/jsp/weixinMng/gameThree/img/head2.png" />
            <!--高级-->
            <img class="senior" src="${contentPath }/jsp/weixinMng/gameThree/img/head3.png" />
        </header>
        <!--main-->
        <div class="main junior">
            <div class="left"></div>
            <div class="right"></div>
            <!--爪子-->
            <div class="arm">
                <div class="machine">
                    <span class="top"></span>
                    <div class="line_height">
                        <div class="line"></div>
                        <div class="handle">
                            <span class="origin"></span>
                            <span class="left_arm"></span>
                            <span class="right_arm"></span>
                        </div>
                        <ul class="hideCar">
                            <li>
                                <label>10元</label>
                                加油红包
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!--能量-->
            <div class="energy">
                <img class="img" src="${contentPath }/jsp/weixinMng/gameThree/img/energy.png" />
                <!--lost1 - lost5-->
                <img class="hand" src="${contentPath }/jsp/weixinMng/gameThree/img/hand.png" />
                <p>5次不中可获补偿</p>
            </div>
            <!--前景车-->
            <div class="before">
                <ul class="tip1">
                    <li class="car1">
                        <label>5颗</label>
                        钻石
                    </li>
                    <li class="car4">
                        <label>10元</label>
                        加油红包
                    </li>
                    <li class="car1">
                        <label>50</label>
                        油滴
                    </li>
                    <li class="car5">
                        <label>10元</label>
                        加油红包
                    </li>
                    <li class="car1">
                        <label>15颗</label>
                        钻石
                    </li>
                    <li class="car2">
                        <label>50元</label>
                        加油红包
                    </li>
                    <li class="car3">
                        <label>Beats</label>
                        耳机一副
                    </li>
                    <li class="car2">
                        <label>电子</label>
                        代金券
                    </li>
                </ul>
                <ul class="tip2">
                    <li class="car1">
                        <label>5颗</label>
                        钻石
                    </li>
                    <li class="car4">
                        <label>10元</label>
                        加油红包
                    </li>
                    <li class="car1">
                        <label>50</label>
                        油滴
                    </li>
                    <li class="car5">
                        <label>10元</label>
                        加油红包
                    </li>
                    <li class="car1">
                        <label>15颗</label>
                        钻石
                    </li>
                    <li class="car2">
                        <label>50元</label>
                        加油红包
                    </li>
                    <li class="car3">
                        <label>Beats耳机</label>
                        一副
                    </li>
                    <li class="car2">
                        <label>电子</label>
                        代金券
                    </li>
                </ul>
            </div>
            <!--后景车-->
            <div class="after">
                <ul class="tip1">
                    <li class="car1">
                        <label>5颗</label>
                        钻石
                    </li>
                    <li class="car4">
                        <label>10元</label>
                        加油红包
                    </li>
                    <li class="car1">
                        <label>50</label>
                        油滴
                    </li>
                    <li class="car5">
                        <label>10元</label>
                        加油红包
                    </li>
                    <li class="car1">
                        <label>15颗</label>
                        钻石
                    </li>
                    <li class="car2">
                        <label>50元</label>
                        加油红包
                    </li>
                    <li class="car3">
                        <label>Beats</label>
                        耳机一副
                    </li>
                    <li class="car2">
                        <label>电子</label>
                        代金券
                    </li>
                </ul>
                <ul class="tip2">
                    <li class="car1">
                        <label>5颗</label>
                        钻石
                    </li>
                    <li class="car4">
                        <label>10元</label>
                        加油红包
                    </li>
                    <li class="car1">
                        <label>50</label>
                        油滴
                    </li>
                    <li class="car5">
                        <label>10元</label>
                        加油红包
                    </li>
                    <li class="car1">
                        <label>15颗</label>
                        钻石
                    </li>
                    <li class="car2">
                        <label>50元</label>
                        加油红包
                    </li>
                    <li class="car3">
                        <label>Beats耳机</label>
                        一副
                    </li>
                    <li class="car2">
                        <label>电子</label>
                        代金券
                    </li>
                </ul>
            </div>
            <!--中奖信息-->
            <ul class="prize">
                <li>恭喜135****1234 获得iphone7手机一部</li>
                <li>恭喜135****1234 获得iphone7手机一部</li>
                <li>恭喜135****1234 获得iphone7手机一部</li>
                <li>恭喜135****1234 获得iphone7手机一部</li>
                <li>恭喜135****1234 获得iphone7手机一部</li>
                <li>恭喜135****1234 获得iphone7手机一部</li>
            </ul>
        </div>
        <!--footer-->
        <footer>
            <div class="btn1 cur">
                <img class="up" src="${contentPath }/jsp/weixinMng/gameThree/img/btn_10.png" />
                <img class="down" src="${contentPath }/jsp/weixinMng/gameThree/img/btn_10-2.png" />
            </div>
            <div class="btn1">
                <img class="up" src="${contentPath }/jsp/weixinMng/gameThree/img/btn_30.png" />
                <img class="down" src="${contentPath }/jsp/weixinMng/gameThree/img/btn_30-2.png" />
            </div>
            <div class="btn1">
                <img class="up" src="${contentPath }/jsp/weixinMng/gameThree/img/btn_50.png" />
                <img class="down" src="${contentPath }/jsp/weixinMng/gameThree/img/btn_50-2.png" />
            </div>
            <div class="btn2">
            </div>
        </footer>
    </div>
    <input type="hidden" value="${T3USERGAMEMODE.energyNum}" id="energyNum"/>
    <input type="hidden" value="${USERBEAN.REMAIN_DIAMOND}" id="remainDiamond"/>
</body>
</html>

