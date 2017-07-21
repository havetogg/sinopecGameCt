package org.jumutang.project.weixinMng.gameTwo.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jumutang.project.tools.DateUtil;
import org.jumutang.project.weixinMng.gameTwo.dao.IT2GameRecordDao;
import org.jumutang.project.weixinMng.gameTwo.dao.IT2UserGameDao;
import org.jumutang.project.weixinMng.gameTwo.model.GameTwoMode;
import org.jumutang.project.weixinMng.gameTwo.model.T2GameRecordMode;
import org.jumutang.project.weixinMng.gameTwo.model.T2UserGameMode;
import org.jumutang.project.weixinMng.gameTwo.service.IGameTwoService;
import org.jumutang.project.weixinMng.mallMng.dao.IManageDao;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 15:00 2017/7/12
 * @Modified By:
 */
@Service
public class GameTwoServiceImpl implements IGameTwoService{

    private final static Logger log= LogManager.getLogger(GameTwoServiceImpl.class);

    @Autowired
    private IManageDao manageDao;

    @Autowired
    private IT2UserGameDao it2UserGameDao;

    @Autowired
    private IT2GameRecordDao it2GameRecordDao;

    @Override
    @Transactional
    public int saveUserGameTwo(MallUserMode bean) {
        T2UserGameMode queryT2UserGameMode = new T2UserGameMode();
        queryT2UserGameMode.setUserId(bean.getID());
        List<T2UserGameMode> t2UserGameModes = it2UserGameDao.list(queryT2UserGameMode);
        if(t2UserGameModes.size() == 0){
            T2UserGameMode t2UserGameMode = new T2UserGameMode();
            t2UserGameMode.setUserId(bean.getID());
            t2UserGameMode.setDayTimes("1");
            t2UserGameMode.setGameUsedTimes("0");
            t2UserGameMode.setGameAllTimes("0");
            t2UserGameMode.setLastGameTime("");
            t2UserGameMode.setEnergyNum("0");
            t2UserGameMode.setKnow("0");
            t2UserGameMode.setCreateTime(DateUtil.get4yMdHms(new Date()));
            return it2UserGameDao.saveT2UserGameMode(t2UserGameMode);
        }
        return 0;
    }

    @Override
    @Transactional
    public GameTwoMode findUserGameTwoInfo(MallUserMode bean) {
        T2UserGameMode queryT2UserGameMode = new T2UserGameMode();
        queryT2UserGameMode.setUserId(bean.getID());
        T2UserGameMode t2UserGameMode = it2UserGameDao.list(queryT2UserGameMode).get(0);
        GameTwoMode gameTwoMode = new GameTwoMode();
        gameTwoMode.setTodayRemainTimes(t2UserGameMode.getDayTimes());
        gameTwoMode.setEnergyNum(t2UserGameMode.getEnergyNum());
        gameTwoMode.setKnow(t2UserGameMode.getKnow());
        return gameTwoMode;
    }


    @Override
    @Transactional
    public int updateUserGameTwo(MallUserMode bean) {
        T2UserGameMode queryT2UserGameMode = new T2UserGameMode();
        queryT2UserGameMode.setUserId(bean.getID());
        T2UserGameMode t2UserGameMode = it2UserGameDao.list(queryT2UserGameMode).get(0);
        t2UserGameMode.setDayTimes("0");
        t2UserGameMode.setGameUsedTimes(String.valueOf(Integer.parseInt(t2UserGameMode.getGameUsedTimes())+1));
        t2UserGameMode.setGameAllTimes(String.valueOf(Integer.parseInt(t2UserGameMode.getGameAllTimes())+1));
        t2UserGameMode.setLastGameTime(DateUtil.get4yMdHms(new Date()));
        t2UserGameMode.setUserId(bean.getID());
        return it2UserGameDao.updateT2UserGameMode(t2UserGameMode);
    }


    @Override
    @Transactional
    public void updateUserGameTwoDiamond(MallUserMode bean) {
        manageDao.update_UserInfo_Used_diamond(bean);
    }

    @Override
    @Transactional
    public int saveUserGameTwoRecord(MallUserMode bean) {
        T2GameRecordMode t2GameRecordMode = new T2GameRecordMode();
        t2GameRecordMode.setUserId(bean.getID());
        return it2GameRecordDao.saveT2GameRecordMode(t2GameRecordMode);
    }

    @Override
    @Transactional
    public int updateUserGameTwoRecord(T2GameRecordMode t2GameRecordMode) {
        return it2GameRecordDao.updateT2GameRecordMode(t2GameRecordMode);
    }

    @Override
    @Transactional
    public List<T2GameRecordMode> listT2GameRecordMode(MallUserMode bean) {
        T2GameRecordMode t2GameRecordMode = new T2GameRecordMode();
        t2GameRecordMode.setUserId(bean.getID());
        return it2GameRecordDao.list(t2GameRecordMode);
    }

    @Override
    @Transactional
    public int updateUserGameEnergyNum(MallUserMode bean,int num) {
        T2UserGameMode t2UserGameMode =new T2UserGameMode();
        t2UserGameMode.setUserId(bean.getID());
        t2UserGameMode.setEnergyNum(String.valueOf(num));
        return it2UserGameDao.updateT2UserGameMode(t2UserGameMode);
    }

    @Override
    @Transactional
    public int updateGameTwoDay() {
        return it2UserGameDao.updateGameTwoDay();
    }

    @Override
    @Transactional
    public int updateGameTwoIKnow(MallUserMode bean) {
        T2UserGameMode t2UserGameMode =new T2UserGameMode();
        t2UserGameMode.setUserId(bean.getID());
        t2UserGameMode.setKnow(String.valueOf("1"));
        return it2UserGameDao.updateT2UserGameMode(t2UserGameMode);
    }
}
