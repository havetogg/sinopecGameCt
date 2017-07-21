package org.jumutang.project.weixinMng.mallMng.model;



import java.io.Serializable;

/*
 * 商城用户的Bean
 */

public class SysMsgMode implements Serializable {

	private static final long serialVersionUID = -5065336974131274470L;

	private String ID;
	
	private String USER_ID;
	
	private String MSG_TITLE;
	
	private String MSG_DETAIL;
	
	private String CREAT_TIME;
	
	private String TYPE;

	private String DIAMOND;
	
	private String GOLD;
	
	private String GET_TIME;
	
	private String DELETE_FLAG;

	private String READ_FLAG;
	
	private String READ_TIME;
	
	
	public String getDIAMOND() {
		return DIAMOND;
	}

	public void setDIAMOND(String dIAMOND) {
		DIAMOND = dIAMOND;
	}

	public String getGOLD() {
		return GOLD;
	}

	public void setGOLD(String gOLD) {
		GOLD = gOLD;
	}
	
	public String getREAD_FLAG() {
		return READ_FLAG;
	}

	public void setREAD_FLAG(String rEAD_FLAG) {
		READ_FLAG = rEAD_FLAG;
	}

	public String getREAD_TIME() {
		return READ_TIME;
	}

	public void setREAD_TIME(String rEAD_TIME) {
		READ_TIME = rEAD_TIME;
	}
	
	public void setID(String ID){
		this.ID=ID;
	}
	
	public String getID(){
		return ID;
	}
	public void setUSER_ID(String USER_ID){
		this.USER_ID=USER_ID;
	}
	
	public String getUSER_ID(){
		return USER_ID;
	}
	public void setMSG_TITLE(String MSG_TITLE){
		this.MSG_TITLE=MSG_TITLE;
	}
	
	public String getMSG_TITLE(){
		return MSG_TITLE;
	}
	public void setMSG_DETAIL(String MSG_DETAIL){
		this.MSG_DETAIL=MSG_DETAIL;
	}
	
	public String getMSG_DETAIL(){
		return MSG_DETAIL;
	}
	public void setCREAT_TIME(String CREAT_TIME){
		this.CREAT_TIME=CREAT_TIME;
	}
	
	public String getCREAT_TIME(){
		return CREAT_TIME;
	}
	public void setTYPE(String TYPE){
		this.TYPE=TYPE;
	}
	
	public String getTYPE(){
		return TYPE;
	}

	public void setGET_TIME(String GET_TIME){
		this.GET_TIME=GET_TIME;
	}
	
	public String getGET_TIME(){
		return GET_TIME;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
	
	public String getDELETE_FLAG(){
		return DELETE_FLAG;
	}
	
}
