package org.jumutang.project.backMng.discus.service.impl;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.discus.dao.IDiscusCommentDao;
import org.jumutang.project.backMng.discus.model.DiscusCommentMode;
import org.jumutang.project.backMng.discus.service.IDiscusCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscusCommentServiceImpl implements IDiscusCommentService{

	@Autowired
	private IDiscusCommentDao discusCommentDao;
	
	@Override
	public List<DiscusCommentMode> findList(Map<String, String> queryParam, Page page) {
		// TODO Auto-generated method stub
		page.setRecordCount(discusCommentDao.findCount(queryParam));
		return discusCommentDao.findList(queryParam, page);
	}

	@Override
	public DiscusCommentMode findInfo(Integer id) {
		// TODO Auto-generated method stub
		return discusCommentDao.findInfo(id);
	}

	@Override
	@Transactional
	public int saveInfo(DiscusCommentMode discusCommentMode) {
		// TODO Auto-generated method stub
		return discusCommentDao.saveInfo(discusCommentMode);
	}

	@Override
	@Transactional
	public void updateInfo(DiscusCommentMode discusCommentMode) {
		// TODO Auto-generated method stub
		discusCommentDao.updateInfo(discusCommentMode);
	}

	@Override
	@Transactional
	public void deleteInfo(Integer id,String DISCUSS_ID) {
		// TODO Auto-generated method stub
		discusCommentDao.updatediscusComment_NUM(DISCUSS_ID);
		discusCommentDao.deleteInfo(id);
	}
	
	
}
