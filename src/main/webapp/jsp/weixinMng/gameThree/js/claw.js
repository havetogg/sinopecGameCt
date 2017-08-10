//初始化
var lost=0;

$(function(){
	//初始化循环判断奖品
	setInterval(function(){
	   findCar();
	   //console.log($(".thisCar").offset().left);
	},300);
	
	//点击开始游戏
	$("footer .btn2").on("click",function(){
		if (!$(this).hasClass("cur")) {
			Play();
			ClearPlay();
		}
	});
	
	//关闭弹窗
	$(".close").on("click",function(){
		$(this).parent().parent().fadeOut();
	})
	
	//关闭引导
	$(".know").on("click",function(){
		$(".guide").fadeOut();
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
	
	//切换金额
	$("footer .btn1").on("click",function(){
		if(!$("footer .btn2").hasClass("cur")){
            $(".loading").fadeIn();
			var index = $(this).index();
			$(this).addClass("cur").siblings().removeClass("cur")
			//console.log(index);
			var level = "";
			if (index == 0) {
				toJunior();
				level = "primary";
			} else if (index == 1) {
				toMedium();
                level = "middle";
			}else{
				toSenior();
                level = "high";
			}
            $.ajax({
                type : 'post',
                url : getRootPath() + "/weixinMng/GameThreeC/switchLevel.htm",
                data: {level:level},
                dataType : 'json',
                timeout : 10000000,
                success : function(data, textStatus) {
                    $(".loading").fadeOut();
                }
            });
		}
	})

	//我知道了
    $("#iknow").on("click", function () {
        var $this  = $("#know");
        if($this.is(":checked")){
            //选中的时候你的操作
            $.ajax({
                type : 'post',
                url : getRootPath() + "/weixinMng/GameThreeC/iKnow.htm",
                data: {},
                dataType : 'json',
                timeout : 10000000,
                success : function(data, textStatus) {
                	alert(data);
                }
            });
        }
        $(".guide").hide();
    });

	//暴击点击
	$(".energy").on("click",function () {
		if(lost == 5){
            $(".loading").fadeIn();
            $.ajax({
                type : 'post',
                url : getRootPath() + "/weixinMng/GameThreeC/getDiamond.htm",
                data: {},
                dataType : 'json',
                timeout : 10000000,
                success : function(data, textStatus) {
                	if(data.result){
                        $(".loading").fadeOut();
                        $("#diamond").html(data.diamond);
                        $(".impact").fadeIn();
                        hitClear();
					}else{
                		alert(data.message);
					}

                }
            });

		}

    });

    var energyNum = parseInt($("#energyNum").val());
    show_energy(energyNum);
		
})

//切换初级场
function toJunior(){
	//切换背景
	$(".main").addClass("junior").removeClass("medium senior");
	//切换爪子
	$(".machine").removeClass("medium senior");
	//切换头部
	$("header .junior").show().siblings().hide();
	//切换奖品
	var Junior = "<label>Beats耳机</label>1副"
	$(".before .tip1 li.car1").eq(0).find("label").text("5颗");
	$(".before .tip1 li.car1").eq(1).find("label").text("50");
	$(".before .tip1 li.car1").eq(2).find("label").text("15颗");
	$(".before .tip1 li.car4").eq(0).find("label").text("10元");
	$(".before .tip1 li.car5").eq(0).find("label").text("10元");
	$(".before .tip1 li.car2").eq(0).find("label").text("50元");
	$(".before .tip1 li.car3").eq(0).html(Junior);
	
	$(".before .tip2 li.car1").eq(0).find("label").text("5颗");
	$(".before .tip2 li.car1").eq(1).find("label").text("50");
	$(".before .tip2 li.car1").eq(2).find("label").text("15颗");
	$(".before .tip2 li.car4").eq(0).find("label").text("10元");
	$(".before .tip2 li.car5").eq(0).find("label").text("10元");
	$(".before .tip2 li.car2").eq(0).find("label").text("50元");
	$(".before .tip2 li.car3").eq(0).html(Junior);
	
	$(".after .tip1 li.car1").eq(0).find("label").text("5颗");
	$(".after .tip1 li.car1").eq(1).find("label").text("50");
	$(".after .tip1 li.car1").eq(2).find("label").text("15颗");
	$(".after .tip1 li.car4").eq(0).find("label").text("10元");
	$(".after .tip1 li.car5").eq(0).find("label").text("10元");
	$(".after .tip1 li.car2").eq(0).find("label").text("50元");
	$(".after .tip1 li.car3").eq(0).html(Junior);
	
	$(".after .tip2 li.car1").eq(0).find("label").text("5颗");
	$(".after .tip2 li.car1").eq(1).find("label").text("50");
	$(".after .tip2 li.car1").eq(2).find("label").text("15颗");
	$(".after .tip2 li.car4").eq(0).find("label").text("10元");
	$(".after .tip2 li.car5").eq(0).find("label").text("10元");
	$(".after .tip2 li.car2").eq(0).find("label").text("50元");
	$(".after .tip2 li.car3").eq(0).html(Junior);
}

//切换中级场
function toMedium(){
	//切换背景
	$(".main").addClass("medium").removeClass("junior senior");
	//切换爪子
	$(".machine").addClass("medium").removeClass("senior");
	//切换头部
	$("header .medium").show().siblings().hide();
	//切换奖品
	var Medium = "<label>Ipad mini</label>1台"
	$(".before .tip1 li.car1").eq(0).find("label").text("10颗");
	$(".before .tip1 li.car1").eq(1).find("label").text("150");
	$(".before .tip1 li.car1").eq(2).find("label").text("50颗");
	$(".before .tip1 li.car4").eq(0).find("label").text("10元");
	$(".before .tip1 li.car5").eq(0).find("label").text("50元");
	$(".before .tip1 li.car2").eq(0).find("label").text("200元");
	$(".before .tip1 li.car3").eq(0).html(Medium);
	
	$(".before .tip2 li.car1").eq(0).find("label").text("10颗");
	$(".before .tip2 li.car1").eq(1).find("label").text("150");
	$(".before .tip2 li.car1").eq(2).find("label").text("50颗");
	$(".before .tip2 li.car4").eq(0).find("label").text("10元");
	$(".before .tip2 li.car5").eq(0).find("label").text("50元");
	$(".before .tip2 li.car2").eq(0).find("label").text("200元");
	$(".before .tip2 li.car3").eq(0).html(Medium);
	
	$(".after .tip1 li.car1").eq(0).find("label").text("10颗");
	$(".after .tip1 li.car1").eq(1).find("label").text("150");
	$(".after .tip1 li.car1").eq(2).find("label").text("50颗");
	$(".after .tip1 li.car4").eq(0).find("label").text("10元");
	$(".after .tip1 li.car5").eq(0).find("label").text("50元");
	$(".after .tip1 li.car2").eq(0).find("label").text("200元");
	$(".after .tip1 li.car3").eq(0).html(Medium);
	
	$(".after .tip2 li.car1").eq(0).find("label").text("10颗");
	$(".after .tip2 li.car1").eq(1).find("label").text("150");
	$(".after .tip2 li.car1").eq(2).find("label").text("50颗");
	$(".after .tip2 li.car4").eq(0).find("label").text("10元");
	$(".after .tip2 li.car5").eq(0).find("label").text("50元");
	$(".after .tip2 li.car2").eq(0).find("label").text("200元");
	$(".after .tip2 li.car3").eq(0).html(Medium);
}

//切换高级场
function toSenior(){
	//切换背景
	$(".main").addClass("senior").removeClass("junior medium");
	//切换爪子
	$(".machine").addClass("senior").removeClass("medium");
	//切换头部
	$("header .senior").show().siblings().hide();
	//切换奖品
	var Senior = "<label>Iphone 7</label>手机一部"
	$(".before .tip1 li.car1").eq(0).find("label").text("30颗");
	$(".before .tip1 li.car1").eq(1).find("label").text("300");
	$(".before .tip1 li.car1").eq(2).find("label").text("70颗");
	$(".before .tip1 li.car4").eq(0).find("label").text("50元");
	$(".before .tip1 li.car5").eq(0).find("label").text("50元");
	$(".before .tip1 li.car2").eq(0).find("label").text("500元");
	$(".before .tip1 li.car3").eq(0).html(Senior);
	
	$(".before .tip2 li.car1").eq(0).find("label").text("30颗");
	$(".before .tip2 li.car1").eq(1).find("label").text("300");
	$(".before .tip2 li.car1").eq(2).find("label").text("70颗");
	$(".before .tip2 li.car4").eq(0).find("label").text("50元");
	$(".before .tip2 li.car5").eq(0).find("label").text("50元");
	$(".before .tip2 li.car2").eq(0).find("label").text("500元");
	$(".before .tip2 li.car3").eq(0).html(Senior);
	
	$(".after .tip1 li.car1").eq(0).find("label").text("30颗");
	$(".after .tip1 li.car1").eq(1).find("label").text("300");
	$(".after .tip1 li.car1").eq(2).find("label").text("70颗");
	$(".after .tip1 li.car4").eq(0).find("label").text("50元");
	$(".after .tip1 li.car5").eq(0).find("label").text("50元");
	$(".after .tip1 li.car2").eq(0).find("label").text("500元");
	$(".after .tip1 li.car3").eq(0).html(Senior);
	
	$(".after .tip2 li.car1").eq(0).find("label").text("30颗");
	$(".after .tip2 li.car1").eq(1).find("label").text("300");
	$(".after .tip2 li.car1").eq(2).find("label").text("70颗");
	$(".after .tip2 li.car4").eq(0).find("label").text("50元");
	$(".after .tip2 li.car5").eq(0).find("label").text("50元");
	$(".after .tip2 li.car2").eq(0).find("label").text("500元");
	$(".after .tip2 li.car3").eq(0).html(Senior);
}

//标记可被抓车辆
function findCar(){
	$(".before ul li").each(function(){
		var l1 = $(this).offset().left;
		var l2 = $(".main").width();
		var left =parseInt(l1*100/l2);
		//console.log(left);
		if(left >= 27 && left<=33){
			//$(".before ul li").removeClass("thisCar");
			$(this).addClass("thisCar");
			
		}else{
			$(this).removeClass("thisCar");
		}
	});
}

//游戏开始
function Play(){
	//按钮点击
	$("footer .btn2").addClass("cur");
	//爪子下降
	$(".machine .line").addClass("down");
	$(".machine .handle").addClass("down");
	
	setTimeout(function () {
		var num = $(".thisCar").length;
		if (num==1) {
			var text = $(".thisCar").html();
			var cla = $(".thisCar").attr("class");
			var cla2 = cla.split(" ")[0]
			//console.log(cla.split(" ")[0]);
			$(".machine .hideCar li").addClass(cla2);
			$(".thisCar").addClass("removeCar");
			$(".machine .hideCar li").html(text);
			$(".win .text").html(text);
			$(".machine .hideCar").addClass("cur");
			setTimeout(function () {
				$(".machine .hideCar li").removeClass(cla2);
			}, 5000);
			
			//抓到奖品了
			setTimeout(function () {
				//奖品抖动
				$(".machine .hideCar li").addClass("sway");
				//判断概率
					//成功
				setTimeout(function () {
					$(".win").fadeIn();
				}, 2000);
					//失败
				/*setTimeout(function () {
					$(".machine .hideCar").addClass("down");
					setTimeout(function () {
						$(".lost").fadeIn();
						hit();
					}, 1000);
				}, 1000);*/
			}, 2000);
		}else{
			//抓空了
			setTimeout(function () {
				$(".lost").fadeIn();
				hit();
			}, 2000);
		}
	}, 1200);
}

//清除开始状态
function ClearPlay(){
	//延迟清除状态
	setTimeout(function () {
		$("footer .btn2").removeClass("cur");
        $(".machine .line").removeClass("down");
        $(".machine .handle").removeClass("down");
        
    }, 3000);
    setTimeout(function(){
    	$(".machine .hideCar").removeClass("cur");
    	$(".removeCar").removeClass("removeCar");
    	$(".machine .hideCar li").removeClass("sway");
    	$(".machine .hideCar").removeClass("down");
    }, 5000)
}


//能量初始化
function show_energy(initNum){
    lost = initNum;
    var num = "lost"+lost;
    $(".energy .hand").removeClass("lost1 lost2 lost3 lost4 lost5").addClass(num);
}

//暴击记次
function hit(){
	if (lost <5) {
		lost +=1;
		var num = "lost"+lost;
		$(".energy .hand").removeClass("lost1 lost2 lost3 lost4 lost5").addClass(num);
	}
}

//暴击清空
function hitClear(){
	lost = 0;
	$(".energy .hand").removeClass("lost1 lost2 lost3 lost4 lost5");

}



