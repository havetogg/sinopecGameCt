package org.jumutang.project.weixinMng.mallMng.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jumutang.basicbox.model.JSONResultModel;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.tools.WXShareUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONSerializer;

@Controller
@RequestMapping(value = "/weChatJSConfigC", method = { RequestMethod.GET, RequestMethod.POST })
public class WeChatJSConfigController extends BaseController{

	
	@Value(value = "#{propertyFactoryBean['appID']}")
	private String appID;

	@Value(value = "#{propertyFactoryBean['appSecret']}")
	private String appSecret;
	
	private static final Logger _LOGGER = Logger.getLogger(WeChatJSConfigController.class);
	/**
	 * 微信分享
	 * @param request
	 * @param response
	 * @param session
	 * @param shareUrl
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getWeCharJSConfigM" , method = {RequestMethod.GET , RequestMethod.POST} )
	public String getWeCharJSConfig(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "currUrl" , required = true)String currUrl){
		JSONResultModel<String> jsonResultModel = new JSONResultModel<String>();
		try {
			String openID = super.getWxLoginUser(request).getOPEN_ID();
			WXShareUtil shareUtil = WXShareUtil.getInstance(appID, appSecret);
			String config = shareUtil.genJSSDKConf(currUrl);
			jsonResultModel.setCode(1).setMsg("获取微信shareconfig成功！").setResultObject(config);
		} catch (Exception e) {
			_LOGGER.error(e.getMessage(), e);
			jsonResultModel.setCode(0).setMsg("获取微信shareconfig失败！");
		}
		String returnStr = JSONSerializer.toJSON(jsonResultModel).toString();
		return returnStr;
	}
	
}
