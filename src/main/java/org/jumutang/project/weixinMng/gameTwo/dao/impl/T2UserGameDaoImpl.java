package org.jumutang.project.weixinMng.gameTwo.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.weixinMng.gameTwo.dao.IT2UserGameDao;
import org.jumutang.project.weixinMng.gameTwo.model.T2UserGameMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 15:19 2017/7/12
 * @Modified By:
 */

@Repository
public class T2UserGameDaoImpl implements IT2UserGameDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveT2UserGameMode(T2UserGameMode t2UserGameMode) {

        StringBuffer stringBuffer= new StringBuffer();
        stringBuffer.append("insert into t2_user_game ");
        stringBuffer.append("(user_id,day_times ,game_used_times ,game_all_times ,energy_num,know,create_time)");

        stringBuffer.append("values (?,?,?,?,?,?,now())");

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(stringBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
                    int i = 1;
                    ps.setString(i++,t2UserGameMode.getUserId());
                    ps.setString(i++,t2UserGameMode.getDayTimes());
                    ps.setString(i++,t2UserGameMode.getGameUsedTimes());
                    ps.setString(i++,t2UserGameMode.getGameAllTimes());
                    ps.setString(i++,t2UserGameMode.getEnergyNum());
                    ps.setString(i++,t2UserGameMode.getKnow());
                    return ps;
                }
            }, keyHolder);

            return keyHolder.getKey().intValue();
    }

    @Override
    public int updateT2UserGameMode(T2UserGameMode t2UserGameMode) {
        final StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" UPDATE t2_user_game ");
        sqlBuffer.append(" SET user_id = "+t2UserGameMode.getUserId());
        if(StringUtils.isNotBlank(t2UserGameMode.getDayTimes())){
            sqlBuffer.append(",day_times = "+t2UserGameMode.getDayTimes());
        }
        if(StringUtils.isNotBlank(t2UserGameMode.getGameUsedTimes())){
            sqlBuffer.append(",game_used_times = "+t2UserGameMode.getGameUsedTimes());
        }
        if(StringUtils.isNotBlank(t2UserGameMode.getGameAllTimes())){
            sqlBuffer.append(",game_all_times = "+t2UserGameMode.getGameAllTimes());
        }
        if(StringUtils.isNotBlank(t2UserGameMode.getLastGameTime())){
            sqlBuffer.append(",last_game_time = '"+t2UserGameMode.getLastGameTime()+"'");
        }
        if(StringUtils.isNotBlank(t2UserGameMode.getEnergyNum())){
            sqlBuffer.append(",energy_num = "+t2UserGameMode.getEnergyNum());
        }
        if(StringUtils.isNotBlank(t2UserGameMode.getKnow())){
            sqlBuffer.append(",know = "+t2UserGameMode.getKnow());
        }
        sqlBuffer.append(" WHERE user_id = "+t2UserGameMode.getUserId());

        return jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
                int i = 1;
                return ps;
            }
        });
    }

    @Override
    public List<T2UserGameMode> list(T2UserGameMode t2UserGameMode) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from t2_user_game where  1=1   ");
        if(StringUtils.isNotBlank(t2UserGameMode.getId())){
            stringBuffer.append(" and id =   "+t2UserGameMode.getId());
        }
        if(StringUtils.isNotBlank(t2UserGameMode.getUserId())){
            stringBuffer.append(" and user_id =   "+t2UserGameMode.getUserId());
        }
        if(StringUtils.isNotBlank(t2UserGameMode.getDayTimes())){
            stringBuffer.append(" and day_times =   "+t2UserGameMode.getDayTimes());
        }
        if(StringUtils.isNotBlank(t2UserGameMode.getGameUsedTimes())){
            stringBuffer.append(" and game_used_times =  "+t2UserGameMode.getGameUsedTimes());
        }
        if(StringUtils.isNotBlank(t2UserGameMode.getGameAllTimes())){
            stringBuffer.append(" and game_all_times = "+t2UserGameMode.getGameAllTimes()+"");
        }
        if(StringUtils.isNotBlank(t2UserGameMode.getEnergyNum())){
            stringBuffer.append(" and energy_num =   "+ t2UserGameMode.getEnergyNum());
        }
        if(StringUtils.isNotBlank(t2UserGameMode.getCreateTime())){
            stringBuffer.append(" and create_time =  '"+t2UserGameMode.getCreateTime()+"'");
        }

        return this.jdbcTemplate.query(stringBuffer.toString(), new ResultSetExtractor<List<T2UserGameMode>>() {
            @Override
            public List<T2UserGameMode> extractData(ResultSet rs) throws SQLException, DataAccessException {

                List<T2UserGameMode> list = new ArrayList<T2UserGameMode>();

                while(rs.next()){
                    T2UserGameMode t2 = new T2UserGameMode();
                    t2.setId(rs.getString("id"));
                    t2.setUserId(rs.getString("user_id"));
                    t2.setDayTimes(rs.getString("day_times"));
                    t2.setGameUsedTimes(rs.getString("game_used_times"));
                    t2.setGameAllTimes(rs.getString("game_all_times"));
                    t2.setLastGameTime(rs.getString("last_game_time"));
                    t2.setEnergyNum(rs.getString("energy_num"));
                    t2.setKnow(rs.getString("know"));
                    t2.setCreateTime(rs.getString("create_time"));
                    list.add(t2);
                }

                return list;
            }
        });
    }

    @Override
    public int updateGameTwoDay() {
        final StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("UPDATE t2_user_game SET day_times=1");

        return jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
                int i = 1;

                return ps;
            }
        });
    }
}
