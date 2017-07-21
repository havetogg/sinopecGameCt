/**
 *
 *  build by rwson @ 2015-01-10
 *
 *  完成微信端的一些自适应屏幕功能(css3中的缩放特性)
 *
 */

$(function () {

    var phoneWidth = parseInt(window.screen.width),
        phoneScale = phoneWidth / 640,
        ua = navigator.userAgent;
    if (/Android/.test(ua)) {
        if (/Android (\d+\.\d+)/.test(ua)) {
            var version = parseFloat(RegExp.$1);
            $("head").append('<meta name="viewport" content="initial-scale=0.5,maximum-scale=0.5,minimum-scale=0.5, width=640, target-densitydpi=device-dpi">');
        } else {
            $("head").append('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
        }
    } else {
       
       $("head").append('<meta name="viewport" content="initial-scale=0.5,maximum-scale=0.5,minimum-scale=0.5, user-scalable=no">');
    
    }

    var w = $(window).width();
    var h = $(window).height();

    var scale = w / 640;
    window.win_scale = scale;
    var tw = Math.ceil(w / scale);
    var th = Math.ceil(h / scale);
//缩放原点
    $(".zoomer,body>div#win_t,.fixed_1").css({
        "-webkit-transform-origin": "0 0",
        "-moz-transform-origin": "0 0",
        "-ms-transform-origin": "0 0",
        "-o-transform-origin": "0 0",
        "transform-origin": "0 0"
    });
//缩放
    $(".zoomer,body>div#win_t,.fixed_1").css({
        "-webkit-transform": "scale(" + scale + "," + scale + ")",
        "-moz-transform": "scale(" + scale + "," + scale + ")",
        "-ms-transform": "scale(" + scale + "," + scale + ")",
        "-o-transform": "scale(" + scale + "," + scale + ")",
        "transform": "scale(" + scale + "," + scale + ")"
    });

    $("html").css({"width": w});
    $("body").css({"width": w});
    $("body").css({"height": h});
    $(".zoomer").css({"width": tw});
    $(".zoomer").css({"min-height": th});
    /*
     *   在页面上制定了全屏时，再应用高度值
     * */
    var imgs = [
        "img/common/loading.gif",
        "img/common/pop_close.png"
    ];

    //preloadImage(imgs, function () {});
    //jxTool.musicPlayer();
});

function closeAlertMessage() {
    $("#alert_message").remove();
}
function showAlertM(str) {
    $("#alert_message").remove();
    var o = $("<div id=\"alert_message\"><div><span>" + str + "</span><button ontouchend=\"closeAlertMessage()\">确定</button></div></div>");
    $("body").append(o);
    $("#alert_message").attr("class", "alert_message_animate");
}

function getUrlParam(name) {
    //构造一个含有目标参数的正则表达式对象
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    //匹配目标参数
    var r = window.location.search.substr(1).match(reg);
	//alert(unescape(r[2]))
    //返回参数值
    if (r != null) return unescape(r[2]);
    return null;
}

function alerw(titletext, btext, okFunction) {
    with (window.top.document) {

        if ($("#alerw")[0]) {
            $("#alerw").remove();
            $('body').css("overflow", "normal");

        }
//        if (content == null || String(content) == "") {
//            content = "发生错误，请重试";
//        }
        if (titletext == null || String(titletext) == "") {
            titletext = "注意";
        }

        if (btext == null || String(btext) == "") {

            btext = "确定";
        }

        $("body").append("<div id='alerw'></div>");
        $("#alerw").hide();
        $("#alerw").append("<div id='alerw_bg'></div>");
        $("#alerw").append("<div id='alerw_div'></div>");
        $("#alerw_div").append("<div class='title'>" + titletext + "</div>");
//        $("#alerw_div").append("<div class='content'>" + content + "</div>");
        $("#alerw_div").append("<a class='ab ab_one ab_ok' href='javascript:;'>" + btext + "</a>");
        $('body').css("overflow", "hidden");
        $("#alerw").animate({opacity: 'show'}, 100, "", function () {
            $("#alerw").focus();
        });

        $("#alerw_div .wx_close").click(function () {
            $("#alerw").animate({opacity: 'hide'}, 100);
        });

        $("#alerw_div .ab_ok").click(function () {
            $("#alerw").animate({opacity: 'hide'}, 100, "", function () {
                $('body').css("overflow", "auto");
                if (okFunction) {
                    okFunction();
                }
            });
        });

        $("#alerw").bind("keypress", function (e) {
            var ev = document.all ? window.event : e;
            //alert(1);
            if (ev.keyCode == 13) {
                $("#alerw_div .ab_ok").addClass("active");
                $("#alerw_div .ab_ok").click();
            }
        });

    }

}
function loading(state, callback) {
    if (state == "start") {

        $("body").prepend('<div id="loading"><div id="loading_bg"></div><div id="loading_div"><div id="loading_pic"></div></div></div>');
        callback;
    } else if (state == "stop") {
        $("#loading").remove();

    }

}

function fitimg(img) {

    var newimg = new Image();
    newimg.src = img[0].src;

    $(newimg).load(function () {
        console.log(newimg.width)
        if (newimg.width > newimg.height) {
            img.height("100%")
        } else {
            img.width("100%")
        }
    });


}

function vote(id, activityType, callbackfunction) {
    $.get("voteC/voteM.htm", {
        "activityContentID": id,
        "activityType": activityType
    }, function (resulte) {

        if (resulte.code == 1) {
            callbackfunction()
        } else {
            alert(resulte.msg)
        }

    }, "json");
}

//微信jssdk初始化
function wxjsinit(callbackFunction) {
	/*

    $.get("weChatJSConfigC/getWeCharJSConfigM.htm", {
        "currUrl": location.href
    }, function (result) {

        if (result.code == 1) {

            if (result.resultObject) {

                result.resultObject.debug = false;

                wx.config(result.resultObject);

                wx.ready(function () {

                    wx.checkJsApi({
                        jsApiList: result.resultObject.jsApiList,
                        success: function (res) {
                            callbackFunction();
							
                        }
                    });

                    wx.error(function (res) {
                        alert(res.errMsg);
                    });
                })
            }

        } else {
            alerw(result.msg)
        }


    }, "json")*/

}

function wxshare(data) {
    wx.onMenuShareAppMessage({
        title: data.title,
        desc: data.desc,
        link: data.link,
        imgUrl: data.imgUrl,
        trigger: function (res) {
        },
        success: function (res) {
        },
        cancel: function (res) {
        },
        fail: function (res) {
        }
    });
    wx.onMenuShareTimeline({
        title: data.desc,
        link: data.link,
        imgUrl: data.imgUrl,
        trigger: function (res) {
        },
        success: function (res) {
        },
        cancel: function (res) {
        },
        fail: function (res) {
        }
    });
}