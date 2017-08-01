package org.jumutang.project.weixinMng.mallMng.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.jumutang.project.weixinMng.mallMng.dao.IRewardModeDao;
import org.jumutang.project.weixinMng.mallMng.model.RewardMode;
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
 * @Date: Create in 16:58 2017/7/27
 * @Modified By:
 */
@Repository
public class IRewardModeDaoImpl implements IRewardModeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int saveRewardMode(RewardMode rewardMode) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("insert into t_reward (user_id,reward_type ,reward_name,create_time) ");
        stringBuffer.append("values (?,?,?,now())");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(stringBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++,rewardMode.getUserId());
                ps.setString(i++,rewardMode.getRewardType());
                ps.setString(i++,rewardMode.getRewardName());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public List<RewardMode> list(RewardMode rewardMode) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from t_reward where 1=1");
        if(StringUtils.isNotBlank(rewardMode.getId())){
            stringBuffer.append(" and id = "+rewardMode.getId());
        }
        if(StringUtils.isNotBlank(rewardMode.getUserId())){
            stringBuffer.append(" and user_id = "+rewardMode.getUserId());
        }
        if(StringUtils.isNotBlank(rewardMode.getRewardType())){
            stringBuffer.append(" and reward_type = "+rewardMode.getRewardType());
        }

        return this.jdbcTemplate.query(stringBuffer.toString(), new ResultSetExtractor<List<RewardMode>>() {
            @Override
            public List<RewardMode> extractData(ResultSet rs) throws SQLException, DataAccessException {

                List<RewardMode> list = new ArrayList<RewardMode>();

                while(rs.next()){
                    RewardMode rewardMode1 = new RewardMode();
                    rewardMode1.setId(rs.getString("id"));
                    rewardMode1.setUserId(rs.getString("user_id"));
                    rewardMode1.setRewardType(rs.getString("reward_type"));
                    rewardMode1.setRewardName(rs.getString("reward_name"));
                    rewardMode1.setCreateTime(rs.getString("create_time"));
                    list.add(rewardMode1);
                }
                return list;
            }
        });
    }
}
