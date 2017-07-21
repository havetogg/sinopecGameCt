package org.jumutang.project.backMng.news.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.base.Page;
import org.jumutang.project.backMng.news.dao.INewsCoverimageMngDao;
import org.jumutang.project.backMng.news.model.NewsCoverimageMngMode;
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
public class NewsCoverimageMngDaoImpl implements INewsCoverimageMngDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findCount(final Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT COUNT(1) ");
		sqlBuffer.append("	FROM t_news_coverimage");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append("	AND NEWS_ID=?");
		
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
				ps.setString(i++, queryParam.get("NEWS_ID"));
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
	public List<NewsCoverimageMngMode> findList(final Map<String, String> queryParam, final Page page) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, NEWS_ID, ORIGINAL_PATH, CUTTED_PATH, SHOW_ORDER, DELETE_FLAG");
		sqlBuffer.append("	FROM t_news_coverimage");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		sqlBuffer.append("	AND NEWS_ID=?");
		
		final String QUERY_NAME=queryParam.get("QUERY_NAME");
		if(!StringUtil.isEmpty(QUERY_NAME)){
			sqlBuffer.append(" AND NAME like CONCAT('%',?,'%') ");
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
				ps.setString(i++, queryParam.get("NEWS_ID"));
			}
		}, new ResultSetExtractor<List<NewsCoverimageMngMode>>() {

			@Override
			public List<NewsCoverimageMngMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<NewsCoverimageMngMode> list=new ArrayList<NewsCoverimageMngMode>();
				if(page==null){
					while(rs.next()){
						NewsCoverimageMngMode bean= new NewsCoverimageMngMode();
						bean.setID(rs.getString("ID"));
						bean.setNEWS_ID(rs.getString("NEWS_ID"));
						bean.setORIGINAL_PATH(rs.getString("ORIGINAL_PATH"));
						bean.setCUTTED_PATH(rs.getString("CUTTED_PATH"));
						bean.setSHOW_ORDER(rs.getString("SHOW_ORDER"));
						bean.setDELETE_FLAG(rs.getString("DELETE_FLAG"));
						
						list.add(bean);
					}
				}else{
					int num=0;
					if(rs.absolute(page.getStartRow()+1)){
						do{
							NewsCoverimageMngMode bean= new NewsCoverimageMngMode();
							bean.setID(rs.getString("ID"));
							bean.setNEWS_ID(rs.getString("NEWS_ID"));
							bean.setORIGINAL_PATH(rs.getString("ORIGINAL_PATH"));
							bean.setCUTTED_PATH(rs.getString("CUTTED_PATH"));
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
	public NewsCoverimageMngMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, NEWS_ID, ORIGINAL_PATH, CUTTED_PATH, SHOW_ORDER, DELETE_FLAG");
		sqlBuffer.append("	FROM t_news_coverimage");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<NewsCoverimageMngMode>() {

			@Override
			public NewsCoverimageMngMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					NewsCoverimageMngMode bean= new NewsCoverimageMngMode();

					bean.setID(rs.getString("ID"));
					bean.setNEWS_ID(rs.getString("NEWS_ID"));
					bean.setORIGINAL_PATH(rs.getString("ORIGINAL_PATH"));
					bean.setCUTTED_PATH(rs.getString("CUTTED_PATH"));
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
	public int saveInfo(final NewsCoverimageMngMode newsCoverimageMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t_news_coverimage(ID, NEWS_ID, ORIGINAL_PATH, CUTTED_PATH, SHOW_ORDER, DELETE_FLAG) ");
		sqlBuffer.append(" VALUES(?,?,?,?,?,?)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, newsCoverimageMngMode.getID());
				ps.setString(i++, newsCoverimageMngMode.getNEWS_ID());
				ps.setString(i++, newsCoverimageMngMode.getORIGINAL_PATH());
				ps.setString(i++, newsCoverimageMngMode.getCUTTED_PATH());
				ps.setString(i++, newsCoverimageMngMode.getSHOW_ORDER());
				ps.setString(i++, newsCoverimageMngMode.getDELETE_FLAG());
				
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void updateInfo(final NewsCoverimageMngMode newsCoverimageMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_news_coverimage ");
		sqlBuffer.append(" SET NEWS_ID=?,ORIGINAL_PATH=?,CUTTED_PATH=?,SHOW_ORDER=?,DELETE_FLAG=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, newsCoverimageMngMode.getNEWS_ID());
				ps.setString(i++, newsCoverimageMngMode.getORIGINAL_PATH());
				ps.setString(i++, newsCoverimageMngMode.getCUTTED_PATH());
				ps.setString(i++, newsCoverimageMngMode.getSHOW_ORDER());
				ps.setString(i++, newsCoverimageMngMode.getDELETE_FLAG());
				ps.setString(i++, newsCoverimageMngMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public void deleteInfo(final Integer id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_news_coverimage ");
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