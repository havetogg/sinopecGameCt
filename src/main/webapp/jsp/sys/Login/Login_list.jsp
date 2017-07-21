<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<html>
	<head>
		<c:set var="contentPath" value="<%=request.getContextPath()%>"></c:set>
		<meta charset="utf-8" />
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
		<title></title>
		<link rel="stylesheet" href="${contentPath}/css/style.css" />
		<script type="text/javascript" src="${contentPath}/js/common/jquery-1.9.0.min.js" ></script>
		
		<script type="text/javascript">
			function getRootPath() {
			    // 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
			    var curWwwPath = window.document.location.href;
			    // 获取主机地址之后的目录，如： uimcardprj/meun.jsp
			    var pathName = window.document.location.pathname;
			    var pos = curWwwPath.indexOf(pathName);
			    // 获取主机地址，如： http://localhost:8083
			    var localhostPaht = curWwwPath.substring(0, pos);
			    // 获取带"/"的项目名，如：/uimcardprj
			    var projectName = pathName
			        .substring(0, pathName.substr(1).indexOf('/') + 1);
			    return (localhostPaht + projectName);
			}
			$(function(){
				$(".login1").click(function(){
					$.ajax({
				        url:getRootPath()+"/LoginC/loginM.htm",
						data:{
							'loginName':$.trim($('.yhm').val()),
							'loginPass':$.trim($('.mm').val())
						},
						dataType:'json',
						success:function(data,textStatus){
							if(data.code == 1){
								window.location =getRootPath()+ "/main.html";
							}else{
								alert(data.msg)
							}
						}
					});
				});
			});
		</script>
	</head>
	<body>
		<div class="indexmain">
			<div class="indexhead">
				<img class="fl logo" src="${contentPath}/img/pflogo.png">
			</div>
			<div class="login">
				<input class="yhm" type="text" value="admin"/>
				<input class="mm" type="password" value="123123"/>
				<div class="login1"></div>
			</div>
			<div class="indexbottom">
				<p class="banben">聚客有礼后台管理系统  |  技术支持 <span class="jmt">聚目堂</span>  |  版本号：V1.0
<br>Copyright © 2016 聚客有礼后台管理系统 苏ICP备115741300号</p>
			</div>
		</div>
	</body>
</html>
