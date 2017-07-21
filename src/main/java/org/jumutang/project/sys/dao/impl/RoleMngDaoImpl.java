package org.jumutang.project.sys.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.base.TreeModel;
import org.jumutang.project.sys.dao.IRoleMngDao;
import org.jumutang.project.sys.model.RoleMngMode;
import org.jumutang.project.tools.StringUtil;
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
public class RoleMngDaoImpl implements IRoleMngDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findCount(Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT COUNT(1)");
		sqlBuffer.append("	FROM t_sys_role");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		
		final String QUERY_NAME=queryParam.get("QUERY_NAME");
		if(!StringUtil.isEmpty(QUERY_NAME)){
			sqlBuffer.append(" AND(  ");
			sqlBuffer.append(" NAME like CONCAT('%',?,'%')  )");
			return jdbcTemplate.queryForObject(sqlBuffer.toString(), new Object[]{QUERY_NAME},Integer.class);
		}else{
			return jdbcTemplate.queryForObject(sqlBuffer.toString(), null,Integer.class);
		}
		
		
	}
	
	@Override
	public List<RoleMngMode> findList(Map<String, String> queryParam, final Page page) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, NAME, CREATE_USERID, CREATE_TIME, DELETE_FLAG");
		sqlBuffer.append("	FROM t_sys_role");
		sqlBuffer.append("	WHERE DELETE_FLAG=0");
		
		final String QUERY_NAME=queryParam.get("QUERY_NAME");
		if(!StringUtil.isEmpty(QUERY_NAME)){
			sqlBuffer.append(" AND(  ");
			sqlBuffer.append(" NAME like CONCAT('%',?,'%')  )");
		}
		
		sqlBuffer.append("	ORDER BY  CREATE_TIME DESC");

		return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
				if(!StringUtil.isEmpty(QUERY_NAME)){
					ps.setString(i++, QUERY_NAME);
				}
			}
		}, new ResultSetExtractor<List<RoleMngMode>>() {

			@Override
			public List<RoleMngMode> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<RoleMngMode> list=new ArrayList<RoleMngMode>();
				
				rs.absolute(page.getStartRow()+1);
				int num=0;
				if (0==rs.getRow()) {
					return list;
				}
				do{
					RoleMngMode bean= new RoleMngMode();
					bean.setID(rs.getInt("ID"));
					bean.setNAME(rs.getString("NAME"));
					bean.setCREATE_USERID(rs.getInt("CREATE_USERID"));
					bean.setCREATE_TIME(rs.getTimestamp("CREATE_TIME"));
					bean.setDELETE_FLAG(rs.getInt("DELETE_FLAG"));
					
					list.add(bean);
					num++;
					if(num>=page.getPageSize()){
						break;
					}
				}while(rs.next());
				return list;
			}
		});
	}
	
	@Override
	public RoleMngMode findInfo(final Integer id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT ID, NAME, CREATE_USERID, CREATE_TIME, DELETE_FLAG");
		sqlBuffer.append("	FROM t_sys_role");
		sqlBuffer.append("	WHERE ID=?");
		
		return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					int i = 1;
					ps.setInt(i++, id);	
			}

		}, new ResultSetExtractor<RoleMngMode>() {

			@Override
			public RoleMngMode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					RoleMngMode bean= new RoleMngMode();

					bean.setID(rs.getInt("ID"));
					bean.setNAME(rs.getString("NAME"));
					bean.setCREATE_USERID(rs.getInt("CREATE_USERID"));
					bean.setCREATE_TIME(rs.getTimestamp("CREATE_TIME"));
					bean.setDELETE_FLAG(rs.getInt("DELETE_FLAG"));
					return bean;
				}else{
					return null;
				}
			}
		});
	}

	@Override
	public int saveInfo(final RoleMngMode roleMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO t_sys_role(NAME, CREATE_USERID, CREATE_TIME, DELETE_FLAG) ");
		sqlBuffer.append(" VALUES(?,?,current_timestamp,0)");

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
				int i = 1;

				ps.setString(i++, roleMngMode.getNAME());
				ps.setInt(i++, roleMngMode.getCREATE_USERID());				
				return ps;
			}
		}, keyHolder);
		int ROLE_ID=keyHolder.getKey().intValue();
		insertRoleMenu(ROLE_ID, roleMngMode.getCHECKED_NODE_IDS());
		
		return ROLE_ID;

	}
	
	private void insertRoleMenu(Integer ROLE_ID,String CHECKED_NODE_IDS){
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("delete from t_sys_role_menu where SYS_ROLE_ID=?");
		jdbcTemplate.update(sqlBuffer.toString(), new Object[]{ROLE_ID});
		//写入选中的节点信息
		if(!StringUtil.isEmpty(CHECKED_NODE_IDS)){
			sqlBuffer.delete(0, sqlBuffer.length());
			sqlBuffer.append("INSERT INTO t_sys_role_menu (SYS_ROLE_ID, MENU_ID)VALUES (?,?)");
			
			String[] array=CHECKED_NODE_IDS.split(",");
			for(String s:array){
				if(!StringUtil.isEmpty(s)){
					
					jdbcTemplate.update(sqlBuffer.toString(),new Object[]{ROLE_ID,s});
				}
			}
		}
	}

	@Override
	public void updateInfo(final RoleMngMode roleMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_sys_role ");
		sqlBuffer.append(" SET NAME=?");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, roleMngMode.getNAME());
				ps.setInt(i++, roleMngMode.getID());
				
				return ps;
			}
		});

		insertRoleMenu(roleMngMode.getID(), roleMngMode.getCHECKED_NODE_IDS());
	}

	@Override
	public void deleteInfo(final Integer id) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t_sys_role ");
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
	public List<TreeModel> loadTree(final Integer ROLE_ID, final Integer PARENT_ID, String LOAD_TYPE) {
		// TODO Auto-generated method stub
		List<TreeModel> list=new ArrayList<TreeModel>();
		StringBuffer sqlBuffer=new StringBuffer();
		if("add".equals(LOAD_TYPE)){
			if(PARENT_ID!=null){
				sqlBuffer.append(" SELECT ID,NAME,'false' isParent FROM t_sys_menu ");
				sqlBuffer.append(" WHERE PARENT_ID=? ");
				sqlBuffer.append(" ORDER BY SHOW_ORDER ");
			}else{
				sqlBuffer.append(" SELECT ID,NAME,'true' isParent FROM t_sys_menu ");
				sqlBuffer.append(" WHERE PARENT_ID is null  ");
				sqlBuffer.append(" ORDER BY SHOW_ORDER ");
			}
			
			list=jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					// TODO Auto-generated method stub
					int i=1;
					if(PARENT_ID!=null){
						ps.setInt(i++, PARENT_ID);
					}
				}
			},new RowMapper<TreeModel>() {

				@Override
				public TreeModel mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					TreeModel mode=new TreeModel();
					mode.setId(rs.getString("ID"));
					mode.setName(rs.getString("NAME"));
					mode.setIsParent(rs.getString("isParent"));
					mode.setChecked("false");
					return mode;
				}
				
			});
		}else if("view".equals(LOAD_TYPE)){
			if(PARENT_ID!=null){
				sqlBuffer.append(" SELECT ID,NAME,'false' isParent FROM t_sys_menu ");
				sqlBuffer.append(" WHERE PARENT_ID=? ");
				sqlBuffer.append(" AND ID IN(SELECT m.MENU_ID FROM t_sys_role_menu m WHERE m.SYS_ROLE_ID=?)");
				sqlBuffer.append(" ORDER BY SHOW_ORDER ");
			}else{
				sqlBuffer.append(" SELECT ID,NAME,'true' isParent FROM t_sys_menu ");
				sqlBuffer.append(" WHERE PARENT_ID is null  ");
				sqlBuffer.append(" AND ID IN(SELECT m.MENU_ID FROM t_sys_role_menu m WHERE m.SYS_ROLE_ID=?)");
				sqlBuffer.append(" ORDER BY SHOW_ORDER ");
			}
			
			list=jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					// TODO Auto-generated method stub
					int i=1;
					if(PARENT_ID!=null){
						ps.setInt(i++, PARENT_ID);
					}
					ps.setInt(i++, ROLE_ID);
				}
			},new RowMapper<TreeModel>() {

				@Override
				public TreeModel mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					TreeModel mode=new TreeModel();
					mode.setId(rs.getString("ID"));
					mode.setName(rs.getString("NAME"));
					mode.setIsParent(rs.getString("isParent"));
					return mode;
				}
				
			});
		}else if("edit".equals(LOAD_TYPE)){

			if(PARENT_ID!=null){
				sqlBuffer.append(" SELECT m.ID,m.NAME,'false' isParent,(case when r.ID is null then 'false' else 'true' END) CHECKED ");
				sqlBuffer.append(" FROM t_sys_menu m left join t_sys_role_menu r on m.ID=r.MENU_ID AND r.SYS_ROLE_ID=? ");
				sqlBuffer.append(" WHERE m.PARENT_ID=? ");
				sqlBuffer.append(" ORDER BY m.SHOW_ORDER ");
			}else{
				sqlBuffer.append(" SELECT m.ID,m.NAME,'true' isParent,(case when r.ID is null then 'false' else 'true' END) CHECKED ");
				sqlBuffer.append(" FROM t_sys_menu m left join t_sys_role_menu r on m.ID=r.MENU_ID AND r.SYS_ROLE_ID=? ");
				sqlBuffer.append(" WHERE m.PARENT_ID is null  ");
				sqlBuffer.append(" ORDER BY m.SHOW_ORDER ");
			}
			
			list=jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					// TODO Auto-generated method stub
					int i=1;
					ps.setInt(i++, ROLE_ID);
					if(PARENT_ID!=null){
						ps.setInt(i++, PARENT_ID);
					}
				}
			},new RowMapper<TreeModel>() {

				@Override
				public TreeModel mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					TreeModel mode=new TreeModel();
					mode.setId(rs.getString("ID"));
					mode.setName(rs.getString("NAME"));
					mode.setIsParent(rs.getString("isParent"));
					mode.setChecked(rs.getString("CHECKED"));
					return mode;
				}
				
			});
		}
		return list;
	}


}