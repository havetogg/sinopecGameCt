package org.jumutang.project.appMng.mallMng.service.impl;

import org.jumutang.project.appMng.mallMng.dao.IAppDao;
import org.jumutang.project.appMng.mallMng.model.AppModel;
import org.jumutang.project.appMng.mallMng.service.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 11:46 2017/8/8
 * @Modified By:
 */
@Service
public class AppServiceImpl implements IAppService{

    @Autowired
    private IAppDao appDao;

    @Override
    @Transactional
    public AppModel saveApp(AppModel appModel) {
        if(appDao.listApp(appModel).size()>0){
            return appDao.listApp(appModel).get(0);
        }else{
            int primary = appDao.saveApp(appModel);
            appModel.setId(String.valueOf(primary));
            return appModel;
        }
    }

    @Override
    @Transactional
    public List<AppModel> listApp(AppModel appModel) {
        return appDao.listApp(appModel);
    }

    @Override
    @Transactional
    public int updateApp(AppModel appModel) {
        return appDao.updateApp(appModel);
    }
}
