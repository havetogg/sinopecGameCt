package org.jumutang.project.weixinMng.gameThree.service.impl;

import org.jumutang.project.weixinMng.gameThree.dao.T3AttributeBuyDao;
import org.jumutang.project.weixinMng.gameThree.dao.T3UserGameDao;
import org.jumutang.project.weixinMng.gameThree.model.T3AttributeBuyMode;
import org.jumutang.project.weixinMng.gameThree.service.IT3AttributeBuyService;
import org.jumutang.project.weixinMng.gameTwo.model.T2AttributeBuyMode;
import org.jumutang.project.weixinMng.gameTwo.service.IT2AttributeBuyService;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 14:23 2017/8/4
 * @Modified By:
 */

@Service
public class T3AttributeBuyServiceImpl implements IT3AttributeBuyService {

    @Autowired
    private T3AttributeBuyDao t3AttributeBuyDao;

    @Override
    @Transactional
    public int saveT3AttributeBuy(T3AttributeBuyMode t3AttributeBuyMode) {
        return t3AttributeBuyDao.saveT3AttributeMode(t3AttributeBuyMode);
    }

    @Override
    @Transactional
    public List<T3AttributeBuyMode> T2AttributeBuyMode(T3AttributeBuyMode t3AttributeBuyMode) {
        return t3AttributeBuyDao.list(t3AttributeBuyMode);
    }
}
