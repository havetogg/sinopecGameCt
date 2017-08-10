package org.jumutang.project.weixinMng.gameThree.dao;

import org.jumutang.project.weixinMng.gameThree.model.T3AttributeBuyMode;
import org.jumutang.project.weixinMng.gameThree.model.T3UserGameMode;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 11:51 2017/7/27
 * @Modified By:
 */
public interface T3UserGameDao {

    int saveT3UserGame(T3UserGameMode t3UserGameMode);

    List<T3UserGameMode> list(T3UserGameMode t3UserGameMode);

    int updateT3UserGame(T3UserGameMode t3UserGameMode);
}
