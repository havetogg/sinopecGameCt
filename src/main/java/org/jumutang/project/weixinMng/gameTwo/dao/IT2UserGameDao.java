package org.jumutang.project.weixinMng.gameTwo.dao;

import org.jumutang.project.weixinMng.gameTwo.model.GameTwoMode;
import org.jumutang.project.weixinMng.gameTwo.model.T2UserGameMode;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 15:19 2017/7/12
 * @Modified By:
 */
public interface IT2UserGameDao {

    int saveT2UserGameMode(T2UserGameMode t2UserGameMode);

    int updateT2UserGameMode(T2UserGameMode t2UserGameMode);

    List<T2UserGameMode> list(T2UserGameMode t2UserGameMode);

    //游戏2的每日任务
    int updateGameTwoDay();
}
