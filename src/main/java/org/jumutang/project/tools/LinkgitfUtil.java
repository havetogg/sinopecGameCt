package org.jumutang.project.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;


/**
 * 
 * 用户的userid,兑换接口
 * @author 
 * @date 2015-12-11
 */
public class LinkgitfUtil {
	private static final Logger _LOGGER = Logger.getLogger(LinkgitfUtil.class);
	// 取得用户的userid 15751349530 o4FD4v0zLYohDPtlt5E7xorezV4w
	private String geturl ="http://www.linkgift.cn/giftpay_wap/userCenter/getAllUser.htm?openId=%s&phone=%s";
	// 创建平台用户
	private String creaturl ="http://www.linkgift.cn/giftpay_wap/userCenter/addAllUser.htm?openId=%s&phone=%s&type=%s";
	// 创建红包
	private String creatredpurl ="http://www.linkgift.cn/giftcenter/saveFuliCenterRedPkg?openId=%s&channelId=%s&userId=%s";
	
	/**
	 * 取得用户的第三方userid
	 * @param openid
	 * @param phone
	 * @return
	 */
	public HashMap<String,String> getUseridByOpenidAndPhone(String openid, String phone){
		HashMap<String,String> resultmap = new HashMap<String,String>();
		try {
			String userid = getUserid(openid,phone);
			if(StringUtil.isEmpty(userid)){
				//添加用户
				JSONObject creatMainUser = CreatMainUser(openid,phone,"2");
				String code = creatMainUser.getString("code");
				if("20000".equals(code) || "20001".equals(code)){
					//添加用户出错				
					resultmap.put("RESULT", "0");
					resultmap.put("USERID", "");
					resultmap.put("RESULT_MSG", "查询失败!");
				}else{
					userid = getUserid(openid,phone);
					if(StringUtil.isEmpty(userid)){
						resultmap.put("RESULT", "0");
						resultmap.put("USERID", "");
						resultmap.put("RESULT_MSG", "查询失败!");
					}else{
						resultmap.put("RESULT", "1");
						resultmap.put("USERID", userid);
						resultmap.put("RESULT_MSG", "查询成功!");
					}
				}
				
			}else{
				resultmap.put("RESULT", "1");
				resultmap.put("USERID", userid);
				resultmap.put("RESULT_MSG", "查询成功!");
			}
			
		} catch (IOException e) {
			_LOGGER.info(e.getMessage());
			resultmap.put("RESULT", "0");
			resultmap.put("USERID", "");
			resultmap.put("RESULT_MSG", "查询失败!");
		}
		return resultmap;
	}
	
	/**
	 * 取得用户的第三方userid
	 * @param openid
	 * @param phone
	 * @return
	 */
	public HashMap<String,String> getUseridByOpenidAndPhone3(String openid, String phone){
		HashMap<String,String> resultmap = new HashMap<String,String>();
		try {
			String userid = getUserid(openid,phone);
			if(StringUtil.isEmpty(userid)){
				//添加用户
				JSONObject creatMainUser = CreatMainUser(openid,phone,"3");
				String code = creatMainUser.getString("code");
				if("20000".equals(code) || "20001".equals(code)){
					//添加用户出错				
					resultmap.put("RESULT", "0");
					resultmap.put("USERID", "");
					resultmap.put("RESULT_MSG", "查询失败!");
				}else{
					userid = getUserid(openid,phone);
					if(StringUtil.isEmpty(userid)){
						resultmap.put("RESULT", "0");
						resultmap.put("USERID", "");
						resultmap.put("RESULT_MSG", "查询失败!");
					}else{
						resultmap.put("RESULT", "1");
						resultmap.put("USERID", userid);
						resultmap.put("RESULT_MSG", "查询成功!");
					}
				}
				
			}else{
				resultmap.put("RESULT", "1");
				resultmap.put("USERID", userid);
				resultmap.put("RESULT_MSG", "查询成功!");
			}
			
		} catch (IOException e) {
			_LOGGER.info(e.getMessage());
			resultmap.put("RESULT", "0");
			resultmap.put("USERID", "");
			resultmap.put("RESULT_MSG", "查询失败!");
		}
		return resultmap;
	}

	
	/**
	 * 
	 * @param openid
	 * @param channelId  固定传:0002
	 * @param userid  第三方平台userid
	 * @return
	 */
	public HashMap<String,String> CreatRedp(String openid, String channelId,String userid){
		HashMap<String,String> resultmap = new HashMap<String,String>();
		String USERCREATREDPURL_result="";
		try {
			// 创建红包
			String USERCREATREDPURL = String.format(creatredpurl, openid,channelId,userid);
			USERCREATREDPURL_result = PostOrGet.sendGet(USERCREATREDPURL);
			JSONObject jsonObject = JSONObject.fromObject(USERCREATREDPURL_result);
			if("0".equals(jsonObject.getString("state"))){
				resultmap.put("RESULT", "1");
				resultmap.put("RESULT_MSG", "加油红包创建成功!");
			}else{
				resultmap.put("RESULT", "0");
				resultmap.put("RESULT_MSG", "加油红包创建失败!");
			}

		} catch (IOException e) {
			_LOGGER.info(e.getMessage());
			resultmap.put("RESULT", "0");
			resultmap.put("RESULT_MSG", "加油红包创建失败!");
		}
		_LOGGER.info("result::"+USERCREATREDPURL_result);  //{"redpkgId":"90","state":0}
		return resultmap;
	}
	
	private String getUserid(String openid, String phone) throws IOException {
		String result_userid="";
		String GETUSERURL = String.format(geturl, openid,phone);
		String GETUSERURL_result = PostOrGet.sendGet(GETUSERURL);

		_LOGGER.info("GETUSERURL_result:"+GETUSERURL_result);
		JSONObject jsonObject = JSONObject.fromObject(GETUSERURL_result);
		
		JSONObject mainUser = jsonObject.getJSONObject("mainUser");
		boolean containsKey_id = mainUser.containsKey("id");
		if(containsKey_id){
			//主表中存在
			result_userid= mainUser.getString("id");
		}
		return result_userid;
	}
	

	private JSONObject CreatMainUser(String openid, String phone,String type) throws IOException {
		String USERCREATURL = String.format(creaturl, openid,phone,type);
		String USERCREATURL_result = PostOrGet.sendGet(USERCREATURL);
		JSONObject jsonObject = JSONObject.fromObject(USERCREATURL_result);
		_LOGGER.info("result::"+jsonObject);
		return jsonObject;
	}
	

	public static void main(String[] args) throws UnsupportedEncodingException {
		LinkgitfUtil aPhoneMsgSendUtil = new LinkgitfUtil();
		HashMap<String, String> useridByOpenidAndPhone = aPhoneMsgSendUtil.getUseridByOpenidAndPhone("o4FD4v1oUqyYKuGAA0ryRzxpoCT0","13605170458");
         System.out.println(useridByOpenidAndPhone);
		/*		try {
			//aPhoneMsgSendUtil.getUserid("o4FD4v5ZFyJ_QLaER8taVEJNoInk", "18936770259");
			//aPhoneMsgSendUtil.getUserid("o4FD4v9l4Wnqi2YFJWatxb_WhlII", "13851558832");
			
			//aPhoneMsgSendUtil.CreatMainUser("o4FD4v5ZFyJ_QLaER8taVEJNoInk", "18936770259","2");
			aPhoneMsgSendUtil.CreatRedp("o4FD4v9l4Wnqi2YFJWatxb_WhlII","0002","344f72730b994d4bacbef6342a851ba3");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}

}
