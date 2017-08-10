package org.jumutang.project.weixinMng.api.controller;

import com.alibaba.fastjson.JSON;
import org.jumutang.project.base.BaseController;
import org.jumutang.project.weixinMng.api.service.IApiService;
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

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 13:48 2017/8/1
 * @Modified By:
 */
@Controller
@RequestMapping(value = "/weixinMng/api", method = { RequestMethod.GET, RequestMethod.POST })
public class ApiController extends BaseController {

    @Autowired
    private IApiService apiService;


    //获取所有用户信息
    @RequestMapping(value = "/getAllUser", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String getAllUser(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        return JSON.toJSONString(apiService.queryAllUserApi());
    }
}
