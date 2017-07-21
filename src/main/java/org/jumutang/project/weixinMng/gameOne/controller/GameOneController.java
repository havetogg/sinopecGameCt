package org.jumutang.project.weixinMng.gameOne.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jumutang.basicbox.model.JSONResultModel;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.tools.DateUtil;
import org.jumutang.project.weixinMng.gameOne.model.GameOneMode;
import org.jumutang.project.weixinMng.gameOne.model.T1GameRecordMode;
import org.jumutang.project.weixinMng.gameOne.model.T1UserGametypeMode;
import org.jumutang.project.weixinMng.gameOne.service.IGameOneService;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;
import org.jumutang.project.weixinMng.mallMng.service.IGameMngService;
import org.jumutang.project.weixinMng.mallMng.service.IManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/weixinMng/GameOneC", method = { RequestMethod.GET, RequestMethod.POST })
public class GameOneController extends BaseController {

	private static final Logger _LOGGER = Logger.getLogger(GameOneController.class);

	@Autowired
	private IGameOneService gameOneService;
	
	@Autowired
	private IManageService manageService;
	
	@Autowired
	private IGameMngService gameMngService;
	
	// 游戏1的id
	@Value(value = "#{propertyFactoryBean['gameOneid']}")
	private String gameOneid;
	
	
	// 用户进入游戏首页
	@RequestMapping(value = "/gameOneIn", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView userIn(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		// 取得游戏的id
		MallUserMode userbean = refreshtWxLoginUser(request);
		gameOneService.saveUerGame_GameOne(userbean, gameOneid);
		// 初始化游戏 
		ModelAndView view = new ModelAndView("/jsp/weixinMng/gameOne/index.jsp");
		
		// 用户游戏1的相关信息
		GameOneMode gameonebean = refreshGameOne(request);
		view.addObject("GAMEONEBEAN", gameonebean);
		view.addObject("USERBEAN", userbean);
		
		// 查询排版
		Map<String, String> amap = new HashMap<>();
		amap.put("GAME_ID", gameOneid);
		List<User_gameMngMode> GAMEONERANDLIST = gameMngService.find_rand_byGameId(amap);
		view.addObject("GAMEONERANDLIST", GAMEONERANDLIST);
		return view;
	}
	
	// 用户游戏1的相关信息
	@ResponseBody
	@RequestMapping(value = "/getgameOneInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public String getgameOneInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONResultModel<Map<String, Object>> jsonResultModel=new JSONResultModel<>();
		Map<String, Object> resultmap=new HashMap<>();
		try {
			
			MallUserMode userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
			GameOneMode gameonebean = refreshGameOne(request);
			
			resultmap.put("GAMEONEBEAN", gameonebean);
			resultmap.put("USERBEAN", userbean);
			jsonResultModel.setCode(1).setMsg("成功!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
				
		} catch (Exception e) {
			_LOGGER.info(e.getMessage());
			jsonResultModel.setCode(0).setMsg("异常!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
		}
	}
	
	
	// 开始游戏
	@ResponseBody
	@RequestMapping(value = "/startGame", method = { RequestMethod.GET, RequestMethod.POST })
	public String startGame(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONResultModel<Map<String, Object>> jsonResultModel=new JSONResultModel<>();
		Map<String, Object> resultmap=new HashMap<>();
		MallUserMode userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
		GameOneMode gameonebean = refreshGameOne(request);
		
		String USERMODETYPE = super.getStr(request, "USERMODETYPE");
		
		try {
			T1UserGametypeMode usergametypemode = null;
			String remain_GAME_TIMES = "0";
			if("4".equals(USERMODETYPE)){
				usergametypemode=gameonebean.getUSERGAMETYPEMODE4();
				remain_GAME_TIMES=usergametypemode.getREMAIN_GAME_TIMES();
			}else if ("5".equals(USERMODETYPE)) {
				usergametypemode=gameonebean.getUSERGAMETYPEMODE5();
				remain_GAME_TIMES=usergametypemode.getREMAIN_GAME_TIMES();
			}
			
			if(Integer.parseInt(remain_GAME_TIMES)<=0){
				jsonResultModel.setCode(2).setMsg("您今天的游戏机会已经用完,请购买!");
				resultmap.put("GAMEONEBEAN", gameonebean);
				jsonResultModel.setResultObject(resultmap);
				return JSONObject.fromObject(jsonResultModel).toString();
			}
			
			// 更新游戏次数
			// gameOneService.update_GameOneUsedTimes(usergametypemode);
			
			gameonebean = refreshGameOne(request);
			resultmap.put("GAMEONEBEAN", gameonebean);
			jsonResultModel.setCode(1).setMsg("成功!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
				
		} catch (Exception e) {
			_LOGGER.info(e.getMessage());
			jsonResultModel.setCode(0).setMsg("异常!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
		}
	}
	
	// 购买游戏次数(BUY_FLAG=0 表示询问价格,BUY_FLAG=1 表示购买)
	@ResponseBody
	@RequestMapping(value = "/gameOneTimesBuy", method = { RequestMethod.GET, RequestMethod.POST })
	public String gameOneTimesBuy(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONResultModel<Map<String, Object>> jsonResultModel=new JSONResultModel<>();
		Map<String, Object> resultmap=new HashMap<>();
		MallUserMode userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
		GameOneMode gameonebean = refreshGameOne(request);
		String BUY_FLAG = super.getStr(request, "BUY_FLAG");
		String BUYUSERMODETYPE = super.getStr(request, "BUYUSERMODETYPE");
		try {
			int today_GAME_BUY =0;
			int needDom=0;
			if("4".equals(BUYUSERMODETYPE)){
				//新手
				today_GAME_BUY = gameonebean.getTODAY_GAME_BUY4(); // 今天购买的次数
				needDom= today_GAME_BUY+2;           //新人模式：在2钻石的基数上，每次购买价格加一钻石（当天内）
			}else if ("5".equals(BUYUSERMODETYPE)) {
				today_GAME_BUY = gameonebean.getTODAY_GAME_BUY5(); // 今天购买的次数
				needDom= today_GAME_BUY+5;           //购买需要的钻石
			}
			
			String remain_DIAMOND = userbean.getREMAIN_DIAMOND();
			if(Integer.parseInt(remain_DIAMOND) < needDom){
				jsonResultModel.setCode(2).setMsg("您的钻石不足,请去充值大厅充值!");
				resultmap.put("GAMEONEBEAN", gameonebean);
				jsonResultModel.setResultObject(resultmap);
				return JSONObject.fromObject(jsonResultModel).toString();
			}
			
			if("0".equals(BUY_FLAG)){
				//BUY_FLAG=0 表示询问价格
				jsonResultModel.setCode(1).setMsg("您购买次数,需要消耗的钻石!");
				resultmap.put("NEEDDOM", needDom);
				jsonResultModel.setResultObject(resultmap);
				return JSONObject.fromObject(jsonResultModel).toString();
			}
			
			// 更新游戏购买次数
			gameOneService.update_AllGameTimes(BUYUSERMODETYPE,userbean,gameonebean,needDom);
			
			gameonebean = refreshGameOne(request);
			resultmap.put("GAMEONEBEAN", gameonebean);
			jsonResultModel.setCode(1).setMsg("成功!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
				
		} catch (Exception e) {
			_LOGGER.info(e.getMessage());
			jsonResultModel.setCode(0).setMsg("异常!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
		}
	}
	
	// 新手模式
	@RequestMapping(value = "/gameOneGame2In", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView gameOneGame2In(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		// 取得游戏的id
		MallUserMode userbean = refreshtWxLoginUser(request);
		GameOneMode gameonebean = refreshGameOne(request);
		
		T1UserGametypeMode usergametypemode=gameonebean.getUSERGAMETYPEMODE4();
		String remain_GAME_TIMES=usergametypemode.getREMAIN_GAME_TIMES();
		
		if(Integer.parseInt(remain_GAME_TIMES)<=0){
			//游戏记录数大于 主表保存的数,防止页面刷新
			ModelAndView view = new ModelAndView("redirect:gameOneIn.htm");
			return view;
		}
		
/*		//查询剩余次数,以防不返回就刷新 
		int finduserGamedCount = gameOneService.finduserGamedCount(gameonebean.getUSER_GAMEMNGMODE().getID(),"4");
		String game_USED_TIMES = gameonebean.getUSERGAMETYPEMODE4().getGAME_USED_TIMES();
		if(finduserGamedCount>=Integer.parseInt(game_USED_TIMES)){
			//游戏记录数大于 主表保存的数,防止页面刷新
			ModelAndView view = new ModelAndView("redirect:gameOneIn.htm");
			return view;
		}*/
		
		// 更新游戏次数
		gameOneService.update_GameOneUsedTimes(usergametypemode);
		
		// 初始化游戏 
		ModelAndView view = new ModelAndView("/jsp/weixinMng/gameOne/game2.jsp");
		
		view.addObject("USERBEAN", userbean);
		view.addObject("GAMEONEBEAN", gameonebean);
		return view;
	}
	
	// 老手模式
	@RequestMapping(value = "/gameOneGame1In", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView gameOneGame1In(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		// 取得游戏的id
		MallUserMode userbean = refreshtWxLoginUser(request);
		GameOneMode gameonebean = refreshGameOne(request);

		T1UserGametypeMode usergametypemode=gameonebean.getUSERGAMETYPEMODE5();
		String remain_GAME_TIMES=usergametypemode.getREMAIN_GAME_TIMES();
		
		if(Integer.parseInt(remain_GAME_TIMES)<=0){
			//游戏记录数大于 主表保存的数,防止页面刷新
			ModelAndView view = new ModelAndView("redirect:gameOneIn.htm");
			return view;
		}
		
/*		//查询剩余次数,以防不返回就刷新 
		int finduserGamedCount = gameOneService.finduserGamedCount(gameonebean.getUSER_GAMEMNGMODE().getID(),"5");
		String game_USED_TIMES = gameonebean.getUSERGAMETYPEMODE5().getGAME_USED_TIMES();
		if(finduserGamedCount>=Integer.parseInt(game_USED_TIMES)){
			//游戏记录数大于 主表保存的数,防止页面刷新
			ModelAndView view = new ModelAndView("redirect:gameOneIn.htm");
			return view;
		}*/
		
		// 更新游戏次数
		gameOneService.update_GameOneUsedTimes(usergametypemode);
		
		// 初始化游戏 
		ModelAndView view = new ModelAndView("/jsp/weixinMng/gameOne/game.jsp");
		
		view.addObject("USERBEAN", userbean);
		view.addObject("GAMEONEBEAN", gameonebean);
		return view;
	}
	
	
	// 购买车位(BUY_FLAG=0 表示询问价格,BUY_FLAG=1 表示购买)
	@ResponseBody
	@RequestMapping(value = "/gameOneParksBuy", method = { RequestMethod.GET, RequestMethod.POST })
	public String gameOneParksBuy(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONResultModel<Map<String, Object>> jsonResultModel=new JSONResultModel<>();
		Map<String, Object> resultmap=new HashMap<>();
		MallUserMode userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
		GameOneMode gameonebean = refreshGameOne(request);
		String BUY_FLAG = super.getStr(request, "BUY_FLAG");
		try {
			//BUY_FLAG=0 表示询问价格
			// 购置第一台消耗1000金币,购置第二台消耗2000金币，购置第三台消耗4000金币。
			int park_buy = gameonebean.getPARK_BUY(); // 车位的购买次数
			int needGold =0;
			if(0==park_buy){
				needGold=1000;
			}else if (1==park_buy) {
				needGold=2000;
			}else if (2==park_buy) {
				needGold=4000;
			}else{
				// 不能再购买了
				jsonResultModel.setCode(0).setMsg("车位已经满了!");
				jsonResultModel.setResultObject(resultmap);
				return JSONObject.fromObject(jsonResultModel).toString();
			}
			
			String remain_GOLD = userbean.getREMAIN_GOLD();
			if(Integer.parseInt(remain_GOLD) < needGold){
				jsonResultModel.setCode(2).setMsg("您的金币不足,请去充值大厅兑换!");
				resultmap.put("GAMEONEBEAN", gameonebean);
				jsonResultModel.setResultObject(resultmap);
				return JSONObject.fromObject(jsonResultModel).toString();
			}
			
			if("0".equals(BUY_FLAG)){
				
				jsonResultModel.setCode(1).setMsg("您购买车位,需要消耗的金币!");
				resultmap.put("NEEDGOLD", needGold);
				resultmap.put("PARK_BUY", park_buy+3);
				jsonResultModel.setResultObject(resultmap);
				return JSONObject.fromObject(jsonResultModel).toString();
			}
			
			
			// 购买加油机的记录
			gameOneService.save_ParkBuyTimes(userbean,gameonebean,needGold);
			
//			gameonebean = refreshGameOne(request);
			userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
			resultmap.put("USERBEAN", userbean);
//			resultmap.put("GAMEONEBEAN", gameonebean);
			jsonResultModel.setCode(1).setMsg("成功!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
				
		} catch (Exception e) {
			_LOGGER.info(e.getMessage());
			jsonResultModel.setCode(0).setMsg("异常!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
		}
	}		
	
	
	// 购买加油机(BUY_FLAG=0 表示询问价格,BUY_FLAG=1 表示购买)
	@ResponseBody
	@RequestMapping(value = "/gameOneOilMachineBuy", method = { RequestMethod.GET, RequestMethod.POST })
	public String gameOneOilMachineBuy(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONResultModel<Map<String, Object>> jsonResultModel=new JSONResultModel<>();
		Map<String, Object> resultmap=new HashMap<>();
		MallUserMode userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
		GameOneMode gameonebean = refreshGameOne(request);
		String BUY_FLAG = super.getStr(request, "BUY_FLAG");
		try {
			//BUY_FLAG=0 表示询问价格
			// 购置第一台消耗1000金币,购置第二台消耗2000金币，购置第三台消耗4000金币。
			int oil_machine_buy = gameonebean.getOIL_MACHINE_BUY(); // 加油机的购买次数
			int needGold =0;
			String oil_buy ="";
			if(0==oil_machine_buy){
				needGold=1000;
				oil_buy="92#";
			}else if (1==oil_machine_buy) {
				needGold=2000;
				oil_buy="95#";
			}else if (2==oil_machine_buy) {
				needGold=4000;
				oil_buy="98#";
			}else{
				// 不能再购买了
				jsonResultModel.setCode(0).setMsg("加油机已经满了!");
				jsonResultModel.setResultObject(resultmap);
				return JSONObject.fromObject(jsonResultModel).toString();
			}
			
			String remain_GOLD = userbean.getREMAIN_GOLD();
			if(Integer.parseInt(remain_GOLD) < needGold){
				jsonResultModel.setCode(2).setMsg("您的金币不足,请去充值大厅兑换!");
				resultmap.put("GAMEONEBEAN", gameonebean);
				jsonResultModel.setResultObject(resultmap);
				return JSONObject.fromObject(jsonResultModel).toString();
			}
			
			if("0".equals(BUY_FLAG)){
				jsonResultModel.setCode(1).setMsg("您购买加油机,需要消耗的金币!");
				resultmap.put("NEEDGOLD", needGold);
				resultmap.put("OIL_BUY", oil_buy);
				jsonResultModel.setResultObject(resultmap);
				return JSONObject.fromObject(jsonResultModel).toString();
			}
			
			// 购买加油机的记录
			gameOneService.save_OilMachineBuyTimes(userbean,gameonebean,needGold);
			
			userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
			resultmap.put("USERBEAN", userbean);
			jsonResultModel.setCode(1).setMsg("成功!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
				
		} catch (Exception e) {
			_LOGGER.info(e.getMessage());
			jsonResultModel.setCode(0).setMsg("异常!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
		}
	}
	
	// 保存我知道了
	@ResponseBody
	@RequestMapping(value = "/saveKnow", method = { RequestMethod.GET, RequestMethod.POST })
	public String saveKnow(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONResultModel<Map<String, Object>> jsonResultModel=new JSONResultModel<>();
		Map<String, Object> resultmap=new HashMap<>();
		MallUserMode userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
		GameOneMode gameonebean = refreshGameOne(request);
		String KNOWTYPE = super.getStr(request, "KNOWTYPE"); // 4:新手已阅读 5:老手已阅读
		try {
			if("4".equals(KNOWTYPE)){
				if(gameonebean.getGAME2KNOWFLAG()<1){
					// 保存我知道了
					gameOneService.saveknow(userbean,gameonebean,KNOWTYPE);
				}
			}else if("5".equals(KNOWTYPE)){
				if(gameonebean.getGAME1KNOWFLAG()<1){
					// 保存我知道了
					gameOneService.saveknow(userbean,gameonebean,KNOWTYPE);
				}
			}
			jsonResultModel.setCode(1).setMsg("成功!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
				
		} catch (Exception e) {
			_LOGGER.info(e.getMessage());
			jsonResultModel.setCode(0).setMsg("异常!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
		}
	}
	
	// 保存游戏记录
	@ResponseBody
	@RequestMapping(value = "/save_record", method = { RequestMethod.GET, RequestMethod.POST })
	public String save_record(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONResultModel<Map<String, Object>> jsonResultModel=new JSONResultModel<>();
		Map<String, Object> resultmap=new HashMap<>();
		MallUserMode userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
		GameOneMode gameonebean = refreshGameOne(request);
		String GET_DIAMOND = super.getStr(request, "GET_DIAMOND");
		String GET_GOLD = super.getStr(request, "GET_GOLD");
		String GAS_TIMES = super.getStr(request, "GAS_TIMES");
		String OPER_NICE = super.getStr(request, "OPER_NICE");
		String OPER_NOT_NICE = super.getStr(request, "OPER_NOT_NICE");
		String SCORE = super.getStr(request, "SCORE");
		String LEVEL_SCORE = super.getStr(request, "LEVEL_SCORE"); //经验分数
		
		
		String TYPE = super.getStr(request, "TYPE");
		
		try {
			T1GameRecordMode t1gamerecordmode = new T1GameRecordMode();
			// 保存游戏记录
			t1gamerecordmode.setUSER_GAME_ID(gameonebean.getUSER_GAMEMNGMODE().getID());
			t1gamerecordmode.setGET_DIAMOND(GET_DIAMOND);
			t1gamerecordmode.setGET_GOLD(GET_GOLD);
			t1gamerecordmode.setGAS_TIMES(GAS_TIMES);
			t1gamerecordmode.setOPER_NICE(OPER_NICE);
			t1gamerecordmode.setOPER_NOT_NICE(OPER_NOT_NICE);
			t1gamerecordmode.setSCORE(SCORE);
			t1gamerecordmode.setGAME_TIME(DateUtil.get4yMdHms(new Date()));
			t1gamerecordmode.setDELETE_FLAG("0");
			t1gamerecordmode.setTYPE(TYPE);
			t1gamerecordmode.setLEVEL_SCORE(LEVEL_SCORE);
			
			gameOneService.save_GameOneRecord(TYPE,userbean,gameonebean,t1gamerecordmode);
			
			gameonebean = refreshGameOne(request);
			resultmap.put("GAMEONEBEAN", gameonebean);
			jsonResultModel.setCode(1).setMsg("成功!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
		} catch (Exception e) {
			_LOGGER.info(e.getMessage());
			jsonResultModel.setCode(0).setMsg("异常!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
		}
	}
	
	/*
	 * 取得用户信息
	 */
	private MallUserMode refreshtWxLoginUser(HttpServletRequest request) {
		MallUserMode userinfo = super.getWxLoginUser(request);
		userinfo = manageService.queryMallUserInfo(userinfo.getOPEN_ID());
		super.setWxLoginUser(request, userinfo);
		return userinfo;
	}
	
	/*
	 * 取得用户1的信息
	 */
	private GameOneMode refreshGameOne(HttpServletRequest request) {
		MallUserMode userinfo = super.getWxLoginUser(request);
		GameOneMode gameOneMode_bean = gameOneService.findUer_GameOneInfo(userinfo, gameOneid);
		return gameOneMode_bean;
	}
}
