package org.jumutang.project.weixinMng.mallMng.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.weixinMng.mallMng.dao.IChange_goldMngDao;
import org.jumutang.project.weixinMng.mallMng.model.Change_goldMngMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class Change_goldMngDaoImpl implements IChange_goldMngDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Change_goldMngMode> findList(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, CHANGE_NAME, GOLD, YH_FLAG, DIAMOND_NUMB, PAY_DIAMOND_NUMB, SHOW_ORDER, CREAT_TIME, DELETE_FLAG");
		sqlBuffer.append("	FROM t_change_gold");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append(" ORDER BY SHOW_ORDER ASC ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
/*				if(!StringUtil.isEmpty(QUERY_NAME)){
					ps.setString(i++, QUERY_NAME);
				}*/
			}
		}, new ResultSetExtractor<List<Change_goldMngMode>>() {

			@Override
			public List<Change_goldMngMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Change_goldMngMode> list=new ArrayList<Change_goldMngMode>();
				while(rs.next()){
					Change_goldMngMode bean= new Change_goldMngMode();
					bean.setID(rs.getString("ID"));
					bean.setCHANGE_NAME(rs.getString("CHANGE_NAME"));
					bean.setGOLD(rs.getString("GOLD"));
					bean.setYH_FLAG(rs.getString("YH_FLAG"));
					bean.setDIAMOND_NUMB(rs.getString("DIAMOND_NUMB"));
					bean.setPAY_DIAMOND_NUMB(rs.getString("PAY_DIAMOND_NUMB"));
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
	public Change_goldMngMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, CHANGE_NAME, GOLD, YH_FLAG, DIAMOND_NUMB, PAY_DIAMOND_NUMB, SHOW_ORDER, CREAT_TIME, DELETE_FLAG");
		sqlBuffer.append("	FROM t_change_gold");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<Change_goldMngMode>() {

			@Override
			public Change_goldMngMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Change_goldMngMode bean= new Change_goldMngMode();

					bean.setID(rs.getString("ID"));
					bean.setCHANGE_NAME(rs.getString("CHANGE_NAME"));
					bean.setGOLD(rs.getString("GOLD"));
					bean.setYH_FLAG(rs.getString("YH_FLAG"));
					bean.setDIAMOND_NUMB(rs.getString("DIAMOND_NUMB"));
					bean.setPAY_DIAMOND_NUMB(rs.getString("PAY_DIAMOND_NUMB"));
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

	


}