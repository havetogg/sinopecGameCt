package org.jumutang.project.appMng.mallMng.service;

import org.jumutang.project.appMng.mallMng.model.AppModel;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 11:42 2017/8/8
 * @Modified By:
 */
public interface IAppService {

    AppModel saveApp(AppModel appModel);

    List<AppModel> listApp(AppModel appModel);

    int updateApp(AppModel appModel);
}
