package org.jumutang.project.weixinMng.mallMng.service;

import org.jumutang.project.weixinMng.mallMng.model.RewardMode;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 14:21 2017/8/3
 * @Modified By:
 */
public interface IRewardModeService {

    int saveRewardMode(RewardMode rewardMode);

    List<RewardMode> list(RewardMode rewardMode);

}
