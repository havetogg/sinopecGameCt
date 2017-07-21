package org.jumutang.project.weixinMng.gameTwo.service;

import org.jumutang.project.weixinMng.gameTwo.model.T2AttributeBuyMode;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 19:17 2017/7/13
 * @Modified By:
 */
public interface IT2AttributeBuyService {

    //游戏2购买信息保存
    int saveT2AttributeBuy(MallUserMode bean);

    List<T2AttributeBuyMode> T2AttributeBuyMode(T2AttributeBuyMode t2AttributeBuyMode);
}
