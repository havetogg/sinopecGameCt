package org.jumutang.project.weixinMng.gameTwo.dao;

import org.jumutang.project.weixinMng.gameTwo.model.T2AttributeBuyMode;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */
public interface IT2AttributeBuyDao {

    int saveT2AttributeMode(T2AttributeBuyMode t2AttributeBuyMode);


    List<T2AttributeBuyMode> list(T2AttributeBuyMode t2AttributeBuyMode);

}
