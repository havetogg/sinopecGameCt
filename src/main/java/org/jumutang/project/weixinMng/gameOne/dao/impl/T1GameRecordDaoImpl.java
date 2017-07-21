package org.jumutang.project.weixinMng.gameOne.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.weixinMng.gameOne.dao.IT1GameRecordDao;
import org.jumutang.project.weixinMng.gameOne.model.T1GameRecordMode;
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
public class T1GameRecordDaoImpl implements IT1GameRecordDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findCount(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT COUNT(1) ");
		sqlBuffer.append("	FROM t1_game_record");
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
	public List<T1GameRecordMode> findList(Map<String, String> queryParam, final Page page) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_GAME_ID, GET_DIAMOND, GET_GOLD, GAS_TIMES, OPER_NICE, OPER_NOT_NICE, SCORE, GAME_TIME, DELETE_FLAG,TYPE");
		sqlBuffer.append("	FROM t1_game_record");
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
		}, new ResultSetExtractor<List<T1GameRecordMode>>() {

			@Override
			public List<T1GameRecordMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<T1GameRecordMode> list=new ArrayList<T1GameRecordMode>();
				if(page==null){
					while(rs.next()){
						T1GameRecordMode bean= new T1GameRecordMode();
						bean.setID(rs.getString("ID"));
						bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
						bean.setGET_DIAMOND(rs.getString("GET_DIAMOND"));
						bean.setGET_GOLD(rs.getString("GET_GOLD"));
						bean.setGAS_TIMES(rs.getString("GAS_TIMES"));
						bean.setOPER_NICE(rs.getString("OPER_NICE"));
						bean.setOPER_NOT_NICE(rs.getString("OPER_NOT_NICE"));
						bean.setSCORE(rs.getString("SCORE"));
						bean.setGAME_TIME(rs.getString("GAME_TIME"));
						bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
						bean.setTYPE(rs.getString("TYPE"));
						
						list.add(bean);
					}
				}else{
					int num=0;
					if(rs.absolute(page.getStartRow()+1)){
						do{
							T1GameRecordMode bean= new T1GameRecordMode();
							bean.setID(rs.getString("ID"));
							bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
							bean.setGET_DIAMOND(rs.getString("GET_DIAMOND"));
							bean.setGET_GOLD(rs.getString("GET_GOLD"));
							bean.setGAS_TIMES(rs.getString("GAS_TIMES"));
							bean.setOPER_NICE(rs.getString("OPER_NICE"));
							bean.setOPER_NOT_NICE(rs.getString("OPER_NOT_NICE"));
							bean.setSCORE(rs.getString("SCORE"));
							bean.setGAME_TIME(rs.getString("GAME_TIME"));
							bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
							bean.setTYPE(rs.getString("TYPE"));
							
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
	public T1GameRecordMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_GAME_ID, GET_DIAMOND, GET_GOLD, GAS_TIMES, OPER_NICE, OPER_NOT_NICE, SCORE, GAME_TIME, DELETE_FLAG,TYPE");
		sqlBuffer.append("	FROM t1_game_record");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<T1GameRecordMode>() {

			@Override
			public T1GameRecordMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					T1GameRecordMode bean= new T1GameRecordMode();

					bean.setID(rs.getString("ID"));
					bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
					bean.setGET_DIAMOND(rs.getString("GET_DIAMOND"));
					bean.setGET_GOLD(rs.getString("GET_GOLD"));
					bean.setGAS_TIMES(rs.getString("GAS_TIMES"));
					bean.setOPER_NICE(rs.getString("OPER_NICE"));
					bean.setOPER_NOT_NICE(rs.getString("OPER_NOT_NICE"));
					bean.setSCORE(rs.getString("SCORE"));
					bean.setGAME_TIME(rs.getString("GAME_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setTYPE(rs.getString("TYPE"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public int saveInfo(final T1GameRecordMode t1GameRecordMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t1_game_record(ID, USER_GAME_ID, GET_DIAMOND, GET_GOLD, GAS_TIMES, OPER_NICE, OPER_NOT_NICE, SCORE, GAME_TIME, DELETE_FLAG,TYPE,LEVEL_SCORE) ");
		sqlBuffer.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, t1GameRecordMode.getID());
				ps.setString(i++, t1GameRecordMode.getUSER_GAME_ID());
				ps.setString(i++, t1GameRecordMode.getGET_DIAMOND());
				ps.setString(i++, t1GameRecordMode.getGET_GOLD());
				ps.setString(i++, t1GameRecordMode.getGAS_TIMES());
				ps.setString(i++, t1GameRecordMode.getOPER_NICE());
				ps.setString(i++, t1GameRecordMode.getOPER_NOT_NICE());
				ps.setString(i++, t1GameRecordMode.getSCORE());
				ps.setString(i++, t1GameRecordMode.getGAME_TIME());
				ps.setString(i++, t1GameRecordMode.getDELETE_FLAG());
				ps.setString(i++, t1GameRecordMode.getTYPE());
				ps.setString(i++, t1GameRecordMode.getLEVEL_SCORE());
				
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo(final T1GameRecordMode t1GameRecordMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_game_record ");
		sqlBuffer.append(" SET ID=?,USER_GAME_ID=?,GET_DIAMOND=?,GET_GOLD=?,GAS_TIMES=?,OPER_NICE=?,OPER_NOT_NICE=?,SCORE=?,GAME_TIME=?,DELETE_FLAG=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, t1GameRecordMode.getID());
				ps.setString(i++, t1GameRecordMode.getUSER_GAME_ID());
				ps.setString(i++, t1GameRecordMode.getGET_DIAMOND());
				ps.setString(i++, t1GameRecordMode.getGET_GOLD());
				ps.setString(i++, t1GameRecordMode.getGAS_TIMES());
				ps.setString(i++, t1GameRecordMode.getOPER_NICE());
				ps.setString(i++, t1GameRecordMode.getOPER_NOT_NICE());
				ps.setString(i++, t1GameRecordMode.getSCORE());
				ps.setString(i++, t1GameRecordMode.getGAME_TIME());
				ps.setString(i++, t1GameRecordMode.getDELETE_FLAG());
				ps.setString(i++, t1GameRecordMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public void deleteInfo(final Integer id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_game_record ");
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
	public int finduserGamedCount(final String user_game_id,final String TYPE) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT COUNT(1) ");
		sqlBuffer.append("	FROM t1_game_record");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append("	AND USER_GAME_ID=?");
		sqlBuffer.append("	AND TYPE=?");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
				ps.setString(i++, user_game_id);
				ps.setString(i++, TYPE);
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


}