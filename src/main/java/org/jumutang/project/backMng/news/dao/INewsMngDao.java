package org.jumutang.project.backMng.news.dao;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.news.model.NewsMngMode;

public interface INewsMngDao {

	public int findCount(Map<String,String> queryParam);
	
	public List<NewsMngMode> findList(Map<String,String> queryParam,Page page);
	
	public NewsMngMode findInfo(Integer id);
	
	public int saveInfo(NewsMngMode NewsMngMode);
	
	public void updateInfo(NewsMngMode NewsMngMode);
	
	public void deleteInfo(Integer id);
	
	//---RICH_CONTENT
	public void saveRICH_CONTENTInfo(String ID, String CONTENT);
	
	public void updateRICH_CONTENTInfo(String ID, String CONTENT);

}
