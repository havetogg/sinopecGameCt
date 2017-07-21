package org.jumutang.project.weixinMng.prizeMng.service.impl;


import com.alibaba.fastjson.JSONArray;
import org.jumutang.project.weixinMng.prizeMng.dao.IPrizeRedeemDao;
import org.jumutang.project.weixinMng.prizeMng.model.PrizeRedeem;
import org.jumutang.project.weixinMng.prizeMng.service.IPrizeRedeemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

@Service
public class PrizeRedeemServiceImpl implements IPrizeRedeemService {

    @Autowired
    private IPrizeRedeemDao prizeRedeemDao;

    @Override
    @Transactional
    public int savePrizeRedeem(PrizeRedeem prizeRedeem) {
        return prizeRedeemDao.savePrizeRedeem(prizeRedeem);
    }

    @Override
    @Transactional
    public int updatePrizeRedeem(PrizeRedeem prizeRedeem) {
        return prizeRedeemDao.updatePrizeRedeem(prizeRedeem);
    }

    @Override
    @Transactional
    public List<PrizeRedeem> list(PrizeRedeem prizeRedeem) {
        return prizeRedeemDao.list(prizeRedeem);
    }

    @Override
    public JSONArray prizeList() {
        return prizeRedeemDao.prizeList();
    }
}
