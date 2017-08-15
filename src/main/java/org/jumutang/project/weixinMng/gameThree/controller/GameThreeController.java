package org.jumutang.project.weixinMng.gameThree.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.weixinMng.gameThree.dao.T3UserGameDao;
import org.jumutang.project.weixinMng.gameThree.model.T3AttributeBuyMode;
import org.jumutang.project.weixinMng.gameThree.model.T3GameRecordMode;
import org.jumutang.project.weixinMng.gameThree.model.T3UserGameMode;
import org.jumutang.project.weixinMng.gameThree.service.IGameThreeService;
import org.jumutang.project.weixinMng.gameThree.service.IT3AttributeBuyService;
import org.jumutang.project.weixinMng.gameThree.service.IT3GameRecordService;
import org.jumutang.project.weixinMng.gameThree.service.IT3UserGameService;
import org.jumutang.project.weixinMng.gameTwo.controller.GameTwoController;
import org.jumutang.project.weixinMng.gameTwo.model.GameTwoMode;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.service.IManageService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Date: Create in 10:11 2017/7/25
 * @Modified By:
 */
@Controller
@RequestMapping(value = "/weixinMng/GameThreeC", method = { RequestMethod.GET, RequestMethod.POST })
public class GameThreeController extends BaseController {

    private static final Logger _LOGGER = Logger.getLogger(GameThreeController.class);

    private static final int primary = 10;

    private static final int middle = 30;

    private static final int high = 50;

    @Autowired
    private IManageService manageService;

    @Autowired
    private IGameThreeService gameThreeService;

    @Autowired
    private IT3UserGameService t3UserGameService;

    @Autowired
    private IT3GameRecordService t3GameRecordService;

    @Autowired
    private IT3AttributeBuyService t3AttributeBuyService;

