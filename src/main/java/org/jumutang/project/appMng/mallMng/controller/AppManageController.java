package org.jumutang.project.appMng.mallMng.controller;

import com.alibaba.fastjson.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jumutang.basicbox.model.JSONResultModel;
import org.jumutang.project.appMng.mallMng.model.AppModel;
import org.jumutang.project.appMng.mallMng.service.IAppService;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.tools.*;
import org.jumutang.project.weixinMng.mallMng.model.GameMngMode;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.service.IGameMngService;
import org.jumutang.project.weixinMng.mallMng.service.IManageService;
import org.jumutang.project.weixinMng.mallMng.service.ISysMsgService;
import org.jumutang.project.weixinMng.prizeMng.model.PrizePool;
import org.jumutang.project.weixinMng.prizeMng.model.PrizeRedeem;
import org.jumutang.project.weixinMng.prizeMng.service.IPrizePoolService;
import org.jumutang.project.weixinMng.prizeMng.service.IPrizeRedeemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 9:35 2017/8/8
 * @Modified By:
 */
@Controller
@RequestMapping(value = "/appMng/ManageC", method = { RequestMethod.GET, RequestMethod.POST })
public class AppManageController extends BaseController {

    private static final Logger _LOGGER = Logger.getLogger(AppManageController.class);

    @Autowired
    private IAppService appService;

    @Autowired
    private IManageService manageService;

    @Autowired
    private ISysMsgService sysMsgService;

    @Autowired
    private IPrizeRedeemService iPrizeRedeemService;

    @Autowired
    private IGameMngService gameMngService;

    @Autowired
    private IPrizePoolService iPrizePoolService;

    private static final String APP_KEY ="1e22953212ed4c553w37r2e6saf2e121";
    private static final String AES_KEY ="ws57x644g34k5e56";

