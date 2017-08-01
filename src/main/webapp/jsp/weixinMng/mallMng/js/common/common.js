/**
 *
 *  build by rwson @ 2015-01-10
 *
 *  完成微信端的一些自适应屏幕功能(css3中的缩放特性)
 *
 */





//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath() {
    // 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    // 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    // 获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    // 获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName
        .substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}
//获取网址里的name参数
function getUrlParam(name) {
    //构造一个含有目标参数的正则表达式对象
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    //匹配目标参数
    var r = window.location.search.substr(1).match(reg);
    //alert(unescape(r[2]))
    //返回参数值.
    if (r != null) return unescape(r[2]);
    return null;
}
//var totalMoneys = getUrlParam("name");   页面获取参数

//t弹框页面
var html2= "  <div  id=\"ad_id2\" class=\"advertisement_bg\">"
    + "        <div class=\"tip-cont1-\">"
    + "            <img src=\""+getRootPath()+"/jsp/weixinMng/mallMng/img/ad_bg2.png\" alt=\"\">"
    // + "        <a class=\"ad_toBTN\" href=\"https://prodone.juxinbox.com/sinopecGameCt/weixinMng/ManageC/rechargeIn.htm\">去充值</a>"
    + "            <div class=\"ad_little_img\"><img src=\""+getRootPath()+"/jsp/weixinMng/mallMng/img/closebtn.png\" alt=\"\" onclick=\"closeAd('ad_id2')\"></div>"
    + "        </div>"
    + "  </div>";
function ad_tip2(state) {
    if(state == 'show'){
        document.body.style.position='fixed';
        $('.zoomer').prepend(html2);
    }else if(state=='hide'){
        $('#ad_id2').remove();
        document.body.style.position='absolute';
    }
}
function closeAd(id) {
    $("#"+id).hide();
    document.body.style.position='absolute';
}
//分享功能
$(function () {

   /* $(".alertTip1").click(function(){
        $(".alertTip1").css("display","none")
    });
    var shareData = {};
    var shareFriends = {};
    $.ajax({
        url: getRootPath() + "/wechat/weChatJSConfigC/getWeCharJSConfigM.htm",
        data: {currUrl: location.href},
        dataType: "json",
        success: function (config) {
            wx.config($.parseJSON(config.resultObject));
            shareData = {
                title: "车答应9折加油啦",
                desc: "我正在参加车答应的九折加油活动，都抢疯啦！",
                link: getRootPath(),
                imgUrl: getRootPath() + '/img/share.png',
                // linkpath + '/share.jsp='+wxId+'&id='+currentId;
                trigger: function (res) {
                    //alert('用户点击发送给朋友');
                },
                success: function (res) {
                    //alert('已分享');
                    closeShareTip();
                },
                cancel: function (res) {
                    //alert('已取消');
                },
                fail: function (res) {
                    //alert("this is "+JSON.stringify(res));
                }
            };
            shareFriends = {
                title: "我正在参加车答应的九折加油活动，都抢疯啦！ ",
                link: getRootPath(),
                imgUrl: getRootPath() + '/img/share.png',
                // linkpath + '/share.jsp='+wxId+'&id='+currentId;
                trigger: function (res) {
                    //alert('用户点击发送给朋友');
                },
                success: function (res) {
                    //alert('已分享');
                    closeShareTip();
                },
                cancel: function (res) {
                    //alert('已取消');
                },
                fail: function (res) {
                    //alert("this is "+JSON.stringify(res));
                }
            };
            wx.ready(function () {
                wx.onMenuShareAppMessage(shareData);
                wx.onMenuShareTimeline(shareFriends);
            });
            wx.error(function (res) {
                //alert(JSON.stringify(res));
            });
        },
        error: function (json) {
            //alert(JSON.stingify(json));
        }
    });*/



});
