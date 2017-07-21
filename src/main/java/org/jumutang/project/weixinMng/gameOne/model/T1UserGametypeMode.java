package org.jumutang.project.weixinMng.gameOne.model;

public class T1UserGametypeMode{

	private String ID;
	
	private String USER_GAME_ID;
	
	private String TYPE;
	
	private String DAY_TIMES;
	
	private String GAME_USED_TIMES;
	
	private String GAME_ALL_TIMES;
	
	private String REMAIN_GAME_TIMES;  //剩余次数
	
	private String DELETE_FLAG;
	
	public String getREMAIN_GAME_TIMES() {
		return REMAIN_GAME_TIMES;
	}

	public void setREMAIN_GAME_TIMES() {
		REMAIN_GAME_TIMES = String.valueOf(Integer.parseInt(GAME_ALL_TIMES)-Integer.parseInt(GAME_USED_TIMES));
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
	public void setDAY_TIMES(String DAY_TIMES){
		this.DAY_TIMES=DAY_TIMES;
	}
	
	public String getDAY_TIMES(){
		return DAY_TIMES;
	}
	public void setGAME_USED_TIMES(String GAME_USED_TIMES){
		this.GAME_USED_TIMES=GAME_USED_TIMES;
	}
	
	public String getGAME_USED_TIMES(){
		return GAME_USED_TIMES;
	}
	public void setGAME_ALL_TIMES(String GAME_ALL_TIMES){
		this.GAME_ALL_TIMES=GAME_ALL_TIMES;
	}
	
	public String getGAME_ALL_TIMES(){
		return GAME_ALL_TIMES;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
	
	public String getDELETE_FLAG(){
		return DELETE_FLAG;
	}
	
}
