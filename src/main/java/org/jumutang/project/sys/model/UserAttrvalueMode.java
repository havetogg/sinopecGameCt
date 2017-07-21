package org.jumutang.project.sys.model;

import java.io.Serializable;

public class UserAttrvalueMode implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3011079604611499717L;

	private String id;
	private String user_id;
	private String attr_id;
	private String attr_name;
	private String attr_val;
	private String delete_flag;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAttr_id() {
		return attr_id;
	}
	public void setAttr_id(String attr_id) {
		this.attr_id = attr_id;
	}
	public String getAttr_name() {
		return attr_name;
	}
	public void setAttr_name(String attr_name) {
		this.attr_name = attr_name;
	}
	public String getAttr_val() {
		return attr_val;
	}
	public void setAttr_val(String attr_val) {
		this.attr_val = attr_val;
	}
	public String getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
