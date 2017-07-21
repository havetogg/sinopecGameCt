package org.jumutang.project.weixinMng.mallMng.dao;


import java.util.HashMap;

import org.jumutang.project.weixinMng.mallMng.model.RegistCodeMode;

public interface IRegistCodeDao {
	// 插入验证码的表
	public int insertRegistCodeInfoVolume(HashMap<String, String> amap);
	// 查询验证码的表
	public RegistCodeMode queryRegistCodeInfo(HashMap<String, String> amap);
}
