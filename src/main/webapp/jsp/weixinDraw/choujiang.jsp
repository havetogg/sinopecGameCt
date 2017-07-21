<%@ include file="cs.jsp" %>
<%CS cs = new CS(1261676026);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />

<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<c:set var="contentPath" value="<%=request.getContextPath()%>">
</c:set>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=0.5,maximum-scale=0.5,minimum-scale=0.5, width=640, target-densitydpi=device-dpi">

<meta http-eqiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<!--如果IE用户安装了Chrome Frame插件，则使用这个插件渲染页面，否则用IE最新的、最好的引擎来渲染页面-->
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<title>抽奖</title>
<link type="text/css"
	href="${contentPath }/jsp/weixinDraw/css/common/common.css"
	rel="stylesheet">

<script type="text/javascript"
	src="${contentPath }/js/common/jQuery-1.11.3.js"></script>
<script type="text/javascript"
	src="${contentPath }/js/common/jWeChat-Adaptive.js"></script>
<script type="text/javascript"
	src="${contentPath }/js/common/m.tool.juxinbox.com.js"></script>
<script type="text/javascript"
	src="${contentPath }/js/common/jWeChat-1.0.0.js"></script>
<!-- <script type="text/javascript" src="123.js"></script> -->
<link rel="stylesheet" href="${contentPath }/jsp/weixinDraw/css/flexslider.css" />
	<script src="${contentPath }/js/jquery.flexslider-min.js"></script>
<script type="text/javascript"
	src="${contentPath }/jsp/weixinDraw/js/common/common.js"></script>
<style>
* {
	margin: 0;
	padding: 0;
}

.contents {
	width: 640px;
	min-height: 1065px;
	background: url(${contentPath }/jsp/weixinDraw/img/bg_02.png) no-repeat
		#4f1d6f;
	position: absolute;
}

.head {
	width: 610px;
	height: 71px;
	background: url(${contentPath }/jsp/weixinDraw/img/10_02.png);
	margin: 0 15px;
}

.jindutiao {
	width: 260px;
	height: 23px;
	border: solid #eaeaea 2px;
	border-radius: 50px;
	margin: 23px 15px;
	float: left;
	background: #212121;
}

.green {
	position: absolute;
	/*width: 260px;*/
	height: 23px;
	background: #52b410;
	border-radius: 50px;
	margin: 25px 17px;
}

.money {
	position: absolute;
	color: white;
	text-align: center;
	font-size: 18px;
	margin: 25px 17px;
	z-index: 9999;
	width: 260px;
	height: 23px;
}

#maxmoney {
	color: white;
	font-size: 18px;
}

#minmoney {
	color: white;
	font-size: 18px;
}
.duihuan-button{
    		width: 152px;
    		height: 44px;
    		float: left;
    		margin-left: 15px;
    		margin-top: 9px;
    		background: url(${contentPath }/jsp/weixinDraw/img/9_.png);
    	}
.duihuan-button_{
    		width: 152px;
    		height: 44px;
    		float: left;
    		margin-left: 15px;
    		margin-top: 9px;
    		background:url(${contentPath }/jsp/weixinDraw/img/9.png);
    	}

.rules {
	width: 103px;
	height: 83px;
	float: left;
	margin-left: 23px;
}

.redbox {
	width: 640px;
	height: 30px;
	text-align: center;
	margin-top: 160px;
	color: white;
	font-size: 20px;
}

.redbox span {
	font-size: 20px;
	color: white;
}

.num {
	width: 330px;
	height: 51px;
	background: url(${contentPath }/jsp/weixinDraw/img/11_03.png);
	margin: 20px auto 0px;
	text-align: center;
	font-size: 24px;
	color: white;
	line-height: 52px;
}

.num span {
	font-size: 30px;
	color: #ffe026;
}

.cont {
	width: 606px;
	height: 536px;
	background: url(${contentPath }/jsp/weixinDraw/img/14.png);
	margin: -5px auto 0px;
}

table {
	position: absolute;
	margin: 34px 15px;
}

#lottery {
	width: 570px;
	height: 510px;
	margin: 0px auto;
}

.lottery-unit {
	width: 182px;
	height: 156px;
}
/*#lottery table{background-color:yellow;}*/
#lottery table td {
	position: relative;
	width: 170px;
	height: 140px;
	text-align: center;
	color: #333;
	font-index: -999
}

#lottery table td img {
	display: block;
	width: 170px;
	height: 140px;
	padding: 5px;
}

#lottery table td a {
	width: 170px;
	height: 140px;
	display: block;
	text-decoration: none;
	position: absolute;
	margin-top: 5px;
	background: url(${contentPath}/jsp/weixinDraw/img/start_02.png)
		no-repeat;
	margin-left: 5px;
}

#lottery table td a:hover {
	background-image: url(${contentPath}/jsp/weixinDraw/img/start_02.png);
}

#lottery table td.active .mask {
	display: block;
}

