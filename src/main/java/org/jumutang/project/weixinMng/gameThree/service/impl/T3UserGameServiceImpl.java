package org.jumutang.project.weixinMng.gameThree.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jumutang.project.weixinMng.gameThree.dao.T3UserGameDao;
import org.jumutang.project.weixinMng.gameThree.model.T3UserGameMode;
import org.jumutang.project.weixinMng.gameThree.service.IT3UserGameService;
import org.jumutang.project.weixinMng.gameTwo.dao.IT2UserGameDao;
import org.jumutang.project.weixinMng.gameTwo.service.impl.GameTwoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 10:56 2017/8/3
 * @Modified By:
 */
@Service
public class T3UserGameServiceImpl implements IT3UserGameService{

    private final static Logger log= LogManager.getLogger(T3UserGameServiceImpl.class);

    @Autowired
    private T3UserGameDao t3UserGameDao;

    @Override
    @Transactional
    public int saveT3UserGame(T3UserGameMode t3UserGameMode) {
        List<T3UserGameMode> t3UserGameModes = t3UserGameDao.list(t3UserGameMode);
        if(t3UserGameModes.size()>0) {
            t3UserGameMode = t3UserGameModes.get(0);
            return Integer.parseInt(t3UserGameMode.getId());
        }else{
            return t3UserGameDao.saveT3UserGame(t3UserGameMode);
        }
    }

    @Override
    public List<T3UserGameMode> list(T3UserGameMode t3UserGameMode) {
        return t3UserGameDao.list(t3UserGameMode);
    }

    @Override
    @Transactional
    public int updateT3UserGame(T3UserGameMode t3UserGameMode) {
        return t3UserGameDao.updateT3UserGame(t3UserGameMode);
    }

    @Override
    @Transactional
    public int updateT3UserGameOnce(String userId) {
        T3UserGameMode t3UserGameMode = new T3UserGameMode();
        t3UserGameMode.setUserId(userId);
        List<T3UserGameMode> t3UserGameModes = t3UserGameDao.list(t3UserGameMode);
        if(t3UserGameModes.size()>0){
            t3UserGameMode = t3UserGameModes.get(0);
            t3UserGameMode.setGameAllTimes(t3UserGameMode.getGameAllTimes()+1);
            t3UserGameMode.setLastGameTime("123");
            return t3UserGameDao.updateT3UserGame(t3UserGameMode);
        }else{
            return 0;
        }
    }

}
