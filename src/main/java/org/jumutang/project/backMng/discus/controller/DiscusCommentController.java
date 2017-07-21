package org.jumutang.project.backMng.discus.controller;

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
import org.jumutang.project.backMng.discus.model.DiscusCommentMode;
import org.jumutang.project.backMng.discus.service.IDiscusCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/backMng/DiscusComment", method = { RequestMethod.GET, RequestMethod.POST })
public class DiscusCommentController extends BaseController{
	
	private static final Logger _LOGGER = Logger.getLogger(DiscusCommentController.class);
	
	@Autowired
	private IDiscusCommentService discusCommentService;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Page page=getPage(request);
		
		Map<String,String> queryParam=new HashMap<String, String>();
		queryParam.put("QUERY_NAME", getStr(request, "QUERY_NAME"));
		queryParam.put("DISCUSS_ID", getStr(request, "DISCUSS_ID"));
		
		List<DiscusCommentMode> list=discusCommentService.findList(queryParam, page);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/DiscusComment/DiscusComment_list.jsp");
		view.addObject(PAGE, page);
		view.addObject(LIST, list);
		view.addObject("queryParam", queryParam);
		
		return view;
	}

	@RequestMapping(value = "/view", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView view(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Integer ID=getInt(request, "ID");
		DiscusCommentMode mode=discusCommentService.findInfo(ID);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/DiscusComment/DiscusComment_view.jsp");
		view.addObject(BEAN, mode);
		
		return view;
	}
	
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView add(HttpServletRequest request , HttpServletResponse response , HttpSession session) {

		ModelAndView view=new ModelAndView("/jsp/backMng/DiscusComment/DiscusComment_add.jsp");
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
	public String save(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		try {
			DiscusCommentMode mode=new DiscusCommentMode();
			mode.setID(getStr(request, "ID"));
			mode.setDISCUSS_ID(getStr(request, "DISCUSS_ID"));
			mode.setUSER_ID(getStr(request, "USER_ID"));
			mode.setCONTENT(getStr(request, "CONTENT"));
			mode.setCREATE_TIME(getStr(request, "CREATE_TIME"));
			mode.setPRAISE_NUM(getStr(request, "PRAISE_NUM"));
			mode.setREPLY_NUM(getStr(request, "REPLY_NUM"));
			mode.setDELETE_FLAG(getStr(request, "DELETE_FLAG"));
			discusCommentService.saveInfo(mode);
			
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
		DiscusCommentMode mode=discusCommentService.findInfo(ID);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/DiscusComment/DiscusComment_edit.jsp");
		view.addObject(BEAN,mode);
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		try {
			DiscusCommentMode mode=new DiscusCommentMode();
			mode.setID(getStr(request, "ID"));
			mode.setDISCUSS_ID(getStr(request, "DISCUSS_ID"));
			mode.setUSER_ID(getStr(request, "USER_ID"));
			mode.setCONTENT(getStr(request, "CONTENT"));
			mode.setCREATE_TIME(getStr(request, "CREATE_TIME"));
			mode.setPRAISE_NUM(getStr(request, "PRAISE_NUM"));
			mode.setREPLY_NUM(getStr(request, "REPLY_NUM"));
			mode.setDELETE_FLAG(getStr(request, "DELETE_FLAG"));
			discusCommentService.updateInfo(mode);
			
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
			String  DISCUSS_ID=request.getParameter("DISCUSS_ID");
			discusCommentService.deleteInfo(id,DISCUSS_ID);
			return super.responseJsonText(ResultBean.newSuccessResult());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return super.responseJsonText(ResultBean.newErrorResult("删除失败"));
		}
	}
	
}