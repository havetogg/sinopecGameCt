package org.jumutang.project.backMng.adverts.service;

import java.util.List;
import java.util.Map;

import org.jumutang.project.backMng.adverts.model.AdvertsMngMode;
import org.jumutang.project.base.Page;

public interface IAdvertsMngService {
	
	public List<AdvertsMngMode> findList(Map<String,String> queryParam,Page page);
	
	public AdvertsMngMode findInfo(Integer id);
	
	public int saveInfo(AdvertsMngMode bannerMode);
	
	public void updateInfo(AdvertsMngMode bannerMode);
	
	public void deleteInfo(Integer id);
	
	public AdvertsMngMode findEditInfo(Integer id);
}