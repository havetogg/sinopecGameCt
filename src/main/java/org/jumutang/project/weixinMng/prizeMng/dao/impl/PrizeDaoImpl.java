package org.jumutang.project.weixinMng.prizeMng.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.jumutang.project.weixinMng.prizeMng.dao.IPrizeDao;
import org.jumutang.project.weixinMng.prizeMng.model.Prize;
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
 * @Date: Create in 20:36 2017/7/11
 * @Modified By:
 */
@Repository
public class PrizeDaoImpl implements IPrizeDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Prize> listPrize(Prize prize) {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" SELECT id,type, grade, prize_name, img_url, stock, sales, create_time, flag FROM t_prize WHERE 1=1");
        if(StringUtils.isNotBlank(prize.getId())){
            sqlBuffer.append(" AND id="+prize.getId());
        }
        if(StringUtils.isNotBlank(prize.getGrade())){
            sqlBuffer.append(" AND grade <= "+prize.getGrade());
        }
        sqlBuffer.append(" AND flag = 0");
        sqlBuffer.append(" ORDER BY create_time DESC");
        return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 1;
            }

        }, new ResultSetExtractor<List<Prize>>() {

            @Override
            public List<Prize> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Prize> prizes = new ArrayList<>();
                while (rs.next()) {
                    Prize prize1 = new Prize();
                    prize1.setId(rs.getString("id"));
                    prize1.setType(rs.getString("type"));
                    prize1.setGrade(rs.getString("grade"));
                    prize1.setPrizeName(rs.getString("prize_name"));
                    prize1.setImgUrl(rs.getString("img_url"));
                    prize1.setStock(rs.getString("stock"));
                    prize1.setSales(rs.getString("sales"));
                    prize1.setCreateTime(rs.getString("create_time"));
                    prize1.setFlag(rs.getString("flag"));
                    prizes.add(prize1);
                }
                return prizes;
            }
        });
    }

    @Override
    public int savePrize(Prize prize) {
        final StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" INSERT INTO t_prize(type,grade, prize_name, img_url, stock, sales, create_time, flag) ");
        sqlBuffer.append(" VALUES(?,?,?,?,?,0,now(),0)");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++, prize.getType());
                ps.setString(i++, prize.getGrade());
                ps.setString(i++, prize.getPrizeName());
                ps.setString(i++, prize.getImgUrl());
                ps.setString(i++, prize.getStock());


                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public int updatePrize(Prize prize) {
        final StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" UPDATE t_prize ");
        sqlBuffer.append(" SET type=?,grade=?,prize_name=?,img_url=?,stock=?,sales=?,create_time=?,flag=?");
        sqlBuffer.append(" WHERE ID = ?");

        return jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
                int i = 1;
                ps.setString(i++, prize.getType());
                ps.setString(i++, prize.getGrade());
                ps.setString(i++, prize.getPrizeName());
                ps.setString(i++, prize.getImgUrl());
                ps.setString(i++, prize.getStock());
                ps.setString(i++, prize.getSales());
                ps.setString(i++, prize.getCreateTime());
                ps.setString(i++, prize.getFlag());
                ps.setString(i++, prize.getId());
                return ps;
            }
        });
    }
}
