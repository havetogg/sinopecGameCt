<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<c:set var="contentPath" value="<%=request.getContextPath()%>"></c:set>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
<%-- 	<script type="text/javascript" src="${contentPath }/js/visitStat.js"></script> --%>
	<script type="text/javascript" src="${contentPath }/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${contentPath }/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="${contentPath}/js/My97DatePicker/WdatePicker.js" ></script>
    <script type="text/javascript" src="${contentPath }/js/common/jquery-1.9.0.min.js"></script>
<%--     <script type="text/javascript" src="${contentPath }/js/m.tool.juxinbox.com.js"></script> --%>

<script type="text/javascript">

$(function(){
	ue.ready(function() {
		ue.execCommand('insertHtml', '${bean.DETAIL_RICH_TEXT}');
	});
  });
	function getContext(){
		var arr = [];
        arr.push(UE.getEditor('CONTENT').getContent());
        return arr.join("\n");
	}
	
</script>
</head>
<body>
	<a class="h_categoryAdd_close" onclick="hideBg()">X</a>

	<div class="divclass">
		<span class="h_class_name">标题</span>
		<span><input id="NAME" name="NAME" value="${bean.NAME}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div class="divclass">
		<span class="h_class_name">详细内容</span>
		
		<textarea id="CONTENT" name="CONTENT" style="width: 550px; height: 280px;margin: 0 auto;"></textarea>
		<script type="text/javascript">
	    var ue = UE.getEditor("CONTENT");
	    </script>
	</div>
	
	<div class="divclass">
		<span class="h_class_name">第三方url</span>
		<span><input id="NEWSURL" name="NEWSURL" value="${bean.NEWSURL}" type="text" placeholder="" autocomplete="off"></span>
	</div>
    <div class="divclass">
		<span class="h_class_name">作者名</span>
		<span><input id="AUTHOR_NAME" name="AUTHOR_NAME" value="${bean.AUTHOR_NAME}" type="text" placeholder="" autocomplete="off"></span>
	</div>
    <div class="divclass">
		<span class="h_class_name">作者linK</span>
		<span><input id="AUTHOR_LINK" name="AUTHOR_LINK" value="${bean.AUTHOR_LINK}" type="text" placeholder="" autocomplete="off"></span>
	</div>
    <div class="divclass">
		<span class="h_class_name">阅读基数</span>
		<span><input id="CLICK_BASE" name="CLICK_BASE" value="${bean.CLICK_BASE}" type="text" placeholder="" autocomplete="off"></span>
	</div>
    <div class="divclass">
		<span class="h_class_name">点赞基数</span>
		<span><input id="DIANZ_BASE" name="DIANZ_BASE" value="${bean.DIANZ_BASE}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div class="divclass">
		<span class="h_class_name">备注</span>
		<span><input id="REMARK" name="REMARK" value="${bean.REMARK}" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div class="divclass">
		<span class="h_class_name">启用状态</span>
		<span>
		   	<input type="radio" name="IN_USE_FLAG" value="1" checked="checked">是
			<input type="radio" name="IN_USE_FLAG" value="0">否
        </span>
	</div>
	<div class="divclass">
		<span class="h_class_name">发布时间</span>
		<span><input id="PUBLISH_TIME" name="PUBLISH_TIME" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00'})" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div class="divclass">
		<span class="h_class_name">上线开始时间</span>
		<span><input id="ONLINE_START_TIME" name="ONLINE_START_TIME" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00'})"  type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div class="divclass">
		<span class="h_class_name">上线结束时间</span>
		<span><input id="ONLINE_END_TIME" name="ONLINE_END_TIME" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00'})" type="text" placeholder="" autocomplete="off"></span>
	</div>
	<div class="divclass">
		<span class="h_class_name">显示顺序</span>
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
					TipShow('请输入标题',1200);
					return ;
				}
				
				var REMARK=$("#REMARK").val();
				if($.trim(REMARK).length==0){
					TipShow('请输入备注',1200);
					return ;
				}
				
				var IN_USE_FLAG=getSelectedValue('IN_USE_FLAG');
/* 				if($.trim(IN_USE_FLAG).length==0){
					TipShow('请输入IN_USE_FLAG',1200);
					return ;
				} */
				
				var NEWSURL=$("#NEWSURL").val();
				
				
				var PUBLISH_TIME=$("#PUBLISH_TIME").val();
				if($.trim(PUBLISH_TIME).length==0){
					TipShow('请输入PUBLISH_TIME',1200);
					return ;
				}
				
				var RICH_CONTENT_ID=getContext();
				if($.trim(RICH_CONTENT_ID).length==0){
					TipShow('请输入RICH_CONTENT_ID',1200);
					return ;
				}
				
				var ONLINE_START_TIME=$("#ONLINE_START_TIME").val();
				if($.trim(ONLINE_START_TIME).length==0){
					TipShow('请输入ONLINE_START_TIME',1200);
					return ;
				}
				
				var ONLINE_END_TIME=$("#ONLINE_END_TIME").val();
				if($.trim(ONLINE_END_TIME).length==0){
					TipShow('请输入ONLINE_END_TIME',1200);
					return ;
				}
				
				var SHOW_ORDER=$("#SHOW_ORDER").val();
				if($.trim(SHOW_ORDER).length==0){
					TipShow('请输入SHOW_ORDER',1200);
					return ;
				}
				
				// 作者名
				var AUTHOR_NAME=$("#AUTHOR_NAME").val();
				if($.trim(AUTHOR_NAME).length==0){
					TipShow('请输入作者名',1200);
					return ;
				}
				// 作者链接
				var AUTHOR_LINK=$("#AUTHOR_LINK").val();
				if($.trim(AUTHOR_LINK).length==0){
					AUTHOR_LINK="";
				}
				
				// 阅读基数
				var CLICK_BASE=$("#CLICK_BASE").val();
				if($.trim(CLICK_BASE).length==0){
					TipShow('请输入阅读基数',1200);
					return ;
				}
				if(!jxTool.isInt(CLICK_BASE)){
					TipShow('阅读基数应为整数',1200);
					return ;
				}
				
				// 点赞基数
				var DIANZ_BASE=$("#DIANZ_BASE").val();
				if($.trim(DIANZ_BASE).length==0){
					TipShow('请输入点赞基数',1200);
					return ;
				}
				if(!jxTool.isInt(DIANZ_BASE)){
					TipShow('点赞基数应为整数',1200);
					return ;
				}
								
				if(confirm('确实要提交吗？')){
					$.ajax({
						type : 'post',
						url : 'save.htm',
						data:{'NAME':NAME,'REMARK':REMARK,'IN_USE_FLAG':IN_USE_FLAG,'PUBLISH_TIME':PUBLISH_TIME,
							'RICH_CONTENT_ID':RICH_CONTENT_ID,'ONLINE_START_TIME':ONLINE_START_TIME,
							'ONLINE_END_TIME':ONLINE_END_TIME,
							'SHOW_ORDER':SHOW_ORDER,
							'NEWSURL':NEWSURL,
							'AUTHOR_NAME':AUTHOR_NAME,
							'AUTHOR_LINK':AUTHOR_LINK,
							'CLICK_BASE':CLICK_BASE,
							'DIANZ_BASE':DIANZ_BASE
							},
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