.mask {
	width: 100%;
	height: 100%;
	position: absolute;
	left: 5px;
	top: 5px;
	background: url(${contentPath }/jsp/weixinDraw/img/1-1_02.png) no-repeat;
	opacity: 0.7;
	display: none;
}

.list {
	width: 640px;
	height: 469px;
	position: absolute;
	background: url(${contentPath }/jsp/weixinDraw/img/11_10.png) no-repeat;
}

.con {
	width: 570px;
	height: 225px;
	background: #3f1759;
	margin:5px 35px 0px;
	position: absolute;
	overflow: hidden;
}

.li {
	width: 475px;
	height: 20px;
	margin: 30px 47px 30px;
}

.name {
	color: white;
	width: 235px;
	font-size: 24px;
	text-align: left;
	float: left;
}

.sca {
	width: 240px;
	text-align: right;
	float: left;
	font-size: 24px;
	color: white;
}

p {
	font-size: 24px;
	width: 430px;
	margin-left: 55px;
	height: 102px;
	margin-top: 66px;
	color: #888888;
	line-height: 50px;
}

p span {
	font-size: 24px;
	color: #e2380a;
}

.tip1-button {
	width: 430px;
	height: 60px;
	background: #ffb018;
	border-radius: 50px;
	text-align: center;
	color: white;
	font-size: 24px;
	line-height: 60px;
	margin: 36px auto;
}

.p2 {
	font-size: 24px;
	width: 430px;
	margin-left: 55px;
	height: 102px;
	margin-top: 66px;
	color: #888888;
	text-align: center;
	line-height: 50px;
}

.p2 span {
	font-size: 24px;
	color: #e2380a;
}

.btns {
	width: 430px;
	height: 60px;
	margin: 25px auto;
}

.btn1 {
	width: 210px;
	height: 60px;
	background: #dbdbdb;
	border-radius: 50px;
	float: left;
	font-size: 24px;
	text-align: center;
	line-height: 60px;
	color: #888888;
}

.btn2 {
	width: 210px;
	height: 60px;
	background: #ffb018;
	border-radius: 50px;
	float: left;
	font-size: 24px;
	text-align: center;
	line-height: 60px;
	margin-left: 10px;
	color: white;
}

.tel {
	width: 100px;
	height: 40px;
	font-size: 24px;
	margin-left: 55px;
	margin-top: 50px;
	float: left;
}

.tel-txt {
	width: 317px;
	height: 60px;
	border: solid 1px #888888;
	border-radius: 5px;
	margin-top: 28px;
	text-indent: 10px;
	font-size: 24px;
}

.card {
	width: 100px;
	height: 40px;
	font-size: 24px;
	margin-left: 55px;
	margin-top: 50px;
	float: left;
	position:absolute;
}

.card-txt {
	width: 150px;
	height: 60px;
	border: solid 1px #888888;
	margin-top: 25px;
	border-radius: 5px;
	text-indent: 10px;
	font-size: 24px;
	float: left;
}

.card-button {
	width: 150px;
	height: 60px;
	border-radius: 5px;
	background: #333333;
	float: left;
	margin-top: 25px;
	margin-left: 17px;
	color: white;
	font-size: 24px;
	line-height: 60px;
	text-align: center;
}

.tip6-button {
	width: 430px;
	height: 60px;
	background: #ffb018;
	border-radius: 50px;
	text-align: center;
	color: white;
	font-size: 24px;
	line-height: 60px;
	margin: 139px auto;
}
.tip7-button {
	width: 430px;
	height: 60px;
	background: #ffb018;
	border-radius: 50px;
	text-align: center;
	color: white;
	font-size: 24px;
	line-height: 60px;
	margin: 139px auto;
}

.close {
	width: 52px;
	height: 52px;
	position: absolute;
	margin-left: 502px;
	margin-top: -20px;
}

.rules-title {
	width: 100%;
	height: 114px;
	color: white;
	font-size: 30px;
	text-align: center;
	line-height: 167px;
	margin: 0 auto;
	float: left;
}

.rules-close {
	width: 10px;
	height: 10px;
	color: white;
	font-size: 40px;
	position: absolute;
	line-height: 167px;
	margin-left: 88%;
}

.rules-list {
	width: 90%;
	height: 100px;
	margin-left: 5%;
	margin-top: 26%;
}

.act-title {
	width: 140px;
	height: 40px;
	background: url(${contentPath }/jsp/weixinDraw/img/15.png);
	margin-top: 20px;
	position: absolute;
	font-size: 24px;
	color: white;
	text-align: center;
	line-height: 40px;
}

.act-con {
	width: 90%;
	color: white;
	height: 80px;
	font-size: 24px;
	line-height: 37px;
	margin-top: 77px;
	position: absolute;
	letter-spacing: 2px;
}

.tip {
	width: 90%;
	font-size: 24px;
	color: #ffe50d;
	margin-left: 5%;
	letter-spacing: 2px;
	position: absolute;
	margin-top: 100px;
	line-height: 37px;
}

