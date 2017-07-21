package org.jumutang.project.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.jumutang.basicbox.tools.MD5X;
import org.jumutang.project.base.ResultBean;

import net.sf.json.JSONObject;


public class ScoreTool {
	//40846462
	private static final Logger _LOGGER = Logger.getLogger(ScoreTool.class);
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
	
	private static long lastRegiest = new Date().getTime();
	
	
	public static synchronized ResultBean regiest(String loginname,String password){
		long waitTime = new Date().getTime() - lastRegiest;
		if(waitTime<4000){
			try {
				Thread.sleep(4000-waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String url = "http://passport.njstudy.com/api/user/reg?auth=20160418nj";
		StringBuffer param = new StringBuffer().append("username=").append(loginname).append("&password=").append(password);
		String result = PostOrGet.sendPost(url,param.toString(),PostOrGet.POST_FORM_SUBMIT_TYPE);
		lastRegiest = new Date().getTime();
		JSONObject resultJsoin = JSONObject.fromObject(result);
		if(resultJsoin.getBoolean("ret")){
			//成功的时候
			return ResultBean.newSuccessResult(resultJsoin.getJSONObject("msg").getString("username"));
		}else{
			if(resultJsoin.getString("msg").startsWith("每个接口")){
				return ResultBean.newErrorResult("注册失败请重试");
			}
			//失败的时候
			return ResultBean.newErrorResult(resultJsoin.getString("msg"));
		}
	
	}
	//20160418nj
	public static Double getScore(String userId){
		try {
			_LOGGER.info(userId+" getScore");
			String url = "http://score.njstudy.com/services/ScoreOutService/getScore";
			StringBuffer param = new StringBuffer().append("fid=6638&sType=0&callBack=&uid=").append(userId).append("&token=");
			String md5Str = MD5X.getUpperCaseMD5For32("6638_"+userId+"_0_njstudy_"+sdf.format(new Date()));
			param.append(md5Str);
			String result = PostOrGet.sendPost(url,param.toString(),PostOrGet.POST_FORM_SUBMIT_TYPE);
			_LOGGER.info("getScoreResult "+userId);
			JSONObject resultBean = JSONObject.fromObject(result);
			if("0".equals(resultBean.getString("result"))){
				return Double.parseDouble(JSONObject.fromObject(resultBean.get("obj")).getString("score"))+Double.parseDouble(JSONObject.fromObject(resultBean.get("obj")).getString("score_used"));
			}else{
				return null;
			}
		} catch (Exception e) {
			_LOGGER.info(e);
			e.printStackTrace();
			return null;
		}
	}
	
	public static Boolean useScore(String userId,Double score,String prodName){
		try {
			_LOGGER.info(userId+" useScore");
			String url = "http://score.njstudy.com/services/ScoreOutService/useScore";
			StringBuffer param = new StringBuffer().append("fid=6638&sType=0&callBack=&uid=").append(userId).append("&useScore=-").append(score).append("&resourceName=").append(prodName).append("&token=");
			String md5Str = MD5X.getUpperCaseMD5For32("6638_"+userId+"_0_njstudy_"+sdf.format(new Date()));
			param.append(md5Str);
			String result = PostOrGet.sendPost(url,param.toString(),PostOrGet.POST_FORM_SUBMIT_TYPE);
			_LOGGER.info("useScoreResult "+result);
			JSONObject resultBean = JSONObject.fromObject(result);
			if("0".equals(resultBean.getString("result"))){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			_LOGGER.info(e);
			e.printStackTrace();
			return false;
		}
	}
	
	
//	public static Boolean addScore(String userId,Double score,String prodName){
//		//http://score.njstudy.com/services/ScoreOutService/addScore?token=1ABEA8A0CEC66A15E6A3B0BB215946A0&fid=6638&uid=1&sType=25
//
//		try {
//			String url = "http://score.njstudy.com/services/ScoreOutService/addScore";
//			StringBuffer param = new StringBuffer().append("fid=6638&sType=0&uid=").append(userId).append("&addScore=").append(score).append("&resourceName=").append(prodName).append("&token=");
//			String md5Str = MD5X.getUpperCaseMD5For32("6638_"+userId+"_0_njstudy_"+sdf.format(new Date()));
//			param.append(md5Str);
//			String result = PostOrGet.sendPost(url,param.toString(),PostOrGet.POST_FORM_SUBMIT_TYPE);
//			System.out.println(result);
//			JSONObject resultBean = JSONObject.fromObject(result);
//			if("0".equals(resultBean.getString("result"))){
//				return true;
//			}else{
//				return false;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	
//		
//	}
	
	
	public static String getUserInfo(String studyUserId){
		//http://passport.sh.chaoxing.com/api/user/userByCxid?cxid=20952175&auth=20141021yy

		try {
			_LOGGER.info(studyUserId+" getUserInfo");
			String url = "http://passport.njstudy.com/api/user/userByCxid";
			url = url+"?auth=20160418nj&cxid="+studyUserId;
			String result = PostOrGet.sendGet(url);
			_LOGGER.info("getUserInfo "+result);
			JSONObject resultBean = JSONObject.fromObject(result);
			if("true".equals(resultBean.getString("ret"))){
				String nickname = resultBean.getJSONObject("msg").getJSONObject(studyUserId).getString("nickname");
				return nickname;
			}
			return "";
		} catch (Exception e) {
			_LOGGER.info(e);
			e.printStackTrace();
			return "";
		}
	}

}
