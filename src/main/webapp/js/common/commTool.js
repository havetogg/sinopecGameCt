var tool=(function(){
	var DEFAULT_WIDTH=650;
	var DEFAULT_HEIGHT=450;
	
	return {
		/**
		 * 弹出模态对话框
		 * @param url
		 * @returns
		 */
		dialog:function(url,width,height){
			window.showModalDialog(url,window,'resizable:yes;dialogWidth:'+(tool.isEmpty(width)?DEFAULT_WIDTH:width)+'px;dialogHeight:'+(tool.isEmpty(height)?DEFAULT_HEIGHT:height)+'px');
		},
		/**
		 * 获取选中的记录值
		 * @param radioName
		 * @returns
		 */
		getSelectedValue:function(radioName){
			if($.trim(radioName).length==0){
				radioName='listRadio';
			}
			
			var radio=$("input[type='radio'][name='"+radioName+"']:checked");
			if(radio&&radio.length>0){
				return radio[0].value;
			}else{
				return '';
			}
		},
		/**
		 * 获取选中的记录值同级的隐藏项目值
		 * @param Name
		 * @returns
		 */
		getSelectedhiddenValue:function(name){
			radioName='listRadio';
			
			var event=$("input[type='radio'][name='"+radioName+"']:checked")[0].parentElement.children;
			var inputname = event[name];
			if(inputname){
				return inputname.value;
			}else{
				return '';
			}
		},
		
		/**
		 * 判断字符串是否为空
		 * @param str
		 * @returns
		 */
		isEmpty:function(str){
			if(typeof(str)=='undefined'||$.trim(str).length==0){
				return true;
			}else{
				return false;
			}
		},
		/**
		 * 提交表单信息
		 * @param formId
		 * @returns
		 */
		formSubmit:function(formId){
			if(tool.isEmpty(formId)){
				$("#pageForm").submit();
			}else{
				$("#"+formId).submit();
			}
		},
		/**
		 * 重新加载当前页面
		 * @returns
		 */
		reload:function(){
			document.location.reload();
		},
		/**
		 * 关闭窗口
		 * @returns
		 */
		close:function(){
			window.close();
		},
		/**
		 * 跳转至指定的URL地址
		 */
		go:function(url){
			document.location=url;
		}
	};
})();