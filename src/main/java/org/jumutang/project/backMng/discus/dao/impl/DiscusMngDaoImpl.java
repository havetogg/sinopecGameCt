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
import org.jumutang.project.backMng.discus.dao.IDiscusMngDao;
import org.jumutang.project.backMng.discus.model.DiscusMngMode;
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
public class DiscusMngDaoImpl implements IDiscusMngDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findCount(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT COUNT(1) ");
		sqlBuffer.append("	FROM t_discussion");
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
	public List<DiscusMngMode> findList(Map<String, String> queryParam, final Page page) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, NAME, IN_USE_FLAG, PUBLISH_TIME, CONTENT, ORIGINAL_URL, CUTTED_PATH, REPLY_NUM, PRAISE_NUM, CONCERN_NUM, ONLINE_START_TIME, ONLINE_END_TIME, CREATE_TIME, LAST_MODIFY_TIME, SHOW_ORDER, DELETE_FLAG");
		sqlBuffer.append("	FROM t_discussion");
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
		}, new ResultSetExtractor<List<DiscusMngMode>>() {

			@Override
			public List<DiscusMngMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<DiscusMngMode> list=new ArrayList<DiscusMngMode>();
				if(page==null){
					while(rs.next()){
						DiscusMngMode bean= new DiscusMngMode();
						bean.setID(rs.getString("ID"));
						bean.setNAME(rs.getString("NAME"));
						bean.setIN_USE_FLAG(rs.getString("IN_USE_FLAG"));
						bean.setPUBLISH_TIME(rs.getString("PUBLISH_TIME"));
						bean.setCONTENT(rs.getString("CONTENT"));
						bean.setORIGINAL_URL(rs.getString("ORIGINAL_URL"));
						bean.setCUTTED_PATH(rs.getString("CUTTED_PATH"));
						bean.setREPLY_NUM(rs.getString("REPLY_NUM"));
						bean.setPRAISE_NUM(rs.getString("PRAISE_NUM"));
						bean.setCONCERN_NUM(rs.getString("CONCERN_NUM"));
						bean.setONLINE_START_TIME(rs.getString("ONLINE_START_TIME"));
						bean.setONLINE_END_TIME(rs.getString("ONLINE_END_TIME"));
						bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
						bean.setLAST_MODIFY_TIME(rs.getString("LAST_MODIFY_TIME"));
						bean.setSHOW_ORDER(rs.getString("SHOW_ORDER"));
						bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
						
						list.add(bean);
					}
				}else{
					int num=0;
					if(rs.absolute(page.getStartRow()+1)){
						do{
							DiscusMngMode bean= new DiscusMngMode();
							bean.setID(rs.getString("ID"));
							bean.setNAME(rs.getString("NAME"));
							bean.setIN_USE_FLAG(rs.getString("IN_USE_FLAG"));
							bean.setPUBLISH_TIME(rs.getString("PUBLISH_TIME"));
							bean.setCONTENT(rs.getString("CONTENT"));
							bean.setORIGINAL_URL(rs.getString("ORIGINAL_URL"));
							bean.setCUTTED_PATH(rs.getString("CUTTED_PATH"));
							bean.setREPLY_NUM(rs.getString("REPLY_NUM"));
							bean.setPRAISE_NUM(rs.getString("PRAISE_NUM"));
							bean.setCONCERN_NUM(rs.getString("CONCERN_NUM"));
							bean.setONLINE_START_TIME(rs.getString("ONLINE_START_TIME"));
							bean.setONLINE_END_TIME(rs.getString("ONLINE_END_TIME"));
							bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
							bean.setLAST_MODIFY_TIME(rs.getString("LAST_MODIFY_TIME"));
							bean.setSHOW_ORDER(rs.getString("SHOW_ORDER"));
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
	public DiscusMngMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, NAME, IN_USE_FLAG, PUBLISH_TIME, CONTENT, ORIGINAL_URL, CUTTED_PATH, REPLY_NUM, PRAISE_NUM, CONCERN_NUM, ONLINE_START_TIME, ONLINE_END_TIME, CREATE_TIME, LAST_MODIFY_TIME, SHOW_ORDER, DELETE_FLAG");
		sqlBuffer.append("	FROM t_discussion");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<DiscusMngMode>() {

			@Override
			public DiscusMngMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					DiscusMngMode bean= new DiscusMngMode();

					bean.setID(rs.getString("ID"));
					bean.setNAME(rs.getString("NAME"));
					bean.setIN_USE_FLAG(rs.getString("IN_USE_FLAG"));
					bean.setPUBLISH_TIME(rs.getString("PUBLISH_TIME"));
					bean.setCONTENT(rs.getString("CONTENT"));
					bean.setORIGINAL_URL(rs.getString("ORIGINAL_URL"));
					bean.setCUTTED_PATH(rs.getString("CUTTED_PATH"));
					bean.setREPLY_NUM(rs.getString("REPLY_NUM"));
					bean.setPRAISE_NUM(rs.getString("PRAISE_NUM"));
					bean.setCONCERN_NUM(rs.getString("CONCERN_NUM"));
					bean.setONLINE_START_TIME(rs.getString("ONLINE_START_TIME"));
					bean.setONLINE_END_TIME(rs.getString("ONLINE_END_TIME"));
					bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
					bean.setLAST_MODIFY_TIME(rs.getString("LAST_MODIFY_TIME"));
					bean.setSHOW_ORDER(rs.getString("SHOW_ORDER"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public int saveInfo(final DiscusMngMode discusMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t_discussion(NAME, IN_USE_FLAG, PUBLISH_TIME, CONTENT, ORIGINAL_URL, CUTTED_PATH, REPLY_NUM, PRAISE_NUM, CONCERN_NUM, ONLINE_START_TIME, ONLINE_END_TIME, CREATE_TIME, LAST_MODIFY_TIME, SHOW_ORDER, DELETE_FLAG) ");
		sqlBuffer.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,current_timestamp,current_timestamp,?,?)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, discusMngMode.getNAME());
				ps.setString(i++, discusMngMode.getIN_USE_FLAG());
				ps.setString(i++, discusMngMode.getPUBLISH_TIME());
				ps.setString(i++, discusMngMode.getCONTENT());
				ps.setString(i++, discusMngMode.getORIGINAL_URL());
				ps.setString(i++, discusMngMode.getCUTTED_PATH());
				ps.setString(i++, discusMngMode.getREPLY_NUM());
				ps.setString(i++, discusMngMode.getPRAISE_NUM());
				ps.setString(i++, discusMngMode.getCONCERN_NUM());
				ps.setString(i++, discusMngMode.getONLINE_START_TIME());
				ps.setString(i++, discusMngMode.getONLINE_END_TIME());
//				ps.setString(i++, discusMngMode.getCREATE_TIME());
//				ps.setString(i++, discusMngMode.getLAST_MODIFY_TIME());
				ps.setString(i++, discusMngMode.getSHOW_ORDER());
				ps.setString(i++, discusMngMode.getDELETE_FLAG());
				
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo(final DiscusMngMode discusMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_discussion ");
		sqlBuffer.append(" SET NAME=?,IN_USE_FLAG=?,PUBLISH_TIME=?,CONTENT=?,ORIGINAL_URL=?,CUTTED_PATH=?,ONLINE_START_TIME=?,ONLINE_END_TIME=?,LAST_MODIFY_TIME=current_timestamp,SHOW_ORDER=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, discusMngMode.getNAME());
				ps.setString(i++, discusMngMode.getIN_USE_FLAG());
				ps.setString(i++, discusMngMode.getPUBLISH_TIME());
				ps.setString(i++, discusMngMode.getCONTENT());
				ps.setString(i++, discusMngMode.getORIGINAL_URL());
				ps.setString(i++, discusMngMode.getCUTTED_PATH());
				ps.setString(i++, discusMngMode.getONLINE_START_TIME());
				ps.setString(i++, discusMngMode.getONLINE_END_TIME());
				ps.setString(i++, discusMngMode.getSHOW_ORDER());
				ps.setString(i++, discusMngMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public void deleteInfo(final Integer id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_discussion ");
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


}