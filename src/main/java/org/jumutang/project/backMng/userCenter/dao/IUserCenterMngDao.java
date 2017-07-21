package org.jumutang.project.backMng.userCenter.dao;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.userCenter.model.UserCenterMngMode;

public interface IUserCenterMngDao {

	public int findCount(Map<String,String> queryParam);
	
	public List<UserCenterMngMode> findList(Map<String,String> queryParam,Page page);
	
	public UserCenterMngMode findInfo(Integer id);
	
	public void deleteInfo(Integer id);

}
