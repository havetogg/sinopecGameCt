package org.jumutang.project.backMng.news.controller;

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
import org.jumutang.project.backMng.news.model.NewsMngMode;
import org.jumutang.project.backMng.news.service.INewsMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/backMng/NewsMng", method = { RequestMethod.GET, RequestMethod.POST })
public class NewsMngController extends BaseController{
	
	private static final Logger _LOGGER = Logger.getLogger(NewsMngController.class);
	
	@Autowired
	private INewsMngService newsMngService;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Page page=getPage(request);
		
		Map<String,String> queryParam=new HashMap<String, String>();
		queryParam.put("QUERY_NAME", getStr(request, "QUERY_NAME"));
		
		List<NewsMngMode> list=newsMngService.findList(queryParam, page);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/NewsMng/NewsMng_list.jsp");
		view.addObject(PAGE, page);
		view.addObject(LIST, list);
		view.addObject("queryParam", queryParam);
		
		return view;
	}

	@RequestMapping(value = "/view", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView view(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Integer ID=getInt(request, "ID");
		NewsMngMode mode=newsMngService.findInfo(ID);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/NewsMng/NewsMng_view.jsp");
		view.addObject(BEAN, mode);
		
		return view;
	}
	
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView add(HttpServletRequest request , HttpServletResponse response , HttpSession session) {

		ModelAndView view=new ModelAndView("/jsp/backMng/NewsMng/NewsMng_add.jsp");
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
	public String save(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		try {
			NewsMngMode mode=new NewsMngMode();
			mode.setID(getStr(request, "ID"));
			mode.setNAME(getStr(request, "NAME"));
			mode.setREMARK(getStr(request, "REMARK"));
			mode.setIN_USE_FLAG(getStr(request, "IN_USE_FLAG"));
			mode.setPUBLISH_TIME(getStr(request, "PUBLISH_TIME"));
			mode.setCONTENT(getStr(request, "RICH_CONTENT_ID"));
			mode.setONLINE_START_TIME(getStr(request, "ONLINE_START_TIME"));
			mode.setONLINE_END_TIME(getStr(request, "ONLINE_END_TIME"));
			mode.setCREATE_TIME(getStr(request, "CREATE_TIME"));
			mode.setLAST_MODIFY_TIME(getStr(request, "LAST_MODIFY_TIME"));
			mode.setSHOW_ORDER(getStr(request, "SHOW_ORDER"));
			mode.setNEWSURL(getStr(request, "NEWSURL"));
			mode.setCOMMENT_NUM("0");
			mode.setDELETE_FLAG("0");
			
			mode.setAUTHOR_NAME(getStr(request, "AUTHOR_NAME"));
			mode.setAUTHOR_LINK(getStr(request, "AUTHOR_LINK"));
			mode.setDIANZ_BASE(getStr(request, "DIANZ_BASE"));
			mode.setCLICK_BASE(getStr(request, "CLICK_BASE"));
			newsMngService.saveInfo(mode);
			
			return super.responseJsonText(ResultBean.newSuccessResult());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return super.responseJsonText(ResultBean.newErrorResult("保存失败"));
		}
	}
	
	@RequestMapping(value = "/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView edit(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Integer ID=getInt(request, "ID");
		NewsMngMode mode=newsMngService.findInfo(ID);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/NewsMng/NewsMng_edit.jsp");
		view.addObject(BEAN,mode);
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		try {
			NewsMngMode mode=new NewsMngMode();
			mode.setID(getStr(request, "ID"));
			mode=newsMngService.findInfo(Integer.parseInt(getStr(request, "ID")));
			mode.setNAME(getStr(request, "NAME"));
			mode.setREMARK(getStr(request, "REMARK"));
			mode.setIN_USE_FLAG(getStr(request, "IN_USE_FLAG"));
			mode.setPUBLISH_TIME(getStr(request, "PUBLISH_TIME"));
			mode.setCONTENT(getStr(request, "RICH_CONTENT_ID"));
			mode.setONLINE_START_TIME(getStr(request, "ONLINE_START_TIME"));
			mode.setONLINE_END_TIME(getStr(request, "ONLINE_END_TIME"));
			mode.setSHOW_ORDER(getStr(request, "SHOW_ORDER"));
			mode.setNEWSURL(getStr(request, "NEWSURL"));
			
			mode.setAUTHOR_NAME(getStr(request, "AUTHOR_NAME"));
			mode.setAUTHOR_LINK(getStr(request, "AUTHOR_LINK"));
			mode.setDIANZ_BASE(getStr(request, "DIANZ_BASE"));
			mode.setCLICK_BASE(getStr(request, "CLICK_BASE"));
			newsMngService.updateInfo(mode);
			
			return super.responseJsonText(ResultBean.newSuccessResult());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return super.responseJsonText(ResultBean.newErrorResult("保存失败"));
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		try {
			Integer id=Integer.parseInt(request.getParameter("ID"));
			newsMngService.deleteInfo(id);
			return super.responseJsonText(ResultBean.newSuccessResult());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return super.responseJsonText(ResultBean.newErrorResult("删除失败"));
		}
	}
	
}