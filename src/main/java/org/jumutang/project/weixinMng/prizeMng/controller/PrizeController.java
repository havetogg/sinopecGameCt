package org.jumutang.project.weixinMng.prizeMng.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.tools.*;
import org.jumutang.project.weixinMng.broadcast.WebSocket;
import org.jumutang.project.weixinMng.gameTwo.model.GameTwoMode;
import org.jumutang.project.weixinMng.gameTwo.model.T2GameRecordMode;
import org.jumutang.project.weixinMng.gameTwo.service.IGameTwoService;
import org.jumutang.project.weixinMng.mallMng.controller.ChangeMngController;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.TchangeOrderMode;
import org.jumutang.project.weixinMng.mallMng.service.IManageService;
import org.jumutang.project.weixinMng.mallMng.service.ITchangeOrderService;
import org.jumutang.project.weixinMng.prizeMng.model.Prize;
import org.jumutang.project.weixinMng.prizeMng.model.PrizePool;
import org.jumutang.project.weixinMng.prizeMng.model.PrizeRedeem;
import org.jumutang.project.weixinMng.prizeMng.service.IPrizePoolService;
import org.jumutang.project.weixinMng.prizeMng.service.IPrizeRedeemService;
import org.jumutang.project.weixinMng.prizeMng.service.IPrizeService;
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
import java.net.URLEncoder;
import java.util.*;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 10:58 2017/7/12
 * @Modified By:
 */
//奖品抽奖
@Controller
@RequestMapping(value = "/weixinMng/getPrize" ,method = {RequestMethod.GET,RequestMethod.POST})
public class PrizeController extends BaseController{

    private static final Logger _LOGGER = Logger.getLogger(PrizeController.class);

    @Autowired
    private IManageService manageService;

    @Autowired
    private ITchangeOrderService iTchangeOrderService;

    @Autowired
    private IPrizeRedeemService iPrizeRedeemService;

    @Autowired
    private IPrizeService iPrizeService;

    @Autowired
    private IGameTwoService gameTwoService;

    @Autowired
    private IPrizePoolService iPrizePoolService;

    //获取中石化openId
    @Value("#{propertyFactoryBean['get_zsh_openId']}")
    protected String getZshOpenIdUrl;

    @Value("#{propertyFactoryBean['add_red_url']}")
    protected String addRedUrl;
    @Value("#{propertyFactoryBean['query_red_url']}")
    protected String queryRedUrl;
    @Value("#{propertyFactoryBean['update_red_url']}")
    protected String updateRedUrl;

    @Value("#{propertyFactoryBean['exchange_oil_url']}")
    protected String exchange_oil_url;

