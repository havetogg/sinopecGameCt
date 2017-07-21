package org.jumutang.project.weixinMng.mallMng.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.dao.IChangeMngDao;
import org.jumutang.project.weixinMng.mallMng.model.ChangeMngMode;
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
public class ChangeMngDaoImpl implements IChangeMngDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ChangeMngMode> findList(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, CHANGE_NAME, DIAMOND_NUMB, YH_FLAG, MONEY, PAY_MONEY, SHOW_ORDER, CREAT_TIME, DELETE_FLAG");
		sqlBuffer.append("	FROM t_change");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append(" ORDER BY SHOW_ORDER ASC ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
//				if(!StringUtil.isEmpty(QUERY_NAME)){
//					ps.setString(i++, QUERY_NAME);
//				}
			}
		}, new ResultSetExtractor<List<ChangeMngMode>>() {

			@Override
			public List<ChangeMngMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<ChangeMngMode> list=new ArrayList<ChangeMngMode>();
				while(rs.next()){
					ChangeMngMode bean= new ChangeMngMode();
					bean.setID(rs.getString("ID"));
					bean.setCHANGE_NAME(rs.getString("CHANGE_NAME"));
					bean.setDIAMOND_NUMB(rs.getString("DIAMOND_NUMB"));
					bean.setYH_FLAG(rs.getString("YH_FLAG"));
					bean.setMONEY(rs.getString("MONEY"));
					bean.setPAY_MONEY(rs.getString("PAY_MONEY"));
					bean.setSHOW_ORDER(rs.getString("SHOW_ORDER"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					
					list.add(bean);
				}
				
				return list;
			}
		});
	}
	
	@Override
	public ChangeMngMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, CHANGE_NAME, DIAMOND_NUMB, YH_FLAG, MONEY, PAY_MONEY, SHOW_ORDER, CREAT_TIME, DELETE_FLAG");
		sqlBuffer.append("	FROM t_change");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<ChangeMngMode>() {

			@Override
			public ChangeMngMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					ChangeMngMode bean= new ChangeMngMode();

					bean.setID(rs.getString("ID"));
					bean.setCHANGE_NAME(rs.getString("CHANGE_NAME"));
					bean.setDIAMOND_NUMB(rs.getString("DIAMOND_NUMB"));
					bean.setYH_FLAG(rs.getString("YH_FLAG"));
					bean.setMONEY(rs.getString("MONEY"));
					bean.setPAY_MONEY(rs.getString("PAY_MONEY"));
					bean.setSHOW_ORDER(rs.getString("SHOW_ORDER"));
					bean.setCREAT_TIME(rs.getString("CREAT_TIME"));
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public int saveInfo(final ChangeMngMode changeMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t_change(ID, CHANGE_NAME, DIAMOND_NUMB, YH_FLAG, MONEY, PAY_MONEY, SHOW_ORDER, CREAT_TIME, DELETE_FLAG) ");
		sqlBuffer.append(" VALUES(?,?,?,?,?,?,?,?,?)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, changeMngMode.getID());
				ps.setString(i++, changeMngMode.getCHANGE_NAME());
				ps.setString(i++, changeMngMode.getDIAMOND_NUMB());
				ps.setString(i++, changeMngMode.getYH_FLAG());
				ps.setString(i++, changeMngMode.getMONEY());
				ps.setString(i++, changeMngMode.getPAY_MONEY());
				ps.setString(i++, changeMngMode.getSHOW_ORDER());
				ps.setString(i++, changeMngMode.getCREAT_TIME());
				ps.setString(i++, changeMngMode.getDELETE_FLAG());
				
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo(final ChangeMngMode changeMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_change ");
		sqlBuffer.append(" SET ID=?,CHANGE_NAME=?,DIAMOND_NUMB=?,YH_FLAG=?,MONEY=?,PAY_MONEY=?,SHOW_ORDER=?,CREAT_TIME=?,DELETE_FLAG=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, changeMngMode.getID());
				ps.setString(i++, changeMngMode.getCHANGE_NAME());
				ps.setString(i++, changeMngMode.getDIAMOND_NUMB());
				ps.setString(i++, changeMngMode.getYH_FLAG());
				ps.setString(i++, changeMngMode.getMONEY());
				ps.setString(i++, changeMngMode.getPAY_MONEY());
				ps.setString(i++, changeMngMode.getSHOW_ORDER());
				ps.setString(i++, changeMngMode.getCREAT_TIME());
				ps.setString(i++, changeMngMode.getDELETE_FLAG());
				ps.setString(i++, changeMngMode.getID());
				
				return ps;
			}
		});

	}

}