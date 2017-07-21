package org.jumutang.project.weixinMng.mallMng.dao.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.weixinMng.mallMng.dao.IUser_gameMngDao;
import org.jumutang.project.weixinMng.mallMng.model.GameMngMode;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;
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
public class User_gameMngDaoImpl implements IUser_gameMngDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

/*	@Override
	public int findCount(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT COUNT(1) ");
		sqlBuffer.append("	FROM t1_user_game");
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
	}*/
	
	@Override
	public List<User_gameMngMode> findList(final Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_ID, GAME_ID, CREAT_TIME, DAY_TIMES,GAME_USED_TIMES, GAME_ALL_TIMES, WEEK_SCORE, WEEK_TIME, ALL_SCORE, LAST_GAME_TIME, DELETE_FLAG,WEEK_GET_GOLD,WEEK_GET_DIAMOND,NOTE");
		sqlBuffer.append("	FROM t1_user_game");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		
		final String GAME_ID=queryParam.get("GAME_ID");
		if(!StringUtil.isEmpty(GAME_ID)){
			sqlBuffer.append(" AND GAME_ID =? ");
		}		
		sqlBuffer.append("  ORDER BY CREAT_TIME desc ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
				final String GAME_ID=queryParam.get("GAME_ID");
				if(!StringUtil.isEmpty(GAME_ID)){
					ps.setString(i++, GAME_ID);
				}		
				
			}
		}, new ResultSetExtractor<List<User_gameMngMode>>() {

			@Override
			public List<User_gameMngMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<User_gameMngMode> list=new ArrayList<User_gameMngMode>();
				while(rs.next()){
					User_gameMngMode bean= new User_gameMngMode();
					bean.setID(rs.getString("ID"));
					bean.setUSER_ID(rs.getString("USER_ID"));
					bean.setGAME_ID(rs.getString("GAME_ID"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setDAY_TIMES(rs.getString("DAY_TIMES"));
					bean.setGAME_USED_TIMES(rs.getString("GAME_USED_TIMES"));
					bean.setGAME_ALL_TIMES(rs.getString("GAME_ALL_TIMES"));
					bean.setWEEK_SCORE(rs.getString("WEEK_SCORE"));
					bean.setWEEK_TIME(rs.getString("WEEK_TIME"));
					bean.setALL_SCORE(rs.getString("ALL_SCORE"));
					bean.setLAST_GAME_TIME(rs.getString("LAST_GAME_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setWEEK_GET_GOLD(rs.getString("WEEK_GET_GOLD"));	
					bean.setWEEK_GET_DIAMOND(rs.getString("WEEK_GET_DIAMOND"));	
					bean.setNOTE(rs.getString("NOTE"));	
					bean.setREMAIN_GAME_TIMES();   //今天游戏的剩余次数
					
					list.add(bean);
				}
				
				return list;
			}
		});
	}
	
	@Override
	public List<User_gameMngMode> findListWeekLimit10(final String GAME_ID) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_ID, GAME_ID, CREAT_TIME, DAY_TIMES,GAME_USED_TIMES, GAME_ALL_TIMES, WEEK_SCORE, WEEK_TIME, ALL_SCORE, LAST_GAME_TIME, DELETE_FLAG,WEEK_GET_GOLD,WEEK_GET_DIAMOND,NOTE");
		sqlBuffer.append("	FROM t1_user_game");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append("	and GAME_ID=?");
		sqlBuffer.append("  ORDER BY ALL_SCORE desc, LAST_GAME_TIME ASC");
		sqlBuffer.append("  limit 10 ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
				ps.setString(i++, GAME_ID);
				
			}
		}, new ResultSetExtractor<List<User_gameMngMode>>() {

			@Override
			public List<User_gameMngMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<User_gameMngMode> list=new ArrayList<User_gameMngMode>();
				while(rs.next()){
					User_gameMngMode bean= new User_gameMngMode();
					bean.setID(rs.getString("ID"));
					bean.setUSER_ID(rs.getString("USER_ID"));
					bean.setGAME_ID(rs.getString("GAME_ID"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setDAY_TIMES(rs.getString("DAY_TIMES"));
					bean.setGAME_USED_TIMES(rs.getString("GAME_USED_TIMES"));
					bean.setGAME_ALL_TIMES(rs.getString("GAME_ALL_TIMES"));
					bean.setWEEK_SCORE(rs.getString("WEEK_SCORE"));
					bean.setWEEK_TIME(rs.getString("WEEK_TIME"));
					bean.setALL_SCORE(rs.getString("ALL_SCORE"));
					bean.setLAST_GAME_TIME(rs.getString("LAST_GAME_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setWEEK_GET_GOLD(rs.getString("WEEK_GET_GOLD"));	
					bean.setWEEK_GET_DIAMOND(rs.getString("WEEK_GET_DIAMOND"));	
					bean.setNOTE(rs.getString("NOTE"));	
					bean.setREMAIN_GAME_TIMES();   //今天游戏的剩余次数
					
					list.add(bean);
				}
				
				return list;
			}
		});
	}
	
	@Override
	public User_gameMngMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_ID, GAME_ID, CREAT_TIME,DAY_TIMES, GAME_USED_TIMES, GAME_ALL_TIMES, WEEK_SCORE, WEEK_TIME, ALL_SCORE, LAST_GAME_TIME, DELETE_FLAG,WEEK_GET_GOLD,WEEK_GET_DIAMOND,NOTE");
		sqlBuffer.append("	FROM t1_user_game");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<User_gameMngMode>() {

			@Override
			public User_gameMngMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					User_gameMngMode bean= new User_gameMngMode();

					bean.setID(rs.getString("ID"));
					bean.setUSER_ID(rs.getString("USER_ID"));
					bean.setGAME_ID(rs.getString("GAME_ID"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setDAY_TIMES(rs.getString("DAY_TIMES"));
					bean.setGAME_USED_TIMES(rs.getString("GAME_USED_TIMES"));
					bean.setGAME_ALL_TIMES(rs.getString("GAME_ALL_TIMES"));
					bean.setWEEK_SCORE(rs.getString("WEEK_SCORE"));
					bean.setWEEK_TIME(rs.getString("WEEK_TIME"));
					bean.setALL_SCORE(rs.getString("ALL_SCORE"));
					bean.setLAST_GAME_TIME(rs.getString("LAST_GAME_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setWEEK_GET_GOLD(rs.getString("WEEK_GET_GOLD"));	
					bean.setWEEK_GET_DIAMOND(rs.getString("WEEK_GET_DIAMOND"));	
					bean.setNOTE(rs.getString("NOTE"));	
					bean.setREMAIN_GAME_TIMES();   //今天游戏的剩余次数
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public int saveInfo(final User_gameMngMode user_gameMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t1_user_game(ID, USER_ID, GAME_ID, CREAT_TIME, DAY_TIMES,GAME_USED_TIMES, GAME_ALL_TIMES, WEEK_SCORE, WEEK_TIME, ALL_SCORE, LAST_GAME_TIME, DELETE_FLAG) ");
		sqlBuffer.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, user_gameMngMode.getID());
				ps.setString(i++, user_gameMngMode.getUSER_ID());
				ps.setString(i++, user_gameMngMode.getGAME_ID());
				ps.setString(i++, user_gameMngMode.getCREAT_TIME());
				ps.setString(i++, user_gameMngMode.getDAY_TIMES());
				ps.setString(i++, user_gameMngMode.getGAME_USED_TIMES());
				ps.setString(i++, user_gameMngMode.getGAME_ALL_TIMES());
				ps.setString(i++, user_gameMngMode.getWEEK_SCORE());
				ps.setString(i++, user_gameMngMode.getWEEK_TIME());
				ps.setString(i++, user_gameMngMode.getALL_SCORE());
				ps.setString(i++, user_gameMngMode.getLAST_GAME_TIME());
				ps.setString(i++, user_gameMngMode.getDELETE_FLAG());
				
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo(final User_gameMngMode user_gameMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_user_game ");
		sqlBuffer.append(" SET ID=?,USER_ID=?,GAME_ID=?,CREAT_TIME=?,GAME_USED_TIMES=?,GAME_ALL_TIMES=?,WEEK_SCORE=?,WEEK_TIME=?,ALL_SCORE=?,LAST_GAME_TIME=?,DELETE_FLAG=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, user_gameMngMode.getID());
				ps.setString(i++, user_gameMngMode.getUSER_ID());
				ps.setString(i++, user_gameMngMode.getGAME_ID());
				ps.setString(i++, user_gameMngMode.getCREAT_TIME());
				ps.setString(i++, user_gameMngMode.getGAME_USED_TIMES());
				ps.setString(i++, user_gameMngMode.getGAME_ALL_TIMES());
				ps.setString(i++, user_gameMngMode.getWEEK_SCORE());
				ps.setString(i++, user_gameMngMode.getWEEK_TIME());
				ps.setString(i++, user_gameMngMode.getALL_SCORE());
				ps.setString(i++, user_gameMngMode.getLAST_GAME_TIME());
				ps.setString(i++, user_gameMngMode.getDELETE_FLAG());
				ps.setString(i++, user_gameMngMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public List<User_gameMngMode> findUserGameDList(final Map<String, String> queryParam) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT TA.ID TAID,TA.USER_ID TAUSER_ID,TA.GAME_ID TAGAME_ID,TA.CREAT_TIME TACREAT_TIME,TA.DAY_TIMES TADAY_TIMES,TA.GAME_USED_TIMES TAGAME_USED_TIMES,TA.GAME_ALL_TIMES TAGAME_ALL_TIMES,TA.WEEK_SCORE TAWEEK_SCORE,TA.WEEK_TIME TAWEEK_TIME,TA.ALL_SCORE TAALL_SCORE,TA.LAST_GAME_TIME TALAST_GAME_TIME,TA.DELETE_FLAG TADELETE_FLAG ,TA.WEEK_GET_GOLD TAWEEK_GET_GOLD,TA.WEEK_GET_DIAMOND TAWEEK_GET_DIAMOND,TA.NOTE TANOTE ");
		sqlBuffer.append(" ,TB.ID TBID,TB.GAME_NAME TBGAME_NAME,TB.GAME_DETAIL TBGAME_DETAIL,TB.GAME_IMG_URL TBGAME_IMG_URL,TB.GAME_URL TBGAME_URL,TB.CREAT_TIME TBCREAT_TIME,TB.SHOW_ORDER TBSHOW_ORDER,TB.DELETE_FLAG TBDELETE_FLAG ");
		sqlBuffer.append(" ,TC.ID TCID,TC.USER_ID TCUSER_ID,TC.GAME_ID TCGAME_ID,TC.CREAT_TIME TCCREAT_TIME,TC.DELETE_FLAG TCDELETE_FLAG ");
		sqlBuffer.append(" FROM t1_user_game TA ");
		sqlBuffer.append(" INNER JOIN t_game TB ON TA.GAME_ID=TB.ID AND TB.DELETE_FLAG=0 ");
		sqlBuffer.append(" LEFT JOIN t_collection TC ON TA.GAME_ID =TC.GAME_ID AND TA.USER_ID= TC.USER_ID ");
		sqlBuffer.append(" WHERE TA.DELETE_FLAG=0 ");
		sqlBuffer.append(" AND TA.USER_ID = ? ");
		sqlBuffer.append(" ORDER BY TA.CREAT_TIME desc ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
				ps.setString(i++, queryParam.get("USER_ID"));
			}
		}, new ResultSetExtractor<List<User_gameMngMode>>() {

			@Override
			public List<User_gameMngMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<User_gameMngMode> list=new ArrayList<User_gameMngMode>();
				while(rs.next()){
					User_gameMngMode bean1= new User_gameMngMode();
					
					bean1.setID(rs.getString("TAID"));	
					bean1.setUSER_ID(rs.getString("TAUSER_ID"));	
					bean1.setGAME_ID(rs.getString("TAGAME_ID"));	
					bean1.setCREAT_TIME(rs.getString("TACREAT_TIME"));	
					bean1.setDAY_TIMES(rs.getString("TADAY_TIMES"));	
					bean1.setGAME_USED_TIMES(rs.getString("TAGAME_USED_TIMES"));	
					bean1.setGAME_ALL_TIMES(rs.getString("TAGAME_ALL_TIMES"));	
					bean1.setWEEK_SCORE(rs.getString("TAWEEK_SCORE"));	
					bean1.setWEEK_TIME(rs.getString("TAWEEK_TIME"));	
					bean1.setALL_SCORE(rs.getString("TAALL_SCORE"));	
					bean1.setLAST_GAME_TIME(rs.getString("TALAST_GAME_TIME"));	
					bean1.setDELETE_FLAG(rs.getString("TADELETE_FLAG"));	
					bean1.setWEEK_GET_GOLD(rs.getString("TAWEEK_GET_GOLD"));	
					bean1.setWEEK_GET_DIAMOND(rs.getString("TAWEEK_GET_DIAMOND"));	
					bean1.setNOTE(rs.getString("TANOTE"));	
					bean1.setREMAIN_GAME_TIMES();   //今天游戏的剩余次数
					
					GameMngMode bean2= new GameMngMode();
					bean2.setID(rs.getString("TBID"));	
					bean2.setGAME_NAME(rs.getString("TBGAME_NAME"));	
					bean2.setGAME_DETAIL(rs.getString("TBGAME_DETAIL"));	
					bean2.setGAME_IMG_URL(rs.getString("TBGAME_IMG_URL"));	
					bean2.setGAME_URL(rs.getString("TBGAME_URL"));	
					bean2.setCREAT_TIME(rs.getString("TBCREAT_TIME"));	
					bean2.setSHOW_ORDER(rs.getString("TBSHOW_ORDER"));	
					bean2.setDELETE_FLAG(rs.getString("TBDELETE_FLAG"));	
					
					String string_TBID = rs.getString("TCID");
					if(StringUtil.isEmpty(string_TBID)){
						bean2.setUSER_COLLECTION_FLAG("0");
					}else{
						bean2.setUSER_COLLECTION_FLAG("1");
					}
					bean1.setGAMEMNGMODE(bean2);
					
					list.add(bean1);
				}
				
				return list;
			}
		});
	}

	@Override
	public User_gameMngMode findInfobyUseridAndGameid(final String userid, final String gameid) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_ID, GAME_ID, CREAT_TIME, DAY_TIMES,GAME_USED_TIMES, GAME_ALL_TIMES, WEEK_SCORE, WEEK_TIME, ALL_SCORE, LAST_GAME_TIME, DELETE_FLAG,WEEK_GET_GOLD,WEEK_GET_DIAMOND,NOTE");
		sqlBuffer.append("	FROM t1_user_game");
		sqlBuffer.append("	WHERE USER_ID=?");
		sqlBuffer.append("	AND GAME_ID=?");
		sqlBuffer.append("	AND DELETE_FLAG=0");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setString(i++, userid);	
					ps.setString(i++, gameid);	
			}

		}, new ResultSetExtractor<User_gameMngMode>() {

			@Override
			public User_gameMngMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					User_gameMngMode bean= new User_gameMngMode();

					bean.setID(rs.getString("ID"));
					bean.setUSER_ID(rs.getString("USER_ID"));
					bean.setGAME_ID(rs.getString("GAME_ID"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setDAY_TIMES(rs.getString("DAY_TIMES"));
					bean.setGAME_USED_TIMES(rs.getString("GAME_USED_TIMES"));
					bean.setGAME_ALL_TIMES(rs.getString("GAME_ALL_TIMES"));
					bean.setWEEK_SCORE(rs.getString("WEEK_SCORE"));
					bean.setWEEK_TIME(rs.getString("WEEK_TIME"));
					bean.setALL_SCORE(rs.getString("ALL_SCORE"));
					bean.setLAST_GAME_TIME(rs.getString("LAST_GAME_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setWEEK_GET_GOLD(rs.getString("WEEK_GET_GOLD"));	
					bean.setWEEK_GET_DIAMOND(rs.getString("WEEK_GET_DIAMOND"));	
					bean.setNOTE(rs.getString("NOTE"));	
					bean.setREMAIN_GAME_TIMES();   //今天游戏的剩余次数
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public void updateInfo_GAME_ALL_TIMES(final User_gameMngMode user_gameMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_user_game ");
		sqlBuffer.append(" SET GAME_ALL_TIMES=? ");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, user_gameMngMode.getGAME_ALL_TIMES());
				ps.setString(i++, user_gameMngMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public void updateInfo_WEEK_SCORE_TIME(final User_gameMngMode user_gameMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_user_game ");
		sqlBuffer.append(" SET WEEK_TIME=LAST_GAME_TIME ");
		sqlBuffer.append(" , WEEK_SCORE=ALL_SCORE ");
		sqlBuffer.append(" , WEEK_GET_DIAMOND=? ");
		sqlBuffer.append(" , WEEK_GET_GOLD=? ");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, user_gameMngMode.getWEEK_GET_DIAMOND());
				ps.setString(i++, user_gameMngMode.getWEEK_GET_GOLD());
				ps.setString(i++, user_gameMngMode.getID());
				
				return ps;
			}
		});

	}
	
	@Override
	public void updateInfo_ALL_SCOREANDTIME(final User_gameMngMode user_gameMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_user_game ");
		sqlBuffer.append(" SET ALL_SCORE=? ");
		sqlBuffer.append(" , LAST_GAME_TIME=current_timestamp ");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, user_gameMngMode.getALL_SCORE());
				ps.setString(i++, user_gameMngMode.getID());
				
				return ps;
			}
		});

	}
	
}