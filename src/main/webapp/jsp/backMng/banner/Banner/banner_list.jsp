<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
	<c:set var="contentPath" value="<%=request.getContextPath()%>"></c:set>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<link rel="stylesheet" href="${contentPath }/css/app.css" />
	<script type="text/javascript" src="${contentPath }/js/common/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="${contentPath }/js/app.js"></script>
	<script type="text/javascript" src="${contentPath }/js/m.tool.juxinbox.com.js"></script>
	<script type="text/javascript" src="${contentPath }/jsp/backMng/banner/Banner/banner_list.js"></script>
	<script type="text/javascript" src="${contentPath }/js/visitStat.js"></script>
</head>
<script type="text/javascript">
function showPic(imgurl){
    $(".showpic").attr("src","${contentPath }/"+imgurl);
    $(".alertTips1").css("display","block");
}

$(function(){
    $(".alertTips1").click(function(){
        $(this).css("display","none");
    })
})
</script>
<body>
	<!-- 图片的预览 -->
	<div class="alertTips1"style="background-color: rgba(10, 10, 10, 0.6); width: 100%; height: 100%; position: fixed; top: 0; left: 0; z-index: 1100; display: none;">
    <div class="tips1" style="overflow: hidden;background: #ffffff; width: 300px; height: 350px; border-radius: 10px; position: absolute; top: 50%; left: 50%;    margin: -228px -114px;border: 13px solid white">
        <img class="showpic" src="${contentPath }/jsp/weixinMng/img/t1.png" width="300" height="350" alt=""/>
    </div>
    </div>





	<form action="list.htm" method="post" id="pageForm">
		<div class="spdb_content">
			<!--头部-->
			<div>
				<div>
					<a>
						<input name="QUERY_NAME" value="${queryParam.NAME }" class="common_input_search" type="text" placeholder="输入关键字" autocomplete="off">
					</a>
					<img id="list_searchBtn" src="${contentPath }/img/search.png" onclick="query();" alt="" width="90px;">
				</div>
			</div>
			<div style="height: 5px;"></div>
			<!--操作-->
			<div class="common_active">
				<span id="list_addBtn" class="spd_btn1">新增</span>
			</div>
			<!--数据-->
			<div>
				<table class="common_table">
					<thead>
						<th width="40px">序号</th>
						<th>名称</th>
						<th>焦点图路径</th>
						<th>显示顺序</th>
						<th width="150px">操 作</th>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="obj" varStatus="status">
							<tr>
								<td>${page.startRow+status.index+1}</td>
								<td>${obj.NAME}</td>
								<td onclick="showPic('${obj.IMAGE_URL}')">${obj.IMAGE_URL}</td>
								<td>${obj.SHOW_ORDER}</td>
								<td>
									<input name="list_viewBtn" class="spd_btn1_s" objId="${obj.ID }" type="button" value="查看">
									<input name="list_editBtn" class="spd_btn2_s" objId="${obj.ID }" type="button" value="修改">
									<input name="list_deleteBtn" class="spd_btn1_s" objId="${obj.ID }" type="button" value="删除">
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!--分页-->
			<%@include file="/divPage.jsp"%>
		</div>
	</form>
	<%@include file="/footer.jsp"%>
	
	
	<!--弹出层-->
	<div class="h_categoryAdd">
    </div>
    
	<div class="h_bg"></div>
</body>
</html>