package org.jumutang.project.weixinMng.prizeMng.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.weixinMng.gameThree.model.T3GameRecordMode;
import org.jumutang.project.weixinMng.gameThree.model.T3UserGameMode;
import org.jumutang.project.weixinMng.gameThree.service.IT3GameRecordService;
import org.jumutang.project.weixinMng.gameThree.service.IT3UserGameService;
import org.jumutang.project.weixinMng.gameTwo.model.GameTwoMode;
import org.jumutang.project.weixinMng.gameTwo.model.T2GameRecordMode;
import org.jumutang.project.weixinMng.gameTwo.service.IGameTwoService;
import org.jumutang.project.weixinMng.mallMng.dao.impl.ManageDaoImpl;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.service.IManageService;
import org.jumutang.project.weixinMng.mallMng.service.impl.ManageServiceImpl;
import org.jumutang.project.weixinMng.prizeMng.model.DiamondRecord;
import org.jumutang.project.weixinMng.prizeMng.service.IDiamondRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/7/11.
 */


//钻石
@Controller
@RequestMapping(value = "/weixinMng/getDiamonds" ,method = {RequestMethod.GET,RequestMethod.POST})
public class DiamondsController extends BaseController {

    private Logger logger = Logger.getLogger(DiamondsController.class);


    @Autowired
    private IDiamondRecordService diamondRecordService;

    @Autowired
    private ManageDaoImpl manageDao;

    @Autowired
    private IGameTwoService gameTwoService;

    @Autowired
    private IManageService manageService;

    @Autowired
    private IT3UserGameService t3UserGameService;

    @Autowired
    private IT3GameRecordService t3GameRecordService;

    //调用接口获取钻石
    @RequestMapping(value = "/diamondsPrize")
    @ResponseBody
    public String diamondsPrize(HttpServletRequest request , HttpServletResponse response , HttpSession session){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject jsonObject = new JSONObject();
        DiamondsController diamond = new DiamondsController();

        //测试暂时注释
        //控制接口调用频率
        if(session.getAttribute("diamondsPrizeTime") == null){
            session.setAttribute("diamondsPrizeTime", new Date());
        }else {
            Date diamondsPrizeTime = (Date) session.getAttribute("diamondsPrizeTime");
            Date currentDate = new Date();
            int interval = (int) (currentDate.getTime() - diamondsPrizeTime.getTime()) / 1000;
            session.setAttribute("diamondsPrizeTime", new Date());
            if (interval < 5) {
                jsonObject.put("result","1");
                jsonObject.put("message","接口频率调用次数过多请重试");
                return JSON.toJSONString(jsonObject);
            }
        }

        //获取游戏gameId
        String gameId = request.getParameter("gameId");

        //获取当前userId和openId
        MallUserMode mallUsermode = getWxLoginUser(request);
        String openId = mallUsermode.getOPEN_ID();
        String userId = mallUsermode.getID();

        logger.info("---钻石接口--用户信息----[ gameId:"+gameId+" ;openId:"+openId+" ;userId:"+userId+" ]");

        //判断当前游戏类型
        if("1".equals(gameId)){ //加油达人

            Date date = new Date();

            //查询当前用户userId 当前游戏gameId 当天day 是否获取钻石
            DiamondRecord diamondRecord = new DiamondRecord();
            diamondRecord.setGameId(gameId); //游戏gameId
            diamondRecord.setUserId(userId); //用户userId
            diamondRecord.setCreateTime(simpleDateFormat.format(date)); //当前日期 yyyy-mm-dd
            List<DiamondRecord> list = diamondRecordService.listDiamondRecord(diamondRecord);

            /**
             * 注释当前查询是否存在钻石记录，提高钻石获得几率
             * */
//            if(list.size()>0){ //今日用户已经存在免费获得钻石
//                jsonObject.put("result","0");
//                jsonObject.put("status","false");
//                jsonObject.put("message","当前用户今日已经获取钻石！");
//            }else{//用户未获得钻石

                String  diamondCountRand = String.valueOf(diamond.random_Diamond());
                logger.info("当前随机random获取钻石记录:"+diamondCountRand);

                //添加钻石记录
                diamondRecord.setDiamonds(diamondCountRand);
                diamondRecordService.saveDiamondRecord(diamondRecord);

                //修改用户当前钻石总额
                MallUserMode mallUserMode = new MallUserMode();
                mallUserMode.setOPEN_ID(openId);
                mallUserMode.setALL_DIAMOND(diamondCountRand);
                int updateCount = manageService.UpdateDiamond(mallUserMode);

                logger.info("修改用户钻石总额状态:"+updateCount);

                jsonObject.put("result",diamondCountRand);
                jsonObject.put("status","success");
                jsonObject.put("message","添加用户获取钻石记录success");
//            }
     }else if("2".equals(gameId)){ //扭蛋机
            // 取得用户登陆信息
            MallUserMode userbean = refreshtWxLoginUser(request);
            // 用户游戏2的相关信息
            GameTwoMode gameTwoMode = refreshGameTwo(request);
            if(Integer.parseInt(gameTwoMode.getEnergyNum())==5) {

                //钻石记录表插入数据
                //当前随机获取钻石
                String  diamondCountRand = String.valueOf(diamond.random_Diamond());
                Date date = new Date();
                DiamondRecord diamondRecord = new DiamondRecord();
                diamondRecord.setGameId(gameId); //游戏gameId
                diamondRecord.setUserId(userId); //用户userId
                diamondRecord.setDiamonds(diamondCountRand);
                int refId = diamondRecordService.saveDiamondRecord(diamondRecord);

                String recordId = request.getParameter("recordId");
                if(StringUtils.isNotBlank(recordId)){
                    T2GameRecordMode t2GameRecordMode = new T2GameRecordMode();
                    t2GameRecordMode.setId(recordId);
                    t2GameRecordMode.setType("1");
                    t2GameRecordMode.setRefId(String.valueOf(refId));
                    gameTwoService.updateUserGameTwoRecord(t2GameRecordMode);
                }

                //修改用户当前钻石总额
                MallUserMode mallUserMode = new MallUserMode();
                mallUserMode.setOPEN_ID(openId);
                mallUserMode.setALL_DIAMOND(diamondCountRand);
                int updateCount = manageService.UpdateDiamond(mallUserMode);

                //能量设为0
                gameTwoService.updateUserGameEnergyNum(userbean,0);

                jsonObject.put("result",true);
                jsonObject.put("diamond",diamondCountRand);

            }else{
                jsonObject.put("result",false);
                jsonObject.put("message","能量不足无法暴击");
            }
            return JSON.toJSONString(jsonObject);
        }else if("3".equals(gameId)){ //扭蛋机
            // 取得用户登陆信息
            MallUserMode userbean = refreshtWxLoginUser(request);
            // 用户游戏2的相关信息
            T3UserGameMode t3UserGameMode = new T3UserGameMode();
            t3UserGameMode.setUserId(userbean.getID());
            t3UserGameMode = t3UserGameService.list(t3UserGameMode).get(0);
            if(Integer.parseInt(t3UserGameMode.getEnergyNum())==5) {

                //钻石记录表插入数据
                //当前随机获取钻石
                String  diamondCountRand = String.valueOf(diamond.random_Diamond());
                DiamondRecord diamondRecord = new DiamondRecord();
                diamondRecord.setGameId(gameId); //游戏gameId
                diamondRecord.setUserId(userId); //用户userId
                diamondRecord.setDiamonds(diamondCountRand);
                int refId = diamondRecordService.saveDiamondRecord(diamondRecord);

                String recordId = request.getParameter("recordId");
                if(StringUtils.isNotBlank(recordId)){
                    T3GameRecordMode t3GameRecordMode = new T3GameRecordMode();
                    t3GameRecordMode.setId(recordId);
                    t3GameRecordMode.setPrizeType(String.valueOf("1"));
                    t3GameRecordMode.setRefId(String.valueOf(refId));
                    t3GameRecordService.updateT3GameRecord(t3GameRecordMode);
                }

                //修改用户当前钻石总额
                MallUserMode mallUserMode = new MallUserMode();
                mallUserMode.setOPEN_ID(openId);
                mallUserMode.setALL_DIAMOND(diamondCountRand);
                manageService.UpdateDiamond(mallUserMode);

                //能量设为0
                t3UserGameMode.setEnergyNum("0");
                t3UserGameService.updateT3UserGame(t3UserGameMode);

                jsonObject.put("result",true);
                jsonObject.put("diamond",diamondCountRand);

            }else{
                jsonObject.put("result",false);
                jsonObject.put("message","能量不足无法暴击");
            }
            return JSON.toJSONString(jsonObject);
        }
        return JSON.toJSONString(jsonObject);
    }


