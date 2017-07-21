package org.jumutang.project.weixinMng.gameOne.model;


public class T1WeekRankReturnMode{

	private String ID;
	
	private String USER_GAME_ID;
	
	private String TYPE;
	
	private String RETURN_DIAMOND;
	
	private String RETURN_GOLD;
	
	private String LASTE_WEEK_RANK;
	
	private String RETURN_TIME;
	
	private String DELETE_FLAG;
	
	
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
	public void setRETURN_DIAMOND(String RETURN_DIAMOND){
		this.RETURN_DIAMOND=RETURN_DIAMOND;
	}
	
	public String getRETURN_DIAMOND(){
		return RETURN_DIAMOND;
	}
	public void setRETURN_GOLD(String RETURN_GOLD){
		this.RETURN_GOLD=RETURN_GOLD;
	}
	
	public String getRETURN_GOLD(){
		return RETURN_GOLD;
	}
	public void setLASTE_WEEK_RANK(String LASTE_WEEK_RANK){
		this.LASTE_WEEK_RANK=LASTE_WEEK_RANK;
	}
	
	public String getLASTE_WEEK_RANK(){
		return LASTE_WEEK_RANK;
	}
	public void setRETURN_TIME(String RETURN_TIME){
		this.RETURN_TIME=RETURN_TIME;
	}
	
	public String getRETURN_TIME(){
		return RETURN_TIME;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
	
	public String getDELETE_FLAG(){
		return DELETE_FLAG;
	}
	
}
