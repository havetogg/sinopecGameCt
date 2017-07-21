package org.jumutang.project.weixinMng.prizeMng.service;


import com.alibaba.fastjson.JSONArray;
import org.jumutang.project.weixinMng.prizeMng.model.PrizeRedeem;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */
public interface IPrizeRedeemService {

    int savePrizeRedeem(PrizeRedeem prizeRedeem);

    int updatePrizeRedeem(PrizeRedeem prizeRedeem);

    List<PrizeRedeem> list (PrizeRedeem prizeRedeem);

    //获取十位用户中奖信息
    JSONArray prizeList();

}
