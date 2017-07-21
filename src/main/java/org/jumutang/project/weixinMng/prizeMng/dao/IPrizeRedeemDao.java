package org.jumutang.project.weixinMng.prizeMng.dao;


import com.alibaba.fastjson.JSONArray;
import org.jumutang.project.weixinMng.prizeMng.model.PrizeRedeem;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
public interface IPrizeRedeemDao {

    int savePrizeRedeem(PrizeRedeem prizeRedeem);

    int updatePrizeRedeem(PrizeRedeem prizeRedeem);

    List<PrizeRedeem> list(PrizeRedeem prizeRedeem);

    JSONArray prizeList();
}
