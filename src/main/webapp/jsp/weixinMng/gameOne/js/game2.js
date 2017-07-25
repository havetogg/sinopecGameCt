//初始化
var hp = 3; 			//生命值
var time = 60; 			//加油倒计时
var getMoney = 0; 		//获得的金币
var perfect = 0; 		//完美加油
var lose = 0 ;			//失败次数
var goldClassNum =0	;	//加油次数
var goldNum = 0;		//当前加油(1次)获得金币
var know = true;		//是否看界面介绍
var level = '';
var term = '';
var oilTime = '';
var isStart = true;

var isExits = 0;

document.addEventListener('DOMContentLoaded', function () { //添加监听DOM 结构是否加载完，（不包含图片和其他资源）
    function audioAutoPlay() {
        var audio = document.getElementById('audio');//获取音乐标签的id
        audio.play(); //播放
        document.addEventListener("WeixinJSBridgeReady", function () { //监听微信准备就绪事件，只能在微信用
            audio.play(); //播放
        }, false);
    }
    audioAutoPlay();

    console.log("zidongbofang");

});

$(document).ready(function() {

	if(GAME2KNOWFLAG>=1){
		// 已经阅读了
		know = false;
	}else{
		know = true;
	}

	//开始游戏
	Play();

	//关闭弹窗
	$(".close").on("click", function() {
		$(this).parent().parent().fadeOut();
	});

	//购买车位
	$(".park .add").on("click", function() {
        $.ajax({
     		type : 'post',
     		url : getRootPath() + "/weixinMng/GameOneC/gameOneParksBuy.htm",
               data: {
                  "BUY_FLAG": 0
               },
     		dataType : 'json',
     		timeout : 10000000, 
             beforeSend: function () {
                 //loading("start");
             },
             complete: function (XMLHttpRequest) {
                 //loading("stop");
             },
     		success : function(data, textStatus) {
               	if(-1==data.timeout){
               		// session已经过期了
               		//alert("ajax-timeout");
               		return;
               	}
     			if(data.code==1){
     				
     				var NEEDGOLD =data.resultObject.NEEDGOLD;
     				var PARK_BUY = data.resultObject.PARK_BUY;
     				//var i = $(".park .tip.not_pay:last dd span").text();
     				//alert(i);
     				$(".buyPark").fadeIn();
     				$(".buyPark .img").text(PARK_BUY);
     				$(".buyPark span").text(NEEDGOLD);
     			}else if(data.code==2){
     				$(".noGold").fadeIn();
     			}else{
     				alerw(data.msg);
     			}
     		},
             //调用出错执行的函数
             error: function(XMLHttpRequest, textStatus, errorThrown){
                   //请求出错处理
                 //alert("系统异常!"+textStatus);
             } 
     	});


	});
	
	// 车位购买确认
	$(".buyPark .buy").on("click", function() {
        $.ajax({
     		type : 'post',
     		url : getRootPath() + "/weixinMng/GameOneC/gameOneParksBuy.htm",
               data: {
                  "BUY_FLAG": 1
               },
     		dataType : 'json',
     		timeout : 10000000, 
             beforeSend: function () {
                 loading("start");
             },
             complete: function (XMLHttpRequest) {
                 loading("stop");
             },
     		success : function(data, textStatus) {
               	if(-1==data.timeout){
               		// session已经过期了
               		//alert("ajax-timeout");
               		return;
               	}
     			if(data.code==1){
     				$(".park .tip.not_pay:last").removeClass("not_pay");
     				$(".buyPark").fadeOut();
     				var USERBEAN = data.resultObject.USERBEAN;
     				$(".gold").html(USERBEAN.REMAIN_GOLD);
     				
     			}else if(data.code==2){
     				$(".noGold").fadeIn();
     			}else{
     				alerw(data.msg);
     			}
     		},
             //调用出错执行的函数
             error: function(XMLHttpRequest, textStatus, errorThrown){
                   //请求出错处理
                 //alert("系统异常!"+textStatus);
             } 
     	});

	});

	//购买加油机
	$(".tank .add").on("click", function() {
        $.ajax({
     		type : 'post',
     		url : getRootPath() + "/weixinMng/GameOneC/gameOneOilMachineBuy.htm",
               data: {
                  "BUY_FLAG": 0
               },
     		dataType : 'json',
     		timeout : 10000000, 
             beforeSend: function () {
                 //loading("start");
             },
             complete: function (XMLHttpRequest) {
                 //loading("stop");
             },
     		success : function(data, textStatus) {
               	if(-1==data.timeout){
               		// session已经过期了
               		//alert("ajax-timeout");
               		return;
               	}
     			if(data.code==1){
     				
     				var NEEDGOLD =data.resultObject.NEEDGOLD;
     				var OIL_BUY = data.resultObject.OIL_BUY;
     				$(".buyTank").fadeIn();
     				
     				$("#oilimgId").text(OIL_BUY);
     				$(".buyTank span").text(NEEDGOLD);
     			}else if(data.code==2){
     				$(".noGold").fadeIn();
     			}else{
     				alerw(data.msg);
     			}
     		},
             //调用出错执行的函数
             error: function(XMLHttpRequest, textStatus, errorThrown){
                   //请求出错处理
                 //alert("系统异常!"+textStatus);
             } 
     	});
		
	});
	$(".buyTank .buy").on("click", function() {
        $.ajax({
     		type : 'post',
     		url : getRootPath() + "/weixinMng/GameOneC/gameOneOilMachineBuy.htm",
               data: {
                  "BUY_FLAG": 1
               },
     		dataType : 'json',
     		timeout : 10000000, 
             beforeSend: function () {
                 loading("start");
             },
             complete: function (XMLHttpRequest) {
                 loading("stop");
             },
     		success : function(data, textStatus) {
               	if(-1==data.timeout){
               		// session已经过期了
               		//alert("ajax-timeout");
               		return;
               	}
     			if(data.code==1){
     				
     				$(".right .tank.not_pay:first").removeClass("not_pay");
     				$(".buyTank").fadeOut();
     				var USERBEAN = data.resultObject.USERBEAN;
     				$(".gold").html(USERBEAN.REMAIN_GOLD);
     			}else if(data.code==2){
     				$(".noGold").fadeIn();
     			}else{
     				alerw(data.msg);
     			}
     		},
             //调用出错执行的函数
             error: function(XMLHttpRequest, textStatus, errorThrown){
                   //请求出错处理
                 //alert("系统异常!"+textStatus);
             } 
     	});
		
	});


	//开始加油
	$(".game_begin").on("click", function() {
		$(this).parent().addClass("toPlay");	//添加标记
		$(".petrol").fadeIn();
		countDown();						//倒计时开始
		putPetrol();						//加油流程
	});


})
//开始游戏
function Play() {
	sessionStorage.setItem("need-refresh-game2", true);
	if (know) {
		$(".know").on("click", function(){
			if($('#know_checkid').is(':checked')){
				//选中
				$.ajax({
					type : 'post',
					url : getRootPath() + "/weixinMng/GameOneC/saveKnow.htm",
			         data: {
			            "KNOWTYPE": 4
			         },
					dataType : 'json',
			 		async: false,
					timeout : 10000000, 
			       beforeSend: function () {
			           //loading("start");
			       },
			       complete: function (XMLHttpRequest) {
			           //loading("stop");
			       },
					success : function(data, textStatus) {
			         	if(-1==data.timeout){
			         		// session已经过期了
			         		//alert("ajax-timeout");
			         		return;
			         	}
						if(data.code==1){
							$(".Countdown .centerDiv").fadeIn();
							$(".Countdown .introduce").hide();
							playCartoon();
						}else{
							alerw(data.msg);
						}
					},
			       //调用出错执行的函数
			       error: function(XMLHttpRequest, textStatus, errorThrown){
			             //请求出错处理
			           //alert("系统异常!"+textStatus);
			       } 
				});	
				
				
			}else{
				$(".Countdown .centerDiv").fadeIn();
				$(".Countdown .introduce").hide();
				playCartoon();
			}
			

		});
	} else{
		$(".Countdown .centerDiv").fadeIn();
		$(".Countdown .introduce").hide();
		playCartoon();
	}
}

