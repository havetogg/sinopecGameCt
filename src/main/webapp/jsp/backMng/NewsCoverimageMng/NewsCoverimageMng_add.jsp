<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<c:set var="contentPath" value="<%=request.getContextPath()%>"></c:set>
	
	<link rel="stylesheet" href="${contentPath }/css/app.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<script type="text/javascript" src="${contentPath }/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${contentPath }/js/visitStat.js"></script>
	<script type="text/javascript">
	function imgupdate() {  
	    loading("start");
	    $.ajaxFileUpload  
	    ({  
	        url:'imgupdate.htm',  
	        secureuri:false,  
	        fileElementId:'IMAGE_UPLOAD',  
	        dataType: 'json',  
	/* 		            data:{name: $('#name').val()},   */
	        success: function (data, status)  {  
	        	loading("stop");
	            if(0==data.success){
	            	// 失败的场合
	            	 $("#IMAGE_URL").val("");
	                //这里处理的是网络异常，返回参数解析异常，DOM操作异常  
	                alert(data.message);  
	            }else{
	            	// 成功的场合
	            	alert("成功!");
	                $("#IMAGE_URL").val(data.message);
	                
	                var IMAGE_URLAll= data.message;
	                var URLs=IMAGE_URLAll.split("@@");
	                $("#uploadImg").attr('src','${contentPath }/'+URLs[1]);
	            }
	        },  
	        error: function (data, status, e) {  
	        	loading("stop");
	             alert("上传发生异常");  
	        }  
	    })  

	    return false;  
	}  
	</script>
</head>
<body>
	<a class="h_categoryAdd_close" onclick="hideBg()">X</a>

<%-- 	<div>
		<span class="h_class_name">焦点图类型</span>
		<span>
			<select id="BANNER_TYPE" name="BANNER_TYPE" value="${bean.BANNER_TYPE}" type="text" placeholder="" autocomplete="off">
				<option value="1">微信</option>
				<option value="2">PC端</option>
			</select>
		</span>
	</div> --%>
<%-- 	<div>
		<span class="h_class_name">焦点图名称</span>
		<span><input id="NAME" name="NAME" value="${bean.NAME}" type="text" placeholder="" autocomplete="off"></span>
	</div> --%>
	 <div>
            <span class="h_class_name">上传图片</span>
            <label for="IMAGE_UPLOAD"><img id="uploadImg" src="${contentPath }/img/h_ticket_add.png"  style="width: 100px;height: 100px;" alt=""></label>
     		<input type="hidden" id="IMAGE_URL"/>
     		<input style="display: none" onchange="imgupdate()" id="IMAGE_UPLOAD" type="file" name="IMAGE_URL">
     </div>
<%-- 	<div>
		<span class="h_class_name">link地址</span>
		<span><input id="LINK_URL" name="LINK_URL" value="${bean.LINK_URL}" type="text" placeholder="" autocomplete="off"></span>
	</div> --%>
<!-- 	<div>
		<span class="h_class_name">启用状态</span>
		<span>
			<input type="radio" name="IN_USE_FLAG" value="1" checked="checked">是
			<input type="radio" name="IN_USE_FLAG" value="0">否
 		</span>
	
	</div> -->

	<div>
		<span class="h_class_name">显示顺序</span>
		<span><input id="SHOW_ORDER" name="SHOW_ORDER" value="${bean.SHOW_ORDER}" type="text" placeholder="" autocomplete="off"></span>
	</div>

	<div style="text-align: center;">
		<input id="save" class="h_category_save" type="button" value="保存">
	</div>

	<script type="text/javascript">

		$(function(){
			
			$("#save").click(function(){
				
				var IMAGE_URL=$("#IMAGE_URL").val();
				if($.trim(IMAGE_URL).length==0){
					TipShow('请上传图片',1200);
					return ;
				}
				
				var SHOW_ORDER=$("#SHOW_ORDER").val();
				if($.trim(SHOW_ORDER).length==0){
					TipShow('请输入显示顺序',1200);
					return ;
				}
				
				if(confirm('确实要提交吗？')){
					$.ajax({
						type : 'post',
						url : 'save.htm',
						data:{'NEWS_ID':'${param.NEWS_ID }','IMAGE_URL':IMAGE_URL,'SHOW_ORDER':SHOW_ORDER},
						dataType : 'json',
						success : function(data, textStatus) {
							if(data.success){
								hideBg();
								query();
							}else{
								alert(data.message);
							}
						},
						error : function(data, textStatus) {
							alert('保存失败');
						}

					});
				}
			});
		});
	</script>
</body>
</html>