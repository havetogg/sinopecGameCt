package org.jumutang.project.weixinMng.mallMng.model;

public class WxNews_ImgMngMode{
	private String ID;
	private String NEWS_ID;
	private String ORIGINAL_PATH;
	private String CUTTED_PATH;
	private String SHOW_ORDER;
	private String DELETE_FLAG;
	public String getID(){
		return this.ID;
	}
	public void setID(String ID){
		this.ID=ID;
	}
	public String getNEWS_ID(){
		return this.NEWS_ID;
	}
	public void setNEWS_ID(String NEWS_ID){
		this.NEWS_ID=NEWS_ID;
	}
	public String getORIGINAL_PATH(){
		return this.ORIGINAL_PATH;
	}
	public void setORIGINAL_PATH(String ORIGINAL_PATH){
		this.ORIGINAL_PATH=ORIGINAL_PATH;
	}
	public String getCUTTED_PATH(){
		return this.CUTTED_PATH;
	}
	public void setCUTTED_PATH(String CUTTED_PATH){
		this.CUTTED_PATH=CUTTED_PATH;
	}
	public String getSHOW_ORDER(){
		return this.SHOW_ORDER;
	}
	public void setSHOW_ORDER(String SHOW_ORDER){
		this.SHOW_ORDER=SHOW_ORDER;
	}
	public String getDELETE_FLAG(){
		return this.DELETE_FLAG;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
}
