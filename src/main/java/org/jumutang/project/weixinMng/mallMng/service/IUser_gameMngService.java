package org.jumutang.project.weixinMng.mallMng.service;


import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;


public interface IUser_gameMngService {
	
	public List<User_gameMngMode> findUserGameDList(Map<String,String> queryParam);
	
	public List<User_gameMngMode> findList(Map<String,String> queryParam);
	
	public User_gameMngMode findInfo(Integer id);
	
	public int saveInfo(User_gameMngMode user_gameMngMode);
	
	public void updateInfo(User_gameMngMode user_gameMngMode);
	
}