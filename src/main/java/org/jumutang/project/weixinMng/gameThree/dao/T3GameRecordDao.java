package org.jumutang.project.weixinMng.gameThree.dao;

import org.jumutang.project.weixinMng.gameThree.model.T3GameRecordMode;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 11:50 2017/7/27
 * @Modified By:
 */
public interface T3GameRecordDao {

    int saveT3GameRecord(T3GameRecordMode t3GameRecordMode);

    List<T3GameRecordMode> list(T3GameRecordMode t3GameRecordMode);

    int updateT3GameRecord(T3GameRecordMode t3GameRecordMode);
}
