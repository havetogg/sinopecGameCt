package org.jumutang.project.backMng.userCenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.base.Page;
import org.jumutang.project.base.ResultBean;
import org.jumutang.project.backMng.userCenter.model.UserCenterMngMode;
import org.jumutang.project.backMng.userCenter.service.IUserCenterMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/backMng/UserCenterMng", method = { RequestMethod.GET, RequestMethod.POST })
public class UserCenterMngController extends BaseController{
	
	private static final Logger _LOGGER = Logger.getLogger(UserCenterMngController.class);
	
	@Autowired
	private IUserCenterMngService userCenterMngService;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Page page=getPage(request);
		
		Map<String,String> queryParam=new HashMap<String, String>();
		queryParam.put("QUERY_NAME", getStr(request, "QUERY_NAME"));
		
		List<UserCenterMngMode> list=userCenterMngService.findList(queryParam, page);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/userCenter/UserCenterMng/UserCenterMng_list.jsp");
		view.addObject(PAGE, page);
		view.addObject(LIST, list);
		view.addObject("queryParam", queryParam);
		
		return view;
	}

	@RequestMapping(value = "/view", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView view(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Integer ID=getInt(request, "ID");
		UserCenterMngMode mode=userCenterMngService.findInfo(ID);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/userCenter/UserCenterMng/UserCenterMng_view.jsp");
		view.addObject(BEAN, mode);
		
		return view;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		try {
			Integer id=Integer.parseInt(request.getParameter("ID"));
			userCenterMngService.deleteInfo(id);
			return super.responseJsonText(ResultBean.newSuccessResult());
		} catch (Exception e) {
			e.printStackTrace();
			return super.responseJsonText(ResultBean.newErrorResult("删除失败"));
		}
	}
	
}