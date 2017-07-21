<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		
		<link rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="js/common/jquery-1.9.0.min.js" ></script>
		<script type="text/javascript" src="js/main.js" ></script>
		<style>

			a{
				text-decoration:none
			}

			*{
				margin: 0;
				padding: 0;
			}
			.left{
				width: 200px;
				min-height: 700px;
				background: #504b51;
			}
			.title_{

				color: #b5abb5;
				height: 46px;
				line-height: 49px;
				text-indent: 68px;

			}
			.title{
				height: 30px;
				line-height: 46px;
				text-indent: 52px;
			}
			.title_pic2{
				position: absolute;
				margin: 9px -33px;
			}
			.title_:hover{

				color: #fff;
				font-weight: 800;
				height: 46px;
				line-height: 49px;
				text-indent: 68px;

			}
			.mains{
				width: 190px;

				background: #fff;
				margin: 0px 10px;
				z-index: 11;
			}
			.main{
				width: 100px;
				height: 40px;
				margin: 0px 56px;
				line-height: 60px;
			}

			.main:hover{
				width: 100px;
				height: 40px;
				margin: 0px 56px;
				line-height: 60px;
				color:#be77d6 ;
				font-weight: 800;
			}
			.mian_c{
				width: 100px;
				height: 40px;
				margin: 0px 56px;
				line-height: 60px;
				color:#be77d6 ;
				font-weight: 800;
			}
			.title_pic{
				margin: 10px -43px;
				position: absolute;
			}
			.chose{
				position: absolute;
				margin: -60px 94px;
				display: none;
			}
		</style>
		<script>
			function kai(dom){
				$(".mains").css("display","none");
				$(dom).parent().children().eq(1).slideToggle()
			}
	
			function which(dom){
				$(".chose").css("display","none");
				$(dom).children().css("display","block");
				$(".mian_c").removeClass("mian_c");
				$(dom).addClass("mian_c");
			}
		</script>
	</head>
	<body>
		<div class="conetent">
			<div class="left">
				  <c:forEach items="${currUserMenu}" var="obj" varStatus="status">
				  	<div>
			  			<div class="title_" onclick="kai(this)"><img class="title_pic" src="img/4.png" alt=""/>${obj.NAME }</div>
			  			<div class="mains" style="display: none;">
			  				<c:forEach items="${obj.subList }" var="sub">
			  					<div class="main" onclick="which(this)">
			  						<a href="${sub.URL }" target="rightFrame">${sub.NAME }</a> 
			  						<div class="chose"><img  src="img/5.png" alt=""/></div>
			  					</div>
			  				</c:forEach>
						</div>
					</div>
				  </c:forEach>
			</div>
		</div>
	</body>
</html>
