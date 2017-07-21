package org.jumutang.project.tools;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/*import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.csii.payment.client.core.CebMerchantSignVerify;*/


class Demo{
	public static void main(String[] args) throws Exception {
		/*String url = "https://111.205.51.141/per/QueryMerchantEpay.do";
		
		*//**屏蔽支付网关证书*//*
		SSLContext sslContext = null;
		try { 
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null,new TrustManager[]{new X509TrustManager(){
				public void checkClientTrusted(
						X509Certificate[] ax509certificate, String s)
				throws CertificateException {
				}
				public void checkServerTrusted(
						X509Certificate[] ax509certificate, String s)
				throws CertificateException {
				}
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			}},null);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		LayeredConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		
		
		
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		String seraurl ="transId=IQSR~|~merchantId={0}~|~originalorderId={1}~|~originalTransDateTime={2}~|~originalTransAmt={3}";
		String formatseraurl = MessageFormat.format(seraurl, "370310000004","201609220939515928552","20160922093907","0.01");
		formParams.add(new BasicNameValuePair("Plain", formatseraurl));  
		String Signature = CebMerchantSignVerify.merchantSignData_ABA(formatseraurl);
		formParams.add(new BasicNameValuePair("Signature", Signature));
		formParams.add(new BasicNameValuePair("TransName", "IQSR"));
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams,"GBK");

		HttpPost httppost = new HttpPost(url);  
		httppost.setEntity(entity);
		try {

			CloseableHttpResponse response = httpClient.execute(httppost);
			HttpEntity ret = response.getEntity();
			String searchStr = new String(EntityUtils.toByteArray(ret),"GBK").replaceAll("\r\n", "");
			int indexOfPlain = searchStr.indexOf("Plain");
			int indexOfSignature = searchStr.indexOf("Signature");
			String ResponseCode = searchStr.substring(0, indexOfPlain);
			String[] splitResponseCode = ResponseCode.split("=");
			System.out.println("ResponseCode:"+ResponseCode);
			System.out.println("splitResponseCode:"+splitResponseCode[1]);
			
			String Plain = searchStr.substring(indexOfPlain, indexOfSignature);

			String[] splitPlain = Plain.split("=");
			searchStr.contains("transStatus=00");
		    if(searchStr.contains("ResponseCode=0000")&&searchStr.contains("transStatus=04")){
		    	System.out.println("对付正常");
		    }
			System.out.println("Plain:"+Plain);
			System.out.println("splitPlain:"+splitPlain[1]);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
}