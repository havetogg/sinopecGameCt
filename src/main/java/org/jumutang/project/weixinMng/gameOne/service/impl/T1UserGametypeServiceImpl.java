package org.jumutang.project.weixinMng.gameOne.service.impl;


import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.gameOne.dao.IT1UserGametypeDao;
import org.jumutang.project.weixinMng.gameOne.model.T1UserGametypeMode;
import org.jumutang.project.weixinMng.gameOne.service.IT1UserGametypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class T1UserGametypeServiceImpl implements IT1UserGametypeService{

	@Autowired
	private IT1UserGametypeDao t1UserGametypeDao;
	
	@Override
	public List<T1UserGametypeMode> findList(Map<String, String> queryParam, Page page) {
		// TODO Auto-generated method stub
		page.setRecordCount(t1UserGametypeDao.findCount(queryParam));
		return t1UserGametypeDao.findList(queryParam, page);
	}

	@Override
	public T1UserGametypeMode findInfo(Integer id) {
		// TODO Auto-generated method stub
		return t1UserGametypeDao.findInfo(id);
	}

	@Override
	@Transactional
	public int saveInfo(T1UserGametypeMode t1UserGametypeMode) {
		// TODO Auto-generated method stub
		return t1UserGametypeDao.saveInfo(t1UserGametypeMode);
	}

	@Override
	@Transactional
	public void updateInfo(T1UserGametypeMode t1UserGametypeMode) {
		// TODO Auto-generated method stub
		t1UserGametypeDao.updateInfo(t1UserGametypeMode);
	}

	@Override
	@Transactional
	public void deleteInfo(Integer id) {
		// TODO Auto-generated method stub
		t1UserGametypeDao.deleteInfo(id);
	}
	
	
}
