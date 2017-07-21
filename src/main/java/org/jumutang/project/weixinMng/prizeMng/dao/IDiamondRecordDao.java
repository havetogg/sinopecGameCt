package org.jumutang.project.weixinMng.prizeMng.dao;

import org.jumutang.project.weixinMng.prizeMng.model.DiamondRecord;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 18:20 2017/7/11
 * @Modified By:
 */
public interface IDiamondRecordDao {

    int saveDiamondRecord(DiamondRecord diamondRecord);

    List<DiamondRecord> listDiamondRecord(DiamondRecord diamondRecord);

}
