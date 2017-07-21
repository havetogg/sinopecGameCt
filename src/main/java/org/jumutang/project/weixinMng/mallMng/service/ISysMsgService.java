package org.jumutang.project.weixinMng.mallMng.service;

import java.util.List;

import org.jumutang.project.weixinMng.mallMng.model.SysMsgMode;

/**
 * 注册码service
 * @author Administrator
 *
 */
public interface ISysMsgService {
	
	public int findNotReadCount(String user_id);
	
	public List<SysMsgMode> findList(String user_id);
	
	public SysMsgMode findInfo(Integer id);
	
	public int saveInfo(SysMsgMode sysMsgMode);
	
	// 更新领取时间
	public void updateInfo(SysMsgMode sysMsgMode);
	
	// 更新读取
	public void updateInfo_readTime( String user_id);
	
	//VIP会员每周福利到账通知。
	public void updateVipWeak_gold();
	
	//VIP会员每月等级下降
	public void updateVipMonthRankDown();

}
