package org.jumutang.project.weixinMng.mallMng.service.impl;


import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.dao.IUser_gameMngDao;
import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;
import org.jumutang.project.weixinMng.mallMng.service.IUser_gameMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User_gameMngServiceImpl implements IUser_gameMngService{

	@Autowired
	private IUser_gameMngDao user_gameMngDao;
	
	@Override
	public List<User_gameMngMode> findList(Map<String, String> queryParam) {
		return user_gameMngDao.findList(queryParam);
	}

	@Override
	public User_gameMngMode findInfo(Integer id) {
		return user_gameMngDao.findInfo(id);
	}

	@Override
	@Transactional
	public int saveInfo(User_gameMngMode user_gameMngMode) {
		return user_gameMngDao.saveInfo(user_gameMngMode);
	}

	@Override
	@Transactional
	public void updateInfo(User_gameMngMode user_gameMngMode) {
		user_gameMngDao.updateInfo(user_gameMngMode);
	}

	@Override
	public List<User_gameMngMode> findUserGameDList(Map<String, String> queryParam) {
		return user_gameMngDao.findUserGameDList(queryParam);
	}
	
	
}
