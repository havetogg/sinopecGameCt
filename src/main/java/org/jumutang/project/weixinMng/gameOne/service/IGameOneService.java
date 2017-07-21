package org.jumutang.project.weixinMng.gameOne.service;

import org.jumutang.project.weixinMng.gameOne.model.GameOneMode;
import org.jumutang.project.weixinMng.gameOne.model.T1GameRecordMode;
import org.jumutang.project.weixinMng.gameOne.model.T1UserGametypeMode;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;

/**
 * gameOneservice
 * @author Administrator
 *
 */
public interface IGameOneService {
	
	/**
	 * 登录游戏1到 UerGame表
	 * @return
	 */
	public void saveUerGame_GameOne(MallUserMode bean,String gameId);
	
	/**
	 * 查询游戏1的相关信息
	 * @return
	 */
	public GameOneMode findUer_GameOneInfo(MallUserMode bean,String gameId);
	
	
	/**
	 * 游戏1的每天定时任务
	 * @return
	 */
	public void update_GameOneDays(String gameId);
	
	/**
	 * 游戏1,更新游戏过的次数(点击开始游戏)
	 * @return
	 */
	public void update_GameOneUsedTimes(T1UserGametypeMode T1UserGametypeMode);
	
	/**
	 * 游戏1,购买游戏次数
	 * @return
	 */
	public void update_AllGameTimes(String BUYUSERMODETYPE,MallUserMode userbean,GameOneMode gameonebean,int needDom);
	
	/**
	 * 每周一统计上周排名,并发放福利
	 */
	public void update_WeekRank(String game_id);
	
	/**
	 * 游戏1,购买加油机
	 * @return
	 */
	public void save_OilMachineBuyTimes(MallUserMode userbean,GameOneMode gameonebean,int needGold);
	
	/**
	 * 游戏1,购买车位
	 * @return
	 */
	public void save_ParkBuyTimes(MallUserMode userbean,GameOneMode gameonebean,int needGold);
	
	/**
	 * 游戏1,保存游戏记录    并更新用户的金币
	 * @return
	 */
	public void save_GameOneRecord(String TYPE, MallUserMode userbean,GameOneMode gameonebean,T1GameRecordMode t1gamerecordmode);
	
	
	/**
	 * 保存我知道
	 * @return
	 */
	public void saveknow(MallUserMode userbean,GameOneMode gameonebean,String type);

	// 查询用户玩的次数
	public int finduserGamedCount(String user_game_id,String type);
}
