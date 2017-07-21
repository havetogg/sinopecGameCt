package org.jumutang.project.weixinMng.mallMng.dao.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.weixinMng.mallMng.dao.ITchangeOrderDao;
import org.jumutang.project.weixinMng.mallMng.model.TchangeOrderMode;
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
public class TchangeOrderDaoImpl implements ITchangeOrderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public List<TchangeOrderMode> findList(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_ID, CHANGE_ID, ORDER_NO, MONEY, CREAT_TIME, PAYED_FLAG, PAY_MONEY, PAYED_TIME, DELETE_FLAG,DIAMOND_NUMB");
		sqlBuffer.append("	FROM t_change_order");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		
		final String QUERY_NAME=queryParam.get("QUERY_NAME");
		if(!StringUtil.isEmpty(QUERY_NAME)){
			sqlBuffer.append(" AND NAME like CONCAT('%',?,'%') ");
		}
		final String userId = queryParam.get("userId");
		if(!StringUtil.isEmpty(userId)){
			sqlBuffer.append(" AND USER_ID = "+userId);
		}
		final String payedFlag = queryParam.get("payedFlag");
		if(!StringUtil.isEmpty(payedFlag)){
			sqlBuffer.append(" AND PAYED_FLAG = "+payedFlag);
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
		}, new ResultSetExtractor<List<TchangeOrderMode>>() {

			@Override
			public List<TchangeOrderMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<TchangeOrderMode> list=new ArrayList<TchangeOrderMode>();
				
				while(rs.next()){
					TchangeOrderMode bean= new TchangeOrderMode();
					bean.setID(rs.getString("ID"));
					bean.setUSER_ID(rs.getString("USER_ID"));
					bean.setCHANGE_ID(rs.getString("CHANGE_ID"));
					bean.setORDER_NO(rs.getString("ORDER_NO"));
					bean.setMONEY(rs.getString("MONEY"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setPAYED_FLAG(rs.getString("PAYED_FLAG"));
					bean.setPAY_MONEY(rs.getString("PAY_MONEY"));
					bean.setPAYED_TIME(rs.getString("PAYED_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					bean.setDIAMOND_NUMB(rs.getString("DIAMOND_NUMB"));
					
					list.add(bean);
				}
				
				return list;
			}
		});
	}
	
	@Override
	public TchangeOrderMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_ID, CHANGE_ID, ORDER_NO, MONEY, CREAT_TIME, PAYED_FLAG, PAY_MONEY, PAYED_TIME, DELETE_FLAG ,DIAMOND_NUMB");
		sqlBuffer.append("	FROM t_change_order");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<TchangeOrderMode>() {

			@Override
			public TchangeOrderMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					TchangeOrderMode bean= new TchangeOrderMode();

					bean.setID(rs.getString("ID"));
					bean.setUSER_ID(rs.getString("USER_ID"));
					bean.setCHANGE_ID(rs.getString("CHANGE_ID"));
					bean.setORDER_NO(rs.getString("ORDER_NO"));
					bean.setMONEY(rs.getString("MONEY"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setPAYED_FLAG(rs.getString("PAYED_FLAG"));
					bean.setPAY_MONEY(rs.getString("PAY_MONEY"));
					bean.setPAYED_TIME(rs.getString("PAYED_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
				    bean.setDIAMOND_NUMB(rs.getString("DIAMOND_NUMB"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public int saveInfo(final TchangeOrderMode tchangeOrderMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t_change_order(ID, USER_ID, CHANGE_ID, ORDER_NO, MONEY, CREAT_TIME, PAYED_FLAG, PAY_MONEY, PAYED_TIME, DELETE_FLAG ,DIAMOND_NUMB) ");
		sqlBuffer.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, tchangeOrderMode.getID());
				ps.setString(i++, tchangeOrderMode.getUSER_ID());
				ps.setString(i++, tchangeOrderMode.getCHANGE_ID());
				ps.setString(i++, tchangeOrderMode.getORDER_NO());
				ps.setString(i++, tchangeOrderMode.getMONEY());
				ps.setString(i++, tchangeOrderMode.getCREAT_TIME());
				ps.setString(i++, tchangeOrderMode.getPAYED_FLAG());
				ps.setString(i++, tchangeOrderMode.getPAY_MONEY());
				ps.setString(i++, tchangeOrderMode.getPAYED_TIME());
				ps.setString(i++, tchangeOrderMode.getDELETE_FLAG());
				ps.setString(i++, tchangeOrderMode.getDIAMOND_NUMB());
				
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo(final TchangeOrderMode tchangeOrderMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_change_order ");
		sqlBuffer.append(" SET PAYED_FLAG=?,PAYED_TIME=current_timestamp");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, tchangeOrderMode.getPAYED_FLAG());
				ps.setString(i++, tchangeOrderMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public void deleteInfo(final Integer id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_change_order ");
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
	public TchangeOrderMode findInfo(final String order_no) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, USER_ID, CHANGE_ID, ORDER_NO, MONEY, CREAT_TIME, PAYED_FLAG, PAY_MONEY, PAYED_TIME, DELETE_FLAG ,DIAMOND_NUMB");
		sqlBuffer.append("	FROM t_change_order");
		sqlBuffer.append("	WHERE ORDER_NO=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setString(i++, order_no);	
			}

		}, new ResultSetExtractor<TchangeOrderMode>() {

			@Override
			public TchangeOrderMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					TchangeOrderMode bean= new TchangeOrderMode();

					bean.setID(rs.getString("ID"));
					bean.setUSER_ID(rs.getString("USER_ID"));
					bean.setCHANGE_ID(rs.getString("CHANGE_ID"));
					bean.setORDER_NO(rs.getString("ORDER_NO"));
					bean.setMONEY(rs.getString("MONEY"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setPAYED_FLAG(rs.getString("PAYED_FLAG"));
					bean.setPAY_MONEY(rs.getString("PAY_MONEY"));
					bean.setPAYED_TIME(rs.getString("PAYED_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
				    bean.setDIAMOND_NUMB(rs.getString("DIAMOND_NUMB"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}


}