package org.jumutang.project.weixinMng.gameThree.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.jumutang.project.weixinMng.gameThree.dao.T3AttributeBuyDao;
import org.jumutang.project.weixinMng.gameThree.model.T3AttributeBuyMode;
import org.jumutang.project.weixinMng.gameTwo.model.T2AttributeBuyMode;
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
 * @Date: Create in 11:51 2017/7/27
 * @Modified By:
 */
@Repository
public class T3AttributeBuyDaoImpl implements T3AttributeBuyDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveT3AttributeMode(T3AttributeBuyMode t3AttributeBuyMode) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("insert into t3_attribute_buy (user_id,buy_time ,pay_diamond) ");
        stringBuffer.append("values (?,now(),?)");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(stringBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++, t3AttributeBuyMode.getUserId());
                ps.setString(i++, t3AttributeBuyMode.getPayDiamond());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public List<T3AttributeBuyMode> list(T3AttributeBuyMode t3AttributeBuyMode) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from t3_attribute_buy where 1=1 ");
        if(StringUtils.isNotBlank(t3AttributeBuyMode.getId())){
            stringBuffer.append("and  id = "+t3AttributeBuyMode.getId());
        }
        if(StringUtils.isNotBlank(t3AttributeBuyMode.getUserId())){
            stringBuffer.append("and user_id = "+t3AttributeBuyMode.getUserId());
        }

        return this.jdbcTemplate.query(stringBuffer.toString(), new ResultSetExtractor<List<T3AttributeBuyMode>>() {
            @Override
            public List<T3AttributeBuyMode> extractData(ResultSet rs) throws SQLException, DataAccessException {

                List<T3AttributeBuyMode> list = new ArrayList<T3AttributeBuyMode>();

                while(rs.next()){
                    T3AttributeBuyMode t3 = new T3AttributeBuyMode();
                    t3.setId(rs.getString("id"));
                    t3.setUserId(rs.getString("user_id"));
                    t3.setPayDiamond(rs.getString("pay_diamond"));
                    t3.setBuyTime(rs.getString("buy_time"));
                    list.add(t3);
                }
                return list;
            }
        });
    }
}
