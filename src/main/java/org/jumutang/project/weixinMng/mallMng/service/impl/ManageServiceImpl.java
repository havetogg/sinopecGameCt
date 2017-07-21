package org.jumutang.project.weixinMng.mallMng.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jumutang.project.weixinMng.mallMng.dao.IManageDao;
import org.jumutang.project.weixinMng.mallMng.dao.ISysMsgDao;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.SysMsgMode;
import org.jumutang.project.weixinMng.mallMng.service.IManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManageServiceImpl implements IManageService {

	@Autowired
	private IManageDao manageDao;
	
	@Autowired
	private ISysMsgDao sysMsgDao;
	
	private final static Logger log=LogManager.getLogger(ManageServiceImpl.class);

	// 查询绑定的用户 
	@Override
	@Transactional
	public MallUserMode queryMallUserInfo(String openid) {
		MallUserMode userbean = manageDao.queryMallUserInfo(openid);
		return userbean;
	}
	
	// 插入数据
	@Override
	@Transactional
	public int insertMallUserInfoVolume(MallUserMode bean) {
		return manageDao.insertMallUserInfoVolume(bean);
	}

	// 更新用户login时的信息
	@Override
	@Transactional
	public void update_UserInfo_login(MallUserMode bean) {
		manageDao.update_UserInfo_login(bean);
		// 调用登录msg
		SysMsgMode sysMsgMode_bean = new SysMsgMode();
		sysMsgMode_bean.setUSER_ID(bean.getID());
		sysMsgMode_bean.setMSG_TITLE("登录福利");
		sysMsgMode_bean.setMSG_DETAIL("首次登录奖励10钻石.");
		sysMsgMode_bean.setTYPE("1");   //1:钻石 2:金币 3:两都都有
		sysMsgMode_bean.setDIAMOND("10");
		sysMsgMode_bean.setGOLD("0");
		sysMsgDao.saveInfo(sysMsgMode_bean);
		
	}

	@Override
	@Transactional
	public void collect_add(MallUserMode bean, String gameId) {
		manageDao.collect_add(bean,gameId);
	}

	@Override
	@Transactional
	public void collect_del(MallUserMode bean, String gameId) {
		manageDao.collect_del(bean,gameId);
	}

	@Override
	@Transactional
	public void get_reward(MallUserMode bean, String sysMsgId) {
		// 查询消息
		SysMsgMode sysMsgMode = sysMsgDao.findUnGetInfo(Integer.parseInt(sysMsgId));
		if(null !=sysMsgMode){
			if("1".equals(sysMsgMode.getTYPE())){
				//1:钻石 2:金币 3:两都有
				String all_DIAMOND = bean.getALL_DIAMOND();
				bean.setALL_DIAMOND(String.valueOf(Integer.parseInt(all_DIAMOND)+Integer.parseInt(sysMsgMode.getDIAMOND())));
			}else if("2".equals(sysMsgMode.getTYPE())){
				String all_GOLD = bean.getALL_GOLD();
				bean.setALL_GOLD(String.valueOf(Integer.parseInt(all_GOLD)+Integer.parseInt(sysMsgMode.getGOLD())));
			}else{
				String all_DIAMOND = bean.getALL_DIAMOND();
				bean.setALL_DIAMOND(String.valueOf(Integer.parseInt(all_DIAMOND)+Integer.parseInt(sysMsgMode.getDIAMOND())));
				String all_GOLD = bean.getALL_GOLD();
				bean.setALL_GOLD(String.valueOf(Integer.parseInt(all_GOLD)+Integer.parseInt(sysMsgMode.getGOLD())));
			}
			
			manageDao.update_User_DiamondANDGold(bean);  //更新钻石和金币
			
			sysMsgDao.updateInfo(sysMsgMode);            //更新领取状态
		}

	}


	@Override
	@Transactional
	public int UpdateDiamond(MallUserMode mallUserMode) {
		return manageDao.UpdateDiamond(mallUserMode);
	}
}
