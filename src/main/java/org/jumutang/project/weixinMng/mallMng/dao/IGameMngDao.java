package org.jumutang.project.weixinMng.mallMng.dao;

import java.util.List;
import java.util.Map;
import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.mallMng.model.GameMngMode;
import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;

public interface IGameMngDao {

	public List<GameMngMode> findList(Map<String,String> queryParam);
	
	public GameMngMode findInfo(Integer id);
	
	// 查询游戏及用户是否收藏
	public List<GameMngMode> findList_collection(Map<String,String> queryParam);
	
	// 查询我收藏的游戏
	public List<GameMngMode> find_MycollectionList(Map<String, String> queryParam);
	
	// 查询单个游戏的上周排行 
	public List<User_gameMngMode> find_rand_byGameId(Map<String, String> queryParam);

}
