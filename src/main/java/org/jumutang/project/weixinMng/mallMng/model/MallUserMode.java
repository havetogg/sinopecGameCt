package org.jumutang.project.weixinMng.mallMng.model;



import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/*
 * 商城用户的Bean
 */

public class MallUserMode implements Serializable {

	private static final long serialVersionUID = -5065336974131274470L;
	private String ID;
	private String OPEN_ID;
	private String NICK_NAME;
	private String HEAD_IMG;
	private String MOBILE;
	private String NAME;
	private String CREAT_TIME;
	private String USED_DIAMOND;
	private String ALL_DIAMOND;
	private String REMAIN_DIAMOND;
	private String USED_GOLD;
	private String ALL_GOLD;
	private String SELF_CHANGED_ALL_DIAMOND;  //自己的值累计
	private String REMAIN_GOLD;
	private String USER_RANK_ID;
	private String USER_MAX_RANK_ID;
	private String THIRD_PART_ID;

	private String JS_USER_ID;

	private String DELETE_FLAG;
	
	private RankMode RANKMODE;     //关联的用户等信息,用于每周利
	
	private String USER_LEVEL_SCORE;  // 关联的用户经验分数
	
	private String USER_LEVEL_ID;  // 关联的用户经验分数
	
	private String CURRENT_LEVEL_MAXSCORE;// 关联的用户经验分升级的最大分数
	
	public String getUSER_LEVEL_ID() {
		return USER_LEVEL_ID;
	}
	public void setUSER_LEVEL_ID(String uSER_LEVEL_ID) {
		USER_LEVEL_ID = uSER_LEVEL_ID;
	}
	public String getCURRENT_LEVEL_MAXSCORE() {
		return CURRENT_LEVEL_MAXSCORE;
	}
	public void setCURRENT_LEVEL_MAXSCORE(String cURRENT_LEVEL_MAXSCORE) {
		CURRENT_LEVEL_MAXSCORE = cURRENT_LEVEL_MAXSCORE;
	}
	
	public String getUSER_LEVEL_SCORE() {
		return USER_LEVEL_SCORE;
	}
	public void setUSER_LEVEL_SCORE(String uSER_LEVEL_SCORE) {
		USER_LEVEL_SCORE = uSER_LEVEL_SCORE;
	}
	public String getSELF_CHANGED_ALL_DIAMOND() {
		return SELF_CHANGED_ALL_DIAMOND;
	}
	public void setSELF_CHANGED_ALL_DIAMOND(String sELF_CHANGED_ALL_DIAMOND) {
		SELF_CHANGED_ALL_DIAMOND = sELF_CHANGED_ALL_DIAMOND;
	}
	public RankMode getRANKMODE() {
		return RANKMODE;
	}
	public void setRANKMODE(RankMode rANKMODE) {
		RANKMODE = rANKMODE;
	}
	public String getREMAIN_DIAMOND() {
		return REMAIN_DIAMOND;
	}
	public void setREMAIN_DIAMOND(String rEMAIN_DIAMOND) {
		REMAIN_DIAMOND = String.valueOf(Integer.parseInt(ALL_DIAMOND)-Integer.parseInt(USED_DIAMOND));
	}
	public String getREMAIN_GOLD() {
		return REMAIN_GOLD;
	}
	public void setREMAIN_GOLD(String rEMAIN_GOLD) {
		REMAIN_GOLD = String.valueOf(Integer.parseInt(ALL_GOLD)-Integer.parseInt(USED_GOLD));;
	}
	public String getID(){
		return this.ID;
	}
	public void setID(String ID){
		this.ID=ID;
	}
	public String getOPEN_ID(){
		return this.OPEN_ID;
	}
	public void setOPEN_ID(String OPEN_ID){
		this.OPEN_ID=OPEN_ID;
	}
	public String getNICK_NAME(){
		return this.NICK_NAME;
	}
	public void setNICK_NAME(String nICK_NAME) {
		this.NICK_NAME = nICK_NAME;
		try {
			this.NICK_NAME = URLDecoder.decode(nICK_NAME, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public String getHEAD_IMG(){
		return this.HEAD_IMG;
	}
	public void setHEAD_IMG(String HEAD_IMG){
		this.HEAD_IMG=HEAD_IMG;
	}
	public String getMOBILE(){
		return this.MOBILE;
	}
	public void setMOBILE(String MOBILE){
		this.MOBILE=MOBILE;
	}
	public String getNAME(){
		return this.NAME;
	}
	public void setNAME(String NAME){
		this.NAME=NAME;
	}
	public String getCREAT_TIME(){
		return this.CREAT_TIME;
	}
	public void setCREAT_TIME(String CREAT_TIME){
		this.CREAT_TIME=CREAT_TIME;
	}
	public String getUSED_DIAMOND(){
		return this.USED_DIAMOND;
	}
	public void setUSED_DIAMOND(String USED_DIAMOND){
		this.USED_DIAMOND=USED_DIAMOND;
	}
	public String getALL_DIAMOND(){
		return this.ALL_DIAMOND;
	}
	public void setALL_DIAMOND(String ALL_DIAMOND){
		this.ALL_DIAMOND=ALL_DIAMOND;
	}
	public String getUSED_GOLD(){
		return this.USED_GOLD;
	}
	public void setUSED_GOLD(String USED_GOLD){
		this.USED_GOLD=USED_GOLD;
	}
	public String getALL_GOLD(){
		return this.ALL_GOLD;
	}
	public void setALL_GOLD(String ALL_GOLD){
		this.ALL_GOLD=ALL_GOLD;
	}
	public String getUSER_RANK_ID(){
		return this.USER_RANK_ID;
	}
	public void setUSER_RANK_ID(String USER_RANK_ID){
		this.USER_RANK_ID=USER_RANK_ID;
	}
	public String getUSER_MAX_RANK_ID(){
		return this.USER_MAX_RANK_ID;
	}
	public void setUSER_MAX_RANK_ID(String USER_MAX_RANK_ID){
		this.USER_MAX_RANK_ID=USER_MAX_RANK_ID;
	}
	public String getTHIRD_PART_ID(){
		return this.THIRD_PART_ID;
	}
	public void setTHIRD_PART_ID(String THIRD_PART_ID){
		this.THIRD_PART_ID=THIRD_PART_ID;
	}
	public String getJS_USER_ID() {
		return JS_USER_ID;
	}
	public void setJS_USER_ID(String JS_USER_ID) {
		this.JS_USER_ID = JS_USER_ID;
	}
	public String getDELETE_FLAG(){
		return this.DELETE_FLAG;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}

}
