package org.jumutang.project.weixinMng.gameOne.model;


public class T1AttributeBuyMode{

	private String ID;
	
	private String USER_GAME_ID;
	
	private String USER_GAMETYPE;

	private String TYPE;
	
	private String BUY_TIME;
	
	private String DELETE_FLAG;
	
	private String PAYED_DIAMOND;
	
	private String PAYED_GOLD;
	
	public String getUSER_GAMETYPE() {
		return USER_GAMETYPE;
	}

	public void setUSER_GAMETYPE(String uSER_GAMETYPE) {
		USER_GAMETYPE = uSER_GAMETYPE;
	}
	public String getPAYED_DIAMOND() {
		return PAYED_DIAMOND;
	}

	public void setPAYED_DIAMOND(String pAYED_DIAMOND) {
		PAYED_DIAMOND = pAYED_DIAMOND;
	}

	public String getPAYED_GOLD() {
		return PAYED_GOLD;
	}

	public void setPAYED_GOLD(String pAYED_GOLD) {
		PAYED_GOLD = pAYED_GOLD;
	}
	
	public void setID(String ID){
		this.ID=ID;
	}
	
	public String getID(){
		return ID;
	}
	public void setUSER_GAME_ID(String USER_GAME_ID){
		this.USER_GAME_ID=USER_GAME_ID;
	}
	
	public String getUSER_GAME_ID(){
		return USER_GAME_ID;
	}
	public void setTYPE(String TYPE){
		this.TYPE=TYPE;
	}
	
	public String getTYPE(){
		return TYPE;
	}
	public void setBUY_TIME(String BUY_TIME){
		this.BUY_TIME=BUY_TIME;
	}
	
	public String getBUY_TIME(){
		return BUY_TIME;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
	
	public String getDELETE_FLAG(){
		return DELETE_FLAG;
	}
	
}
