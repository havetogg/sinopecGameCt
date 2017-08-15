package org.jumutang.project.weixinMng.mallMng.dao.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.weixinMng.mallMng.dao.IManageDao;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.RankMode;
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
public class ManageDaoImpl implements IManageDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insertMallUserInfoVolume(final MallUserMode bean) {
		int result = 0;
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t_mall_user (OPEN_ID, NICK_NAME, HEAD_IMG, MOBILE, NAME, CREAT_TIME, USED_DIAMOND, ALL_DIAMOND, USED_GOLD, ALL_GOLD, USER_RANK_ID, USER_MAX_RANK_ID, THIRD_PART_ID, JS_USER_ID, DELETE_FLAG)");
		sqlBuffer.append(" VALUES (?, ?, ?, ?, ?, current_timestamp, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		result = jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, bean.getOPEN_ID());	
				try {
					ps.setString(i++, URLEncoder.encode( bean.getNICK_NAME(), "utf-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				ps.setString(i++, bean.getHEAD_IMG());	
				ps.setString(i++, bean.getMOBILE());	
				ps.setString(i++, bean.getNAME());	
//				ps.setString(i++, bean.getCREAT_TIME());	
				ps.setString(i++, "0");	
				ps.setString(i++, "0");	
				ps.setString(i++, "0");	
				ps.setString(i++, "1000");	//初期有1000
				ps.setString(i++, "0");	
				ps.setString(i++, "0");	
				ps.setString(i++, null);	
				ps.setString(i++, null);
				ps.setString(i++, "0");
				return ps;
			}
		}, keyHolder);

		System.out.println("自动插入id============================" + keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}
	
	@Override
	public MallUserMode queryMallUserInfo(final String openid) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("  SELECT t_mall_user.ID, OPEN_ID, NICK_NAME, HEAD_IMG, MOBILE, NAME, t_mall_user.CREAT_TIME, USED_DIAMOND, ALL_DIAMOND, USED_GOLD, ALL_GOLD, USER_RANK_ID, USER_MAX_RANK_ID, THIRD_PART_ID, JS_USER_ID, t_mall_user.DELETE_FLAG ,SELF_CHANGED_ALL_DIAMOND,USER_LEVEL_SCORE,t_level.ID USER_LEVEL_ID,t_level.LEVEL_TO CURRENT_LEVEL_MAXSCORE");
		sqlBuffer.append("  FROM t_mall_user ,t_level ");
		sqlBuffer.append("  WHERE OPEN_ID = ? ");
		sqlBuffer.append("  AND USER_LEVEL_SCORE>=t_level.LEVEL_FROM  ");
		sqlBuffer.append("  AND USER_LEVEL_SCORE<t_level.LEVEL_TO ");
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
				ps.setString(i++, openid);
			}

		}, new ResultSetExtractor<MallUserMode>() {

			@Override
			public MallUserMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					MallUserMode bean = new MallUserMode();
					bean.setID(rs.getString("ID"));	
					bean.setOPEN_ID(rs.getString("OPEN_ID"));	
					bean.setNICK_NAME(rs.getString("NICK_NAME"));	
					bean.setHEAD_IMG(rs.getString("HEAD_IMG"));	
					bean.setMOBILE(rs.getString("MOBILE"));	
					bean.setNAME(rs.getString("NAME"));	
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));	
					bean.setUSED_DIAMOND(rs.getString("USED_DIAMOND"));	
					bean.setALL_DIAMOND(rs.getString("ALL_DIAMOND"));	
					bean.setUSED_GOLD(rs.getString("USED_GOLD"));	
					bean.setALL_GOLD(rs.getString("ALL_GOLD"));	
					bean.setSELF_CHANGED_ALL_DIAMOND(rs.getString("SELF_CHANGED_ALL_DIAMOND"));	
					bean.setUSER_RANK_ID(rs.getString("USER_RANK_ID"));	
					bean.setUSER_MAX_RANK_ID(rs.getString("USER_MAX_RANK_ID"));	
					bean.setTHIRD_PART_ID(rs.getString("THIRD_PART_ID"));
					bean.setJS_USER_ID(rs.getString("JS_USER_ID"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));	
					bean.setUSER_LEVEL_SCORE(rs.getString("USER_LEVEL_SCORE"));	
					
					bean.setCURRENT_LEVEL_MAXSCORE(rs.getString("CURRENT_LEVEL_MAXSCORE"));	
					bean.setUSER_LEVEL_ID(rs.getString("USER_LEVEL_ID"));	
					
					
					bean.setREMAIN_DIAMOND(null); // 剩余钻石
					bean.setREMAIN_GOLD(null);    // 剩余金币
					return bean;
				}
				return null;
			}
		});
	}

	@Override
	public MallUserMode queryMallUserInfoByMobile(String mobile) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("  SELECT t_mall_user.ID, OPEN_ID, NICK_NAME, HEAD_IMG, MOBILE, NAME, t_mall_user.CREAT_TIME, USED_DIAMOND, ALL_DIAMOND, USED_GOLD, ALL_GOLD, USER_RANK_ID, USER_MAX_RANK_ID, THIRD_PART_ID, JS_USER_ID, t_mall_user.DELETE_FLAG ,SELF_CHANGED_ALL_DIAMOND,USER_LEVEL_SCORE,t_level.ID USER_LEVEL_ID,t_level.LEVEL_TO CURRENT_LEVEL_MAXSCORE");
		sqlBuffer.append("  FROM t_mall_user ,t_level ");
		sqlBuffer.append("  WHERE MOBILE = ? ");
		sqlBuffer.append("  AND USER_LEVEL_SCORE>=t_level.LEVEL_FROM  ");
		sqlBuffer.append("  AND USER_LEVEL_SCORE<t_level.LEVEL_TO ");
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
				ps.setString(i++, mobile);
			}

		}, new ResultSetExtractor<MallUserMode>() {

			@Override
			public MallUserMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					MallUserMode bean = new MallUserMode();
					bean.setID(rs.getString("ID"));
					bean.setOPEN_ID(rs.getString("OPEN_ID"));
					bean.setNICK_NAME(rs.getString("NICK_NAME"));
					bean.setHEAD_IMG(rs.getString("HEAD_IMG"));
					bean.setMOBILE(rs.getString("MOBILE"));
					bean.setNAME(rs.getString("NAME"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setUSED_DIAMOND(rs.getString("USED_DIAMOND"));
					bean.setALL_DIAMOND(rs.getString("ALL_DIAMOND"));
					bean.setUSED_GOLD(rs.getString("USED_GOLD"));
					bean.setALL_GOLD(rs.getString("ALL_GOLD"));
					bean.setSELF_CHANGED_ALL_DIAMOND(rs.getString("SELF_CHANGED_ALL_DIAMOND"));
					bean.setUSER_RANK_ID(rs.getString("USER_RANK_ID"));
					bean.setUSER_MAX_RANK_ID(rs.getString("USER_MAX_RANK_ID"));
					bean.setTHIRD_PART_ID(rs.getString("THIRD_PART_ID"));
					bean.setJS_USER_ID(rs.getString("JS_USER_ID"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setUSER_LEVEL_SCORE(rs.getString("USER_LEVEL_SCORE"));

					bean.setCURRENT_LEVEL_MAXSCORE(rs.getString("CURRENT_LEVEL_MAXSCORE"));
					bean.setUSER_LEVEL_ID(rs.getString("USER_LEVEL_ID"));


					bean.setREMAIN_DIAMOND(null); // 剩余钻石
					bean.setREMAIN_GOLD(null);    // 剩余金币
					return bean;
				}
				return null;
			}
		});
	}

	@Override
	public MallUserMode queryMallUserInfo_byId(final String id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("  SELECT t_mall_user.ID, OPEN_ID, NICK_NAME, HEAD_IMG, MOBILE, NAME, t_mall_user.CREAT_TIME, USED_DIAMOND, ALL_DIAMOND, USED_GOLD, ALL_GOLD, USER_RANK_ID, USER_MAX_RANK_ID, THIRD_PART_ID, JS_USER_ID, t_mall_user.DELETE_FLAG ,SELF_CHANGED_ALL_DIAMOND,USER_LEVEL_SCORE,t_level.ID USER_LEVEL_ID,t_level.LEVEL_TO CURRENT_LEVEL_MAXSCORE");
		sqlBuffer.append("  FROM t_mall_user ,t_level ");
		sqlBuffer.append("  WHERE ID = ? ");
		sqlBuffer.append("  AND USER_LEVEL_SCORE>=t_level.LEVEL_FROM  ");
		sqlBuffer.append("  AND USER_LEVEL_SCORE<t_level.LEVEL_TO ");
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
				ps.setString(i++, id);
			}

		}, new ResultSetExtractor<MallUserMode>() {

			@Override
			public MallUserMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					MallUserMode bean = new MallUserMode();
					bean.setID(rs.getString("ID"));
					bean.setOPEN_ID(rs.getString("OPEN_ID"));
					bean.setNICK_NAME(rs.getString("NICK_NAME"));
					bean.setHEAD_IMG(rs.getString("HEAD_IMG"));
					bean.setMOBILE(rs.getString("MOBILE"));
					bean.setNAME(rs.getString("NAME"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setUSED_DIAMOND(rs.getString("USED_DIAMOND"));
					bean.setALL_DIAMOND(rs.getString("ALL_DIAMOND"));
					bean.setUSED_GOLD(rs.getString("USED_GOLD"));
					bean.setALL_GOLD(rs.getString("ALL_GOLD"));
					bean.setSELF_CHANGED_ALL_DIAMOND(rs.getString("SELF_CHANGED_ALL_DIAMOND"));
					bean.setUSER_RANK_ID(rs.getString("USER_RANK_ID"));
					bean.setUSER_MAX_RANK_ID(rs.getString("USER_MAX_RANK_ID"));
					bean.setTHIRD_PART_ID(rs.getString("THIRD_PART_ID"));
					bean.setJS_USER_ID(rs.getString("JS_USER_ID"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setUSER_LEVEL_SCORE(rs.getString("USER_LEVEL_SCORE"));

					bean.setCURRENT_LEVEL_MAXSCORE(rs.getString("CURRENT_LEVEL_MAXSCORE"));
					bean.setUSER_LEVEL_ID(rs.getString("USER_LEVEL_ID"));


					bean.setREMAIN_DIAMOND(null); // 剩余钻石
					bean.setREMAIN_GOLD(null);    // 剩余金币
					return bean;
				}
				return null;
			}
		});
	}

	@Override
	public MallUserMode queryMallUserInfo_byJSUSERID(final String jsUserId) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("  SELECT t_mall_user.ID, OPEN_ID, NICK_NAME, HEAD_IMG, MOBILE, NAME, t_mall_user.CREAT_TIME, USED_DIAMOND, ALL_DIAMOND, USED_GOLD, ALL_GOLD, USER_RANK_ID, USER_MAX_RANK_ID, THIRD_PART_ID, JS_USER_ID, t_mall_user.DELETE_FLAG ,SELF_CHANGED_ALL_DIAMOND,USER_LEVEL_SCORE,t_level.ID USER_LEVEL_ID,t_level.LEVEL_TO CURRENT_LEVEL_MAXSCORE");
		sqlBuffer.append("  FROM t_mall_user ,t_level ");
		sqlBuffer.append("  WHERE JS_USER_ID = ? ");
		sqlBuffer.append("  AND USER_LEVEL_SCORE>=t_level.LEVEL_FROM  ");
		sqlBuffer.append("  AND USER_LEVEL_SCORE<t_level.LEVEL_TO ");
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
				ps.setString(i++, jsUserId);
			}

		}, new ResultSetExtractor<MallUserMode>() {

			@Override
			public MallUserMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					MallUserMode bean = new MallUserMode();
					bean.setID(rs.getString("ID"));
					bean.setOPEN_ID(rs.getString("OPEN_ID"));
					bean.setNICK_NAME(rs.getString("NICK_NAME"));
					bean.setHEAD_IMG(rs.getString("HEAD_IMG"));
					bean.setMOBILE(rs.getString("MOBILE"));
					bean.setNAME(rs.getString("NAME"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setUSED_DIAMOND(rs.getString("USED_DIAMOND"));
					bean.setALL_DIAMOND(rs.getString("ALL_DIAMOND"));
					bean.setUSED_GOLD(rs.getString("USED_GOLD"));
					bean.setALL_GOLD(rs.getString("ALL_GOLD"));
					bean.setSELF_CHANGED_ALL_DIAMOND(rs.getString("SELF_CHANGED_ALL_DIAMOND"));
					bean.setUSER_RANK_ID(rs.getString("USER_RANK_ID"));
					bean.setUSER_MAX_RANK_ID(rs.getString("USER_MAX_RANK_ID"));
					bean.setTHIRD_PART_ID(rs.getString("THIRD_PART_ID"));
					bean.setJS_USER_ID(rs.getString("JS_USER_ID"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setUSER_LEVEL_SCORE(rs.getString("USER_LEVEL_SCORE"));

					bean.setCURRENT_LEVEL_MAXSCORE(rs.getString("CURRENT_LEVEL_MAXSCORE"));
					bean.setUSER_LEVEL_ID(rs.getString("USER_LEVEL_ID"));


					bean.setREMAIN_DIAMOND(null); // 剩余钻石
					bean.setREMAIN_GOLD(null);    // 剩余金币
					return bean;
				}
				return null;
			}
		});
	}

	@Override
	public void update_UserInfo_login(final MallUserMode bean) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_mall_user set  ");
		sqlBuffer.append(" MOBILE=? ");
		sqlBuffer.append(" ,THIRD_PART_ID=?");
		sqlBuffer.append(" where OPEN_ID =  ?  ");
				;
		jdbcTemplate.update(sqlBuffer.toString(),new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i=1;
				ps.setString(i++, bean.getMOBILE());
				ps.setString(i++, bean.getTHIRD_PART_ID());
				ps.setString(i++, bean.getOPEN_ID());
			}
		});
	}
	
	@Override
	public void update_UserInfo_ToGold(final MallUserMode bean) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_mall_user  ");
		sqlBuffer.append(" SET USED_DIAMOND = ?  ");
		sqlBuffer.append(" , ALL_GOLD = ?  ");
		sqlBuffer.append(" where OPEN_ID =  ?  ");
				;
		jdbcTemplate.update(sqlBuffer.toString(),new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i=1;
				ps.setString(i++, bean.getUSED_DIAMOND());
				ps.setString(i++, bean.getALL_GOLD());
				ps.setString(i++, bean.getOPEN_ID());
			}
		});
	}
	
	@Override
	public void update_UserInfo_Used_diamond(final MallUserMode bean) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_mall_user  ");
		sqlBuffer.append(" SET USED_DIAMOND = ?  ");
		sqlBuffer.append(" where OPEN_ID =  ?  ");
				;
		jdbcTemplate.update(sqlBuffer.toString(),new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i=1;
				ps.setString(i++, bean.getUSED_DIAMOND());
				ps.setString(i++, bean.getOPEN_ID());
			}
		});
	}
	
	@Override
	public void update_UserInfo_Used_gold(final MallUserMode bean) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_mall_user  ");
		sqlBuffer.append(" SET USED_GOLD = ?  ");
		sqlBuffer.append(" where OPEN_ID =  ?  ");
				;
		jdbcTemplate.update(sqlBuffer.toString(),new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i=1;
				ps.setString(i++, bean.getUSED_GOLD());
				ps.setString(i++, bean.getOPEN_ID());
			}
		});
	}

	@Override
	public void update_User_DiamondANDGold(final MallUserMode bean) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_mall_user  ");
		sqlBuffer.append(" SET ALL_DIAMOND = ?  ");
		sqlBuffer.append(" , ALL_GOLD = ?  ");
		sqlBuffer.append(" where OPEN_ID =  ?  ");
				;
		jdbcTemplate.update(sqlBuffer.toString(),new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i=1;
				ps.setString(i++, bean.getALL_DIAMOND());
				ps.setString(i++, bean.getALL_GOLD());
				ps.setString(i++, bean.getOPEN_ID());
			}
		});
	}

	@Override
	public void collect_add(final MallUserMode bean,final  String gameId) {
		final StringBuffer buffer=new StringBuffer();
		buffer.append(" SELECT ID, USER_ID, GAME_ID, CREAT_TIME, DELETE_FLAG ");  
		buffer.append(" FROM t_collection ");         
		buffer.append(" WHERE USER_ID=? ");   
		buffer.append(" AND GAME_ID=? ");   

		final String collection_id=jdbcTemplate.query(buffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i =1;
				ps.setString(i++, bean.getID());	
				ps.setString(i++, gameId);	
			}

		}, new ResultSetExtractor<String>() {

			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getString("ID");
				}
				return null;
			}
		});
		
		if(StringUtil.isEmpty(collection_id)){
			buffer.delete(0, buffer.length());
			buffer.append(" INSERT INTO t_collection (USER_ID, GAME_ID, CREAT_TIME, DELETE_FLAG)");
			buffer.append(" VALUES ( ?, ?, current_timestamp, ?) ");
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int result = jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(buffer.toString(), Statement.RETURN_GENERATED_KEYS);
					int i = 1;
					ps.setString(i++, bean.getID());	
					ps.setString(i++, gameId);	
					ps.setString(i++, "0");	
					return ps;
				}
			}, keyHolder);
		}

		//System.out.println("自动插入id============================" + keyHolder.getKey().intValue());
	}
	
	@Override
	public void collect_del(final MallUserMode bean,final  String gameId) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" DELETE FROM t_collection ");
		sqlBuffer.append(" WHERE USER_ID = ? ");
		sqlBuffer.append(" AND GAME_ID = ?  ");
				;
		jdbcTemplate.update(sqlBuffer.toString(),new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i=1;
				ps.setString(i++, bean.getID());
				ps.setString(i++, gameId);
			}
		});
	}

	@Override
	public List<MallUserMode> findUserList(final Map<String, String> amap) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT TA.ID TAID,TA.OPEN_ID TAOPEN_ID,TA.NICK_NAME TANICK_NAME,TA.HEAD_IMG TAHEAD_IMG,TA.MOBILE TAMOBILE,TA.NAME TANAME,TA.CREAT_TIME TACREAT_TIME,TA.USED_DIAMOND TAUSED_DIAMOND,TA.ALL_DIAMOND TAALL_DIAMOND,TA.USED_GOLD TAUSED_GOLD,TA.ALL_GOLD TAALL_GOLD,TA.USER_RANK_ID TAUSER_RANK_ID,TA.USER_MAX_RANK_ID TAUSER_MAX_RANK_ID,TA.THIRD_PART_ID TATHIRD_PART_ID, TA.JS_USER_ID JS_USER_ID,TA.DELETE_FLAG TADELETE_FLAG,TA.SELF_CHANGED_ALL_DIAMOND TASELF_CHANGED_ALL_DIAMOND,TA.USER_LEVEL_SCORE TAUSER_LEVEL_SCORE");
		sqlBuffer.append(" ,TB.ID TBID,TB.RANK_NAME TBRANK_NAME,TB.RETURN_DIAMOND TBRETURN_DIAMOND,TB.RETURN_GOLD TBRETURN_GOLD,TB.CREAT_TIME TBCREAT_TIME,TB.DELETE_FLAG TBDELETE_FLAG ");
		sqlBuffer.append(" FROM t_mall_user TA ");
		sqlBuffer.append(" inner JOIN t_rank TB ON  TA.USER_RANK_ID=TB.ID ");
		sqlBuffer.append(" where TA.DELETE_FLAG=0 ");
		sqlBuffer.append(" and TA.USER_RANK_ID >0 ");


		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
