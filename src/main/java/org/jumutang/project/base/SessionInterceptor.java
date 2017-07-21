package org.jumutang.project.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jumutang.project.tools.PropertiesUtil;
import org.jumutang.project.tools.StringUtil;
import org.jumutang.project.tools.WeixinUtil;
import org.jumutang.project.weixinMng.mallMng.model.MallUserMode;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

public class SessionInterceptor implements HandlerInterceptor{
	
	private List<String> filterUrl=new ArrayList<String>();                 //过滤不check
	private List<String> filterUrlNeedLogin=new ArrayList<String>();        //需要登录
	// 手机端
	private final String _STWEINXIN="/weixinMng/";
	// 后台管理
	private final String _PCMNG="/backMng/";
	
	public SessionInterceptor() {
		filterUrl.add("/weixinMng/ManageC/wxLogin");
		filterUrl.add("/LoginC");
		filterUrl.add("/weChatJSConfigC");
		filterUrl.add("/weixinMng/ManageC/toPayPage_return.htm");
		filterUrl.add("/websocket");

		
		filterUrlNeedLogin.add("/weixinMng/Manage/addressManager");
		filterUrlNeedLogin.add("/weixinMng/Manage/merchanttypelist.htm");
		filterUrlNeedLogin.add("/weixinMng/supervise/paniBuy.htm");
		filterUrlNeedLogin.add("/weixinMng/GroupBuy");

		
		
//		filterUrlNeedLogin.add("/weixinMng/DrawMng/gotoDrawPage");
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String PROJECT_NAME=request.getContextPath();
  		String URL=request.getRequestURI();

		//过滤相应的URL
		String COMPARE_URL=URL.substring(PROJECT_NAME.length());
		for(String s:filterUrl){
			if(COMPARE_URL.startsWith(s)){
				return true;
			}
		}
		// 后台管理的过滤器
		if(COMPARE_URL.startsWith(_PCMNG)){
			//判断用户是否
			Object object=request.getSession().getAttribute("currUser");
			if(object==null){
				response.sendRedirect(PROJECT_NAME+"/sessionTimeOut.jsp");
				return false;
			}else{
				return true;
			}
		}

		
		// 手机前台的过滤器
		if(COMPARE_URL.startsWith(_STWEINXIN)||COMPARE_URL.equals("/getPrize/prizeIn.htm")){
 			if(COMPARE_URL.startsWith("/weixinMng/ManageC/wxLogin")){
				return true;
			}
			for(String s:filterUrl){
				if(COMPARE_URL.startsWith(s)){
					return true;
				}
			}
			
			MallUserMode userBean=(MallUserMode)request.getSession().getAttribute("WxloginUser");
			if(userBean==null||StringUtil.isEmpty(userBean.getOPEN_ID())){
				System.out.println(userBean);
				if(userBean!=null){
					System.out.println(userBean.getOPEN_ID());
				}else{
					System.out.println("-------");
					
				}
				String REDIRECT_URL=URL.substring(PROJECT_NAME.length());
				String paramStr=request.getQueryString();
		    	if(!StringUtil.isEmpty(paramStr)){
		    		REDIRECT_URL=REDIRECT_URL+"?"+paramStr;
		    	}
				
	    		// 前台ajax请求的场合
	    		if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ //如果是ajax请求响应头会有，x-requested-with  
	                HashMap<String, String> aMap = new HashMap<>();
	                aMap.put("timeout", "-1");      // session 过期返回
	    			responseOutWithJson(response,aMap);
	                return false;
	    		}
		    	
/*				String conf_appId=WeixinUtil.getAppId();
				String domain_name=PropertiesUtil.get("DOMAIN.WEB_SERVER");
				String target=domain_name+PROJECT_NAME+"/weixinMng/ManageC/wxLogin.htm?redirectUrl="+URLEncoder.encode(REDIRECT_URL);
								
				final String reVisitUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+conf_appId+"&redirect_uri="+URLEncoder.encode(target)+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
				
				response.sendRedirect(reVisitUrl);
				return false;*/
	    		String domain_name=PropertiesUtil.get("DOMAIN.WEB_SERVER");
	    		String local_target=domain_name+PROJECT_NAME+"/weixinMng/ManageC/wxLogin.htm?redirectUrl="+URLEncoder.encode(REDIRECT_URL);


	    		String gate_appId=PropertiesUtil.get("gate_appId");
	    		String gate_secret=PropertiesUtil.get("gate_secret");
	    		long timestamp=System.currentTimeMillis();
	    		String md5=md5Encode(gate_appId+gate_secret+timestamp);
	    		
	    		String gate_domain=PropertiesUtil.get("gate_domain");
	    		String gateUrl=String.format(gate_domain,gate_appId,timestamp, md5);
	    		String target=gateUrl+"&redirect_client_url="+URLEncoder.encode(local_target);

	    		final String reVisitUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+gate_appId+"&redirect_uri="+URLEncoder.encode(target)+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	    		response.sendRedirect(reVisitUrl);
	    		return false;
			}else{
				return true;
			}
		}
		
		return true;
		
	}
		

	/** 
	 * 以JSON格式输出 
	 * @param response 
	 */  
	private void responseOutWithJson(HttpServletResponse response,  
	        Object responseObject) {  
	    //将实体对象转换为JSON Object转换  
	    JSONObject responseJSONObject = JSONObject.fromObject(responseObject);  
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(responseJSONObject.toString());  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    }  
	}  
	
	public  String md5Encode(String sourceStr){
		MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = null;
		try {
			byteArray = sourceStr.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
	}

}
