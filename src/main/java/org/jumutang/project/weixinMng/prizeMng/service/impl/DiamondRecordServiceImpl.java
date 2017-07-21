package org.jumutang.project.weixinMng.prizeMng.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jumutang.project.weixinMng.gameOne.service.impl.GameOneServiceImpl;
import org.jumutang.project.weixinMng.prizeMng.dao.IDiamondRecordDao;
import org.jumutang.project.weixinMng.prizeMng.model.DiamondRecord;
import org.jumutang.project.weixinMng.prizeMng.service.IDiamondRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 18:18 2017/7/11
 * @Modified By:
 */

@Service
public class DiamondRecordServiceImpl implements IDiamondRecordService{

    private final static Logger log= LogManager.getLogger(DiamondRecordServiceImpl.class);

    @Autowired
    private IDiamondRecordDao iDiamondRecordDao;

    @Override
    @Transactional
    public int saveDiamondRecord(DiamondRecord diamondRecord) {
        return iDiamondRecordDao.saveDiamondRecord(diamondRecord);
    }

    @Override
    @Transactional
    public List<DiamondRecord> listDiamondRecord(DiamondRecord diamondRecord) {
        return iDiamondRecordDao.listDiamondRecord(diamondRecord);
    }
}
