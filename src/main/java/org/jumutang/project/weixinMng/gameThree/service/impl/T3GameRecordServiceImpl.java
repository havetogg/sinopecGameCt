package org.jumutang.project.weixinMng.gameThree.service.impl;

import org.jumutang.project.weixinMng.gameThree.dao.T3GameRecordDao;
import org.jumutang.project.weixinMng.gameThree.dao.T3UserGameDao;
import org.jumutang.project.weixinMng.gameThree.model.T3GameRecordMode;
import org.jumutang.project.weixinMng.gameThree.service.IT3GameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 17:05 2017/8/4
 * @Modified By:
 */
@Service
public class T3GameRecordServiceImpl implements IT3GameRecordService{

    @Autowired
    private T3GameRecordDao t3GameRecordDao;

    @Override
    @Transactional
    public int saveT3GameRecord(T3GameRecordMode t3GameRecordMode) {
        return t3GameRecordDao.saveT3GameRecord(t3GameRecordMode);
    }

    @Override
    @Transactional
    public List<T3GameRecordMode> list(T3GameRecordMode t3GameRecordMode) {
        return t3GameRecordDao.list(t3GameRecordMode);
    }

    @Override
    @Transactional
    public int updateT3GameRecord(T3GameRecordMode t3GameRecordMode) {
        return t3GameRecordDao.updateT3GameRecord(t3GameRecordMode);
    }
}