//游戏开始动画
function playCartoon(){
	var n = 3;
	setTime=setInterval(function(){
        if(n<=1){
            clearInterval(setTime);
            $(".Countdown .num").hide();
            $(".Countdown img").addClass("show");
            setTimeout(function(){
            	$(".Countdown").fadeOut();
				//开始刷新车辆
				carShow();
				var level=setInterval(carShow, 6000);
				//车辆进入加油站
				setInterval(toTank,1000);
			},1000);
            
            return;
        }
        n--;
        $(".Countdown .num").text(n);
    },1000);
}

//停车位随机出现车辆
function carShow() {
	var car = Math.floor(Math.random() * 3)
//	console.log(car);
	var i = $(".park .not_pay").length + 1;					//已购买停车位数量
	var tip = $(".park .tip").not(".no_pay, .has_car");		//已购买的空停车位
	if(tip.length > i) {
		tip.last().addClass("has_car").find(".car div").eq(car).addClass("show");
	} else {
		var display =$(".gameOver").css('display');
		if(display == 'none'){
		   gameOver();
		   $(".stop .car div").eq(car).addClass("show");
		}
		
	}
}

//车辆进入加油站
function toTank() {
	var tip = $(".tank").not(".not_pay, .has_car");	//查询空加油机
	tip.each(function() {
		var t1 = $(this).find("label").text()		//查询空加油机型号
		var t2 = $(".park .show").text();			//查询等待车辆型号
		if(t2 == '') {
			return;
		}
		//console.log((t2+"").indexOf(t1+""));
		if ((t2+"").indexOf(t1+"")!=-1) {
			var str=$(".park .tip").not(".not_pay").find(".car .show");
			var isStop=true;
			$.each($(str), function(index,item) {
				var clazz=$(item).html();
				if(clazz==t1&&isStop){
					$(item).removeClass("show").parent().parent().removeClass("has_car");
					isStop=false;
				}
			});
			$(this).addClass("has_car");
			$(this).find(".car div").addClass("show");
			$(this).addClass("begin");
		}
	});
}

