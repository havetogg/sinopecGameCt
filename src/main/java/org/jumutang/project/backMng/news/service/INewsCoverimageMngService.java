package org.jumutang.project.backMng.news.service;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.news.model.NewsCoverimageMngMode;

public interface INewsCoverimageMngService {
	
	public List<NewsCoverimageMngMode> findList(Map<String,String> queryParam,Page page);
	
	public NewsCoverimageMngMode findInfo(Integer id);
	
	public int saveInfo(NewsCoverimageMngMode newsCoverimageMngMode);
	
	public void updateInfo(NewsCoverimageMngMode newsCoverimageMngMode);
	
	public void deleteInfo(Integer id);
}