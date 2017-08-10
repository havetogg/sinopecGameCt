package org.jumutang.project.weixinMng.activity.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.tools.DateUtil;
import org.jumutang.project.tools.RandomTool;
import org.jumutang.project.weixinMng.api.service.IApiService;
import org.jumutang.project.weixinMng.mallMng.controller.ManageController;
import org.jumutang.project.weixinMng.mallMng.dao.ISysMsgDao;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.model.RewardMode;
import org.jumutang.project.weixinMng.mallMng.model.SysMsgMode;
import org.jumutang.project.weixinMng.mallMng.service.IManageService;
import org.jumutang.project.weixinMng.mallMng.service.IRewardModeService;
import org.jumutang.project.weixinMng.mallMng.service.ISysMsgService;
import org.jumutang.project.weixinMng.prizeMng.model.PrizeRedeem;
import org.jumutang.project.weixinMng.prizeMng.service.IPrizeRedeemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 14:18 2017/8/3
 * @Modified By:
 */
@Controller
@RequestMapping(value = "/weixinMng/activity", method = { RequestMethod.GET, RequestMethod.POST })
public class ActivityController extends BaseController{

    private static final Logger _LOGGER = Logger.getLogger(ActivityController.class);

    @Autowired
    private IManageService manageService;

    @Autowired
    private IRewardModeService rewardModeService;

    @Autowired
    private IPrizeRedeemService iPrizeRedeemService;

    @Autowired
    private ISysMsgService sysMsgService;


    //获取所有用户信息
    @RequestMapping(value = "/getOilDropReward", method = { RequestMethod.GET, RequestMethod.POST })
    public String getOilDropReward(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        MallUserMode userMode = refreshtWxLoginUser(request);
        //1.查询有没有reward
        RewardMode rewardMode = new RewardMode();
        rewardMode.setUserId(userMode.getID());
        rewardMode.setRewardType("2");
        if(rewardModeService.list(rewardMode).size()==0){
            //增加奖品
            PrizeRedeem prizeRedeem1 = new PrizeRedeem();
            prizeRedeem1.setUserId(userMode.getID());
            prizeRedeem1.setPrizeId("3");
            prizeRedeem1.setRedeemCode(RandomTool.getRandomStringByType("01"));
            iPrizeRedeemService.savePrizeRedeem(prizeRedeem1);

            //增加reward
            rewardMode.setRewardName("油滴奖励");
            rewardModeService.saveRewardMode(rewardMode);

            //增加消息
            // 调用msg
            SysMsgMode sysMsgMode_bean = new SysMsgMode();
            sysMsgMode_bean.setUSER_ID(userMode.getID());
            sysMsgMode_bean.setMSG_TITLE("油滴奖励");
            sysMsgMode_bean.setMSG_DETAIL("油滴奖励");
            sysMsgMode_bean.setTYPE("-1");   //1:钻石 2:金币 3:两都都有 4:奖品
            sysMsgMode_bean.setDIAMOND("0");
            sysMsgMode_bean.setGOLD("0");
            sysMsgMode_bean.setGET_TIME(DateUtil.get4yMdHms(new Date()));  //领取时间
            sysMsgService.saveInfo(sysMsgMode_bean);
        }

        return "redirect:/weixinMng/ManageC/prizeList.htm";
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
