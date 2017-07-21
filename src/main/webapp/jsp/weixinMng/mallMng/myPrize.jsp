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
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jQuery-1.11.3.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/common.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/index.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jWeChat-Adaptive.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/fastclick.min.js"></script>
    <script>

    </script>
</head>
<body>
<div class="zoomer">
    <div class="game_myPrize">
            <div class="game_myPrize_banner">
             <%--<img src="${contentPath }/jsp/weixinMng/mallMng/img/back_arrow.png" alt="">--%>
                我的奖品
            </div>
            <ul class="game_myPrize_noPrize">
                <img src="${contentPath }/jsp/weixinMng/mallMng/img/noPrize.png" alt="">
            </ul>
            <ul class="game_myPrize_Prize">

                <c:forEach items="${prizeArray}" var="obj" varStatus="status">

                    <li class="flex">
                        <div>
                            <img src="${contentPath }/jsp/weixinMng/mallMng/img/tel.png" alt="" class="goodsImg">
                        </div>

                        <c:choose>
                            <c:when test="${obj.isOut==0}">
                                <div class="flex-1 prize_info1" >
                                    <div class="prize_name">${obj.prizeName}<label class="Prize_expire">(已过期)</label></div>
                                    <div class="prize_name1">${obj.prizeName} </div>
                                    <div class="prize_limit">有效期：${obj.winningTime}~${obj.endTime} </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="flex-1 prize_info" >
                                    <div class="prize_name">${obj.prizeName}</div>
                                    <div class="prize_name1">${obj.prizeName} </div>
                                    <div class="prize_limit">有效期：${obj.winningTime}~${obj.endTime} </div>
                                </div>
                            </c:otherwise>
                        </c:choose>

                        <div>
                    <c:choose>
                        <c:when test="${obj.status==0&&obj.isOut==1}">
                            <img class="exchange" src="${contentPath }/jsp/weixinMng/mallMng/img/exchangeBtn2.png" alt="" onclick="javascript:window.location.href=getRootPath()+'/getPrize/exchangeRed.htm?prizeId=${obj.prizeId}&prizeRedeemId=${obj.prizeRedeemId}&imgUrl=${obj.imgUrl}&redeemCode=${obj.redeemCode}'">
                        </c:when>
                        <c:when test="${obj.status==0&&obj.isOut==0}">
                        </c:when>
                        <c:otherwise>
                            <img class="exchanged" src="${contentPath }/jsp/weixinMng/mallMng/img/exchanged.png" alt="">
                        </c:otherwise>
                    </c:choose>
                        </div>
                    </li>

                </c:forEach>

               <%-- <li class="flex">
                    <div>
                        <img src="img/tel.png" alt="" class="goodsImg">
                    </div>
                    <div class="flex-1 prize_info" >
                            <div class="prize_name">Iphone7</div>
                            <div class="prize_name1">Iphone7（64G）金色 </div>
                            <div class="prize_limit">有效期：2017/07/12~2017/07/31 </div>
                    </div>
                    <div>
                        <img class="exchange" src="img/exchangeBtn2.png" alt="">
                    </div>

                </li>
                <li class="flex">
                    <div>
                        <img src="img/tel.png" alt="" class="goodsImg">
                    </div>
                    <div class="flex-1 prize_info" >
                        <div class="prize_name">Iphone7</div>
                        <div class="prize_name1">Iphone7（64G）金色 </div>
                        <div class="prize_limit">有效期：2017/07/12~2017/07/31 </div>
                    </div>
                    <div>
                        <img class="exchanged" src="img/exchanged.png" alt="">
                    </div>
                </li>--%>

                <%--<li class="flex">
                    <div>
                        <img src="img/tel.png" alt="" class="goodsImg">
                    </div>
                    <div class="flex-1 prize_info1" >
                        <div class="prize_name">Iphone7 <label class="Prize_expire">(已过期)</label></div>
                        <div class="prize_name1">Iphone7（64G）金色 </div>
                        <div class="prize_limit">有效期：2017/07/12~2017/07/31 </div>
                    </div>
                    <div>

                    </div>
                </li>

                <li class="flex">
                    <div>
                        <img src="img/tel.png" alt="" class="goodsImg">
                    </div>
                    <div class="flex-1 prize_info1" >
                        <div class="prize_name">Iphone7 <label class="Prize_expire">(已过期)</label></div>
                        <div class="prize_name1">Iphone7（64G）金色 </div>
                        <div class="prize_limit">有效期：2017/07/12~2017/07/31 </div>
                    </div>
                    <div>

                    </div>
                </li>

                <li class="flex">
                    <div>
                        <img src="img/tel.png" alt="" class="goodsImg">
                    </div>
                    <div class="flex-1 prize_info1" >
                        <div class="prize_name">Iphone7 <label class="Prize_expire">(已过期)</label></div>
                        <div class="prize_name1">Iphone7（64G）金色 </div>
                        <div class="prize_limit">有效期：2017/07/12~2017/07/31 </div>
                    </div>
                    <div>

                    </div>
                </li>

                <li class="flex">
                    <div>
                        <img src="img/tel.png" alt="" class="goodsImg">
                    </div>
                    <div class="flex-1 prize_info1" >
                        <div class="prize_name">Iphone7 <label class="Prize_expire">(已过期)</label></div>
                        <div class="prize_name1">Iphone7（64G）金色 </div>
                        <div class="prize_limit">有效期：2017/07/12~2017/07/31 </div>
                    </div>
                    <div>

                    </div>
                </li>
                <li class="flex">
                    <div>
                        <img src="img/tel.png" alt="" class="goodsImg">
                    </div>
                    <div class="flex-1 prize_info1" >
                        <div class="prize_name">Iphone7 <label class="Prize_expire">(已过期)</label></div>
                        <div class="prize_name1">Iphone7（64G）金色 </div>
                        <div class="prize_limit">有效期：2017/07/12~2017/07/31 </div>
                    </div>
                    <div>

                    </div>
                </li>

                <li class="flex">
                    <div>
                        <img src="img/tel.png" alt="" class="goodsImg">
                    </div>
                    <div class="flex-1 prize_info1" >
                        <div class="prize_name">Iphone7 <label class="Prize_expire">(已过期)</label></div>
                        <div class="prize_name1">Iphone7（64G）金色 </div>
                        <div class="prize_limit">有效期：2017/07/12~2017/07/31 </div>
                    </div>
                    <div>

                    </div>
                </li>--%>

            </ul>
    </div>
</div>
</body>
</html>