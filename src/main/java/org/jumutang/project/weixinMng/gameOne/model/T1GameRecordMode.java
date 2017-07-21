package org.jumutang.project.weixinMng.gameOne.model;

public class T1GameRecordMode{

	private String ID;
	
	private String USER_GAME_ID;
	
	private String GET_DIAMOND;
	
	private String GET_GOLD;
	
	private String GAS_TIMES;
	
	private String OPER_NICE;
	
	private String OPER_NOT_NICE;
	
	private String SCORE;
	
	private String GAME_TIME;
	
	private String DELETE_FLAG;

	private String TYPE;
	
	private String LEVEL_SCORE;
	
	public String getLEVEL_SCORE() {
		return LEVEL_SCORE;
	}

	public void setLEVEL_SCORE(String lEVEL_SCORE) {
		LEVEL_SCORE = lEVEL_SCORE;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
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
	public void setGET_DIAMOND(String GET_DIAMOND){
		this.GET_DIAMOND=GET_DIAMOND;
	}
	
	public String getGET_DIAMOND(){
		return GET_DIAMOND;
	}
	public void setGET_GOLD(String GET_GOLD){
		this.GET_GOLD=GET_GOLD;
	}
	
	public String getGET_GOLD(){
		return GET_GOLD;
	}
	public void setGAS_TIMES(String GAS_TIMES){
		this.GAS_TIMES=GAS_TIMES;
	}
	
	public String getGAS_TIMES(){
		return GAS_TIMES;
	}
	public void setOPER_NICE(String OPER_NICE){
		this.OPER_NICE=OPER_NICE;
	}
	
	public String getOPER_NICE(){
		return OPER_NICE;
	}
	public void setOPER_NOT_NICE(String OPER_NOT_NICE){
		this.OPER_NOT_NICE=OPER_NOT_NICE;
	}
	
	public String getOPER_NOT_NICE(){
		return OPER_NOT_NICE;
	}
	public void setSCORE(String SCORE){
		this.SCORE=SCORE;
	}
	
	public String getSCORE(){
		return SCORE;
	}
	public void setGAME_TIME(String GAME_TIME){
		this.GAME_TIME=GAME_TIME;
	}
	
	public String getGAME_TIME(){
		return GAME_TIME;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
	
	public String getDELETE_FLAG(){
		return DELETE_FLAG;
	}
	
}
