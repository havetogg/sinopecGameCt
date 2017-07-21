package org.jumutang.project.sys.model;

import java.util.ArrayList;
import java.util.List;

public class SysMenuMode {
	
	private int ID;
	
	private String NAME;
	
	private String URL;
	
	private Integer PARENT_ID;
	
	private Integer SHOW_ORDER;
	
	private Integer USER_TYPE;
	
	private List<SysMenuMode> subList=new ArrayList<SysMenuMode>();

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public Integer getPARENT_ID() {
		return PARENT_ID;
	}

	public void setPARENT_ID(Integer pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}

	public Integer getSHOW_ORDER() {
		return SHOW_ORDER;
	}

	public void setSHOW_ORDER(Integer sHOW_ORDER) {
		SHOW_ORDER = sHOW_ORDER;
	}

	public Integer getUSER_TYPE() {
		return USER_TYPE;
	}

	public void setUSER_TYPE(Integer uSER_TYPE) {
		USER_TYPE = uSER_TYPE;
	}

	public List<SysMenuMode> getSubList() {
		return subList;
	}

	public void setSubList(List<SysMenuMode> subList) {
		this.subList = subList;
	}

}
