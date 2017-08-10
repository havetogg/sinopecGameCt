package org.jumutang.project.weixinMng.gameThree.service;

import org.jumutang.project.weixinMng.gameThree.model.T3AttributeBuyMode;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 14:20 2017/8/4
 * @Modified By:
 */
public interface IT3AttributeBuyService {

    //游戏3购买信息保存
    int saveT3AttributeBuy(T3AttributeBuyMode t3AttributeBuyMode);

    List<T3AttributeBuyMode> T2AttributeBuyMode(T3AttributeBuyMode t3AttributeBuyMode);

}
