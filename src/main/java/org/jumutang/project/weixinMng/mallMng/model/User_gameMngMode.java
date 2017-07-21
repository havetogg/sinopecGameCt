package org.jumutang.project.weixinMng.mallMng.model;

public class User_gameMngMode{

	private String ID;
	
	private String USER_ID;
	
	private String GAME_ID;
	
	private String CREAT_TIME;
	
	private String DAY_TIMES;

	private String GAME_USED_TIMES;
	
	private String GAME_ALL_TIMES;
	
	private String REMAIN_GAME_TIMES;   //剩余的游戏次数

	private String WEEK_SCORE;
	
	private String WEEK_TIME;
	
	private String ALL_SCORE;
	
	private String LAST_GAME_TIME;
	
	private String DELETE_FLAG;

	private String WEEK_GET_GOLD;
	
	private String WEEK_GET_DIAMOND;
	
	private String NOTE;
	
	private GameMngMode GAMEMNGMODE;
	
	private MallUserMode MALLUSERMODE;
	
	public String getREMAIN_GAME_TIMES() {
		return REMAIN_GAME_TIMES;
	}

	public void setREMAIN_GAME_TIMES() {
		REMAIN_GAME_TIMES = String.valueOf(Integer.parseInt(GAME_ALL_TIMES)-Integer.parseInt(GAME_USED_TIMES));
	}
	
	public String getDAY_TIMES() {
		return DAY_TIMES;
	}

	public void setDAY_TIMES(String dAY_TIMES) {
		DAY_TIMES = dAY_TIMES;
	}
	
	public String getWEEK_GET_GOLD() {
		return WEEK_GET_GOLD;
	}

	public void setWEEK_GET_GOLD(String wEEK_GET_GOLD) {
		WEEK_GET_GOLD = wEEK_GET_GOLD;
	}

	public String getWEEK_GET_DIAMOND() {
		return WEEK_GET_DIAMOND;
	}

	public void setWEEK_GET_DIAMOND(String wEEK_GET_DIAMOND) {
		WEEK_GET_DIAMOND = wEEK_GET_DIAMOND;
	}

	public String getNOTE() {
		return NOTE;
	}

	public void setNOTE(String nOTE) {
		NOTE = nOTE;
	}
	
	public MallUserMode getMALLUSERMODE() {
		return MALLUSERMODE;
	}

	public void setMALLUSERMODE(MallUserMode mALLUSERMODE) {
		MALLUSERMODE = mALLUSERMODE;
	}

	public GameMngMode getGAMEMNGMODE() {
		return GAMEMNGMODE;
	}

	public void setGAMEMNGMODE(GameMngMode gAMEMNGMODE) {
		GAMEMNGMODE = gAMEMNGMODE;
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
	public void setGAME_ID(String GAME_ID){
		this.GAME_ID=GAME_ID;
	}
	
	public String getGAME_ID(){
		return GAME_ID;
	}
	public void setCREAT_TIME(String CREAT_TIME){
		this.CREAT_TIME=CREAT_TIME;
	}
	
	public String getCREAT_TIME(){
		return CREAT_TIME;
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
	public void setWEEK_SCORE(String WEEK_SCORE){
		this.WEEK_SCORE=WEEK_SCORE;
	}
	
	public String getWEEK_SCORE(){
		return WEEK_SCORE;
	}
	public void setWEEK_TIME(String WEEK_TIME){
		this.WEEK_TIME=WEEK_TIME;
	}
	
	public String getWEEK_TIME(){
		return WEEK_TIME;
	}
	public void setALL_SCORE(String ALL_SCORE){
		this.ALL_SCORE=ALL_SCORE;
	}
	
	public String getALL_SCORE(){
		return ALL_SCORE;
	}
	public void setLAST_GAME_TIME(String LAST_GAME_TIME){
		this.LAST_GAME_TIME=LAST_GAME_TIME;
	}
	
	public String getLAST_GAME_TIME(){
		return LAST_GAME_TIME;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
	
	public String getDELETE_FLAG(){
		return DELETE_FLAG;
	}
	
}
