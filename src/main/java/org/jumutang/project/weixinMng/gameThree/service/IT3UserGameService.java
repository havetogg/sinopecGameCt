package org.jumutang.project.weixinMng.gameThree.service;

import org.jumutang.project.weixinMng.gameThree.model.T3UserGameMode;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 10:44 2017/8/3
 * @Modified By:
 */
public interface IT3UserGameService {

    int saveT3UserGame(T3UserGameMode t3UserGameMode);

    List<T3UserGameMode> list(T3UserGameMode t3UserGameMode);

    int updateT3UserGame(T3UserGameMode t3UserGameMode);

    int updateT3UserGameOnce(String userId);
}
