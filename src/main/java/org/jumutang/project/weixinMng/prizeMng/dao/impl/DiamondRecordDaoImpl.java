package org.jumutang.project.weixinMng.prizeMng.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.jumutang.project.weixinMng.mallMng.model.Change_goldMngMode;
import org.jumutang.project.weixinMng.prizeMng.dao.IDiamondRecordDao;
import org.jumutang.project.weixinMng.prizeMng.model.DiamondRecord;
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
 * @Date: Create in 18:22 2017/7/11
 * @Modified By:
 */
@Repository
public class DiamondRecordDaoImpl implements IDiamondRecordDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveDiamondRecord(DiamondRecord diamondRecord) {
        final StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" INSERT INTO t_diamond_record(game_id, user_id, diamonds, create_time,status) ");
        sqlBuffer.append(" VALUES(?,?,?,now(),0)");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlBuffer.toString(), Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++, diamondRecord.getGameId());
                ps.setString(i++, diamondRecord.getUserId());
                ps.setString(i++, diamondRecord.getDiamonds());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public List<DiamondRecord> listDiamondRecord(DiamondRecord diamondRecord) {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" select * from t_diamond_record where 1=1 ");
        if(StringUtils.isNotBlank(diamondRecord.getUserId())){
            sqlBuffer.append(" AND user_id="+diamondRecord.getUserId());
        }
        if(StringUtils.isNotBlank(diamondRecord.getGameId())){
            sqlBuffer.append(" AND game_id="+diamondRecord.getGameId());
        }
        if(StringUtils.isNotBlank(diamondRecord.getCreateTime())){
            //sqlBuffer.append(" AND create_time="+diamondRecord.getCreateTime());
            //DATE_FORMAT(create_time,'%Y-%m-%d')='2017-07-11'
            sqlBuffer.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d')='"+diamondRecord.getCreateTime()+"' ");
        }
        if(StringUtils.isNotBlank(diamondRecord.getStatus())){
            sqlBuffer.append(" AND status="+diamondRecord.getStatus());
        }
        sqlBuffer.append(" ORDER BY create_time DESC ");


//        List<DiamondRecord> list = new ArrayList<>();

           // String sql1 =" select * from t_diamond_record where 1=1  AND user_id=32 AND game_id=1 AND DATE_FORMAT(create_time,'%Y-%m-%d')='2017-07-13'";

            return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    int i = 1;
                }

            }, new ResultSetExtractor<List<DiamondRecord>>() {
                @Override
                public List<DiamondRecord> extractData(ResultSet rs) throws SQLException, DataAccessException {
                    List<DiamondRecord> diamondRecords = new ArrayList<>();
                    while (rs.next()) {
                        DiamondRecord diamondRecord = new DiamondRecord();
                        diamondRecord.setId(rs.getString("id"));
                        diamondRecord.setGameId(rs.getString("game_id"));
                        diamondRecord.setUserId(rs.getString("user_id"));
                        diamondRecord.setDiamonds(rs.getString("diamonds"));
                        diamondRecord.setCreateTime(rs.getString("create_time"));
                        diamondRecord.setStatus(rs.getString("status"));
                        diamondRecords.add(diamondRecord);
                    }
                    return diamondRecords;
                }
            });



    }
}
