package org.jumutang.project.backMng.userCenter.service.impl;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.userCenter.dao.IUserCenterMngDao;
import org.jumutang.project.backMng.userCenter.model.UserCenterMngMode;
import org.jumutang.project.backMng.userCenter.service.IUserCenterMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCenterMngServiceImpl implements IUserCenterMngService{

	@Autowired
	private IUserCenterMngDao userCenterMngDao;
	
	@Override
	public List<UserCenterMngMode> findList(Map<String, String> queryParam, Page page) {
		page.setRecordCount(userCenterMngDao.findCount(queryParam));
		return userCenterMngDao.findList(queryParam, page);
	}

	@Override
	public UserCenterMngMode findInfo(Integer id) {
		// TODO Auto-generated method stub
		return userCenterMngDao.findInfo(id);
	}

	@Override
	@Transactional
	public void deleteInfo(Integer id) {
		// TODO Auto-generated method stub
		userCenterMngDao.deleteInfo(id);
	}
	
	
}
