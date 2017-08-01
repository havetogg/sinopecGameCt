package org.jumutang.project.weixinMng.prizeMng.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.jumutang.project.weixinMng.prizeMng.dao.IPrizePoolDao;
import org.jumutang.project.weixinMng.prizeMng.model.Prize;
import org.jumutang.project.weixinMng.prizeMng.model.PrizePool;
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
 * @Date: Create in 15:50 2017/7/20
 * @Modified By:
 */
@Repository
public class PrizePoolDaoImpl implements IPrizePoolDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int savePrizePool(PrizePool prizePool) {
        final StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" INSERT INTO t_prize_pool(user_id, prize_pool, used_pool, create_time) ");
        sqlBuffer.append(" VALUES(?,0,0,now())");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++, prizePool.getUserId());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public int updatePrizePool(PrizePool prizePool) {
        final StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("UPDATE t_prize_pool");
        sqlBuffer.append(" SET");
        if(StringUtils.isNotBlank(prizePool.getPrizePool())){
            sqlBuffer.append(" prize_pool = prize_pool+"+prizePool.getPrizePool());
        }
        if(StringUtils.isNotBlank(prizePool.getUsedPool())){
            sqlBuffer.append(" used_pool = used_pool+"+prizePool.getUsedPool());
        }
        sqlBuffer.append(" WHERE 1 = 1");
        if(StringUtils.isNotBlank(prizePool.getId())){
            sqlBuffer.append(" and id = "+prizePool.getId());
        }
        if(StringUtils.isNotBlank(prizePool.getUserId())){
            sqlBuffer.append(" and user_id = "+prizePool.getUserId());
        }

        return jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
                int i = 1;
                return ps;
            }
        });
    }

    @Override
    public List<PrizePool> list(PrizePool prizePool) {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" SELECT * FROM t_prize_pool WHERE 1=1");
        if(StringUtils.isNotBlank(prizePool.getId())){
            sqlBuffer.append(" AND id="+prizePool.getId());
        }
        if(StringUtils.isNotBlank(prizePool.getUserId())){
            sqlBuffer.append(" AND user_id= "+prizePool.getUserId());
        }
        sqlBuffer.append(" ORDER BY create_time DESC");
        return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 1;
            }

        }, new ResultSetExtractor<List<PrizePool>>() {

            @Override
            public List<PrizePool> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<PrizePool> prizePools = new ArrayList<>();
                while (rs.next()) {
                    PrizePool prizePool1 = new PrizePool();
                    prizePool1.setId(rs.getString("id"));
                    prizePool1.setUserId(rs.getString("user_id"));
                    prizePool1.setPrizePool(rs.getString("prize_pool"));
                    prizePool1.setUsedPool(rs.getString("used_pool"));
                    prizePool1.setCreateTime(rs.getString("create_time"));
                    prizePools.add(prizePool1);
                }
                return prizePools;
            }
        });
    }
}
