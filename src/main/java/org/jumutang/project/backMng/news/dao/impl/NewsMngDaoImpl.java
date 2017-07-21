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
import org.jumutang.project.backMng.news.dao.INewsMngDao;
import org.jumutang.project.backMng.news.model.NewsMngMode;
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
public class NewsMngDaoImpl implements INewsMngDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findCount(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT COUNT(1) ");
		sqlBuffer.append("	FROM t_news");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		
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
	public List<NewsMngMode> findList(Map<String, String> queryParam, final Page page) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, NAME, REMARK, IN_USE_FLAG, PUBLISH_TIME, RICH_CONTENT_ID, ONLINE_START_TIME, ONLINE_END_TIME, CREATE_TIME, LAST_MODIFY_TIME, SHOW_ORDER, COMMENT_NUM, DELETE_FLAG");
		sqlBuffer.append("	FROM t_news");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		
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
			}
		}, new ResultSetExtractor<List<NewsMngMode>>() {

			@Override
			public List<NewsMngMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<NewsMngMode> list=new ArrayList<NewsMngMode>();
				if(page==null){
					while(rs.next()){
						NewsMngMode bean= new NewsMngMode();
						bean.setID(rs.getString("ID"));
						bean.setNAME(rs.getString("NAME"));
						bean.setREMARK(rs.getString("REMARK"));
						bean.setIN_USE_FLAG(rs.getString("IN_USE_FLAG"));
						bean.setPUBLISH_TIME(rs.getString("PUBLISH_TIME"));
						bean.setRICH_CONTENT_ID(rs.getString("RICH_CONTENT_ID"));
						bean.setONLINE_START_TIME(rs.getString("ONLINE_START_TIME"));
						bean.setONLINE_END_TIME(rs.getString("ONLINE_END_TIME"));
						bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
						bean.setLAST_MODIFY_TIME(rs.getString("LAST_MODIFY_TIME"));
						bean.setSHOW_ORDER(rs.getString("SHOW_ORDER"));
						bean.setCOMMENT_NUM(rs.getString("COMMENT_NUM"));
						bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
						
						list.add(bean);
					}
				}else{
					int num=0;
					if(rs.absolute(page.getStartRow()+1)){
						do{
							NewsMngMode bean= new NewsMngMode();
							bean.setID(rs.getString("ID"));
							bean.setNAME(rs.getString("NAME"));
							bean.setREMARK(rs.getString("REMARK"));
							bean.setIN_USE_FLAG(rs.getString("IN_USE_FLAG"));
							bean.setPUBLISH_TIME(rs.getString("PUBLISH_TIME"));
							bean.setRICH_CONTENT_ID(rs.getString("RICH_CONTENT_ID"));
							bean.setONLINE_START_TIME(rs.getString("ONLINE_START_TIME"));
							bean.setONLINE_END_TIME(rs.getString("ONLINE_END_TIME"));
							bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
							bean.setLAST_MODIFY_TIME(rs.getString("LAST_MODIFY_TIME"));
							bean.setSHOW_ORDER(rs.getString("SHOW_ORDER"));
							bean.setCOMMENT_NUM(rs.getString("COMMENT_NUM"));
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
	public NewsMngMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT tnews.ID, tnews.NAME, tnews.REMARK, tnews.IN_USE_FLAG, tnews.PUBLISH_TIME, tnews.RICH_CONTENT_ID, tnews.ONLINE_START_TIME, tnews.ONLINE_END_TIME, tnews.CREATE_TIME, tnews.LAST_MODIFY_TIME, tnews.SHOW_ORDER, tnews.COMMENT_NUM, tnews.DELETE_FLAG,tnews.NEWSURL,tcont.CONTENT");
		sqlBuffer.append("	,tnews.CLICK_RATE,tnews.CLICK_BASE,tnews.AUTHOR_NAME,tnews.AUTHOR_LINK,tnews.DIANZ_NUM,tnews.DIANZ_BASE");
		sqlBuffer.append("	FROM t_news tnews, t_rich_content tcont");
		sqlBuffer.append("	WHERE tnews.ID=?");
		sqlBuffer.append("	and tnews.RICH_CONTENT_ID=tcont.ID");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<NewsMngMode>() {

			@Override
			public NewsMngMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					NewsMngMode bean= new NewsMngMode();

					bean.setID(rs.getString("ID"));
					bean.setNAME(rs.getString("NAME"));
					bean.setREMARK(rs.getString("REMARK"));
					bean.setIN_USE_FLAG(rs.getString("IN_USE_FLAG"));
					bean.setPUBLISH_TIME(rs.getString("PUBLISH_TIME"));
					bean.setRICH_CONTENT_ID(rs.getString("RICH_CONTENT_ID"));
					bean.setONLINE_START_TIME(rs.getString("ONLINE_START_TIME"));
					bean.setONLINE_END_TIME(rs.getString("ONLINE_END_TIME"));
					bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
					bean.setLAST_MODIFY_TIME(rs.getString("LAST_MODIFY_TIME"));
					bean.setSHOW_ORDER(rs.getString("SHOW_ORDER"));
					bean.setCOMMENT_NUM(rs.getString("COMMENT_NUM"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setCONTENT(rs.getString("CONTENT"));
					bean.setNEWSURL(rs.getString("NEWSURL"));
					
					bean.setCLICK_RATE(rs.getString("CLICK_RATE"));
					bean.setCLICK_BASE(rs.getString("CLICK_BASE"));
					bean.setAUTHOR_NAME(rs.getString("AUTHOR_NAME"));
					bean.setAUTHOR_LINK(rs.getString("AUTHOR_LINK"));
					bean.setDIANZ_NUM(rs.getString("DIANZ_NUM"));
					bean.setDIANZ_BASE(rs.getString("DIANZ_BASE"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public int saveInfo(final NewsMngMode newsMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t_news(ID, NAME, REMARK, IN_USE_FLAG, PUBLISH_TIME, RICH_CONTENT_ID, ONLINE_START_TIME, ONLINE_END_TIME, CREATE_TIME, LAST_MODIFY_TIME, SHOW_ORDER, COMMENT_NUM, DELETE_FLAG,NEWSURL,AUTHOR_NAME,AUTHOR_LINK,DIANZ_BASE,CLICK_BASE) ");
		sqlBuffer.append(" VALUES(?,?,?,?,?,?,?,?,current_timestamp,current_timestamp,?,?,?,?,?,?,?,?)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, newsMngMode.getID());
				ps.setString(i++, newsMngMode.getNAME());
				ps.setString(i++, newsMngMode.getREMARK());
				ps.setString(i++, newsMngMode.getIN_USE_FLAG());
				ps.setString(i++, newsMngMode.getPUBLISH_TIME());
				ps.setString(i++, newsMngMode.getRICH_CONTENT_ID());
				ps.setString(i++, newsMngMode.getONLINE_START_TIME());
				ps.setString(i++, newsMngMode.getONLINE_END_TIME());
/*				ps.setString(i++, newsMngMode.getCREATE_TIME());
				ps.setString(i++, newsMngMode.getLAST_MODIFY_TIME());*/
				ps.setString(i++, newsMngMode.getSHOW_ORDER());
				ps.setString(i++, newsMngMode.getCOMMENT_NUM());
				ps.setString(i++, newsMngMode.getDELETE_FLAG());
				ps.setString(i++, newsMngMode.getNEWSURL());
				
				ps.setString(i++, newsMngMode.getAUTHOR_NAME());
				ps.setString(i++, newsMngMode.getAUTHOR_LINK());
				ps.setString(i++, newsMngMode.getDIANZ_BASE());
				ps.setString(i++, newsMngMode.getCLICK_BASE());
				
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo(final NewsMngMode newsMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_news ");
		sqlBuffer.append(" SET NAME=?,REMARK=?,IN_USE_FLAG=? ");
		sqlBuffer.append(" ,PUBLISH_TIME=?,ONLINE_START_TIME=?,ONLINE_END_TIME=?,LAST_MODIFY_TIME=current_timestamp,SHOW_ORDER=?,DELETE_FLAG=0,NEWSURL=?");
		sqlBuffer.append(" ,AUTHOR_NAME=?,AUTHOR_LINK=?,DIANZ_BASE=?,CLICK_BASE=?");
		sqlBuffer.append(" WHERE ID = ? ");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, newsMngMode.getNAME());
				ps.setString(i++, newsMngMode.getREMARK());
				ps.setString(i++, newsMngMode.getIN_USE_FLAG());
				ps.setString(i++, newsMngMode.getPUBLISH_TIME());
				ps.setString(i++, newsMngMode.getONLINE_START_TIME());
				ps.setString(i++, newsMngMode.getONLINE_END_TIME());
				ps.setString(i++, newsMngMode.getSHOW_ORDER());
				ps.setString(i++, newsMngMode.getNEWSURL());
				
				ps.setString(i++, newsMngMode.getAUTHOR_NAME());
				ps.setString(i++, newsMngMode.getAUTHOR_LINK());
				ps.setString(i++, newsMngMode.getDIANZ_BASE());
				ps.setString(i++, newsMngMode.getCLICK_BASE());
				
				ps.setString(i++, newsMngMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public void deleteInfo(final Integer id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_news ");
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
	public void saveRICH_CONTENTInfo(final String ID, final String CONTENT) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t_rich_content(ID, CONTENT) ");
		sqlBuffer.append(" VALUES(?,?)");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, ID);
				ps.setString(i++, CONTENT);
				
				return ps;
			}
		});
	}
	
	@Override
	public void updateRICH_CONTENTInfo(final String ID, final String CONTENT) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_rich_content ");
		sqlBuffer.append(" SET CONTENT=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, CONTENT);
				ps.setString(i++, ID);
				return ps;
			}
		});

	}

}