package org.jumutang.project.backMng.discus.service;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.discus.model.DiscusMngMode;

public interface IDiscusMngService {
	
	public List<DiscusMngMode> findList(Map<String,String> queryParam,Page page);
	
	public DiscusMngMode findInfo(Integer id);
	
	public int saveInfo(DiscusMngMode discusMngMode);
	
	public void updateInfo(DiscusMngMode discusMngMode);
	
	public void deleteInfo(Integer id);
}