$(function(){
    var shareData = {};
    var shareFriends={};
    $.ajax({
        url: getRootPath()+"/weChatJSConfigC/getWeCharJSConfigM.htm",
        data: {currUrl: location.href},
        dataType: "json",
        success: function (config) {
            wx.config(config.resultObject);
            shareData = {
                title: '中石化游戏中心,中奖中的我都不好意思了！',
                desc: "中石化游戏中心震撼上线玩加油达人，扭蛋机就有机会中iphone7",
                link: getRootPath()+"/weixinMng/ManageC/userIn.htm",
                imgUrl: getRootPath()+'/jsp/weixinMng/mallMng/img/common/share.jpg',
                // linkpath + '/share.jsp='+wxId+'&id='+currentId;
                trigger: function (res) {
                    //alert('用户点击发送给朋友');
                },
                success: function (res) {
                	
                },
                cancel: function (res) {
//                    alert('已取消');
                },
                fail: function (res) {
//                    alert("this is "+JSON.stringify(res));
                }
            };
            shareFriends = {
                title: '中石化游戏中心,中奖中的我都不好意思了！',
                desc: "中石化游戏中心震撼上线玩加油达人，扭蛋机就有机会中iphone7",
                link: getRootPath()+"/weixinMng/ManageC/userIn.htm",
                imgUrl: getRootPath()+'/jsp/weixinMng/mallMng/img/common/share.jpg',
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
            wx.ready(function(){
                wx.onMenuShareAppMessage(shareData);
                wx.onMenuShareTimeline(shareFriends);
            });
            wx.error(function(res){
                //alert(JSON.stringify(res));
            });
        },
        error: function (json) {
            //alert(JSON.stingify(json));
        }
    });


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
});

