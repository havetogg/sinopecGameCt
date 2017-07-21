package org.jumutang.project.weixinMng.mallMng.service;

import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.model.WxBannerMngMode;

public interface IWxBannerMngService {
	
	// 查询焦点图片
	public List<WxBannerMngMode> findList();
	

}