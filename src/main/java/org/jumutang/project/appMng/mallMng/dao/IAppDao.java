package org.jumutang.project.appMng.mallMng.dao;

import org.jumutang.project.appMng.mallMng.model.AppModel;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 10:51 2017/8/8
 * @Modified By:
 */
public interface IAppDao {

    int saveApp(AppModel appModel);

    List<AppModel> listApp(AppModel appModel);

    int updateApp(AppModel appModel);
}
