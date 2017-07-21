package org.jumutang.project.sys.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jumutang.project.sys.dao.ILoginDao;
import org.jumutang.project.sys.model.SysLoginUserBean;
import org.jumutang.project.sys.model.SysMenuMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class LoginDaoImpl implements ILoginDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	// 查询登录用户
	@Override
	public SysLoginUserBean queryLoginUserInfo(final HashMap<String,String> map) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, LOGIN_NAME, PASSWORD, USER_TYPE");
		sqlBuffer.append(" FROM t_sysuser");
		sqlBuffer.append(" WHERE LOGIN_NAME = ? ");
		sqlBuffer.append(" AND PASSWORD = ? ");
//		sqlBuffer.append(" union");
//		sqlBuffer.append(" SELECT ID, USERID, PASSWORD, 2");
//		sqlBuffer.append(" FROM t_user");
//		sqlBuffer.append(" WHERE USERID = ? ");
//		sqlBuffer.append(" AND PASSWORD = ? ");
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i=1;
				ps.setString(i++, map.get("loginName"));	
				ps.setString(i++, map.get("loginPass"));
			}

		}, new ResultSetExtractor<SysLoginUserBean>() {

			@Override
			public SysLoginUserBean extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					SysLoginUserBean returnmap = new SysLoginUserBean();
					returnmap.setID(rs.getInt("ID"));
					returnmap.setLOGIN_NAME(rs.getString("LOGIN_NAME"));
//					returnmap.put("PASSWORD", rs.getString("PASSWORD"));
					returnmap.setUSER_TYPE(rs.getInt("USER_TYPE"));
					return returnmap;
				}
				return null;
			}
		});
	}

	// 查询登录用户的操作菜单
	@Override
	public List<SysMenuMode> queryLoginUserMenu(final HashMap<String, String> map) {
		
		StringBuffer sqlBuffer = new StringBuffer();
		
		if("1".equals(map.get("USER_TYPE"))){
			sqlBuffer.append(" SELECT ID, NAME, URL, PARENT_ID, SHOW_ORDER, USER_TYPE");
			sqlBuffer.append(" FROM t_sys_menu");
			sqlBuffer.append(" ORDER BY SHOW_ORDER asc");
		}else if("2".equals(map.get("USER_TYPE"))){
			sqlBuffer.append(" SELECT m.ID, m.NAME, m.URL, m.PARENT_ID, m.SHOW_ORDER, m.USER_TYPE");
			sqlBuffer.append(" FROM t_sys_menu m");
			sqlBuffer.append(" WHERE m.ID IN (SELECT MENU.MENU_ID "
										 + "FROM t_user_rel_role R , t_sys_role_menu MENU "
										 + "WHERE R.USER_ID=? AND R.SYS_ROLE_ID=MENU.SYS_ROLE_ID) ");
			sqlBuffer.append(" ORDER BY m.SHOW_ORDER asc");
		}
		
		
		List<SysMenuMode> allList=this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i=1;
				if("2".equals(map.get("USER_TYPE"))){
					ps.setString(i++, map.get("USER_ID"));
				}
					
			}

		}, new RowMapper<SysMenuMode>() {

			@Override
			public SysMenuMode mapRow(ResultSet rs, int rowNum) throws SQLException {
				SysMenuMode mode=new SysMenuMode();
				mode.setID(rs.getInt("ID"));
				mode.setNAME(rs.getString("NAME"));
				if(rs.getObject("PARENT_ID")!=null){
					mode.setPARENT_ID(rs.getInt("PARENT_ID"));
				}
				
				mode.setSHOW_ORDER(rs.getInt("SHOW_ORDER"));
				mode.setURL(rs.getString("URL"));
				mode.setUSER_TYPE(rs.getInt("USER_TYPE"));
				
				return mode;
			}
		});
		
		
		List<SysMenuMode> resultList=new ArrayList<SysMenuMode>();
		if(allList!=null){
			for(SysMenuMode m :allList){
				if(m.getPARENT_ID()==null){
					resultList.add(m);
				}
			}
			
			for(SysMenuMode m :resultList){
				for(SysMenuMode m2 :allList){
					if(m2.getPARENT_ID()!=null&&m.getID()==m2.getPARENT_ID()){
						m.getSubList().add(m2);
					}
				}
			}
		}
		return resultList;
	}

}
