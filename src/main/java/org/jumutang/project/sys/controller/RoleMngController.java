package org.jumutang.project.sys.controller;

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
import org.jumutang.project.base.TreeModel;
import org.jumutang.project.sys.model.RoleMngMode;
import org.jumutang.project.sys.service.IRoleMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/RoleMng", method = { RequestMethod.GET, RequestMethod.POST })
public class RoleMngController extends BaseController{
	
	private static final Logger _LOGGER = Logger.getLogger(RoleMngController.class);
	
	@Autowired
	private IRoleMngService roleMngService;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Page page=getPage(request);
		
		Map<String,String> queryParam=new HashMap<String, String>();
		queryParam.put("QUERY_NAME", getStr(request, "QUERY_NAME"));

		List<RoleMngMode> list=roleMngService.findList(queryParam, page);
		
		ModelAndView view=new ModelAndView("/jsp/sys/RoleMng/RoleMng_list.jsp");
		view.addObject(PAGE, page);
		view.addObject(LIST, list);
		view.addObject("queryParam",queryParam);

		return view;
	}

	@RequestMapping(value = "/view", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView view(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Integer ID=getInt(request, "ID");
		RoleMngMode mode=roleMngService.findInfo(ID);
		
		ModelAndView view=new ModelAndView("/jsp/sys/RoleMng/RoleMng_view.jsp");
		view.addObject(BEAN, mode);
		
		return view;
	}
	
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView add(HttpServletRequest request , HttpServletResponse response , HttpSession session) {

		ModelAndView view=new ModelAndView("/jsp/sys/RoleMng/RoleMng_add.jsp");
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
	public String save(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		try {
			RoleMngMode mode=new RoleMngMode();
			mode.setID(getInt(request, "ID"));
			mode.setNAME(getStr(request, "NAME"));
			mode.setCREATE_USERID(getLoginUser(request).getID());
			mode.setCHECKED_NODE_IDS(getStr(request, "CHECKED_NODE_IDS"));
			
			roleMngService.saveInfo(mode);
			
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
		RoleMngMode mode=roleMngService.findInfo(ID);
		
		ModelAndView view=new ModelAndView("/jsp/sys/RoleMng/RoleMng_edit.jsp");
		view.addObject(BEAN,mode);
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		try {
			RoleMngMode mode=new RoleMngMode();
			mode.setID(getInt(request, "ID"));
			mode.setNAME(getStr(request, "NAME"));
			mode.setCHECKED_NODE_IDS(getStr(request, "CHECKED_NODE_IDS"));
			roleMngService.updateInfo(mode);
			
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
			roleMngService.deleteInfo(id);
			return super.responseJsonText(ResultBean.newSuccessResult());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return super.responseJsonText(ResultBean.newErrorResult("删除失败"));
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadMenuTree", method = { RequestMethod.GET, RequestMethod.POST })
	public String loadMenuTree(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Integer ROLE_ID=getInt(request, "ROLE_ID");
		Integer PARENT_ID=getInt(request, "parentId");
		String LOAD_TYPE=getStr(request, "LOAD_TYPE");
		List<TreeModel> list=roleMngService.loadTree(ROLE_ID, PARENT_ID, LOAD_TYPE);
		
		return JSONArray.fromObject(list).toString();
	}
	
}
