package org.jumutang.project.sys.service;

import java.util.HashMap;
import java.util.List;

import org.jumutang.project.sys.model.SysLoginUserBean;
import org.jumutang.project.sys.model.SysMenuMode;

public interface ILoginService {
	
	// 查询用户信息
	public SysLoginUserBean queryLoginUserInfo(HashMap<String,String> map);

	// 查询登录的操作菜单
	public List<SysMenuMode>	 queryLoginUserMenu(HashMap<String,String> map);
}
