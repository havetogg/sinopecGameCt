package org.jumutang.project.backMng.adverts.model;

public class AdvertsMngMode{

	private String ID;
	
	private String NAME;

	private String LINK_URL;
	
	private String BANNER_TYPE;
	
	private String IMAGE_URL;
	
	private String IN_USE_FLAG;
	
	private String SHOW_ORDER;
	
	private String DELETE_FLAG;
	
	
	public String getLINK_URL() {
		return LINK_URL;
	}

	public void setLINK_URL(String lINK_URL) {
		LINK_URL = lINK_URL;
	}

	public String getBANNER_TYPE() {
		return BANNER_TYPE;
	}

	public void setBANNER_TYPE(String bANNER_TYPE) {
		BANNER_TYPE = bANNER_TYPE;
	}
	public void setID(String ID){
		this.ID=ID;
	}
	
	public String getID(){
		return ID;
	}
	public void setNAME(String NAME){
		this.NAME=NAME;
	}
	
	public String getNAME(){
		return NAME;
	}
	public void setIMAGE_URL(String IMAGE_URL){
		this.IMAGE_URL=IMAGE_URL;
	}
	
	public String getIMAGE_URL(){
		return IMAGE_URL;
	}
	public void setIN_USE_FLAG(String IN_USE_FLAG){
		this.IN_USE_FLAG=IN_USE_FLAG;
	}
	
	public String getIN_USE_FLAG(){
		return IN_USE_FLAG;
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