//				if(!StringUtil.isEmpty(QUERY_NAME)){
//					ps.setString(i++, QUERY_NAME);
//				}
			}
		}, new ResultSetExtractor<List<MallUserMode>>() {

			@Override
			public List<MallUserMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<MallUserMode> list=new ArrayList<MallUserMode>();
				while(rs.next()){
					MallUserMode bean1= new MallUserMode();

					bean1.setID(rs.getString("TAID"));	
					bean1.setOPEN_ID(rs.getString("TAOPEN_ID"));	
					bean1.setNICK_NAME(rs.getString("TANICK_NAME"));	
					bean1.setHEAD_IMG(rs.getString("TAHEAD_IMG"));	
					bean1.setMOBILE(rs.getString("TAMOBILE"));	
					bean1.setNAME(rs.getString("TANAME"));	
					bean1.setCREAT_TIME(rs.getString("TACREAT_TIME"));	
					bean1.setUSED_DIAMOND(rs.getString("TAUSED_DIAMOND"));	
					bean1.setALL_DIAMOND(rs.getString("TAALL_DIAMOND"));	
					bean1.setUSED_GOLD(rs.getString("TAUSED_GOLD"));	
					bean1.setALL_GOLD(rs.getString("TAALL_GOLD"));	
					bean1.setSELF_CHANGED_ALL_DIAMOND(rs.getString("TASELF_CHANGED_ALL_DIAMOND"));	
					bean1.setUSER_RANK_ID(rs.getString("TAUSER_RANK_ID"));	
					bean1.setUSER_MAX_RANK_ID(rs.getString("TAUSER_MAX_RANK_ID"));	
					bean1.setTHIRD_PART_ID(rs.getString("TATHIRD_PART_ID"));
					bean1.setJS_USER_ID(rs.getString("JS_USER_ID"));
					bean1.setDELETE_FLAG(rs.getString("TADELETE_FLAG"));	
					bean1.setUSER_LEVEL_SCORE(rs.getString("TAUSER_LEVEL_SCORE"));	
					
					RankMode bean2 = new RankMode();
					bean2.setID(rs.getString("TBID"));	
					bean2.setRANK_NAME(rs.getString("TBRANK_NAME"));	
					bean2.setRETURN_DIAMOND(rs.getString("TBRETURN_DIAMOND"));	
					bean2.setRETURN_GOLD(rs.getString("TBRETURN_GOLD"));	
					bean2.setCREAT_TIME(rs.getString("TBCREAT_TIME"));	
					bean2.setDELETE_FLAG(rs.getString("TBDELETE_FLAG"));	
					
					bean1.setRANKMODE(bean2);
					list.add(bean1);
				}
				
				return list;
			}
		});
	}

	@Override
	public void updateVipMonthRankDown(final MallUserMode bean) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_mall_user  ");
		sqlBuffer.append(" SET USER_RANK_ID = USER_RANK_ID-1  ");
		sqlBuffer.append(" where OPEN_ID =  ?  ");
				;
		jdbcTemplate.update(sqlBuffer.toString(),new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i=1;
				ps.setString(i++, bean.getOPEN_ID());
			}
		});
	}

	@Override
	public void update_UserInfo_ToGold(final String userid, final String diamond_numb) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_mall_user  ");
		sqlBuffer.append(" SET ALL_DIAMOND = ALL_DIAMOND+?  ");
		sqlBuffer.append(" , SELF_CHANGED_ALL_DIAMOND = SELF_CHANGED_ALL_DIAMOND+?  ");
		sqlBuffer.append(" where ID =  ?  ");
				;
		jdbcTemplate.update(sqlBuffer.toString(),new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i=1;
				ps.setString(i++, diamond_numb);
				ps.setString(i++, diamond_numb);
				ps.setString(i++, userid);
			}
		});
	}

	@Override
	public int  UpdateDiamond(MallUserMode mallUserMode) {

		StringBuffer sqlBuffer =  new StringBuffer();
		sqlBuffer.append(" update t_mall_user  ");
		sqlBuffer.append(" set ALL_DIAMOND = ALL_DIAMOND + ? ");
		sqlBuffer.append(" where 1=1  ");
		if(StringUtils.isNotBlank(mallUserMode.getOPEN_ID())){
			sqlBuffer.append("  and open_id = ? ");

			int updateCount = jdbcTemplate.update(sqlBuffer.toString(),new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					int i=1;
					ps.setString(i++, mallUserMode.getALL_DIAMOND());
					ps.setString(i++, mallUserMode.getOPEN_ID());
				}
			});

			return updateCount;

		}

		return 0;

	}

	@Override
	public void update_UserInfo_Lv(final String userid,final String user_RANK_ID, final String user_MAX_RANK_ID) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_mall_user  ");
		sqlBuffer.append(" SET USER_RANK_ID = ?  ");
		sqlBuffer.append(" , USER_MAX_RANK_ID = ?  ");
		sqlBuffer.append(" where ID =  ?  ");
				;
		jdbcTemplate.update(sqlBuffer.toString(),new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i=1;
				ps.setString(i++, user_RANK_ID);
				ps.setString(i++, user_MAX_RANK_ID);
				ps.setString(i++, userid);
			}
		});
	}
	

	@Override
	public void updateInfo_USER_LEVEL_SCORE(final MallUserMode bean) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_mall_user ");
		sqlBuffer.append(" SET USER_LEVEL_SCORE=? ");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, bean.getUSER_LEVEL_SCORE());
				ps.setString(i++, bean.getID());
				
				return ps;
			}
		});

	}

}
