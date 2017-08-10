package org.jumutang.project.weixinMng.api.dao.impl;

import com.alibaba.fastjson.JSONObject;
import org.jumutang.project.weixinMng.api.dao.IApiDao;
import org.jumutang.project.weixinMng.mallMng.model.Change_goldMngMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 15:01 2017/8/1
 * @Modified By:
 */

@Repository
public class ApiDaoImpl implements IApiDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<JSONObject> queryAllUserApi() {
        // TODO Auto-generated method stub
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" select a.OPEN_ID as open_id,a.MOBILE as mobile,a.CREAT_TIME as create_time,(case when b.prize_pool is null then 0 else b.prize_pool end) as prize_pool,(case when b.used_pool is null then 0 else b.used_pool end) as used_pool");
        sqlBuffer.append(" from t_mall_user a LEFT JOIN t_prize_pool b on a.ID = b.user_id where a.mobile is not null order by a.CREAT_TIME");

        return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 1;
/*				if(!StringUtil.isEmpty(QUERY_NAME)){
					ps.setString(i++, QUERY_NAME);
				}*/
            }
        }, new ResultSetExtractor<List<JSONObject>>() {

            @Override
            public List<JSONObject> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<JSONObject> jsonObjects = new ArrayList<>();
                while(rs.next()){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("open_id",rs.getString("open_id"));
                    jsonObject.put("mobile",rs.getString("mobile"));
                    jsonObject.put("create_time",rs.getString("create_time"));
                    jsonObject.put("prize_pool",rs.getString("prize_pool"));
                    jsonObject.put("used_pool",rs.getString("used_pool"));
                    jsonObjects.add(jsonObject);
                }
                return jsonObjects;
            }
        });
    }
}
