package org.jumutang.project.weixinMng.prizeMng.dao;

import org.jumutang.project.weixinMng.prizeMng.model.Prize;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 20:35 2017/7/11
 * @Modified By:
 */
public interface IPrizeDao {

    List<Prize> listPrize(Prize prize);

    int savePrize(Prize prize);

    int updatePrize(Prize prize);
}
