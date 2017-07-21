package org.jumutang.project.weixinMng.mallMng.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.dao.IGameMngDao;
import org.jumutang.project.weixinMng.mallMng.model.GameMngMode;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;
import org.jumutang.project.weixinMng.mallMng.service.IGameMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameMngServiceImpl implements IGameMngService{

	@Autowired
	private IGameMngDao gameMngDao;
	
	@Override
	public List<GameMngMode> findList(Map<String, String> queryParam) {
		return gameMngDao.findList(queryParam);
	}

	@Override
	public GameMngMode findInfo(Integer id) {
		return gameMngDao.findInfo(id);
	}

	// 查询游戏及用户是否收藏
	@Override
	public List<GameMngMode> findList_collection(Map<String, String> queryParam) {
		return gameMngDao.findList_collection(queryParam);
	}

	@Override
	public List<GameMngMode> find_MycollectionList(Map<String, String> queryParam) {
		return gameMngDao.find_MycollectionList(queryParam);
	}

	@Override
	public List<User_gameMngMode> find_rand_byGameId(Map<String, String> queryParam) {
		return gameMngDao.find_rand_byGameId(queryParam);
	}

	@Override
	public List<GameMngMode> findList_UserIN(Map<String, String> queryParam,MallUserMode userbean) {
		 List<GameMngMode> gameListreturn = new ArrayList<>();
		 List<GameMngMode> gameList = gameMngDao.findList(queryParam);
		 for (GameMngMode gameMngMode : gameList) {
			 String game_id = gameMngMode.getID();
			 if("2".equals(game_id)){
			 	continue;
			 }
			 queryParam.put("GAME_ID", game_id);
			 List<User_gameMngMode> find_rand_byGameId_list = gameMngDao.find_rand_byGameId(queryParam);
			 for (User_gameMngMode user_gameMngMode : find_rand_byGameId_list) {
				 String user_ID = user_gameMngMode.getUSER_ID();
				 if(user_ID.equals(userbean.getID())){
					 gameMngMode.setUSER_RANK_FLAG("1");
					 break;
				 }
			}
			 gameListreturn.add(gameMngMode);
		}
		 return gameListreturn;
	}

	@Override
	public int findList_UserIN_Size(Map<String, String> queryParam, MallUserMode userbean) {
		 int returnnumb = 0;
		 List<GameMngMode> gameList = gameMngDao.findList(queryParam);
		 for (GameMngMode gameMngMode : gameList) {
			 String game_id = gameMngMode.getID();
			 queryParam.put("GAME_ID", game_id);
			 List<User_gameMngMode> find_rand_byGameId_list = gameMngDao.find_rand_byGameId(queryParam);
			 for (User_gameMngMode user_gameMngMode : find_rand_byGameId_list) {
				 String user_ID = user_gameMngMode.getUSER_ID();
				 if(user_ID.equals(userbean.getID())){
					 returnnumb=returnnumb+1;
					 break;
				 }
			}
		}
		return returnnumb;
	}

}
