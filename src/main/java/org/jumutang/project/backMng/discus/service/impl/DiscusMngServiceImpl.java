package org.jumutang.project.backMng.discus.service.impl;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.discus.dao.IDiscusMngDao;
import org.jumutang.project.backMng.discus.model.DiscusMngMode;
import org.jumutang.project.backMng.discus.service.IDiscusMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscusMngServiceImpl implements IDiscusMngService{

	@Autowired
	private IDiscusMngDao discusMngDao;
	
	@Override
	public List<DiscusMngMode> findList(Map<String, String> queryParam, Page page) {
		// TODO Auto-generated method stub
		page.setRecordCount(discusMngDao.findCount(queryParam));
		return discusMngDao.findList(queryParam, page);
	}

	@Override
	public DiscusMngMode findInfo(Integer id) {
		// TODO Auto-generated method stub
		return discusMngDao.findInfo(id);
	}

	@Override
	@Transactional
	public int saveInfo(DiscusMngMode discusMngMode) {
		// TODO Auto-generated method stub
		return discusMngDao.saveInfo(discusMngMode);
	}

	@Override
	@Transactional
	public void updateInfo(DiscusMngMode discusMngMode) {
		// TODO Auto-generated method stub
		discusMngDao.updateInfo(discusMngMode);
	}

	@Override
	@Transactional
	public void deleteInfo(Integer id) {
		// TODO Auto-generated method stub
		discusMngDao.deleteInfo(id);
	}
	
	
}
