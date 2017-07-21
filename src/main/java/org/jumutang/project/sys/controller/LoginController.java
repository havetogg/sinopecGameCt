package org.jumutang.project.sys.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jumutang.basicbox.model.JSONResultModel;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.sys.model.SysLoginUserBean;
import org.jumutang.project.sys.model.SysMenuMode;
import org.jumutang.project.sys.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;

/*
 * 部门管理
 */
@Controller
@RequestMapping(value = "/LoginC", method = { RequestMethod.GET, RequestMethod.POST })
public class LoginController extends BaseController{
	
	private static final Logger _LOGGER = Logger.getLogger(LoginController.class);
	
	@Autowired
	private ILoginService loginService;
	
	@Value("#{propertyFactoryBean['appID']}")
	private String appID;

	// 登录处理
	@ResponseBody
	@RequestMapping(value = "/loginM" , method = {RequestMethod.GET , RequestMethod.POST})
	public String login(HttpServletRequest request , HttpServletResponse response , HttpSession session , 
						@RequestParam(value = "loginName" , required = true)String loginName,
						@RequestParam(value = "loginPass" , required = true)String loginPass){
		JSONResultModel<String> jsonResult = new JSONResultModel<String>();
		try {
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("loginName", loginName);
			map.put("loginPass", loginPass);
			SysLoginUserBean loginuser = loginService.queryLoginUserInfo(map);
			if (null != loginuser) {
                // 取得用户可以操作的菜单
				HashMap<String,String> getmenumap = new HashMap<String,String>();
				getmenumap.put("USER_TYPE", String.valueOf(loginuser.getUSER_TYPE()));
				getmenumap.put("USER_ID", String.valueOf(loginuser.getID()));
				List<SysMenuMode> currUserMenu = loginService.queryLoginUserMenu(getmenumap);
				
				setLoginUser(request, loginuser);
				session.setAttribute("currUserMenu", currUserMenu);
				jsonResult.setCode(1).setMsg("登录成功！");
			}else {
				jsonResult.setCode(0).setMsg("登录失败！用户名或密码错误");
			}
			return JSONObject.fromObject(jsonResult).toString();
		} catch (Exception e) {
			_LOGGER.error(e.getMessage(), e);
			jsonResult.setCode(0).setMsg("登录失败！系统异常！");
			return JSONObject.fromObject(jsonResult).toString();
		}
	}
	
	@RequestMapping(value = "/logoutM" , method = {RequestMethod.GET , RequestMethod.POST})
	public String logout(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		session.invalidate();
		return "redirect:/index.html";
	}
	
}