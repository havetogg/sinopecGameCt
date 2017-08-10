package org.jumutang.project.weixinMng.gameTwo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.weixinMng.gameOne.model.GameOneMode;
import org.jumutang.project.weixinMng.gameTwo.model.GameTwoMode;
import org.jumutang.project.weixinMng.gameTwo.model.T2UserGameMode;
import org.jumutang.project.weixinMng.gameTwo.service.IGameTwoService;
import org.jumutang.project.weixinMng.gameTwo.service.IT2AttributeBuyService;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.service.IGameMngService;
import org.jumutang.project.weixinMng.mallMng.service.IManageService;
import org.jumutang.project.weixinMng.prizeMng.model.PrizeRedeem;
import org.jumutang.project.weixinMng.prizeMng.service.IPrizeRedeemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 14:18 2017/7/12
 * @Modified By:
 */

@Controller
@RequestMapping(value = "/weixinMng/GameTwoC", method = { RequestMethod.GET, RequestMethod.POST })
public class GameTwoController extends BaseController {

    private static final Logger _LOGGER = Logger.getLogger(GameTwoController.class);

    @Autowired
    private IGameTwoService gameTwoService;

    @Autowired
    private IManageService manageService;

    @Autowired
    private IT2AttributeBuyService it2AttributeBuyService;

    @Autowired
    private IPrizeRedeemService iPrizeRedeemService;


    // 游戏2的id
    @Value(value = "#{propertyFactoryBean['gameTwoid']}")
    private String gameTwoid;


    // 用户进入游戏首页
    @RequestMapping(value = "/gameTwoIn", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView userIn(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
        // 取得用户登陆信息
        MallUserMode userbean = refreshtWxLoginUser(request);
        //新增游戏记录
        gameTwoService.saveUserGameTwo(userbean);
        // 初始化游戏
        ModelAndView view = new ModelAndView("/jsp/weixinMng/gameTwo/index.jsp");
        // 用户游戏2的相关信息
        GameTwoMode gameTwoMode = refreshGameTwo(request);
        //查询最近的中奖结果用户信息
        JSONArray prizeJSONArray = iPrizeRedeemService.prizeList();

        view.addObject("GAMETWOBEAN", gameTwoMode);
        view.addObject("USERBEAN", userbean);
        view.addObject("prizeJSONArray", prizeJSONArray);
        return view;
    }

    //检查是否有资格抽奖并且返回免费次数
    @RequestMapping(value = "/canPrize", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String canPrize(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
        JSONObject jsonObject = new JSONObject();
        // 取得用户登陆信息
        MallUserMode userbean = refreshtWxLoginUser(request);
        // 用户游戏2的相关信息
        GameTwoMode gameTwoMode = refreshGameTwo(request);
        int todayRemainTimes = Integer.parseInt(gameTwoMode.getTodayRemainTimes());
        if(todayRemainTimes >0){
            jsonObject.put("result",true);
            jsonObject.put("remainTimes",1);
        }else if(Integer.parseInt(userbean.getREMAIN_DIAMOND())>=10){
            jsonObject.put("result",true);
            jsonObject.put("remainTimes",0);
        }else{
            jsonObject.put("result",false);
            jsonObject.put("message","免费次数用完并且钻石数不够");
        }
        return JSON.toJSONString(jsonObject);
    }


    //抽奖
    @RequestMapping(value = "/getPrize", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String  getPrize(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
        // 取得用户登陆信息
        MallUserMode userbean = refreshtWxLoginUser(request);
        // 用户游戏2的相关信息
        GameTwoMode gameTwoMode = refreshGameTwo(request);
        //获取是否同意扣款
        String allow  = request.getParameter("allow");

        //1.获取用户抽奖免费次数 如果次数大于0 则使用免费次数 更新数据库 跳转链接
        int todayRemainTimes = Integer.parseInt(gameTwoMode.getTodayRemainTimes());
        if(todayRemainTimes >0){
            //更新游戏次数
            gameTwoService.updateUserGameTwo(userbean);
            //增加游戏记录
            int recordId = gameTwoService.saveUserGameTwoRecord(userbean);
            response.sendRedirect(request.getContextPath()+"/weixinMng/getPrize/randomPrize.htm?gameId=2&recordId="+recordId);
        }//2.如果免费次数用完了则 看是否允许使用 如果允许 更新数据库 跳转链接
        else if(Integer.parseInt(userbean.getREMAIN_DIAMOND())>=10){
            userbean.setUSED_DIAMOND(String.valueOf(Integer.parseInt(userbean.getUSED_DIAMOND())+10));
            //更新使用钻石数量
            gameTwoService.updateUserGameTwoDiamond(userbean);
            //新增扣钻石记录
            it2AttributeBuyService.saveT2AttributeBuy(userbean);
            //更新游戏次数
            gameTwoService.updateUserGameTwo(userbean);
            //增加游戏记录
            int recordId = gameTwoService.saveUserGameTwoRecord(userbean);
            response.sendRedirect(request.getContextPath()+"/weixinMng/getPrize/randomPrize.htm?gameId=2&recordId="+recordId);
        }//3.如果不允许，或者钻石数量不够 返回无法抽奖信息
        else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result",false);
            jsonObject.put("message","钻石数量不够，无法抽奖");
            return JSON.toJSONString(jsonObject);
        }
        return null;
    }

    //我知道了
    @RequestMapping(value = "/iKnow", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String iKnow(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws Exception{
        // 取得用户登陆信息
        MallUserMode userbean = refreshtWxLoginUser(request);
        // 用户游戏2的相关信息
        GameTwoMode gameTwoMode = refreshGameTwo(request);
        gameTwoService.updateGameTwoIKnow(userbean);
        return null;
    }

    //暴击
    @RequestMapping(value = "/getDiamond", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String getDiamond(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws Exception{
        // 取得用户登陆信息
        MallUserMode userbean = refreshtWxLoginUser(request);
        // 用户游戏2的相关信息
        GameTwoMode gameTwoMode = refreshGameTwo(request);
        if(Integer.parseInt(gameTwoMode.getEnergyNum())==5){
            int recordId = gameTwoService.saveUserGameTwoRecord(userbean);
            response.sendRedirect(request.getContextPath()+"/weixinMng/getDiamonds/diamondsPrize.htm?gameId=2&recordId="+recordId);
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result",false);
            jsonObject.put("message","能量不足无法暴击");
            return JSON.toJSONString(jsonObject);
        }
        return null;
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

    private GameTwoMode refreshGameTwo(HttpServletRequest request) {
        MallUserMode userinfo = super.getWxLoginUser(request);
        GameTwoMode gameTwoMode = gameTwoService.findUserGameTwoInfo(userinfo);
        return gameTwoMode;
    }
}
