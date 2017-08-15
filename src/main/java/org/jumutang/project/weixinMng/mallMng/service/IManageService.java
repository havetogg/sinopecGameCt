package org.jumutang.project.weixinMng.mallMng.service;

import com.alibaba.fastjson.JSONObject;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
/**
 * 主service
 * @author Administrator
 *
 */
public interface IManageService {
	
	/**
	 * 绑定员工号和手机号
	 * @return
	 */
	public int insertMallUserInfoVolume(MallUserMode bean);
	
	/**
	 * 查询用户的绑定信息
	 * @return
	 */
	public MallUserMode queryMallUserInfo(String openid);

	/**
	 * 通过手机查询用户的绑定信息
	 * @return
	 */
	public MallUserMode queryMallUserInfoByMobile(String phone);

	/**
	 * 通过手机查询用户的绑定信息
	 * @return
	 */
	public MallUserMode queryMallUserInfo_byJSUSERID(String jsUserId);
	
	/**
	 * 更新用户login时的信息
	 * @return
	 */
	public void update_UserInfo_login(MallUserMode bean);
	
	/**
	 * 添加收藏
	 * @return
	 */
	public void collect_add(MallUserMode bean,String gameId);
	
	/**
	 * 删除收藏
	 * @return
	 */
	public void collect_del(MallUserMode bean,String gameId);
	
	/**
	 * 用户领取奖励
	 * @return
	 */
	public void get_reward(MallUserMode bean,String sysMsgId);

	/**
	 * 更新当前用户钻石总数 [游戏返回钻石]
	 * myr
	 * */
	public int  UpdateDiamond(MallUserMode mallUserMode);
	
	

}
