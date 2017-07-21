package org.jumutang.project.tools;

import java.io.UnsupportedEncodingException;
import java.util.SortedMap;
import java.util.TreeMap;

import net.sf.json.JSONObject;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url ="http://api.juxinbox.com/jxapi/sendMsm_doSelectMessage";
		
//		String url ="http://v.juhe.cn/sms/send";
		String formattest="您的短信验证码是：123456，该验证码5分钟内有效。【江苏有线微信公众号】";
		String appid= "26949d7cd2ecc48565cb91f24602b608";
		
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
//		packageParams.put("mobile", "13851558832");
//		packageParams.put("content", formattest);
//		packageParams.put("call_sign", "】");

		
		String createSign;
		try {
//			createSign = RequestHandler.createSign("appid=26949d7cd2ecc48565cb91f24602b608");
			String sign = Md5.GetMD5Code(RequestHandler.UrlEncode("appid=26949d7cd2ecc48565cb91f24602b608"))
					.toUpperCase();
			System.out.println(sign);
			packageParams.put("sign",sign);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
//			System.out.println(JSONObject.fromObject(packageParams).toString());
//			JSONObject httpRequest = PhoneMsgSendUtil.httpRequest(url, "POST", JSONObject.fromObject(packageParams).toString());
//			System.out.println(httpRequest.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
