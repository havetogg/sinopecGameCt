package org.jumutang.project.weixinMng.gameOne.service.impl;


import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.gameOne.dao.IT1WeekRankReturnDao;
import org.jumutang.project.weixinMng.gameOne.model.T1WeekRankReturnMode;
import org.jumutang.project.weixinMng.gameOne.service.IT1WeekRankReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class T1WeekRankReturnServiceImpl implements IT1WeekRankReturnService{

	@Autowired
	private IT1WeekRankReturnDao t1WeekRankReturnDao;
	
	@Override
	public List<T1WeekRankReturnMode> findList(Map<String, String> queryParam, Page page) {
		// TODO Auto-generated method stub
		page.setRecordCount(t1WeekRankReturnDao.findCount(queryParam));
		return t1WeekRankReturnDao.findList(queryParam, page);
	}

	@Override
	public T1WeekRankReturnMode findInfo(Integer id) {
		// TODO Auto-generated method stub
		return t1WeekRankReturnDao.findInfo(id);
	}

	@Override
	@Transactional
	public int saveInfo(T1WeekRankReturnMode t1WeekRankReturnMode) {
		// TODO Auto-generated method stub
		return t1WeekRankReturnDao.saveInfo(t1WeekRankReturnMode);
	}

	@Override
	@Transactional
	public void updateInfo(T1WeekRankReturnMode t1WeekRankReturnMode) {
		// TODO Auto-generated method stub
		t1WeekRankReturnDao.updateInfo(t1WeekRankReturnMode);
	}

	@Override
	@Transactional
	public void deleteInfo(Integer id) {
		// TODO Auto-generated method stub
		t1WeekRankReturnDao.deleteInfo(id);
	}
	
	
}
