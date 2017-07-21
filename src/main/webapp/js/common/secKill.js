$(function () {
    var btn = true;
    var bool;
    var request = {
        "url": 'web-execute.action?execute=isGetAward',
        "data": {},
        "needValidate": false,
        "invokeMethod": addSuccess
    };
    $.sendRequest(request);
    function addSuccess(getData) {
        var str = getData.returnData;
        bool = str.split("_")[0] == "true";
        //if(!bool){
        //    alerw("对不起,您已经成功秒杀过了！");
        //}
        $("#giftNum").text("").text(str.split("_")[1])

    }


    function myDate() {
        var d = new Date();
        var d2 = new Date();
        var d3 = new Date();
        var d4 = new Date();
        var d5 = new Date();
        var d6 = new Date();
        var d7 = new Date();
        var d8 = new Date();
        d2.setDate(d.getDate() + 1);
        d2.setHours(9);
        d2.setMinutes(0);
        d2.setSeconds(0);
        d3.setHours(9);
        d3.setMinutes(0);
        d3.setSeconds(0);
        d4.setHours(15);
        d4.setMinutes(0);
        d4.setSeconds(0);
        d5.setHours(23);
        d5.setMinutes(59);
        d5.setSeconds(59);
        d6.setHours(0);
        d6.setMinutes(0);
        d6.setSeconds(1);
        d7.setHours(12);
        d7.setMinutes(0);
        d7.setSeconds(0);
        d8.setHours(18);
        d8.setMinutes(0);
        d8.setSeconds(0);
        var test = d3.getTime() < d.getTime() && d.getTime() < d7.getTime();
        var test1 = d4.getTime() < d.getTime() && d.getTime() < d8.getTime();
        //console.log(d4.getTime()<d.getTime()&&d.getTime()<d5.getTime());
        //console.log(d6.getTime()<d.getTime()&&d.getTime()<d3.getTime());
        if (d3.getTime() < d.getTime() && d.getTime() < d4.getTime()) {
            var x = d4.getTime() - d.getTime();
            var x2 = x - d.setTime(x / 60 / 60 / 1000) * 60 * 60 * 1000;
            var x3 = x2 - d.setTime(x2 / 60 / 1000) * 60 * 1000;
            var youHour = d.setTime(x / 60 / 60 / 1000);
            var youMinite = d.setTime(x2 / 60 / 1000);
            var youSecond = d.setTime(x3 / 1000);
            if (test) {
                //$(".wordTwo").html("").html("00时" + "00分" + "00秒");
                $(".firstLine").css("display","none");
                if (bool) {
                    $(".secKillNow").addClass("add-secKillNow");
                    $(".add-secKillNow").click(function () {
                        if (btn) {
                            var request = {
                                "url": 'web-execute.action?execute=myGetAward',
                                "data": {},
                                "needValidate": false,
                                "invokeMethod": addSuccess
                            };
                            $.sendRequest(request);
                            function addSuccess(getData) {
                                //$("#giftNum").html("").html(getData.returnData["num"]);
                                if (getData.returnData == undefined || getData.returnData.length == 0) {
                                    alerw("<p style='line-height:45px;font-size:36;color: #000;width: 440px;text-align: left;'>"+"手慢一步,但别气馁,下次继续!点击“分享好友”，喊上你的好友一起秒杀吧!"+"</p>", "分享好友", function () {
                                        showShareTip();
                                    })
                                } else {
                                    alerw("<p style='line-height:45px;font-size:36;color: #000;width: 440px;text-align: left;'>"+"恭喜您!成功秒杀到“本来生活”粽子礼包一份！！点击下方按钮，查看奖品详情及兑换流程！！！"+"</p>", "查看奖品", function () {
                                        location.href = "/DragonBoat/web-execute.action?goPage=share.jsp";
                                    })
                                }
                            }

                            btn = false;
                            //$(".secKillNow").removeClass("add-secKillNow");
                        }

                    });
                }
            } else {
                $(".firstLine").css("display","block");
                $(".wordTwo").html("").html(youHour + "时" + youMinite + "分" + youSecond + "秒");
                $(".secKillNow").removeClass("add-secKillNow");
            }

        } else if (d4.getTime() < d.getTime() && d.getTime() < d5.getTime()) {
            var o = d2.getTime() - d.getTime();
            var o2 = o - d.setTime(o / 60 / 60 / 1000) * 60 * 60 * 1000;
            var o3 = o2 - d.setTime(o2 / 60 / 1000) * 60 * 1000;
            var myHour = d.setTime(o / 60 / 60 / 1000);
            var myMinite = d.setTime(o2 / 60 / 1000);
            var mySecond = d.setTime(o3 / 1000);
            if (test1) {
                //$(".wordTwo").html("").html("00时" + "00分" + "00秒");
                $(".firstLine").css("display","none");
                if (bool) {
                    $(".secKillNow").addClass("add-secKillNow");
                    $(".add-secKillNow").click(function () {

                        if (btn) {
                            var request = {
                                "url": 'web-execute.action?execute=myGetAward',
                                "data": {},
                                "needValidate": false,
                                "invokeMethod": addSuccess
                            };
                            $.sendRequest(request);
                            function addSuccess(getData) {
                                //$("#giftNum").html("").html(getData.returnData["num"]);
                                if (getData.returnData == undefined || getData.returnData.length == 0) {
                                    alerw("<p style='line-height:45px;font-size:36;color: #000;width: 440px;text-align: left;'>"+"手慢一步,但别气馁,下次继续!点击“分享好友”，喊上你的好友一起秒杀吧!"+"</p>", "分享好友", function () {
                                        showShareTip();
                                    })
                                } else {
                                    alerw("<p style='line-height:45px;font-size:36;color: #000;width: 440px;text-align: left;'>"+"恭喜您!成功秒杀到“本来生活”粽子礼包一份！！点击下方按钮，查看奖品详情及兑换流程！！！"+"</p>", "查看奖品", function () {
                                        location.href = "/DragonBoat/web-execute.action?goPage=share.jsp";
                                    })
                                }
                            }

                            btn = false;
                            //$(".secKillNow").removeClass("add-secKillNow");
                        }
                    });
                }
            } else {
                $(".firstLine").css("display","block");
                $(".wordTwo").html("").html(myHour + "时" + myMinite + "分" + mySecond + "秒");
                $(".secKillNow").removeClass("add-secKillNow");
            }
        } else if (d6.getTime() < d.getTime() && d.getTime() < d3.getTime()) {
            var y = d3.getTime() - d.getTime();
            var y2 = y - d.setTime(y / 60 / 60 / 1000) * 60 * 60 * 1000;
            var y3 = y2 - d.setTime(y2 / 60 / 1000) * 60 * 1000;
            var heHour = d.setTime(y / 60 / 60 / 1000);
            var heMinite = d.setTime(y2 / 60 / 1000);
            var heSecond = d.setTime(y3 / 1000);
            $(".wordTwo").html("").html(heHour + "时" + heMinite + "分" + heSecond + "秒");
        }

    }

    setInterval(function () {
        myDate();
    }, 1000);


    $(".myRewards").click(function () {
        if ($("#islottery").val() == "1") {
            location.href = "/DragonBoat/web-execute.action?goPage=share.jsp";
        }
        else {
            alerw("您还没中奖!");
        }
    });
});