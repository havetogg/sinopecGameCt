package org.jumutang.project.weixinMng.gameThree.service;

import org.jumutang.project.weixinMng.gameThree.model.T3GameRecordMode;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 17:05 2017/8/4
 * @Modified By:
 */
public interface IT3GameRecordService {
    int saveT3GameRecord(T3GameRecordMode t3GameRecordMode);

    List<T3GameRecordMode> list(T3GameRecordMode t3GameRecordMode);

    int updateT3GameRecord(T3GameRecordMode t3GameRecordMode);
}
