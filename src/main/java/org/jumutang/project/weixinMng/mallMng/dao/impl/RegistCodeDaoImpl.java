package org.jumutang.project.weixinMng.mallMng.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.jumutang.project.weixinMng.mallMng.dao.IRegistCodeDao;
import org.jumutang.project.weixinMng.mallMng.model.RegistCodeMode;
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
public class RegistCodeDaoImpl implements IRegistCodeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public int insertRegistCodeInfoVolume(final HashMap<String, String> amap) {
		int result = 0;
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t_registcode ( MOBILE, CODE, EFFECTIVE_TIME, CREATE_TIME)");
		sqlBuffer.append(" values ( ?, ?, ?,?)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		result = jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;
				ps.setString(i++, amap.get("MOBILE"));
				ps.setString(i++, amap.get("CODE"));
				ps.setString(i++, amap.get("EFFECTIVE_TIME"));
				ps.setString(i++, amap.get("CREATE_TIME"));
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

	@Override
	public RegistCodeMode queryRegistCodeInfo(final HashMap<String, String> amap) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, MOBILE, CODE, EFFECTIVE_TIME, CREATE_TIME ");
		sqlBuffer.append(" FROM t_registcode ");
		sqlBuffer.append(" WHERE MOBILE = ?");
		sqlBuffer.append(" AND CODE = ? ");
		sqlBuffer.append(" AND str_to_date( ? ,'%Y-%m-%d %H:%i:%s')<=str_to_date(EFFECTIVE_TIME,'%Y-%m-%d %H:%i:%s') ");
		sqlBuffer.append(" ORDER BY CREATE_TIME DESC ");
		sqlBuffer.append(" limit 1 ");
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
				ps.setString(i++, amap.get("MOBILE"));
				ps.setString(i++, amap.get("CODE"));
				ps.setString(i++, amap.get("EFFECTIVE_TIME"));
			}

		}, new ResultSetExtractor<RegistCodeMode>() {

			@Override
			public RegistCodeMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					RegistCodeMode registcodemode = new RegistCodeMode();
					registcodemode.setID(rs.getString("ID"));
					return registcodemode;
				}
				return null;
			}
		});
	}
}
