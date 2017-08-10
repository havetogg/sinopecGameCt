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
		<script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/layout.js" ></script>
		<script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jQuery-1.11.3.js" ></script>
		<script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/common.js"></script>
		<script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/index.js"></script>
		<script type="text/javascript" src="${contentPath }/jsp/weixinMng/mallMng/js/common/jWeChat-Adaptive.js"></script>
		<link rel="stylesheet" href="${contentPath }/jsp/weixinMng/mallMng/css/exchange.css" />
		<title>兑换中心</title>
	</head>
	<script type="text/javascript">
		$(function () {
            //关闭弹窗
            $(".close").on("click",function(){
                $(this).parent().parent().fadeOut();
            })
			//兑换按钮
			$("#activate").bind("click",function () {
                $(".loading").fadeIn();
                    $.ajax({
                        url: getRootPath() + "/weixinMng/getPrize/activateCode.htm",
                        data: {"redeemCode": $("#code").val()},
                        dataType: "json",
                        type: "post",
                        success: function (data) {
                            $(".loading").fadeOut();
                            if(data.result){
                                exchangeSuccess(data.type);
                            }else{
                                exchangeFailure();
                            }
                        }
                    })
            })
        })

        //验证成功
        function exchangeSuccess(type){
		    if(type==0){
                window.location.href=getRootPath()+"/jsp/weixinMng/mallMng/exchangeSuccess.html";
			}else if(type==1){
                window.location.href=getRootPath()+"/jsp/weixinMng/mallMng/exchangeSuccess_oil.html";
            }

        }

        //验证不成功
        function exchangeFailure(){
            $(".failure").fadeIn();
        }
	</script>
	<body class="bg1 pt20">
		<!--loading-->
		<div class="pop loading">
			<img class="show" src="${contentPath }/jsp/weixinMng/mallMng/img/loading.png" />
		</div>
		<!--兑换失败-->
		<div class="pop failure">
			<div class="centerDiv">
				<h1>兑换失败</h1>
				<p>您的兑换码输入有误</p>
				<p>请核对后重新填写</p>
				<a class="close" href="javascritp:;">确 定</a>
			</div>
		</div>
		<!--主界面-->
		<div class="wrapper">
			<div class="exchange">
				<input type="text" placeholder="请输入兑换码" id="code"/>
				<p>请在此输入兑换码兑换奖品</p>
				<a class="toExchange" href="javascript:;" id="activate">兑 换</a>
			</div>
		</div>
	</body>
</html>
