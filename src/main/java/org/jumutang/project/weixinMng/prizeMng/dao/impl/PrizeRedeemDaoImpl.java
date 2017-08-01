package org.jumutang.project.weixinMng.prizeMng.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jumutang.project.tools.PhoneUtil;
import org.jumutang.project.weixinMng.prizeMng.dao.IPrizeRedeemDao;
import org.jumutang.project.weixinMng.prizeMng.model.PrizeRedeem;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
@Repository
public class PrizeRedeemDaoImpl implements IPrizeRedeemDao {


    private Logger _logger = Logger.getLogger(PrizeRedeemDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate ;

    @Override
    public int savePrizeRedeem(PrizeRedeem prizeRedeem) {


        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("insert into t_prize_redeem (user_id ,prize_id ,redeem_code ,winning_time,end_time,status) values");
        stringBuffer.append(" (?,?,?,now(),date_sub(now(),interval -1 month),0) ");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(stringBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++,prizeRedeem.getUserId());
                ps.setString(i++,prizeRedeem.getPrizeId());
                ps.setString(i++,prizeRedeem.getRedeemCode());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }


    @Override
    public int updatePrizeRedeem(PrizeRedeem prizeRedeem) {

        final StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" UPDATE t_prize_redeem ");
        sqlBuffer.append(" SET id = "+prizeRedeem.getId());
        if(StringUtils.isNotBlank(prizeRedeem.getReceiveTime())){
            sqlBuffer.append(",receive_time = now()");
        }
        if(StringUtils.isNotBlank(prizeRedeem.getStatus())){
            sqlBuffer.append(",status = "+prizeRedeem.getStatus());
        }
        if(StringUtils.isNotBlank(prizeRedeem.getRedpkgId())){
            sqlBuffer.append(",redpkg_id = '"+prizeRedeem.getRedpkgId()+"'");
        }
        if(StringUtils.isNotBlank(prizeRedeem.getOilOpenId())){
            sqlBuffer.append(",oil_open_id = '"+prizeRedeem.getOilOpenId()+"'");
        }
        sqlBuffer.append(" WHERE id = "+prizeRedeem.getId());

        _logger.info("更新语句为:"+sqlBuffer);
        return jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
                int i = 1;
                return ps;
            }
        });
    }

    @Override
    public List<PrizeRedeem> list(PrizeRedeem prizeRedeem) {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from t_prize_redeem where 1=1 ");
        if(StringUtils.isNotBlank(prizeRedeem.getId())){
            stringBuffer.append(" AND id="+prizeRedeem.getId());
        }
        if(StringUtils.isNotBlank(prizeRedeem.getUserId())){
            stringBuffer.append(" AND user_id="+prizeRedeem.getUserId());
        }
        if(StringUtils.isNotBlank(prizeRedeem.getPrizeId())){
            stringBuffer.append(" AND prize_id="+prizeRedeem.getPrizeId());
        }
        if(StringUtils.isNotBlank(prizeRedeem.getStatus())){
            stringBuffer.append(" AND status="+prizeRedeem.getStatus());
        }
        if(StringUtils.isNotBlank(prizeRedeem.getRedeemCode())){
            stringBuffer.append(" AND redeem_code= '"+prizeRedeem.getRedeemCode()+"'");
        }
        if(StringUtils.isNotBlank(prizeRedeem.getEndTime())){
            stringBuffer.append(" and end_time >= NOW()");
        }

        stringBuffer.append(" order by winning_time desc");
        _logger.info("查询当前prizeRedeem的SQL语句:"+stringBuffer.toString());

        return this.jdbcTemplate.query(stringBuffer.toString(), new ResultSetExtractor<List<PrizeRedeem>>() {
            @Override
            public List<PrizeRedeem> extractData(ResultSet res) throws SQLException, DataAccessException {

                List<PrizeRedeem> list = new ArrayList<PrizeRedeem>();

                while(res.next()){
                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    PrizeRedeem prize = new PrizeRedeem();
                    prize.setId(res.getString("id"));
                    prize.setUserId(res.getString("user_id"));
                    prize.setPrizeId(res.getString("prize_id"));
                    prize.setRedeemCode(res.getString("redeem_code"));
                    try{
                        prize.setWinningTime(StringUtils.isNotBlank(res.getString("winning_time"))?sDateFormat.format(sDateFormat.parse(res.getString("winning_time"))):" ");
                        prize.setEndTime(StringUtils.isNotBlank(res.getString("end_time"))?sDateFormat.format(sDateFormat.parse(res.getString("end_time"))):" ");
                        prize.setReceiveTime(StringUtils.isNotBlank(res.getString("receive_time"))?sDateFormat.format(sDateFormat.parse(res.getString("receive_time"))):" ");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    prize.setStatus(res.getString("status"));
                    prize.setRedpkgId(res.getString("redpkg_id"));
                    prize.setOilOpenId(res.getString("oil_open_id"));
                    list.add(prize);
                }

                return list;
            }
        });
    }

    @Override
    public JSONArray prizeList() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SELECT a.NICK_NAME,a.MOBILE,b.prize_name from t_mall_user a INNER JOIN t_prize_redeem c on a.ID = c.user_id INNER JOIN t_prize b on c.prize_id = b.id ORDER BY c.winning_time DESC LIMIT 0,10");

        return this.jdbcTemplate.query(stringBuffer.toString(), new ResultSetExtractor<JSONArray>() {
            @Override
            public JSONArray extractData(ResultSet res) throws SQLException, DataAccessException {

                JSONArray jsonArray = new JSONArray();

                while(res.next()){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("nickName",res.getString("NICK_NAME"));
                    jsonObject.put("mobile", PhoneUtil.phoneSecrecy(res.getString("MOBILE")));
                    jsonObject.put("prizeName",res.getString("prize_name"));
                    jsonArray.add(jsonObject);
                }
                return jsonArray;
            }
        });
    }
}
