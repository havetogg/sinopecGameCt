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
import org.jumutang.project.backMng.news.model.NewsCommentMode;
import org.jumutang.project.backMng.news.service.INewsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/backMng/NewsComment", method = { RequestMethod.GET, RequestMethod.POST })
public class NewsCommentController extends BaseController{
	
	private static final Logger _LOGGER = Logger.getLogger(NewsCommentController.class);
	
	@Autowired
	private INewsCommentService newsCommentService;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Page page=getPage(request);
		
		Map<String,String> queryParam=new HashMap<String, String>();
		queryParam.put("QUERY_NAME", getStr(request, "QUERY_NAME"));
		
		queryParam.put("NEWS_ID",getStr(request, "NEWS_ID"));
		
		List<NewsCommentMode> list=newsCommentService.findList(queryParam, page);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/NewsComment/NewsComment_list.jsp");
		view.addObject(PAGE, page);
		view.addObject(LIST, list);
		view.addObject("queryParam", queryParam);
		
		return view;
	}

	@RequestMapping(value = "/view", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView view(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Integer ID=getInt(request, "ID");
		NewsCommentMode mode=newsCommentService.findInfo(ID);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/NewsComment/NewsComment_view.jsp");
		view.addObject(BEAN, mode);
		
		return view;
	}
	
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView add(HttpServletRequest request , HttpServletResponse response , HttpSession session) {

		ModelAndView view=new ModelAndView("/jsp/backMng/NewsComment/NewsComment_add.jsp");
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
	public String save(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		try {
			NewsCommentMode mode=new NewsCommentMode();
			mode.setID(getStr(request, "ID"));
			mode.setUSER_ID(getStr(request, "USER_ID"));
			mode.setNEWS_ID(getStr(request, "NEWS_ID"));
			mode.setCONTENT(getStr(request, "CONTENT"));
			mode.setCREATE_TIME(getStr(request, "CREATE_TIME"));
			mode.setAUDIT_FLAG(getStr(request, "AUDIT_FLAG"));
			mode.setDELETE_FLAG(getStr(request, "DELETE_FLAG"));
			newsCommentService.saveInfo(mode);
			
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
		NewsCommentMode mode=newsCommentService.findInfo(ID);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/NewsComment/NewsComment_edit.jsp");
		view.addObject(BEAN,mode);
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		try {
			NewsCommentMode mode=new NewsCommentMode();
			mode.setID(getStr(request, "ID"));
			mode.setUSER_ID(getStr(request, "USER_ID"));
			mode.setNEWS_ID(getStr(request, "NEWS_ID"));
			mode.setCONTENT(getStr(request, "CONTENT"));
			mode.setCREATE_TIME(getStr(request, "CREATE_TIME"));
			mode.setAUDIT_FLAG(getStr(request, "AUDIT_FLAG"));
			mode.setDELETE_FLAG(getStr(request, "DELETE_FLAG"));
			newsCommentService.updateInfo(mode);
			
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
			String  NEWS_ID=request.getParameter("NEWS_ID");
			newsCommentService.deleteInfo(id,NEWS_ID);
			return super.responseJsonText(ResultBean.newSuccessResult());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return super.responseJsonText(ResultBean.newErrorResult("删除失败"));
		}
	}
	
}