package org.jumutang.project.weixinMng.mallMng.service.impl;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.mallMng.dao.IWxAdvertsMngDao;
import org.jumutang.project.weixinMng.mallMng.model.WxAdvertsMngMode;
import org.jumutang.project.weixinMng.mallMng.service.IWxAdvertsMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxAdvertsMngServiceImpl implements IWxAdvertsMngService{

	@Autowired
	private IWxAdvertsMngDao wxadvertsDao;
	
	@Override
	public List<WxAdvertsMngMode> findList(Map<String, String> queryParam, Page page) {
		if(null == page){
			return wxadvertsDao.findList(queryParam, null);
		}else{
			page.setRecordCount(wxadvertsDao.findCount(queryParam));
			return wxadvertsDao.findList(queryParam, page);
		}
	}
		
	@Override
	public WxAdvertsMngMode findInfo(Integer id) {
		return wxadvertsDao.findInfo(id);
	}
	
}
