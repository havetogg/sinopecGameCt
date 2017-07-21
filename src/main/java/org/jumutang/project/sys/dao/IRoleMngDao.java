package org.jumutang.project.sys.dao;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.base.TreeModel;
import org.jumutang.project.sys.model.RoleMngMode;

public interface IRoleMngDao {

	public int findCount(Map<String,String> queryParam);
	
	public List<RoleMngMode> findList(Map<String,String> queryParam,Page page);
	
	public RoleMngMode findInfo(Integer id);
	
	public int saveInfo(RoleMngMode RoleMngMode);
	
	public void updateInfo(RoleMngMode RoleMngMode);
	
	public void deleteInfo(Integer id);

	public List<TreeModel> loadTree(Integer ROLE_ID,Integer PARENT_ID,String LOAD_TYPE);
}
