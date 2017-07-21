<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
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
    <script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/clip.js"></script>
    <script>
        $(function () {
            var codeText=$('.r_bg').html();
            $('.copyCodeBtn').on('click', function () {
                new Clipboard('.btn', {
                    text: function () {
                        return codeText.replace(/<br\/>/g, "");
                    }
                });
                setTimeout(function () {
                    TipShow('已复制',1000);
                }, 300);
            });

            //获取商品名字
            var prizeId = '${prizeId}';
            /*$.ajax({
                url: getRootPath() + "/weixinMng/ManageC/getPriceDetail.htm",
                data: {"prizeId": prizeId},
                dataType: "json",
                type: "post",
                success: function (data) {
                    $("#prizeName").html(data.prizeName);
                    $("#prizeName1").html(data.prizeName);
                }
            })*/
        });
    </script>
</head>
<body>
<div class="zoomer">
    <div class="game_myPrize">
            <div class="game_myPrize_banner">
             <!--<img src="img/back_arrow.png" alt="" onclick="hsBack()">-->
                兑换奖品
            </div>
            <ul class="game_myPrize_Prize">
                <li class="flex">
                    <div>
                        <img src="${contentPath }/jsp/weixinMng/mallMng/img/tel.png" alt="" class="goodsImg">
                    </div>
                    <div class="flex-1 prize_info" >
                            <div class="prize_name" id="prizeName">${prizeName}</div>
                            <div class="prize_name1" id="prizeName1">${prizeName}</div>
                            <div class="prize_limit">有效期：${winningTime}~${endTime} </div>
                    </div>
                    <div>
                    </div>
                </li>
            </ul>
        <div class="down_arrow">
            <img src="${contentPath }/jsp/weixinMng/mallMng/img/down_arrow.png" alt="">
        </div>
        <div class="w_bg">
                <div class="exchange_w_title">兑换码</div>
                <div class="r_bg" id="redeemCode">${redeemCode}</div>
                <div class="btn copyCodeBtn">
                    <img src="${contentPath }/jsp/weixinMng/mallMng/img/copyCode.png" alt="">
                </div>
                <div class="jmt_center"><img src="${contentPath }/jsp/weixinMng/mallMng/img/erCode.png" alt=""></div>
                <div class="exchange_w_title">长按二维码进入兑换中心</div>
                <ul class="exchange_rules">
                    <li>兑换规则:</li>
                    <li>1.复制兑换码后，长按二维码进入公众号</li>
                    <li>
                        2.打开公众号“我的红包—通用兑换”，输入兑换码，即可进行兑换
                    </li>
                </ul>
        </div>
    </div>
</div>
</body>
</html>