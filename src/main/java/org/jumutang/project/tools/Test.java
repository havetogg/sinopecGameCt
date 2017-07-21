package org.jumutang.project.tools;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.SortedMap;
import java.util.TreeMap;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url ="http://api.juxinbox.com/jxapi/sendMsm_doSendMessage";
/*		
//		String url ="http://v.juhe.cn/sms/send";
		String formattest="您的短信验证码是：{0,number,#.#}，该验证码5分钟内有效。【江苏有线微信公众号】";
		String appid= "26949d7cd2ecc48565cb91f24602b608";
		
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mobile", "13851558832");
		packageParams.put("content", formattest);
		String format = MessageFormat.format(formattest,99.99);
		System.out.println(format);*/
		
		String   Plain = "transId=IPER~|~merchantId=370310000004~|~orderId=13000000000000010009~|~transAmt=0.01~|~transDateTime=20120518171805~|~currencyType=01~|~customerName=~|~productInfo=~|~customerEMail=~|~transSeqNo=901239294263~|~ppDateTime=20120518~|~clearingDate=20120518~|~respCode=~|~msgExt=~|~&ResponseCode=0000";
		   
		String[] split = Plain.split("~\\u007C~");
		System.out.println("\\u007C");

/*		
		String createSign;
		try {
			createSign = RequestHandler.createSign(packageParams);
			System.out.println(createSign);
			packageParams.put("sign",createSign);

			System.out.println(JSONObject.fromObject(packageParams).toString());
			String sendPost = PostOrGet.sendPost(url,  JSONObject.fromObject(packageParams).toString(), null);
			System.out.println(sendPost);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		switch ("3333") {
		case "333":
			System.out.println(333);
			break;

		default:
			System.out.println(222);
			break;
		}
	}

}


