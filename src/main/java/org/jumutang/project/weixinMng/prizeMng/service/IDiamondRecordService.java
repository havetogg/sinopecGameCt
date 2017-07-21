package org.jumutang.project.weixinMng.prizeMng.service;

import org.jumutang.project.weixinMng.prizeMng.model.DiamondRecord;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 18:09 2017/7/11
 * @Modified By:
 */
public interface IDiamondRecordService {

    int saveDiamondRecord(DiamondRecord diamondRecord);

    List<DiamondRecord> listDiamondRecord(DiamondRecord diamondRecord);
}
