package org.jumutang.project.weixinMng.prizeMng.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jumutang.project.weixinMng.prizeMng.dao.IPrizeGradeDao;
import org.jumutang.project.weixinMng.prizeMng.model.PrizeGrade;
import org.jumutang.project.weixinMng.prizeMng.service.IPrizeGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 20:14 2017/7/11
 * @Modified By:
 */
@Service
public class PrizeGradeServiceImpl implements IPrizeGradeService{

    private final static Logger log= LogManager.getLogger(PrizeGradeServiceImpl.class);

    @Autowired
    private IPrizeGradeDao iPrizeGradeDao;

    @Override
    @Transactional
    public List<PrizeGrade> listPrizeGrade() {
        return iPrizeGradeDao.listPrizeGrade();
    }
}
