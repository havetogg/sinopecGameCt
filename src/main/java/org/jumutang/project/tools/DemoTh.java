package org.jumutang.project.tools;


import java.text.MessageFormat;

/*import com.csii.payment.client.core.MerchantSignTool;
import com.csii.payment.client.entity.RequestParameterObject;
import com.csii.payment.client.entity.SignParameterObject;
import com.csii.payment.client.http.HttpUtil;*/


class DemoTh{
	public static void main(String[] args) throws Exception {
		/*String Plainold = "<MessageSuit><Message id=\"\"><Plain id=\"CSRReq\"><transId>CSRReq</transId><merId>{0}</merId><serialNo>{1}</serialNo><date>{2}</date><originalSerialNo>{3}</originalSerialNo><originalDate>{4}</originalDate><amount>{5}</amount></Plain></Message></MessageSuit>";
		String Plainformat = MessageFormat.format(Plainold,"370310000004","90195304","20160922 10:51:15","201609220939515928552","20160922 09:39:07","0.01");
		SignParameterObject signParam=new SignParameterObject();
		
		signParam.setMerchantId("370310000004");//商户号
		signParam.setPlain(Plainformat);//明文
		signParam.setTransId("CSRReq");//交易码
		signParam.setCharset("UTF-8");//明文使用的字符集
		signParam.setType(2);//2-XML报文签名(使用Apache)
		signParam.setAlgorithm("RSAwithSHA1");//签名算法

		String sign = MerchantSignTool.sign(signParam);
		
		System.out.println(sign);
		
		String sendPost = sendToHost(sign,"https://111.205.51.141/agreeEpayper/payAccess.do");*/
	}
	
	/*private static String sendToHost(String reqXml, String postUrl) {
		RequestParameterObject params =new RequestParameterObject();
		params.setRequestData(reqXml);//设置请求数据
		params.setRequestURL(postUrl);//设置请求地址	
		// 连接建立超时
		params.setConnectTimeout("10000");
//		params.setRequestCharset("UTF-8");//设置请求字符集,如果没有设置，默认为GBK	
		params.addRequestProperties("Content-Type", "text/xml; charset=UTF-8");
		params.setVerifyFlag(false);
		String test ="";
		try {
			byte[] sendHost = HttpUtil.sendHost(params);
			
			test = new String(sendHost,"GBK");
			System.out.println(test);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return test;
}*/
	
	
/*	private static String sendToHost(String reqXml, String postUrl) {
		
		HttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		// 连接建立超时
		params.setConnectionTimeout(10000);
		// 数据等待超时
		params.setSoTimeout(60000);
		// 默认每个Host最多10个连接
		params.setDefaultMaxConnectionsPerHost(10);
		// 最大连接数（所有Host加起来）
		params.setMaxTotalConnections(200);

		connectionManager.setParams(params);		
		
		// 发送报文
		HttpClient httpClient = new HttpClient(connectionManager);
		PostMethod method = new PostMethod(postUrl);

		method.addRequestHeader("Content-Type", "text/xml; charset=UTF-8");
		try {
			method.setRequestEntity(new StringRequestEntity(reqXml, null, "utf-8"));
			httpClient.executeMethod(method);
			// 获得返回报文
			String res = method.getResponseBodyAsString();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return null;
	}*/
}