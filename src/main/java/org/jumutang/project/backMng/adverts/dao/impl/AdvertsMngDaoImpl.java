package org.jumutang.project.backMng.adverts.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.backMng.adverts.dao.IAdvertsMngDao;
import org.jumutang.project.backMng.adverts.model.AdvertsMngMode;
import org.jumutang.project.base.Page;
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
public class AdvertsMngDaoImpl implements IAdvertsMngDao {

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
	public List<AdvertsMngMode> findList(final Map<String, String> queryParam, final Page page) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT t.ID, t.BANNER_TYPE, t.NAME, t.IMAGE_URL, t.LINK_URL, t.IN_USE_FLAG, t.SHOW_ORDER, t.DELETE_FLAG");
		sqlBuffer.append("	FROM t_adverts t ");
		sqlBuffer.append("	WHERE t.DELETE_FLAG=0 ");
		if(queryParam.get("name")!=null&&!"".equals(queryParam.get("name"))){
			sqlBuffer.append("	and t.NAME LIKE ?  ");
		}
		sqlBuffer.append(" limit ?,?");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
				if(queryParam.get("name")!=null&&!"".equals(queryParam.get("name"))){
					ps.setString(i++, "%"+queryParam.get("name")+"%");
				}
				ps.setInt(i++, ((page.getCurrentPage()<1?1:page.getCurrentPage())-1)*page.getPageSize());
				ps.setInt(i++, page.getPageSize());
			}
		}, new RowMapper<AdvertsMngMode>() {

			@Override
			public AdvertsMngMode mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdvertsMngMode bean= new AdvertsMngMode();
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
	
	
	
	
	@Override
	public AdvertsMngMode findInfo(final Integer id) {
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

		}, new ResultSetExtractor<AdvertsMngMode>() {

			@Override
			public AdvertsMngMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					AdvertsMngMode bean= new AdvertsMngMode();

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
	
	@Override
	public int saveInfo(final AdvertsMngMode bannerMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t_adverts(ID, BANNER_TYPE, NAME, IMAGE_URL, LINK_URL, IN_USE_FLAG, SHOW_ORDER, DELETE_FLAG) ");
		sqlBuffer.append(" VALUES(null,?,?,?,?,?,?,0)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, bannerMode.getBANNER_TYPE());
				ps.setString(i++, bannerMode.getNAME());
				ps.setString(i++, bannerMode.getIMAGE_URL());
				ps.setString(i++, bannerMode.getLINK_URL());
				ps.setString(i++, bannerMode.getIN_USE_FLAG());
				ps.setString(i++, bannerMode.getSHOW_ORDER());
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo(final AdvertsMngMode bannerMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_adverts ");
		sqlBuffer.append(" SET BANNER_TYPE=?,LINK_URL=?,NAME=?,IMAGE_URL=?,IN_USE_FLAG=?,SHOW_ORDER=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, bannerMode.getBANNER_TYPE());
				ps.setString(i++, bannerMode.getLINK_URL());
				ps.setString(i++, bannerMode.getNAME());
				ps.setString(i++, bannerMode.getIMAGE_URL());
				ps.setString(i++, bannerMode.getIN_USE_FLAG());
				ps.setString(i++, bannerMode.getSHOW_ORDER());
				ps.setString(i++, bannerMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public void deleteInfo(final Integer id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_adverts ");
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
	public AdvertsMngMode findEditInfo(final Integer id) {

		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, BANNER_TYPE, NAME, IMAGE_URL, LINK_URL, IN_USE_FLAG, SHOW_ORDER, DELETE_FLAG ");
		sqlBuffer.append("	FROM t_adverts ");
		sqlBuffer.append("	WHERE DELETE_FLAG=0 ");
		sqlBuffer.append("	AND ID=? ");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<AdvertsMngMode>() {

			@Override
			public AdvertsMngMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					AdvertsMngMode bean= new AdvertsMngMode();

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