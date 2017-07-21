/*
function wxjsinit(callbackFunction) {

    $.get(getRootPath()+"/weChatJSConfigC/getWeCharJSConfigM.htm", {
        "currUrl": location.href
    }, function (result) {
    	alert(JSON.stringify(result));
        if (result.code == 1) {
            alert(JSON.stringify(result.resultObject));
            if (result.resultObject) {
                result.resultObject.debug = false
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


    }, "json")

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
}*/
