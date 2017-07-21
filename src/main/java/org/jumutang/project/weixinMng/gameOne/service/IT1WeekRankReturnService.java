package org.jumutang.project.weixinMng.gameOne.service;


import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.gameOne.model.T1WeekRankReturnMode;


public interface IT1WeekRankReturnService {
	
	public List<T1WeekRankReturnMode> findList(Map<String,String> queryParam,Page page);
	
	public T1WeekRankReturnMode findInfo(Integer id);
	
	public int saveInfo(T1WeekRankReturnMode t1WeekRankReturnMode);
	
	public void updateInfo(T1WeekRankReturnMode t1WeekRankReturnMode);
	
	public void deleteInfo(Integer id);
}