    //用户随机抽奖功能
    @RequestMapping(value = "/randomPrize")
    @ResponseBody
    public String randomPrize(HttpServletRequest request , HttpServletResponse response , HttpSession session){

        JSONObject jsonObject = new JSONObject();

        //1.控制接口调用频率 10秒一次
        //控制接口调用频率
        /*if(session.getAttribute("PrizeTime") == null){
            session.setAttribute("PrizeTime", new Date());
        }else {
            Date diamondsPrizeTime = (Date) session.getAttribute("PrizeTime");
            Date currentDate = new Date();
            int interval = (int) (currentDate.getTime() - diamondsPrizeTime.getTime()) / 1000;
            session.setAttribute("PrizeTime", new Date());
            if (interval < 5) {
                jsonObject.put("result",false);
                jsonObject.put("message","接口频率调用次数过多请重试");
                return JSON.toJSONString(jsonObject);
            }
        }*/
        String gameId = request.getParameter("gameId");
        String recordId = request.getParameter("recordId");
        //2.查看用户充钱数量 如果充钱为0直接让其无法中奖 返回空
        // 取得用户登陆信息payedFlag
        MallUserMode userbean = refreshtWxLoginUser(request);
        PrizePool prizePool = new PrizePool();
        prizePool.setUserId(userbean.getID());
        prizePool = iPrizePoolService.list(prizePool).get(0);
        if("0".equals(prizePool.getPrizePool())){
            //没中奖哦
            if(StringUtils.isNotBlank(gameId)&&StringUtils.isNotBlank(recordId)){
                if("2".equals(gameId)){
                    // 用户游戏2的相关信息
                    GameTwoMode gameTwoMode = refreshGameTwo(request);
                    T2GameRecordMode t2GameRecordMode = new T2GameRecordMode();
                    t2GameRecordMode.setId(recordId);
                    t2GameRecordMode.setType("2");
                    //更新游戏2游戏结果
                    gameTwoService.updateUserGameTwoRecord(t2GameRecordMode);
                    //更新暴击
                    gameTwoService.updateUserGameEnergyNum(userbean,Integer.parseInt(gameTwoMode.getEnergyNum())==5?5:Integer.parseInt(gameTwoMode.getEnergyNum())+1);
                }
            }
            jsonObject.put("result",true);
            jsonObject.put("prize",false);
            return JSON.toJSONString(jsonObject);
        }
        //3.如果有充钱，减去用户中奖价值，得到净充钱，乘以80%即所能得到的
        double payMoney = Double.parseDouble(prizePool.getPrizePool());
        double getMoney = Double.parseDouble(prizePool.getUsedPool());
        double canGetMoney = (payMoney - getMoney)>0?(payMoney - getMoney)*0.8:0;
        //4.将所得到的钱数 查询得到档次 划分概率
        Prize prize = new Prize();
        prize.setGrade(String.valueOf(canGetMoney));
        List<Prize> prizes = iPrizeService.listPrize(prize);
        //没有匹配档次的商品
        if(prizes.size()>0){
            int size = prizes.size()*20;
            int random = (int) (Math.random() * size);
            if(random>=0&&random<size/2-1){
                //中奖了
                Prize prize1 = prizes.get((int) (Math.random() * prizes.size()));
                PrizeRedeem prizeRedeem1 = new PrizeRedeem();
                prizeRedeem1.setUserId(userbean.getID());
                prizeRedeem1.setPrizeId(prize1.getId());
                if("0".equals(prize1.getType())){
                    //0 代表加油卡
                    prizeRedeem1.setRedeemCode(RandomTool.getRandomString(10));
                }else if("1".equals(prize1.getType())){
                    //1 代表油滴
                    prizeRedeem1.setRedeemCode(RandomTool.getRandomStringByType("01"));
                }
                int refId = iPrizeRedeemService.savePrizeRedeem(prizeRedeem1);
                if(StringUtils.isNotBlank(gameId)&&StringUtils.isNotBlank(recordId)){
                    if("2".equals(gameId)){
                        T2GameRecordMode t2GameRecordMode = new T2GameRecordMode();
                        t2GameRecordMode.setId(recordId);
                        t2GameRecordMode.setType("0");
                        t2GameRecordMode.setRefId(String.valueOf(refId));
                        gameTwoService.updateUserGameTwoRecord(t2GameRecordMode);
                        //更新暴击
                        gameTwoService.updateUserGameEnergyNum(userbean,0);
                    }
                }
                PrizePool usedPool = new PrizePool();
                usedPool.setUserId(userbean.getID());
                usedPool.setUsedPool(prize1.getGrade());
                iPrizePoolService.updatePrizePool(usedPool);

                PrizePool AllPrizePool = new PrizePool();
                AllPrizePool.setId("1");
                AllPrizePool.setUsedPool(prize1.getGrade());
                iPrizePoolService.updatePrizePool(AllPrizePool);

                jsonObject.put("result",true);
                jsonObject.put("prize",true);
                jsonObject.put("prizeName",prize1.getPrizeName());
                //改库存之后写
            }else if(random>=size/2-1&&random<size*0.05+size/2-1){
                //中了额外奖励
                List<Prize> AllPrizes = iPrizeService.listPrize(new Prize());
                Prize prize1 = AllPrizes.get((int) (Math.random() * AllPrizes.size()));
                PrizeRedeem prizeRedeem1 = new PrizeRedeem();
                prizeRedeem1.setUserId(userbean.getID());
                prizeRedeem1.setPrizeId(prize1.getId());
                if("0".equals(prize1.getType())){
                    //0 代表加油卡
                    prizeRedeem1.setRedeemCode(RandomTool.getRandomString(10));
                }else if("1".equals(prize1.getType())){
                    //1 代表油滴
                    prizeRedeem1.setRedeemCode(RandomTool.getRandomStringByType("01"));
                }
                int refId = iPrizeRedeemService.savePrizeRedeem(prizeRedeem1);
                if(StringUtils.isNotBlank(gameId)&&StringUtils.isNotBlank(recordId)){
                    if("2".equals(gameId)) {
                        T2GameRecordMode t2GameRecordMode = new T2GameRecordMode();
                        t2GameRecordMode.setId(recordId);
                        t2GameRecordMode.setType("0");
                        t2GameRecordMode.setRefId(String.valueOf(refId));
                        gameTwoService.updateUserGameTwoRecord(t2GameRecordMode);
                        //更新暴击
                        gameTwoService.updateUserGameEnergyNum(userbean, 0);
                    }
                }

                PrizePool usedPool = new PrizePool();
                usedPool.setUserId(userbean.getID());
                usedPool.setUsedPool(prize1.getGrade());
                iPrizePoolService.updatePrizePool(usedPool);

                PrizePool AllPrizePool = new PrizePool();
                AllPrizePool.setId("1");
                AllPrizePool.setUsedPool(prize1.getGrade());
                iPrizePoolService.updatePrizePool(AllPrizePool);

                jsonObject.put("result",true);
                jsonObject.put("prize",true);
                jsonObject.put("prizeName",prize1.getPrizeName());
            }else{
                //没中
                if(StringUtils.isNotBlank(gameId)&&StringUtils.isNotBlank(recordId)){
                    if("2".equals(gameId)) {
                        // 用户游戏2的相关信息
                        GameTwoMode gameTwoMode = refreshGameTwo(request);
                        T2GameRecordMode t2GameRecordMode = new T2GameRecordMode();
                        t2GameRecordMode.setId(recordId);
                        t2GameRecordMode.setType("2");
                        gameTwoService.updateUserGameTwoRecord(t2GameRecordMode);
                        gameTwoService.updateUserGameEnergyNum(userbean, Integer.parseInt(gameTwoMode.getEnergyNum()) == 5 ? 5 : Integer.parseInt(gameTwoMode.getEnergyNum()) + 1);
                    }
                }
                jsonObject.put("result",true);
                jsonObject.put("prize",false);
            }
        }else if(prizes.size()==0&&canGetMoney>0){
            int random = (int) (Math.random() * 100);
            if(random>=0&&random<3){
                //中了额外奖励
                List<Prize> Allprizes = iPrizeService.listPrize(new Prize());
                Prize prize1 = null;
                //只能给最低的奖励
                for(Prize prize2:Allprizes){
                    if(prize1 == null||Double.parseDouble(prize1.getGrade())>Double.parseDouble(prize2.getGrade())){
                        prize1 = prize2;
                    }
                    if(Double.parseDouble(prize1.getGrade())==Double.parseDouble(prize2.getGrade())){
                        if(new Random().nextInt(2)==0){
                            prize1 = prize2;
                        }
                    }
                }
                if(prize1 == null){
                    //没中
                    if(StringUtils.isNotBlank(gameId)&&StringUtils.isNotBlank(recordId)){
                        if("2".equals(gameId)) {
                            // 用户游戏2的相关信息
                            GameTwoMode gameTwoMode = refreshGameTwo(request);
                            T2GameRecordMode t2GameRecordMode = new T2GameRecordMode();
                            t2GameRecordMode.setId(recordId);
                            t2GameRecordMode.setType("2");
                            gameTwoService.updateUserGameTwoRecord(t2GameRecordMode);
                            gameTwoService.updateUserGameEnergyNum(userbean, Integer.parseInt(gameTwoMode.getEnergyNum()) == 5 ? 5 : Integer.parseInt(gameTwoMode.getEnergyNum()) + 1);
                        }
                    }
                    jsonObject.put("result",true);
                    jsonObject.put("prize",false);
                }else{
                    //保存奖品中奖纪录
                    PrizeRedeem prizeRedeem1 = new PrizeRedeem();
                    prizeRedeem1.setUserId(userbean.getID());
                    prizeRedeem1.setPrizeId(prize1.getId());
                    if("0".equals(prize1.getType())){
                        //0 代表加油卡
                        prizeRedeem1.setRedeemCode(RandomTool.getRandomString(10));
                    }else if("1".equals(prize1.getType())){
                        //1 代表油滴
                        prizeRedeem1.setRedeemCode(RandomTool.getRandomStringByType("01"));
                    }
                    int refId = iPrizeRedeemService.savePrizeRedeem(prizeRedeem1);
                    //更新暴击
                    gameTwoService.updateUserGameEnergyNum(userbean,0);
                    //保存游戏二中奖记录
                    if(StringUtils.isNotBlank(gameId)&&StringUtils.isNotBlank(recordId)){
                        if("2".equals(gameId)) {
                            T2GameRecordMode t2GameRecordMode = new T2GameRecordMode();
                            t2GameRecordMode.setId(recordId);
                            t2GameRecordMode.setType("0");
                            t2GameRecordMode.setRefId(String.valueOf(refId));
                            gameTwoService.updateUserGameTwoRecord(t2GameRecordMode);
                        }
                    }

                    PrizePool usedPool = new PrizePool();
                    usedPool.setUserId(userbean.getID());
                    usedPool.setUsedPool(prize1.getGrade());
                    iPrizePoolService.updatePrizePool(usedPool);

                    PrizePool AllPrizePool = new PrizePool();
                    AllPrizePool.setId("1");
                    AllPrizePool.setUsedPool(prize1.getGrade());
                    iPrizePoolService.updatePrizePool(AllPrizePool);

                    jsonObject.put("result",true);
                    jsonObject.put("prize",true);
                    jsonObject.put("prizeName",prize1.getPrizeName());
                }
            }else{
                //没中
                if(StringUtils.isNotBlank(gameId)&&StringUtils.isNotBlank(recordId)){
                    if("2".equals(gameId)) {
                        // 用户游戏2的相关信息
                        GameTwoMode gameTwoMode = refreshGameTwo(request);
                        T2GameRecordMode t2GameRecordMode = new T2GameRecordMode();
                        t2GameRecordMode.setId(recordId);
                        t2GameRecordMode.setType("2");
                        gameTwoService.updateUserGameTwoRecord(t2GameRecordMode);
                        gameTwoService.updateUserGameEnergyNum(userbean, Integer.parseInt(gameTwoMode.getEnergyNum()) == 5 ? 5 : Integer.parseInt(gameTwoMode.getEnergyNum()) + 1);
                    }
                }
                jsonObject.put("result",true);
                jsonObject.put("prize",false);
            }
        }else{
            //没中
            if(StringUtils.isNotBlank(gameId)&&StringUtils.isNotBlank(recordId)){
                if("2".equals(gameId)) {
                    // 用户游戏2的相关信息
                    GameTwoMode gameTwoMode = refreshGameTwo(request);
                    T2GameRecordMode t2GameRecordMode = new T2GameRecordMode();
                    t2GameRecordMode.setId(recordId);
                    t2GameRecordMode.setType("2");
                    gameTwoService.updateUserGameTwoRecord(t2GameRecordMode);
                    gameTwoService.updateUserGameEnergyNum(userbean, Integer.parseInt(gameTwoMode.getEnergyNum()) == 5 ? 5 : Integer.parseInt(gameTwoMode.getEnergyNum()) + 1);
                }
            }
            jsonObject.put("result",true);
            jsonObject.put("prize",false);
        }
        if((boolean)jsonObject.get("prize")){
            String mobile = PhoneUtil.phoneSecrecy(userbean.getMOBILE());
            String prizeName = jsonObject.get("prizeName").toString();
            WebSocket webSocket = new WebSocket();
            webSocket.sendMsg("恭喜 "+mobile+" 获得"+prizeName);
        }
        return JSON.toJSONString(jsonObject);
    }

