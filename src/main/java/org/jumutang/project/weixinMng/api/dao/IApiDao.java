package org.jumutang.project.weixinMng.api.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 15:01 2017/8/1
 * @Modified By:
 */
public interface IApiDao {

    List<JSONObject> queryAllUserApi();
}
