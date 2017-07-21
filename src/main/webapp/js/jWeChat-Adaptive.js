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

    var tw = Math.ceil(w / scale);
    var th = Math.ceil(h / scale);

    $(".zoomer").css({
        "-webkit-transform-origin": "0 0",
        "-moz-transform-origin": "0 0",
        "-ms-transform-origin": "0 0",
        "-o-transform-origin": "0 0",
        "transform-origin": "0 0"
    });
    $(".zoomer").css({
        "-webkit-transform": "scale(" + scale + "," + scale + ")",
        "-moz-transform": "scale(" + scale + "," + scale + ")",
        "-ms-transform": "scale(" + scale + "," + scale + ")",
        "-o-transform": "scale(" + scale + "," + scale + ")",
        "transform": "scale(" + scale + "," + scale + ")"
    });

    $("html").css({"width": w});
    $("body").css({"width": w});
    $(".zoomer").css({"width": tw});
    $(".zoomer").css({"height": th});

    if($("div.full-screen").length > 0){
        $("html").css({"height": h});
        $("body").css({"height": h});
        $(".zoomer").css({"height": th});
        $(".wrap").css({"height": th});
    }
    /*
    *   在页面上制定了全屏时，再应用高度值
    * */

});
