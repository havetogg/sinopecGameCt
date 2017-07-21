package org.jumutang.project.backMng.news.dao;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.news.model.NewsCommentMode;

public interface INewsCommentDao {

	public int findCount(Map<String,String> queryParam);
	
	public List<NewsCommentMode> findList(Map<String,String> queryParam,Page page);
	
	public NewsCommentMode findInfo(Integer id);
	
	public int saveInfo(NewsCommentMode NewsCommentMode);
	
	public void updateInfo(NewsCommentMode NewsCommentMode);
	
	public void deleteInfo(Integer id);
	
	public void updateNewsCOMMENT_NUM(String newsid);

}
