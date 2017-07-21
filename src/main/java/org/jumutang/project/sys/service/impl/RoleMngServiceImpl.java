package org.jumutang.project.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.base.TreeModel;
import org.jumutang.project.sys.dao.IRoleMngDao;
import org.jumutang.project.sys.model.RoleMngMode;
import org.jumutang.project.sys.service.IRoleMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleMngServiceImpl implements IRoleMngService{

	@Autowired
	private IRoleMngDao roleMngDao;
	
	@Override
	public List<RoleMngMode> findList(Map<String, String> queryParam, Page page) {
		// TODO Auto-generated method stub
		page.setRecordCount(roleMngDao.findCount(queryParam));
		return roleMngDao.findList(queryParam, page);
	}

	@Override
	public RoleMngMode findInfo(Integer id) {
		// TODO Auto-generated method stub
		return roleMngDao.findInfo(id);
	}

	@Override
	@Transactional
	public int saveInfo(RoleMngMode roleMngMode) {
		// TODO Auto-generated method stub
		return roleMngDao.saveInfo(roleMngMode);
	}

	@Override
	@Transactional
	public void updateInfo(RoleMngMode roleMngMode) {
		// TODO Auto-generated method stub
		roleMngDao.updateInfo(roleMngMode);
	}

	@Override
	@Transactional
	public void deleteInfo(Integer id) {
		// TODO Auto-generated method stub
		roleMngDao.deleteInfo(id);
	}

	@Override
	public List<TreeModel> loadTree(Integer ROLE_ID, Integer PARENT_ID, String LOAD_TYPE) {
		// TODO Auto-generated method stub
		return roleMngDao.loadTree(ROLE_ID, PARENT_ID, LOAD_TYPE);
	}
	
	
}
