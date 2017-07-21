
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:set var="contentPath" value="<%=request.getContextPath() %>"></c:set>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=0.5,maximum-scale=0.5,minimum-scale=0.5, width=640, target-densitydpi=device-dpi">

    <meta http-eqiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <!--如果IE用户安装了Chrome Frame插件，则使用这个插件渲染页面，否则用IE最新的、最好的引擎来渲染页面-->
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>${WXNEWSMNGMODE.NAME }</title>
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
    		background: white;
    		position: absolute;
    	}
    	.list{
    		width: 580px;
    		margin: 20px auto;
    		
    	}
    	.touxiang{
            height:110px;
            width: 110px;
            border: 3px solid #7cc2d4;
            border-radius: 95px;
            overflow: hidden;
            z-index: 9999;
            float: left;
            margin-bottom: 10px;
    	}
    	.name{
    		color: #00e0ec;
    		font-size: 20px;
    		float: left;
    		padding-right: 9px;
    		margin-left: 20px;
    		width: 100px;
    		overflow: hidden;
    		text-overflow: ellipsis;
    		white-space: nowrap;
    	}
    	.time{
    		width: 100px;
    		float: left;
    		color: #CCCCCC;
    		font-size: 18px;
    	}
    	.zan{
    		width: 24px;
    		height: 22px;
    		float: left;
    		margin-left: 157px;
    	}
    	.zan-txt{
    		width: 50px;
    		text-align: right;
    		float: left;
    		font-size: 18px;
    		color:#00e0ec ;
    	}
    	.con{
    		width:440px;
    		font-size: 20px;
    		color: #444444;
    		float: left;
    		margin-left: 20px;
    		margin-top: 10px;
    		line-height: 31px;
    	}
    	.fli{
    		width: 580px;
    	}
    </style>
    <script type="text/javascript">
    
    function newsCommPraise(id,obj,NEWS_ID){
    	
    	
	    // 登录判断
		var redirectUrl=getRootPath()+"/weixinMng/WxNewsMng/pinglList.htm?NEWSID="+NEWS_ID;
     	 $.ajax({
                url: "newsCommPraise.htm",  
                async: true,         //false为同步,（默认是true）;
                data: {"NEWS_COMMENT_ID": id},
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
	                	$(obj).parent().find(".zan").attr("onclick","");
	                	$(obj).parent().find(".zan").find("img").attr("src","${contentPath }/jsp/weixinMng/mallMng/img/18.png");
	                	var numb=$(obj).parent().find(".zan-txt").attr("numb");
	                	$(obj).parent().find(".zan-txt").html("("+(parseInt(numb)+1)+")")
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
	
}

    </script>
	</head>
	<body>
		<div class="zoomer">
			<div class="contents">
			
			
					<c:forEach items="${COMMENTLIST}" var="obj">
					<div class="list">
					<div class="touxiang"><img src="${obj.MALLUSERMODE.HEAD_IMG }" width="110" height="110" alt=""/></div>
					<div class="name">${obj.MALLUSERMODE.NICK_NAME }</div>
					<div class="time">${obj.CREATE_TIME_HIS }</div>
					<c:choose> 
					  <c:when test="${obj.USERNUMB>0 }">   
					      <div class="zan" numb="100"><img src="${contentPath }/jsp/weixinDraw/img/18.png"></div>
					  </c:when> 
					  <c:otherwise>   
					      <div class="zan" onclick="newsCommPraise(${obj.ID },this,${obj.NEWS_ID })" numb="100"><img src="${contentPath }/jsp/weixinDraw/img/18-ON.png"></div>
					  </c:otherwise> 
					</c:choose> 
					<div class="zan-txt" numb="${obj.NEWSCOMMENT_NUB }">(${obj.NEWSCOMMENT_NUB })</div>
					<div class="con">${obj.CONTENT }</div>
					<div class="fli"><img src="${contentPath }/jsp/weixinDraw/img/line.png"></div>
					
				</div>
				
				</c:forEach>
				
				
		
			
<!-- 				<div class="list"> -->
<%-- 					<div class="touxiang"><img src="${contentPath }/jsp/weixinDraw/img/121.png" width="110" height="110" alt=""/></div> --%>
<!-- 					<div class="name">豪斯K先生</div> -->
<!-- 					<div class="time">2小时前</div> -->
<%-- 					<div class="zan"><img src="${contentPath }/jsp/weixinDraw/img/18.png"></div> --%>
<!-- 					<div class="zan-txt">(541)</div> -->
<!-- 					<div class="con">首先来说，我们平台的美国本土内容，比如NBA在中国市场有很多的粉丝，美国歌手的演唱会在中国也会很人气很旺，这些内容拿到中国来是有市场的；其次，我们也觉得和中国本地的大企业合作，拿到本地热门内容的直播版权，然后把内容带给中国用户也是个很有前景的生意。</div> -->
<%-- 					<div class="fli"><img src="${contentPath }/jsp/weixinDraw/img/line.png"></div> --%>
<!-- 				</div> -->
<!-- 				<div class="list"> -->
<%-- 					<div class="touxiang"><img src="${contentPath }/jsp/weixinDraw/img/121.png" width="110" height="110" alt=""/></div> --%>
<!-- 					<div class="name">花花世界</div> -->
<!-- 					<div class="time">2小时前</div> -->
<%-- 					<div class="zan"><img src="${contentPath }/jsp/weixinDraw/img/18.png"></div> --%>
<!-- 					<div class="zan-txt">(41)</div> -->
<!-- 					<div class="con">一花一世界，一叶一菩提。</div> -->
<%-- 					<div class="fli"><img src="${contentPath }/jsp/weixinDraw/img/line.png"></div> --%>
<!-- 				</div> -->
<!-- 				<div class="list"> -->
<%-- 					<div class="touxiang"><img src="${contentPath }/jsp/weixinDraw/img/121.png" width="110" height="110" alt=""/></div> --%>
<!-- 					<div class="name">三八女人节</div> -->
<!-- 					<div class="time">2小时前</div> -->
<%-- 					<div class="zan"><img src="${contentPath }/jsp/weixinDraw/img/18.png"></div> --%>
<!-- 					<div class="zan-txt">(41)</div> -->
<!-- 					<div class="con">不放假不放假不放假不放假不放假不放假不放假不放假不放假不放假不放假不放假</div> -->
<%-- 					<div class="fli"><img src="${contentPath }/jsp/weixinDraw/img/line.png"></div> --%>
<!-- 				</div> -->
<!-- 				<div class="list"> -->
<%-- 					<div class="touxiang"><img src="${contentPath }/jsp/weixinDraw/img/121.png" width="110" height="110" alt=""/></div> --%>
<!-- 					<div class="name">豪斯K先生</div> -->
<!-- 					<div class="time">2小时前</div> -->
<%-- 					<div class="zan"><img src="${contentPath }/jsp/weixinDraw/img/18.png"></div> --%>
<!-- 					<div class="zan-txt">(541)</div> -->
<!-- 					<div class="con">首先来说，我们平台的美国本土内容，比如NBA在中国市场有很多的粉丝，美国歌手的演唱会在中国也会很人气很旺，这些内容拿到中国来是有市场的；其次，我们也觉得和中国本地的大企业合作，拿到本地热门内容的直播版权，然后把内容带给中国用户也是个很有前景的生意。</div> -->
<%-- 					<div class="fli"><img src="${contentPath }/jsp/weixinDraw/img/line.png"></div> --%>
<!-- 				</div> -->
<!-- 				<div class="list"> -->
<%-- 					<div class="touxiang"><img src="${contentPath }/jsp/weixinDraw/img/121.png" width="110" height="110" alt=""/></div> --%>
<!-- 					<div class="name">豪斯K先生</div> -->
<!-- 					<div class="time">2小时前</div> -->
<%-- 					<div class="zan"><img src="${contentPath }/jsp/weixinDraw/img/18.png"></div> --%>
<!-- 					<div class="zan-txt">(541)</div> -->
<!-- 					<div class="con">首先来说，我们平台的美国本土内容，比如NBA在中国市场有很多的粉丝，美国歌手的演唱会在中国也会很人气很旺，这些内容拿到中国来是有市场的；其次，我们也觉得和中国本地的大企业合作，拿到本地热门内容的直播版权，然后把内容带给中国用户也是个很有前景的生意。</div> -->
<%-- 					<div class="fli"><img src="${contentPath }/jsp/weixinDraw/img/line.png"></div> --%>
<!-- 				</div> -->
				
			</div>
		</div>
	</body>
</html>
