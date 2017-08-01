package org.jumutang.project.weixinMng.mallMng.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jumutang.basicbox.model.JSONResultModel;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.tools.*;
import org.jumutang.project.weixinMng.gameTwo.model.T2GameRecordMode;
import org.jumutang.project.weixinMng.gameTwo.service.IGameTwoService;
import org.jumutang.project.weixinMng.mallMng.model.GameMngMode;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.RegistCodeMode;
import org.jumutang.project.weixinMng.mallMng.model.SysMsgMode;
import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;
import org.jumutang.project.weixinMng.mallMng.service.IGameMngService;
import org.jumutang.project.weixinMng.mallMng.service.IManageService;
import org.jumutang.project.weixinMng.mallMng.service.IRegistCodeService;
import org.jumutang.project.weixinMng.mallMng.service.ISysMsgService;
import org.jumutang.project.weixinMng.mallMng.service.IUser_gameMngService;
import org.jumutang.project.weixinMng.prizeMng.dao.IPrizePoolDao;
import org.jumutang.project.weixinMng.prizeMng.model.Prize;
import org.jumutang.project.weixinMng.prizeMng.model.PrizePool;
import org.jumutang.project.weixinMng.prizeMng.model.PrizeRedeem;
import org.jumutang.project.weixinMng.prizeMng.service.IPrizePoolService;
import org.jumutang.project.weixinMng.prizeMng.service.IPrizeRedeemService;
import org.jumutang.project.weixinMng.prizeMng.service.IPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/weixinMng/ManageC", method = { RequestMethod.GET, RequestMethod.POST })
public class ManageController extends BaseController {

	private static final Logger _LOGGER = Logger.getLogger(ManageController.class);

	@Autowired
	private IManageService manageService;
	
	@Autowired
	private IRegistCodeService registCodeService;
	
	@Autowired
	private ISysMsgService sysMsgService;
	
	@Autowired
	private IGameMngService gameMngService;
	
	@Autowired
	private IUser_gameMngService user_gameMngService;

	@Autowired
	private IGameTwoService gameTwoService;

	@Autowired
	private IPrizeRedeemService iPrizeRedeemService;

	@Autowired
	private IPrizeService iPrizeService;

	@Autowired
	private IPrizePoolService iPrizePoolService;
	
	@RequestMapping(value = "/wxLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public String wxLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		HashMap<String, String> returnMap = getUserOpenId(request);
		String openId = returnMap.get("USER_OPEN_ID");
		String nickName = returnMap.get("USER_NICKNAME");
		String headimgurl = returnMap.get("USER_HEADIMGURL");

		String redictUrl = getStr(request, "redirectUrl");

		if (StringUtil.isEmpty(openId) || "null".equals(openId) ) {
			return "redirect:/jsp/weixinMng/wx_error.jsp";
		} else {
			MallUserMode bean = manageService.queryMallUserInfo(openId);
			if (null == bean) {
				bean = new MallUserMode();
				bean.setOPEN_ID(openId);	
				try {
					String nickNameencode = URLEncoder.encode(nickName, "utf-8");
					bean.setNICK_NAME(nickNameencode);	
				} catch (UnsupportedEncodingException e) {
					_LOGGER.info(e.getMessage());
				}
				bean.setHEAD_IMG(headimgurl);	
				// 新绑定用户
				manageService.insertMallUserInfoVolume(bean);
			}
			
			bean = manageService.queryMallUserInfo(openId);
			super.setWxLoginUser(request, bean);
			
			if(StringUtil.isEmpty(redictUrl)){
				// 转到首页
				return "redirect:/weixinMng/ManageC/userIn.htm";
			}else{
				return "redirect:"+redictUrl;
			}
		}
	}
	
	
	// 类似招行网关
	private HashMap<String, String> getUserOpenId(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String USER_OPEN_ID=null;
		String USER_NICKNAME=null;
		String USER_HEADIMGURL=null;
		if ("1".equals(getStr(request, "WX.DEBUG_FLAG"))) {
			USER_OPEN_ID = "o4FD4v_VSL8r87LR713h4s_ywo1Y";
			USER_NICKNAME = "%E9%80%97%E6%AF%94%E5%8D%97%E6%B3%A2%E4%B8%87";
			USER_HEADIMGURL = "http://wx.qlogo.cn/mmhead/Q3auHgzwzM6Hq6JJDmjKmJWdRX4F7bEuupSiaHM7iclfBno9libkhv5Jg/0";
		}else{
			String openid =(String)request.getParameter("openid");
			_LOGGER.info("网关返回openId为---------------------"+openid);
			String nickName =(String)request.getParameter("nickname");
			String nickname =new String(nickName.getBytes("ISO-8859-1"), "UTF-8");
			_LOGGER.info("转码之后的NICKNAME---------------------"+nickName);
			String headimgurl = (String)request.getParameter("headimgurl");
			_LOGGER.info("网关返回headimgurl为---------------------"+headimgurl);

			USER_OPEN_ID=openid;
			//USER_NICKNAME = EmojiFilter.filterEmoji(nickname);
			USER_NICKNAME = nickname;
			_LOGGER.info("表情过滤后为---------------------"+USER_NICKNAME);
			USER_HEADIMGURL=headimgurl;
			/*String code = getStr(request,"code");
			_LOGGER.info("获取到的code为"+code);
			Map<String,String> accessTokenMap = WeixinUtil.getAccessTokenByOauth2(code);
			String access_token = accessTokenMap.get("accessToken");
			_LOGGER.info("获取到的accessToken为"+access_token);
			String openId = accessTokenMap.get("openId");
			_LOGGER.info("获取到的openId为"+openId);
			JSONObject jsonObject = WeixinUtil.getuserInfo(access_token,openId);
			if(jsonObject.containsKey("errcode")&&"40001".equals(jsonObject.getString("errcode"))){
				_LOGGER.error("TOKEN异常超时，重新获取TOKEN");
				jsonObject = WeixinUtil.getuserInfo(access_token,openId);
			}
			USER_OPEN_ID=openId;
			USER_NICKNAME=(String) jsonObject.get("nickname");
			USER_HEADIMGURL=(String) jsonObject.get("headimgurl");
			_LOGGER.info(USER_OPEN_ID+"======"+USER_NICKNAME+"======="+USER_HEADIMGURL);*/
		}

		HashMap<String, String> returnmap= new HashMap<>();
		returnmap.put("USER_OPEN_ID",USER_OPEN_ID);
		returnmap.put("USER_NICKNAME",USER_NICKNAME);
		returnmap.put("USER_HEADIMGURL",USER_HEADIMGURL);
		return returnmap;
	}
	
