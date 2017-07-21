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
	<script type="text/javascript" src="${contentPath }/jsp/backMng/discus/DiscusMng/DiscusMng_list.js"></script>
	<script type="text/javascript" src="${contentPath }/js/visitStat.js"></script>
</head>
<body>
	<form action="list.htm" method="post" id="pageForm">
		<div class="spdb_content">
			<!--头部-->
			<div>
				<div>
					<a>
						<input name="NAME" value="${queryParam.NAME }" class="common_input_search" type="text" placeholder="输入关键字" autocomplete="off">
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
						<th width="20px">序号</th>
						<th>话题</th>
						<th>启用状态</th>
						<th>发布时间</th>
						<th>回复数</th>
						<th>点赞数</th>
						<th>关注数</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>表示顺</th>
						<th width="150px">操 作</th>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="obj" varStatus="status">
							<tr>
								<td>${page.startRow+status.index+1}</td>
								<td>${obj.NAME}</td>
								<td>${obj.IN_USE_FLAG}</td>
								<td>${obj.PUBLISH_TIME}</td>
								<td>${obj.REPLY_NUM}</td>
								<td>${obj.PRAISE_NUM}</td>
								<td>${obj.CONCERN_NUM}</td>
								<td>${obj.ONLINE_START_TIME}</td>
								<td>${obj.ONLINE_END_TIME}</td>
								<td>${obj.SHOW_ORDER}</td>
								<td>
									<input name="list_viewBtn" class="spd_btn1_s" objId="${obj.ID }" type="button" value="查看">
									<input name="list_editBtn" class="spd_btn2_s" objId="${obj.ID }" type="button" value="修改">
									<input name="list_deleteBtn" class="spd_btn1_s" objId="${obj.ID }" type="button" value="删除">
									<input name="discus_comment" class="spd_btn1_s" objId="${obj.ID }" type="button" value="评论管理">
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