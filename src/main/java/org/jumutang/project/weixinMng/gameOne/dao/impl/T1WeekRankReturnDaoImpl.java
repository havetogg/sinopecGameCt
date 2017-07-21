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
import org.jumutang.project.weixinMng.gameOne.dao.IT1WeekRankReturnDao;
import org.jumutang.project.weixinMng.gameOne.model.T1WeekRankReturnMode;
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
public class T1WeekRankReturnDaoImpl implements IT1WeekRankReturnDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findCount(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT COUNT(1) ");
		sqlBuffer.append("	FROM t1_weekrank_return");
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
	public List<T1WeekRankReturnMode> findList(Map<String, String> queryParam, final Page page) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_GAME_ID, TYPE, RETURN_DIAMOND, RETURN_GOLD, LASTE_WEEK_RANK, RETURN_TIME, DELETE_FLAG");
		sqlBuffer.append("	FROM t1_weekrank_return");
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
		}, new ResultSetExtractor<List<T1WeekRankReturnMode>>() {

			@Override
			public List<T1WeekRankReturnMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<T1WeekRankReturnMode> list=new ArrayList<T1WeekRankReturnMode>();
				if(page==null){
					while(rs.next()){
						T1WeekRankReturnMode bean= new T1WeekRankReturnMode();
						bean.setID(rs.getString("ID"));
						bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
						bean.setTYPE(rs.getString("TYPE"));
						bean.setRETURN_DIAMOND(rs.getString("RETURN_DIAMOND"));
						bean.setRETURN_GOLD(rs.getString("RETURN_GOLD"));
						bean.setLASTE_WEEK_RANK(rs.getString("LASTE_WEEK_RANK"));
						bean.setRETURN_TIME(rs.getString("RETURN_TIME"));
						bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
						
						list.add(bean);
					}
				}else{
					int num=0;
					if(rs.absolute(page.getStartRow()+1)){
						do{
							T1WeekRankReturnMode bean= new T1WeekRankReturnMode();
							bean.setID(rs.getString("ID"));
							bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
							bean.setTYPE(rs.getString("TYPE"));
							bean.setRETURN_DIAMOND(rs.getString("RETURN_DIAMOND"));
							bean.setRETURN_GOLD(rs.getString("RETURN_GOLD"));
							bean.setLASTE_WEEK_RANK(rs.getString("LASTE_WEEK_RANK"));
							bean.setRETURN_TIME(rs.getString("RETURN_TIME"));
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
	public T1WeekRankReturnMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_GAME_ID, TYPE, RETURN_DIAMOND, RETURN_GOLD, LASTE_WEEK_RANK, RETURN_TIME, DELETE_FLAG");
		sqlBuffer.append("	FROM t1_weekrank_return");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<T1WeekRankReturnMode>() {

			@Override
			public T1WeekRankReturnMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					T1WeekRankReturnMode bean= new T1WeekRankReturnMode();

					bean.setID(rs.getString("ID"));
					bean.setUSER_GAME_ID(rs.getString("USER_GAME_ID"));
					bean.setTYPE(rs.getString("TYPE"));
					bean.setRETURN_DIAMOND(rs.getString("RETURN_DIAMOND"));
					bean.setRETURN_GOLD(rs.getString("RETURN_GOLD"));
					bean.setLASTE_WEEK_RANK(rs.getString("LASTE_WEEK_RANK"));
					bean.setRETURN_TIME(rs.getString("RETURN_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public int saveInfo(final T1WeekRankReturnMode t1WeekRankReturnMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t1_weekrank_return(ID, USER_GAME_ID, TYPE, RETURN_DIAMOND, RETURN_GOLD, LASTE_WEEK_RANK, RETURN_TIME, DELETE_FLAG) ");
		sqlBuffer.append(" VALUES(?,?,?,?,?,?,?,?)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, t1WeekRankReturnMode.getID());
				ps.setString(i++, t1WeekRankReturnMode.getUSER_GAME_ID());
				ps.setString(i++, t1WeekRankReturnMode.getTYPE());
				ps.setString(i++, t1WeekRankReturnMode.getRETURN_DIAMOND());
				ps.setString(i++, t1WeekRankReturnMode.getRETURN_GOLD());
				ps.setString(i++, t1WeekRankReturnMode.getLASTE_WEEK_RANK());
				ps.setString(i++, t1WeekRankReturnMode.getRETURN_TIME());
				ps.setString(i++, t1WeekRankReturnMode.getDELETE_FLAG());
				
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo(final T1WeekRankReturnMode t1WeekRankReturnMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_weekrank_return ");
		sqlBuffer.append(" SET ID=?,USER_GAME_ID=?,TYPE=?,RETURN_DIAMOND=?,RETURN_GOLD=?,LASTE_WEEK_RANK=?,RETURN_TIME=?,DELETE_FLAG=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, t1WeekRankReturnMode.getID());
				ps.setString(i++, t1WeekRankReturnMode.getUSER_GAME_ID());
				ps.setString(i++, t1WeekRankReturnMode.getTYPE());
				ps.setString(i++, t1WeekRankReturnMode.getRETURN_DIAMOND());
				ps.setString(i++, t1WeekRankReturnMode.getRETURN_GOLD());
				ps.setString(i++, t1WeekRankReturnMode.getLASTE_WEEK_RANK());
				ps.setString(i++, t1WeekRankReturnMode.getRETURN_TIME());
				ps.setString(i++, t1WeekRankReturnMode.getDELETE_FLAG());
				ps.setString(i++, t1WeekRankReturnMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public void deleteInfo(final Integer id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_weekrank_return ");
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