package org.jumutang.project.weixinMng.gameOne.dao;


import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.gameOne.model.GameOneMode;
import org.jumutang.project.weixinMng.gameOne.model.T1UserGametypeMode;


public interface IT1UserGametypeDao {

	public int findCount(Map<String,String> queryParam);
	
	public List<T1UserGametypeMode> findList(Map<String,String> queryParam,Page page);
	
	public List<T1UserGametypeMode> findList(String user_game_id);
	
	public List<T1UserGametypeMode> findListALL();
	
	public T1UserGametypeMode findInfo(Integer id);
	
	public int saveInfo(T1UserGametypeMode T1UserGametypeMode);
	
	public void updateInfo(T1UserGametypeMode T1UserGametypeMode);
	
	public void updateInfo_GAME_ALL_TIMES(T1UserGametypeMode T1UserGametypeMode);
	
	public void deleteInfo(Integer id);
	
	public void updateUserGameInfo_Times(String type);
	
	public void update_GameOneUsedTimes(T1UserGametypeMode T1UserGametypeMode);

}
