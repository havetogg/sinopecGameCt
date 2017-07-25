package org.jumutang.project.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	/**
	 * PROPERTIES的MAP
	 */
	private static Properties prop=new Properties();
	//是否初始化过本地配置文件标志位
	private static boolean initLocalFlag=false;
	
	private static void init(){
		InputStream is=null;
		try {
			is=PropertiesUtil.class.getClassLoader().getResourceAsStream("conf/produ/common.properties");
			//is=PropertiesUtil.class.getClassLoader().getResourceAsStream("conf/deve/common.properties");
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * 获取属性的值
	 * @param properName
	 * @return
	 */
	public static String get(String properName){
		if(initLocalFlag==false){
			init();
			initLocalFlag=true;
		}
		return prop.getProperty(properName);
	}
	
	public static void put(String PROP_KEY,String PROP_VALUE){
		prop.put(PROP_KEY, PROP_VALUE);
	}
}
