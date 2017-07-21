package org.jumutang.project.weixinMng.mallMng.service;

import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.model.GameMngMode;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;

public interface IGameMngService {
	
	public List<GameMngMode> findList(Map<String,String> queryParam);
	
	public GameMngMode findInfo(Integer id);
	
	// 查询游戏及用户是否收藏
	public List<GameMngMode> findList_collection(Map<String, String> queryParam);
	
	// 查询我收藏的游戏
	public List<GameMngMode> find_MycollectionList(Map<String, String> queryParam);
	
	// 查询单个游戏的排行
	public List<User_gameMngMode> find_rand_byGameId(Map<String, String> queryParam);
	
	// 查询游戏,并判断用户是不是在榜单里
	public List<GameMngMode> findList_UserIN(Map<String,String> queryParam,MallUserMode userbean);
	
	// 查询用户在游戏中的上榜个数
	public int findList_UserIN_Size(Map<String,String> queryParam,MallUserMode userbean);

}