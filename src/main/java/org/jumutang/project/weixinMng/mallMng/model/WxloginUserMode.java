package org.jumutang.project.weixinMng.mallMng.model;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import org.jumutang.project.tools.DateUtil;
import org.jumutang.project.tools.StringUtil;

public class WxloginUserMode{

	private String ID;
	
	private String NICK_NAME;
	
	private String SEX;
	
	private String EMAIL;
	
	private String MOBILE;
	
	private String PASSWORD;
	
	private String BIRTH_YEAR;
	
	private String HEAD_IMG_URL;
	
	private String OPEN_ID;
	
	private String CREATE_TIME;
	
	private String LAST_UPDATE_TIME;
	
	private String DELETE_FLAG;
	
	private String AGE;
	
	
	public String getAGE() {
		return AGE;
	}

	public void setAGE() {
		if(StringUtil.isEmpty(BIRTH_YEAR)){
			AGE = "";	
		}else{
			AGE = String.valueOf((DateUtil.getPartOfTime(new Date(),"Y")-Integer.parseInt(BIRTH_YEAR)));
		}
	}

	public void setID(String ID){
		this.ID=ID;
	}
	
	public String getID(){
		return ID;
	}
	public void setNICK_NAME(String NICK_NAME){
		this.NICK_NAME = NICK_NAME;
	}
	
	public String getNICK_NAME(){
		return NICK_NAME;
	}
	public void setSEX(String SEX){
		this.SEX=SEX;
	}
	
	public String getSEX(){
		return SEX;
	}
	public void setEMAIL(String EMAIL){
		this.EMAIL=EMAIL;
	}
	
	public String getEMAIL(){
		return EMAIL;
	}
	public void setMOBILE(String MOBILE){
		this.MOBILE=MOBILE;
	}
	
	public String getMOBILE(){
		return MOBILE;
	}
	public void setPASSWORD(String PASSWORD){
		this.PASSWORD=PASSWORD;
	}
	
	public String getPASSWORD(){
		return PASSWORD;
	}
	public void setBIRTH_YEAR(String BIRTH_YEAR){
		this.BIRTH_YEAR=BIRTH_YEAR;
		setAGE();
	}
	
	public String getBIRTH_YEAR(){
		return BIRTH_YEAR;
	}
	public void setHEAD_IMG_URL(String HEAD_IMG_URL){
		this.HEAD_IMG_URL=HEAD_IMG_URL;
	}
	
	public String getHEAD_IMG_URL(){
		return HEAD_IMG_URL;
	}
	public void setOPEN_ID(String OPEN_ID){
		this.OPEN_ID=OPEN_ID;
	}
	
	public String getOPEN_ID(){
		return OPEN_ID;
	}
	public void setCREATE_TIME(String CREATE_TIME){
		this.CREATE_TIME=CREATE_TIME;
	}
	
	public String getCREATE_TIME(){
		return CREATE_TIME;
	}
	public void setLAST_UPDATE_TIME(String LAST_UPDATE_TIME){
		this.LAST_UPDATE_TIME=LAST_UPDATE_TIME;
	}
	
	public String getLAST_UPDATE_TIME(){
		return LAST_UPDATE_TIME;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
	
	public String getDELETE_FLAG(){
		return DELETE_FLAG;
	}
	
}

