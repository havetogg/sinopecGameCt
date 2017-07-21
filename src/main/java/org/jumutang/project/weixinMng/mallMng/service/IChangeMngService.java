package org.jumutang.project.weixinMng.mallMng.service;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.mallMng.model.ChangeMngMode;
import org.jumutang.project.weixinMng.mallMng.model.Change_goldMngMode;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;

/**
 * 注册码service
 * @author Administrator
 *
 */
public interface IChangeMngService {
	
	public List<ChangeMngMode> findList(Map<String,String> queryParam);
	
	public ChangeMngMode findInfo(Integer id);
	
	public int saveInfo(ChangeMngMode changeMngMode);
	
	public void updateInfo(ChangeMngMode changeMngMode);
	
	// 金币
	public List<Change_goldMngMode> findList_gold(Map<String,String> queryParam);
	
	// 查询要兑换的金币的信息
	public Change_goldMngMode findInfo_gold(Integer id);
	
	// 钻石换成金币
	public void update_UserInfo_ToGold(MallUserMode bean,String gold);
	
}
