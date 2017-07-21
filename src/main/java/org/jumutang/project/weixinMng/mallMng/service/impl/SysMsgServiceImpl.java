package org.jumutang.project.weixinMng.mallMng.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.jumutang.project.tools.DateUtil;
import org.jumutang.project.weixinMng.mallMng.dao.IManageDao;
import org.jumutang.project.weixinMng.mallMng.dao.IRankDao;
import org.jumutang.project.weixinMng.mallMng.dao.ISysMsgDao;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.RankMode;
import org.jumutang.project.weixinMng.mallMng.model.SysMsgMode;
import org.jumutang.project.weixinMng.mallMng.service.ISysMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysMsgServiceImpl implements ISysMsgService {

	@Autowired
	private ISysMsgDao sysMsgDao;

	@Autowired
	private IManageDao manageDao;
	
	@Autowired
	private IRankDao rankDao;
	
	
	@Override
	public List<SysMsgMode> findList(String user_id){
		return sysMsgDao.findList(user_id);
	}

	@Override
	public SysMsgMode findInfo(Integer id) {
		return sysMsgDao.findInfo(id);
	}

	@Override
	@Transactional
	public int saveInfo(SysMsgMode sysMsgMode) {
		return sysMsgDao.saveInfo(sysMsgMode);
	}

	@Override
	@Transactional
	public void updateInfo(SysMsgMode sysMsgMode) {
		sysMsgDao.updateInfo(sysMsgMode);
	}

	@Override
	@Transactional
	public void updateInfo_readTime(String user_id) {
		sysMsgDao.updateInfo_readTime(user_id);
		
	}

	@Override
	public int findNotReadCount(String user_id) {
		return sysMsgDao.findNotReadCount(user_id);
	}

	@Override
	@Transactional
	public void updateVipWeak_gold() {
		HashMap<String, String> amap = new HashMap<>();
		List<MallUserMode> findUserList = manageDao.findUserList(amap);
		for (MallUserMode bean : findUserList) {
			// 调用登录msg
			SysMsgMode sysMsgMode_bean = new SysMsgMode();
			sysMsgMode_bean.setUSER_ID(bean.getID());
			sysMsgMode_bean.setMSG_TITLE("VIP LV"+bean.getUSER_RANK_ID()+"每周会员福利到账");
			sysMsgMode_bean.setMSG_DETAIL("亲爱的会员,我们已经将"+bean.getRANKMODE().getRETURN_GOLD()+"金币和"+bean.getRANKMODE().getRETURN_DIAMOND()+"钻石双手奉上!");
			sysMsgMode_bean.setTYPE("3");   //1:钻石 2:金币 3:两都都有
			sysMsgMode_bean.setDIAMOND(bean.getRANKMODE().getRETURN_DIAMOND());
			sysMsgMode_bean.setGOLD(bean.getRANKMODE().getRETURN_GOLD());
			sysMsgDao.saveInfo(sysMsgMode_bean);
		}
	}

	@Override
	@Transactional
	public void updateVipMonthRankDown() {
		HashMap<String, String> amap = new HashMap<>();
		List<MallUserMode> findUserList = manageDao.findUserList(amap);
		for (MallUserMode bean : findUserList) {
			manageDao.updateVipMonthRankDown(bean);
			// 降低等级发消息
			SysMsgMode sysMsgMode_bean = new SysMsgMode();
			String user_RANK_ID = bean.getUSER_RANK_ID();
			RankMode rankMode_down = rankDao.findInfo(Integer.parseInt(user_RANK_ID)-1); //下降后的等级
			
			sysMsgMode_bean.setUSER_ID(bean.getID());
			sysMsgMode_bean.setMSG_TITLE("VIP等级下降!");
			if("0".equals(rankMode_down.getID())){
				// 已经降为0级了
				sysMsgMode_bean.setMSG_DETAIL("您的VIP等级降为viplv0，再次充值大于60钻石即可恢复历史最高等级，享受每周会员福利！");
			}else{
				sysMsgMode_bean.setMSG_DETAIL("您的VIP等级降为VIP LV"+rankMode_down.getID()+",每周奖获得"+rankMode_down.getRETURN_GOLD()+"金币和"+rankMode_down.getRETURN_DIAMOND()+"钻石的奖励.再次充值大于60钻石即可恢复历史最高等级!");
			}
			sysMsgMode_bean.setTYPE("-1");   //1:钻石 2:金币 3:两都都有
			sysMsgMode_bean.setDIAMOND("0");
			sysMsgMode_bean.setGOLD("0");
			sysMsgMode_bean.setGET_TIME(DateUtil.get4yMdHms(new Date()));  //领取时间
			sysMsgDao.saveInfo(sysMsgMode_bean);
		}
		
	}

}
