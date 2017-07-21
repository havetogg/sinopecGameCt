package org.jumutang.project.weixinMng.gameTwo.service;

import org.jumutang.project.weixinMng.gameTwo.model.GameTwoMode;
import org.jumutang.project.weixinMng.gameTwo.model.T2GameRecordMode;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 14:45 2017/7/12
 * @Modified By:
 */
public interface IGameTwoService {

    //保存游戏信息到gameTwo
    int saveUserGameTwo(MallUserMode bean);

    //查找GameTwo中是否有游戏信息
    GameTwoMode findUserGameTwoInfo(MallUserMode bean);

    //更新GameTwo用户信息--购买服务
    int updateUserGameTwo(MallUserMode bean);

    //更新用户扣钻石信息
    void updateUserGameTwoDiamond(MallUserMode bean);

    //保存游戏2的游戏记录
    int saveUserGameTwoRecord(MallUserMode bean);

    //更新游戏记录状态
    int updateUserGameTwoRecord(T2GameRecordMode t2GameRecordMode);

    //设置暴击能量
    int updateUserGameEnergyNum(MallUserMode bean,int num);

    //游戏2每日任务
    int updateGameTwoDay();

    //保存我知道了
    int updateGameTwoIKnow(MallUserMode bean);


    List<T2GameRecordMode> listT2GameRecordMode(MallUserMode bean);


}
