package org.jumutang.project.weixinMng.mallMng.model;

public class TchangeOrderMode{

	private String ID;
	
	private String USER_ID;
	
	private String CHANGE_ID;
	
	private String ORDER_NO;
	
	private String MONEY;
	
	private String CREAT_TIME;
	
	private String PAYED_FLAG;
	
	private String PAY_MONEY;
	
	private String PAYED_TIME;
	
	private String DELETE_FLAG;
	
	private String DIAMOND_NUMB;
	
	
	public String getDIAMOND_NUMB() {
		return DIAMOND_NUMB;
	}

	public void setDIAMOND_NUMB(String dIAMOND_NUMB) {
		DIAMOND_NUMB = dIAMOND_NUMB;
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
	public void setCHANGE_ID(String CHANGE_ID){
		this.CHANGE_ID=CHANGE_ID;
	}
	
	public String getCHANGE_ID(){
		return CHANGE_ID;
	}
	public void setORDER_NO(String ORDER_NO){
		this.ORDER_NO=ORDER_NO;
	}
	
	public String getORDER_NO(){
		return ORDER_NO;
	}
	public void setMONEY(String MONEY){
		this.MONEY=MONEY;
	}
	
	public String getMONEY(){
		return MONEY;
	}
	public void setCREAT_TIME(String CREAT_TIME){
		this.CREAT_TIME=CREAT_TIME;
	}
	
	public String getCREAT_TIME(){
		return CREAT_TIME;
	}
	public void setPAYED_FLAG(String PAYED_FLAG){
		this.PAYED_FLAG=PAYED_FLAG;
	}
	
	public String getPAYED_FLAG(){
		return PAYED_FLAG;
	}
	public void setPAY_MONEY(String PAY_MONEY){
		this.PAY_MONEY=PAY_MONEY;
	}
	
	public String getPAY_MONEY(){
		return PAY_MONEY;
	}
	public void setPAYED_TIME(String PAYED_TIME){
		this.PAYED_TIME=PAYED_TIME;
	}
	
	public String getPAYED_TIME(){
		return PAYED_TIME;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
	
	public String getDELETE_FLAG(){
		return DELETE_FLAG;
	}
	
}
