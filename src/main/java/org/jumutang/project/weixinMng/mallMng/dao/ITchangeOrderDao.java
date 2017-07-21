package org.jumutang.project.weixinMng.mallMng.dao;


import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.model.TchangeOrderMode;


public interface ITchangeOrderDao {

	public List<TchangeOrderMode> findList(Map<String,String> queryParam);
	
	public TchangeOrderMode findInfo(Integer id);
	
	public int saveInfo(TchangeOrderMode TchangeOrderMode);
	
	public void updateInfo(TchangeOrderMode TchangeOrderMode);
	
	public void deleteInfo(Integer id);
	
	public TchangeOrderMode findInfo(String order_no);

}