    //用户某件奖品是否中奖
    @RequestMapping(value = "/singlePrize")
    @ResponseBody
    public String  singlePrize(HttpServletRequest request , HttpServletResponse response , HttpSession session){
        //设置方法返回
        JSONObject returnObj = new JSONObject();
        String gameId = request.getParameter("gameId");
        String recordId = request.getParameter("recordId");
        String prizeId = request.getParameter("prizeId");
        String numStr = request.getParameter("num");
        if(StringUtils.isBlank(gameId)||StringUtils.isBlank(recordId)||StringUtils.isBlank(prizeId)||StringUtils.isBlank(numStr)){
            returnObj.put("result",false);
            returnObj.put("message","参数错误");
            return JSON.toJSONString(returnObj);
        }
        int num = Integer.parseInt(numStr);
        Prize prize = new Prize();
        prize.setId(prizeId);
        List<Prize> prizes = iPrizeService.listPrize(prize);
        if(prizes.size()==0){
            returnObj.put("result",false);
            returnObj.put("message","查无数据");
            return JSON.toJSONString(returnObj);
        }
        Prize prize1 = prizes.get(0);
        //得到商品的价值
        double grade = Double.parseDouble(prize1.getGrade())*num;
        //这次用个人奖金池尝试
        MallUserMode userbean = refreshtWxLoginUser(request);
        PrizePool prizePool = new PrizePool();
        prizePool.setUserId(userbean.getID());
        List<PrizePool> prizePools = iPrizePoolService.list(prizePool);
        prizePool = prizePools.get(0);
        double canGet = Double.parseDouble(prizePool.getPrizePool().trim())-Double.parseDouble(prizePool.getUsedPool().trim());
        if(canGet>=grade){
            //可以中奖 百分之30几率中奖
            Random random = new Random();
            int r = random.nextInt(100);
            if(r<30) {
                for(int i=0;i<num;i++){
                    //保存奖品中奖纪录
                    PrizeRedeem prizeRedeem1 = new PrizeRedeem();
                    prizeRedeem1.setUserId(userbean.getID());
                    prizeRedeem1.setPrizeId(prize1.getId());
                    if("0".equals(prize1.getType())){
                        //0 代表加油卡
                        prizeRedeem1.setRedeemCode(RandomTool.getRandomString(10));
                    }else if("1".equals(prize1.getType())){
                        //1 代表油滴
                        prizeRedeem1.setRedeemCode(RandomTool.getRandomStringByType("01"));
                    }
                    iPrizeRedeemService.savePrizeRedeem(prizeRedeem1);
                }

                PrizePool usedPool = new PrizePool();
                usedPool.setUserId(userbean.getID());
                usedPool.setUsedPool(prize1.getGrade());
                iPrizePoolService.updatePrizePool(usedPool);

                PrizePool AllPrizePool = new PrizePool();
                AllPrizePool.setId("1");
                AllPrizePool.setUsedPool(String.valueOf(Double.parseDouble(prize1.getGrade())*num));
                iPrizePoolService.updatePrizePool(AllPrizePool);

                returnObj.put("result",true);
                returnObj.put("prize",true);
                returnObj.put("prizeName",prize1.getPrizeName());
                return JSON.toJSONString(returnObj);
            }else{
                returnObj.put("result",true);
                returnObj.put("prize",false);
                return JSON.toJSONString(returnObj);
            }
        }else if(Double.parseDouble(prizePool.getPrizePool().trim())>0){
            //有百分之3的几率中奖
            Random random = new Random();
            int r = random.nextInt(100);
            if(r<3){
                for(int i=0;i<num;i++){
                    //保存奖品中奖纪录
                    PrizeRedeem prizeRedeem1 = new PrizeRedeem();
                    prizeRedeem1.setUserId(userbean.getID());
                    prizeRedeem1.setPrizeId(prize1.getId());
                    if("0".equals(prize1.getType())){
                        //0 代表加油卡
                        prizeRedeem1.setRedeemCode(RandomTool.getRandomString(10));
                    }else if("1".equals(prize1.getType())){
                        //1 代表油滴
                        prizeRedeem1.setRedeemCode(RandomTool.getRandomStringByType("01"));
                    }
                    iPrizeRedeemService.savePrizeRedeem(prizeRedeem1);
                }

                PrizePool usedPool = new PrizePool();
                usedPool.setUserId(userbean.getID());
                usedPool.setUsedPool(prize1.getGrade());
                iPrizePoolService.updatePrizePool(usedPool);

                PrizePool AllPrizePool = new PrizePool();
                AllPrizePool.setId("1");
                AllPrizePool.setUsedPool(String.valueOf(Double.parseDouble(prize1.getGrade())*num));
                iPrizePoolService.updatePrizePool(AllPrizePool);

                returnObj.put("result",true);
                returnObj.put("prize",true);
                returnObj.put("prizeName",prize1.getPrizeName());
                return JSON.toJSONString(returnObj);
            }else{
                returnObj.put("result",true);
                returnObj.put("prize",false);
                return JSON.toJSONString(returnObj);
            }
        }else{
            returnObj.put("result",true);
            returnObj.put("prize",false);
            return JSON.toJSONString(returnObj);
        }
    }

