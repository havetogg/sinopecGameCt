package org.jumutang.project.weixinMng.gameTwo.service.impl;

import org.jumutang.project.weixinMng.gameTwo.dao.IT2AttributeBuyDao;
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
 * @Date: Create in 19:17 2017/7/13
 * @Modified By:
 */
@Service
public class T2AttributeBuyServiceImpl implements IT2AttributeBuyService{

    @Autowired
    private IT2AttributeBuyDao it2AttributeBuyDao;

    @Override
    @Transactional
    public int saveT2AttributeBuy(MallUserMode bean) {
        T2AttributeBuyMode t2AttributeBuyMode = new T2AttributeBuyMode();
        t2AttributeBuyMode.setUserId(bean.getID());
        t2AttributeBuyMode.setPayDiamond("10");
        return it2AttributeBuyDao.saveT2AttributeMode(t2AttributeBuyMode);
    }


    @Override
    @Transactional
    public List<T2AttributeBuyMode> T2AttributeBuyMode(T2AttributeBuyMode t2AttributeBuyMode) {
        return it2AttributeBuyDao.list(t2AttributeBuyMode);
    }
}
