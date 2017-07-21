package org.jumutang.project.weixinMng.mallMng.service;

import java.util.HashMap;

import org.jumutang.project.weixinMng.mallMng.model.RegistCodeMode;

/**
 * 注册码service
 * @author Administrator
 *
 */
public interface IRegistCodeService {
	
	/**
	 * 插入验证码表  
	 */
	public int insertRegistCodeInfoVolume(HashMap<String, String> amap);
	
	/**
	 * 查询验证码表 
	 */
	public RegistCodeMode queryRegistCodeInfo(HashMap<String, String> amap);
	
	

}
