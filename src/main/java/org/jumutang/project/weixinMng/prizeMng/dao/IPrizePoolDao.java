package org.jumutang.project.weixinMng.prizeMng.dao;

import org.jumutang.project.weixinMng.prizeMng.model.PrizePool;
import org.jumutang.project.weixinMng.prizeMng.model.PrizeRedeem;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 15:44 2017/7/20
 * @Modified By:
 */
public interface IPrizePoolDao {

    int savePrizePool(PrizePool prizePool);

    int updatePrizePool(PrizePool prizePool);

    List<PrizePool> list(PrizePool prizePool);
}
