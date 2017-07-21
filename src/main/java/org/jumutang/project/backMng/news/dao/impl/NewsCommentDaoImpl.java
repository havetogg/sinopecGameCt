package org.jumutang.project.backMng.news.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.news.dao.INewsCommentDao;
import org.jumutang.project.backMng.news.model.NewsCommentMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class NewsCommentDaoImpl implements INewsCommentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findCount(final Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT COUNT(1) ");
		sqlBuffer.append("	FROM t_news_comment");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append("	AND NEWS_ID=?");
		
		final String QUERY_NAME=queryParam.get("QUERY_NAME");
		if(!StringUtil.isEmpty(QUERY_NAME)){
			sqlBuffer.append(" AND NAME like CONCAT('%',?,'%') ");
		}		

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
				if(!StringUtil.isEmpty(QUERY_NAME)){
					ps.setString(i++, QUERY_NAME);
				}
				ps.setString(i++, queryParam.get("NEWS_ID"));
			}
		}, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				if(rs.next()){
					return rs.getInt(1);
				}else{
					return 0;
				}
			}
		});
	}
	
	@Override
	public List<NewsCommentMode> findList(final Map<String, String> queryParam, final Page page) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_ID, NEWS_ID, CONTENT, CREATE_TIME, AUDIT_FLAG, DELETE_FLAG");
		sqlBuffer.append("	FROM t_news_comment");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append("	AND NEWS_ID=?");
		
		final String QUERY_NAME=queryParam.get("QUERY_NAME");
		if(!StringUtil.isEmpty(QUERY_NAME)){
			sqlBuffer.append(" AND NAME like CONCAT('%',?,'%') ");
		}
		
//		sqlBuffer.append(" ORDER BY NAME ASC ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
				if(!StringUtil.isEmpty(QUERY_NAME)){
					ps.setString(i++, QUERY_NAME);
				}
				ps.setString(i++, queryParam.get("NEWS_ID"));
			}
		}, new ResultSetExtractor<List<NewsCommentMode>>() {

			@Override
			public List<NewsCommentMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<NewsCommentMode> list=new ArrayList<NewsCommentMode>();
				if(page==null){
					while(rs.next()){
						NewsCommentMode bean= new NewsCommentMode();
						bean.setID(rs.getString("ID"));
						bean.setUSER_ID(rs.getString("USER_ID"));
						bean.setNEWS_ID(rs.getString("NEWS_ID"));
						bean.setCONTENT(rs.getString("CONTENT"));
						bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
						bean.setAUDIT_FLAG(rs.getString("AUDIT_FLAG"));
						bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
						
						list.add(bean);
					}
				}else{
					int num=0;
					if(rs.absolute(page.getStartRow()+1)){
						do{
							NewsCommentMode bean= new NewsCommentMode();
							bean.setID(rs.getString("ID"));
							bean.setUSER_ID(rs.getString("USER_ID"));
							bean.setNEWS_ID(rs.getString("NEWS_ID"));
							bean.setCONTENT(rs.getString("CONTENT"));
							bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
							bean.setAUDIT_FLAG(rs.getString("AUDIT_FLAG"));
							bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
							
							list.add(bean);
							num++;
							if(num>=page.getPageSize()){
								break;
							}
						}while(rs.next());
					}
				}
				
				return list;
			}
		});
	}
	
	@Override
	public NewsCommentMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_ID, NEWS_ID, CONTENT, CREATE_TIME, AUDIT_FLAG, DELETE_FLAG");
		sqlBuffer.append("	FROM t_news_comment");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<NewsCommentMode>() {

			@Override
			public NewsCommentMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					NewsCommentMode bean= new NewsCommentMode();

					bean.setID(rs.getString("ID"));
					bean.setUSER_ID(rs.getString("USER_ID"));
					bean.setNEWS_ID(rs.getString("NEWS_ID"));
					bean.setCONTENT(rs.getString("CONTENT"));
					bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
					bean.setAUDIT_FLAG(rs.getString("AUDIT_FLAG"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public int saveInfo(final NewsCommentMode newsCommentMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t_news_comment(ID, USER_ID, NEWS_ID, CONTENT, CREATE_TIME, AUDIT_FLAG, DELETE_FLAG) ");
		sqlBuffer.append(" VALUES(?,?,?,?,?,?,?)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, newsCommentMode.getID());
				ps.setString(i++, newsCommentMode.getUSER_ID());
				ps.setString(i++, newsCommentMode.getNEWS_ID());
				ps.setString(i++, newsCommentMode.getCONTENT());
				ps.setString(i++, newsCommentMode.getCREATE_TIME());
				ps.setString(i++, newsCommentMode.getAUDIT_FLAG());
				ps.setString(i++, newsCommentMode.getDELETE_FLAG());
				
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo(final NewsCommentMode newsCommentMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_news_comment ");
		sqlBuffer.append(" SET USER_ID=?,NEWS_ID=?,CONTENT=?,CREATE_TIME=?,AUDIT_FLAG=?,DELETE_FLAG=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, newsCommentMode.getUSER_ID());
				ps.setString(i++, newsCommentMode.getNEWS_ID());
				ps.setString(i++, newsCommentMode.getCONTENT());
				ps.setString(i++, newsCommentMode.getCREATE_TIME());
				ps.setString(i++, newsCommentMode.getAUDIT_FLAG());
				ps.setString(i++, newsCommentMode.getDELETE_FLAG());
				ps.setString(i++, newsCommentMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public void deleteInfo(final Integer id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_news_comment ");
		sqlBuffer.append(" SET DELETE_FLAG = 1 ");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setInt(i++, id);
				
				return ps;
			}
		});

	}
	
	@Override
	public void updateNewsCOMMENT_NUM(final String newsid) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_news ");
		sqlBuffer.append(" SET COMMENT_NUM=COMMENT_NUM-1, ");
		sqlBuffer.append(" LAST_MODIFY_TIME=current_timestamp ");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, newsid);
				
				return ps;
			}
		});

	}


}