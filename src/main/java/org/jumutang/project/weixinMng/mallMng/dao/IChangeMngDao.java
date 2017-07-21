package org.jumutang.project.weixinMng.mallMng.dao;


import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.model.ChangeMngMode;

public interface IChangeMngDao {
	
	public List<ChangeMngMode> findList(Map<String,String> queryParam);
	
	public ChangeMngMode findInfo(Integer id);
	
	public int saveInfo(ChangeMngMode ChangeMngMode);
	
	public void updateInfo(ChangeMngMode ChangeMngMode);
	
}
