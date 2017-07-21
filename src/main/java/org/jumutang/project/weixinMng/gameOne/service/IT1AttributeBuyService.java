package org.jumutang.project.weixinMng.gameOne.service;


import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.gameOne.model.T1AttributeBuyMode;


public interface IT1AttributeBuyService {
	
	public List<T1AttributeBuyMode> findList(Map<String,String> queryParam,Page page);
	
	public T1AttributeBuyMode findInfo(Integer id);
	
	public int saveInfo(T1AttributeBuyMode t1AttributeBuyMode);
	
	public void updateInfo(T1AttributeBuyMode t1AttributeBuyMode);
	
	public void deleteInfo(Integer id);
}