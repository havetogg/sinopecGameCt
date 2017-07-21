package org.jumutang.project.weixinMng.mallMng.model;



import java.io.Serializable;

/*
 * 商城用户的Bean
 */

public class RankMode implements Serializable {

	private static final long serialVersionUID = -5065336974131274470L;

	private String ID;
	private String RANK_NAME;
	private String RETURN_DIAMOND;
	private String RETURN_GOLD;
	private String CREAT_TIME;
	private String DELETE_FLAG;
	public String getID(){
		return this.ID;
	}
	public void setID(String ID){
		this.ID=ID;
	}
	public String getRANK_NAME(){
		return this.RANK_NAME;
	}
	public void setRANK_NAME(String RANK_NAME){
		this.RANK_NAME=RANK_NAME;
	}
	public String getRETURN_DIAMOND(){
		return this.RETURN_DIAMOND;
	}
	public void setRETURN_DIAMOND(String RETURN_DIAMOND){
		this.RETURN_DIAMOND=RETURN_DIAMOND;
	}
	public String getRETURN_GOLD(){
		return this.RETURN_GOLD;
	}
	public void setRETURN_GOLD(String RETURN_GOLD){
		this.RETURN_GOLD=RETURN_GOLD;
	}
	public String getCREAT_TIME(){
		return this.CREAT_TIME;
	}
	public void setCREAT_TIME(String CREAT_TIME){
		this.CREAT_TIME=CREAT_TIME;
	}
	public String getDELETE_FLAG(){
		return this.DELETE_FLAG;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
	
}
