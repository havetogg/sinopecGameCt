package org.jumutang.project.weixinMng.mallMng.dao;


import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;

public interface IManageDao {
	// 插入用户表数据
	public int insertMallUserInfoVolume(MallUserMode bean);
	
	// 查询用户的信息(根据openid)
	public MallUserMode queryMallUserInfo(String openid);

	// 查询用户的信息(根据phone)
	public MallUserMode queryMallUserInfoByMobile(String Phone);
	
	// 查询用户的信息(根据ID)
	public MallUserMode queryMallUserInfo_byId(String ID);

	// 查询用户的信息(根据JSUSERID)
	public MallUserMode queryMallUserInfo_byJSUSERID(String jsUserId);
	
	// 更新用户login时的信息
	public void update_UserInfo_login(MallUserMode bean);
	
	// 钻石换成金币
	public void update_UserInfo_ToGold(MallUserMode bean);
	
	// 更新使用过的钻石
	public void update_UserInfo_Used_diamond(MallUserMode bean);
	
	// 更新使用过的金币
	public void update_UserInfo_Used_gold(MallUserMode bean);
	
	// 添加收藏
	public void collect_add(MallUserMode bean, String gameId);
	
	
	// 删除收藏
	public void collect_del(MallUserMode bean, String gameId);
	
	// 更新用户总的钻石和总的金币
	public void update_User_DiamondANDGold(MallUserMode bean);
	
	// 查询用户的信息
	public List<MallUserMode> findUserList(Map<String, String> amap);
	
	// 用户的等级下降信息
	public void updateVipMonthRankDown(MallUserMode bean);
	
	// 充值更新钻石数
	public void update_UserInfo_ToGold(String userid, String diamond_numb);
	
	// 钻石充值后,更新用户的等级和最高等级
	public void update_UserInfo_Lv(String userid,String user_RANK_ID, String user_MAX_RANK_ID);
	
	// 更新经验值
	public void updateInfo_USER_LEVEL_SCORE(MallUserMode bean);

	/**
	 * 更新当前用户钻石总数 [游戏返回钻石]
	 * myr
	 * */
	public int  UpdateDiamond(MallUserMode mallUserMode);

}
