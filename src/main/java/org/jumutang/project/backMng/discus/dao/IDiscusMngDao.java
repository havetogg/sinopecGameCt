package org.jumutang.project.backMng.discus.dao;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.discus.model.DiscusMngMode;

public interface IDiscusMngDao {

	public int findCount(Map<String,String> queryParam);
	
	public List<DiscusMngMode> findList(Map<String,String> queryParam,Page page);
	
	public DiscusMngMode findInfo(Integer id);
	
	public int saveInfo(DiscusMngMode DiscusMngMode);
	
	public void updateInfo(DiscusMngMode DiscusMngMode);
	
	public void deleteInfo(Integer id);

}
