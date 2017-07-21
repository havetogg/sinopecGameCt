var media;
$(function(){
    var isPageHide = false;
    window.addEventListener('pageshow', function () {
        if (isPageHide) {
            window.location.reload();
        }
    });
    window.addEventListener('pagehide', function () {
        isPageHide = true;
    });

    $("#iknow").on("click", function () {
	    var $this  = $("#know");
	    if($this.is(":checked")){
	        //选中的时候你的操作
            $.ajax({
                type : 'post',
                url : getRootPath() + "/weixinMng/GameTwoC/iKnow.htm",
                data: {},
                dataType : 'json',
                timeout : 10000000,
                success : function(data, textStatus) {
                }
            });
	    }
        $(".guide").hide();
	});
	
	var energyNum = parseInt($("#energyNum").val());
    show_energy(energyNum);
	//弹窗关闭
	$(".close").on("click",function(){
		$(this).parent().parent().parent().fadeOut();
	})
	
	//规则说明 
	$(".ruleButton").on("click",function(){
		$(".rule").fadeIn();
	})
	
	//钻石充值
	$(".jewel .add").on("click",function(){
		$(".recharge").fadeIn();
	})
	
	//中奖信息滚动
	$('.prize').liMarquee({
		hoverstop:false,
		drag: false,
	});
	
	//中奖左
	$('.prizes1').liMarquee({
		hoverstop: false,		//鼠标悬停暂停
		drag: false,			//鼠标可拖动
		direction: 'up',		//滚动方向
	});
	
	//中奖右
	$('.prizes2').liMarquee({
		hoverstop:false,
		drag: false,
		direction: 'down',
	});

	//抽奖动画
	$(".start").on("click",function(){
        //canPrize();
        media = document.getElementById("audio");
        media.src = getRootPath()+"/jsp/weixinMng/gameTwo/css/music.mp3";
        media.play();
        $.when(canPrize()).done(function(data){
            if(data.result==true){
                gameTimes = data.remainTimes;
                $.when(getPrize()).done(function(data){
                    $(".loading").fadeOut();
                    if(data.result==true){
                        if(gameTimes == 0){
                            if(parseInt($("#remainDiamond").html()) >= 10){
                                var remainDiamond = parseInt($("#remainDiamond").html())-10;
                                $("#remainDiamond").html(remainDiamond);
                            }else{
                                $("#remainDiamond").html(remainDiamond);
                            }
                        }else{
                            gameTimes == 0;
                        }
                        if(data.prize==true){
                            //中奖弹窗
                            $(".prizeName").text(data.prizeName);
                            prizeResult = 0;
                        }else{
                            prizeResult = 1;
                        }
                    }else if(data.result==false){
                        prizeResult = 2;
                    }
                    //指针摆动
                    $(".middle .finger").addClass("play");
                    //球滚动
                    $(".top .ball").addClass("play");
                    //旋钮动画
                    $(".ornament").addClass("play");
                    //随机出现球
                    lottery_ball();
                    //中奖球延迟出现
                    setTimeout(function () {
                        $(".middle .ball").addClass("play");
                    }, 1500);
                    //显示抽奖结果
                    setTimeout(function(){
                        if(prizeResult == 0){
                            $(".win").fadeIn();
                            hitClear();
                        }else if(prizeResult == 1){
                            $(".lost").fadeIn();
                            hit();
                        }else if(prizeResult == 2){
                            $(".no_money").fadeIn();
                        }
                        //清除运动动画
                        $(".middle .finger").removeClass("play");
                        $(".top .ball").removeClass("play");
                        $(".middle .ball").removeClass("play");
                        $(".ornament").removeClass("play");
                        //清除随机球
                        $(".middle .ball").removeClass("ball2").removeClass("ball3").removeClass("ball4");
                    }, 2500);
                });
            }else{
                $(".loading").fadeOut();
                //钻石不足
                $(".no_money").fadeIn();
            }
        });
	})
    //在抽一次
    $(".again").on("click",function(){
        $(".win").fadeOut();
        $(".lost").fadeOut();
        //canPrize();
        media = document.getElementById("audio");
        media.src = getRootPath()+"/jsp/weixinMng/gameTwo/css/music.mp3";
        media.play();
        $.when(canPrize()).done(function(data){
            if(data.result==true){
                gameTimes = data.remainTimes;
                $.when(getPrize()).done(function(data){
                    $(".loading").fadeOut();
                    if(data.result==true){
                        if(gameTimes == 0){
                            var remainDiamond = parseInt($("#remainDiamond").html())-10;
                            $("#remainDiamond").html(remainDiamond);
                        }else{
                            gameTimes == 0;
                        }
                        if(data.prize==true){
                            //中奖弹窗
                            $(".prizeName").text(data.prizeName);
                            prizeResult = 0;
                        }else{
                            prizeResult = 1;
                        }
                    }else if(data.result==false){
                        prizeResult = 2;
                    }
                    //指针摆动
                    $(".middle .finger").addClass("play");
                    //球滚动
                    $(".top .ball").addClass("play");
                    //旋钮动画
                    $(".ornament").addClass("play");
                    //随机出现球
                    lottery_ball();
                    //中奖球延迟出现
                    setTimeout(function () {
                        $(".middle .ball").addClass("play");
                    }, 1500);
                    //显示抽奖结果
                    setTimeout(function(){
                        if(prizeResult == 0){
                            $(".win").fadeIn();
                            hitClear();
                        }else if(prizeResult == 1){
                            $(".lost").fadeIn();
                            hit();
                        }else if(prizeResult == 2){
                            $(".no_money").fadeIn();
                        }
                        //清除运动动画
                        $(".middle .finger").removeClass("play");
                        $(".top .ball").removeClass("play");
                        $(".middle .ball").removeClass("play");
                        $(".ornament").removeClass("play");
                        //清除随机球
                        $(".middle .ball").removeClass("ball2").removeClass("ball3").removeClass("ball4");
                    }, 2500);
                });
            }else{
                $(".loading").fadeOut();
                //钻石不足
                $(".no_money").fadeIn();
            }
        });
    })
	//点击暴击
	$("#hit").on("click",function(){
		if(lost == 5){
			getDiamond();
		}
	})



});
//能量数字
var lost=0;
//游戏免费次数
var gameTimes = 0;
//游戏结果
var prizeResult = 3;
//开始抽奖
function canPrize(){
    $(".loading").fadeIn();
    var defer = $.Deferred();
    $.ajax({
        type : 'post',
        url : getRootPath() + "/weixinMng/GameTwoC/canPrize.htm",
        data: {},
        dataType : 'json',
        timeout : 10000000,
        //async:false,
        beforeSend: function () {
            //$(".loading").fadeIn();
        },
        complete: function (XMLHttpRequest) {
            //$(".loading").fadeOut();
        },
        success : function(data, textStatus) {
            defer.resolve(data);
        },
        //调用出错执行的函数
        error: function(XMLHttpRequest, textStatus, errorThrown){
            //请求出错处理
            alert("系统异常!"+textStatus);
        }
    });
    return defer.promise();
}