//加油倒计时
function countDown() {
	setTime=setInterval(function(){
        if(time<=0){
            clearInterval(setTime);
            petrolOver();					//关闭游戏弹窗
            cutHp();
            return;
        }
        time--;
        $("#time").text(time);
    },1000);
}

//加油流程
function putPetrol(){		
	//油表移动
	$(".press").on("click", function() {
		var l1=$(".meter i").css("left").substring(0,$(".meter i").css("left").indexOf("px"));
		var l2 = $(".meter").width();
		var left =parseInt(l1*100/l2);
		var startNum = left;
		var ran = false;
		if(isStart) {
			oilTime = setInterval(function() {
				if(startNum == 100) {
					ran = true;
				} else if(startNum == 0) {
					ran = false;
				}
				if(ran) {
					startNum--;
				} else {
					startNum++;
				}
				$(".meter i").css("left", startNum + "%");
			}, 1);
			$(".press").addClass("over");
			isStart = false;

            randomGetGift();

		} else {
			clearInterval(oilTime);
			setTimeout(function(){
				petrolOver();
			},1000);
			getGold();
			gold();
			$(".tank.toPlay").removeClass("toPlay");
			
		}
	});
}

//获取钻石随机
function randomGetGift(){

    //每次只有抽奖一次[每次进行游戏中只能获得一次抽奖机会]
    if(isExits==0){
            $.ajax({
                url:"/sinopecGameCt/getDiamonds/diamondsPrize.htm",
                type: 'post',
                async:false,
                data:{
                    gameId:1
                },
                dataType: 'json',
                success: function(data){
                    if(data.status=="success"){
                        window.setTimeout(function(){
                            $(".getJewel").addClass("show");
                            jeWelAll= data.result;
                            $("#jewel_span").text(jeWelAll);
                            console.log("jeWelAll="+jeWelAll);
                        },2000);
                    }
                },
                error: function(){
                    //alert("游戏异常！")
                }
            })

        isExits++;
    }

}

//获得金币
function gold() {
	goldClassNum += 1;
	getMoney+=goldNum;
	//console.log(getMoney);
	if(3<getMoney&&getMoney<5){
		clearInterval(level);
		var level = setInterval(carShow, 4000);
	}else if(getMoney>10){
		clearInterval(level);
		var level = setInterval(carShow, 3000);
	}else if(getMoney>20){
		clearInterval(level);
		var level = setInterval(carShow, 2000);
	}	
	$("#goldNum").html("+"+goldNum);
	$(".getGold").addClass("show");
	setTimeout(function(){
		$(".getGold").removeClass("show");
	},4000);
	$(".tank.toPlay").removeClass("begin").removeClass("has_car");		//清除获得金币按钮
	$(".tank.toPlay").find(".car div").removeClass("show");				//清除车辆
	if (goldNum == 2) {
		$(".face img").eq(2).addClass("on").siblings().removeClass("on");
	} else if(goldNum == 1){
		$(".face img").eq(1).addClass("on").siblings().removeClass("on");
	}else{
		$(".face img").eq(0).addClass("on").siblings().removeClass("on");
	}
	
	var goldAll=$(".gold").html();
	
	$(".gold").html(Number(goldAll)+goldNum);  //金币总数更新
	
	
}

