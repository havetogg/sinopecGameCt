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
import org.jumutang.project.weixinMng.gameOne.dao.IT1AttributeBuyDao;
import org.jumutang.project.weixinMng.gameOne.model.T1AttributeBuyMode;
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
public class T1AttributeBuyDaoImpl implements IT1AttributeBuyDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findCount(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT COUNT(1) ");
		sqlBuffer.append("	FROM t1_attribute_buy");
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
	public int findCount_Today_game_buy(final String user_game_id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("  SELECT COUNT(1)  ");
		sqlBuffer.append("  FROM t1_attribute_buy ");
		sqlBuffer.append("  WHERE DELETE_FLAG=0 ");
		sqlBuffer.append("  and TYPE=1 ");
		sqlBuffer.append("  and to_days(BUY_TIME) = to_days(now()) ");
		sqlBuffer.append("  and USER_GAME_ID=? ");
		
		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
					ps.setString(i++, user_game_id);
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
	public int findCount_Oil_machine_buy(final String user_game_id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("  SELECT COUNT(1)  ");
		sqlBuffer.append("  FROM t1_attribute_buy ");
		sqlBuffer.append("  WHERE DELETE_FLAG=0 ");
		sqlBuffer.append("  and TYPE=3 ");
		sqlBuffer.append("  and USER_GAME_ID=? ");
		
		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
					ps.setString(i++, user_game_id);
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
	public int findCount_Park_buy(final String user_game_id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("  SELECT COUNT(1)  ");
		sqlBuffer.append("  FROM t1_attribute_buy ");
		sqlBuffer.append("  WHERE DELETE_FLAG=0 ");
		sqlBuffer.append("  and TYPE=2 ");
		sqlBuffer.append("  and USER_GAME_ID=? ");
		
		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
					ps.setString(i++, user_game_id);
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
	public List<T1AttributeBuyMode> findList(Map<String, String> queryParam, final Page page) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_GAME_ID, TYPE, BUY_TIME, DELETE_FLAG, PAYED_DIAMOND, PAYED_GOLD,USER_GAMETYPE");
		sqlBuffer.append("	FROM t1_attribute_buy");
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
		}, new ResultSetExtractor<List<T1AttributeBuyMode>>() {

			@Override
			public List<T1AttributeBuyMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<T1AttributeBuyMode> list=new ArrayList<T1AttributeBuyMode>();
				if(page==null){
					while(rs.next()){
						T1AttributeBuyMode bean= new T1AttributeBuyMode();
						bean.setID(rs.getString("ID"));
						bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
						bean.setTYPE(rs.getString("TYPE"));
						bean.setUSER_GAMETYPE(rs.getString("USER_GAMETYPE"));
						bean.setBUY_TIME(rs.getString("BUY_TIME"));
						bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
						bean.setPAYED_DIAMOND(rs.getString("PAYED_DIAMOND"));
						bean.setPAYED_GOLD(rs.getString("PAYED_GOLD"));
						
						list.add(bean);
					}
				}else{
					int num=0;
					if(rs.absolute(page.getStartRow()+1)){
						do{
							T1AttributeBuyMode bean= new T1AttributeBuyMode();
							bean.setID(rs.getString("ID"));
							bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
							bean.setTYPE(rs.getString("TYPE"));
							bean.setUSER_GAMETYPE(rs.getString("USER_GAMETYPE"));
							bean.setBUY_TIME(rs.getString("BUY_TIME"));
							bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
							bean.setPAYED_DIAMOND(rs.getString("PAYED_DIAMOND"));
							bean.setPAYED_GOLD(rs.getString("PAYED_GOLD"));
							
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
	public List<T1AttributeBuyMode> findList_notgametimes(final String user_game_id) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_GAME_ID, TYPE, BUY_TIME, DELETE_FLAG, PAYED_DIAMOND, PAYED_GOLD,USER_GAMETYPE");
		sqlBuffer.append("	FROM t1_attribute_buy");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append("  and USER_GAME_ID=? ");
		sqlBuffer.append("  and TYPE!='1' ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
				ps.setString(i++, user_game_id);
			}
		}, new ResultSetExtractor<List<T1AttributeBuyMode>>() {

			@Override
			public List<T1AttributeBuyMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<T1AttributeBuyMode> list=new ArrayList<T1AttributeBuyMode>();
				while(rs.next()){
					T1AttributeBuyMode bean= new T1AttributeBuyMode();
					bean.setID(rs.getString("ID"));
					bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
					bean.setTYPE(rs.getString("TYPE"));
					bean.setUSER_GAMETYPE(rs.getString("USER_GAMETYPE"));
					bean.setBUY_TIME(rs.getString("BUY_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setPAYED_DIAMOND(rs.getString("PAYED_DIAMOND"));
					bean.setPAYED_GOLD(rs.getString("PAYED_GOLD"));
					
					list.add(bean);
				}
				
				return list;
			}
		});
	}
	
	
	@Override
	public List<T1AttributeBuyMode> findList_gameBuytimes(final String user_game_id) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_GAME_ID, TYPE, BUY_TIME, DELETE_FLAG, PAYED_DIAMOND, PAYED_GOLD,USER_GAMETYPE");
		sqlBuffer.append("	FROM t1_attribute_buy");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append("  and USER_GAME_ID=? ");
		sqlBuffer.append("  and TYPE='1' ");
		sqlBuffer.append("  and to_days(BUY_TIME) = to_days(now()) ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
				ps.setString(i++, user_game_id);
			}
		}, new ResultSetExtractor<List<T1AttributeBuyMode>>() {

			@Override
			public List<T1AttributeBuyMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<T1AttributeBuyMode> list=new ArrayList<T1AttributeBuyMode>();
				while(rs.next()){
					T1AttributeBuyMode bean= new T1AttributeBuyMode();
					bean.setID(rs.getString("ID"));
					bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
					bean.setTYPE(rs.getString("TYPE"));
					bean.setUSER_GAMETYPE(rs.getString("USER_GAMETYPE"));
					bean.setBUY_TIME(rs.getString("BUY_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setPAYED_DIAMOND(rs.getString("PAYED_DIAMOND"));
					bean.setPAYED_GOLD(rs.getString("PAYED_GOLD"));
					
					list.add(bean);
				}
				
				return list;
			}
		});
	}
	
	@Override
	public T1AttributeBuyMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_GAME_ID, TYPE, BUY_TIME, DELETE_FLAG, PAYED_DIAMOND, PAYED_GOLD,USER_GAMETYPE");
		sqlBuffer.append("	FROM t1_attribute_buy");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<T1AttributeBuyMode>() {

			@Override
			public T1AttributeBuyMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					T1AttributeBuyMode bean= new T1AttributeBuyMode();

					bean.setID(rs.getString("ID"));
					bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
					bean.setTYPE(rs.getString("TYPE"));
					bean.setUSER_GAMETYPE(rs.getString("USER_GAMETYPE"));
					bean.setBUY_TIME(rs.getString("BUY_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setPAYED_DIAMOND(rs.getString("PAYED_DIAMOND"));
					bean.setPAYED_GOLD(rs.getString("PAYED_GOLD"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public int saveInfo(final T1AttributeBuyMode t1AttributeBuyMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t1_attribute_buy(ID, USER_GAME_ID, TYPE,USER_GAMETYPE, BUY_TIME, PAYED_DIAMOND, PAYED_GOLD, DELETE_FLAG) ");
		sqlBuffer.append(" VALUES(?,?,?,?,?,?,?,?)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, t1AttributeBuyMode.getID());
				ps.setString(i++, t1AttributeBuyMode.getUSER_GAME_ID());
				ps.setString(i++, t1AttributeBuyMode.getTYPE());
				ps.setString(i++, t1AttributeBuyMode.getUSER_GAMETYPE());
				ps.setString(i++, t1AttributeBuyMode.getBUY_TIME());
				ps.setString(i++, t1AttributeBuyMode.getPAYED_DIAMOND());
				ps.setString(i++, t1AttributeBuyMode.getPAYED_GOLD());
				ps.setString(i++, t1AttributeBuyMode.getDELETE_FLAG());
				
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo(final T1AttributeBuyMode t1AttributeBuyMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_attribute_buy ");
		sqlBuffer.append(" SET ID=?,USER_GAME_ID=?,TYPE=?,BUY_TIME=?,DELETE_FLAG=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, t1AttributeBuyMode.getID());
				ps.setString(i++, t1AttributeBuyMode.getUSER_GAME_ID());
				ps.setString(i++, t1AttributeBuyMode.getTYPE());
				ps.setString(i++, t1AttributeBuyMode.getBUY_TIME());
				ps.setString(i++, t1AttributeBuyMode.getDELETE_FLAG());
				ps.setString(i++, t1AttributeBuyMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public void deleteInfo(final Integer id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_attribute_buy ");
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