package org.jumutang.project.weixinMng.mallMng.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jumutang.project.tools.DateUtil;
import org.jumutang.project.weixinMng.mallMng.dao.*;
import org.jumutang.project.weixinMng.mallMng.model.*;
import org.jumutang.project.weixinMng.mallMng.service.ITchangeOrderService;
import org.jumutang.project.weixinMng.prizeMng.controller.PrizeController;
import org.jumutang.project.weixinMng.prizeMng.dao.IPrizePoolDao;
import org.jumutang.project.weixinMng.prizeMng.dao.IPrizeRedeemDao;
import org.jumutang.project.weixinMng.prizeMng.model.Prize;
import org.jumutang.project.weixinMng.prizeMng.model.PrizePool;
import org.jumutang.project.weixinMng.prizeMng.model.PrizeRedeem;
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

	@Autowired
	private IRewardModeDao rewardModeDao;

	@Autowired
	private IPrizeRedeemDao prizeRedeemDao;
	
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

			 //更新总数
			 PrizePool prizePool2 = new PrizePool();
			 prizePool2.setId("1");
			 prizePool2.setPrizePool(tchangeordermode.getPAY_MONEY());
			 prizePoolDao.updatePrizePool(prizePool2);

			 //查询当前用户金额 充值返钱
 			 /*PrizePool prizePool3 = new PrizePool();
			 prizePool3.setUserId(tchangeordermode.getUSER_ID());
			 String prizePoolStr =prizePoolDao.list(prizePool3).get(0).getPrizePool();
			 if(Double.parseDouble(prizePoolStr)>=10){
				 RewardMode rewardMode = new RewardMode();
				 rewardMode.setUserId(tchangeordermode.getUSER_ID());
				 rewardMode.setRewardType("0");
				 if(rewardModeDao.list(rewardMode).size()==0){

					 rewardMode.setRewardName("充值累积10元奖励");
					 rewardModeDao.saveRewardMode(rewardMode);

					 // 调用msg
					 SysMsgMode sysMsgMode_bean = new SysMsgMode();
					 sysMsgMode_bean.setUSER_ID(tchangeordermode.getUSER_ID());
					 sysMsgMode_bean.setMSG_TITLE("充值累积10元奖励");
					 sysMsgMode_bean.setMSG_DETAIL("您的充值累积到了10元，赠送的10元加油红包已发放，请至“我的奖品”查看");
					 sysMsgMode_bean.setTYPE("-1");   //1:钻石 2:金币 3:两都都有 4:奖品
					 sysMsgMode_bean.setDIAMOND("0");
					 sysMsgMode_bean.setGOLD("0");
					 sysMsgMode_bean.setGET_TIME(DateUtil.get4yMdHms(new Date()));  //领取时间
					 sysMsgDao.saveInfo(sysMsgMode_bean);

					 PrizePool prizePool4 = new PrizePool();
					 prizePool4.setUserId(tchangeordermode.getUSER_ID());
					 prizePool4.setUsedPool("10");
					 prizePoolDao.updatePrizePool(prizePool4);

					 PrizePool prizePool5 = new PrizePool();
					 prizePool5.setId("1");
					 prizePool5.setUsedPool("10");
					 prizePoolDao.updatePrizePool(prizePool5);


					 //保存奖品中奖纪录
					 PrizeRedeem prizeRedeem1 = new PrizeRedeem();
					 prizeRedeem1.setUserId(tchangeordermode.getUSER_ID());
					 prizeRedeem1.setPrizeId("1");
					 prizeRedeem1.setRedeemCode(PrizeController.getRandomString(10));
					 prizeRedeemDao.savePrizeRedeem(prizeRedeem1);
				 }
			 }*/
 			 //充值一百元
			 if(Double.parseDouble(tchangeordermode.getPAY_MONEY().trim())==115){
				 //充值100元奖励
				 RewardMode rewardMode = new RewardMode();
				 rewardMode.setUserId(tchangeordermode.getUSER_ID());
				 rewardMode.setRewardType("1");
				 rewardMode.setRewardName("充值115元奖励");
				 rewardModeDao.saveRewardMode(rewardMode);

				 // 调用msg
				 SysMsgMode sysMsgMode_bean = new SysMsgMode();
				 sysMsgMode_bean.setUSER_ID(tchangeordermode.getUSER_ID());
				 sysMsgMode_bean.setMSG_TITLE("充值115元奖励");
				 sysMsgMode_bean.setMSG_DETAIL("您已充值115元，赠送的100元加油红包已发放，请至“我的奖品”查看");
				 sysMsgMode_bean.setTYPE("-1");   //1:钻石 2:金币 3:两都都有 4:奖品
				 sysMsgMode_bean.setDIAMOND("0");
				 sysMsgMode_bean.setGOLD("0");
				 sysMsgMode_bean.setGET_TIME(DateUtil.get4yMdHms(new Date()));  //领取时间
				 sysMsgDao.saveInfo(sysMsgMode_bean);

				 PrizePool prizePool4 = new PrizePool();
				 prizePool4.setUserId(tchangeordermode.getUSER_ID());
				 prizePool4.setUsedPool("100");
				 prizePoolDao.updatePrizePool(prizePool4);

				 PrizePool prizePool5 = new PrizePool();
				 prizePool5.setId("1");
				 prizePool5.setUsedPool("100");
				 prizePoolDao.updatePrizePool(prizePool5);

				 //保存奖品中奖纪录
				 PrizeRedeem prizeRedeem1 = new PrizeRedeem();
				 prizeRedeem1.setUserId(tchangeordermode.getUSER_ID());
				 prizeRedeem1.setPrizeId("2");
				 prizeRedeem1.setRedeemCode(PrizeController.getRandomString(10));
				 prizeRedeemDao.savePrizeRedeem(prizeRedeem1);
				 prizeRedeem1.setRedeemCode(PrizeController.getRandomString(10));
				 prizeRedeemDao.savePrizeRedeem(prizeRedeem1);
			 }else if(Double.parseDouble(tchangeordermode.getPAY_MONEY().trim())==190){
				 //充值190元奖励
				 RewardMode rewardMode = new RewardMode();
				 rewardMode.setUserId(tchangeordermode.getUSER_ID());
				 rewardMode.setRewardType("1");
				 rewardMode.setRewardName("充值190元奖励");
				 rewardModeDao.saveRewardMode(rewardMode);

				 // 调用msg
				 SysMsgMode sysMsgMode_bean = new SysMsgMode();
				 sysMsgMode_bean.setUSER_ID(tchangeordermode.getUSER_ID());
				 sysMsgMode_bean.setMSG_TITLE("充值190元奖励");
				 sysMsgMode_bean.setMSG_DETAIL("您已充值190元，赠送的150元加油红包已发放，请至“我的奖品”查看");
				 sysMsgMode_bean.setTYPE("-1");   //1:钻石 2:金币 3:两都都有 4:奖品
				 sysMsgMode_bean.setDIAMOND("0");
				 sysMsgMode_bean.setGOLD("0");
				 sysMsgMode_bean.setGET_TIME(DateUtil.get4yMdHms(new Date()));  //领取时间
				 sysMsgDao.saveInfo(sysMsgMode_bean);

				 PrizePool prizePool4 = new PrizePool();
				 prizePool4.setUserId(tchangeordermode.getUSER_ID());
				 prizePool4.setUsedPool("150");
				 prizePoolDao.updatePrizePool(prizePool4);

				 PrizePool prizePool5 = new PrizePool();
				 prizePool5.setId("1");
				 prizePool5.setUsedPool("150");
				 prizePoolDao.updatePrizePool(prizePool5);

				 //保存奖品中奖纪录
				 PrizeRedeem prizeRedeem1 = new PrizeRedeem();
				 prizeRedeem1.setUserId(tchangeordermode.getUSER_ID());
				 prizeRedeem1.setPrizeId("2");
				 prizeRedeem1.setRedeemCode(PrizeController.getRandomString(10));
				 prizeRedeemDao.savePrizeRedeem(prizeRedeem1);
				 prizeRedeem1.setRedeemCode(PrizeController.getRandomString(10));
				 prizeRedeemDao.savePrizeRedeem(prizeRedeem1);
				 prizeRedeem1.setRedeemCode(PrizeController.getRandomString(10));
				 prizeRedeemDao.savePrizeRedeem(prizeRedeem1);
			 }else if(Double.parseDouble(tchangeordermode.getPAY_MONEY().trim())==330){
				 //充值330元奖励
				 RewardMode rewardMode = new RewardMode();
				 rewardMode.setUserId(tchangeordermode.getUSER_ID());
				 rewardMode.setRewardType("1");
				 rewardMode.setRewardName("充值330元奖励");
				 rewardModeDao.saveRewardMode(rewardMode);

				 // 调用msg
				 SysMsgMode sysMsgMode_bean = new SysMsgMode();
				 sysMsgMode_bean.setUSER_ID(tchangeordermode.getUSER_ID());
				 sysMsgMode_bean.setMSG_TITLE("充值330元奖励");
				 sysMsgMode_bean.setMSG_DETAIL("您已充值330元，赠送的300元加油红包已发放，请至“我的奖品”查看");
				 sysMsgMode_bean.setTYPE("-1");   //1:钻石 2:金币 3:两都都有 4:奖品
				 sysMsgMode_bean.setDIAMOND("0");
				 sysMsgMode_bean.setGOLD("0");
				 sysMsgMode_bean.setGET_TIME(DateUtil.get4yMdHms(new Date()));  //领取时间
				 sysMsgDao.saveInfo(sysMsgMode_bean);

				 PrizePool prizePool4 = new PrizePool();
				 prizePool4.setUserId(tchangeordermode.getUSER_ID());
				 prizePool4.setUsedPool("300");
				 prizePoolDao.updatePrizePool(prizePool4);

				 PrizePool prizePool5 = new PrizePool();
				 prizePool5.setId("1");
				 prizePool5.setUsedPool("300");
				 prizePoolDao.updatePrizePool(prizePool5);

				 //保存奖品中奖纪录
				 PrizeRedeem prizeRedeem1 = new PrizeRedeem();
				 prizeRedeem1.setUserId(tchangeordermode.getUSER_ID());
				 prizeRedeem1.setPrizeId("2");
				 prizeRedeem1.setRedeemCode(PrizeController.getRandomString(10));
				 prizeRedeemDao.savePrizeRedeem(prizeRedeem1);
				 prizeRedeem1.setRedeemCode(PrizeController.getRandomString(10));
				 prizeRedeemDao.savePrizeRedeem(prizeRedeem1);
				 prizeRedeem1.setRedeemCode(PrizeController.getRandomString(10));
				 prizeRedeemDao.savePrizeRedeem(prizeRedeem1);
				 prizeRedeem1.setRedeemCode(PrizeController.getRandomString(10));
				 prizeRedeemDao.savePrizeRedeem(prizeRedeem1);
				 prizeRedeem1.setRedeemCode(PrizeController.getRandomString(10));
				 prizeRedeemDao.savePrizeRedeem(prizeRedeem1);
				 prizeRedeem1.setRedeemCode(PrizeController.getRandomString(10));
				 prizeRedeemDao.savePrizeRedeem(prizeRedeem1);
			 }


			// 调用登录msg
			SysMsgMode sysMsgMode_bean = new SysMsgMode();
			sysMsgMode_bean.setUSER_ID(tchangeordermode.getUSER_ID());
			sysMsgMode_bean.setMSG_TITLE("充值到账通知");
			sysMsgMode_bean.setMSG_DETAIL("您充值的"+tchangeordermode.getDIAMOND_NUMB()+"钻石已成功到账.");
			sysMsgMode_bean.setTYPE("-1");   //1:钻石 2:金币 3:两都都有 4:奖品
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
