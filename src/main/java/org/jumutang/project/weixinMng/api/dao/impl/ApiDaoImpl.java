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

    @Override
    public JSONObject queryTotalApi() {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("SELECT count(*) as total,sum(case when t.USED_DIAMOND >0 then 1 else 0 end) as total_diamond,sum(case when t.MOBILE is null then 0 else 1 end) as total_mobile,p.prize_pool as total_prize from t_mall_user t,t_prize_pool p where p.id='1'");

        return jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 1;
/*				if(!StringUtil.isEmpty(QUERY_NAME)){
					ps.setString(i++, QUERY_NAME);
				}*/
            }
        }, new ResultSetExtractor<JSONObject>() {

            @Override
            public JSONObject extractData(ResultSet rs) throws SQLException, DataAccessException {
                while(rs.next()){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("total",rs.getString("total"));
                    jsonObject.put("total_diamond",rs.getString("total_diamond"));
                    jsonObject.put("total_mobile",rs.getString("total_mobile"));
                    jsonObject.put("total_prize",rs.getString("total_prize"));
                    return jsonObject;
                }
                return null;
            }
        });
    }

    @Override
    public JSONObject queryTodayApi() {
        JSONObject jsonObject = new JSONObject();
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("SELECT count(*) as today_total,case when sum(case when t.USED_DIAMOND >0 then 1 else 0 end) is null then 0 else sum(case when t.USED_DIAMOND >0 then 1 else 0 end) end as today_diamond,case when sum(case when t.MOBILE is null then 0 else 1 end) is null then 0 else sum(case when t.MOBILE is null then 0 else 1 end) end as today_mobile ");
        sqlBuffer.append("from t_mall_user t where date_format(now(),'%y-%m-%d') = date_format(t.CREAT_TIME,'%y-%m-%d')");
        jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 1;
            }
        }, new ResultSetExtractor<JSONObject>() {

            @Override
            public JSONObject extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    jsonObject.put("today_total", rs.getString("today_total"));
                    jsonObject.put("today_diamond", rs.getString("today_diamond"));
                    jsonObject.put("today_mobile", rs.getString("today_mobile"));
                    return jsonObject;
                }
                return null;
            }
        });

        StringBuffer sqlBuffer2 = new StringBuffer();
        sqlBuffer2.append("SELECT case when COUNT(*)=0 then 0 else sum(t.PAY_MONEY) end as today_prize ");
        sqlBuffer2.append("from t_change_order t where date_format(now(),'%y-%m-%d') = date_format(t.CREAT_TIME,'%y-%m-%d') and t.PAYED_FLAG='1'");
        jdbcTemplate.query(sqlBuffer2.toString(), new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps  ) throws SQLException {
                int i = 1;
            }
        }, new ResultSetExtractor<JSONObject>() {

            @Override
            public JSONObject extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    jsonObject.put("today_prize", rs.getString("today_prize"));
                    return jsonObject;
                }
                return null;
            }
        });

        return jsonObject;
    }
}