    //app端登录
    @RequestMapping(value = "/userIn", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView appLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        ModelAndView view = null;
        request.getSession().setAttribute("enterType","app");
        String userCode = getUserCode(request);
        if(userCode==null){
            String path = request.getContextPath();
            String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
            view = new ModelAndView(new RedirectView(basePath+"/jsp/appMng/mallMng/jump.html"));
            return view;
        }
        AppModel appModel = new AppModel();
        appModel.setUserCode(userCode);
        appModel = appService.saveApp(appModel);
        this.setAppUser(request,appModel);
        if(StringUtils.isBlank(appModel.getMobile())){
            view = new ModelAndView("/jsp/appMng/mallMng/index.jsp");

            view.addObject("appModel",appModel);

            Map<String,String> queryParam = new HashMap<>();
            List<GameMngMode> gameMngModeList = gameMngService.findList(queryParam);
            view.addObject("GAMEMNGMODELIST", gameMngModeList);

            //查询最近的中奖结果用户信息
            JSONArray prizeJSONArray = iPrizeRedeemService.prizeList();
            view.addObject("prizeJSONArray", prizeJSONArray);

            return view;
        }else{
            String mobile = appModel.getMobile();
            MallUserMode userbean = manageService.queryMallUserInfoByMobile(mobile);
            if(userbean == null){
                //没有绑定
                view = new ModelAndView("/jsp/appMng/mallMng/index.jsp");

                view.addObject("appModel",appModel);

                Map<String,String> queryParam = new HashMap<>();
                List<GameMngMode> gameMngModeList = gameMngService.findList(queryParam);
                view.addObject("GAMEMNGMODELIST", gameMngModeList);

                //查询最近的中奖结果用户信息
                JSONArray prizeJSONArray = iPrizeRedeemService.prizeList();
                view.addObject("prizeJSONArray", prizeJSONArray);

                return view;
            }else{
                super.setWxLoginUser(request, userbean);
                int notReadSize = sysMsgService.findNotReadCount(userbean.getID());
                view = new ModelAndView("/jsp/weixinMng/mallMng/index.jsp");
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
        }
    }


    private String getUserCode(HttpServletRequest request){
        String jfuserid = request.getParameter("jfuserid");
        String timestamp = request.getParameter("timestamp");
        String usercode = request.getParameter("usercode");
        String appsign =request.getParameter("appsign");
        if(StringUtils.isNotBlank(jfuserid)&&StringUtils.isNotBlank(timestamp)&&StringUtils.isNotBlank(usercode)&&StringUtils.isNotBlank(appsign)){
            jfuserid = URLEncoder.encode(jfuserid);
            timestamp = URLEncoder.encode(timestamp);
            usercode = URLEncoder.encode(usercode);
            appsign = URLEncoder.encode(appsign);
            Map<String,String> sortMap = new HashMap<>();
            sortMap.put("jfuserid",jfuserid);
            sortMap.put("usercode",usercode);
            sortMap.put("timestamp",timestamp);
            String comSign = AESUtils.toSignString(sortMap,APP_KEY);
            if(comSign.equals(appsign)){
                try{
                    return AESUtils.aesDecryptByBytes(URLDecoder.decode(usercode), AES_KEY);
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
                return null;
            }else{
                return null;
            }
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
            if(manageService.queryMallUserInfoByMobile(tel)==null){
                jsonResultModel.setCode(0).setMsg("该手机号未在有礼付go公众号中注册,请到有礼付go公众号注册后再绑定!");
                return JSONObject.fromObject(jsonResultModel).toString();
            }
            String randomCode = randomdate.substring(randomdate.length() - numb, randomdate.length());
            PhoneMsgSendUtil phonemsgsendutil = new PhoneMsgSendUtil();
            JSONObject sendMessage = phonemsgsendutil.sendMessage(tel, randomCode);
            if (!"1".equals(sendMessage.getString("code"))) {
                jsonResultModel.setCode(0).setMsg("验证码取得失败！");
                return JSONObject.fromObject(jsonResultModel).toString();
            }
            request.getSession().setAttribute("appRegistCode",randomCode);
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
        AppModel appModel =	refreshtAppUser(request);// 取得最新的用户信息
        try {
            String MOBILE = getStr(request, "tel");
            // 验证码
            String code = getStr(request, "code");

            // 查询验证码
            String appRegistCode =  (String)request.getSession().getAttribute("appRegistCode");
            if(appRegistCode.equals(code)){
                if(manageService.queryMallUserInfoByMobile(MOBILE)==null){
                    jsonResultModel.setCode(0).setMsg("该手机号未在有礼付go公众号中注册,请到有礼付go公众号注册后再绑定!");
                    return JSONObject.fromObject(jsonResultModel).toString();
                }else{
                    AppModel queryAppModel = new AppModel();
                    queryAppModel.setMobile(MOBILE);
                    for(AppModel clearMobileModel:appService.listApp(queryAppModel)){
                        clearMobileModel.setMobile("");
                        appService.updateApp(clearMobileModel);
                    }
                    appModel.setMobile(MOBILE);
                    appService.updateApp(appModel);
                    super.setWxLoginUser(request, manageService.queryMallUserInfoByMobile(MOBILE));
                    jsonResultModel.setCode(1).setMsg("登录成功!");
                    jsonResultModel.setResultObject(resultmap);
                    return JSONObject.fromObject(jsonResultModel).toString();
                }
            }else{
                jsonResultModel.setCode(0).setMsg("验证码错误!");
                return JSONObject.fromObject(jsonResultModel).toString();
            }
        } catch (Exception e) {
            _LOGGER.info(e.getMessage());
            jsonResultModel.setCode(0).setMsg("异常!");
            jsonResultModel.setResultObject(resultmap);
            return JSONObject.fromObject(jsonResultModel).toString();
        }
    }

    private AppModel refreshtAppUser(HttpServletRequest request) {
        AppModel appModel = super.getAppUser(request);
        appModel = appService.saveApp(appModel);
        super.setAppUser(request, appModel);
        return appModel;
    }
}
