package org.jumutang.project.backMng.news.service;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.news.model.NewsCommentMode;

public interface INewsCommentService {
	
	public List<NewsCommentMode> findList(Map<String,String> queryParam,Page page);
	
	public NewsCommentMode findInfo(Integer id);
	
	public int saveInfo(NewsCommentMode newsCommentMode);
	
	public void updateInfo(NewsCommentMode newsCommentMode);
	
	public void deleteInfo(Integer id,String NEWS_ID);
}