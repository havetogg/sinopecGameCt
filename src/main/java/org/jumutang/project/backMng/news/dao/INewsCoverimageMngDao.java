package org.jumutang.project.backMng.news.dao;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.news.model.NewsCoverimageMngMode;

public interface INewsCoverimageMngDao {

	public int findCount(Map<String,String> queryParam);
	
	public List<NewsCoverimageMngMode> findList(Map<String,String> queryParam,Page page);
	
	public NewsCoverimageMngMode findInfo(Integer id);
	
	public int saveInfo(NewsCoverimageMngMode NewsCoverimageMngMode);
	
	public void updateInfo(NewsCoverimageMngMode NewsCoverimageMngMode);
	
	public void deleteInfo(Integer id);

}