    //兑换红包跳转中石化
    @RequestMapping(value = "/exchangeRed")
    public ModelAndView exchangeRed(HttpServletRequest request , HttpServletResponse response , HttpSession session) throws Exception{
        MallUserMode userbean = refreshtWxLoginUser(request);
        String prizeId = request.getParameter("prizeId");
        String prizeRedeemId = request.getParameter("prizeRedeemId");
        String imgUrl = request.getParameter("imgUrl");
        String redeemCode = request.getParameter("redeemCode");
        Prize prize = new Prize();
        prize.setId(prizeId);
        Prize prize1 = iPrizeService.listPrize(prize).get(0);
        PrizeRedeem prizeRedeem = new PrizeRedeem();
        prizeRedeem.setUserId(userbean.getID());
        prizeRedeem.setId(prizeRedeemId);
        prizeRedeem.setStatus("0");
        prizeRedeem.setEndTime("123");
        List<PrizeRedeem> prizeRedeemList = iPrizeRedeemService.list(prizeRedeem);
        if(prizeRedeemList.size()==1&& StringUtils.isBlank(prizeRedeemList.get(0).getRedpkgId())){
            String domain_name= PropertiesUtil.get("DOMAIN.WEB_SERVER");
            String redirectUrl=domain_name+"/sinopecGameCt/weixinMng/getPrize/addRed.htm?prizeId="+prizeId+"&prizeRedeemId="+prizeRedeemId+"&imgUrl="+imgUrl+"&redeemCode="+redeemCode+"&openId="+userbean.getOPEN_ID();
            _LOGGER.info("回调链接地址为"+redirectUrl);
            response.sendRedirect(response.encodeRedirectURL(String.format(getZshOpenIdUrl,URLEncoder.encode(redirectUrl))));
            _LOGGER.info("跳转地址为"+String.format(getZshOpenIdUrl,redirectUrl));
        }else{
            ModelAndView view = new ModelAndView("/jsp/weixinMng/mallMng/exchangePrize.jsp");
            view.addObject("prizeName",prize1.getPrizeName());
            view.addObject("prizeId",prizeId);
            view.addObject("prizeRedeemId",prizeRedeemId);
            view.addObject("imgUrl",imgUrl);
            view.addObject("redeemCode",redeemCode);
            view.addObject("winningTime",prizeRedeemList.get(0).getWinningTime());
            view.addObject("endTime",prizeRedeemList.get(0).getEndTime());
            return view;
        }
        return null;
    }

