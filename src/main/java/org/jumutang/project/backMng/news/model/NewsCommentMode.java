package org.jumutang.project.backMng.news.model;

public class NewsCommentMode{

	private String ID;
	
	private String USER_ID;
	
	private String NEWS_ID;
	
	private String CONTENT;
	
	private String CREATE_TIME;
	
	private String AUDIT_FLAG;
	
	private String DELETE_FLAG;
	
	
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
	public void setNEWS_ID(String NEWS_ID){
		this.NEWS_ID=NEWS_ID;
	}
	
	public String getNEWS_ID(){
		return NEWS_ID;
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
	public void setAUDIT_FLAG(String AUDIT_FLAG){
		this.AUDIT_FLAG=AUDIT_FLAG;
	}
	
	public String getAUDIT_FLAG(){
		return AUDIT_FLAG;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
	
	public String getDELETE_FLAG(){
		return DELETE_FLAG;
	}
	
}
