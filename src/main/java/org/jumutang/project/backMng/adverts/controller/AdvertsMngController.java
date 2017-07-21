package org.jumutang.project.backMng.adverts.controller;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jumutang.project.backMng.adverts.model.AdvertsMngMode;
import org.jumutang.project.backMng.adverts.service.IAdvertsMngService;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.base.Page;
import org.jumutang.project.base.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/backMng/adverts", method = { RequestMethod.GET, RequestMethod.POST })
public class AdvertsMngController extends BaseController{
	
	private static final Logger _LOGGER = Logger.getLogger(AdvertsMngController.class);
	
	@Autowired
	private IAdvertsMngService advertsMngService;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Page page=getPage(request);
		
		Map<String,String> queryParam=new HashMap<String, String>();
		
		queryParam.put("name", getStr(request, "QUERY_NAME"));
		
		List<AdvertsMngMode> list=advertsMngService.findList(queryParam, page);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/adverts/adverts_list.jsp");
		view.addObject(PAGE, page);
		view.addObject(LIST, list);
		view.addAllObjects(queryParam);
		return view;
	}

	@RequestMapping(value = "/view", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView view(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Integer ID=getInt(request, "ID");
		AdvertsMngMode mode=advertsMngService.findInfo(ID);
		
		ModelAndView view=new ModelAndView("/jsp/backMng/adverts/adverts_view.jsp");
		view.addObject(BEAN, mode);
		
		return view;
	}
	
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView add(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		ModelAndView view=new ModelAndView("/jsp/backMng/adverts/adverts_add.jsp");
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
	public String save(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		try {
			AdvertsMngMode mode=new AdvertsMngMode();
			mode.setID(getStr(request, "ID"));
			mode.setNAME(getStr(request, "NAME"));
			mode.setLINK_URL(getStr(request, "LINK_URL"));
			mode.setBANNER_TYPE(getStr(request, "BANNER_TYPE"));
			mode.setIMAGE_URL(getStr(request, "IMAGE_URL"));
			mode.setIN_USE_FLAG(getStr(request, "IN_USE_FLAG"));
			mode.setSHOW_ORDER(getStr(request, "SHOW_ORDER"));
			mode.setDELETE_FLAG(getStr(request, "DELETE_FLAG"));
			advertsMngService.saveInfo(mode);
			
			return super.responseJsonText(ResultBean.newSuccessResult());
		} catch (Exception e) {
			e.printStackTrace();
			return super.responseJsonText(ResultBean.newErrorResult("保存失败"));
		}
	}
	
	@RequestMapping(value = "/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView edit(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		Integer ID=getInt(request, "ID");
		AdvertsMngMode mode=advertsMngService.findEditInfo(ID);
		ModelAndView view=new ModelAndView("/jsp/backMng/adverts/adverts_edit.jsp");
		view.addObject(BEAN,mode);
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		try {
			AdvertsMngMode mode=new AdvertsMngMode();
			mode.setID(getStr(request, "ID"));
			mode.setNAME(getStr(request, "NAME"));
			mode.setIMAGE_URL(getStr(request, "IMAGE_URL"));
			mode.setSHOW_ORDER(getStr(request, "SHOW_ORDER"));
			mode.setLINK_URL(getStr(request, "LINK_URL"));
			mode.setBANNER_TYPE(getStr(request, "BANNER_TYPE"));
			mode.setIN_USE_FLAG(getStr(request, "IN_USE_FLAG"));
			
			advertsMngService.updateInfo(mode);
			
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
			advertsMngService.deleteInfo(id);
			return super.responseJsonText(ResultBean.newSuccessResult());
		} catch (Exception e) {
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
        	String IMAGE_URL =super.saveFile(file, request,null);
    		String ABSOLUTE_PATH=request.getRealPath("/")+IMAGE_URL;
    		System.out.println("ABSOLUTE_PATH = "+ABSOLUTE_PATH);
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
	    	//ImagesResize aimg=new ImagesResize();
			//aimg.zoomOutImage(request.getRealPath("/")+IMAGE_URL,request.getRealPath("/")+IMAGE_URL,640,420,false);
        	return super.responseJsonText(ResultBean.newSuccessResult(IMAGE_URL));
    	}catch(Exception e){
    		return super.responseJsonText(ResultBean.newErrorResult("图片上传不成功"));
    	}
    	
	}
	
}
