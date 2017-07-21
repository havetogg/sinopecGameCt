package org.jumutang.project.weixinMng.mallMng.dao;

import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;

public interface IUser_gameMngDao {

	public List<User_gameMngMode> findList(Map<String,String> queryParam);
	
	// 查询前10名(用于定时任务,周一0点)
	public List<User_gameMngMode> findListWeekLimit10(String GAME_ID);
	
	public User_gameMngMode findInfo(Integer id);
	
	public int saveInfo(User_gameMngMode User_gameMngMode);
	
	public void updateInfo(User_gameMngMode User_gameMngMode);
	
	public List<User_gameMngMode> findUserGameDList(Map<String, String> queryParam);

	public User_gameMngMode findInfobyUseridAndGameid(String userid,String gameid);
	
	public void updateInfo_GAME_ALL_TIMES(User_gameMngMode user_gameMngMode);
	
	// 更新上一周的排名分数和时间(WEEK_SCORE desc, WEEK_TIME)
	public void updateInfo_WEEK_SCORE_TIME(User_gameMngMode user_gameMngMode);
	
	// 更新游戏分数和时间
	public void updateInfo_ALL_SCOREANDTIME(User_gameMngMode user_gameMngMode);

	
}
