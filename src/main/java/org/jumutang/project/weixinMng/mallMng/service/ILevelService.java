package org.jumutang.project.weixinMng.mallMng.service;


import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.model.LevelMode;


public interface ILevelService {
	
	public List<LevelMode> findList(Map<String,String> queryParam);
	
	public LevelMode findInfo(Integer id);
	
	public int saveInfo(LevelMode levelMode);
	
	public void updateInfo(LevelMode levelMode);
	
	public void deleteInfo(Integer id);
}