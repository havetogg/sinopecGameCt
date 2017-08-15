package org.jumutang.project.base;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jumutang.project.appMng.mallMng.model.AppModel;
import org.jumutang.project.sys.model.SysLoginUserBean;
import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.WxloginUserMode;
import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public abstract class BaseController {
	
	protected final String PAGE="page";
	
	protected final String LIST="list";

	protected final String BEAN="bean";
	
	private final String CURRENT_USER="currUser";
	
	private final String CURRENT_WXUSER="WxloginUser";

	private final String CURRENT_APPUSER="AppUser";
	
	public Page getPage(HttpServletRequest request){
		Page page=new Page();
		
		String currentPage=request.getParameter("page.currentPage");
		if(currentPage!=null&&currentPage.trim().length()>0){
			page.setCurrentPage(Integer.parseInt(currentPage));
		}
		String pageSize=request.getParameter("page.pageSize");
		if(pageSize!=null&&pageSize.trim().length()>0){
			page.setPageSize(Integer.parseInt(pageSize));
		}
		return page;
	}
	/**
	 * 获取变量
	 * @param name
	 * @return
	 */
	protected String getStr(HttpServletRequest request,String name){
		return request.getParameter(name);
	}
	/**
	 * 获取变量
	 * @param name
	 * @return
	 */
	protected Integer getInt(HttpServletRequest request,String name){
		String value=getStr(request,name);
		if(value==null){
			return null;
		}else{
			return Integer.parseInt(value);
		}
	}
	/**
	 * 获取变量
	 * @param name
	 * @return
	 */
	protected Float getFloat(HttpServletRequest request,String name){
		String value=getStr(request,name);
		if(value==null){
			return null;
		}else{
			return Float.parseFloat(value);
		}
	}
	
	protected String responseJsonText(Object object){
		if(object instanceof List){
			return JSONArray.fromObject(object).toString();
		}else{
			return JSONObject.fromObject(object).toString();
		}
		
	}

	protected void setLoginUser(HttpServletRequest request,SysLoginUserBean userbean){
		request.getSession().setAttribute(CURRENT_USER, userbean);
	}
	
	@SuppressWarnings("unchecked")
	protected SysLoginUserBean getLoginUser(HttpServletRequest request){
		if(request.getSession().getAttribute(CURRENT_USER)==null){
			return null;
		}else{
			SysLoginUserBean bean= (SysLoginUserBean) request.getSession().getAttribute(CURRENT_USER);
			return bean;
		}
	}
	
	// 微信端
	protected void setWxLoginUser(HttpServletRequest request,MallUserMode userBean){
		request.getSession().setAttribute(CURRENT_WXUSER, userBean);
	}
	// 微信端
	@SuppressWarnings("unchecked")
	protected MallUserMode getWxLoginUser(HttpServletRequest request){
		if(request.getSession().getAttribute(CURRENT_WXUSER)==null){
			return null;
		}else{
			MallUserMode bean= (MallUserMode) request.getSession().getAttribute(CURRENT_WXUSER);
			return bean;
		}
	}

	// app端
	protected void setAppUser(HttpServletRequest request,AppModel appModel){
		request.getSession().setAttribute(CURRENT_APPUSER, appModel);
	}
	// app端
	@SuppressWarnings("unchecked")
	protected AppModel getAppUser(HttpServletRequest request){
		if(request.getSession().getAttribute(CURRENT_APPUSER)==null){
			return null;
		}else{
			AppModel bean= (AppModel) request.getSession().getAttribute(CURRENT_APPUSER);
			return bean;
		}
	}
	
	protected String saveFile(MultipartFile file,HttpServletRequest request,String filename){
		if(file!=null&&!StringUtil.isEmpty(file.getOriginalFilename())){
			String fileName = file.getOriginalFilename();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String targetFile=null;
			if(StringUtil.isEmpty(filename)){
				targetFile=sdf.format(new Date())+fileName.substring(fileName.lastIndexOf("."));
			}else{
				//自己前面加前标识了
				targetFile=filename+sdf.format(new Date())+fileName.substring(fileName.lastIndexOf("."));
			}
			String RELATIVE_PATH=".."+request.getContextPath()+"Img/";
		    String pathDir =request.getRealPath("/")+RELATIVE_PATH;
		    
//			String pathDir =request.getRealPath("/")+request.getContextPath()+"Img/";
	        File dirFile=new File(pathDir);
	        if(!dirFile.exists()){
	        	 dirFile.mkdir();
	        }
			
			try {
				file.transferTo(new File(pathDir+targetFile));
				return RELATIVE_PATH+targetFile;
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	protected String getBasePath(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		return basePath;
	}
}
