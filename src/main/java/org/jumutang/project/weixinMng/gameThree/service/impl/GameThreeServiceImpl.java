package org.jumutang.project.weixinMng.gameThree.service.impl;

import org.jumutang.project.weixinMng.gameThree.service.IGameThreeService;
import org.jumutang.project.weixinMng.mallMng.dao.IManageDao;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 14:16 2017/8/4
 * @Modified By:
 */
@Service
public class GameThreeServiceImpl implements IGameThreeService{

    @Autowired
    private IManageDao manageDao;

    @Override
    @Transactional
    public void updateUserGameTwoDiamond(MallUserMode bean) {
        manageDao.update_UserInfo_Used_diamond(bean);
    }
}
