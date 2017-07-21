<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<c:set var="contentPath" value="<%=request.getContextPath()%>"></c:set>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<script type="text/javascript" src="${contentPath }/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${contentPath }/js/visitStat.js"></script>
	<script type="text/javascript" src="${contentPath}/js/My97DatePicker/WdatePicker.js" ></script>
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

	<div>
		<span class="h_class_name">讨论名称</span>
		<span><input id="NAME" name="NAME" value="${bean.NAME}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div class="divclass">
		<span class="h_class_name">启用状态</span>
		<span>
		   	<input type="radio" name="IN_USE_FLAG" value="1" checked="checked">是
			<input type="radio" name="IN_USE_FLAG" value="0">否
        </span>
	</div>
	<div>
		<span class="h_class_name">发布时间</span>
		<span><input id="PUBLISH_TIME" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00'})" name="PUBLISH_TIME" value="${bean.PUBLISH_TIME}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">内容</span>
<%-- 		<span><input id="CONTENT" name="CONTENT" value="${bean.CONTENT}" type="text" placeholder="" autocomplete="off"></span> --%>
	<textarea id="CONTENT" name="CONTENT" style="width: 500px; height: 180px;margin: 0 auto;"></textarea>
	</div>
	 <div>
            <span class="h_class_name">上传图片</span>
            <label for="IMAGE_UPLOAD"><img id="uploadImg" src="${contentPath }/img/h_ticket_add.png"  style="width: 100px;height: 100px;" alt=""></label>
     		<input type="hidden" id="IMAGE_URL"/>
     		<input style="display: none" onchange="imgupdate()" id="IMAGE_UPLOAD" type="file" name="IMAGE_URL">
     </div>
	<div>
		<span class="h_class_name">开始时间</span>
		<span><input id="ONLINE_START_TIME" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00'})" name="ONLINE_START_TIME" value="${bean.ONLINE_START_TIME}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">结束时间</span>
		<span><input id="ONLINE_END_TIME" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00'})" name="ONLINE_END_TIME" value="${bean.ONLINE_END_TIME}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div>
		<span class="h_class_name">表示顺</span>
		<span><input id="SHOW_ORDER" name="SHOW_ORDER" value="${bean.SHOW_ORDER}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div style="text-align: center;">
		<input id="save" class="h_category_save" type="button" value="保存">
	</div>

	<script type="text/javascript">
	function getSelectedValue(radioName){
		var radio=$("input[type='radio'][name='"+radioName+"']:checked");
		if(radio&&radio.length>0){
			return radio[0].value;
		}else{
			return '';
		}
	}
	
	
		$(function(){
			
			$("#save").click(function(){
				var NAME=$("#NAME").val();
				if($.trim(NAME).length==0){
					TipShow('请输入话题名',1200);
					return ;
				}
				
				var IN_USE_FLAG=getSelectedValue('IN_USE_FLAG');
/* 				if($.trim(IN_USE_FLAG).length==0){
					TipShow('请输入IN_USE_FLAG',1200);
					return ;
				} */
				
				var PUBLISH_TIME=$("#PUBLISH_TIME").val();
				if($.trim(PUBLISH_TIME).length==0){
					TipShow('请输入发布时间',1200);
					return ;
				}
				
				var CONTENT=$("#CONTENT").val();
				if($.trim(CONTENT).length==0){
					TipShow('请输入内容',1200);
					return ;
				}
				
				var IMAGE_URL=$("#IMAGE_URL").val();
				if($.trim(IMAGE_URL).length==0){
					TipShow('请上传图片',1200);
					return ;
				}
				
				var ONLINE_START_TIME=$("#ONLINE_START_TIME").val();
				if($.trim(ONLINE_START_TIME).length==0){
					TipShow('请输入开始时间',1200);
					return ;
				}
				
				var ONLINE_END_TIME=$("#ONLINE_END_TIME").val();
				if($.trim(ONLINE_END_TIME).length==0){
					TipShow('请输入结束时间',1200);
					return ;
				}

				
				var SHOW_ORDER=$("#SHOW_ORDER").val();
				if($.trim(SHOW_ORDER).length==0){
					TipShow('请输入表示顺',1200);
					return ;
				}
								
				if(confirm('确实要提交吗？')){
					$.ajax({
						type : 'post',
						url : 'save.htm',
						data:{'NAME':NAME,'IN_USE_FLAG':IN_USE_FLAG,'PUBLISH_TIME':PUBLISH_TIME,'CONTENT':CONTENT,'IMAGE_URL':IMAGE_URL,'ONLINE_START_TIME':ONLINE_START_TIME,'ONLINE_END_TIME':ONLINE_END_TIME,'SHOW_ORDER':SHOW_ORDER},
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