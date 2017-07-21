package org.jumutang.project.weixinMng.prizeMng.service;

import org.jumutang.project.weixinMng.prizeMng.model.Prize;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 20:53 2017/7/11
 * @Modified By:
 */
public interface IPrizeService {

    List<Prize> listPrize(Prize prize);

    int savePrize(Prize prize);

    void updatePrize(Prize prize);
}
