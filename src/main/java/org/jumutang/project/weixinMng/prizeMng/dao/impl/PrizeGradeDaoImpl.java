package org.jumutang.project.weixinMng.prizeMng.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.jumutang.project.weixinMng.prizeMng.dao.IPrizeGradeDao;
import org.jumutang.project.weixinMng.prizeMng.model.DiamondRecord;
import org.jumutang.project.weixinMng.prizeMng.model.PrizeGrade;
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

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 20:07 2017/7/11
 * @Modified By:
 */
@Repository
public class PrizeGradeDaoImpl implements IPrizeGradeDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<PrizeGrade> listPrizeGrade() {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" SELECT id, grade");
        sqlBuffer.append("	FROM t_prize_grade");
        sqlBuffer.append("	WHERE 1=1");
        return this.jdbcTemplate.query(sqlBuffer.toString(), new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 1;
            }

        }, new ResultSetExtractor<List<PrizeGrade>>() {

            @Override
            public List<PrizeGrade> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<PrizeGrade> prizeGrades = new ArrayList<>();
                while (rs.next()) {
                    PrizeGrade prizeGrade = new PrizeGrade();
                    prizeGrade.setId(rs.getString("id"));
                    prizeGrade.setGrade(rs.getString("grade"));
                }
                return prizeGrades;
            }
        });
    }
}
