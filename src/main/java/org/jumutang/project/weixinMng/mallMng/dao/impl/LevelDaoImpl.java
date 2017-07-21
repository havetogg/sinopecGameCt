package org.jumutang.project.weixinMng.mallMng.dao.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.weixinMng.mallMng.dao.ILevelDao;
import org.jumutang.project.weixinMng.mallMng.model.LevelMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class LevelDaoImpl implements ILevelDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findCount(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT COUNT(1) ");
		sqlBuffer.append("	FROM t_level");
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
	public List<LevelMode> findList(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, LEVEL_NAME, LEVEL_FROM, LEVEL_TO, CREAT_TIME, DELETE_FLAG");
		sqlBuffer.append("	FROM t_level");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append(" ORDER BY ID ASC ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
//				if(!StringUtil.isEmpty(QUERY_NAME)){
//					ps.setString(i++, QUERY_NAME);
//				}
			}
		}, new ResultSetExtractor<List<LevelMode>>() {

			@Override
			public List<LevelMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<LevelMode> list=new ArrayList<LevelMode>();
				while(rs.next()){
					LevelMode bean= new LevelMode();
					bean.setID(rs.getString("ID"));
					bean.setLEVEL_NAME(rs.getString("LEVEL_NAME"));
					bean.setLEVEL_FROM(rs.getString("LEVEL_FROM"));
					bean.setLEVEL_TO(rs.getString("LEVEL_TO"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					
					list.add(bean);
				}
				
				return list;
			}
		});
	}
	
	@Override
	public LevelMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, LEVEL_NAME, LEVEL_FROM, LEVEL_TO, CREAT_TIME, DELETE_FLAG");
		sqlBuffer.append("	FROM t_level");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<LevelMode>() {

			@Override
			public LevelMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					LevelMode bean= new LevelMode();

					bean.setID(rs.getString("ID"));
					bean.setLEVEL_NAME(rs.getString("LEVEL_NAME"));
					bean.setLEVEL_FROM(rs.getString("LEVEL_FROM"));
					bean.setLEVEL_TO(rs.getString("LEVEL_TO"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public int saveInfo(final LevelMode levelMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t_level(ID, LEVEL_NAME, LEVEL_FROM, LEVEL_TO, CREAT_TIME, DELETE_FLAG) ");
		sqlBuffer.append(" VALUES(?,?,?,?,?,?)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, levelMode.getID());
				ps.setString(i++, levelMode.getLEVEL_NAME());
				ps.setString(i++, levelMode.getLEVEL_FROM());
				ps.setString(i++, levelMode.getLEVEL_TO());
				ps.setString(i++, levelMode.getCREAT_TIME());
				ps.setString(i++, levelMode.getDELETE_FLAG());
				
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo(final LevelMode levelMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_level ");
		sqlBuffer.append(" SET ID=?,LEVEL_NAME=?,LEVEL_FROM=?,LEVEL_TO=?,CREAT_TIME=?,DELETE_FLAG=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, levelMode.getID());
				ps.setString(i++, levelMode.getLEVEL_NAME());
				ps.setString(i++, levelMode.getLEVEL_FROM());
				ps.setString(i++, levelMode.getLEVEL_TO());
				ps.setString(i++, levelMode.getCREAT_TIME());
				ps.setString(i++, levelMode.getDELETE_FLAG());
				ps.setString(i++, levelMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public void deleteInfo(final Integer id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_level ");
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