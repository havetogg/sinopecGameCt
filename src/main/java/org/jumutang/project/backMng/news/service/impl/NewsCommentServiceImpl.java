package org.jumutang.project.backMng.news.service.impl;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.news.dao.INewsCommentDao;
import org.jumutang.project.backMng.news.model.NewsCommentMode;
import org.jumutang.project.backMng.news.service.INewsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsCommentServiceImpl implements INewsCommentService{

	@Autowired
	private INewsCommentDao newsCommentDao;
	
	@Override
	public List<NewsCommentMode> findList(Map<String, String> queryParam, Page page) {
		// TODO Auto-generated method stub
		page.setRecordCount(newsCommentDao.findCount(queryParam));
		return newsCommentDao.findList(queryParam, page);
	}

	@Override
	public NewsCommentMode findInfo(Integer id) {
		// TODO Auto-generated method stub
		return newsCommentDao.findInfo(id);
	}

	@Override
	@Transactional
	public int saveInfo(NewsCommentMode newsCommentMode) {
		// TODO Auto-generated method stub
		return newsCommentDao.saveInfo(newsCommentMode);
	}

	@Override
	@Transactional
	public void updateInfo(NewsCommentMode newsCommentMode) {
		// TODO Auto-generated method stub
		newsCommentDao.updateInfo(newsCommentMode);
	}

	@Override
	@Transactional
	public void deleteInfo(Integer id,String NEWS_ID) {
		// TODO Auto-generated method stub
		newsCommentDao.updateNewsCOMMENT_NUM(NEWS_ID);
		newsCommentDao.deleteInfo(id);
	}
	
	
}
