package org.jumutang.project.backMng.discus.model;

public class DiscusCommentMode{

	private String ID;
	
	private String DISCUSS_ID;
	
	private String USER_ID;
	
	private String CONTENT;
	
	private String CREATE_TIME;
	
	private String PRAISE_NUM;
	
	private String REPLY_NUM;
	
	private String DELETE_FLAG;
	
	
	public void setID(String ID){
		this.ID=ID;
	}
	
	public String getID(){
		return ID;
	}
	public void setDISCUSS_ID(String DISCUSS_ID){
		this.DISCUSS_ID=DISCUSS_ID;
	}
	
	public String getDISCUSS_ID(){
		return DISCUSS_ID;
	}
	public void setUSER_ID(String USER_ID){
		this.USER_ID=USER_ID;
	}
	
	public String getUSER_ID(){
		return USER_ID;
	}
	public void setCONTENT(String CONTENT){
		this.CONTENT=CONTENT;
	}
	
	public String getCONTENT(){
		return CONTENT;
	}
	public void setCREATE_TIME(String CREATE_TIME){
		this.CREATE_TIME=CREATE_TIME;
	}
	
	public String getCREATE_TIME(){
		return CREATE_TIME;
	}
	public void setPRAISE_NUM(String PRAISE_NUM){
		this.PRAISE_NUM=PRAISE_NUM;
	}
	
	public String getPRAISE_NUM(){
		return PRAISE_NUM;
	}
	public void setREPLY_NUM(String REPLY_NUM){
		this.REPLY_NUM=REPLY_NUM;
	}
	
	public String getREPLY_NUM(){
		return REPLY_NUM;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
	
	public String getDELETE_FLAG(){
		return DELETE_FLAG;
	}
	
}
