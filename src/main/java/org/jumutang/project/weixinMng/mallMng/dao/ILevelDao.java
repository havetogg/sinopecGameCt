package org.jumutang.project.weixinMng.mallMng.dao;


import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.mallMng.model.LevelMode;


public interface ILevelDao {

	public int findCount(Map<String,String> queryParam);
	
	public List<LevelMode> findList(Map<String,String> queryParam);
	
	public LevelMode findInfo(Integer id);
	
	public int saveInfo(LevelMode LevelMode);
	
	public void updateInfo(LevelMode LevelMode);
	
	public void deleteInfo(Integer id);

}
