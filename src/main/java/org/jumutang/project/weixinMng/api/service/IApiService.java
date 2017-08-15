package org.jumutang.project.weixinMng.api.service;


import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description: api接口返回所需要的数据
 * @Date: Create in 14:53 2017/8/1
 * @Modified By:
 */
public interface IApiService {

    //所有用户信息 openid mobile prize_pool used_pool
    JSONObject queryAllUserApi();
}
