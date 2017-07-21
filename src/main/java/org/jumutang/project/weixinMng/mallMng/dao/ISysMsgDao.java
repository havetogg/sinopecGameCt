package org.jumutang.project.weixinMng.mallMng.dao;


import java.util.List;

import org.jumutang.project.weixinMng.mallMng.model.SysMsgMode;

public interface ISysMsgDao {
	
	public int findNotReadCount(String user_id);
	
	public List<SysMsgMode> findList(String user_id);
	
	public SysMsgMode findInfo(Integer id);
	
	public SysMsgMode findUnGetInfo(Integer id);
	
	public int saveInfo(SysMsgMode SysMsgMode);
	
	public void updateInfo(SysMsgMode SysMsgMode);
	
	public void updateInfo_readTime(String user_id);
	
}