function getPrize(){
    var defer = $.Deferred();
    $.ajax({
        type : 'post',
        url : getRootPath() + "/weixinMng/GameTwoC/getPrize.htm",
        data: {},
        dataType : 'json',
        timeout : 10000000,
		//async:false,
        beforeSend: function () {
            //$(".loading").fadeIn();
        },
        complete: function (XMLHttpRequest) {
            //$(".loading").fadeOut();
        },
        success : function(data, textStatus) {
            defer.resolve(data);
        },
        //调用出错执行的函数
        error: function(XMLHttpRequest, textStatus, errorThrown){
            //请求出错处理
            alert("系统异常!"+textStatus);
        }
    });
    return defer.promise();
}

//随机出现球
function lottery_ball(){
	var ball = Math.floor(Math.random() * 4)
	if (ball == 1) {
		$(".middle .ball").addClass("ball2");
	} else if(ball == 2){
		$(".middle .ball").addClass("ball3");
	}else if(ball == 3){
		$(".middle .ball").addClass("ball4");
	}
	
}

//暴击记次
function hit(){
    if(lost < 5){
        lost += 1;
    }
	var img = $(".energys img.show").length;
	var img2 = 4 - img;
	if (lost ==5) {
		$(".hit").addClass("has_hit");
		$(".energys img").eq(0).addClass("show");
	}else{
		$(".energys img").eq(img2).addClass("show");
	}
}

//暴击清空
function hitClear(){
    lost = 0;
    $(".energys img").removeClass("show");
    $(".hit").removeClass("has_hit");
}

//能量初始化
function show_energy(num){
	$(".energys img").removeClass("show");
	lost = num;
	if (lost ==5) {
		$(".hit").addClass("has_hit");
	}
	var num2 = 4;
	for(var i=0;i<num;i++){
		$(".energys img").eq(num2).addClass("show");
		num2--;
	}
}

//暴击
function getDiamond() {
    $.ajax({
        type : 'post',
        url : getRootPath() + "/weixinMng/GameTwoC/getDiamond.htm",
        data: {},
        dataType : 'json',
        timeout : 10000000,
        beforeSend: function () {
            $(".loading").fadeIn();
        },
        complete: function (XMLHttpRequest) {
            $(".loading").fadeOut();
        },
        success : function(data, textStatus) {
            if(data.result==true){
                var diamonds = data.diamond;
                var remainDiamond = parseInt($("#remainDiamond").html())+parseInt(diamonds);
                $("#remainDiamond").html(remainDiamond);
                $("#impact").html(diamonds);
                $(".impact").fadeIn();
                lost = 0;
                $(".hit").removeClass("has_hit");
                $(".energys img").removeClass("show");
            }else{
                alert(data.message);
            }

        },
        //调用出错执行的函数
        error: function(XMLHttpRequest, textStatus, errorThrown){
            //请求出错处理
            alert("系统异常!"+textStatus);
        }
    });
}

//去充值
function gotoCharge() {
    window.location=getRootPath() + "/weixinMng/ManageC/rechargeIn.htm";
}

var prize = 0;
function appendPrize(text) {
    $(".prize li").eq(prize).text(text);
    if(prize == ($(".prize li").length-1) ){
        prize = 0;
    }else {
        prize++;
    }
}