    //增加红包
    @RequestMapping(value = "/addRed")
    public ModelAndView addRed(HttpServletRequest request , HttpServletResponse response , HttpSession session) throws Exception{
        //中石化openId
        String userOpenId = request.getParameter("userOpenid");
        _LOGGER.info("获取到中石化openid为"+userOpenId);
        String prizeId = request.getParameter("prizeId");
        _LOGGER.info("获取到prizeId为"+prizeId);
        String prizeRedeemId = request.getParameter("prizeRedeemId");
        _LOGGER.info("获取到prizeRedeemId为"+prizeRedeemId);
        String imgUrl = request.getParameter("imgUrl");
        _LOGGER.info("获取到imgUrl为"+imgUrl);
        String redeemCode = request.getParameter("redeemCode");
        _LOGGER.info("获取到redeemCode为"+redeemCode);
        String openId = request.getParameter("openId");
        _LOGGER.info("获取到openId为"+openId);
        if(StringUtils.isNotBlank(userOpenId)&&StringUtils.isNotBlank(prizeId)&&StringUtils.isNotBlank(prizeRedeemId)){
            PrizeRedeem prizeRedeem = new PrizeRedeem();
            prizeRedeem.setId(prizeRedeemId);
            if(StringUtils.isBlank(iPrizeRedeemService.list(prizeRedeem).get(0).getRedpkgId())){
                _LOGGER.info("用户红包Id为空——————————");
                MallUserMode userinfo = manageService.queryMallUserInfo(openId);
                String redpkgResult = "";
                if("1".equals(prizeId)||prizeId == "1"){
                    _LOGGER.info("红包接口地址为"+addRedUrl + "?openId=" + userOpenId + "&channelId=0002&userId=" + userinfo.getTHIRD_PART_ID());
                    redpkgResult = PostOrGet.sendGet(addRedUrl + "?openId=" + userOpenId + "&channelId=0002&userId=" + userinfo.getTHIRD_PART_ID());
                }else if("2".equals(prizeId)||prizeId == "2"){
                    _LOGGER.info("红包接口地址为"+addRedUrl + "?openId=" + userOpenId + "&channelId=0002&userId=" + userinfo.getTHIRD_PART_ID());
                    redpkgResult = PostOrGet.sendGet(addRedUrl + "?openId=" + userOpenId + "&channelId=0001&userId=" + userinfo.getTHIRD_PART_ID());
                }
                _LOGGER.info("获取到redpkgResult为"+redpkgResult);
                JSONObject redResult = (JSONObject) JSONObject.parse(redpkgResult);
                if("0".equals(redResult.getString("state"))){
                    String redpkgId = redResult.getString("redpkgId");
                    PrizeRedeem prizeRedeem1 = new PrizeRedeem();
                    prizeRedeem1.setId(prizeRedeemId);
                    prizeRedeem1.setRedpkgId(redpkgId);
                    prizeRedeem1.setOilOpenId(userOpenId);
                    iPrizeRedeemService.updatePrizeRedeem(prizeRedeem1);
                }
            }
            ModelAndView view = new ModelAndView("/jsp/weixinMng/mallMng/exchangePrize.jsp");
            Prize prize = new Prize();
            prize.setId(prizeId);
            Prize prize1 = iPrizeService.listPrize(prize).get(0);
            List<PrizeRedeem> prizeRedeemList = iPrizeRedeemService.list(prizeRedeem);
            view.addObject("prizeName",prize1.getPrizeName());
            view.addObject("prizeId",prizeId);
            view.addObject("prizeRedeemId",prizeRedeemId);
            view.addObject("imgUrl",imgUrl);
            view.addObject("redeemCode",redeemCode);
            view.addObject("winningTime",prizeRedeemList.get(0).getWinningTime());
            view.addObject("endTime",prizeRedeemList.get(0).getEndTime());
            return view;
        }else{
            return null;
        }
    }

