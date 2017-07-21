package org.jumutang.project.backMng.adverts.dao;

import java.util.List;
import java.util.Map;

import org.jumutang.project.backMng.adverts.model.AdvertsMngMode;
import org.jumutang.project.base.Page;

public interface IAdvertsMngDao {

	public int findCount(Map<String,String> queryParam);
	
	public List<AdvertsMngMode> findList(Map<String,String> queryParam,Page page);
	
	public AdvertsMngMode findInfo(Integer id);
	
	public int saveInfo(AdvertsMngMode advertsMngMode);
	
	public void updateInfo(AdvertsMngMode advertsMngMode);
	
	public void deleteInfo(Integer id);
	
	public AdvertsMngMode findEditInfo(Integer id);
}
