package org.jumutang.project.sys.model;

import java.sql.Timestamp;

public class RoleMngMode{

	private Integer ID;
	
	private String NAME;
	
	private Integer CREATE_USERID;
	
	private Timestamp CREATE_TIME;
	
	private Integer DELETE_FLAG;
	
	private String CHECKED_NODE_IDS;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public Integer getCREATE_USERID() {
		return CREATE_USERID;
	}

	public void setCREATE_USERID(Integer cREATE_USERID) {
		CREATE_USERID = cREATE_USERID;
	}

	public Timestamp getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(Timestamp cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public Integer getDELETE_FLAG() {
		return DELETE_FLAG;
	}

	public void setDELETE_FLAG(Integer dELETE_FLAG) {
		DELETE_FLAG = dELETE_FLAG;
	}

	public String getCHECKED_NODE_IDS() {
		return CHECKED_NODE_IDS;
	}

	public void setCHECKED_NODE_IDS(String cHECKED_NODE_IDS) {
		CHECKED_NODE_IDS = cHECKED_NODE_IDS;
	}
	
	
}
