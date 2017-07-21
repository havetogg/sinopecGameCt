package org.jumutang.project.weixinMng.gameOne.service;


import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.gameOne.model.T1UserGametypeMode;


public interface IT1UserGametypeService {
	
	public List<T1UserGametypeMode> findList(Map<String,String> queryParam,Page page);
	
	public T1UserGametypeMode findInfo(Integer id);
	
	public int saveInfo(T1UserGametypeMode t1UserGametypeMode);
	
	public void updateInfo(T1UserGametypeMode t1UserGametypeMode);
	
	public void deleteInfo(Integer id);
}