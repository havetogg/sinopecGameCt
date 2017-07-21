package org.jumutang.project.weixinMng.mallMng.dao;

import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.model.Change_goldMngMode;



public interface IChange_goldMngDao {

	public List<Change_goldMngMode> findList(Map<String,String> queryParam);
	
	public Change_goldMngMode findInfo(Integer id);

}
