package org.jumutang.project.appMng.mallMng.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.jumutang.project.appMng.mallMng.dao.IAppDao;
import org.jumutang.project.appMng.mallMng.model.AppModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 10:55 2017/8/8
 * @Modified By:
 */
@Repository
public class AppDaoImpl implements IAppDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveApp(AppModel appModel) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("insert into t_app_user (user_code,create_time) ");
        stringBuffer.append("values (?,now())");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(stringBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++,appModel.getUserCode());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public List<AppModel> listApp(AppModel appModel) {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("  SELECT *");
        sqlBuffer.append("	FROM t_app_user");
        sqlBuffer.append("	WHERE 1=1");
        if(StringUtils.isNotBlank(appModel.getUserCode())){
            sqlBuffer.append(" and user_code = '"+appModel.getUserCode()+"'");
        }
        if(StringUtils.isNotBlank(appModel.getMobile())){
            sqlBuffer.append(" and mobile = '"+appModel.getMobile()+"'");
        }

        return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 1;
/*				if(!StringUtil.isEmpty(QUERY_NAME)){
					ps.setString(i++, QUERY_NAME);
				}*/
            }
        }, new ResultSetExtractor<List<AppModel>>() {

            @Override
            public List<AppModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<AppModel> list=new ArrayList<AppModel>();
                while(rs.next()){
                    AppModel bean= new AppModel();
                    bean.setId(rs.getString("id"));
                    bean.setUserCode(rs.getString("user_code"));
                    bean.setMobile(rs.getString("mobile"));
                    bean.setCreate_time(rs.getString("create_time"));
                    list.add(bean);
                }
                return list;
            }
        });
    }

    @Override
    public int updateApp(AppModel appModel) {
        final StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" UPDATE t_app_user ");
        sqlBuffer.append(" SET mobile=?");
        sqlBuffer.append(" WHERE user_code = ?");

        return jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
                int i = 1;
                ps.setString(i++, appModel.getMobile());
                ps.setString(i++, appModel.getUserCode());
                return ps;
            }
        });
    }
}
