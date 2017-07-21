package org.jumutang.project.backMng.news.service.impl;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.news.dao.INewsCoverimageMngDao;
import org.jumutang.project.backMng.news.model.NewsCoverimageMngMode;
import org.jumutang.project.backMng.news.service.INewsCoverimageMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsCoverimageMngServiceImpl implements INewsCoverimageMngService{

	@Autowired
	private INewsCoverimageMngDao newsCoverimageMngDao;
	
	@Override
	public List<NewsCoverimageMngMode> findList(Map<String, String> queryParam, Page page) {
		// TODO Auto-generated method stub
		page.setRecordCount(newsCoverimageMngDao.findCount(queryParam));
		return newsCoverimageMngDao.findList(queryParam, page);
	}

	@Override
	public NewsCoverimageMngMode findInfo(Integer id) {
		// TODO Auto-generated method stub
		return newsCoverimageMngDao.findInfo(id);
	}

	@Override
	@Transactional
	public int saveInfo(NewsCoverimageMngMode newsCoverimageMngMode) {
		// TODO Auto-generated method stub
		return newsCoverimageMngDao.saveInfo(newsCoverimageMngMode);
	}

	@Override
	@Transactional
	public void updateInfo(NewsCoverimageMngMode newsCoverimageMngMode) {
		// TODO Auto-generated method stub
		newsCoverimageMngDao.updateInfo(newsCoverimageMngMode);
	}

	@Override
	@Transactional
	public void deleteInfo(Integer id) {
		// TODO Auto-generated method stub
		newsCoverimageMngDao.deleteInfo(id);
	}
	
	
}
