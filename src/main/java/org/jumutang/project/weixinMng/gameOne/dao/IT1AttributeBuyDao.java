package org.jumutang.project.weixinMng.gameOne.dao;


import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.gameOne.model.T1AttributeBuyMode;


public interface IT1AttributeBuyDao {
	
	// 查询用户购买游戏的次数
	public int findCount_Today_game_buy(String user_game_id);
	
	// 今天游戏购买的list
	public List<T1AttributeBuyMode> findList_gameBuytimes(String user_game_id);
	
	// 加油机购买次数
	public int findCount_Oil_machine_buy(String user_game_id);
	
	// 车位购买次数
	public int findCount_Park_buy(String user_game_id);

	public int findCount(Map<String,String> queryParam);
	
	public List<T1AttributeBuyMode> findList(Map<String,String> queryParam,Page page);
	
	public List<T1AttributeBuyMode> findList_notgametimes(String user_game_id);
	
	public T1AttributeBuyMode findInfo(Integer id);
	
	public int saveInfo(T1AttributeBuyMode T1AttributeBuyMode);
	
	public void updateInfo(T1AttributeBuyMode T1AttributeBuyMode);
	
	public void deleteInfo(Integer id);

}
