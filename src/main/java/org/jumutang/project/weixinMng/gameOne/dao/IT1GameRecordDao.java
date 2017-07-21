package org.jumutang.project.weixinMng.gameOne.dao;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.gameOne.model.T1GameRecordMode;


public interface IT1GameRecordDao {

	public int findCount(Map<String,String> queryParam);
	
	public List<T1GameRecordMode> findList(Map<String,String> queryParam,Page page);
	
	public T1GameRecordMode findInfo(Integer id);
	
	public int saveInfo(T1GameRecordMode T1GameRecordMode);
	
	public void updateInfo(T1GameRecordMode T1GameRecordMode);
	
	public void deleteInfo(Integer id);
	
	// 查询用户玩的次数
	public int finduserGamedCount(String user_game_id,String type);

}
