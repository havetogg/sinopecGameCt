package org.jumutang.project.weixinMng.mallMng.dao;

import org.jumutang.project.weixinMng.mallMng.model.RewardMode;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 16:57 2017/7/27
 * @Modified By:
 */
public interface IRewardModeDao {

    int saveRewardMode(RewardMode rewardMode);

    List<RewardMode> list(RewardMode rewardMode);

}
