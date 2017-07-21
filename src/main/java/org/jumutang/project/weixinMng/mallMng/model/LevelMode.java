package org.jumutang.project.weixinMng.mallMng.model;

public class LevelMode{

	private String ID;
	
	private String LEVEL_NAME;
	
	private String LEVEL_FROM;
	
	private String LEVEL_TO;
	
	private String CREAT_TIME;
	
	private String DELETE_FLAG;
	
	
	public void setID(String ID){
		this.ID=ID;
	}
	
	public String getID(){
		return ID;
	}
	public void setLEVEL_NAME(String LEVEL_NAME){
		this.LEVEL_NAME=LEVEL_NAME;
	}
	
	public String getLEVEL_NAME(){
		return LEVEL_NAME;
	}
	public void setLEVEL_FROM(String LEVEL_FROM){
		this.LEVEL_FROM=LEVEL_FROM;
	}
	
	public String getLEVEL_FROM(){
		return LEVEL_FROM;
	}
	public void setLEVEL_TO(String LEVEL_TO){
		this.LEVEL_TO=LEVEL_TO;
	}
	
	public String getLEVEL_TO(){
		return LEVEL_TO;
	}
	public void setCREAT_TIME(String CREAT_TIME){
		this.CREAT_TIME=CREAT_TIME;
	}
	
	public String getCREAT_TIME(){
		return CREAT_TIME;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
	
	public String getDELETE_FLAG(){
		return DELETE_FLAG;
	}
	
}
