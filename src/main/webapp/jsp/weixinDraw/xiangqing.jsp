
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<%-- <script type="text/javascript" src="${contentPath }/js/jquery-1.9.0.min.js"> --%>

</script>
<html lang="en">


<script type="text/javascript">
	

</script>
<head>
<c:set value="<%=request.getContextPath() %>" var="contentPath"></c:set>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=0.5,maximum-scale=0.5,minimum-scale=0.5, width=640, target-densitydpi=device-dpi">

    <meta http-eqiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <!--如果IE用户安装了Chrome Frame插件，则使用这个插件渲染页面，否则用IE最新的、最好的引擎来渲染页面-->
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>${NEWSINFO.NAME }</title>
    <link type="text/css" href="${contentPath }/jsp/weixinDraw/css/common/common.css" rel="stylesheet">
    <!--<script type="text/javascript" src="js/common/jquery-1.7.2.min.js"></script>-->
    <!--<script type="text/javascript" src="js/common/m.tool.juxinbox.com.js"></script>-->

    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/jQuery-1.11.3.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/jWeChat-Adaptive.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/m.tool.juxinbox.com.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/jWeChat-1.0.0.js"></script>
    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/123.js"></script>

    <script type="text/javascript" src="${contentPath }/jsp/weixinDraw/js/common/common.js"></script>
    <style>
    	*{
    		margin: 0;
    		padding: 0;
    	}
    	.contents{
    		width: 640px;
    		position: absolute;
    		background: white;
    	}
    	.head{
    		width: 580px;
    		height: 170px;
    		margin: 40px auto 0px;
    	}
    	.title{
    		font-size: 40px;
    		letter-spacing: 4px;
    		line-height: 56px;
    		color: #000000;
    		font-family: "微软雅黑";
    	}
    	.time{
    		width: 176px;
    		height: 30px;
    		color: #888888;
    		font-size: 28px;
    		margin-top: 28px;
    		float: left;
    		font-family: "微软雅黑";
    	}
    	.dizhi{
    		height: 30px;
    		color: #648cb2;
    		float: left;
    		font-size: 28px;
    		margin-top: 28px;
    		font-family: "微软雅黑";
    	}
    	.pic{
    		width: 580px;
    		height: 366px;
    		margin:31px auto;
    	}
    	.pic img{
    		width: 580px;
    		height: 366px;
    	}
    	.con{
    		width: 580px;
    		margin: 10px auto 88px;
    	}
    	p{
    		width: 580px;
    		font-size: 20px;
    		color: #666666;
    		line-height: 39px;
    		text-indent: 2em;
    	}
    	.foot{
    		position: fixed;
    		bottom: 0;
    		width: 100%;
    		height: 94px;
			background:#ffffff;
    		border-top: solid #dac9c9 1px;
    	}
    	.chakan{
    		width: 200px;
    		height: 30px;
    		color: #1cb8ee;
    		line-height: 30px;
    		font-size: 24px;
    		position: absolute;
    		margin-top: 31px;
    		margin-left: 30px;
    	}
    	.chakan span{
    		font-size: 24px;
    		color: #999999;
    	}
    	.txt{
    		width: 53%;
    		height: 60px;
    		font-size: 24px;
    		color: #999999;
			padding-left:18px;
    		position: absolute;
    		margin-top: 14px;
    		margin-left: 193px;
    		border: solid #dac9c9 1px;
    		line-height:60px;
    	}
    	.button{
    		width: 90px;
    		height: 60px;
    		background:#1cb8ee ;
    		color: white;
    		font-size: 24px;
    		text-align: center;
    		margin-top: 12px;
    		margin-left:83%;
    		line-height: 60px;
    		border-radius: 10px;
    	}
    	.sanv{
    		width: 580px;
    		height: 28px;
    		margin: 0 auto 38px;
    	}
    	.read{
    		color: #888888;
    		font-size: 28px;
    		letter-spacing: 1px;
    		float: left;
			font-family: "微软雅黑";
    	}
    	.read span{
    		color: #888888;
    		font-size: 28px;
			font-family: "微软雅黑";
    	}
    	.zan-pic{
    		width: 22px;
    		height: 22px;
    		float: left;
    		margin-left: 32px;
    		background: url(${contentPath }/jsp/weixinDraw/img/zan.png);
    	}
    	.nzan-pic{
    		width: 24px;
    		height: 23px;
    		float: left;
    		margin-left: 32px;
    		background: url(${contentPath }/jsp/weixinDraw/img/zan_.png);
    	}
    	.zan-txt{
    		color: #888888;
    		font-size: 28px;
    		float: left;
    		margin-left: 5px;
			font-family: "微软雅黑";
    	}
    	.sanv_{
    		width: 580px;
    		height: 28px;
    		margin: 0 auto 38px;
    		display: none;
    	}
    	.zan-txt_{
    		color: #888888;
    		font-size: 28px;
    		float: left;
    		margin-left: 32px;
			font-family: "微软雅黑";
    	}
    	.zan-pic_{
    		width: 22px;
    		height: 22px;
    		float: left;
    		margin-left: 5px;
    		background:url(${contentPath }/jsp/weixinDraw/img/zan.png);
    	}
    </style>
    <script type="text/javascript">
//     	$(function(){
//     		$(".chakan").click(function(){
//     			location.href=getRootPath()+"/weixinMng/DrawMng/gotoDiscutionPage.htm?DISCUSID="+id;;
//     		});
//     		$(".button").click(function(){
    			
    			
//     		});
    		
