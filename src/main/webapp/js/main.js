/**
 * Created by Administrator on 2015/9/24.
 */
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
function getGameStatus() {
	$.ajax({
        url:getRootPath()+"/ManageC/getGameStatusM.htm",
        type:"post",
        dataType:"json",
        success: function (data) {
        	if(null != data){
        		if(data.code == 1){
        			if(data.resultObject.gamemode.c_status==4){
        				alert("您已经全部参加过了。");
        				return;
        			}
        			if(data.resultObject.gamemode.c_status<3){
        				location.href="playGame.jsp"
        			}else{
        				location.href="answer.jsp"
        			}
        		}else{
        			alerw(data.msg);
        		}
        	}
		}
    })
}

$(function(){
    $(".starGames").click(function(){
    	getGameStatus();
        //location.href=getRootPath()+"/playGame.jsp";
    });
    $(".lookRanking").click(function(){
        location.href=getRootPath()+"/rankingnew.jsp";
    });
});

