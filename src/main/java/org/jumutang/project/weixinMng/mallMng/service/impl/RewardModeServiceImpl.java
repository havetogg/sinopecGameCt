package org.jumutang.project.weixinMng.mallMng.service.impl;

import org.jumutang.project.weixinMng.mallMng.dao.IRewardModeDao;
import org.jumutang.project.weixinMng.mallMng.model.RewardMode;
import org.jumutang.project.weixinMng.mallMng.service.IRewardModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 14:22 2017/8/3
 * @Modified By:
 */
@Service
public class RewardModeServiceImpl implements IRewardModeService{

    @Autowired
    private IRewardModeDao rewardModeDao;

    @Override
    @Transactional
    public int saveRewardMode(RewardMode rewardMode) {
        return rewardModeDao.saveRewardMode(rewardMode);
    }

    @Override
    @Transactional
    public List<RewardMode> list(RewardMode rewardMode) {
        return rewardModeDao.list(rewardMode);
    }
}
