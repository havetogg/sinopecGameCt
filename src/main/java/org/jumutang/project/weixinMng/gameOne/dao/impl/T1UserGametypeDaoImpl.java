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
import org.jumutang.project.weixinMng.gameOne.dao.IT1UserGametypeDao;
import org.jumutang.project.weixinMng.gameOne.model.T1UserGametypeMode;
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
public class T1UserGametypeDaoImpl implements IT1UserGametypeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findCount(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT COUNT(1) ");
		sqlBuffer.append("	FROM t1_user_gametype");
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
	public List<T1UserGametypeMode> findList(final String user_game_id) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_GAME_ID, TYPE, DAY_TIMES, GAME_USED_TIMES, GAME_ALL_TIMES, DELETE_FLAG");
		sqlBuffer.append("	FROM t1_user_gametype");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append("	AND USER_GAME_ID=?");
		sqlBuffer.append(" ORDER BY TYPE ASC ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
			    ps.setString(i++, user_game_id);
			}
		}, new ResultSetExtractor<List<T1UserGametypeMode>>() {

			@Override
			public List<T1UserGametypeMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<T1UserGametypeMode> list=new ArrayList<T1UserGametypeMode>();
				while(rs.next()){
					T1UserGametypeMode bean= new T1UserGametypeMode();
					bean.setID(rs.getString("ID"));
					bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
					bean.setTYPE(rs.getString("TYPE"));
					bean.setDAY_TIMES(rs.getString("DAY_TIMES"));
					bean.setGAME_USED_TIMES(rs.getString("GAME_USED_TIMES"));
					bean.setGAME_ALL_TIMES(rs.getString("GAME_ALL_TIMES"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setREMAIN_GAME_TIMES();   //今天游戏的剩余次数
					
					list.add(bean);
				}
				
				return list;
			}
		});
	}
	
	@Override
	public List<T1UserGametypeMode> findListALL() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_GAME_ID, TYPE, DAY_TIMES, GAME_USED_TIMES, GAME_ALL_TIMES, DELETE_FLAG");
		sqlBuffer.append("	FROM t1_user_gametype");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append(" ORDER BY TYPE ASC ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
//			    ps.setString(i++, user_game_id);
			}
		}, new ResultSetExtractor<List<T1UserGametypeMode>>() {

			@Override
			public List<T1UserGametypeMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<T1UserGametypeMode> list=new ArrayList<T1UserGametypeMode>();
				while(rs.next()){
					T1UserGametypeMode bean= new T1UserGametypeMode();
					bean.setID(rs.getString("ID"));
					bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
					bean.setTYPE(rs.getString("TYPE"));
					bean.setDAY_TIMES(rs.getString("DAY_TIMES"));
					bean.setGAME_USED_TIMES(rs.getString("GAME_USED_TIMES"));
					bean.setGAME_ALL_TIMES(rs.getString("GAME_ALL_TIMES"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setREMAIN_GAME_TIMES();   //今天游戏的剩余次数
					
					list.add(bean);
				}
				
				return list;
			}
		});
	}
	
	@Override
	public List<T1UserGametypeMode> findList(Map<String, String> queryParam, final Page page) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_GAME_ID, TYPE, DAY_TIMES, GAME_USED_TIMES, GAME_ALL_TIMES, DELETE_FLAG");
		sqlBuffer.append("	FROM t1_user_gametype");
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
		}, new ResultSetExtractor<List<T1UserGametypeMode>>() {

			@Override
			public List<T1UserGametypeMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<T1UserGametypeMode> list=new ArrayList<T1UserGametypeMode>();
				if(page==null){
					while(rs.next()){
						T1UserGametypeMode bean= new T1UserGametypeMode();
						bean.setID(rs.getString("ID"));
						bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
						bean.setTYPE(rs.getString("TYPE"));
						bean.setDAY_TIMES(rs.getString("DAY_TIMES"));
						bean.setGAME_USED_TIMES(rs.getString("GAME_USED_TIMES"));
						bean.setGAME_ALL_TIMES(rs.getString("GAME_ALL_TIMES"));
						bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
						bean.setREMAIN_GAME_TIMES();   //今天游戏的剩余次数
						
						list.add(bean);
					}
				}else{
					int num=0;
					if(rs.absolute(page.getStartRow()+1)){
						do{
							T1UserGametypeMode bean= new T1UserGametypeMode();
							bean.setID(rs.getString("ID"));
							bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
							bean.setTYPE(rs.getString("TYPE"));
							bean.setDAY_TIMES(rs.getString("DAY_TIMES"));
							bean.setGAME_USED_TIMES(rs.getString("GAME_USED_TIMES"));
							bean.setGAME_ALL_TIMES(rs.getString("GAME_ALL_TIMES"));
							bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
							bean.setREMAIN_GAME_TIMES();   //今天游戏的剩余次数
							
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
	public T1UserGametypeMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_GAME_ID, TYPE, DAY_TIMES, GAME_USED_TIMES, GAME_ALL_TIMES, DELETE_FLAG");
		sqlBuffer.append("	FROM t1_user_gametype");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<T1UserGametypeMode>() {

			@Override
			public T1UserGametypeMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					T1UserGametypeMode bean= new T1UserGametypeMode();

					bean.setID(rs.getString("ID"));
					bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
					bean.setTYPE(rs.getString("TYPE"));
					bean.setDAY_TIMES(rs.getString("DAY_TIMES"));
					bean.setGAME_USED_TIMES(rs.getString("GAME_USED_TIMES"));
					bean.setGAME_ALL_TIMES(rs.getString("GAME_ALL_TIMES"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setREMAIN_GAME_TIMES();   //今天游戏的剩余次数
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public int saveInfo(final T1UserGametypeMode t1UserGametypeMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t1_user_gametype(ID, USER_GAME_ID, TYPE, DAY_TIMES, GAME_USED_TIMES, GAME_ALL_TIMES, DELETE_FLAG) ");
		sqlBuffer.append(" VALUES(?,?,?,?,?,?,?)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, t1UserGametypeMode.getID());
				ps.setString(i++, t1UserGametypeMode.getUSER_GAME_ID());
				ps.setString(i++, t1UserGametypeMode.getTYPE());
				ps.setString(i++, t1UserGametypeMode.getDAY_TIMES());
				ps.setString(i++, t1UserGametypeMode.getGAME_USED_TIMES());
				ps.setString(i++, t1UserGametypeMode.getGAME_ALL_TIMES());
				ps.setString(i++, t1UserGametypeMode.getDELETE_FLAG());
				
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo_GAME_ALL_TIMES(final T1UserGametypeMode t1UserGametypeMode) {
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_user_gametype ");
		sqlBuffer.append(" SET GAME_ALL_TIMES=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, t1UserGametypeMode.getGAME_ALL_TIMES());
				ps.setString(i++, t1UserGametypeMode.getID());
				
				return ps;
			}
		});

	}
	
	@Override
	public void updateInfo(final T1UserGametypeMode t1UserGametypeMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_user_gametype ");
		sqlBuffer.append(" SET ID=?,USER_GAME_ID=?,TYPE=?,DAY_TIMES=?,GAME_USED_TIMES=?,GAME_ALL_TIMES=?,DELETE_FLAG=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, t1UserGametypeMode.getID());
				ps.setString(i++, t1UserGametypeMode.getUSER_GAME_ID());
				ps.setString(i++, t1UserGametypeMode.getTYPE());
				ps.setString(i++, t1UserGametypeMode.getDAY_TIMES());
				ps.setString(i++, t1UserGametypeMode.getGAME_USED_TIMES());
				ps.setString(i++, t1UserGametypeMode.getGAME_ALL_TIMES());
				ps.setString(i++, t1UserGametypeMode.getDELETE_FLAG());
				ps.setString(i++, t1UserGametypeMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public void deleteInfo(final Integer id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_user_gametype ");
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
	public void updateUserGameInfo_Times(final String type) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_user_gametype ");
		sqlBuffer.append(" SET GAME_ALL_TIMES=GAME_ALL_TIMES-DAY_TIMES+3 ");
		sqlBuffer.append(" , DAY_TIMES=3 ");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
//				ps.setString(i++, type);
				
				return ps;
			}
		});

	}

	@Override
	public void update_GameOneUsedTimes(final T1UserGametypeMode T1UserGametypeMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_user_gametype ");
		sqlBuffer.append(" SET DAY_TIMES=? ");
		sqlBuffer.append(" , GAME_USED_TIMES=? ");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, T1UserGametypeMode.getDAY_TIMES());
				ps.setString(i++, T1UserGametypeMode.getGAME_USED_TIMES());
				ps.setString(i++, T1UserGametypeMode.getID());
				
				return ps;
			}
		});

	}

}