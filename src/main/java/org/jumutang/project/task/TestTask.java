package org.jumutang.project.task;

import org.apache.log4j.Logger;
import org.jumutang.project.tools.PropertiesUtil;
import org.jumutang.project.tools.SpringContextHolder;
import org.jumutang.project.weixinMng.gameOne.service.IGameOneService;
import org.jumutang.project.weixinMng.gameTwo.service.IGameTwoService;
import org.jumutang.project.weixinMng.mallMng.service.ISysMsgService;
import org.jumutang.project.weixinMng.mallMng.service.impl.SysMsgServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class TestTask {

	private static final Logger _LOGGER = Logger.getLogger(TestTask.class);
	/**
	 * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下） cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23)
	 * *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT)
	 */
	//@Scheduled(cron = "	0 0 0,1 * * ?")       每0点和1点执行一次
	//@Scheduled(cron = "	0 0,1,2,3 * * * ?")       每0,1,2,3分执行一次
/*	@Scheduled(cron = "	0 45 11 * * ?")    //test
	public void test3() {
		_LOGGER.info("==============>>>有礼付中石化抽奖任务，每0点执行一次");
		ISysMsgService sysMsgService =SpringContextHolder.getBean("sysMsgService");
		sysMsgService.updateVipWeak_gold();
		
		sysMsgService.updateVipMonthRankDown();
		// 每周游戏1的排名的返利
		String gameOneid = PropertiesUtil.get("gameOneid");  //游戏1的id
		IGameOneService gameOneService = SpringContextHolder.getBean("gameOneService");
		gameOneService.update_WeekRank(gameOneid);

	}*/
	
	@Scheduled(cron = "	0 1 0 1 * ?")
	public void VipMonthRankDown() {
		_LOGGER.info("==============>>>每月的降低用户等级:每月1号的0点1分执行");
		// 降低用户等级
		ISysMsgService sysMsgService =SpringContextHolder.getBean("sysMsgService");
		sysMsgService.updateVipMonthRankDown();

	}
	@Scheduled(cron = "	0 0 0 ? * 1")
	public void WeekRank() {
		_LOGGER.info("==============>>>每周用户等级返利:星期一的0点0分执行");
		// 每周用户等级返利
		ISysMsgService sysMsgService =SpringContextHolder.getBean("sysMsgService");
		sysMsgService.updateVipWeak_gold();
		
		// 每周游戏1的排名的返利
		String gameOneid = PropertiesUtil.get("gameOneid");  //游戏1的id
		IGameOneService gameOneService = SpringContextHolder.getBean("gameOneService");
		gameOneService.update_WeekRank(gameOneid);
	}
	
	/**
	 * 每天的定时任务
	 */
	@Scheduled(cron = "	0 2 0 * * ?")
	public void days_execute() {
		_LOGGER.info("==============>>>每天的0点2分执行");
		// 游戏1的每天的定时任务
		gameOneDays();
		//游戏2的每天任务
		gameTwoDays();
	}
	
	// 游戏1的每天的定时任务
	private void gameOneDays() {
		//开始游戏1每天定时任务
		String gameOneid = PropertiesUtil.get("gameOneid");  //游戏1的id
		IGameOneService gameOneService = SpringContextHolder.getBean("gameOneService");
		gameOneService.update_GameOneDays(gameOneid);
	}
	// 游戏2每天的定时任务
	private void gameTwoDays(){
		//开始游戏2每天定时任务
		IGameTwoService iGameTwoService = SpringContextHolder.getBean("gameTwoService");
		iGameTwoService.updateGameTwoDay();
	}
	


}
