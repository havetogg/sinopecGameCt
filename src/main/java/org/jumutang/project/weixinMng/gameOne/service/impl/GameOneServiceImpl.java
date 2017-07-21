package org.jumutang.project.weixinMng.gameOne.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jumutang.project.tools.DateUtil;
import org.jumutang.project.weixinMng.gameOne.dao.IGameOneDao;
import org.jumutang.project.weixinMng.gameOne.dao.IT1AttributeBuyDao;
import org.jumutang.project.weixinMng.gameOne.dao.IT1GameRecordDao;
import org.jumutang.project.weixinMng.gameOne.dao.IT1UserGametypeDao;
import org.jumutang.project.weixinMng.gameOne.dao.IT1WeekRankReturnDao;
import org.jumutang.project.weixinMng.gameOne.model.GameOneMode;
import org.jumutang.project.weixinMng.gameOne.model.T1AttributeBuyMode;
import org.jumutang.project.weixinMng.gameOne.model.T1GameRecordMode;
import org.jumutang.project.weixinMng.gameOne.model.T1UserGametypeMode;
import org.jumutang.project.weixinMng.gameOne.model.T1WeekRankReturnMode;
import org.jumutang.project.weixinMng.gameOne.service.IGameOneService;
import org.jumutang.project.weixinMng.mallMng.dao.IManageDao;
import org.jumutang.project.weixinMng.mallMng.dao.ISysMsgDao;
import org.jumutang.project.weixinMng.mallMng.dao.IUser_gameMngDao;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.SysMsgMode;
import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameOneServiceImpl implements IGameOneService {

	@Autowired
	private IGameOneDao gameOneDao;
	
	@Autowired
	private IUser_gameMngDao user_gameMngDao;
	
	@Autowired
	private IT1AttributeBuyDao t1AttributeBuyDao;
	
	@Autowired
	private IManageDao manageDao;
	
	@Autowired
	private ISysMsgDao sysMsgDao;
	
	@Autowired
	private IT1GameRecordDao t1GameRecordDao;
	
	@Autowired
	private IT1UserGametypeDao t1UserGametypeDao;
	
	@Autowired
	private IT1WeekRankReturnDao t1WeekRankReturnDao;
	
	
	private final static Logger log=LogManager.getLogger(GameOneServiceImpl.class);


	@Override
	@Transactional
	public void saveUerGame_GameOne(MallUserMode bean, String gameId) {
		// TODO Auto-generated method stub
		User_gameMngMode user_gamebean = user_gameMngDao.findInfobyUseridAndGameid(bean.getID(), gameId);
		if(null ==user_gamebean){
			user_gamebean = new User_gameMngMode();
			user_gamebean.setUSER_ID(bean.getID());
			user_gamebean.setGAME_ID(gameId);
			user_gamebean.setCREAT_TIME(DateUtil.get4yMdHms(new Date()));
			user_gamebean.setDAY_TIMES("0");   // 游戏1 的,每天抽奖次数
			user_gamebean.setGAME_USED_TIMES("0");
			user_gamebean.setGAME_ALL_TIMES("0");
			user_gamebean.setWEEK_SCORE("0");
			user_gamebean.setALL_SCORE("0");
			user_gamebean.setDELETE_FLAG("0");
			
			int user_game_ind = user_gameMngDao.saveInfo(user_gamebean);
			// 新手模式初期化
			saveUsergameType(user_game_ind, "4");
			// 老手模式初期化
			saveUsergameType(user_game_ind, "5");
		}
	}


	private void saveUsergameType(int user_game_ind, String type) {
		T1UserGametypeMode t1usergametypemode = new T1UserGametypeMode();
		t1usergametypemode.setUSER_GAME_ID(String.valueOf(user_game_ind));
		t1usergametypemode.setTYPE(type);
		t1usergametypemode.setDAY_TIMES("3");
		t1usergametypemode.setGAME_USED_TIMES("0");
		t1usergametypemode.setGAME_ALL_TIMES("3");
		t1usergametypemode.setDELETE_FLAG("0");
		t1UserGametypeDao.saveInfo(t1usergametypemode);
	}


	@Override
	public GameOneMode findUer_GameOneInfo(MallUserMode bean, String gameId) {
		GameOneMode gameonemode = new GameOneMode();
		User_gameMngMode user_gameMngMode = user_gameMngDao.findInfobyUseridAndGameid(bean.getID(), gameId);
		
		List<T1UserGametypeMode> findList = t1UserGametypeDao.findList(user_gameMngMode.getID());
		for (T1UserGametypeMode t1UserGametypeMode : findList) {
			String type = t1UserGametypeMode.getTYPE();
			if ("4".equals(type)) {
				//新手
				gameonemode.setUSERGAMETYPEMODE4(t1UserGametypeMode);
			}else if ("5".equals(type)) {
				//老手
				gameonemode.setUSERGAMETYPEMODE5(t1UserGametypeMode);
			}
		}
		
		// 今天游戏购买次数
		int findCount_Today_game_buy4 = 0;   //  新手
		int findCount_Today_game_buy5 = 0;   //  老手
		
		List<T1AttributeBuyMode> findList_gameBuytimes = t1AttributeBuyDao.findList_gameBuytimes(user_gameMngMode.getID());
		for (T1AttributeBuyMode t1AttributeBuyMode : findList_gameBuytimes) {
			if("4".equals(t1AttributeBuyMode.getUSER_GAMETYPE())){
				//
				findCount_Today_game_buy4=findCount_Today_game_buy4+1;
			}else if ("5".equals(t1AttributeBuyMode.getUSER_GAMETYPE())) {
				// 
				findCount_Today_game_buy5=findCount_Today_game_buy5+1;
			}
		}
		
		// 加油机的购买次数
		int findCount_Oil_machine_buy =0;
		// 车位的购买次数
		int findCount_Park_buy =0;
		// 新手已阅读
		 int GAME2KNOWFLAG =0;
		// 老手已阅读
		 int GAME1KNOWFLAG  =0;
		List<T1AttributeBuyMode> findList_notgametimes = t1AttributeBuyDao.findList_notgametimes(user_gameMngMode.getID());
		for (T1AttributeBuyMode t1AttributeBuyMode : findList_notgametimes) {
			//1:游戏次数 2: 车位 3 :加油机 4:新手已阅读 5:老手已阅读
			String type = t1AttributeBuyMode.getTYPE();
			if("2".equals(type)){
				findCount_Park_buy=findCount_Park_buy+1;
			}else if ("3".equals(type)) {
				findCount_Oil_machine_buy=findCount_Oil_machine_buy+1;
			}else if ("4".equals(type)) {
				GAME2KNOWFLAG=GAME2KNOWFLAG+1;
			}else if ("5".equals(type)) {
				GAME1KNOWFLAG=GAME1KNOWFLAG+1;
			}
		}
		gameonemode.setUSER_GAMEMNGMODE(user_gameMngMode);
		gameonemode.setTODAY_GAME_BUY4(findCount_Today_game_buy4);  //新手购买次数
		gameonemode.setTODAY_GAME_BUY5(findCount_Today_game_buy5);  //老手购买次数
		gameonemode.setOIL_MACHINE_BUY(findCount_Oil_machine_buy);
		gameonemode.setPARK_BUY(findCount_Park_buy);
		gameonemode.setGAME2KNOWFLAG(GAME2KNOWFLAG);
		gameonemode.setGAME1KNOWFLAG(GAME1KNOWFLAG);
		
		return gameonemode;
	}


	@Override
	@Transactional
	public void update_GameOneDays(String gameId) {
		Map<String, String> queryParam = new HashMap<>();
		queryParam.put("GAME_ID", gameId);
		//总次数是:前一天的剩余次数加上总次数
		//每天有三次免费次数
		t1UserGametypeDao.updateUserGameInfo_Times(null);  // 
/*		List<T1UserGametypeMode> findListALL = t1UserGametypeDao.findListALL();
		List<User_gameMngMode> findList = user_gameMngDao.findList(queryParam);
		for (User_gameMngMode bean : findList) {
			String day_TIMES = bean.getDAY_TIMES();
			String game_ALL_TIMES = bean.getGAME_ALL_TIMES();
			bean.setGAME_ALL_TIMES(String.valueOf(Integer.parseInt(game_ALL_TIMES)-Integer.parseInt(day_TIMES)+3)); //总次数是:前一天的剩余次数加上总次数
			bean.setDAY_TIMES("3"); // 每天有三次免费次数
			gameOneDao.updateUserGameInfo_Times(bean);
		}*/
		
		
	}


	@Override
	@Transactional
	public void update_GameOneUsedTimes(T1UserGametypeMode t1usergametypemode) {
		String day_TIMES = t1usergametypemode.getDAY_TIMES();
		String game_USED_TIMES = t1usergametypemode.getGAME_USED_TIMES();
		
		int remainday_times = Integer.parseInt(day_TIMES)-1;
		if(remainday_times<=0){
			t1usergametypemode.setDAY_TIMES("0");
		}else {
			t1usergametypemode.setDAY_TIMES(String.valueOf(remainday_times));
		}
		t1usergametypemode.setGAME_USED_TIMES(String.valueOf(Integer.parseInt(game_USED_TIMES)+1));
		
		t1UserGametypeDao.update_GameOneUsedTimes(t1usergametypemode);
	}


	@Override
	@Transactional
	public void update_AllGameTimes(String BUYUSERMODETYPE,MallUserMode userbean,GameOneMode gameonebean,int needDom) {
		// 更新用户表的已经使用钻石
		String used_DIAMOND = userbean.getUSED_DIAMOND();
		userbean.setUSED_DIAMOND(String.valueOf(Integer.parseInt(used_DIAMOND)+needDom));
		manageDao.update_UserInfo_Used_diamond(userbean);
		User_gameMngMode user_GAMEMNGMODE = gameonebean.getUSER_GAMEMNGMODE();
		
		if ("4".equals(BUYUSERMODETYPE)) {
			// 更新游戏次数
			T1UserGametypeMode usergametypemode4 = gameonebean.getUSERGAMETYPEMODE4();
			String game_ALL_TIMES = usergametypemode4.getGAME_ALL_TIMES();
			usergametypemode4.setGAME_ALL_TIMES(String.valueOf(Integer.parseInt(game_ALL_TIMES)+1));
			t1UserGametypeDao.updateInfo_GAME_ALL_TIMES(usergametypemode4);
		}else if("5".equals(BUYUSERMODETYPE)){
			// 更新游戏次数
			T1UserGametypeMode usergametypemode5 = gameonebean.getUSERGAMETYPEMODE5();
			String game_ALL_TIMES = usergametypemode5.getGAME_ALL_TIMES();
			usergametypemode5.setGAME_ALL_TIMES(String.valueOf(Integer.parseInt(game_ALL_TIMES)+1));
			t1UserGametypeDao.updateInfo_GAME_ALL_TIMES(usergametypemode5);
		}
		
		// 保存游戏次数的,购买记录
		T1AttributeBuyMode t1attributebuymode = new T1AttributeBuyMode();
		t1attributebuymode.setUSER_GAME_ID(user_GAMEMNGMODE.getID());
		t1attributebuymode.setTYPE("1");    //1:游戏次数 2: 车位 3 :加油机
		t1attributebuymode.setUSER_GAMETYPE(BUYUSERMODETYPE);    //4:新用户 5老用户
		t1attributebuymode.setBUY_TIME(DateUtil.get4yMdHms(new Date()));
		t1attributebuymode.setPAYED_DIAMOND(String.valueOf(needDom));
		t1attributebuymode.setPAYED_GOLD("0");
		t1attributebuymode.setDELETE_FLAG("0");
		t1AttributeBuyDao.saveInfo(t1attributebuymode);
		
	}


	@Override
	@Transactional
	public void update_WeekRank(String game_id) {
		
		//更新掉旧的记录
		// TODO Auto-generated method stub
		List<User_gameMngMode> findListWeekLimit10 = user_gameMngDao.findListWeekLimit10(game_id);
        // 第一名30钻石、第二名25钻石、第三名20钻石、第四名15钻石、5-10名10钻石。
		for (int i = 0; i < findListWeekLimit10.size(); i++) {
			User_gameMngMode user_gameMngMode=findListWeekLimit10.get(i);
			
			switch (i) {
			case 0:
				// 更新用户的排名分数和钻石数
				user_gameMngMode.setWEEK_GET_DIAMOND("30");
				user_gameMngMode.setWEEK_GET_GOLD("0");
				user_gameMngDao.updateInfo_WEEK_SCORE_TIME(user_gameMngMode);
				
				// 保存福利发放到系统消息
				saveRank10(user_gameMngMode,String.valueOf(i+1),"30");
				
				// 保存每周的返回记录
				saveWeekRankRecord(user_gameMngMode.getID(),"30",String.valueOf(i+1));
				
				break;
			case 1:
				// 更新用户的排名分数和钻石数
				user_gameMngMode.setWEEK_GET_DIAMOND("25");
				user_gameMngMode.setWEEK_GET_GOLD("0");
				user_gameMngDao.updateInfo_WEEK_SCORE_TIME(user_gameMngMode);
				// 保存福利发放到系统消息
				saveRank10(user_gameMngMode,String.valueOf(i+1),"25");
				
				// 保存每周的返回记录
				saveWeekRankRecord(user_gameMngMode.getID(),"25",String.valueOf(i+1));
				break;
			case 2:
				// 更新用户的排名分数和钻石数
				user_gameMngMode.setWEEK_GET_DIAMOND("20");
				user_gameMngMode.setWEEK_GET_GOLD("0");
				user_gameMngDao.updateInfo_WEEK_SCORE_TIME(user_gameMngMode);
				// 保存福利发放到系统消息
				saveRank10(user_gameMngMode,String.valueOf(i+1),"20");
				
				// 保存每周的返回记录
				saveWeekRankRecord(user_gameMngMode.getID(),"20",String.valueOf(i+1));
				
				break;
			case 3:
				// 更新用户的排名分数和钻石数
				user_gameMngMode.setWEEK_GET_DIAMOND("15");
				user_gameMngMode.setWEEK_GET_GOLD("0");
				user_gameMngDao.updateInfo_WEEK_SCORE_TIME(user_gameMngMode);
				// 保存福利发放到系统消息
				saveRank10(user_gameMngMode,String.valueOf(i+1),"15");
				
				// 保存每周的返回记录
				saveWeekRankRecord(user_gameMngMode.getID(),"15",String.valueOf(i+1));
				
				break;
			default:
				// 更新用户的排名分数和钻石数
				user_gameMngMode.setWEEK_GET_DIAMOND("10");
				user_gameMngMode.setWEEK_GET_GOLD("0");
				user_gameMngDao.updateInfo_WEEK_SCORE_TIME(user_gameMngMode);
				// 保存福利发放到系统消息
				saveRank10(user_gameMngMode,String.valueOf(i+1),"10");
				
				// 保存每周的返回记录
				saveWeekRankRecord(user_gameMngMode.getID(),"10",String.valueOf(i+1));
				
				break;
			}
			
		}
	}


	private void saveWeekRankRecord(String user_game_id,String return_diamond,String laste_week_rank) {
		T1WeekRankReturnMode t1weekrankreturnmode = new T1WeekRankReturnMode();
		t1weekrankreturnmode.setUSER_GAME_ID(user_game_id);
		t1weekrankreturnmode.setTYPE("1");
		t1weekrankreturnmode.setRETURN_DIAMOND(return_diamond);
		t1weekrankreturnmode.setRETURN_GOLD("0");
		t1weekrankreturnmode.setLASTE_WEEK_RANK(laste_week_rank);
		t1weekrankreturnmode.setRETURN_TIME(DateUtil.get4yMdHms(new Date()));
		t1weekrankreturnmode.setDELETE_FLAG("0");
		t1WeekRankReturnDao.saveInfo(t1weekrankreturnmode);
	}

	/**
	 * 排行榜,发放福利
	 * @param user_gameMngMode
	 */
	private void saveRank10(User_gameMngMode user_gameMngMode,String rank,String DIAMOND) {
		SysMsgMode sysMsgMode_bean = new SysMsgMode();
		sysMsgMode_bean.setUSER_ID(user_gameMngMode.getUSER_ID());
		sysMsgMode_bean.setMSG_TITLE("排行榜上榜通知");
		sysMsgMode_bean.setMSG_DETAIL("您上周在'加油达人'游戏中表现环卓越,分数超过了大部分玩家,名列排行榜NO."+rank+",特发"+DIAMOND+"钻石,以兹鼓励.");
		sysMsgMode_bean.setTYPE("1");   //1:钻石 2:金币 3:两都都有
		sysMsgMode_bean.setDIAMOND(DIAMOND);
		sysMsgMode_bean.setGOLD("0");
		sysMsgDao.saveInfo(sysMsgMode_bean);
	}


	@Override
	@Transactional
	public void save_OilMachineBuyTimes(MallUserMode userbean, GameOneMode gameonebean, int needGold) {
		// 更新购买的金币
		String used_GOLD = userbean.getUSED_GOLD();
		userbean.setUSED_GOLD(String.valueOf(Integer.parseInt(used_GOLD)+needGold));
		manageDao.update_UserInfo_Used_gold(userbean);
		
		// 保存游戏次数的,购买记录
		User_gameMngMode user_GAMEMNGMODE = gameonebean.getUSER_GAMEMNGMODE();
		
		T1AttributeBuyMode t1attributebuymode = new T1AttributeBuyMode();
		t1attributebuymode.setUSER_GAME_ID(user_GAMEMNGMODE.getID());
		t1attributebuymode.setTYPE("3");    //1:游戏次数 2: 车位 3 :加油机
		t1attributebuymode.setBUY_TIME(DateUtil.get4yMdHms(new Date()));
		t1attributebuymode.setPAYED_DIAMOND("0");
		t1attributebuymode.setPAYED_GOLD(String.valueOf(needGold));
		t1attributebuymode.setDELETE_FLAG("0");
		t1AttributeBuyDao.saveInfo(t1attributebuymode);
		
	}
	
	@Override
	@Transactional
	public void save_ParkBuyTimes(MallUserMode userbean, GameOneMode gameonebean, int needGold) {
		// 更新购买的金币
		String used_GOLD = userbean.getUSED_GOLD();
		userbean.setUSED_GOLD(String.valueOf(Integer.parseInt(used_GOLD)+needGold));
		manageDao.update_UserInfo_Used_gold(userbean);
		
		// 保存游戏次数的,购买记录
		User_gameMngMode user_GAMEMNGMODE = gameonebean.getUSER_GAMEMNGMODE();
		T1AttributeBuyMode t1attributebuymode = new T1AttributeBuyMode();
		t1attributebuymode.setUSER_GAME_ID(user_GAMEMNGMODE.getID());
		t1attributebuymode.setTYPE("2");    //1:游戏次数 2: 车位 3 :加油机
		t1attributebuymode.setBUY_TIME(DateUtil.get4yMdHms(new Date()));
		t1attributebuymode.setPAYED_DIAMOND("0");
		t1attributebuymode.setPAYED_GOLD(String.valueOf(needGold));
		t1attributebuymode.setDELETE_FLAG("0");
		t1AttributeBuyDao.saveInfo(t1attributebuymode);
		
	}


	@Override
	@Transactional
	public void save_GameOneRecord(String TYPE,MallUserMode userbean,GameOneMode gameonebean,T1GameRecordMode t1gamerecordmode) {
		t1GameRecordDao.saveInfo(t1gamerecordmode);
		// 更新游戏分数和时间
		User_gameMngMode user_GAMEMNGMODE = gameonebean.getUSER_GAMEMNGMODE();
		String all_SCORE = user_GAMEMNGMODE.getALL_SCORE();
		
		String score = t1gamerecordmode.getSCORE();;     //游戏得分
		String get_GOLD = t1gamerecordmode.getGET_GOLD();
		user_GAMEMNGMODE.setALL_SCORE(String.valueOf(Integer.parseInt(all_SCORE)+Integer.parseInt(score)));
		if("5".equals(TYPE)){
			// 只有是老手的时候才更新得分
			user_gameMngDao.updateInfo_ALL_SCOREANDTIME(user_GAMEMNGMODE);
		}
		
		// 更新游得到的经验值
		String user_level_score = userbean.getUSER_LEVEL_SCORE();
		userbean.setUSER_LEVEL_SCORE(String.valueOf(Integer.parseInt(user_level_score)+Integer.parseInt(t1gamerecordmode.getLEVEL_SCORE())));
		manageDao.updateInfo_USER_LEVEL_SCORE(userbean);
		
		
		// 更新游得到的收益
		String all_GOLD = userbean.getALL_GOLD();
		userbean.setALL_GOLD(String.valueOf(Integer.parseInt(all_GOLD)+Integer.parseInt(get_GOLD)));
		manageDao.update_User_DiamondANDGold(userbean);
		
	}


	@Override
	@Transactional
	public void saveknow(MallUserMode userbean, GameOneMode gameonebean, String type) {
		// 保存游戏次数的,购买记录
		User_gameMngMode user_GAMEMNGMODE = gameonebean.getUSER_GAMEMNGMODE();
		T1AttributeBuyMode t1attributebuymode = new T1AttributeBuyMode();
		t1attributebuymode.setUSER_GAME_ID(user_GAMEMNGMODE.getID());
		t1attributebuymode.setTYPE(type);    //1:游戏次数 2: 车位 3 :加油机 4:新手已阅读 5:老手已阅读
		t1attributebuymode.setBUY_TIME(DateUtil.get4yMdHms(new Date()));
		t1attributebuymode.setPAYED_DIAMOND("0");
		t1attributebuymode.setPAYED_GOLD("0");
		t1attributebuymode.setDELETE_FLAG("0");
		t1AttributeBuyDao.saveInfo(t1attributebuymode);
		
	}


	@Override
	public int finduserGamedCount(String user_game_id,String type) {
		return t1GameRecordDao.finduserGamedCount(user_game_id,type);
	}
	
}
