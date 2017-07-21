jQuery.fn.extend({
	/**
	 * 寻找所有指定选择器第一级元素
	 * @param selector 选择器
	 * @param allElements 找到的所有元素
	 * @returns
	 */
	findFirst : function(selector, allElements){
		if($(this).children().length == 0){return $();}
		allElements = allElements == undefined ? [] : allElements;
		
		var searchElement = $(this).children(selector);
		if(searchElement == undefined || searchElement.length == 0)
		{
			$(this).children().each(function(){
				searchElement = $(this).findFirst(selector, allElements);
			});			
		}
		else
		{
			allElements.push(searchElement);
		}
		
		return allElements;
	},
	
	/**
	 * 按属性寻找所有第一级元素
	 * @param attr 属性
	 * @param allElements 找到的所有元素
	 * @returns
	 */
	findFirstAttr : function(attr, allElements){
		if($(this).children().length == 0){return $();}
		allElements = allElements == undefined ? [] : allElements;
		var curElement = $(this);
		
		$(curElement).children().each(function(){
			if($(this).attr(attr) != undefined)
			{
				allElements.push($(this));
			}
			else
			{
				$(this).findFirstAttr(attr, allElements);
			}
		});
		
		return allElements;
	},
	
	/**
	 * 对页面元素进行有效性验证,可在m.tool.juxinbox.com.js的jxTool里增加验证方法,
	 * 在validateFailed里加入验证失败信息,失败信息均以弹窗显示!!
	 * @returns {Boolean}
	 */
	validate : function(){
		var VALIDATE_ATTR = "validate";
		var elements = $(this);
		var result = true;
		
		$(elements).each(function(index){
			var validate = $(this).attr(VALIDATE_ATTR);
			validate = validate != undefined && validate != "" ? eval("(" + validate + ")") : null;
			
			if(isValidate($(this), validate))
			{
				result = performValidation(elements, $(this), validate);
				if(!result){return false;}
			}
		});
		return result;
		
		/**
		 * 判断是否需要验证
		 * @param element 元素
		 * @param validate 验证规则
		 * @returns {Boolean}
		 */
		function isValidate(element, validate)
		{
			return (element != undefined && element.length > 0 
					&& $(element).attr(VALIDATE_ATTR) != undefined && validate != null && (validate.required == true || $(element).val() != ""));
		}
		
		/**
		 * 执行验证
		 * @param allElements 所有待验证元素
		 * @param element 当前元素
		 * @param validate 验证规则
		 * @returns {Boolean}
		 */
		function performValidation(allElements, element, validate)
		{
			var val = $(element).val();
			var rules = validate.rules;
			rules = rules != undefined && rules != "" ? splitSeparator(rules) : [];
			
			if(val != undefined && rules.length > 0)
			{
				for(var i = 0;i < rules.length;i++)
				{
					var rule = rules[i];
					var errorMsg = "";
					var methodName = getMethodName(rule);
					var expandData = {"allElements" : allElements, "element" : element, "validate" : validate, "params" : getMethodParams(rule)};
					var result = eval("jxTool[methodName](val, expandData)");
					
					
					if((typeof result === 'boolean' && !result) || (typeof result === 'string' && result != ""))
					{
						errorMsg = makeErrorMsg($(element).attr("title"), rule, result);						
					}
					
					if(errorMsg != "")
					{	
						errorProcess(element, errorMsg);
						return false;
					}
				}
			}
			return true;
		}
		
		/**
		 * 切分分割符
		 */
		function splitSeparator(str)
		{
			return str.split(/,\s*/);
		}
		
		/**
		 * 获得验证方法名称
		 * @param rule 验证规则
		 */
		function getMethodName(rule)
		{
			var bracketIndex = rule.indexOf("(");
			return bracketIndex != -1 ? rule.substring(0, bracketIndex) : rule;
		}
		
		/**
		 * 获得验证方法参数
		 * @param rule 验证规则
		 */
		function getMethodParams(rule)
		{
			var bracketIndex = rule.indexOf("(");
			var param = bracketIndex != -1 ? rule.substring(bracketIndex, rule.length) : "";
			param = param.substring(1, param.length - 1);
			var params = splitSeparator(param);
			return params;
		}
		
		/**
		 * 作成错误消息
		 * @param title title属性,代入到错误信息中
		 * @param rule 规则
		 * @param result 验证结果
		 * @returns
		 */
		function makeErrorMsg(title, rule, result)
		{
			//验证方法返回错误信息的情况,直接返回
			if((typeof result === 'string' && result != "")){return result;}			
			
			//获取错误信息
			var errorMsg = validateFailed[rule] + "\n";
			title = title == undefined ? "" : title;
			
			//通过元素title对错误信息格式化
			var titles = title.split(",");
			var evalStr = "errorMsg.format(";
			for(var i = 0;i < titles.length;i++)
			{
				titles[i] = titles[i].trim();
				evalStr += "titles[" + i + "]";
				if(i <  titles.length - 1)
				{
					evalStr += ",";
				}
			}
			evalStr += ")";
			errorMsg = eval(evalStr);
			
			return errorMsg;
		}
		
		/**
		 * 错误处理
		 * @param element 验证元素
		 * @param errorMsg 错误信息
		 */
		function errorProcess(element, errorMsg)
		{
			$(element).focus();
			showError(errorMsg);
		}
		
		/**
		 * 显示错误信息
		 * @param errorMsg 错误信息
		 */
		function showError(errorMsg)
		{
			//若存在用户自定义的错误显示方法则调用，否则使用默认错误显示
			try
			{
				customShowError(errorMsg);
			}
			catch(e)
			{
				alerw(errorMsg);
			}
		}
	}
});



