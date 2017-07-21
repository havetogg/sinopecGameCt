package org.jumutang.project.weixinMng.mallMng.model;



import java.io.Serializable;

/*
 * 商城用户的Bean
 */

public class ChangeMngMode implements Serializable {

	private static final long serialVersionUID = -5065336974131274470L;

    private String ID;
	
	private String CHANGE_NAME;
	
	private String DIAMOND_NUMB;
	
	private String YH_FLAG;
	
	private String MONEY;
	
	private String PAY_MONEY;
	
	private String SHOW_ORDER;
	
	private String CREAT_TIME;
	
	private String DELETE_FLAG;
	
	
	public void setID(String ID){
		this.ID=ID;
	}
	
	public String getID(){
		return ID;
	}
	public void setCHANGE_NAME(String CHANGE_NAME){
		this.CHANGE_NAME=CHANGE_NAME;
	}
	
	public String getCHANGE_NAME(){
		return CHANGE_NAME;
	}
	public void setDIAMOND_NUMB(String DIAMOND_NUMB){
		this.DIAMOND_NUMB=DIAMOND_NUMB;
	}
	
	public String getDIAMOND_NUMB(){
		return DIAMOND_NUMB;
	}
	public void setYH_FLAG(String YH_FLAG){
		this.YH_FLAG=YH_FLAG;
	}
	
	public String getYH_FLAG(){
		return YH_FLAG;
	}
	public void setMONEY(String MONEY){
		this.MONEY=MONEY;
	}
	
	public String getMONEY(){
		return MONEY;
	}
	public void setPAY_MONEY(String PAY_MONEY){
		this.PAY_MONEY=PAY_MONEY;
	}
	
	public String getPAY_MONEY(){
		return PAY_MONEY;
	}
	public void setSHOW_ORDER(String SHOW_ORDER){
		this.SHOW_ORDER=SHOW_ORDER;
	}
	
	public String getSHOW_ORDER(){
		return SHOW_ORDER;
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

