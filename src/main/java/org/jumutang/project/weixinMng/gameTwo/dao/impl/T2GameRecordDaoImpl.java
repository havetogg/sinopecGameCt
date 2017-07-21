package org.jumutang.project.weixinMng.gameTwo.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.weixinMng.gameTwo.dao.IT2GameRecordDao;
import org.jumutang.project.weixinMng.gameTwo.model.T2GameRecordMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
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
 * Created by Administrator on 2017/7/12.
 */
@Repository
public class T2GameRecordDaoImpl implements IT2GameRecordDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveT2GameRecordMode(T2GameRecordMode t2GameRecordMode) {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("insert into t2_game_record (user_id ,game_time ) ");
        stringBuffer.append("values (?,now())");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(stringBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++,t2GameRecordMode.getUserId());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
        }

    @Override
    public int updateT2GameRecordMode(T2GameRecordMode t2GameRecordMode) {
        final StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" UPDATE t2_game_record ");
        sqlBuffer.append(" SET id = "+t2GameRecordMode.getId());
        if(StringUtils.isNotBlank(t2GameRecordMode.getRefId())){
            sqlBuffer.append(",ref_id = "+t2GameRecordMode.getRefId());
        }
        if(StringUtils.isNotBlank(t2GameRecordMode.getGameTime())){
            sqlBuffer.append(",game_time = '"+t2GameRecordMode.getGameTime()+"'");
        }
        if(StringUtils.isNotBlank(t2GameRecordMode.getType())){
            sqlBuffer.append(",type = "+t2GameRecordMode.getType());
        }
        sqlBuffer.append(" WHERE id = "+t2GameRecordMode.getId());

        return jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
                int i = 1;
                return ps;
            }
        });
    }

    @Override
    public List<T2GameRecordMode> list(T2GameRecordMode t2GameRecordMode) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("select * from t2_game_record where  1=1 ");
        if(StringUtils.isNotBlank(t2GameRecordMode.getUserId())){
            stringBuffer.append("  and user_id =  "+t2GameRecordMode.getUserId());
        }
        if(StringUtils.isNotBlank(t2GameRecordMode.getRefId())){
            stringBuffer.append(" and ref_id =  "+t2GameRecordMode.getRefId());
        }
        if(StringUtils.isNotBlank(t2GameRecordMode.getType())){
            stringBuffer.append(" and type= "+t2GameRecordMode.getType());
        }
        if(StringUtils.isNotBlank(t2GameRecordMode.getId())){
            stringBuffer.append("  and id =  "+t2GameRecordMode.getId());
        }
        List<T2GameRecordMode> list = new ArrayList<T2GameRecordMode>();
        return this.jdbcTemplate.query(stringBuffer.toString(), new ResultSetExtractor<List<T2GameRecordMode>>() {
            @Override
            public List<T2GameRecordMode> extractData(ResultSet rs) throws SQLException, DataAccessException {

                List<T2GameRecordMode> list = new ArrayList<T2GameRecordMode>();

                while(rs.next()){

                    T2GameRecordMode t2 = new T2GameRecordMode();
                    t2.setId(rs.getString("id"));
                    t2.setUserId(rs.getString("user_id"));
                    t2.setGameTime(rs.getString("game_time"));
                    t2.setRefId(rs.getString("ref_id"));
                    t2.setType(rs.getString("type"));
                    list.add(t2);
                }

                return list;
            }
        });
    }
}
