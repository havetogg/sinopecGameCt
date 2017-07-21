package org.jumutang.project.weixinMng.mallMng.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jumutang.project.tools.DateUtil;
import org.jumutang.project.weixinMng.mallMng.dao.IManageDao;
import org.jumutang.project.weixinMng.mallMng.dao.IRankDao;
import org.jumutang.project.weixinMng.mallMng.dao.ISysMsgDao;
import org.jumutang.project.weixinMng.mallMng.dao.ITchangeOrderDao;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.RankMode;
import org.jumutang.project.weixinMng.mallMng.model.SysMsgMode;
import org.jumutang.project.weixinMng.mallMng.model.TchangeOrderMode;
import org.jumutang.project.weixinMng.mallMng.service.ITchangeOrderService;
import org.jumutang.project.weixinMng.prizeMng.dao.IPrizePoolDao;
import org.jumutang.project.weixinMng.prizeMng.model.Prize;
import org.jumutang.project.weixinMng.prizeMng.model.PrizePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TchangeOrderServiceImpl implements ITchangeOrderService{

	@Autowired
	private ITchangeOrderDao tchangeOrderDao;
	
	@Autowired
	private IManageDao manageDao;
	
	@Autowired
	private ISysMsgDao sysMsgDao;
	
	@Autowired
	private IRankDao rankDao;

	@Autowired
	private IPrizePoolDao prizePoolDao;
	
	@Override
	public List<TchangeOrderMode> findList(Map<String, String> queryParam) {

		return tchangeOrderDao.findList(queryParam);
	}

	@Override
	public TchangeOrderMode findInfo(Integer id) {
		// TODO Auto-generated method stub
		return tchangeOrderDao.findInfo(id);
	}

	@Override
	@Transactional
	public int saveInfo(TchangeOrderMode tchangeOrderMode) {
		// TODO Auto-generated method stub
		return tchangeOrderDao.saveInfo(tchangeOrderMode);
	}

	@Override
	@Transactional
	public void updateInfo(TchangeOrderMode tchangeOrderMode) {
		// TODO Auto-generated method stub
		tchangeOrderDao.updateInfo(tchangeOrderMode);
	}

	@Override
	@Transactional
	public void deleteInfo(Integer id) {
		// TODO Auto-generated method stub
		tchangeOrderDao.deleteInfo(id);
	}

	@Override
	@Transactional
	public TchangeOrderMode findInfo(String order_no) {
		// TODO Auto-generated method stub
		return tchangeOrderDao.findInfo(order_no);
	}

	@Override
	@Transactional
	public void changeReturn_Diamond(String order_no) {
		 TchangeOrderMode tchangeordermode = tchangeOrderDao.findInfo(order_no);
		 String payed_FLAG = tchangeordermode.getPAYED_FLAG();
		 if("0".equals(payed_FLAG)){
			 // 没有支付
			 tchangeordermode.setPAYED_FLAG("1");
			 tchangeOrderDao.updateInfo(tchangeordermode);  //更新支付状态
			 
			 // 更新用户的钻石数
			 manageDao.update_UserInfo_ToGold(tchangeordermode.getUSER_ID(),tchangeordermode.getDIAMOND_NUMB());

			 //更新用户冲指标
			 PrizePool prizePool = new PrizePool();
			 prizePool.setUserId(tchangeordermode.getUSER_ID());
			 prizePool.setPrizePool(tchangeordermode.getPAY_MONEY());
			 prizePoolDao.updatePrizePool(prizePool);
		
			 
			// 调用登录msg
			SysMsgMode sysMsgMode_bean = new SysMsgMode();
			sysMsgMode_bean.setUSER_ID(tchangeordermode.getUSER_ID());
			sysMsgMode_bean.setMSG_TITLE("充值到账通知");
			sysMsgMode_bean.setMSG_DETAIL("您充值的"+tchangeordermode.getDIAMOND_NUMB()+"钻石已成功到账.");
			sysMsgMode_bean.setTYPE("-1");   //1:钻石 2:金币 3:两都都有
			sysMsgMode_bean.setDIAMOND("0");
			sysMsgMode_bean.setGOLD("0");
			sysMsgMode_bean.setGET_TIME(DateUtil.get4yMdHms(new Date()));  //领取时间
			sysMsgDao.saveInfo(sysMsgMode_bean);
			 
			//更新用户的等级
			updateUserLv(tchangeordermode);

		 }
 	}

	/**
	 * 更新用户的等级
	 * @param tchangeordermode
	 */
	private void updateUserLv(TchangeOrderMode tchangeordermode) {
		MallUserMode mallusermode = manageDao.queryMallUserInfo_byId(tchangeordermode.getUSER_ID());
		 
		 String user_MAX_RANK_ID = mallusermode.getUSER_MAX_RANK_ID();
		 String user_RANK_ID = mallusermode.getUSER_RANK_ID();
		 String changeAfter_RANK_ID="0";
		 int self_changed_all_diamond = Integer.parseInt(mallusermode.getSELF_CHANGED_ALL_DIAMOND());
		 if(self_changed_all_diamond>=2500){
			 changeAfter_RANK_ID="5";
		 }else if (self_changed_all_diamond>=1000) {
			 changeAfter_RANK_ID="4";
		 }else if (self_changed_all_diamond>=600) {
			 changeAfter_RANK_ID="3";
		 }else if (self_changed_all_diamond>=300) {
			 changeAfter_RANK_ID="2";	
		 }else if (self_changed_all_diamond>=60) {
			 changeAfter_RANK_ID="1";	
		 }else {
			 changeAfter_RANK_ID="0";
		 }
		 if(user_MAX_RANK_ID.equals(changeAfter_RANK_ID)){
			 //充值后的等级没有变
			 String diamond_NUMB = tchangeordermode.getDIAMOND_NUMB();
			 if(Integer.parseInt(diamond_NUMB)>=60){
				 if(!user_RANK_ID.equals(user_MAX_RANK_ID)){
					user_RANK_ID =user_MAX_RANK_ID;
					//恢复等级
					RankMode rankmode = rankDao.findInfo(Integer.parseInt(user_RANK_ID));
					// 调用登录msg
					SysMsgMode sysMsgMode_bean = new SysMsgMode();
					sysMsgMode_bean.setUSER_ID(tchangeordermode.getUSER_ID());
					sysMsgMode_bean.setMSG_TITLE("VIP等级恢复!");
					sysMsgMode_bean.setMSG_DETAIL("您的VIP等级已恢复为VIP LV"+user_RANK_ID+"每周将获得"+rankmode.getRETURN_DIAMOND()+"钻石和"+rankmode.getRETURN_GOLD()+"金币奖励!");
					sysMsgMode_bean.setTYPE("-1");   //1:钻石 2:金币 3:两都都有
					sysMsgMode_bean.setDIAMOND("0");
					sysMsgMode_bean.setGOLD("0");
					sysMsgMode_bean.setGET_TIME(DateUtil.get4yMdHms(new Date()));  //领取时间
					sysMsgDao.saveInfo(sysMsgMode_bean);
				 }
			 }
		 }else { 
			 user_MAX_RANK_ID=changeAfter_RANK_ID;
			 user_RANK_ID =user_MAX_RANK_ID;
			 
			RankMode rankmode = rankDao.findInfo(Integer.parseInt(user_RANK_ID));
			// 调用登录msg
			SysMsgMode sysMsgMode_bean = new SysMsgMode();
			sysMsgMode_bean.setUSER_ID(tchangeordermode.getUSER_ID());
			sysMsgMode_bean.setMSG_TITLE("VIP等级提升!");
			sysMsgMode_bean.setMSG_DETAIL("您的VIP等级已提升为VIP LV"+user_RANK_ID+"每周将获得"+rankmode.getRETURN_DIAMOND()+"钻石和"+rankmode.getRETURN_GOLD()+"金币奖励!");
			sysMsgMode_bean.setTYPE("-1");   //1:钻石 2:金币 3:两都都有
			sysMsgMode_bean.setDIAMOND("0");
			sysMsgMode_bean.setGOLD("0");
			sysMsgMode_bean.setGET_TIME(DateUtil.get4yMdHms(new Date()));  //领取时间
			sysMsgDao.saveInfo(sysMsgMode_bean);
		}
		 
		 manageDao.update_UserInfo_Lv(mallusermode.getID(),user_RANK_ID,user_MAX_RANK_ID) ;
	}
	
	
}
