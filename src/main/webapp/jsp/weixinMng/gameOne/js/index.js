$(document).ready(function() {
    /*var needRefresh = sessionStorage.getItem("need-refresh-one");
    if(needRefresh){
        sessionStorage.removeItem("need-refresh-one");
        reloadindex();
    }*/
    var isPageHide = false;
    window.addEventListener('pageshow', function () {
        if (isPageHide) {
            window.location.reload();
        }
    });
    window.addEventListener('pagehide', function () {
        isPageHide = true;
    });

    //关闭弹窗
	$(".close").on("click", function() {
		$(this).parent().parent().fadeOut();
	});
	
	//排行榜
	$(".chart").on("click", function() {
		$(".chart_list").fadeIn();
	});
	
})

function startGame(usermodetype){
    $.ajax({
 		type : 'post',
 		url : getRootPath() + "/weixinMng/GameOneC/startGame.htm",
           data: {
              "USERMODETYPE": usermodetype
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
           		alert("ajax-timeout");
           		return;
           	}
 			if(data.code==1){
 				sessionStorage.setItem("need-refresh-one", true);
               if(4==usermodetype){
            	    sessionStorage.removeItem("need-refresh-game2");
            	   // 新手
            	    var url=getRootPath() + "/weixinMng/GameOneC/gameOneGame2In.htm";
            	    window.location.href = url;
            		/*var form = $('<form id="submitForm" action="" method="POST">');
            		form.attr("action",url);
            		form.submit(); */
                    
               }else{
            	   sessionStorage.removeItem("need-refresh-game1");
            	   // 老手
            	    var url=getRootPath() + "/weixinMng/GameOneC/gameOneGame1In.htm";
                   	window.location.href = url;
            		/*var form = $('<form id="submitForm" action="" method="POST">');
            		form.attr("action",url);
            		form.submit();*/
               } 				
 			}else if(data.code==2){
 				$(".noTimes").fadeIn();
 			}else{
 				alerw(data.msg);
 			}
 		},
         //调用出错执行的函数
         error: function(XMLHttpRequest, textStatus, errorThrown){
               //请求出错处理
             alert("系统异常!"+textStatus);
         } 
 	});
}

// 重新ajax加载页面
function reloadindex(){
    $.ajax({
 		type : 'post',
 		url : getRootPath() + "/weixinMng/GameOneC/getgameOneInfo.htm",
           data: {
              
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
           		alert("ajax-timeout");
           		return;
           	}
 			if(data.code==1){
 				var USERBEAN =data.resultObject.USERBEAN;
 				var GAMEONEBEAN =data.resultObject.GAMEONEBEAN;
 				$(".toGame span").html(GAMEONEBEAN.USERGAMETYPEMODE4.REMAIN_GAME_TIMES);  // 游戏次数
 				$(".toExplain span").html(GAMEONEBEAN.USERGAMETYPEMODE5.REMAIN_GAME_TIMES);// 游戏次数
 				$(".gold").html(USERBEAN.REMAIN_GOLD);
 				$(".diamond").html(USERBEAN.GAMEONEBEAN);
 			}else{
 				alerw(data.msg);
 			}
 		},
         //调用出错执行的函数
         error: function(XMLHttpRequest, textStatus, errorThrown){
               //请求出错处理
             alert("系统异常!"+textStatus);
         } 
 	});
}

//增加游戏次数
function buytimesmoneypay_qinag(usermodetype){
    $.ajax({
 		type : 'post',
 		url : getRootPath() + "/weixinMng/GameOneC/gameOneTimesBuy.htm",
           data: {
              "BUY_FLAG": 0,
              "BUYUSERMODETYPE":usermodetype
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
           		alert("ajax-timeout");
           		return;
           	}
 			if(data.code==1){
 				var NEEDDOM =data.resultObject.NEEDDOM;
 				$("#buytimesmoneyid").html(NEEDDOM+"枚钻石，确认购买吗？");
 				$(".buyTime").fadeIn();
 				
 				$("#buytimesmoneypayid").attr("onclick","buytimesmoneypay("+usermodetype+")");
 				
 			}else if(data.code==2){
 				$(".buyTime").fadeOut();
 				$(".noDiamond").fadeIn();
 			}else{
 				alerw(data.msg);
 			}
 		},
         //调用出错执行的函数
         error: function(XMLHttpRequest, textStatus, errorThrown){
               //请求出错处理
             alert("系统异常!"+textStatus);
         } 
 	});
}

//fengsy
//购买次数确认
function buytimesmoneypay(usermodetype) {
  $.ajax({
		type : 'post',
		url : getRootPath() + "/weixinMng/GameOneC/gameOneTimesBuy.htm",
         data: {
            "BUY_FLAG": 1,
            "BUYUSERMODETYPE":usermodetype
         },
		dataType : 'json',
 		async: false,
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
         		alert("ajax-timeout");
         		return;
         	}
			if(data.code==1){
				$(".buyTime").fadeOut();
				var NEEDDOM =data.resultObject.NEEDDOM;
				
				$(".success").fadeIn();
			}else if(data.code==2){
				$(".buyTime").fadeOut();
 				$(".noDiamond").fadeIn();
 			}else{
				alerw(data.msg);
			}
		},
       //调用出错执行的函数
       error: function(XMLHttpRequest, textStatus, errorThrown){
             //请求出错处理
           alert("系统异常!"+textStatus);
       } 
	});
}


//钻石不够,弹框确认
function noDiamondOk(){
	$(".noDiamond").fadeOut();
}

// 游戏机会用完,弹框确认
function noTimesOk(){
	$(".noTimes").fadeOut();
}

//购买成功,弹框确认
function buysucessOk(){
	$(".success").fadeOut();
    reloadTogameOneIn(); 
}

//fengsy
function reloadTogameOneIn(){
    var url=getRootPath() + "/weixinMng/GameOneC/gameOneIn.htm";
	var form = $('<form id="submitForm" action="" method="POST">');
	form.attr("action",url);
	form.submit(); 
}

//fengsy
function gotoGameIndex(){
    window.location=getRootPath() + "/weixinMng/ManageC/userIn.htm";
}