	private String filterEmojiStr(String source){
		if(StringUtil.isEmpty(source)){
			return "匿名用户";
		}else{
			String replaceName =  source.replaceAll("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]", "");
			if(StringUtil.isEmpty(replaceName)){
				return "匿名用户";
			}
			return replaceName;
		}
	}
	
	// 用户进入首页
	@RequestMapping(value = "/userIn", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView userIn(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		// 取得最新的用户信息
		MallUserMode userbean = refreshtWxLoginUser(request);
		
		int notReadSize = sysMsgService.findNotReadCount(userbean.getID());
		ModelAndView view = new ModelAndView("/jsp/weixinMng/mallMng/index.jsp");
		view.addObject("MSGLIST_SIZE", notReadSize);         //未读消息数

		PrizeRedeem prizeRedeem = new PrizeRedeem();
		prizeRedeem.setUserId(userbean.getID());
		prizeRedeem.setStatus("0");
		prizeRedeem.setEndTime("123");
		List<PrizeRedeem> prizeRedeemList = iPrizeRedeemService.list(prizeRedeem);
		view.addObject("PRIZELIST_SIZE", prizeRedeemList.size());

		Map<String,String> queryParam = new HashMap<>();
		queryParam.put("USER_ID", userbean.getID());
		List<GameMngMode> gameMngModeList = gameMngService.findList_collection(queryParam);
		view.addObject("GAMEMNGMODELIST", gameMngModeList); //首页的的游戏,及是否收藏
		
		int GAMEMNGMODELIST_CONNECT_SIZE=0;  //用户的收藏数
		for (GameMngMode gameMngMode : gameMngModeList) {
			String user_COLLECTION_FLAG = gameMngMode.getUSER_COLLECTION_FLAG();
			if("1".equals(user_COLLECTION_FLAG)){
				GAMEMNGMODELIST_CONNECT_SIZE=GAMEMNGMODELIST_CONNECT_SIZE+1;
			}
		}
		view.addObject("GAMEMNGMODELIST_CONNECT_SIZE", GAMEMNGMODELIST_CONNECT_SIZE); //用户的收藏数
		
		int findList_UserIN_Size = gameMngService.findList_UserIN_Size(queryParam,userbean); //用户的上榜数
		view.addObject("USERINRANK_SIZE", findList_UserIN_Size);

		//查询最近的中奖结果用户信息
		JSONArray prizeJSONArray = iPrizeRedeemService.prizeList();
		view.addObject("prizeJSONArray", prizeJSONArray);

		//增加用户总奖池
		PrizePool prizePool = new PrizePool();
		prizePool.setUserId(userbean.getID());
		if(iPrizePoolService.list(prizePool).size()==0){
			iPrizePoolService.savePrizePool(prizePool);
		}

		return view;
	}

	// 发送注册码
	@ResponseBody
	@RequestMapping(value = "/sendRegistCode", method = { RequestMethod.GET, RequestMethod.POST })
	public String sendRegistCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONResultModel<HashMap<String, String>> jsonResultModel = new JSONResultModel<HashMap<String, String>>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String randomdate = sdf.format(new Date());
			int numb = 4;
			String tel = super.getStr(request, "tel");
			String randomCode = randomdate.substring(randomdate.length() - numb, randomdate.length());
			PhoneMsgSendUtil phonemsgsendutil = new PhoneMsgSendUtil();
			JSONObject sendMessage = phonemsgsendutil.sendMessage(tel, randomCode);
			if (!"1".equals(sendMessage.getString("code"))) {
				jsonResultModel.setCode(0).setMsg("验证码取得失败！");
				return JSONObject.fromObject(jsonResultModel).toString();
			}

			HashMap<String, String> queryParam = new HashMap<String, String>();
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createdate = dateFormat.format(date);
			String getnextMinute = DateUtil.getnextMinute(date, 5); // 有效时间
			queryParam.put("MOBILE", tel);
			queryParam.put("CODE", randomCode);
			queryParam.put("EFFECTIVE_TIME", getnextMinute);
			queryParam.put("CREATE_TIME", createdate);
			registCodeService.insertRegistCodeInfoVolume(queryParam);
			jsonResultModel.setCode(1).setMsg("验证码取得成功！");
			return JSONObject.fromObject(jsonResultModel).toString();
		} catch (Exception e) {
			_LOGGER.error(e.getMessage(), e);
			jsonResultModel.setCode(0).setMsg("验证码取得失败！");
			return JSONObject.fromObject(jsonResultModel).toString();
		}
	}
	
	// 用户登录
	@ResponseBody
	@RequestMapping(value = "/userLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public String userLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONResultModel<Map<String, Object>> jsonResultModel=new JSONResultModel<>();
		Map<String, Object> resultmap=new HashMap<>();
		MallUserMode userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
		try {
			String MOBILE = getStr(request, "tel");
	        // 验证码
			String code = getStr(request, "code");
			
			// 查询验证码
			HashMap<String, String> registcodeparam = new HashMap<String, String>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String searchdate = dateFormat.format(date);
			registcodeparam.put("MOBILE", MOBILE);
			registcodeparam.put("CODE", code);
			registcodeparam.put("EFFECTIVE_TIME", searchdate);
			RegistCodeMode queryRegistCodeInfo = registCodeService.queryRegistCodeInfo(registcodeparam);
			if (null == queryRegistCodeInfo) {
				jsonResultModel.setCode(0).setMsg("验证码不正确!");
				jsonResultModel.setResultObject(resultmap);
				return JSONObject.fromObject(jsonResultModel).toString();
			}
			
			String userbean_mobile = userbean.getMOBILE();
			if(!StringUtil.isEmpty(userbean_mobile)){
				// 已经登录过
				jsonResultModel.setCode(1).setMsg("登录成功!");
				jsonResultModel.setResultObject(resultmap);
				return JSONObject.fromObject(jsonResultModel).toString();
			}
			// 取得第三方平台的用户id
			LinkgitfUtil linkgitfutil = new LinkgitfUtil();
			HashMap<String, String> useridByOpenidAndPhone = linkgitfutil.getUseridByOpenidAndPhone(userbean.getOPEN_ID(), MOBILE);
			String stringRESULT = useridByOpenidAndPhone.get("RESULT");
			if("1".equals(stringRESULT)){
				String stringUSERID = useridByOpenidAndPhone.get("USERID");
				userbean.setTHIRD_PART_ID(stringUSERID);
			}else{
				jsonResultModel.setCode(0).setMsg("获得第三方平台的用户id异常!");
				jsonResultModel.setResultObject(resultmap);
				return JSONObject.fromObject(jsonResultModel).toString();
			}
			
			userbean.setMOBILE(MOBILE);

			manageService.update_UserInfo_login(userbean);
			
			jsonResultModel.setCode(1).setMsg("登录成功!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
				
		} catch (Exception e) {
			_LOGGER.info(e.getMessage());
			jsonResultModel.setCode(0).setMsg("异常!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
		}
	}
	
	// 添加收藏
	@ResponseBody
	@RequestMapping(value = "/collect_add", method = { RequestMethod.GET, RequestMethod.POST })
	public String collect_add(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONResultModel<Map<String, Object>> jsonResultModel=new JSONResultModel<>();
		Map<String, Object> resultmap=new HashMap<>();
		MallUserMode userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
		try {
			String gameid = getStr(request, "gameid");
	  
			manageService.collect_add(userbean,gameid);
			
			jsonResultModel.setCode(1).setMsg("收藏成功!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
				
		} catch (Exception e) {
			_LOGGER.info(e.getMessage());
			jsonResultModel.setCode(0).setMsg("收藏异常!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
		}
	}
	
	// 删除收藏
	@ResponseBody
	@RequestMapping(value = "/collect_del", method = { RequestMethod.GET, RequestMethod.POST })
	public String collect_del(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONResultModel<Map<String, Object>> jsonResultModel=new JSONResultModel<>();
		Map<String, Object> resultmap=new HashMap<>();
		MallUserMode userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
		try {
			String gameid = getStr(request, "gameid");
	  
			manageService.collect_del(userbean,gameid);
			
			jsonResultModel.setCode(1).setMsg("删除收藏成功!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
				
		} catch (Exception e) {
			_LOGGER.info(e.getMessage());
			jsonResultModel.setCode(0).setMsg("删除异常!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
		}
	}
	
	// 用户的游戏(收藏和玩过的游戏)
	@RequestMapping(value = "/myGame", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView myGame(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		// 取得最新的用户信息
		MallUserMode userbean = refreshtWxLoginUser(request);
		
		ModelAndView view = new ModelAndView("/jsp/weixinMng/mallMng/collections.jsp");
		Map<String,String> queryParam = new HashMap<>();
		queryParam.put("USER_ID", userbean.getID());
		List<GameMngMode> mycollectionList = gameMngService.find_MycollectionList(queryParam);
		view.addObject("MYCOLLECTIONLIST", mycollectionList); //用户的收藏数
		
		//用户玩过的游戏
		List<GameMngMode> userGameDList = new ArrayList<>();
		//是否玩过游戏1
		List<User_gameMngMode> findUserGameDList = user_gameMngService.findUserGameDList(queryParam);
		if(findUserGameDList.size()>0){
			GameMngMode gameMngMode = findUserGameDList.get(0).getGAMEMNGMODE();
			userGameDList.add(gameMngMode);
		}
		//是否玩过游戏2
		List<T2GameRecordMode> t2GameRecordModes = gameTwoService.listT2GameRecordMode(userbean);
		if(t2GameRecordModes.size()>0){
			GameMngMode gameMngMode = gameMngService.findInfo(2);
			userGameDList.add(gameMngMode);
		}

		view.addObject("USERGAMEDLIST", userGameDList); //用户玩过的游戏
		return view;
	}
	
	// 游戏的排行及是否上榜样
	@RequestMapping(value = "/rank", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView rank(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		// 取得最新的用户信息
		MallUserMode userbean = refreshtWxLoginUser(request);
		
		ModelAndView view = new ModelAndView("/jsp/weixinMng/mallMng/rank.jsp");
		Map<String,String> queryParam = new HashMap<>();
		List<GameMngMode> list_userin = gameMngService.findList_UserIN(queryParam,userbean);
		view.addObject("RANKLIST", list_userin); //用户的收藏数
		
		return view;
	}
	
	// 单个游戏的排行详细
	@RequestMapping(value = "/rankDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView rankDetail(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		// 取得最新的用户信息
		MallUserMode userbean = refreshtWxLoginUser(request);
		
		ModelAndView view = new ModelAndView("/jsp/weixinMng/mallMng/gameTop.jsp");
		Map<String,String> queryParam = new HashMap<>();
		queryParam.put("GAME_ID", super.getStr(request, "GAME_ID"));
		List<User_gameMngMode> rankdetaillist = gameMngService.find_rand_byGameId(queryParam);
		view.addObject("RANKDETAILLIST", rankdetaillist); //游戏的排行详细
		
		// 取得游戏信息
		String game_id = super.getStr(request, "GAME_ID");
		GameMngMode GameMngMode_bean = gameMngService.findInfo(Integer.parseInt(game_id));
		view.addObject("GAMEMNGMODE_BEAN", GameMngMode_bean); 
		
		return view;
	}
	
	// 消息的列表
	@RequestMapping(value = "/msgList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView msgList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		// 取得最新的用户信息
		MallUserMode userbean = refreshtWxLoginUser(request);
		
		ModelAndView view = new ModelAndView("/jsp/weixinMng/mallMng/myMessage.jsp");
		List<SysMsgMode> msg_list = sysMsgService.findList(userbean.getID());
		
		sysMsgService.updateInfo_readTime(userbean.getID());  // 打开时更新为已经读取过了
		
		view.addObject("MSG_LIST", msg_list);
		view.addObject("MSG_LIST_SIZE", msg_list.size());
		
		return view;
	}
	
	// 消息中领取金币或者钻石
	@ResponseBody
	@RequestMapping(value = "/get_reward", method = { RequestMethod.GET, RequestMethod.POST })
	public String get_reward(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONResultModel<Map<String, Object>> jsonResultModel=new JSONResultModel<>();
		Map<String, Object> resultmap=new HashMap<>();
		MallUserMode userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
		try {
			String sysmsgid = getStr(request, "sysmsgid");
			manageService.get_reward(userbean,sysmsgid);
			
			jsonResultModel.setCode(1).setMsg("领取成功!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
				
		} catch (Exception e) {
			_LOGGER.info(e.getMessage());
			jsonResultModel.setCode(0).setMsg("领取异常!");
			jsonResultModel.setResultObject(resultmap);
			return JSONObject.fromObject(jsonResultModel).toString();
		}
	}

	//进入我的奖品页面
	@RequestMapping(value = "/prizeList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView prizeList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		MallUserMode userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
		//查询用户的中奖纪录
		ModelAndView view = new ModelAndView("/jsp/weixinMng/mallMng/myPrize.jsp");
		PrizeRedeem prizeRedeem  = new PrizeRedeem();
		prizeRedeem.setUserId(userbean.getID());
		List<PrizeRedeem> prizeRedeems = iPrizeRedeemService.list(prizeRedeem);
		JSONArray prizeArray = new JSONArray();
		for(PrizeRedeem prizeRedeem1:prizeRedeems){
			JSONObject jsonObject = new JSONObject();
			PrizeRedeem prizeRedeem2 = new PrizeRedeem();
			prizeRedeem2.setUserId(userbean.getID());
			prizeRedeem2.setStatus("0");
			prizeRedeem2.setEndTime("123");
			List<PrizeRedeem> prizeRedeemList = iPrizeRedeemService.list(prizeRedeem2);
			boolean flag = true;
			for(PrizeRedeem prizeRedeem3:prizeRedeemList){
				if(prizeRedeem3.getId().equals(prizeRedeem1.getId())){
					flag = false;
				}
			}
			if(flag&&"0".equals(prizeRedeem1.getStatus())){
				jsonObject.put("isOut",0);
			}else{
				jsonObject.put("isOut",1);
			}
			Prize prize = new Prize();
			prize.setId(prizeRedeem1.getPrizeId());
			Prize prize1 = iPrizeService.listPrize(prize).get(0);
			jsonObject.put("prizeName",prize1.getPrizeName());
			jsonObject.put("prizeId",prize1.getId());
			jsonObject.put("imgUrl",prize1.getImgUrl());
			jsonObject.put("prizeRedeemId",prizeRedeem1.getId());
			jsonObject.put("winningTime",prizeRedeem1.getWinningTime());
			jsonObject.put("endTime",prizeRedeem1.getEndTime());
			jsonObject.put("redeemCode",prizeRedeem1.getRedeemCode());
			jsonObject.put("status",prizeRedeem1.getStatus());
			prizeArray.add(jsonObject);
		}

		view.addObject("prizeArray", prizeArray);
		return view;
	}

	//进入我的奖品详细
	@RequestMapping(value = "/getPriceDetail", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getPriceDetail(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		MallUserMode userbean =	refreshtWxLoginUser(request);// 取得最新的用户信息
		String prizeId = request.getParameter("prizeId");
		Prize prize = new Prize();
		prize.setId(prizeId);
		Prize prize1 = iPrizeService.listPrize(prize).get(0);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("prizeName",prize1.getPrizeName());
		jsonObject.put("prizeId",prize1.getId());
		jsonObject.put("imgUrl",prize1.getImgUrl());
		return JSON.toJSONString(jsonObject);
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
}
