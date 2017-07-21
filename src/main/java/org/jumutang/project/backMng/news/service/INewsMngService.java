package org.jumutang.project.backMng.news.service;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.news.model.NewsMngMode;

public interface INewsMngService {
	
	public List<NewsMngMode> findList(Map<String,String> queryParam,Page page);
	
	public NewsMngMode findInfo(Integer id);
	
	public void saveInfo(NewsMngMode newsMngMode);
	
	public void updateInfo(NewsMngMode newsMngMode);
	
	public void deleteInfo(Integer id);
}