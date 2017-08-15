package org.jumutang.project.weixinMng.mallMng.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jumutang.basicbox.model.JSONResultModel;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.tools.*;
import org.jumutang.project.weixinMng.mallMng.model.ChangeMngMode;
import org.jumutang.project.weixinMng.mallMng.model.Change_goldMngMode;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.TchangeOrderMode;
import org.jumutang.project.weixinMng.mallMng.service.IChangeMngService;
import org.jumutang.project.weixinMng.mallMng.service.IManageService;
import org.jumutang.project.weixinMng.mallMng.service.IRegistCodeService;
import org.jumutang.project.weixinMng.mallMng.service.ISysMsgService;
import org.jumutang.project.weixinMng.mallMng.service.ITchangeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

/**
 * 充值
 */
@Controller
@RequestMapping(value = "/weixinMng/ManageC", method = { RequestMethod.GET, RequestMethod.POST })
public class ChangeMngController extends BaseController {

	private static final Logger _LOGGER = Logger.getLogger(ChangeMngController.class);

	@Autowired
	private IManageService manageService;
	
	@Autowired
	private IRegistCodeService registCodeService;
	
	@Autowired
	private ISysMsgService sysMsgService;
	
	@Autowired
	private IChangeMngService changeMngService;
	
	@Autowired
	private ITchangeOrderService tchangeOrderService;
	
	
	// 进入充值首页
	@RequestMapping(value = "/rechargeIn", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView userIn(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		// 取得最新的用户信息
		MallUserMode userbean = refreshtWxLoginUser(request);
 		Map<String, String> queryParam = new HashMap<>();
		// 钻石
		List<ChangeMngMode> changeMngList = changeMngService.findList(queryParam);
		// 金币
		List<Change_goldMngMode> changeMngList_gold = changeMngService.findList_gold(queryParam);
		
		ModelAndView view = new ModelAndView("/jsp/weixinMng/mallMng/recharge.jsp");
		view.addObject("CHANGEMNGLIST", changeMngList);
 		view.addObject("CHANGEMNGLIST_GOLD", changeMngList_gold);
		return view;
	}

	// 钻石换金币
	@ResponseBody
	@RequestMapping(value = "/rechargeGold", method = { RequestMethod.GET, RequestMethod.POST })
	public String rechargeGold(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONResultModel<HashMap<String, String>> jsonResultModel = new JSONResultModel<HashMap<String, String>>();
		HashMap<String, String> queryParam = new HashMap<String, String>();
		MallUserMode userbean = refreshtWxLoginUser(request);
		try {
             int gold_id = super.getInt(request, "gold_id");

			Change_goldMngMode findInfo_gold = changeMngService.findInfo_gold(gold_id);
			String pay_DIAMOND_NUMB = findInfo_gold.getPAY_DIAMOND_NUMB();  //要兑换需要的钻石
			String remain_DIAMOND = userbean.getREMAIN_DIAMOND();
			int compare = BigDecimalTool.compare(pay_DIAMOND_NUMB, remain_DIAMOND);
			if(1==compare){
				jsonResultModel.setCode(2).setMsg("您的钻石不足！");
				return JSONObject.fromObject(jsonResultModel).toString();
			}

			// 更新后的钻石和金币
			String change_gold = findInfo_gold.getGOLD();  //要兑换需要的钻石
			String used_DIAMOND = userbean.getUSED_DIAMOND();
			String all_GOLD = userbean.getALL_GOLD();
			userbean.setUSED_DIAMOND(String.valueOf(Integer.parseInt(used_DIAMOND)+Integer.parseInt(pay_DIAMOND_NUMB)));
			userbean.setALL_GOLD(String.valueOf(Integer.parseInt(all_GOLD)+Integer.parseInt(change_gold)));
			changeMngService.update_UserInfo_ToGold(userbean,change_gold);

			jsonResultModel.setCode(1).setMsg("兑换成功！");
			return JSONObject.fromObject(jsonResultModel).toString();
		} catch (Exception e) {
			_LOGGER.error(e.getMessage(), e);
			jsonResultModel.setCode(0).setMsg("兑换失败！");
			return JSONObject.fromObject(jsonResultModel).toString();
		}
	}



	//获取中石化用户的uesrid
	@RequestMapping(value = "/toOilPage", method = { RequestMethod.GET, RequestMethod.POST })
	public void toOilPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		_LOGGER.error("获取中石化sbxUserId开始");
		/*String apiSecret = "zxcQWE123asd0987";
		String timestamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
		String  sysGenSign = MD5Util.getUpperCaseMD5For32("zsh.integral.api" + "qaz123!@#" + apiSecret + timestamp);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("username","zsh.integral.api");
		paramMap.put("password","qaz123!@#");
		paramMap.put("timestamp",timestamp);
		paramMap.put("sign", sysGenSign);
		String token = HttpClientUtil.doHttpPost("https://prod1.juxinbox.com/zsh.integral/api/v1/auth/login.htm", paramMap);
		_LOGGER.error("token为"+token);*/
		String redirectUrl = getBasePath(request)+"/weixinMng/ManageC/toPayPage.htm";
		_LOGGER.error("回调地址为:"+redirectUrl);
		String redirect = "https://prod1.juxinbox.com/zsh.integral/api/v1/user/integral/1.htm?redirectUrl="+redirectUrl;
		_LOGGER.error("跳转地址为:"+redirect);
		response.sendRedirect(redirect);
	}

