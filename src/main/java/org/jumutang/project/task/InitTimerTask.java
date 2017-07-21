package org.jumutang.project.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jumutang.project.tools.SpringContextHolder;

public class InitTimerTask {
	
	private final static Logger _log=LogManager.getLogger(InitTimerTask.class);
	
	private static int REPEAT_NUM=0;
	
	
	public void init(){
/*		new Thread(new Runnable() {
			public void run() {
				while(true){
					try {
						//休眠30秒
						//_log.info("定时任务休眠600秒");
						Thread.sleep(10000);
						process();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						_log.error(e.getMessage());
					}
				}
			}
		}).start();*/
		
/*		IDrawMngService drawMngService =SpringContextHolder.getBean("drawMngService");
		List<JiaPrizeMode> findJiaPrizeList = drawMngService.findJiaPrizeList();
		UserPrizeUtil.setJiaPrizeModeListback(findJiaPrizeList);
		
		List<JiaPrizeMode> templist = new ArrayList<>();
		templist.addAll(UserPrizeUtil.getJiaPrizeModeListback());
		UserPrizeUtil.setJiaPrizeModeListback_useing(templist);*/
	}
	
/*	private void process(){
		IDrawMngService drawMngService =SpringContextHolder.getBean("drawMngService");

		drawMngService.getUserPrize();

	}*/

}
