
/*
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
                title: '中石化"加油金"限量天天抢(内藏彩蛋大奖).',
                desc: "更有其他多种福利等你来抢~先到先得！",
                link: getRootPath()+"/weixinMng/ManageC/userIn.htm?FROMOPENID="+openID,
                imgUrl: getRootPath()+'/jsp/weixinDraw/img/share.png',
                // linkpath + '/share.jsp='+wxId+'&id='+currentId;
                trigger: function (res) {
                    //alert('用户点击发送给朋友');
                },
                success: function (res) {
                	$.ajax({
                		url:getRootPath()+'/weixinMng/ManageC/afterShare.htm',
                		type:'post',
                		dataType:'json',
                		success:function(data){}
                	});
                    alert('已分享');
                    closeShareTip();
                },
                cancel: function (res) {
//                    alert('已取消');
                },
                fail: function (res) {
//                    alert("this is "+JSON.stringify(res));
                }
            };
            shareFriends = {
                title: '中石化"加油金"限量天天抢(内藏彩蛋大奖).',
                link: getRootPath()+"/weixinMng/ManageC/userIn.htm?FROMOPENID="+openID,
                imgUrl: getRootPath()+'/jsp/weixinDraw/img/share.png',
                
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
                alert(JSON.stringify(res));
            });
        },
        error: function (json) {
            alert(JSON.stingify(json));
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
*/

