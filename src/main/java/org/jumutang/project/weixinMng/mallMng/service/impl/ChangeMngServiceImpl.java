package org.jumutang.project.weixinMng.mallMng.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jumutang.project.base.Page;
import org.jumutang.project.tools.DateUtil;
import org.jumutang.project.weixinMng.mallMng.dao.IChangeMngDao;
import org.jumutang.project.weixinMng.mallMng.dao.IChange_goldMngDao;
import org.jumutang.project.weixinMng.mallMng.dao.IManageDao;
import org.jumutang.project.weixinMng.mallMng.dao.ISysMsgDao;
import org.jumutang.project.weixinMng.mallMng.model.ChangeMngMode;
import org.jumutang.project.weixinMng.mallMng.model.Change_goldMngMode;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.SysMsgMode;
import org.jumutang.project.weixinMng.mallMng.service.IChangeMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChangeMngServiceImpl implements IChangeMngService {

	@Autowired
	private IChangeMngDao changeMngDao;
	
	@Autowired
	private IManageDao manageDao;
	
	@Autowired
	private IChange_goldMngDao change_goldMngDao;
	
	@Autowired
	private ISysMsgDao sysMsgDao;
	
	@Override
	public List<ChangeMngMode> findList(Map<String, String> queryParam) {
		return changeMngDao.findList(queryParam);
	}

	@Override
	public ChangeMngMode findInfo(Integer id) {
		return changeMngDao.findInfo(id);
	}

	@Override
	@Transactional
	public int saveInfo(ChangeMngMode changeMngMode) {
		return changeMngDao.saveInfo(changeMngMode);
	}

	@Override
	@Transactional
	public void updateInfo(ChangeMngMode changeMngMode) {
		changeMngDao.updateInfo(changeMngMode);
	}
	
	
	// 金币
	@Override
	public List<Change_goldMngMode> findList_gold(Map<String, String> queryParam) {
		return change_goldMngDao.findList(queryParam);
	}

	@Override
	public Change_goldMngMode findInfo_gold(Integer id) {
		return change_goldMngDao.findInfo(id);
	}

	@Override
	@Transactional
	public void update_UserInfo_ToGold(MallUserMode bean,String pgold) {
		manageDao.update_UserInfo_ToGold(bean);
		
		// 调用登录msg
		SysMsgMode sysMsgMode_bean = new SysMsgMode();
		
		sysMsgMode_bean.setUSER_ID(bean.getID());
		sysMsgMode_bean.setMSG_TITLE("金币兑换成功");
		sysMsgMode_bean.setMSG_DETAIL("您使用钻石兑换了"+pgold+"金币,现已经到账!");
		sysMsgMode_bean.setTYPE("-1");   //1:钻石 2:金币 3:两都都有
		sysMsgMode_bean.setDIAMOND("0");
		sysMsgMode_bean.setGOLD("0");
		sysMsgMode_bean.setGET_TIME(DateUtil.get4yMdHms(new Date()));  //领取时间
		sysMsgDao.saveInfo(sysMsgMode_bean);
		
		
	}
	
	
}
