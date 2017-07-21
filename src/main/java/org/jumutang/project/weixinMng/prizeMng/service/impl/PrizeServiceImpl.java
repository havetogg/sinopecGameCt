package org.jumutang.project.weixinMng.prizeMng.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jumutang.project.weixinMng.prizeMng.dao.IPrizeDao;
import org.jumutang.project.weixinMng.prizeMng.model.Prize;
import org.jumutang.project.weixinMng.prizeMng.service.IPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 20:54 2017/7/11
 * @Modified By:
 */
@Service
public class PrizeServiceImpl implements IPrizeService{

    private final static Logger log= LogManager.getLogger(PrizeServiceImpl.class);

    @Autowired
    private IPrizeDao iPrizeDao;

    @Override
    @Transactional
    public List<Prize> listPrize(Prize prize) {
        return iPrizeDao.listPrize(prize);
    }

    @Override
    @Transactional
    public int savePrize(Prize prize) {
        return iPrizeDao.savePrize(prize);
    }

    @Override
    @Transactional
    public void updatePrize(Prize prize) {
        iPrizeDao.updatePrize(prize);
    }
}