    //---------------------------------------------------------------------------------------------------------------------

    //改造接口，调用抽奖接口
    @RequestMapping(value = "/diamondsOrRedGift")
    public ModelAndView GetDiamondsOrRedGift(HttpServletRequest request ,HttpServletResponse response ,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        Random random = new Random();
        int rand_count  = random.nextInt(10);

        //测试
//        rand_count = 3;

        if(rand_count<2){
            logger.info("未中奖---20%");
            modelAndView.setViewName("forward:/weixinMng/getDiamonds/losePrize.htm");
        }else if (rand_count>=2 && rand_count<5){
            logger.info("礼品---抽奖---30%");
            modelAndView.setViewName("forward:/weixinMng/getPrize/randomPrize.htm");
        }else {
            logger.info("钻石---抽奖---50%");
            modelAndView.setViewName("forward:/weixinMng/getDiamonds/diamondsPrize.htm");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/randomDiamonds")
    public ModelAndView randomDiamonds(HttpServletRequest request ,HttpServletResponse response ,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        Random random = new Random();
        int rand_count  = random.nextInt(10);

        if(rand_count<7){
            logger.info("未中奖---20%");
            modelAndView.setViewName("forward:/weixinMng/getDiamonds/losePrize.htm");
        }else {
            logger.info("钻石---抽奖---50%");
            modelAndView.setViewName("forward:/weixinMng/getDiamonds/diamondsPrize.htm");
        }

        return modelAndView;
    }

    //未中奖
    @RequestMapping(value = "/losePrize")
    @ResponseBody
    public String losePrize(HttpServletRequest request ,HttpServletResponse response,HttpSession session){

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("result","0");
        jsonObject.put("status","false");
        jsonObject.put("message","未中奖！");

       return jsonObject.toJSONString();
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

    //随机获取钻石
    public int random_Diamond(){

        return 10+new Random().nextInt(26);
    }


    public static void main(String  args []){
        Random rand = new Random();

        int O = 0 ;
        int O2 = 0;
        int O3 = 0;

        for(int i = 0 ;i <100;i++){
            int count = rand.nextInt(10);
            if(count<2) {  // 0 1   --> 20%
                O++;
            }else if (count >= 2  && count<5){ // 2 3 4  --> 30%
                O2++;
            }else { // 5 6 7 8 9 --> 50%
                O3++;
            }
        }

        System.out.println("20%:"+O);
        System.out.println("30%:"+O2);
        System.out.println("50%:"+O3);


    }


}
