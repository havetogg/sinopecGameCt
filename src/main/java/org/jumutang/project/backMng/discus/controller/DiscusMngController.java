package org.jumutang.project.backMng.discus.controller;

import java.awt.Image;
import java.io.File;
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
import org.jumutang.project.tools.ImagesResize;
import org.jumutang.project.backMng.discus.model.DiscusMngMode;
import org.jumutang.project.backMng.discus.service.IDiscusMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/backMng/DiscusMng", method = { RequestMethod.GET, RequestMethod.POST })
public class DiscusMngController extends BaseController{
	
	private static final Logger _LOGGER = Logger.getLogger(DiscusMngController.class);
	
	@Autowired
	private IDiscusMngService discusMngService;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Page page=getPage(request);
		
		Map<String,String> queryParam=new HashMap<String, String>();
		queryParam.put("QUERY_NAME", getStr(request, "QUERY_NAME"));
		
		List<DiscusMngMode> list=discusMngService.findList(queryParam, page);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/discus/DiscusMng/DiscusMng_list.jsp");
		view.addObject(PAGE, page);
		view.addObject(LIST, list);
		view.addObject("queryParam", queryParam);
		
		return view;
	}

	@RequestMapping(value = "/view", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView view(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Integer ID=getInt(request, "ID");
		DiscusMngMode mode=discusMngService.findInfo(ID);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/discus/DiscusMng/DiscusMng_view.jsp");
		view.addObject(BEAN, mode);
		
		return view;
	}
	
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView add(HttpServletRequest request , HttpServletResponse response , HttpSession session) {

		ModelAndView view=new ModelAndView("/jsp/backMng/discus/DiscusMng/DiscusMng_add.jsp");
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
	public String save(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		try {
			DiscusMngMode mode=new DiscusMngMode();
			mode.setID(getStr(request, "ID"));
			mode.setNAME(getStr(request, "NAME"));
			mode.setIN_USE_FLAG(getStr(request, "IN_USE_FLAG"));
			mode.setPUBLISH_TIME(getStr(request, "PUBLISH_TIME"));
			mode.setCONTENT(getStr(request, "CONTENT"));
			
			String IMAGE_URL=getStr(request, "IMAGE_URL");
			String[] split = IMAGE_URL.split("@@");
			mode.setORIGINAL_URL(split[0]);
			mode.setCUTTED_PATH(split[1]);
			
			mode.setREPLY_NUM("0");
			mode.setPRAISE_NUM("0");
			mode.setCONCERN_NUM("0");
			mode.setONLINE_START_TIME(getStr(request, "ONLINE_START_TIME"));
			mode.setONLINE_END_TIME(getStr(request, "ONLINE_END_TIME"));
//			mode.setCREATE_TIME(getStr(request, "CREATE_TIME"));
//			mode.setLAST_MODIFY_TIME(getStr(request, "LAST_MODIFY_TIME"));
			mode.setSHOW_ORDER(getStr(request, "SHOW_ORDER"));
			mode.setDELETE_FLAG("0");
			discusMngService.saveInfo(mode);
			
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
		DiscusMngMode mode=discusMngService.findInfo(ID);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/discus/DiscusMng/DiscusMng_edit.jsp");
		view.addObject(BEAN,mode);
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		try {
			DiscusMngMode mode=new DiscusMngMode();
			mode.setID(getStr(request, "ID"));
			mode.setNAME(getStr(request, "NAME"));
			mode.setIN_USE_FLAG(getStr(request, "IN_USE_FLAG"));
			mode.setPUBLISH_TIME(getStr(request, "PUBLISH_TIME"));
			mode.setCONTENT(getStr(request, "CONTENT"));
			
			String IMAGE_URL=getStr(request, "IMAGE_URL");
			String[] split = IMAGE_URL.split("@@");
			mode.setORIGINAL_URL(split[0]);
			mode.setCUTTED_PATH(split[1]);
			/*mode.setREPLY_NUM(getStr(request, "REPLY_NUM"));
			mode.setPRAISE_NUM(getStr(request, "PRAISE_NUM"));
			mode.setCONCERN_NUM(getStr(request, "CONCERN_NUM"));*/
			mode.setONLINE_START_TIME(getStr(request, "ONLINE_START_TIME"));
			mode.setONLINE_END_TIME(getStr(request, "ONLINE_END_TIME"));
			/*mode.setCREATE_TIME(getStr(request, "CREATE_TIME"));
			mode.setLAST_MODIFY_TIME(getStr(request, "LAST_MODIFY_TIME"));*/
			mode.setSHOW_ORDER(getStr(request, "SHOW_ORDER"));
//			mode.setDELETE_FLAG(getStr(request, "DELETE_FLAG"));
			discusMngService.updateInfo(mode);
			
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
			discusMngService.deleteInfo(id);
			return super.responseJsonText(ResultBean.newSuccessResult());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return super.responseJsonText(ResultBean.newErrorResult("删除失败"));
		}
	}
	

	@ResponseBody
	@RequestMapping(value = "/imgupdate", method = { RequestMethod.GET, RequestMethod.POST })
	public String imgupdate(HttpServletRequest request , HttpServletResponse response , HttpSession session,
			@RequestParam(value = "IMAGE_URL", required = true)MultipartFile file){
    	try{
    		String FILE_NAME=file.getOriginalFilename().toUpperCase();
    		if(FILE_NAME.endsWith(".JPG")
    				||FILE_NAME.endsWith(".JPEG")
    				||FILE_NAME.endsWith(".BMP")
    				||FILE_NAME.endsWith(".PNG")
    				||FILE_NAME.endsWith(".GIF")){
    			
    		}else{
    			return super.responseJsonText(ResultBean.newErrorResult("请上传JPG,JPEG,BMP,PNG,GIF格式的图片"));
    		}
    		// 下载图片
        	String IMAGE_URL =super.saveFile(file, request,"NOT_CUT");
    		String ABSOLUTE_PATH=request.getRealPath("/")+IMAGE_URL;
    		File file2 = new File(ABSOLUTE_PATH);
    		Image src=javax.imageio.ImageIO.read(file2);
        	int width=src.getWidth(null);
        	int heigth=src.getHeight(null);
        	final int OFFSET=15;
        	//if(Math.abs(width-640)>OFFSET||Math.abs(heigth-420)>15){
        		//new File(ABSOLUTE_PATH).delete();
        		
        		//return super.responseJsonText(ResultBean.newErrorResult("当前图片尺寸为:"+width+"*"+heigth+",请上传640*420图片"));
        	//}

        	//if(file2.length()>300*1024){
        		// 图上的大小要小于300K
        		//return super.responseJsonText(ResultBean.newErrorResult("当前图片大小为:"+file2.length()/1024+",请上传小于300K的图片"));
        	//}
			//图片的裁剪
	    	ImagesResize aimg=new ImagesResize();
        	String IMAGE_URL_CUTED =IMAGE_URL.replace("NOT_CUT", "CUTED");
			aimg.zoomOutImage(request.getRealPath("/")+IMAGE_URL,request.getRealPath("/")+IMAGE_URL_CUTED,161,161,false);
        	return super.responseJsonText(ResultBean.newSuccessResult(IMAGE_URL+"@@"+IMAGE_URL_CUTED));
    	}catch(Exception e){
    		return super.responseJsonText(ResultBean.newErrorResult("图片上传不成功"));
    	}
    	
	}
	
}