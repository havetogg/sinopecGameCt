package org.jumutang.project.weixinMng.mallMng.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.dao.ISysMsgDao;
import org.jumutang.project.weixinMng.mallMng.model.SysMsgMode;
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
public class SysMsgDaoImpl implements ISysMsgDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public int findNotReadCount(final String user_id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT COUNT(1) ");
		sqlBuffer.append("	FROM t_sysmsg");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append("	AND READ_FLAG=0");
		sqlBuffer.append("	AND USER_ID=?");
		
		
		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
				ps.setString(i++, user_id);
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
	public List<SysMsgMode> findList(final String user_id) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_ID, MSG_TITLE, MSG_DETAIL, DATE_FORMAT(CREAT_TIME,'%Y-%m-%d %H:%i:%S') CREAT_TIME, TYPE, DIAMOND,GOLD, GET_TIME, DELETE_FLAG,READ_FLAG,READ_TIME");
		sqlBuffer.append("	FROM t_sysmsg");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append("	AND USER_ID=? ");
		sqlBuffer.append(" ORDER BY CREAT_TIME desc ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
				ps.setString(i++, user_id);
			}
		}, new ResultSetExtractor<List<SysMsgMode>>() {

			@Override
			public List<SysMsgMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<SysMsgMode> list=new ArrayList<SysMsgMode>();
				while(rs.next()){
					SysMsgMode bean= new SysMsgMode();
					bean.setID(rs.getString("ID"));
					bean.setUSER_ID(rs.getString("USER_ID"));
					bean.setMSG_TITLE(rs.getString("MSG_TITLE"));
					bean.setMSG_DETAIL(rs.getString("MSG_DETAIL"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setTYPE(rs.getString("TYPE"));
					bean.setDIAMOND(rs.getString("DIAMOND"));
					bean.setGOLD(rs.getString("GOLD"));
					bean.setGET_TIME(rs.getString("GET_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setREAD_FLAG(rs.getString("READ_FLAG"));
					bean.setREAD_TIME(rs.getString("READ_TIME"));
					
					list.add(bean);
				}
				
				return list;
			}
		});
	}
	
	@Override
	public SysMsgMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_ID, MSG_TITLE, MSG_DETAIL, CREAT_TIME, TYPE, DIAMOND,GOLD, GET_TIME, DELETE_FLAG,READ_FLAG,READ_TIME");
		sqlBuffer.append("	FROM t_sysmsg");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<SysMsgMode>() {

			@Override
			public SysMsgMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					SysMsgMode bean= new SysMsgMode();

					bean.setID(rs.getString("ID"));
					bean.setUSER_ID(rs.getString("USER_ID"));
					bean.setMSG_TITLE(rs.getString("MSG_TITLE"));
					bean.setMSG_DETAIL(rs.getString("MSG_DETAIL"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setTYPE(rs.getString("TYPE"));
					bean.setDIAMOND(rs.getString("DIAMOND"));
					bean.setGOLD(rs.getString("GOLD"));
					bean.setGET_TIME(rs.getString("GET_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setREAD_FLAG(rs.getString("READ_FLAG"));
					bean.setREAD_TIME(rs.getString("READ_TIME"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public int saveInfo(final SysMsgMode sysMsgMode) {
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t_sysmsg(USER_ID, MSG_TITLE, MSG_DETAIL, CREAT_TIME, TYPE, DIAMOND,GOLD, GET_TIME, DELETE_FLAG) ");
		sqlBuffer.append(" VALUES(?,?,?,current_timestamp,?,?,?,?,?)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, sysMsgMode.getUSER_ID());
				ps.setString(i++, sysMsgMode.getMSG_TITLE());
				ps.setString(i++, sysMsgMode.getMSG_DETAIL());
//				ps.setString(i++, sysMsgMode.getCREAT_TIME());
				ps.setString(i++, sysMsgMode.getTYPE());
				ps.setString(i++, sysMsgMode.getDIAMOND());
				ps.setString(i++, sysMsgMode.getGOLD());
				ps.setString(i++, sysMsgMode.getGET_TIME());
				ps.setString(i++, "0");
				
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo(final SysMsgMode sysMsgMode) {
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_sysmsg ");
		sqlBuffer.append(" SET GET_TIME=current_timestamp ");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				
				ps.setString(i++, sysMsgMode.getID());
				return ps;
			}
		});

	}
	
	@Override
	public void updateInfo_readTime(final String user_id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_sysmsg ");
		sqlBuffer.append(" SET READ_TIME=current_timestamp ");
		sqlBuffer.append(" , READ_FLAG=1 ");
		sqlBuffer.append(" WHERE USER_ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				
				ps.setString(i++, user_id);
				return ps;
			}
		});

	}

	@Override
	public SysMsgMode findUnGetInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_ID, MSG_TITLE, MSG_DETAIL, CREAT_TIME, TYPE, DIAMOND,GOLD, GET_TIME, DELETE_FLAG,READ_FLAG,READ_TIME");
		sqlBuffer.append("	FROM t_sysmsg");
		sqlBuffer.append("	WHERE ID=?");
		sqlBuffer.append("	AND GET_TIME IS NULL");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<SysMsgMode>() {

			@Override
			public SysMsgMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					SysMsgMode bean= new SysMsgMode();

					bean.setID(rs.getString("ID"));
					bean.setUSER_ID(rs.getString("USER_ID"));
					bean.setMSG_TITLE(rs.getString("MSG_TITLE"));
					bean.setMSG_DETAIL(rs.getString("MSG_DETAIL"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setTYPE(rs.getString("TYPE"));
					bean.setDIAMOND(rs.getString("DIAMOND"));
					bean.setGOLD(rs.getString("GOLD"));
					bean.setGET_TIME(rs.getString("GET_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setREAD_FLAG(rs.getString("READ_FLAG"));
					bean.setREAD_TIME(rs.getString("READ_TIME"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}

}