    //激活红包
    @RequestMapping(value = "/activateCode")
    @ResponseBody
    public String activateCode(HttpServletRequest request , HttpServletResponse response , HttpSession session) throws Exception{
        JSONObject jsonObject = new JSONObject();

        if(session.getAttribute("activateCode") == null){
            session.setAttribute("activateCode", new Date());
        }else {
            Date diamondsPrizeTime = (Date) session.getAttribute("activateCode");
            Date currentDate = new Date();
            int interval = (int) (currentDate.getTime() - diamondsPrizeTime.getTime()) / 1000;
            session.setAttribute("diamondsPrizeTime", new Date());
            if (interval < 5) {
                jsonObject.put("result",false);
                jsonObject.put("message","接口频率调用次数过多请重试");
                return JSON.toJSONString(jsonObject);
            }
        }
        MallUserMode userbean = refreshtWxLoginUser(request);
        String redeemCode = request.getParameter("redeemCode");
        //从现在开始对兑换码进行校验
        PrizeRedeem prizeRedeem = new PrizeRedeem();
        prizeRedeem.setRedeemCode(redeemCode);
        prizeRedeem.setUserId(userbean.getID());
        prizeRedeem.setStatus("0");
        List<PrizeRedeem> prizeRedeemList = iPrizeRedeemService.list(prizeRedeem);
        if(prizeRedeemList.size() == 1){
            PrizeRedeem activatePrizeRedeem = prizeRedeemList.get(0);
            if(userbean.getID().equals(activatePrizeRedeem.getUserId())){
                //从这开始
                if(activatePrizeRedeem.getRedeemCode().length()==10){
                    if(StringUtils.isNotBlank(activatePrizeRedeem.getRedpkgId())){
                        String uptUrl = updateRedUrl + "ByThird?redpkgId=" + activatePrizeRedeem.getRedpkgId() + "&openId=" + activatePrizeRedeem.getOilOpenId() + "&phone=" + userbean.getMOBILE() + "&state=1&reState=4";
                        _LOGGER.info("uptUrl"+uptUrl);
                        String redpkgResult = PostOrGet.sendGet(uptUrl);
                        _LOGGER.info("redpkgResult"+redpkgResult);
                        JSONObject redResult = (JSONObject) JSONObject.parse(redpkgResult);
                        if("0".equals(redResult.getString("state"))){
                            PrizeRedeem prizeRedeem1 = new PrizeRedeem();
                            prizeRedeem1.setId(activatePrizeRedeem.getId());
                            prizeRedeem1.setStatus("1");
                            prizeRedeem1.setReceiveTime("123");
                            iPrizeRedeemService.updatePrizeRedeem(prizeRedeem1);
                            jsonObject.put("result",true);
                            jsonObject.put("type",0);
                            jsonObject.put("message","激活成功");
                        }else{
                            jsonObject.put("result",false);
                            jsonObject.put("message","激活失败");
                        }
                    }else{
                        jsonObject.put("result",false);
                        jsonObject.put("message","红包信息为空");
                    }
                }else if(activatePrizeRedeem.getRedeemCode().startsWith("01")){
                    String uptUrl = exchange_oil_url;
                    _LOGGER.info("uptUrl"+uptUrl);
                    Map<String, String> paramMap = new HashMap<String, String>();
                    paramMap.put("openId",userbean.getOPEN_ID());
                    paramMap.put("oilNum","100");
                    String oilDropResult = HttpClientUtil.doHttpsPost(uptUrl,paramMap);
                    _LOGGER.info("--------------------oilDropResult"+oilDropResult);
                    JSONObject oilDropResultObj = (JSONObject) JSONObject.parse(oilDropResult);
                    if("1".equals(oilDropResultObj.getString("code"))){
                        _LOGGER.info("状态码为1");
                        PrizeRedeem prizeRedeem1 = new PrizeRedeem();
                        prizeRedeem1.setId(activatePrizeRedeem.getId());
                        prizeRedeem1.setStatus("1");
                        prizeRedeem1.setReceiveTime("123");
                        iPrizeRedeemService.updatePrizeRedeem(prizeRedeem1);
                        jsonObject.put("result",true);
                        jsonObject.put("type",1);
                        jsonObject.put("message","激活成功");
                    }else{
                        jsonObject.put("result",false);
                        jsonObject.put("message","激活失败");
                    }
                }
            }else{
                jsonObject.put("result",false);
                jsonObject.put("message","用户身份不匹配");
            }
        }else{
            jsonObject.put("result",false);
            jsonObject.put("message","查无此奖品信息");
        }
        return JSON.toJSONString(jsonObject);
    }

