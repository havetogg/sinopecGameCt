package org.jumutang.project.weixinMng.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.jumutang.project.weixinMng.api.dao.IApiDao;
import org.jumutang.project.weixinMng.api.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 14:56 2017/8/1
 * @Modified By:
 */
@Service
public class ApiServiceImpl implements IApiService{

    @Autowired
    private IApiDao apiDao;

    @Override
    public JSONObject queryAllUserApi() {
        List<JSONObject> userList= apiDao.queryAllUserApi();
        JSONObject total = apiDao.queryTotalApi();
        JSONObject today = apiDao.queryTodayApi();
        JSONObject returnObj = new JSONObject();
        returnObj.put("userList",userList);
        returnObj.put("total",total);
        returnObj.put("today",today);
        return returnObj;
    }
}