	//跳转支付页面链接
	static String proUrl="http://www.linkgift.cn/giftpay_wap/giftpay/liftpayment/orderPay.html";

	@ResponseBody
	@RequestMapping(value = "/toPayPage", method = { RequestMethod.GET, RequestMethod.POST })
    public void toPayPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		//获取oil用户
		String codeNum = request.getParameter("codeNum");
		_LOGGER.error("codeNum:"+codeNum);
		String data = request.getParameter("data");
		_LOGGER.error("data:"+data);
		String msg = request.getParameter("msg");
		_LOGGER.error("msg:"+msg);
		String result = request.getParameter("result");
		_LOGGER.error("result:"+result);

		//当前用户购买信息
		MallUserMode userbean = refreshtWxLoginUser(request);
		
        int diamond_id = super.getInt(request, "diamond_id");
		
        ChangeMngMode changeMngMode = changeMngService.findInfo(diamond_id);
        TchangeOrderMode tchangeOrderMode = new TchangeOrderMode();
        String order_no = TenpayUtil.getOrder_no();
       
		tchangeOrderMode.setUSER_ID(userbean.getID());
		tchangeOrderMode.setCHANGE_ID(changeMngMode.getID());
		tchangeOrderMode.setORDER_NO(order_no);
		tchangeOrderMode.setMONEY(changeMngMode.getMONEY());
		tchangeOrderMode.setCREAT_TIME(DateUtil.get4yMdHms(new Date()));
		tchangeOrderMode.setPAYED_FLAG("0");
		tchangeOrderMode.setPAY_MONEY(changeMngMode.getPAY_MONEY());
		tchangeOrderMode.setPAYED_TIME(null);
		tchangeOrderMode.setDELETE_FLAG("0");
		tchangeOrderMode.setDIAMOND_NUMB(changeMngMode.getDIAMOND_NUMB());
        tchangeOrderService.saveInfo(tchangeOrderMode);
		
        String rechargeType="zshgame";//商品类型
        String lifeType="my_buy";//商品名称
        
    	String payamount=tchangeOrderMode.getPAY_MONEY();//订单支付金额
        String userId=userbean.getTHIRD_PART_ID();//有礼付userId
        String openId=userbean.getOPEN_ID();//有礼付openId

        String redPkgId="";//红包ID
        String redPkgValue="";//红包面额
        
        String domain_name=PropertiesUtil.get("DOMAIN.WEB_SERVER");
        String backUrl=domain_name+"/sinopecGameCt/weixinMng/ManageC/userIn.htm";//点击"返回首页"跳转页面
        String redirectUrl=domain_name+"/sinopecGameCt/weixinMng/ManageC/toPayPage_return.htm?ORDER_NO="+order_no;//接收参数方法接口

        String targetUrl=proUrl+"?payamount="+payamount+"&rechargeType="+
                rechargeType+"&lifeType="+lifeType+"&redPkgId="+redPkgId+"&redPkgValue="+redPkgValue+
                "&backUrl="+backUrl+"&redirectUrl="+redirectUrl+"&userId="+userId+"&openId="+openId;
        _LOGGER.error("跳转支付页面地址:"+targetUrl);
        response.sendRedirect(response.encodeRedirectURL(targetUrl));
    }
	
    @RequestMapping(value = "/toPayPage_return",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void toPayPage_return(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
    	_LOGGER.info("sinopecGameCt进入测试回调方法==================================================");
    	//201707280949417819850
    	String ip = IPUtil.getRemoteHost(request);
		_LOGGER.info("ip地址为=================================================="+ip);
        String code=request.getParameter("code");//1:成功  其他都是失败
        String mess=request.getParameter("mess");//成功返回订单id，失败返回提示信息
        String ORDER_NO=request.getParameter("ORDER_NO");//成功返回订单id，失败返回提示信息
        _LOGGER.info("code:"+code+",mess:"+mess+",ORDER_NO:"+ORDER_NO);
        
        if("1".equals(code)){
        	// 成功的时候记录表
        	tchangeOrderService.changeReturn_Diamond(ORDER_NO);
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
}
