package org.jumutang.project.backMng.news.service.impl;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.tools.UuidGenerator;
import org.jumutang.project.backMng.news.dao.INewsMngDao;
import org.jumutang.project.backMng.news.model.NewsMngMode;
import org.jumutang.project.backMng.news.service.INewsMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsMngServiceImpl implements INewsMngService{

	@Autowired
	private INewsMngDao newsMngDao;
	
	@Override
	public List<NewsMngMode> findList(Map<String, String> queryParam, Page page) {
		// TODO Auto-generated method stub
		page.setRecordCount(newsMngDao.findCount(queryParam));
		return newsMngDao.findList(queryParam, page);
	}

	@Override
	public NewsMngMode findInfo(Integer id) {
		// TODO Auto-generated method stub
		return newsMngDao.findInfo(id);
	}

	@Override
	@Transactional
	public void saveInfo(NewsMngMode newsMngMode) {
		// TODO Auto-generated method stub

		String generateUUID = UuidGenerator.generateUUID();
		String content = newsMngMode.getCONTENT();
		
		newsMngMode.setRICH_CONTENT_ID(generateUUID);
		newsMngDao.saveInfo(newsMngMode);         // 保存主表
		newsMngDao.saveRICH_CONTENTInfo(generateUUID,content);  //保存子表
	}

	@Override
	@Transactional
	public void updateInfo(NewsMngMode newsMngMode) {
		// TODO Auto-generated method stub
		newsMngDao.updateInfo(newsMngMode);
		
		String rich_CONTENT_ID = newsMngMode.getRICH_CONTENT_ID();
		newsMngDao.updateRICH_CONTENTInfo(rich_CONTENT_ID,newsMngMode.getCONTENT());
	}

	@Override
	@Transactional
	public void deleteInfo(Integer id) {
		// TODO Auto-generated method stub
		newsMngDao.deleteInfo(id);
	}
	
	
}
