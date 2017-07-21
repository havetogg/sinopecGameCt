package org.jumutang.project.backMng.adverts.service.impl;

import java.util.List;
import java.util.Map;

import org.jumutang.project.backMng.adverts.dao.IAdvertsMngDao;
import org.jumutang.project.backMng.adverts.model.AdvertsMngMode;
import org.jumutang.project.backMng.adverts.service.IAdvertsMngService;
import org.jumutang.project.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdvertsMngServiceImpl implements IAdvertsMngService{

	@Autowired
	private IAdvertsMngDao advertsDao;
	
	@Override
	public List<AdvertsMngMode> findList(Map<String, String> queryParam, Page page) {
		page.setRecordCount(advertsDao.findCount(queryParam));
		return advertsDao.findList(queryParam, page);
	}

	@Override
	public AdvertsMngMode findInfo(Integer id) {
		return advertsDao.findInfo(id);
	}
	
	
	
	
	@Override
	@Transactional
	public int saveInfo(AdvertsMngMode bannerMode) {
		return advertsDao.saveInfo(bannerMode);
	}

	@Override
	@Transactional
	public void updateInfo(AdvertsMngMode bannerMode) {
		advertsDao.updateInfo(bannerMode);
	}

	@Override
	@Transactional
	public void deleteInfo(Integer id) {
		advertsDao.deleteInfo(id);
	}

	@Override
	public AdvertsMngMode findEditInfo(Integer id) {
		return advertsDao.findEditInfo(id);
	}

	
	
}
