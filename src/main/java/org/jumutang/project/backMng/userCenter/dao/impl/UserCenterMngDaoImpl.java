package org.jumutang.project.backMng.userCenter.dao.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.userCenter.dao.IUserCenterMngDao;
import org.jumutang.project.backMng.userCenter.model.UserCenterMngMode;
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
public class UserCenterMngDaoImpl implements IUserCenterMngDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findCount(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT tw.ID, tw.NICK_NAME, tw.SEX, tw.EMAIL, tw.MOBILE, tw.PASSWORD, tw.BIRTH_YEAR, tw.HEAD_IMG_URL, tw.OPEN_ID, tw.CREATE_TIME, tw.LAST_UPDATE_TIME, tw.DELETE_FLAG,twb.CARD_NO");
		sqlBuffer.append("	FROM T_WXUSER tw LEFT JOIN T_WXUSER_BANKCARD twb ");
		sqlBuffer.append("  ON tw.ID = twb.WXUSER_ID ");
		sqlBuffer.append("	WHERE tw.DELETE_FLAG=0 ");
		
		final String QUERY_NAME=queryParam.get("QUERY_NAME");
		if(!StringUtil.isEmpty(QUERY_NAME)){
			sqlBuffer.append(" AND tw.NICK_NAME like CONCAT('%',?,'%') ");
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
	public List<UserCenterMngMode> findList(Map<String, String> queryParam, final Page page) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT tw.ID, tw.NICK_NAME, tw.SEX, tw.EMAIL, tw.MOBILE, tw.PASSWORD, tw.BIRTH_YEAR, tw.HEAD_IMG_URL, tw.OPEN_ID, tw.CREATE_TIME, tw.LAST_UPDATE_TIME, tw.DELETE_FLAG,twb.CARD_NO");
		sqlBuffer.append("	FROM T_WXUSER tw LEFT JOIN T_WXUSER_BANKCARD twb ");
		sqlBuffer.append("  ON tw.ID = twb.WXUSER_ID ");
		sqlBuffer.append("	WHERE tw.DELETE_FLAG=0 ");
		
		final String QUERY_NAME=queryParam.get("QUERY_NAME");
		if(!StringUtil.isEmpty(QUERY_NAME)){
			sqlBuffer.append(" AND tw.NICK_NAME like CONCAT('%',?,'%') ");
		}

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
				if(!StringUtil.isEmpty(QUERY_NAME)){
					ps.setString(i++, QUERY_NAME);
				}
			}
		}, new ResultSetExtractor<List<UserCenterMngMode>>() {

			@Override
			public List<UserCenterMngMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<UserCenterMngMode> list=new ArrayList<UserCenterMngMode>();
				if(page==null){
					while(rs.next()){
						UserCenterMngMode bean= new UserCenterMngMode();
						bean.setID(rs.getString("ID"));
						try {
							bean.setNICK_NAME(URLDecoder.decode(rs.getString("NICK_NAME"),"utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						bean.setSEX(rs.getString("SEX"));
						bean.setEMAIL(rs.getString("EMAIL"));
						bean.setMOBILE(rs.getString("MOBILE"));
						bean.setPASSWORD(rs.getString("PASSWORD"));
						bean.setBIRTH_YEAR(rs.getString("BIRTH_YEAR"));
						bean.setHEAD_IMG_URL(rs.getString("HEAD_IMG_URL"));
						bean.setOPEN_ID(rs.getString("OPEN_ID"));
						bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
						bean.setLAST_UPDATE_TIME(rs.getString("LAST_UPDATE_TIME"));
						bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
						bean.setWXUSER_BANKCARD(rs.getString("CARD_NO"));
						
						list.add(bean);
					}
				}else{
					int num=0;
					if(rs.absolute(page.getStartRow()+1)){
						do{
							UserCenterMngMode bean= new UserCenterMngMode();
							bean.setID(rs.getString("ID"));
							try {
								bean.setNICK_NAME(URLDecoder.decode(rs.getString("NICK_NAME"),"utf-8"));
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
							bean.setSEX(rs.getString("SEX"));
							bean.setEMAIL(rs.getString("EMAIL"));
							bean.setMOBILE(rs.getString("MOBILE"));
							bean.setPASSWORD(rs.getString("PASSWORD"));
							bean.setBIRTH_YEAR(rs.getString("BIRTH_YEAR"));
							bean.setHEAD_IMG_URL(rs.getString("HEAD_IMG_URL"));
							bean.setOPEN_ID(rs.getString("OPEN_ID"));
							bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
							bean.setLAST_UPDATE_TIME(rs.getString("LAST_UPDATE_TIME"));
							bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
							bean.setWXUSER_BANKCARD(rs.getString("CARD_NO"));
							
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
	public UserCenterMngMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT tw.ID, tw.NICK_NAME, tw.SEX, tw.EMAIL, tw.MOBILE, tw.PASSWORD, tw.BIRTH_YEAR, tw.HEAD_IMG_URL, tw.OPEN_ID, tw.CREATE_TIME, tw.LAST_UPDATE_TIME, tw.DELETE_FLAG,twb.CARD_NO,tea.ADDRESS ");
		sqlBuffer.append("	FROM T_WXUSER tw LEFT JOIN T_WXUSER_BANKCARD twb ");
		sqlBuffer.append("  ON tw.ID = twb.WXUSER_ID LEFT JOIN T_EXPRESS_ADDRESS tea");
		sqlBuffer.append("  ON tw.ID =  tea.MALL_USER_ID");
		sqlBuffer.append("	WHERE tw.ID = ? ");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<UserCenterMngMode>() {

			@Override
			public UserCenterMngMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					UserCenterMngMode bean= new UserCenterMngMode();
					bean.setID(rs.getString("ID"));
					try {
						bean.setNICK_NAME(URLDecoder.decode(rs.getString("NICK_NAME"),"utf-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					bean.setSEX(rs.getString("SEX"));
					bean.setEMAIL(rs.getString("EMAIL"));
					bean.setMOBILE(rs.getString("MOBILE"));
					bean.setPASSWORD(rs.getString("PASSWORD"));
					bean.setBIRTH_YEAR(rs.getString("BIRTH_YEAR"));
					bean.setHEAD_IMG_URL(rs.getString("HEAD_IMG_URL"));
					bean.setOPEN_ID(rs.getString("OPEN_ID"));
					bean.setCREATE_TIME(rs.getString("CREATE_TIME"));
					bean.setLAST_UPDATE_TIME(rs.getString("LAST_UPDATE_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setWXUSER_BANKCARD(rs.getString("CARD_NO"));
					bean.setADRESS(rs.getString("ADDRESS"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}


	@Override
	public void deleteInfo(final Integer id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_wxuser ");
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