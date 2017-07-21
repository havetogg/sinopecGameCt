package org.jumutang.project.backMng.banner.service.impl;

import java.util.List;
import java.util.Map;

import org.jumutang.project.backMng.banner.dao.IBannerMngDao;
import org.jumutang.project.backMng.banner.model.BannerMngMode;
import org.jumutang.project.backMng.banner.service.IBannerMngService;
import org.jumutang.project.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BannerMngServiceImpl implements IBannerMngService{

	@Autowired
	private IBannerMngDao bannerDao;
	
	@Override
	public List<BannerMngMode> findList(Map<String, String> queryParam, Page page) {
		page.setRecordCount(bannerDao.findCount(queryParam));
		return bannerDao.findList(queryParam, page);
	}

	@Override
	public BannerMngMode findInfo(Integer id) {
		return bannerDao.findInfo(id);
	}
	
	
	
	
	@Override
	@Transactional
	public int saveInfo(BannerMngMode bannerMode) {
		return bannerDao.saveInfo(bannerMode);
	}

	@Override
	@Transactional
	public void updateInfo(BannerMngMode bannerMode) {
		bannerDao.updateInfo(bannerMode);
	}

	@Override
	@Transactional
	public void deleteInfo(Integer id) {
		bannerDao.deleteInfo(id);
	}

	@Override
	public BannerMngMode findEditInfo(Integer id) {
		return bannerDao.findEditInfo(id);
	}

	
	
}
