package org.jumutang.project.weixinMng.gameTwo.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.weixinMng.gameTwo.dao.IT2AttributeBuyDao;
import org.jumutang.project.weixinMng.gameTwo.model.T2AttributeBuyMode;
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
 * Created by Administrator on 2017/7/12.
 */
@Repository
public class T2AttributeBuyDaoImpl implements IT2AttributeBuyDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveT2AttributeMode(T2AttributeBuyMode t2AttributeBuyMode) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("insert into t2_attribute_buy (user_id,buy_time ,pay_diamond) ");
        stringBuffer.append("values (?,now(),?)");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(stringBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++,t2AttributeBuyMode.getUserId());
                ps.setString(i++, t2AttributeBuyMode.getPayDiamond());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }


    @Override
    public List<T2AttributeBuyMode> list(T2AttributeBuyMode t2AttributeBuyMode) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from t2_attribute_buy where 1=1 ");
        if(StringUtils.isNotBlank(t2AttributeBuyMode.getId())){
            stringBuffer.append("and  id = "+t2AttributeBuyMode.getId());
        }
        if(StringUtils.isNotBlank(t2AttributeBuyMode.getUserId())){
            stringBuffer.append("and user_id = "+t2AttributeBuyMode.getUserId());
        }

        return this.jdbcTemplate.query(stringBuffer.toString(), new ResultSetExtractor<List<T2AttributeBuyMode>>() {
            @Override
            public List<T2AttributeBuyMode> extractData(ResultSet rs) throws SQLException, DataAccessException {

                List<T2AttributeBuyMode> list = new ArrayList<T2AttributeBuyMode>();

                while(rs.next()){
                    T2AttributeBuyMode t2 = new T2AttributeBuyMode();
                    t2.setId(rs.getString("id"));
                    t2.setUserId(rs.getString("user_id"));
                    t2.setPayDiamond(rs.getString("pay_diamond"));
                    t2.setBuyTime(rs.getString("buy_time"));
                    list.add(t2);
                }
                return list;
            }
        });
    }
}
