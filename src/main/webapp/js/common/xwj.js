document.addEventListener('DOMContentLoaded',function(){
    var imgArr = [
        "img/common/loading.gif",
        "img/common/pop_close.png"
    ];
    _PAGE.preloadImg(imgArr, function () {
    });
},true);
/////////////////////////
function showShareTip_2(type) {
    var pictype = type || "share";
    var tipdom = $('<div id="jxtip">' +
    '<img src="http://www.juxinbox.com/img/jxshare/jx' + pictype + '.png" />' +
    '<a class="jxtiplogo"></a>' +
    '</div>');
    tipdom.css({
        "width": "100%",
        "height": "100%",
        "backgroundColor": "rgba(102,102,102,0.98)",
        "position": "fixed",
        "left": "0",
        "top": "0",
        "zIndex": "99999"
    });
    $("img", tipdom).css({
        "width": "100%",
        "position": "absolute"
    });
    $(".jxtiplogo", tipdom).css({
        "display": "block",
        "width": "188px",
        "height": "20px",
        "backgroundSize": "contain",
        "position": "absolute",
        "marginLeft": "-94px",
        "left": "50%",
        "bottom": "40px"
    });
    tipdom.click(function () {
        $(this).remove();
    });
    $("body").append(tipdom);
}
//////////////////////////////////
(function(){
    //命名空间
    window._PAGE = {};
    /**
     * 缓冲菊花
     * a,b,c三种菊花任你爱
     */
    _PAGE.__load = {
        a:{
            load:function(){
                var newDom = document.getElementById("_buffer_");
                if( !newDom || (newDom.parentNode.nodeName.toUpperCase() != "BODY") ){
                    newDom = document.createElement("div");
                    newDom.id = "_buffer_";
                    document.body.insertBefore(newDom,null);
                }
                newDom.style.display = "block";
            },
            finish:function(){
                document.getElementById("_buffer_").style.display = "none";
            }
        },
        b:{
            timer:1,
            load:function( seconds ){
                var newDom = document.getElementById("_buffer_");
                if( !newDom || (newDom.parentNode.nodeName.toUpperCase() != "BODY") ){
                    newDom = document.createElement("div");
                    newDom.id = "_buffer_";
                    document.body.insertBefore(newDom,null);
                }
                clearTimeout(this.timer);
                newDom.style.display = "block";
                this.timer = setTimeout(function(){
                    newDom.style.display = "none";
                },( seconds && seconds>3000 )?seconds:30000);
            },
            finish:function(){
                document.getElementById("_buffer_").style.display = "none";
            }
        },
        c:{
            timer:1,
            load:function( seconds ){
                var newDom = document.getElementById("_buffer2_");
                if( !newDom || (newDom.parentNode.nodeName.toUpperCase() != "BODY") ){
                    newDom = document.createElement("div");
                    newDom.id = "_buffer2_";
                    document.body.insertBefore(newDom,null);
                }
                clearTimeout(this.timer);
                newDom.style.display = "block";
                this.timer = setTimeout(function(){
                    newDom.style.display = "none";
                },( seconds && seconds>3000 )?seconds:30000);
            },
            finish:function(){
                document.getElementById("_buffer2_").style.display = "none";
            }
        }
    };
    //预加载图片
    _PAGE.preloadImg = function(imgurls, callback) {
        var arrLength = imgurls.length;
        if ( arrLength > 0) {
            var dynamicNum = 0;
            _PAGE.__load.b.load();
            for( var i=0;i<arrLength;i++){
                var img = new Image();
                img.src = imgurls[i];
                img.onload = function () {
                    dynamicNum++;
                    if ( dynamicNum == arrLength ){
                        _PAGE.__load.b.finish();
                        callback && ( callback instanceof Function ) && callback();
                    }
                };
                img.onerror = function () {
                    dynamicNum++;
                    if ( dynamicNum == arrLength ) {
                        _PAGE.__load.b.finish();
                        callback && ( callback instanceof Function ) && callback();
                    }
                };
            }
        } else {
            callback && ( callback instanceof Function )  && callback();
        }
    };
})();
///////////////////////
document.addEventListener('DOMContentLoaded',function(){
    $("[name='share_1']").bind("click",function(){
        showShareTip_2();
    });
});
