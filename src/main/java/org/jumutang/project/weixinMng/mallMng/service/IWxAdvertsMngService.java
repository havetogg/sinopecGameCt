package org.jumutang.project.weixinMng.mallMng.service;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.mallMng.model.WxAdvertsMngMode;

public interface IWxAdvertsMngService {
	
	public List<WxAdvertsMngMode> findList(Map<String,String> queryParam,Page page);
	
	public WxAdvertsMngMode findInfo(Integer id);
	
}