package org.jumutang.project.weixinMng.gameOne.dao;

import org.jumutang.project.weixinMng.gameOne.model.GameOneMode;
import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;

public interface IGameOneDao {
	
	// 更新DAY_TIMES, GAME_ALL_TIMES (每天更新)
	public void updateUserGameInfo_Times(User_gameMngMode user_gameMngMode);
	
	public void update_GameOneUsedTimes(User_gameMngMode user_gameMngMode);
	
}
