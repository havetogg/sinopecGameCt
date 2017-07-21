package org.jumutang.project.sys.model;

/**
 * @author Administrator
 *
 */
public class UserDepartmentMode {
	
	private String WX_ID;     //	组织ID
	private String NAME;     //     名组织
	private String LEVEL;     //	层级
	public String getWX_ID() {
		return WX_ID;
	}
	public void setWX_ID(String wX_ID) {
		WX_ID = wX_ID;
	}
	public String getNAME() {
		if("2".equals(this.LEVEL)){
			NAME="--"+NAME;
		}
		if("3".equals(this.LEVEL)){
			NAME="----"+NAME;
		}
		if("4".equals(this.LEVEL)){
			NAME="------"+NAME;
		}
		if("5".equals(this.LEVEL)){
			NAME="--------"+NAME;
		}
		if("6".equals(this.LEVEL)){
			NAME="----------"+NAME;
		}
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getLEVEL() {
		return LEVEL;
	}
	public void setLEVEL(String lEVEL) {
		LEVEL = lEVEL;
	}


}
