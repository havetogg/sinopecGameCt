package org.jumutang.project.weixinMng.gameThree.dao;

import org.jumutang.project.weixinMng.gameThree.model.T3AttributeBuyMode;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 11:50 2017/7/27
 * @Modified By:
 */
public interface T3AttributeBuyDao {
    int saveT3AttributeMode(T3AttributeBuyMode t3AttributeBuyMode);


    List<T3AttributeBuyMode> list(T3AttributeBuyMode t3AttributeBuyMode);
}
