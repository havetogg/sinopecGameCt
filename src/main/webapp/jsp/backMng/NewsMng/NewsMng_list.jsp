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
	<script type="text/javascript" src="${contentPath }/jsp/backMng/NewsMng/NewsMng_list.js"></script>
	<script type="text/javascript" src="${contentPath }/js/visitStat.js"></script>
	<script src="${contentPath }/js/jquery.qrcode.min.js"></script>
<style type="text/css">
			.contents{
			display:none;
			width:100%;
			height: 100%;
			background-color: rgba(10, 10, 10, 0.6);
			position: fixed;
			top: 0; left: 0; z-index: 1100;
		}
		.bs-example-modal-lg{
			width: 44%;
			height: 550px;
			background: #FFFFFF;
			margin-left: 28%;
			margin-top: 4%;
			position: absolute;
		}
		.modal-header{
			width: 90%;
			height: 53px;
			margin-left: 5%;
			margin-top: 30px;
			border-bottom: solid 1px #D9D9D9;
		}
			h4{
				width: 95%;
				color: #999999;
				float: left;
				font-size: 30px;
			}
			.close{
				width: 20px;
				height: 20px;
			    font-size: 30px;
			    color: #999999;
			    float: left;
			}
			.saoma{
				width: 46%;
				margin: 0 auto;
			}
			.saoma img {
				width: 100%;
				margin:30px auto;
			}
			.tip1{
				width: 100%;
				height: 30px;
				text-align: center;
				color: #999999;
				font-size: 18px;
			}
			.tip2{
				width: 80%;
				height: 30px;
				text-align: center;
				color: #999999;
				margin-top: 20px;
				font-size: 18px;
			}
</style>
</head>
<body>
	<form action="list.htm" method="post" id="pageForm">
		<div class="spdb_content">
			<!--头部-->
			<div>
				<div>
					<a>
						<input name="QUERY_NAME" value="${queryParam.QUERY_NAME }" class="common_input_search" type="text" placeholder="输入关键字" autocomplete="off">
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
						<th>NEWSID</th>
						<th>标题</th>
						<th>备注</th>
						<th>启用状态</th>
						<th>发布时间</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th width="150px">操 作</th>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="obj" varStatus="status">
							<tr>
								<td>${page.startRow+status.index+1}</td>
								<td>${obj.ID}</td>
								<td>${obj.NAME}</td>
								<td>${obj.REMARK}</td>
								<td>${obj.IN_USE_FLAG}</td>
								<td>${obj.PUBLISH_TIME}</td>
								<td>${obj.ONLINE_START_TIME}</td>
								<td>${obj.ONLINE_END_TIME}</td>
								<td>
									<input name="list_viewBtn" class="spd_btn1_s" objId="${obj.ID }" type="button" value="查看">
									<input name="list_editBtn" class="spd_btn2_s" objId="${obj.ID }" type="button" value="修改">
									<input name="list_deleteBtn" class="spd_btn1_s" objId="${obj.ID }" type="button" value="删除">
									<input name="news_photos" class="spd_btn1_s" objId="${obj.ID }" type="button" value="图片">
									<input name="news_comment" class="spd_btn1_s" objId="${obj.ID }" type="button" value="评论管理">
									<input name="share" class="spd_btn1_s" objId="${obj.ID }" onclick="sharey(this)" type="button" value="预览">

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
   	<!--弹框-->
	<div class="contents" id="contentsid">
		<!-- 模态框 -->
		<div class="modal fade bs-example-modal-lg" id="list_model" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
     			<div class="modal-dialog modal-lg">
     				<div class="modal-content col-md-8 col-md-offset-2">
       				<div class="modal-header">
       					<h4 class="modal-title" id="list_modal_title">预览</h4>
         					<div type="button" class="close" data-dismiss="modal" onclick="closeshare()">×</div>
       				</div>
       				<div class="modal-body" id="list_modal_body">
       					<!-- 页面内容 -->
       					<div class="saoma"><img src="../../img/17.png"></div>
       					<div class="tip1">右键.保存图片下载二维码</div>
       					<div class="tip2">http://testd.juxinbox.com/guangdaMallSM/weixinMng/ManageC/userIn.htm</div>
       				</div>
     				</div>
     			</div>
   		</div>
	</div>
    
	<div class="h_bg"></div>
</body>
<script type="text/javascript">

function sharey(obj){
	var objId = $(obj).attr('objId');
	$('#list_modal_title').text('预览码');
	$('#list_modal_body').children().remove();
	$('#list_modal_body').append('<div id="qrcode" style="text-align:center;margin:20px;"></div>');
	$('#qrcode').qrcode(getRootPath()+'/weixinMng/WxNewsMng/newsDetail.htm?NEWSID='+objId);
	$('#list_modal_body').append('<div style="text-align:center;margin:20px;">右键-保存图片下载二维码</div>');
	$('#list_modal_body').append('<div style="text-align:center;margin:0px;word-wrap: break-word;">'+getRootPath()+'/weixinMng/groupBuy/toGroupBuy.htm?ID='+objId+'</div>');
	//$('#qrcode').append('<br>');
	//$('#qrcode').append('<button type="button" class="btn btn-default btn-xs hxg-toolBtn" onclick="copy(\''+getRootPath()+'/weixinMng/groupBuy/toGroupBuy.htm?ID=1'+'\')"><span class="glyphicon glyphicon-file"></span>复制到剪贴板</button>');
	$('#contentsid').css("display","block");
}

function closeshare(){
	$('#contentsid').css("display","none");
}


</script>
</html>