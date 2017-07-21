package org.jumutang.project.weixinMng.gameOne.service.impl;


import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.gameOne.dao.IT1AttributeBuyDao;
import org.jumutang.project.weixinMng.gameOne.model.T1AttributeBuyMode;
import org.jumutang.project.weixinMng.gameOne.service.IT1AttributeBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class T1AttributeBuyServiceImpl implements IT1AttributeBuyService{

	@Autowired
	private IT1AttributeBuyDao t1AttributeBuyDao;
	
	@Override
	public List<T1AttributeBuyMode> findList(Map<String, String> queryParam, Page page) {
		// TODO Auto-generated method stub
		page.setRecordCount(t1AttributeBuyDao.findCount(queryParam));
		return t1AttributeBuyDao.findList(queryParam, page);
	}

	@Override
	public T1AttributeBuyMode findInfo(Integer id) {
		// TODO Auto-generated method stub
		return t1AttributeBuyDao.findInfo(id);
	}

	@Override
	@Transactional
	public int saveInfo(T1AttributeBuyMode t1AttributeBuyMode) {
		// TODO Auto-generated method stub
		return t1AttributeBuyDao.saveInfo(t1AttributeBuyMode);
	}

	@Override
	@Transactional
	public void updateInfo(T1AttributeBuyMode t1AttributeBuyMode) {
		// TODO Auto-generated method stub
		t1AttributeBuyDao.updateInfo(t1AttributeBuyMode);
	}

	@Override
	@Transactional
	public void deleteInfo(Integer id) {
		// TODO Auto-generated method stub
		t1AttributeBuyDao.deleteInfo(id);
	}
	
	
}
