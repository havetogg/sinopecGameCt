package org.jumutang.project.backMng.news.model;

public class NewsCoverimageMngMode{

	private String ID;
	
	private String NEWS_ID;
	
	private String ORIGINAL_PATH;
	
	private String CUTTED_PATH;
	
	private String SHOW_ORDER;
	
	private String DELETE_FLAG;
	
	
	public void setID(String ID){
		this.ID=ID;
	}
	
	public String getID(){
		return ID;
	}
	public void setNEWS_ID(String NEWS_ID){
		this.NEWS_ID=NEWS_ID;
	}
	
	public String getNEWS_ID(){
		return NEWS_ID;
	}
	public void setORIGINAL_PATH(String ORIGINAL_PATH){
		this.ORIGINAL_PATH=ORIGINAL_PATH;
	}
	
	public String getORIGINAL_PATH(){
		return ORIGINAL_PATH;
	}
	public void setCUTTED_PATH(String CUTTED_PATH){
		this.CUTTED_PATH=CUTTED_PATH;
	}
	
	public String getCUTTED_PATH(){
		return CUTTED_PATH;
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
