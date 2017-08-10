package org.jumutang.project.weixinMng.gameThree.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.jumutang.project.weixinMng.gameThree.dao.T3UserGameDao;
import org.jumutang.project.weixinMng.gameThree.model.T3AttributeBuyMode;
import org.jumutang.project.weixinMng.gameThree.model.T3UserGameMode;
import org.jumutang.project.weixinMng.gameTwo.model.T2UserGameMode;
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
public class T3UserGameDaoImpl implements T3UserGameDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveT3UserGame(T3UserGameMode t3UserGameMode) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("insert into t3_user_game (user_id, game_all_times, energy_num, know, create_time) ");
        stringBuffer.append("values (?,0,0,0,now())");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(stringBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++, t3UserGameMode.getUserId());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public List<T3UserGameMode> list(T3UserGameMode t3UserGameMode) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from t3_user_game where 1=1 ");
        if(StringUtils.isNotBlank(t3UserGameMode.getId())){
            stringBuffer.append("and id = "+t3UserGameMode.getId());
        }
        if(StringUtils.isNotBlank(t3UserGameMode.getUserId())){
            stringBuffer.append("and user_id = "+t3UserGameMode.getUserId());
        }

        return this.jdbcTemplate.query(stringBuffer.toString(), new ResultSetExtractor<List<T3UserGameMode>>() {
            @Override
            public List<T3UserGameMode> extractData(ResultSet rs) throws SQLException, DataAccessException {

                List<T3UserGameMode> list = new ArrayList<T3UserGameMode>();

                while(rs.next()){
                    T3UserGameMode t3 = new T3UserGameMode();
                    t3.setId(rs.getString("id"));
                    t3.setUserId(rs.getString("user_id"));
                    t3.setGameAllTimes(rs.getString("game_all_times"));
                    t3.setLastGameTime(rs.getString("last_game_time"));
                    t3.setEnergyNum(rs.getString("energy_num"));
                    t3.setKnow(rs.getString("know"));
                    t3.setCreateTime(rs.getString("create_time"));
                    list.add(t3);
                }
                return list;
            }
        });
    }

    @Override
    public int updateT3UserGame(T3UserGameMode t3UserGameMode) {
        final StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" UPDATE t3_user_game ");
        sqlBuffer.append(" SET user_id = "+t3UserGameMode.getUserId());

        if(StringUtils.isNotBlank(t3UserGameMode.getGameAllTimes())){
            sqlBuffer.append(",game_all_times = "+t3UserGameMode.getGameAllTimes());
        }
        if(StringUtils.isNotBlank(t3UserGameMode.getLastGameTime())){
            sqlBuffer.append(",last_game_time = now()");
        }
        if(StringUtils.isNotBlank(t3UserGameMode.getEnergyNum())){
            sqlBuffer.append(",energy_num = "+t3UserGameMode.getEnergyNum());
        }
        if(StringUtils.isNotBlank(t3UserGameMode.getKnow())){
            sqlBuffer.append(",know = "+t3UserGameMode.getKnow());
        }
        sqlBuffer.append(" WHERE user_id = "+t3UserGameMode.getUserId());

        return jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
                int i = 1;
                return ps;
            }
        });
    }
}
