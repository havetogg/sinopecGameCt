package org.jumutang.project.sys.service.impl;


import java.util.HashMap;
import java.util.List;

import org.jumutang.project.sys.dao.ILoginDao;
import org.jumutang.project.sys.model.SysLoginUserBean;
import org.jumutang.project.sys.model.SysMenuMode;
import org.jumutang.project.sys.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*
 * 部门和人员的操作
 */
@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private ILoginDao loginDao;
	

	@Override
	public SysLoginUserBean queryLoginUserInfo(HashMap<String, String> map) {
		return loginDao.queryLoginUserInfo(map);
	}

	@Override
	public List<SysMenuMode> queryLoginUserMenu(HashMap<String, String> map) {
		return loginDao.queryLoginUserMenu(map);
	}

}
