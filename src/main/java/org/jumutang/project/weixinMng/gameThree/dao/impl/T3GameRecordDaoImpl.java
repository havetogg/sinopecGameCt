package org.jumutang.project.weixinMng.gameThree.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.jumutang.project.weixinMng.gameThree.dao.T3GameRecordDao;
import org.jumutang.project.weixinMng.gameThree.model.T3AttributeBuyMode;
import org.jumutang.project.weixinMng.gameThree.model.T3GameRecordMode;
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
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 11:52 2017/7/27
 * @Modified By:
 */
@Repository
public class T3GameRecordDaoImpl implements T3GameRecordDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveT3GameRecord(T3GameRecordMode t3GameRecordMode) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("insert into t3_game_record (user_id,game_time,game_type)");
        stringBuffer.append("values (?,now(),?)");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(stringBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++, t3GameRecordMode.getUserId());
                ps.setString(i++, t3GameRecordMode.getGameType());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public List<T3GameRecordMode> list(T3GameRecordMode t3GameRecordMode) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from t3_game_record where 1=1 ");
        if(StringUtils.isNotBlank(t3GameRecordMode.getId())){
            stringBuffer.append("and  id = "+t3GameRecordMode.getId());
        }
        if(StringUtils.isNotBlank(t3GameRecordMode.getUserId())){
            stringBuffer.append("and user_id = "+t3GameRecordMode.getUserId());
        }

        return this.jdbcTemplate.query(stringBuffer.toString(), new ResultSetExtractor<List<T3GameRecordMode>>() {
            @Override
            public List<T3GameRecordMode> extractData(ResultSet rs) throws SQLException, DataAccessException {

                List<T3GameRecordMode> list = new ArrayList<T3GameRecordMode>();

                while(rs.next()){
                    T3GameRecordMode t3 = new T3GameRecordMode();
                    t3.setId(rs.getString("id"));
                    t3.setUserId(rs.getString("user_id"));
                    t3.setGameTime(rs.getString("game_time"));
                    t3.setGameType(rs.getString("game_type"));
                    t3.setRefId(rs.getString("ref_id"));
                    t3.setPrizeType(rs.getString("prize_type"));
                    list.add(t3);
                }
                return list;
            }
        });
    }

    @Override
    public int updateT3GameRecord(T3GameRecordMode t3GameRecordMode) {
        final StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" UPDATE t3_game_record ");
        sqlBuffer.append(" SET id = "+t3GameRecordMode.getId());

        if(StringUtils.isNotBlank(t3GameRecordMode.getRefId())){
            sqlBuffer.append(",ref_id = "+t3GameRecordMode.getRefId());
        }
        if(StringUtils.isNotBlank(t3GameRecordMode.getPrizeType())){
            sqlBuffer.append(",prize_type = "+t3GameRecordMode.getPrizeType());
        }
        sqlBuffer.append(" WHERE id = "+t3GameRecordMode.getId());

        return jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
                int i = 1;
                return ps;
            }
        });
    }
}
