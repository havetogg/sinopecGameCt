/**
 * backdrop UI component
 */
var TipShow = function (msg, duration) {
    var timeoutId = -1;
    var $backdropObj = $(".loading-backdrop");
    if (!$backdropObj[0]) {
        htmlStr = "<div class='loading-backdrop'>" +
            "<div class='loading-wrapper'>" +
            "<div class='loading-content'>" +
            msg +
            "</div></div></div>";
        $("body").append(htmlStr);
        $(".loading-backdrop").addClass('visible');
        if (typeof duration == "number" && duration > 0) {
            if (timeoutId > 0) {
                clearTimeout(timeoutId);
                delete timeoutId;
            }
            timeoutId = setTimeout(function () {
                TipHide()
            }, duration);
        }
    } else {
        $(".loading-content")[0].innerText = msg;
        $(".loading-backdrop").addClass('visible');
        if (typeof duration == "number" && duration > 0) {
            if (timeoutId > 0) {
                clearTimeout(timeoutId);
                delete timeoutId;
            }

            timeoutId = setTimeout(function () {
                TipHide()
            }, duration);
        }
    }
};
var TipHide = function () {
    $(".loading-backdrop").removeClass('visible');
};

var prize = 0;
function appendPrize(text) {
    $(".index_lunboText li").eq(prize).text(text);
    if(prize == ($(".index_lunboText li").length-1) ){
        prize = 0;
    }else {
        prize++;
    }
}