package org.jumutang.project.backMng.banner.service;

import java.util.List;
import java.util.Map;

import org.jumutang.project.backMng.banner.model.BannerMngMode;
import org.jumutang.project.base.Page;

public interface IBannerMngService {
	
	public List<BannerMngMode> findList(Map<String,String> queryParam,Page page);
	
	public BannerMngMode findInfo(Integer id);
	
	public int saveInfo(BannerMngMode bannerMode);
	
	public void updateInfo(BannerMngMode bannerMode);
	
	public void deleteInfo(Integer id);
	
	public BannerMngMode findEditInfo(Integer id);
}