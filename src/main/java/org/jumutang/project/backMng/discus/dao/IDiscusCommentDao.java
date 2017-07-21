package org.jumutang.project.backMng.discus.dao;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.discus.model.DiscusCommentMode;

public interface IDiscusCommentDao {

	public int findCount(Map<String,String> queryParam);
	
	public List<DiscusCommentMode> findList(Map<String,String> queryParam,Page page);
	
	public DiscusCommentMode findInfo(Integer id);
	
	public int saveInfo(DiscusCommentMode DiscusCommentMode);
	
	public void updateInfo(DiscusCommentMode DiscusCommentMode);
	
	public void deleteInfo(Integer id);
	
	public void updatediscusComment_NUM(String discuss_id);

}
