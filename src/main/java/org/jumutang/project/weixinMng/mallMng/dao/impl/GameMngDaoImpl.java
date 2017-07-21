package org.jumutang.project.weixinMng.mallMng.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.weixinMng.mallMng.dao.IGameMngDao;
import org.jumutang.project.weixinMng.mallMng.model.GameMngMode;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class GameMngDaoImpl implements IGameMngDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public List<GameMngMode> findList(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, GAME_NAME, GAME_DETAIL,GAME_IMG_URL, GAME_URL, CREAT_TIME, SHOW_ORDER, DELETE_FLAG");
		sqlBuffer.append("	FROM t_game");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append(" ORDER BY SHOW_ORDER ASC ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
//				ps.setString(i++, QUERY_NAME);
			}
		}, new ResultSetExtractor<List<GameMngMode>>() {

			@Override
			public List<GameMngMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<GameMngMode> list=new ArrayList<GameMngMode>();
				while(rs.next()){
					GameMngMode bean= new GameMngMode();
					bean.setID(rs.getString("ID"));
					bean.setGAME_NAME(rs.getString("GAME_NAME"));
					bean.setGAME_DETAIL(rs.getString("GAME_DETAIL"));
					bean.setGAME_URL(rs.getString("GAME_URL"));
					bean.setGAME_IMG_URL(rs.getString("GAME_IMG_URL"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setSHOW_ORDER(rs.getString("SHOW_ORDER"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					
					list.add(bean);
				}
				
				return list;
			}
		});
	}
	
	@Override
	public GameMngMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, GAME_NAME, GAME_DETAIL,GAME_IMG_URL, GAME_URL, CREAT_TIME, SHOW_ORDER, DELETE_FLAG");
		sqlBuffer.append("	FROM t_game");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<GameMngMode>() {

			@Override
			public GameMngMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					GameMngMode bean= new GameMngMode();

					bean.setID(rs.getString("ID"));
					bean.setGAME_NAME(rs.getString("GAME_NAME"));
					bean.setGAME_DETAIL(rs.getString("GAME_DETAIL"));
					bean.setGAME_URL(rs.getString("GAME_URL"));
					bean.setGAME_IMG_URL(rs.getString("GAME_IMG_URL"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
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
	public List<GameMngMode> findList_collection(final Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT TA.ID TAID,TA.GAME_NAME TAGAME_NAME,TA.GAME_DETAIL TAGAME_DETAIL,TA.GAME_IMG_URL TAGAME_IMG_URL,TA.GAME_URL TAGAME_URL,TA.CREAT_TIME TACREAT_TIME,TA.SHOW_ORDER TASHOW_ORDER,TA.DELETE_FLAG TADELETE_FLAG ");
		sqlBuffer.append("	,TB.ID TBID,TB.USER_ID TBUSER_ID,TB.GAME_ID TBGAME_ID,TB.CREAT_TIME TBCREAT_TIME,TB.DELETE_FLAG TBDELETE_FLAG ");
        sqlBuffer.append("	FROM t_game TA ");
        sqlBuffer.append("	LEFT JOIN t_collection TB ON TA.ID = TB.GAME_ID AND TB.USER_ID=? ");
		sqlBuffer.append("	WHERE TA.DELETE_FLAG=0");
		sqlBuffer.append(" ORDER BY TA.SHOW_ORDER ASC ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
				ps.setString(i++, queryParam.get("USER_ID"));
			}
		}, new ResultSetExtractor<List<GameMngMode>>() {

			@Override
			public List<GameMngMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<GameMngMode> list=new ArrayList<GameMngMode>();
				while(rs.next()){
					GameMngMode bean= new GameMngMode();
					bean.setID(rs.getString("TAID"));	
					bean.setGAME_NAME(rs.getString("TAGAME_NAME"));	
					bean.setGAME_DETAIL(rs.getString("TAGAME_DETAIL"));	
					bean.setGAME_IMG_URL(rs.getString("TAGAME_IMG_URL"));	
					bean.setGAME_URL(rs.getString("TAGAME_URL"));	
					bean.setCREAT_TIME(rs.getString("TACREAT_TIME"));	
					bean.setSHOW_ORDER(rs.getString("TASHOW_ORDER"));	
					bean.setDELETE_FLAG(rs.getString("TADELETE_FLAG"));	
					String string_TBID = rs.getString("TBID");
					if(StringUtil.isEmpty(string_TBID)){
						bean.setUSER_COLLECTION_FLAG("0");
					}else{
						bean.setUSER_COLLECTION_FLAG("1");
					}
					
					list.add(bean);
				}
				
				return list;
			}
		});
	}

	@Override
	public List<GameMngMode> find_MycollectionList(final Map<String, String> queryParam) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT TA.ID TAID,TA.GAME_NAME TAGAME_NAME,TA.GAME_DETAIL TAGAME_DETAIL,TA.GAME_IMG_URL TAGAME_IMG_URL,TA.GAME_URL TAGAME_URL,TA.CREAT_TIME TACREAT_TIME,TA.SHOW_ORDER TASHOW_ORDER,TA.DELETE_FLAG TADELETE_FLAG ");
		sqlBuffer.append("	,TB.ID TBID,TB.USER_ID TBUSER_ID,TB.GAME_ID TBGAME_ID,TB.CREAT_TIME TBCREAT_TIME,TB.DELETE_FLAG TBDELETE_FLAG ");
        sqlBuffer.append("	FROM t_game TA ");
        sqlBuffer.append("	LEFT JOIN t_collection TB ON TA.ID = TB.GAME_ID AND TB.USER_ID=? ");
		sqlBuffer.append("	WHERE TA.DELETE_FLAG=0 ");
		sqlBuffer.append("	AND TB.ID IS NOT NULL ");
		sqlBuffer.append(" ORDER BY TA.SHOW_ORDER ASC ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
				ps.setString(i++, queryParam.get("USER_ID"));
			}
		}, new ResultSetExtractor<List<GameMngMode>>() {

			@Override
			public List<GameMngMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<GameMngMode> list=new ArrayList<GameMngMode>();
				while(rs.next()){
					GameMngMode bean= new GameMngMode();
					bean.setID(rs.getString("TAID"));	
					bean.setGAME_NAME(rs.getString("TAGAME_NAME"));	
					bean.setGAME_DETAIL(rs.getString("TAGAME_DETAIL"));	
					bean.setGAME_IMG_URL(rs.getString("TAGAME_IMG_URL"));	
					bean.setGAME_URL(rs.getString("TAGAME_URL"));	
					bean.setCREAT_TIME(rs.getString("TACREAT_TIME"));	
					bean.setSHOW_ORDER(rs.getString("TASHOW_ORDER"));	
					bean.setDELETE_FLAG(rs.getString("TADELETE_FLAG"));	
					String string_TBID = rs.getString("TBID");
					if(StringUtil.isEmpty(string_TBID)){
						bean.setUSER_COLLECTION_FLAG("0");
					}else{
						bean.setUSER_COLLECTION_FLAG("1");
					}
					
					list.add(bean);
				}
				
				return list;
			}
		});
	}

	@Override
	public List<User_gameMngMode> find_rand_byGameId(final Map<String, String> queryParam) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT TA.ID TAID,TA.USER_ID TAUSER_ID,TA.GAME_ID TAGAME_ID,TA.CREAT_TIME TACREAT_TIME,TA.GAME_USED_TIMES TAGAME_USED_TIMES,TA.GAME_ALL_TIMES TAGAME_ALL_TIMES,TA.WEEK_SCORE TAWEEK_SCORE,TA.WEEK_TIME TAWEEK_TIME,TA.ALL_SCORE TAALL_SCORE,TA.LAST_GAME_TIME TALAST_GAME_TIME,TA.DELETE_FLAG TADELETE_FLAG,TA.WEEK_GET_GOLD TAWEEK_GET_GOLD,TA.WEEK_GET_DIAMOND TAWEEK_GET_DIAMOND,TA.NOTE TANOTE ");
		sqlBuffer.append(" ,TB.ID TBID,TB.GAME_NAME TBGAME_NAME,TB.GAME_DETAIL TBGAME_DETAIL,TB.GAME_IMG_URL TBGAME_IMG_URL,TB.GAME_URL TBGAME_URL,TB.CREAT_TIME TBCREAT_TIME,TB.SHOW_ORDER TBSHOW_ORDER,TB.DELETE_FLAG TBDELETE_FLAG ");
		sqlBuffer.append(" ,TC.ID TCID,TC.OPEN_ID TCOPEN_ID,TC.NICK_NAME TCNICK_NAME,TC.HEAD_IMG TCHEAD_IMG,TC.MOBILE TCMOBILE,TC.NAME TCNAME,TC.CREAT_TIME TCCREAT_TIME,TC.USED_DIAMOND TCUSED_DIAMOND,TC.ALL_DIAMOND TCALL_DIAMOND,TC.USED_GOLD TCUSED_GOLD,TC.ALL_GOLD TCALL_GOLD,TC.USER_RANK_ID TCUSER_RANK_ID,TC.USER_MAX_RANK_ID TCUSER_MAX_RANK_ID,TC.THIRD_PART_ID TCTHIRD_PART_ID,TC.DELETE_FLAG TCDELETE_FLAG,TC.SELF_CHANGED_ALL_DIAMOND TCSELF_CHANGED_ALL_DIAMOND,TC.USER_LEVEL_SCORE TCUSER_LEVEL_SCORE ");
		sqlBuffer.append(" FROM t1_user_game TA ");
		sqlBuffer.append(" INNER JOIN t_game TB ON TA.GAME_ID=TB.ID AND TB.DELETE_FLAG=0 ");
		sqlBuffer.append(" INNER JOIN t_mall_user TC ON TA.USER_ID=TC.ID AND TC.DELETE_FLAG=0 ");
		sqlBuffer.append(" WHERE TA.DELETE_FLAG=0 ");
		sqlBuffer.append(" AND TA.GAME_ID = ? ");
		sqlBuffer.append(" AND TA.WEEK_TIME IS NOT NULL ");
		sqlBuffer.append(" ORDER BY TA.WEEK_SCORE desc, TA.WEEK_TIME asc");
		sqlBuffer.append(" limit 10");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
				ps.setString(i++, queryParam.get("GAME_ID"));
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
					
					GameMngMode bean2= new GameMngMode();
					bean2.setID(rs.getString("TBID"));	
					bean2.setGAME_NAME(rs.getString("TBGAME_NAME"));	
					bean2.setGAME_DETAIL(rs.getString("TBGAME_DETAIL"));	
					bean2.setGAME_IMG_URL(rs.getString("TBGAME_IMG_URL"));	
					bean2.setGAME_URL(rs.getString("TBGAME_URL"));	
					bean2.setCREAT_TIME(rs.getString("TBCREAT_TIME"));	
					bean2.setSHOW_ORDER(rs.getString("TBSHOW_ORDER"));	
					bean2.setDELETE_FLAG(rs.getString("TBDELETE_FLAG"));	
					bean1.setGAMEMNGMODE(bean2);
					
					MallUserMode bean3 = new MallUserMode();
					bean3.setID(rs.getString("TCID"));	
					bean3.setOPEN_ID(rs.getString("TCOPEN_ID"));	
					bean3.setNICK_NAME(rs.getString("TCNICK_NAME"));	
					bean3.setHEAD_IMG(rs.getString("TCHEAD_IMG"));	
					bean3.setMOBILE(rs.getString("TCMOBILE"));	
					bean3.setNAME(rs.getString("TCNAME"));	
					bean3.setCREAT_TIME(rs.getString("TCCREAT_TIME"));	
					bean3.setUSED_DIAMOND(rs.getString("TCUSED_DIAMOND"));	
					bean3.setALL_DIAMOND(rs.getString("TCALL_DIAMOND"));	
					bean3.setUSED_GOLD(rs.getString("TCUSED_GOLD"));	
					bean3.setALL_GOLD(rs.getString("TCALL_GOLD"));	
					bean3.setSELF_CHANGED_ALL_DIAMOND(rs.getString("TCSELF_CHANGED_ALL_DIAMOND"));	
					bean3.setUSER_RANK_ID(rs.getString("TCUSER_RANK_ID"));	
					bean3.setUSER_MAX_RANK_ID(rs.getString("TCUSER_MAX_RANK_ID"));	
					bean3.setTHIRD_PART_ID(rs.getString("TCTHIRD_PART_ID"));	
					bean3.setDELETE_FLAG(rs.getString("TCDELETE_FLAG"));	
					bean3.setUSER_LEVEL_SCORE(rs.getString("TCUSER_LEVEL_SCORE"));	
					
					bean1.setMALLUSERMODE(bean3);
					
					list.add(bean1);
				}
				
				return list;
			}
		});
	}


}