.user {
	margin-top: 9%;
}

.p1 {
	margin-top: 15%;
}
#btn{
	width: 150px;
	height: 60px;
	border-radius: 5px;
	background: #333333;
	float: left;
	margin-top: 25px;
	margin-left: 17px;
	color: white;
	font-size: 24px;
	line-height: 60px;
	text-align: center;
	-webkit-appearance: none;
}
.tip6-p{
	width: 540px;
	height: 30px;
	text-align: center;
	color: #888888;
	font-size: 20px;
	margin-top:49px;
}
.saoma{
	width: 328px;
	height: 148px;
	background: url(${contentPath }/jsp/weixinDraw/img/bg-ma.png);
	margin: 140px auto 0px;
}
.saoma img{
	width: 122px;
	height: 122px;
	margin: 14px 13px;
	float: left;
}
.sanma-con{
	width: 118px;
	height: 70px;
	color: white;
	font-size: 20px;
	float: left;
	margin: 45px 0px 0px 45px;
	line-height: 28px;
	letter-spacing: 2px;
}
.big-ma img{
	width: 100%;
	position: absolute;
	margin-top: -99%;
	margin-left: -48%;
	display: none;
}
.banner{
	width: 570px;
	height: 120px;
	margin: 94px auto 0px;
}
</style>
<script>
    var duihuanflag=false;
	var tel;
	var card;
	function banner2(){
		window.location="http://www.linkgift.cn/giftpay_wap/giftpay/third/addThirdUserOther.htm?thirdName=pingan";
	}
	$(function() {
		$(".tip6-button").click(function() {
			tel = $(".alertTips6").find(".tel-txt").val();
			card = $(".alertTips6").find(".card-txt").val();
			if (tel.length < 11 || jxTool.isNumber(tel) == false) {
				alerw("请输入电话号码");
			} else if (card.length < 1 || jxTool.isNumber(card) == false) {
				alerw("请输入验证码")
			} else {
				loading("start");
				$.ajax({
					//zanwu
					url : getRootPath() + "/weixinMng/DrawMng/addUserPhone.htm",
					data : {
						"tel" : tel,
						"card" : card
					},
					dataType : "json",
					type : "post",
					success : function(data) {
						loading("stop")
						if (data.resultObject.success) {
							$(".alertTips6").css("display", "none");
							$("#USER_PHONE").val(data.resultObject.USER_PHONE)
							
// 							window.location=getRootPath() + "/weixinMng/DrawMng/gotoDrawPage.htm";
						} else {
							alerw(data.resultObject.msg);
						}
					}
				});
			}
		});
		
		$(".tip7-button").click(function() {
			tel = $(".alertTips7").find(".tel-txt").val();
			card = $(".alertTips7").find(".card-txt").val();
			if (tel.length < 11 || jxTool.isNumber(tel) == false) {
				alerw("请输入电话号码");
			} else if (card.length < 1 || jxTool.isNumber(card) == false) {
				alerw("请输入验证码")
			} else {
				loading("start");
				$.ajax({
					//zanwu
					url : getRootPath() + "/weixinMng/DrawMng/addUserPhone.htm",
					data : {
						"tel" : tel,
						"card" : card
					},
					dataType : "json",
					type : "post",
					success : function(data) {
						loading("stop")
						if (data.resultObject.success) {
							$(".alertTips7").css("display", "none");
							$("#USER_PHONE").val(data.resultObject.USER_PHONE);
							
				 		 	var form = $('<form id="submitForm" action="" method="POST">');
							form.append('<input type=hidden name="Plain" id="Plain" value=""/>');
							form.append('<input type=hidden name="Signature" id="Signature"  value=""/>');
							form.append('<input type="hidden" name="TransName" value="WapPayPre"/>');

							form.attr("action","exchangePrize_getzsh.htm");
							form.submit(); 
							
							//window.location = getRootPath()+"/weixinMng/DrawMng/exchangePrize_getzsh.htm";
						} else {
							alerw(data.resultObject.msg);
						}
					}
				});
			}
		});
		$(".banner1").click(function(){
    			window.location="http://www.linkgift.cn/giftpay_wap/giftpay/third/addThirdUserOther.htm?thirdName=fuli";
    		})
    		
		$(".close").click(function() {
			$(".alertTips6").css("display", "none");
			$(".alertTips7").css("display", "none")
		})
		$(".tip1-button").click(
				function() {
					$(".alertTips1,.alertTips2,.alertTips4,.alertTips5").css(
							"display", "none");
				})
		$(".rules-close").click(function() {
			$(".rules-con").css("display", "none")
		})
		$(".rules").click(function() {
// 			$(".rules-con").css("display", "block")
// 			$(".rules-con").fadeIn();
			location.href="${contentPath }/jsp/weixinDraw/tip.html";
		})
		if($("#OPPORTUNITY_LEFT").val()== 0){
			
			$("#lottery a").css("background","url(${contentPath }/jsp/weixinDraw/img/start__02.png)");
		}
		//进度条
		var TOTAL_WINNING = $("#TOTAL_WINNING").val();
		$("#minmoney").html(TOTAL_WINNING);
		if(parseFloat(TOTAL_WINNING)>=10){
			
			TOTAL_WINNING=10;
		}
		var greenWidth = (parseFloat(TOTAL_WINNING) / 10) * 260;
	
		if(TOTAL_WINNING>0){
			if(greenWidth<26){
				greenWidth=26;
			}
		}
		
		if(greenWidth>=260){
			greenWidth=260;
		}
		$(".green").width(greenWidth);
// 		$("#lottery table td a").click(function() {

			
// 			//			 $(".green").animate({
// 			//			  left:'+=-30px'},2000,function(){
// 			//			  });
// 		})
			if ($(".green").width() < 260) {
				duihuanflag=true;
				$(".duihuan-button").click(function() {
					if(duihuanflag){
						$(".alertTips1").css("display", "block");
					}
				})
				
			} else {
				$(".green").width($(".green").width() + 0)
				$('.duihuan-button').removeClass("duihuan-button").addClass("duihuan-button_")
				$(".duihuan-button_").click(function() {
					//是否能兑换
					canduhuan();
				})
				$(".btn1").click(function() {
					$(".alertTips3").css("display", "none")
				})
				$(".btn2").click(function() {
					var USER_PHONE =$("#USER_PHONE").val();
					if(jxTool.isNull(USER_PHONE)){
						// 是空的
						$(".alertTips3").css("display", "none");
						$(".alertTips7").css("display", "block");
					}else{
						window.location = getRootPath()+"/weixinMng/DrawMng/exchangePrize_getzsh.htm";
					}
					
				})
			}
	})
	
		      //发送短信
	  var countdown=60; 
	  function sendmsg(obj) { 
		  tel = $(".alertTips6").find(".tel-txt").val();
		  if(tel.length<11||jxTool.isMobile(tel)==false){
			 alerw("请填写正确的手机号码!");
			 return;
		  }else{
			      $(".alertTips6").find(".card-txt").val("")
			      settime(obj);   //倒计时
	         	  loading("start");
		          $.ajax({                        //发送验证码
		          url: getRootPath() + "/weixinMng/DrawMng/sendRegistCode.htm",             
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
	  
	  function sendmsg2(obj) { 
		  tel =  $(".alertTips7").find(".tel-txt").val();
		  if(tel.length<11||jxTool.isMobile(tel)==false){
			 alerw("请填写正确的手机号码!");
			 return;
		  }else{
			     $(".alertTips7").find(".card-txt").val("")
			      settime(obj);   //倒计时
	         	  loading("start");
		          $.ajax({                        //发送验证码
		          url: getRootPath() + "/weixinMng/DrawMng/sendRegistCode.htm",             
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

          function settime(obj) {
			if (countdown == 0) {
				obj.removeAttribute("disabled");
				obj.value = "免费获取验证码";
				countdown = 60;
				return;
			} else {
				obj.setAttribute("disabled", true);
				obj.value = "重新发送(" + countdown + ")";
				countdown--;
			}
			setTimeout(function() {
				settime(obj)
			}, 1000)
		}
</script>
<script type="text/javascript">
var lottery={
	    index:-1,    //当前转动到哪个位置，起点位置
	    count:8,    //总共有多少个位置
	    timer:0,    //setTimeout的ID，用clearTimeout清除
	    speed:5,    //初始转动速度
	    times:1,    //转动次数
	    cycle:5,    //转动基本次数：即至少需要转动多少次再进入抽奖环节
	    prize:-1,    //中奖位置
	    init:function(id){
	        if ($("#"+id).find(".lottery-unit").length>0) {
	            $lottery = $("#"+id);
	            $units = $lottery.find(".lottery-unit");
	            this.obj = $lottery;
	            this.count = $units.length;
	            $lottery.find(".lottery-unit-"+this.index).addClass("active");
	        };
	    },
	    roll:function(){
	        var index = this.index;
	        var count = this.count;
	        var lottery = this.obj;
	        $(lottery).find(".lottery-unit-"+index).removeClass("active");
	        index += 1;
	        if (index>count-1) {
	            index = 0;
	        };
	        $(lottery).find(".lottery-unit-"+index).addClass("active");
	        this.index=index;
	        return false;
	    },
	    stop:function(index){
	        this.prize=index; 
	        return false;
	    }
	};

	function roll(){
		var i = lottery.i;
	    lottery.times += 1;
	    lottery.roll();//转动过程调用的是lottery的roll方法，这里是第一次调用初始化
	    if (lottery.times > lottery.cycle+10 && lottery.prize==lottery.index) {
	        clearTimeout(lottery.timer);
	        lottery.prize=-1;
	        lottery.times=0;
	        click=false;
	    }else{
	        if (lottery.times<lottery.cycle) {
	            lottery.speed -= 10;
	        }else if(lottery.times==lottery.cycle) {
//	          var index = Math.random()*(lottery.count)|0;
				var index=0;
				var yes=new Array(0,1,1,1);
				var no=new Array(2,2,2);
					if(i==1){
						index=yes[parseInt(Math.random()*3)];
					}else{
						index=no[parseInt(Math.random()*2)];
					}
				
	            lottery.prize = index;        
	        }else{
	            if (lottery.times > lottery.cycle+10 && ((lottery.prize==0 && lottery.index==7) || lottery.prize==lottery.index+1)) {
	                lottery.speed += 110;
	            }else{
	                lottery.speed += 20;
	            }
	        }
	        if (lottery.speed<40) {
	            lottery.speed=40;
	        };
	        //console.log(lottery.times+'^^^^^^'+lottery.speed+'^^^^^^^'+lottery.Prize);
	        lottery.timer = setTimeout(roll,lottery.speed);//循环调用
	    }
	    return false;

	}

	var click = false;
	window.onload = function(){
//在家里没法测试，如果需要使用定时刷新用户，释放下面的代码↓
// 		setInterval(getUsersInfo,3000); 
		
		function getUsersInfo(){
			$.ajax({
				url:"../DrawMng/getUsersInfo.htm",
				type:"post",
				dataType:"json",
				success:function(data){
					$(data).each(function(){
						var nickname=this.model.NICK_NAME;
						var action=this.action;
						$("#usersInfo").prepend('<div class="li"><div class="name">'+nickname+'</div><div class="sca">'+action+'</div></div>');
						
					});
					
			},});
			
	
		}
		
		
		
		
		if($("#err").val()){
			alerw($("#err").val());
			window.location=getRootPath() + "/weixinMng/ManageC/userIn.htm"; 
		}
		lottery.init('lottery');
		   
		$("#lottery a").click(function() {
			setTimeout(function(){
    		$("#lottery a").css("background","url(${contentPath }/jsp/weixinDraw/img/start__02.png)")
    	},4500);
			if (click) {//click控制一次抽奖过程中不能重复点击抽奖按钮，后面的点击不响应
				return false;
			} else {
				lottery.speed = 100;
				if ($("#OPPORTUNITY_LEFT").val()== 0) {
					
					$(".alertTips5").css("display", "block");
					return;
				}
				
				getDraw();
				click = true; //一次抽奖完成后，设置click为true，可继续抽奖
				return false;
			}
		});
	};

	function getDraw() {
		// 抽奖
		$.ajax({
			type : 'post',
			url : '../DrawMng/drawstart.htm',
			data : {
				"DRAWTYPE" : "111"
			},
			dataType : 'json',
			timeout : 10000000,
			beforeSend : function() {
				loading("start");
			},
			complete : function(XMLHttpRequest) {
				loading("stop");
			},
			success : function(data, textStatus) {
				if (-1 == data.timeout) {
					// session已经过期了
					//window.location="list.htm"; 
					// 	               		alert("ajax-timeout");
					location.href = "/sessionTimeOut.jsp";
					
				}
				if (data.code == 1) {
				$("#left").html(0);
				$("#OPPORTUNITY_LEFT").val(0);
					var drawPrize = data.resultObject.DRAWPRIZE;
					
					if (!drawPrize) {
						// 没有中奖
						lottery.i = 0;
						roll();
						window.setTimeout(function(){
							$(".alertTips4").css("display", "block");
						},4000);
						

					} else {
						
						$(".gotRed").html(drawPrize.PRIZENAME + "元红包");
						lottery.i = 1;
							roll();
						
						var  TOTAL_WINNING =data.resultObject.total_WINNING;
						var nickname=$("#current_NICK_NAME").val();
						var greenWidth = (parseFloat(TOTAL_WINNING) / 10) * 260;
						if(greenWidth<=26){
							greenWidth=26;
						}
						if (!data.resultObject.MOBILE) {
							window.setTimeout(function(){
							$(".alertTips6").css("display", "block");
							$(".green").width(greenWidth);
							$("#minmoney").html(TOTAL_WINNING);
							},3000);
							
							return ;
						}
						
						// 	     					alert("恭喜您抽中"+DRAWPRIZE.PRIZENAME+"元");
						window.setTimeout(function(){
						//延时提示			
						$(".alertTips2").css("display", "block");
						
						if(greenWidth>=260){
							greenWidth=260;
							duihuanflag=false;
							$('.duihuan-button').removeClass("duihuan-button").addClass("duihuan-button_")
							$(".duihuan-button_").click(function() {
								// 是否能兑换
								canduhuan();
							})
							$(".btn1").click(function() {
								$(".alertTips3").css("display", "none")
							})
							$(".btn2").click(function() {
								var USER_PHONE =$("#USER_PHONE").val();
								if(jxTool.isNull(USER_PHONE)){
									// 是空的
									$(".alertTips3").css("display", "none");
									$(".alertTips7").css("display", "block");
								}else{
									window.location = getRootPath()+"/weixinMng/DrawMng/exchangePrize_getzsh.htm";
								}
							})
						}
						
						$(".green").width(greenWidth);
						if(parseInt($("#leftRed").html())>=1){
							$("#leftRed").html(parseInt($("#leftRed").html())-1);
						}
						$("#minmoney").html(TOTAL_WINNING);
						},3000);
					}
				} else if (data.code == 2) {
					// 抽奖机会用完了
					$(".alertTips5").css("display", "block");
					return;
				} 
				else if (data.code == 7) {
					// 已经领取过
					alerw("抽奖活动已经截止了,感谢您的参与！");
				}else if (data.code == 8) {
					// 抽奖机会用完了
					$(".alertTips5").css("display", "block");
					return;
				}

				else {
					alerw(data.msg);
				}
			},
			//调用出错执行的函数
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//请求出错处理
				alert("系统异常!" + textStatus + errorThrown);
			}
		});
	}
	
	// 是否能兑换
	function canduhuan(){
		// 抽奖
		$.ajax({
			type : 'post',
			url : '../DrawMng/canduhuan.htm',
			data : {
				"DRAWTYPE" : "111"
			},
			dataType : 'json',
			timeout : 10000000,
			beforeSend : function() {
				loading("start");
			},
			complete : function(XMLHttpRequest) {
				loading("stop");
			},
			success : function(data, textStatus) {
				if (-1 == data.timeout) {
					// session已经过期了
					//window.location="list.htm"; 
					// 	               		alert("ajax-timeout");
					location.href = "/sessionTimeOut.jsp";
					
				}
				if (data.code == 1) {
					$(".alertTips3").css("display", "block");
				}else if (data.code == 2) {
					alerw("你已经兑换过红包了!");
				}else{
					alerw("系统异常!");
				}
			},
			//调用出错执行的函数
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//请求出错处理
				alert("系统异常!" + textStatus + errorThrown);
			}
		});
		
	}
	
	var test=self.setInterval("getusr()",10000)
	function getusr(){
		$.ajax({
			type : 'post',
			url : '../DrawMng/getprizeuser.htm',
			data : {
				"DRAWTYPE" : "111"
			},
			dataType : 'json',
			timeout : 10000000,
			beforeSend : function() {
			},
			complete : function(XMLHttpRequest) {
			},
			success : function(data, textStatus) {
				if (-1 == data.timeout) {
					// session已经过期了
					//window.location="list.htm"; 
					// 	               		alert("ajax-timeout");
					location.href = "/sessionTimeOut.jsp";
					
				}
				if (data.code == 1) {
					var userbean_opportunity_left =data.resultObject.USERBEAN_OPPORTUNITY_LEFT;
					var userbean_remaintimes =data.resultObject.USERBEAN_REMAINTIMES;
					
					if(0==userbean_opportunity_left){
						$("#left").html(0);
						$("#OPPORTUNITY_LEFT").val(0);
					    $("#lottery a").css("background","url(${contentPath }/jsp/weixinDraw/img/start__02.png)")
					}
					
					$("#leftRed").html(userbean_remaintimes);
					
					var prizelist =data.resultObject.PRIZEUSERLIST;
					$("#usersInfo").html("");   //清空
					var html="";
					$(prizelist).each(function(i, n) {
						
						html=html+'<div class="li"><div class="name">'+n.model.NICK_NAME+'</div><div class="sca">'+n.action+'</div></div>'
					});
					
					$("#usersInfo").html(html);
				}
			},
			//调用出错执行的函数
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//请求出错处理
				//alert("系统异常!" + textStatus + errorThrown);
			}
		});
	}
	
</script>
<script type="text/javascript">
			$(function(){
				 $(".flexslider").flexslider({
                animation: "slide", //String: Select your animation type, "fade" or "slide"图片变换方式：淡入淡出或者滑动
                slideshowSpeed: 2500, //展示时间间隔ms
                animationSpeed: 1100, //滚动时间ms
                touch: true //是否支持触屏滑动
            });

			})
		</script>
</head>
<body>
	<input type="hidden" value="${TOTAL_WINNING }" id="TOTAL_WINNING">
	<input type="hidden" value="${OPPORTUNITY_LEFT }" id="OPPORTUNITY_LEFT">
	<input type="hidden" id="DrawPrizeCurrent">
	<input type="hidden" id="err" value="${errorMsg }">
	<input type="hidden" id="current_NICK_NAME" value="${WxloginUser.NICK_NAME }">
    <input type="hidden" id="USER_PHONE" value="${USER_PHONE }">
	<div class="zoomer">
		<div class="contents">
			<div class="head">
				<div class="jindutiao"></div>
				<div class="money">
					￥<span id="minmoney">0.00</span>/￥<span id="maxmoney">10.00</span>
				</div>
				<div class="green"></div>
				<div class="duihuan-button">
					
				</div>
				<div class="rules">
					<img src="${contentPath }/jsp/weixinDraw/img/8_03.png">
				</div>
			</div>
			<div class="redbox">
				今日剩余红包:<span id="leftRed">${REMAINTIMES}</span>
			</div>
			<div class="num">
				您还有&nbsp;&nbsp;<span id="left">${OPPORTUNITY_LEFT}</span>&nbsp;&nbsp;次抽奖机会
			</div>
			<div class="cont">
				<div id="lottery">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="lottery-unit lottery-unit-0"><img
								src="${contentPath }/jsp/weixinDraw/img/13_03.png">
								<div class="mask"></div></td>
							<td class="lottery-unit lottery-unit-1"><img
								src="${contentPath }/jsp/weixinDraw/img/13_03.png">
								<div class="mask"></div></td>
							<td class="lottery-unit lottery-unit-2"><img
								src="${contentPath }/jsp/weixinDraw/img/12_03.png">
								<div class="mask"></div></td>
						</tr>
						<tr>
							<td class="lottery-unit lottery-unit-7"><img
								src="${contentPath }/jsp/weixinDraw/img/13_03.png">
								<div class="mask"></div></td>
							<td><a href="javascript::"></a></td>
							<td class="lottery-unit lottery-unit-3"><img
								src="${contentPath }/jsp/weixinDraw/img/13_03.png">
								<div class="mask"></div></td>
						</tr>
						<tr>
							<td class="lottery-unit lottery-unit-6"><img
								src="${contentPath }/jsp/weixinDraw/img/12_03.png">
								<div class="mask"></div></td>
							<td class="lottery-unit lottery-unit-5"><img
								src="${contentPath }/jsp/weixinDraw/img/13_03.png">
								<div class="mask"></div></td>
							<td class="lottery-unit lottery-unit-4"><img
								src="${contentPath }/jsp/weixinDraw/img/12_03.png">
								<div class="mask"></div></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="list">
				<div class="flexslider">
                		<ul class="slides">
                    		<li><img class="banner1" src="${contentPath }/jsp/weixinDraw/img/banner1.png" alt="1"/></li>
                    		<li><img class="banner2" src="${contentPath }/jsp/weixinDraw/img/banner2.png" alt="1" onclick="banner2()"></li>
                		</ul>
            	</div>
				<!--<div class="banner"><img src="${contentPath }/jsp/weixinDraw/img/banner.png"></div>-->
				<div class="con" id="usersInfo">
					<c:forEach items="${WIN_USERS }" var="user">
						<div class="li">
						<div class="name">${user.model.NICK_NAME }</div>
						<div class="sca">${user.action }</div>
					</div>	
					</c:forEach>
					
					
<!-- 					<div class="li"> -->
<!-- 						<div class="name">jmt123</div> -->
<!-- 						<div class="sca">兑换50元加油红包</div> -->
<!-- 					</div> -->
<!-- 					<div class="li"> -->
<!-- 						<div class="name">jmt123</div> -->
<!-- 						<div class="sca">兑换50元加油红包</div> -->
<!-- 					</div> -->
<!-- 					<div class="li"> -->
<!-- 						<div class="name">jmt123</div> -->
<!-- 						<div class="sca">兑换50元加油红包</div> -->
<!-- 					</div> -->
<!-- 					<div class="li"> -->
<!-- 						<div class="name">jmt123</div> -->
<!-- 						<div class="sca">兑换50元加油红包</div> -->
<!-- 					</div> -->
				</div>
			</div>
		</div>
	</div>
	<div class="alertTips1"
		style="background-color: rgba(10, 10, 10, 0.6); width: 100%; height: 100%; position: fixed; top: 0; left: 0; z-index: 1100; display: none;">
		<div class="tips1"
			style="background: #ffffff; width: 540px; height: 314px; border-radius: 10px; position: absolute; top: 50%; left: 50%; margin: -152px -270px">
			<p>
				红包金额累计满<span>10</span>元，即可兑换<span>“10元加油支付红包”</span>哦！请继续努力吧~！
			</p>
			<div class="tip1-button">确定</div>
		</div>
	</div>
	<div class="alertTips2"
		style="background-color: rgba(10, 10, 10, 0.6); width: 100%; height: 100%; position: fixed; top: 0; left: 0; z-index: 1100; display: none;">
		<div class="tips1"
			style="background: #ffffff; width: 540px; height: 314px; border-radius: 10px; position: absolute; top: 50%; left: 50%; margin: -152px -270px">
			<p class="p2">
				恭喜您获得<span class="gotRed"></span><br>离兑奖10元加油支付红包又近了一步
			</p>
			<div class="tip1-button">确定</div>
		</div>
	</div>
	<div class="alertTips3"
		style="background-color: rgba(10, 10, 10, 0.6); width: 100%; height: 100%; position: fixed; top: 0; left: 0; z-index: 1100; display: none;">
		<div class="tips1"
			style="background: #ffffff; width: 540px; height: 314px; border-radius: 10px; position: absolute; top: 50%; left: 50%; margin: -152px -270px">
			<p class="p2">确定要兑换吗？</p>
			<div class="btns">
				<div class="btn1">取消</div>
				<div class="btn2">确定</div>
			</div>
		</div>
	</div>
	<div class="alertTips4"
		style="background-color: rgba(10, 10, 10, 0.6); width: 100%; height: 100%; position: fixed; top: 0; left: 0; z-index: 1100; display: none;">
		<div class="tips1"
			style="background: #ffffff; width: 540px; height: 314px; border-radius: 10px; position: absolute; top: 50%; left: 50%; margin: -152px -270px">
			<p class="p2">
				天啊，居然没中奖<br>明天的运气会更好哦~
			</p>
			<div class="tip1-button">确定</div>
		</div>
	</div>
	<div class="alertTips5"
		style="background-color: rgba(10, 10, 10, 0.6); width: 100%; height: 100%; position: fixed; top: 0; left: 0; z-index: 1100; display: none;">
		<div class="tips1"
			style="background: #ffffff; width: 540px; height: 314px; border-radius: 10px; position: absolute; top: 50%; left: 50%; margin: -152px -270px">
			<p class="p2">抽奖机会用完了哦~</p>
			<div class="tip1-button">确定</div>
		</div>
	</div>
	<div class="alertTips6"
		style="background-color: rgba(10, 10, 10, 0.6); width: 100%; height: 100%; position: fixed; top: 0; left: 0; z-index: 1100; display: none;">
		<div class="tips1"
			style="background: #ffffff; width: 540px; height: 514px; border-radius: 10px; position: absolute; top: 50%; left: 50%; margin: -242px -270px">
			<div class="close">
				<img src="${contentPath }/jsp/weixinDraw/img/close.png">
			</div>
			<div class="tip6-p">累计10元即可兑换10元加油红包</div>
			<p style="margin-top: 4px;">
				恭喜您获得<span  class="gotRed">元加油支付红包</span>，快填写手机号拿走它吧！
			</p>
			<div class="tel">手机号</div>
			<input type="text" class="tel-txt" maxlength="11" />
			<div class="card">验证码</div>
			<input class="card-txt" type="text" />
			<input type="button" id="btn" value="获取验证码" onclick="sendmsg(this)" />
<!-- 			<div class="card-button" onclick="sendmsg(this)">获取验证码</div> -->
			<div class="tip6-button">拿走红包</div>
		</div>
	</div>
	
	<div class="alertTips7"style="background-color: rgba(10, 10, 10, 0.6); width: 100%; height: 100%; position: fixed; top: 0; left: 0; z-index: 1100;display: none;">
	    <div class="tips1" style="background: #ffffff; width:540px; height: 514px; border-radius: 10px; position: absolute; top: 50%; left: 50%; margin: -242px -270px">
	       <div class="close"><img src="${contentPath }/jsp/weixinDraw/img/close.png"></div>
	       <p>您已满足兑换条件，快填写手机号拿走红包吧！</p>
	       <div class="tel">手机号</div>
	       <input type="text" class="tel-txt" maxlength="11"/>
	       <div class="card">验证码</div>
	       <input class="card-txt" type="text"/>
	       <input type="button" id="btn" value="获取验证码" onclick="sendmsg2(this)" />
	       <div class="tip7-button">拿走红包</div>
	    </div>
    </div>
	
	<!--<div class="rules-con"
		style="background-color: rgba(10, 10, 10, 0.83); width: 100%; height: 100%; position: fixed; top: 0; left: 0; z-index: 1100; display: none;">
		<div class="rules-title">活动规则</div>
		<div class="rules-close">X</div>
		<div class="rules-list">
			<div class="act-title">活动规则</div>
			<div class="act-con">
				本活动每日限10000人次参与，每人每天限抽奖一次。<br>累计获得红包金额满10元即可兑换“10元加油支付红包”
			</div>
		</div>
		<div class="tip">
			tips:金额满10元不兑换，将无法继续累计红包金额哦~<br>兑换成功后可重新开始累计
		</div>
		<div class="rules-list">
			<div class="act-title">活动奖项</div>
			<div class="act-con">10元石化加油支付红包</div>
		</div>
		<div class="rules-list user">
			<div class="act-title ">红包使用</div>
			<div class="act-con">成功兑换10元加油红包后，在石化加油时，使用微信扫描工作人员出示的二维码，在支付页面选择使用“10元加油红包”，即可抵扣消费金额</div>
		</div>
		<div class="saoma">
			<img src="${contentPath }/jsp/weixinDraw/img/17.png">
    	<div class="sanma-con">长按二维码查询红包</div>
		</div>
		<div class="big-ma"><img src="${contentPath }/jsp/weixinDraw/img/17.png"></div>
	</div>-->
</body>
<script type="text/javascript">
var openID = '${WxloginUser.OPEN_ID}';
</script>
<script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/share.js"></script>
</html>
