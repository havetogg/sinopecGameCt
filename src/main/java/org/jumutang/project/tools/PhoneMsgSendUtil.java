package org.jumutang.project.tools;

import java.io.UnsupportedEncodingException;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import net.sf.json.JSONObject;


/**
 * 发送手机消息
 * 
 * @author 
 * @date 2015-12-11
 */
public class PhoneMsgSendUtil {
	private static final Logger _LOGGER = Logger.getLogger(PhoneMsgSendUtil.class);
	private String url ="http://api.juxinbox.com/jxapi/sendMsm_doSendMessage";
//	private String sendMsg ="【光大商城】验证码：%s，您当前正在注册中国光大银行微商城，验证码很重要，打死都不能告诉任何人哦！";
	private String sendMsg ="【有礼付】您的验证码是%s，如非本人操作请忽略该短信。";
	//	private String sendMsg ="【聚客有礼】您的验证码是：%s，3分钟内有效。如非您本人操作，可忽略本消息。";
//	private String sendMsg ="您的短信验证码是：%s，该验证码5分钟内有效。【江苏有线微信公众号】";
	private String appid= "26949d7cd2ecc48565cb91f24602b608"; // 光大
//	private String appid= "fea95155a310d3171189a4d26dd231ac"; // 聚客有礼
	

	
	/**
	 * @param phone手机号
	 * @param note 发送的内容
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public JSONObject sendMessage(String phone, String note) throws UnsupportedEncodingException {
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mobile", phone);
		packageParams.put("content", String.format(sendMsg, note));

		String createSign = RequestHandler.createSign(packageParams);
		packageParams.put("sign", createSign);
		System.out.println("createSign:"+createSign);
		_LOGGER.info("createSign:"+createSign);
		
		String strpost=JSONObject.fromObject(packageParams).toString();
		
		System.out.println("strpost:"+strpost);
		_LOGGER.info("strpost:"+strpost);
		String sendPost = PostOrGet.sendPost(url,strpost , null);
		JSONObject jsonObject = JSONObject.fromObject(sendPost);

		System.out.println("result:"+sendPost);
		_LOGGER.info("result::"+sendPost);
		return jsonObject;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		PhoneMsgSendUtil aPhoneMsgSendUtil = new PhoneMsgSendUtil();
		aPhoneMsgSendUtil.sendMessage("13851558832", "7880");
	}
}