jQuery.extend({
	/**
	 * 把data对象属性填写到对应的页面元素上
	 * @param data 数据
	 * @param searchElement 搜索的元素(从该元素开始搜索,为该元素下所有元素设值)
	 * @param autoExpand 自动扩展(当前是一个列表的情况,如果存放数据的元素不足则复制一份之前的元素,以存放数据)
	 */
	setValueToPage : function(data, searchElement, autoExpand){
		var DEFAULT_SEARCH_ELEMENT = $("body");					//默认搜索元素body
		var SEARCH_ATTRNAME = "field";							//搜索属性名称
		var LINK_FORMAT = "link-{prop}";						//链接元素的name格式
		var rootElement = null;
		init();
		
		for(var i = 0;i < data.length;i++)
		{
			var curdata = data[i];
			var element = rootElement[i];
			
			//未找到当前数据所存放的元素,复制之前的元素,用于存放
			if(isNeedExpand(element))
			{
				element = elementExpand(element, i);
			}
			//无效的元素,跳过
			else if(noValidElement(element)){continue;}
			
			for(p in curdata)
			{
				//当前属性值是对象的情况,递归调用当前方法,对子集设值!
				if(isObj(curdata[p]))
				{
					element = haveLinkElement(p) ? $(DEFAULT_SEARCH_ELEMENT).find("[" + SEARCH_ATTRNAME + "=" + p + "]") : element;		//当前属性有链接元素的情况,把搜索元素指定到链接到的元素
					$.setValueToPage(curdata[p], element, autoExpand);					
				}
				else
				{
					setValue($($(element).findFirst("[" + SEARCH_ATTRNAME + "=" + p + "]")[0]), curdata[p]);
				}
			}
		}
		
		/**
		 * 初始化
		 */
		function init()
		{
			//数据不是数组，转成数组
			if(!(data instanceof Array))
			{
				data = [data];
			}
			
			//寻找根元素			
			searchElement = searchElement == undefined ? DEFAULT_SEARCH_ELEMENT : searchElement;
			autoExpand = autoExpand == undefined ? true : autoExpand;
			rootElement = findRootElement(searchElement, data[0]);
		}
		
		/**
		 * 寻找根元素
		 */
		function findRootElement(searchElement, props)
		{
			var rootElement = null;
			for(p in props)
			{
				var parentElement = $(searchElement).find("[" + SEARCH_ATTRNAME + "=" + p + "]").parent();
				parentElement = $(parentElement).attr(SEARCH_ATTRNAME) == undefined ? $(parentElement).closest("[" + SEARCH_ATTRNAME + "]") : parentElement;			
				rootElement = $(parentElement).is(searchElement) ? parentElement : $(searchElement).find("[" + SEARCH_ATTRNAME + "=" + $(parentElement).attr(SEARCH_ATTRNAME) + "]");
				if(parentElement.length == 1){break;}
			}
			return rootElement;
		}
		
		/**
		 * 元素需要扩展
		 */
		function isNeedExpand(element)
		{
			return (element == undefined || element.length == 0) && autoExpand;
		}
		
		/**
		 * 无效的元素
		 */
		function noValidElement(element)
		{
			return (element == undefined || element.length == 0);
		}
		
		/**
		 * 扩展元素
		 * @param element 元素
		 * @param index 索引
		 * @returns 返回扩展后的新元素
		 */
		function elementExpand(element, index)
		{
			var copyElement = rootElement[i-1];
			element = $(copyElement).clone(true);
			$(element).insertAfter(copyElement);
			rootElement[i] = element;
			return element;
		}
		
		/**
		 * 是对象
		 * @param val 值
		 * @returns {Boolean}
		 */
		function isObj(val)
		{
			return (val instanceof Object);
		}
		
		/**
		 * 通过属性名查找是否存在链接元素
		 * @param p 属性
		 * @returns {Boolean}
		 */
		function haveLinkElement(p)
		{
			
			var linkElement = $(DEFAULT_SEARCH_ELEMENT).find("[" + SEARCH_ATTRNAME + "=" + LINK_FORMAT.replace("{prop}", p) + "]");
			return (linkElement != undefined && linkElement.length > 0);
		}
		
		/**
		 * 把当前数值设置到对应元素上,对不同元素的设值方式进行处理(可自行扩展)
		 * @param element 元素
		 * @param val 值
		 */
		function setValue(element, val)
		{
			if(val == undefined || val == null){return;}
			
			if($(element).attr("value") != undefined)
			{
				$(element).val(val);
			}
			else if($(element).is("img"))
			{
				$(element).attr("src", val);
			}
			else
			{
				$(element).html(val);
			}
		}
	},


	/**
	 * 从页面指定区域取数据
	 * @param rootElement 根元素
	 * @param data 数据
	 * @param parentName 父级名称
	 * @returns 返回取得数据
	 */
	getValueInPage : function(rootElement, data, parentName){
		var DEFAULT_SEARCH_ELEMENT = $("body");					//默认搜索元素body
		var SEARCH_ATTRNAME = "field";							//搜索属性名称
		var ROOT_NAME = "root";									//根元素名称
		var LIST_TAG = "list";									//列表标记属性(强制指定元素为列表元素)
		var elementIndex = 0;
		init();
		
		//从根元素下取得所有第一级属性名称为SEARCH_ATTRNAME的元素,遍历
		var elements = $(rootElement).findFirstAttr(SEARCH_ATTRNAME);
		$(elements).each(function(index){
			var curElementName = $(this).attr(SEARCH_ATTRNAME);
			
			//有子元素的情况
			if(haveChild($(this)))
			{
				//是链接元素的情况,切分处理,取得元素名,从DEFAULT_SEARCH_ELEMENT元素下定位元素
				var tempRootElement = $(this);
				if(isLinkElement($(this)))
				{
					var nameSplit = curElementName.split("-");
					if(nameSplit != undefined && nameSplit.length == 2)
					{
						curElementName = nameSplit[1];
						tempRootElement = $(DEFAULT_SEARCH_ELEMENT).find("[" + SEARCH_ATTRNAME + "=" + nameSplit[1] + "]");
					}
				}
				
				var tempParentName = makePropName(parentName, $(this), curElementName);
				data = $.getValueInPage(tempRootElement, data, tempParentName);
			}
			else
			{
				elementIndex = 0;
				var name = makePropName(parentName, $(this));
				var val = getValue($(this));
				data[name] = val;
			}
		});
		
		return data;
		
		
		/**
		 * 初始化
		 */
		function init()
		{
			data = data == undefined ? {} : data;		
			rootElement = rootElement == undefined ? $(DEFAULT_SEARCH_ELEMENT).find("[" + SEARCH_ATTRNAME + "=" + ROOT_NAME + "]").length == 0 ? 
					DEFAULT_SEARCH_ELEMENT : $(DEFAULT_SEARCH_ELEMENT).find("[" + SEARCH_ATTRNAME + "=" + ROOT_NAME + "]") : rootElement;
			parentName = parentName == undefined ? "" : parentName;
		}
		
		/**
		 * 存在子集
		 * @param element 元素
		 */
		function haveChild(element)
		{
			return ($(element).findFirstAttr(SEARCH_ATTRNAME).length > 0 || isLinkName($(element).attr(SEARCH_ATTRNAME)));
		}
		
		/**
		 * 是链接名称
		 * @param elementName 元素名
		 * @returns
		 */
		function isLinkName(elementName)
		{
			return (/^link-.+$/.test(elementName));
		}
		
		/**
		 * 判断是否是链接元素
		 * @param element 元素
		 * @returns
		 */
		function isLinkElement(element)
		{
			return isLinkName($(element).attr(SEARCH_ATTRNAME));
		}
		

		/**
		 * 作成属性名称
		 * @param parentName 父级名称
		 * @param element 当前元素
		 * @param curElementName 当前元素名称
		 * @returns {String} 返回作成的名称
		 */
		function makePropName(parentName, element, curElementName)
		{
			var propName = "";
			if(curElementName != undefined)
			{
				var indexStr = "";
				
				if(isList(element))
				{
					indexStr = "[" + (elementIndex++) +"]";
				}

				propName = parentName != "" ? parentName + "." + curElementName + indexStr : curElementName + indexStr;
			}
			else
			{
				propName = parentName != "" ? parentName + "." + $(element).attr(SEARCH_ATTRNAME) : $(element).attr(SEARCH_ATTRNAME);
			}
			return propName;
		}
		
		/**
		 * 判断该元素是否是列表元素
		 * @param element 元素
		 * @returns {Boolean}
		 */
		function isList(element)
		{
			var lastElement = $(element).prev("[" + SEARCH_ATTRNAME + "]");
			var nextElement = $(element).next("[" + SEARCH_ATTRNAME + "]");
			if(lastElement != undefined && $(lastElement).attr(SEARCH_ATTRNAME) == $(element).attr(SEARCH_ATTRNAME) ||
					nextElement != undefined && $(nextElement).attr(SEARCH_ATTRNAME) == $(element).attr(SEARCH_ATTRNAME) || $(element).attr(LIST_TAG) != undefined)
			{
				return true;
			}
			return false;
		}
		
		
		/**
		 * 从页面取值,按不同元素进行取值(可自行扩展)
		 * @param element 元素
		 * @returns 返回取得值
		 */
		function getValue(element)
		{
			var val;
			
			if($(element).attr("value") != undefined)
			{
				val = $(element).val();
			}
			else if($(element).is("img"))
			{
				val = $(element).attr("src");
			}
			else
			{
				val = $(element).html();
			}
			
			return val;
		}
	},
	
	/**
	 * 发送请求
	 */
	sendRequest : function(request){
		if(request.url == undefined || request.url.trim() == "") throw new Error("1","url不能为空!");
		
		var url = request.url;
		var requestMethod = request.requestMethod == undefined ? "GET" : request.requestMethod;
		var data = request.data == undefined ? {} : request.data;
		if(requestMethod == "get" || requestMethod == "GET"){data["useCache"] = "true";}
		var fileId = data["fileId"];
		var needValidate = request.needValidate == undefined ? true : request.needValidate;
		var getElement = request.getDataElementId != undefined ? $(request.getDataElementId) : undefined;
		var setElement = request.setDataElementId != undefined ? $(request.setDataElementId) : undefined;
		var invokeMethod = request.invokeMethod != undefined ? request.invokeMethod : undefined;
		var defaultInvokeMethod = setElement != undefined ? defaultMethod : undefined;
		setElement = invokeMethod == undefined && setElement == undefined ? getElement != undefined ? getElement : undefined : setElement;
		var defaultError = request.defaultError == undefined ? true : request.defaultError;
		ajaxSetUp();
		
		//取得页面数据加入data
		if(getElement != undefined)
		{
			getDataInPage();
			
			//加入接收实体名称,默认为获取页面数据元素id
			data["entityName"] = data["entityName"] == undefined ? $(getElement).attr("id") : data["entityName"];
			
			//需要验证数据的情况
			if(needValidate)
			{
				if(!$(getElement).find("[validate]").validate())
				{
					return false;
				}
			}
		}
		
		if(fileId != undefined && fileId != "")
		{
			sendUpFileAjaxRequest();
		}
		else
		{
			sendAjaxRequest();
		}
	    
	    /**
	     * 从页面取得数据,加入data
	     */
	    function getDataInPage()
	    {
	    	var getdata = $.getValueInPage(getElement);
	    	console.log("==================取出页面数据==================");
	    	console.log(getdata);
	    	console.log("==================取出页面数据==================");
	    	for(p in getdata)
	    	{
	    		data[p] = getdata[p];
	    	}
	    }
	    
	    /**
	     * 设置ajax默认值
	     */
	    function ajaxSetUp()
	    {
	    	$.ajaxSetup({
	    		async : true,
	    		cache:true,
	    		dataType : 'json',
	    		
	            beforeSend: function(XMLHttpRequest){
	            	loading("start");
	            },
	            
	            complete: function(XMLHttpRequest){
	            	loading("stop");
	            },
	            
	            error: function(XMLHttpRequest, textStatus, thrownError){
	            	console.log("处理错误!!!");
	            	console.log(XMLHttpRequest);
	            	console.log(textStatus);
	            	console.log(thrownError);
	            }
	    	});
	    }
	    
	    /**
	     * 发送文件上传的ajax请求
	     */
	    function sendUpFileAjaxRequest()
	    {	
	    	$.ajaxFileUpload({
	    		type: "post",
	    	    secureuri : false,  
	    	    fileElementId : fileId,					//上传控件的id
	    	    url : url,								//请求路径
	    	    data: {returnType : 'json'},
	            
	            success: function(getdata){
	            	console.log(getdata);
	            	if(getdata.error == "Success")
	            	{
	            		if(defaultInvokeMethod != undefined){defaultInvokeMethod(getdata.returnData);}
	            	}
	            	
	            	//系统错误,或业务错误使用默认错误处理
	            	if((getdata.error == "SystemError" || getdata.error == "ServiceError") && defaultError)
	            	{
	            		defaultErrorProcess(getdata);
	            	}
	            	//没有错误,或非系统错误和业务错误的情况
	            	else
	            	{
	            		if(invokeMethod != undefined){invokeMethod(getdata);}
	            	}
	            }
	    	});
	    }
	    
	    /**
	     * 发送ajax请求
	     */
	    function sendAjaxRequest()
	    {
	    	$.ajax({
	    	    url : url,								//请求路径
	    	    type: requestMethod,
	    	    data: data,
	            
	            success: function(getdata){
	            	console.log(getdata);
	            	if(getdata.error == "Success")
	            	{
	            		if(defaultInvokeMethod != undefined){defaultInvokeMethod(getdata.returnData);}
	            	}
	            	
	            	//系统错误,或业务错误使用默认错误处理
	            	if((getdata.error == "SystemError" || getdata.error == "ServiceError") && defaultError)
	            	{
	            		defaultErrorProcess(getdata);
	            	}
	            	//没有错误,或非系统错误和业务错误, 或者不使用默认错误的情况
	            	else
	            	{
	            		if(invokeMethod != undefined){invokeMethod(getdata);}
	            	}
	            }
	    	});
	    }
	    
	    /**
	     * 默认成功处理方法
	     */
	    function defaultMethod(returnData)
	    {
	    	
	    	if(returnData != undefined)
	    	{
	    		$.setValueToPage(returnData, setElement, true);
	    	}
	    }
	    
	    /**
	     * 默认错误处理方法
	     */
	    function defaultErrorProcess(getdata)
	    {
	    	if(getdata.error == "SystemError")
	    	{
	    		console.log("错误原因:  " + getdata.errorMsg);
	    		alerw("系统错误!");
	    	}
	    	else
	    	{
	    		alerw(getdata.errorMsg);
	    	}
	    }
	}
});