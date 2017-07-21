package org.jumutang.project.sys.dao;

import java.util.HashMap;
import java.util.List;

import org.jumutang.project.sys.model.SysLoginUserBean;
import org.jumutang.project.sys.model.SysMenuMode;

public interface ILoginDao {

	// 查询登录用户信息
	public SysLoginUserBean queryLoginUserInfo(HashMap<String,String> map);
	

	// 查询登录的操作菜单
	public List<SysMenuMode> queryLoginUserMenu(HashMap<String,String> map);
	

}
