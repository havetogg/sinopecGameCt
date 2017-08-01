package org.jumutang.project.weixinMng.gameThree.controller;

import com.alibaba.fastjson.JSONArray;
import org.apache.log4j.Logger;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.weixinMng.gameTwo.controller.GameTwoController;
import org.jumutang.project.weixinMng.gameTwo.model.GameTwoMode;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.jumutang.project.weixinMng.mallMng.service.IManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

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

    @Autowired
    private IManageService manageService;

    // 用户进入游戏首页
    @RequestMapping(value = "/gameTwoIn", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView userIn(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
        // 取得用户登陆信息
        MallUserMode userbean = refreshtWxLoginUser(request);
        // 初始化游戏
        ModelAndView view = new ModelAndView("/jsp/weixinMng/gameTwo/index.jsp");
        view.addObject("USERBEAN", userbean);
        return view;
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
