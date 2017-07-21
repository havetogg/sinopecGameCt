package org.jumutang.project.weixinMng.gameTwo.dao;

import org.jumutang.project.weixinMng.gameTwo.model.T2GameRecordMode;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */
public interface IT2GameRecordDao {

    int saveT2GameRecordMode(T2GameRecordMode t2GameRecordMode);

    int updateT2GameRecordMode(T2GameRecordMode t2GameRecordMode);

    List<T2GameRecordMode> list(T2GameRecordMode t2GameRecordMode);

}
