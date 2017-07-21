package org.jumutang.project.weixinMng.mallMng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.mallMng.model.WxAdvertsMngMode;
import org.jumutang.project.weixinMng.mallMng.service.IWxAdvertsMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/weixinMng/WxAdvertsMng", method = { RequestMethod.GET, RequestMethod.POST })
public class WxAdvertsMngController extends BaseController{
	
	private static final Logger _LOGGER = Logger.getLogger(WxAdvertsMngController.class);
	
	@Autowired
	private IWxAdvertsMngService wxadvertsMngService;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Page page=getPage(request);
		
		Map<String,String> queryParam=new HashMap<String, String>();
		
		queryParam.put("name", getStr(request, "QUERY_NAME"));
		
		List<WxAdvertsMngMode> list=wxadvertsMngService.findList(queryParam, page);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/adverts/adverts_list.jsp");
		view.addObject(PAGE, page);
		view.addObject(LIST, list);
		view.addAllObjects(queryParam);
		return view;
	}

	
}
