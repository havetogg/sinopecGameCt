package org.jumutang.project.weixinMng.mallMng.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.tools.DateUtil;
import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.weixinMng.mallMng.dao.IWxAdvertsMngDao;
import org.jumutang.project.weixinMng.mallMng.model.WxAdvertsMngMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class WxAdvertsMngDaoImpl implements IWxAdvertsMngDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findCount(Map<String, String> queryParam) {
		StringBuffer sqlBuffer = new StringBuffer();
		List param = new ArrayList<>();
		sqlBuffer.append(" SELECT COUNT(1)");
		sqlBuffer.append("	FROM t_adverts");
		sqlBuffer.append("	WHERE DELETE_FLAG=0 ");
		if(queryParam.get("name")!=null&&!"".equals(queryParam.get("name"))){
			sqlBuffer.append("	and NAME LIKE ?  ");
			param.add("%"+queryParam.get("name")+"%");
		}
		
		return jdbcTemplate.queryForObject(sqlBuffer.toString(), param.toArray(),Integer.class);
	}
	
	@Override
	public List<WxAdvertsMngMode> findList(final Map<String, String> queryParam, final Page page) {

		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT t.ID, t.BANNER_TYPE, t.NAME, t.IMAGE_URL, t.LINK_URL, t.IN_USE_FLAG, t.SHOW_ORDER, t.DELETE_FLAG");
		sqlBuffer.append("	FROM t_adverts t ");
		sqlBuffer.append("	WHERE t.DELETE_FLAG=0 ");
		
		final String QUERY_NAME=queryParam.get("QUERY_NAME");
		if(!StringUtil.isEmpty(QUERY_NAME)){
			sqlBuffer.append(" AND NAME like CONCAT('%',?,'%') ");
		}
		
		sqlBuffer.append(" ORDER BY SHOW_ORDER ASC ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
				if(!StringUtil.isEmpty(QUERY_NAME)){
					ps.setString(i++, QUERY_NAME);
				}
			}
		}, new ResultSetExtractor<List<WxAdvertsMngMode>>() {

			@Override
			public List<WxAdvertsMngMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<WxAdvertsMngMode> list=new ArrayList<WxAdvertsMngMode>();
				if(page==null){
					while(rs.next()){
						WxAdvertsMngMode bean= new WxAdvertsMngMode();
						bean.setID(rs.getString("ID"));	
						bean.setBANNER_TYPE(rs.getString("BANNER_TYPE"));	
						bean.setNAME(rs.getString("NAME"));	
						bean.setIMAGE_URL(rs.getString("IMAGE_URL"));	
						bean.setLINK_URL(rs.getString("LINK_URL"));	
						bean.setIN_USE_FLAG(rs.getString("IN_USE_FLAG"));	
						bean.setSHOW_ORDER(rs.getString("SHOW_ORDER"));	
						bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));	
						
						list.add(bean);
					}
				}else{
					int num=0;
					if(rs.absolute(page.getStartRow()+1)){
						do{
							WxAdvertsMngMode bean= new WxAdvertsMngMode();
							bean.setID(rs.getString("ID"));	
							bean.setBANNER_TYPE(rs.getString("BANNER_TYPE"));	
							bean.setNAME(rs.getString("NAME"));	
							bean.setIMAGE_URL(rs.getString("IMAGE_URL"));	
							bean.setLINK_URL(rs.getString("LINK_URL"));	
							bean.setIN_USE_FLAG(rs.getString("IN_USE_FLAG"));	
							bean.setSHOW_ORDER(rs.getString("SHOW_ORDER"));	
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
	public WxAdvertsMngMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT t.ID, t.BANNER_TYPE, t.NAME, t.IMAGE_URL, t.LINK_URL, t.IN_USE_FLAG, t.SHOW_ORDER, t.DELETE_FLAG");
		sqlBuffer.append("	FROM t_adverts t ");
		sqlBuffer.append("	WHERE t.DELETE_FLAG=0");
		sqlBuffer.append("	AND t.ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<WxAdvertsMngMode>() {

			@Override
			public WxAdvertsMngMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					WxAdvertsMngMode bean= new WxAdvertsMngMode();

					bean.setID(rs.getString("ID"));	
					bean.setBANNER_TYPE(rs.getString("BANNER_TYPE"));	
					bean.setNAME(rs.getString("NAME"));	
					bean.setIMAGE_URL(rs.getString("IMAGE_URL"));	
					bean.setLINK_URL(rs.getString("LINK_URL"));	
					bean.setIN_USE_FLAG(rs.getString("IN_USE_FLAG"));	
					bean.setSHOW_ORDER(rs.getString("SHOW_ORDER"));	
					bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));	
					return bean;
				}else{
					return null;
				}
			}
		});
	}
	

}