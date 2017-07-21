package org.jumutang.project.backMng.discus.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.base.Page;
import org.apache.poi.ss.formula.functions.FinanceLib;
import org.jumutang.project.backMng.discus.dao.IDiscusCommentDao;
import org.jumutang.project.backMng.discus.model.DiscusCommentMode;
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
public class DiscusCommentDaoImpl implements IDiscusCommentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findCount(final Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT COUNT(1) ");
		sqlBuffer.append("	FROM t_discuss_comment");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append("	AND DISCUSS_ID=?");
		
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
				ps.setString(i++, queryParam.get("DISCUSS_ID"));
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
	public List<DiscusCommentMode> findList(final Map<String, String> queryParam, final Page page) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, DISCUSS_ID, USER_ID, CONTENT, CREATE_TIME, PRAISE_NUM, REPLY_NUM, DELETE_FLAG");
		sqlBuffer.append("	FROM t_discuss_comment");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append("	AND DISCUSS_ID=?");
		
		
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
				ps.setString(i++, queryParam.get("DISCUSS_ID"));
			}
		}, new ResultSetExtractor<List<DiscusCommentMode>>() {

			@Override
			public List<DiscusCommentMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<DiscusCommentMode> list=new ArrayList<DiscusCommentMode>();
				if(page==null){
					while(rs.next()){
						DiscusCommentMode bean= new DiscusCommentMode();
						bean.setID(rs.getString("ID"));
						bean.setDISCUSS_ID(rs.getString("DISCUSS_ID"));
						bean.setUSER_ID(rs.getString("USER_ID"));
						bean.setCONTENT(rs.getString("CONTENT"));
						bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
						bean.setPRAISE_NUM(rs.getString("PRAISE_NUM"));
						bean.setREPLY_NUM(rs.getString("REPLY_NUM"));
						bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
						
						list.add(bean);
					}
				}else{
					int num=0;
					if(rs.absolute(page.getStartRow()+1)){
						do{
							DiscusCommentMode bean= new DiscusCommentMode();
							bean.setID(rs.getString("ID"));
							bean.setDISCUSS_ID(rs.getString("DISCUSS_ID"));
							bean.setUSER_ID(rs.getString("USER_ID"));
							bean.setCONTENT(rs.getString("CONTENT"));
							bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
							bean.setPRAISE_NUM(rs.getString("PRAISE_NUM"));
							bean.setREPLY_NUM(rs.getString("REPLY_NUM"));
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
	public DiscusCommentMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, DISCUSS_ID, USER_ID, CONTENT, CREATE_TIME, PRAISE_NUM, REPLY_NUM, DELETE_FLAG");
		sqlBuffer.append("	FROM t_discuss_comment");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<DiscusCommentMode>() {

			@Override
			public DiscusCommentMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					DiscusCommentMode bean= new DiscusCommentMode();

					bean.setID(rs.getString("ID"));
					bean.setDISCUSS_ID(rs.getString("DISCUSS_ID"));
					bean.setUSER_ID(rs.getString("USER_ID"));
					bean.setCONTENT(rs.getString("CONTENT"));
					bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
					bean.setPRAISE_NUM(rs.getString("PRAISE_NUM"));
					bean.setREPLY_NUM(rs.getString("REPLY_NUM"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public int saveInfo(final DiscusCommentMode discusCommentMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t_discuss_comment(ID, DISCUSS_ID, USER_ID, CONTENT, CREATE_TIME, PRAISE_NUM, REPLY_NUM, DELETE_FLAG) ");
		sqlBuffer.append(" VALUES(?,?,?,?,?,?,?,?)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, discusCommentMode.getID());
				ps.setString(i++, discusCommentMode.getDISCUSS_ID());
				ps.setString(i++, discusCommentMode.getUSER_ID());
				ps.setString(i++, discusCommentMode.getCONTENT());
				ps.setString(i++, discusCommentMode.getCREATE_TIME());
				ps.setString(i++, discusCommentMode.getPRAISE_NUM());
				ps.setString(i++, discusCommentMode.getREPLY_NUM());
				ps.setString(i++, discusCommentMode.getDELETE_FLAG());
				
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo(final DiscusCommentMode discusCommentMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_discuss_comment ");
		sqlBuffer.append(" SET DISCUSS_ID=?,USER_ID=?,CONTENT=?,CREATE_TIME=?,PRAISE_NUM=?,REPLY_NUM=?,DELETE_FLAG=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, discusCommentMode.getDISCUSS_ID());
				ps.setString(i++, discusCommentMode.getUSER_ID());
				ps.setString(i++, discusCommentMode.getCONTENT());
				ps.setString(i++, discusCommentMode.getCREATE_TIME());
				ps.setString(i++, discusCommentMode.getPRAISE_NUM());
				ps.setString(i++, discusCommentMode.getREPLY_NUM());
				ps.setString(i++, discusCommentMode.getDELETE_FLAG());
				ps.setString(i++, discusCommentMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public void deleteInfo(final Integer id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_discuss_comment ");
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
	public void updatediscusComment_NUM(final String discuss_id) {
			// TODO Auto-generated method stub
			final StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(" UPDATE t_discussion ");
			sqlBuffer.append(" SET REPLY_NUM=REPLY_NUM-1, ");
			sqlBuffer.append(" LAST_MODIFY_TIME=current_timestamp ");
			sqlBuffer.append(" WHERE ID = ?");

			jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
					int i = 1;
					ps.setString(i++, discuss_id);
					
					return ps;
				}
			});

		}


}