package org.jumutang.project.weixinMng.mallMng.service;

import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.model.TchangeOrderMode;


public interface ITchangeOrderService {
	
	public List<TchangeOrderMode> findList(Map<String,String> queryParam);
	
	public TchangeOrderMode findInfo(Integer id);
	
	public int saveInfo(TchangeOrderMode tchangeOrderMode);
	
	public void updateInfo(TchangeOrderMode tchangeOrderMode);
	
	public void deleteInfo(Integer id);
	
	public TchangeOrderMode findInfo(String order_no);
	
	public void changeReturn_Diamond(String order_no);
	
}