//     	});
    	function pinglList(id){
  		  window.location = getRootPath()+"/weixinMng/WxNewsMng/pinglList.htm?NEWSID="+id;
  	}
  	
  	function pinglSubmit(id){
  		var redirectUrl=getRootPath()+"/weixinMng/WxNewsMng/newsDetail.htm?NEWSID="+id;
  		
  		var CONTENT=$("#textareaid").val();
  		if(!CONTENT){
  			alert("请输入评论内容");
  			return;
  		}
  		confirw("确认提交评论?", "", "","", 
  		   function(){
  			//OK
 		     	 $.ajax({
 		                url: "commentAdd.htm",  
 		                async: true,         //false为同步,（默认是true）;
 		                data: {"NEWS_ID": '${param.NEWSID }' ,"CONTENT": CONTENT},
 		                dataType: "json",
 		                type: "post",
 		                beforeSend: function () {
 		                    loading("start");
 		                },
 		                complete: function (XMLHttpRequest) {
 		                    loading("stop");
 		                },
 		                success: function (data) {
 		                	if(-1==data.timeout){
 		                		// session已经过期了
 		                		alerw("ajax-timeout!");
 		                		return;
 		                	}
 			                if(data.success==true){
 			                	alerw("保存成功!");
 			                	$("#textareaid").val("");
 			                	var nub=$("#numbid").attr("numb");
 			                	$("#numbid").attr("numb",parseInt(nub)+1);
 			                	$("#numbid").html("("+(parseInt(nub)+1)+")");
 			                	
 			                }else{
 			                	alerw("系统异常!");
 			                }
 		                },
 		                //调用出错执行的函数
 		                error: function(XMLHttpRequest, textStatus, errorThrown){
 	 	                    //请求出错处理
 	                        alerw("系统异常!"+textStatus);
 		                }      
 	        	 })
  			   
  		}, function(){
  			//NG
  			
  		});
  		
  	}
	
	function ziti(dom){
          var isnNot = $(dom).hasClass("zan-pic");
          var num=$(".zan-txt").text()*1;
            if(isnNot){
                $(dom).removeClass("zan-pic").addClass("nzan-pic");
                    dianzanAdd(1);
              		$(".zan-txt").text(num+1);
            }else{
                $(dom).removeClass("nzan-pic").addClass("zan-pic");
                    dianzanAdd(-1);
              		$(".zan-txt").text(num-1);
          		
            }
			
        }
	
	function dianzanAdd(type){
     	 $.ajax({
              url: "dianzanAdd.htm",  
              async: true,         //false为同步,（默认是true）;
              data: {'TYPE':type,"NEWS_ID": '${param.NEWSID }'},
              dataType: "json",
              type: "post",
              beforeSend: function () {
                 // loading("start");
              },
              complete: function (XMLHttpRequest) {
                 // loading("stop");
              },
              success: function (data) {
              	if(-1==data.timeout){
              		alerw("ajax-timeout!");
              		return;
              	}
              },
              //调用出错执行的函数
              error: function(XMLHttpRequest, textStatus, errorThrown){
                   //请求出错处理
                  alerw("系统异常!"+textStatus);
              }      
  	 })
	}
	
	function authorurl(url){
		window.location=url;
	}
    </script>
	</head>
	<body>
		<div class="zoomer">
			<div class="contents">
				<div class="head">
					<div class="title">${NEWSINFO.NAME }</div>
					<div class="time">${fn:substring(NEWSINFO.PUBLISH_TIME, 0, 10)}</div>
					<div class="dizhi" onclick="authorurl('${NEWSINFO.AUTHOR_LINK }')">${NEWSINFO.AUTHOR_NAME }</div>
				</div>
				<div class="con">
					<p>
					${NEWSINFO.CONTENT }
					</p>
				</div>
				<div class="sanv">
					<div class="read">阅读<span>${NEWSINFO.CLICK_BASE+NEWSINFO.CLICK_RATE }</span></div>
					<!--<div class="zan-pic"></div>-->
					<!-- <input type="checkbox" id="ziti" checked="checked" hidden="hidden"/> -->
                	<c:if test="${NEWSADDFLAG==0 }">
                	   <label for="ziti"><div onclick="ziti(this)" class="zan-pic"></div></label>
                	</c:if>
                	<c:if test="${NEWSADDFLAG>0 }">
                	   <label for="ziti"><div onclick="ziti(this)" class="nzan-pic"></div></label>
                	</c:if>
                	
					<div class="zan-txt">${NEWSINFO.DIANZ_BASE+NEWSINFO.DIANZ_NUM }</div>
				</div>
<!-- 				<div class="sanv_">
					<div class="read">阅读<span>14</span></div>
					<div class="zan-txt_">赞</div>
					<div class="zan-pic_"></div>
				</div> -->
			</div>
		</div>
<!-- 		<div class="foot"> -->
<%-- 			<div class="chakan" onclick="pinglList(${NEWSINFO.ID})">查看评论<span id="numbid" numb=${COMMENTLIST.size() }">(${COMMENTLIST.size() })</span></div> --%>
<!-- 			<textarea id="textareaid" class="txt"placeholder="发表内容"></textarea> -->
<!-- 			<!--<div class="test_box" contenteditable="true"><br /></div>--> -->
<!-- 			<div class="button" onclick="pinglSubmit()">提&nbsp;&nbsp;交</div> -->
<!-- 		</div> -->
	</body>
<script type="text/javascript">
window.onload=function(){
		//var screen_width=window.screen.width;
	
		$(".con img").each(function(){
			var w = $(this).width();
	        var pwith=$(this).parent().width();
			if(w>pwith){
				$(this).css('width','98%');
	            $(this).parent().css("text-indent","0px");
			}
		});
	}
</script>
</html>
