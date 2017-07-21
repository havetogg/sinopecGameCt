package org.jumutang.project.weixinMng.mallMng.model;


public class GameMngMode{

	private String ID;
	
	private String GAME_NAME;
	
	private String GAME_DETAIL;
	
	private String GAME_URL;
	
	private String GAME_IMG_URL;

	private String CREAT_TIME;
	
	private String SHOW_ORDER;
	
	private String DELETE_FLAG;
	
	private String USER_COLLECTION_FLAG;           //用户是否收藏    1:已经收藏
	
	private String USER_RANK_FLAG;                 //用户是否上榜    1:已经上榜
	
	public String getUSER_RANK_FLAG() {
		return USER_RANK_FLAG;
	}

	public void setUSER_RANK_FLAG(String uSER_RANK_FLAG) {
		USER_RANK_FLAG = uSER_RANK_FLAG;
	}

	public String getUSER_COLLECTION_FLAG() {
		return USER_COLLECTION_FLAG;
	}

	public void setUSER_COLLECTION_FLAG(String uSER_COLLECTION_FLAG) {
		USER_COLLECTION_FLAG = uSER_COLLECTION_FLAG;
	}

	public String getGAME_IMG_URL() {
		return GAME_IMG_URL;
	}

	public void setGAME_IMG_URL(String gAME_IMG_URL) {
		GAME_IMG_URL = gAME_IMG_URL;
	}
	
	public void setID(String ID){
		this.ID=ID;
	}
	
	public String getID(){
		return ID;
	}
	public void setGAME_NAME(String GAME_NAME){
		this.GAME_NAME=GAME_NAME;
	}
	
	public String getGAME_NAME(){
		return GAME_NAME;
	}
	public void setGAME_DETAIL(String GAME_DETAIL){
		this.GAME_DETAIL=GAME_DETAIL;
	}
	
	public String getGAME_DETAIL(){
		return GAME_DETAIL;
	}
	public void setGAME_URL(String GAME_URL){
		this.GAME_URL=GAME_URL;
	}
	
	public String getGAME_URL(){
		return GAME_URL;
	}
	public void setCREAT_TIME(String CREAT_TIME){
		this.CREAT_TIME=CREAT_TIME;
	}
	
	public String getCREAT_TIME(){
		return CREAT_TIME;
	}
	public void setSHOW_ORDER(String SHOW_ORDER){
		this.SHOW_ORDER=SHOW_ORDER;
	}
	
	public String getSHOW_ORDER(){
		return SHOW_ORDER;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
	
	public String getDELETE_FLAG(){
		return DELETE_FLAG;
	}
	
}
