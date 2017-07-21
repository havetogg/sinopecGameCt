package org.jumutang.project.backMng.discus.service;

import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.discus.model.DiscusCommentMode;

public interface IDiscusCommentService {
	
	public List<DiscusCommentMode> findList(Map<String,String> queryParam,Page page);
	
	public DiscusCommentMode findInfo(Integer id);
	
	public int saveInfo(DiscusCommentMode discusCommentMode);
	
	public void updateInfo(DiscusCommentMode discusCommentMode);
	
	public void deleteInfo(Integer id,String DISCUSS_ID);
}