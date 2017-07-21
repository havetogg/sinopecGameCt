package org.jumutang.project.weixinMng.mallMng.model;


import java.io.Serializable;

/*
 *验证码的Bean
 */

public class WxRegistCodeMode implements Serializable {

	private static final long serialVersionUID = -5065336974131274470L;
	//主键ID
	private String ID ;
	private String MOBILE;
	// 验证码
	private String CODE ;
	// 有效果时间
	private String EFFECTIVE_TIME;
	private String CREATE_TIME;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getMOBILE() {
		return MOBILE;
	}
	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getEFFECTIVE_TIME() {
		return EFFECTIVE_TIME;
	}
	public void setEFFECTIVE_TIME(String eFFECTIVE_TIME) {
		EFFECTIVE_TIME = eFFECTIVE_TIME;
	}
	public String getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

}
