package org.jumutang.project.weixinMng.mallMng.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.weixinMng.mallMng.dao.IWxBannerMngDao;
import org.jumutang.project.weixinMng.mallMng.model.WxBannerMngMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WxBannerMngDaoImpl implements IWxBannerMngDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public List<WxBannerMngMode> findList() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT t.ID, t.BANNER_TYPE, t.NAME, t.IMAGE_URL, t.LINK_URL, t.IN_USE_FLAG, t.SHOW_ORDER, t.DELETE_FLAG");
		sqlBuffer.append("	FROM t_banner t ");
		sqlBuffer.append("	WHERE t.DELETE_FLAG=0 ");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
			}
		}, new RowMapper<WxBannerMngMode>() {

			@Override
			public WxBannerMngMode mapRow(ResultSet rs, int rowNum) throws SQLException {
				WxBannerMngMode bean= new WxBannerMngMode();
				bean.setID(rs.getString("ID"));	
				bean.setBANNER_TYPE(rs.getString("BANNER_TYPE"));	
				bean.setNAME(rs.getString("NAME"));	
				bean.setIMAGE_URL(rs.getString("IMAGE_URL"));	
				bean.setLINK_URL(rs.getString("LINK_URL"));	
				bean.setIN_USE_FLAG(rs.getString("IN_USE_FLAG"));	
				bean.setSHOW_ORDER(rs.getString("SHOW_ORDER"));	
				bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));	
				return bean;
			}
		});
	}
	
	
	


}