package org.jumutang.project.weixinMng.mallMng.dao;



import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.mallMng.model.RankMode;

public interface IRankDao {

	public List<RankMode> findList(Map<String,String> queryParam,Page page);
	
	public RankMode findInfo(Integer id);
	
	public int saveInfo(RankMode RankMode);
	
	public void updateInfo(RankMode RankMode);
	
	public void deleteInfo(Integer id);

}