//关机加油机弹窗
function petrolOver(){
	$(".petrol").fadeOut();
	clearInterval(setTime);
	$("#time").text("60");				//清除倒计时
	time = 60;							
	$(".card").removeClass("on");		//清除插卡
	$(".demand .input").html("");		//清除输入框
	$(".press").removeClass("over");	//清除加油状态
	$(".touch").removeClass("on");	//清除触摸静电
	$(".money").removeClass("on");				//清除输入框
	$(".bulk").removeClass("on");
	isStart = true;
	clearKey();
	$(".toBack").find("img").attr("src","../../jsp/weixinMng/gameOne/img/key_back.png");
}

//关闭加油弹窗事件
function clearKey(){
	$(".press").unbind();
	$(".touch").unbind();
	$(".collate").unbind();
	$(".keyboard td").unbind();
	$(".toBack").unbind();
	$(".toBulk").unbind();
	$(".toMoney").unbind();
	$(".card").unbind();
}

//减少生命值
function cutHp(){
	if(hp > 1){
		lose+=1;
		//hp-=1;
		$(".hp img").last().remove();
		$(".game").addClass("wrong");
		setTimeout(function(){
			$(".game").removeClass("wrong");
		},500);
	}else{
		lose+=1;
		//hp-=1;
		$(".hp img").last().remove();
		gameOver();
		//$(".gameOver").fadeIn();
	}
	
}

//加油奖励
function getGold(){
	var l1=$(".meter i").css("left").substring(0,$(".meter i").css("left").indexOf("px"));
	var l2 = $(".meter").width();
	var left =parseInt(l1*100/l2);
//	var left = $(".meter i").css("left").substr(0,$(".meter i").css('left').length-1)*1;
	if (left <= 62 || left>=85) {//62-85
		//劣等加油
		console.log("劣等加油")
		goldNum = 0;
	} else if((62<left&&left<71) ||  (76<left&&left<85)){
		//普通加油
		console.log("普通加油")
		goldNum = 1;
	}else{
		//完美加油
		console.log("完美加油")
		goldNum = 2;
		perfect +=1;
	}
}

//结束游戏
function gameOver(){
	$(".pop").hide();						//关闭所有弹窗
	$("#income").text(getMoney); 			//获得收益
	$("#times").text(goldClassNum);			//加油次数
	$("#perfect").text(perfect);			//完美加油
	//$("#fault").text(3 - hp);				//步骤失误
	var score = getMoney*5+goldClassNum*5+perfect*20-(3 - hp)*10;
	if (score<0) {
		score=0;
	}
	$("#score").text(score);				//最终得分
	var exp = Math.floor(score/6);
	$("#exp").text(exp);					//获得经验	
	gameSubmit("0",getMoney,goldClassNum,perfect,"0",score,exp);
}

//fengsy 游戏结束提交
function gameSubmit(get_diamond,get_gold,gas_times,oper_nice,oper_not_nice,get_score,level_score){
    $.ajax({
 		type : 'post',
 		url : getRootPath() + "/weixinMng/GameOneC/save_record.htm",
           data: {
        	   "TYPE":4,                   //新手模式
        	   "GET_DIAMOND":get_diamond,
        	   "GET_GOLD":get_gold,
        	   "GAS_TIMES":gas_times,
        	   "OPER_NICE":oper_nice,
        	   "OPER_NOT_NICE":oper_not_nice,
        	   "SCORE":get_score,
        	   "LEVEL_SCORE":level_score
           },
 		dataType : 'json',
 		timeout : 10000000, 
         beforeSend: function () {
             loading("start");
         },
         complete: function (XMLHttpRequest) {
             loading("stop");
         },
 		success : function(data, textStatus) {
           	if(-1==data.timeout){
           		// session已经过期了
           		//alert("ajax-timeout");
           		return;
           	}
 			if(data.code==1){
 				// 提交完之后,展示结果
 				$(".gameOver").fadeIn();
 			}else{
 				//alerw(data.msg);
 			}
 		},
         //调用出错执行的函数
         error: function(XMLHttpRequest, textStatus, errorThrown){
               //请求出错处理
             //alert("系统异常!"+textStatus);
         } 
 	});
}
//金币不够,弹框确认
function noGoldOk(){
	$(".noGold").fadeOut();
}
//fengsy
function gotoGameOne(){
    window.location=getRootPath() + "/weixinMng/GameOneC/gameOneIn.htm";
}
//fengsy
function gotoGameIndex(){
    window.location=getRootPath() + "/weixinMng/ManageC/userIn.htm";
}