    // 用户进入兑换
    @RequestMapping(value = "/prizeIn", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView userIn(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
        // 取得用户登陆信息
        MallUserMode userbean = refreshtWxLoginUser(request);
        ModelAndView view = new ModelAndView("/jsp/weixinMng/mallMng/exchange.jsp");
        return view;
    }

    //兑换油滴跳转
    @RequestMapping(value = "/exchangeOilDrop")
    public ModelAndView exchangeOilDrop(HttpServletRequest request , HttpServletResponse response , HttpSession session) throws Exception{
        MallUserMode userbean = refreshtWxLoginUser(request);
        String prizeId = request.getParameter("prizeId");
        String prizeRedeemId = request.getParameter("prizeRedeemId");
        String imgUrl = request.getParameter("imgUrl");
        String redeemCode = request.getParameter("redeemCode");
        Prize prize = new Prize();
        prize.setId(prizeId);
        Prize prize1 = iPrizeService.listPrize(prize).get(0);
        PrizeRedeem prizeRedeem = new PrizeRedeem();
        prizeRedeem.setUserId(userbean.getID());
        prizeRedeem.setId(prizeRedeemId);
        prizeRedeem.setStatus("0");
        prizeRedeem.setEndTime("123");
        List<PrizeRedeem> prizeRedeemList = iPrizeRedeemService.list(prizeRedeem);
        if(prizeRedeemList.size()==1){
            ModelAndView view = new ModelAndView("/jsp/weixinMng/mallMng/exchangePrize.jsp");
            view.addObject("prizeName",prize1.getPrizeName());
            view.addObject("prizeId",prizeId);
            view.addObject("prizeRedeemId",prizeRedeemId);
            view.addObject("imgUrl",imgUrl);
            view.addObject("redeemCode",redeemCode);
            view.addObject("winningTime",prizeRedeemList.get(0).getWinningTime());
            view.addObject("endTime",prizeRedeemList.get(0).getEndTime());
            return view;
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