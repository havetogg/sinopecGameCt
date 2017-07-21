package org.jumutang.project.weixinMng.mallMng.service.impl;

import java.util.List;

import org.jumutang.project.weixinMng.mallMng.dao.IWxBannerMngDao;
import org.jumutang.project.weixinMng.mallMng.model.WxBannerMngMode;
import org.jumutang.project.weixinMng.mallMng.service.IWxBannerMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxBannerMngServiceImpl implements IWxBannerMngService{

	@Autowired
	private IWxBannerMngDao wxbannerDao;
	
	@Override
	public List<WxBannerMngMode> findList() {
		return wxbannerDao.findList();
	}

}
