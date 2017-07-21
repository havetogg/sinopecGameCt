package org.jumutang.project.weixinMng.mallMng.service.impl;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.mallMng.dao.ILevelDao;
import org.jumutang.project.weixinMng.mallMng.model.LevelMode;
import org.jumutang.project.weixinMng.mallMng.service.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LevelServiceImpl implements ILevelService{

	@Autowired
	private ILevelDao levelDao;
	
	@Override
	public List<LevelMode> findList(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		return levelDao.findList(queryParam);
	}

	@Override
	public LevelMode findInfo(Integer id) {
		// TODO Auto-generated method stub
		return levelDao.findInfo(id);
	}

	@Override
	@Transactional
	public int saveInfo(LevelMode levelMode) {
		// TODO Auto-generated method stub
		return levelDao.saveInfo(levelMode);
	}

	@Override
	@Transactional
	public void updateInfo(LevelMode levelMode) {
		// TODO Auto-generated method stub
		levelDao.updateInfo(levelMode);
	}

	@Override
	@Transactional
	public void deleteInfo(Integer id) {
		// TODO Auto-generated method stub
		levelDao.deleteInfo(id);
	}
	
	
}