    // 用户进入游戏首页
    @RequestMapping(value = "/gameThreeIn", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView userIn(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
        // 取得用户登陆信息
        MallUserMode userbean = refreshtWxLoginUser(request);

        // 取得游戏信息
        T3UserGameMode t3UserGameMode = new T3UserGameMode();
        t3UserGameMode.setUserId(userbean.getID());
        t3UserGameService.saveT3UserGame(t3UserGameMode);
        t3UserGameMode = t3UserGameService.list(t3UserGameMode).get(0);

        request.getSession().setAttribute("game3Level",primary);

        // 初始化游戏
        ModelAndView view = new ModelAndView("/jsp/weixinMng/gameThree/index.jsp");
        view.addObject("USERBEAN", userbean);
        view.addObject("T3USERGAMEMODE",t3UserGameMode);
        return view;
    }

    // 用户切换游戏状态
    @RequestMapping(value = "/switchLevel", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String switchLevel(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
        JSONObject jsonObject = new JSONObject();
        // 取得用户登陆信息
        String game2Level = request.getParameter("level");
        if(StringUtils.isBlank(game2Level)){
            jsonObject.put("result",false);
            jsonObject.put("message","更换失败");
            return JSON.toJSONString(jsonObject);
        }
        switch (game2Level){
            case "primary":
                request.getSession().setAttribute("game3Level",primary);
                break;
            case "middle":
                request.getSession().setAttribute("game3Level",middle);
                break;
            case "high":
                request.getSession().setAttribute("game3Level",high);
                break;
        }
        jsonObject.put("result",true);
        return JSON.toJSONString(jsonObject);
    }

    //检查是否有资格抽奖
    /*@RequestMapping(value = "/canPrize", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String canPrize(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
        JSONObject jsonObject = new JSONObject();
        // 取得用户登陆信息
        MallUserMode userbean = refreshtWxLoginUser(request);
        // 用户游戏2的相关信息
        if(request.getSession().getAttribute("game3Level")==null){
            jsonObject.put("result",false);
            jsonObject.put("message","无法获取session");
            return JSON.toJSONString(jsonObject);
        }
        int userDiamond = Integer.parseInt(String.valueOf(request.getSession().getAttribute("game3Level")));
        if(Integer.parseInt(userbean.getREMAIN_DIAMOND())>=userDiamond){
            jsonObject.put("result",true);
            jsonObject.put("remainTimes",0);
        }else{
            jsonObject.put("result",false);
            jsonObject.put("message","钻石数不够");
        }
        return JSON.toJSONString(jsonObject);
    }*/

    //抽奖
    @RequestMapping(value = "/getPrize", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String getPrize(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
        JSONObject jsonObject = new JSONObject();
        // 取得用户登陆信息
        MallUserMode userbean = refreshtWxLoginUser(request);
        // 用户游戏2的相关信息
        if(request.getSession().getAttribute("game3Level")==null){
            jsonObject.put("result",false);
            jsonObject.put("message","无法获取session");
            return JSON.toJSONString(jsonObject);
        }

        int userDiamond = Integer.parseInt(String.valueOf(request.getSession().getAttribute("game3Level")));
        if(Integer.parseInt(userbean.getREMAIN_DIAMOND())>=userDiamond){
            userbean.setUSED_DIAMOND(String.valueOf(Integer.parseInt(userbean.getUSED_DIAMOND())+userDiamond));
            //更新使用钻石数量
            gameThreeService.updateUserGameTwoDiamond(userbean);
            //新增扣钻石记录
            T3AttributeBuyMode t3AttributeBuyMode = new T3AttributeBuyMode();
            t3AttributeBuyMode.setUserId(userbean.getID());
            t3AttributeBuyMode.setPayDiamond(String.valueOf(userDiamond));
            t3AttributeBuyService.saveT3AttributeBuy(t3AttributeBuyMode);
            //更新游戏次数
            t3UserGameService.updateT3UserGameOnce(userbean.getID());
            //增加游戏记录
            T3GameRecordMode t3GameRecordMode = new T3GameRecordMode();
            t3GameRecordMode.setUserId(userbean.getID());
            //t3GameRecordMode.setGameType(String.valueOf(userDiamond));
            int recordId = t3GameRecordService.saveT3GameRecord(t3GameRecordMode);
            //response.sendRedirect(request.getContextPath()+"/weixinMng/getPrize/randomPrize.htm?recordId="+recordId);
            request.getRequestDispatcher(request.getContextPath()+"/weixinMng/getPrize/randomPrize.htm?recordId="+recordId).forward(request,response);
        }else{

            jsonObject.put("result",false);
            jsonObject.put("message","钻石数不够");
        }
        return JSON.toJSONString(jsonObject);
    }

    //暴击
    @RequestMapping(value = "/getDiamond", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String getDiamond(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws Exception{
        JSONObject jsonObject = new JSONObject();
        // 取得用户登陆信息
        MallUserMode userbean = refreshtWxLoginUser(request);
        // 用户游戏2的相关信息
        T3UserGameMode t3UserGameMode = new T3UserGameMode();
        t3UserGameMode.setUserId(userbean.getID());
        t3UserGameMode = t3UserGameService.list(t3UserGameMode).get(0);
        if(Integer.parseInt(t3UserGameMode.getEnergyNum())==5){
            //增加游戏记录
            T3GameRecordMode t3GameRecordMode = new T3GameRecordMode();
            t3GameRecordMode.setUserId(userbean.getID());
            t3GameRecordMode.setGameType(String.valueOf("1"));
            int recordId = t3GameRecordService.saveT3GameRecord(t3GameRecordMode);
            response.sendRedirect(request.getContextPath()+"/weixinMng/getDiamonds/diamondsPrize.htm?gameId=3&recordId="+recordId);
        }else{
            jsonObject.put("result",false);
            jsonObject.put("message","能量不足无法暴击");
            return JSON.toJSONString(jsonObject);
        }
        return null;
    }

    //我知道了
    @RequestMapping(value = "/iKnow", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String iKnow(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws Exception{
        JSONObject jsonObject = new JSONObject();
        // 取得用户登陆信息
        MallUserMode userbean = refreshtWxLoginUser(request);
        // 取得游戏信息
        T3UserGameMode t3UserGameMode = new T3UserGameMode();
        t3UserGameMode.setUserId(userbean.getID());
        t3UserGameMode = t3UserGameService.list(t3UserGameMode).get(0);
        t3UserGameMode.setKnow("1");
        int result = t3UserGameService.updateT3UserGame(t3UserGameMode);
        if(result == 0){
            jsonObject.put("result",false);
            jsonObject.put("message","更新失败");
        }else{
            jsonObject.put("result",true);
        }
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
