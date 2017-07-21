package org.jumutang.project.weixinMng.prizeMng.service.impl;

import org.jumutang.project.weixinMng.prizeMng.dao.IPrizePoolDao;
import org.jumutang.project.weixinMng.prizeMng.dao.IPrizeRedeemDao;
import org.jumutang.project.weixinMng.prizeMng.model.PrizePool;
import org.jumutang.project.weixinMng.prizeMng.service.IPrizePoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 16:33 2017/7/20
 * @Modified By:
 */
@Service
public class PrizePoolServiceImpl implements IPrizePoolService{

    @Autowired
    private IPrizePoolDao prizePoolDao;

    @Override
    @Transactional
    public int savePrizePool(PrizePool prizePool) {
        return prizePoolDao.savePrizePool(prizePool);
    }

    @Override
    @Transactional
    public int updatePrizePool(PrizePool prizePool) {
        return prizePoolDao.updatePrizePool(prizePool);
    }

    @Override
    @Transactional
    public List<PrizePool> list(PrizePool prizePool) {
        return prizePoolDao.list(prizePool);
    }
}
