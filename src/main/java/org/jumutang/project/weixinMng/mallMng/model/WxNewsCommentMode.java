package org.jumutang.project.weixinMng.mallMng.model;

import org.jumutang.project.tools.DateUtil;
import org.jumutang.project.tools.RelativeDateFormat;
import org.jumutang.project.tools.StringUtil;

public class WxNewsCommentMode{
	private String ID;
	private String USER_ID;
	private String NEWS_ID;
	private String CONTENT;
	private String CREATE_TIME;
	private String CREATE_TIME_HIS;
	private String AUDIT_FLAG;
	private String DELETE_FLAG;
	private String NEWSCOMMENT_NUB; //此评论的点赞数
	private String USERNUMB;   //用户对此评论的点赞状态

	private MallUserMode MALLUSERMODE;  // 评论人的信息
	
	
	public String getUSERNUMB() {
		return USERNUMB;
	}
	public void setUSERNUMB(String uSERNUMB) {
		USERNUMB = uSERNUMB;
	}
	public MallUserMode getMALLUSERMODE() {
		return MALLUSERMODE;
	}
	public void setMALLUSERMODE(MallUserMode mALLUSERMODE) {
		MALLUSERMODE = mALLUSERMODE;
	}
	public String getNEWSCOMMENT_NUB() {
		return NEWSCOMMENT_NUB;
	}
	public void setNEWSCOMMENT_NUB(String nEWSCOMMENT_NUB) {
		NEWSCOMMENT_NUB = nEWSCOMMENT_NUB;
	}

	public String getCREATE_TIME_HIS() {
		return CREATE_TIME_HIS;
	}
	public void setCREATE_TIME_HIS(String cREATE_TIME_HIS) {
		CREATE_TIME_HIS = cREATE_TIME_HIS;
	}
	public String getID(){
		return this.ID;
	}
	public void setID(String ID){
		this.ID=ID;
	}
	public String getUSER_ID(){
		return this.USER_ID;
	}
	public void setUSER_ID(String USER_ID){
		this.USER_ID=USER_ID;
	}
	public String getNEWS_ID(){
		return this.NEWS_ID;
	}
	public void setNEWS_ID(String NEWS_ID){
		this.NEWS_ID=NEWS_ID;
	}
	public String getCONTENT(){
		return this.CONTENT;
	}
	public void setCONTENT(String CONTENT){
		this.CONTENT=CONTENT;
	}
	public String getCREATE_TIME(){
		return this.CREATE_TIME;
	}
	public void setCREATE_TIME(String CREATE_TIME){
		this.CREATE_TIME=CREATE_TIME;
		
		if(!StringUtil.isEmpty(CREATE_TIME)){
			setCREATE_TIME_HIS(RelativeDateFormat.format(DateUtil.parse4yMdHmsS(CREATE_TIME)));
		}else{
			setCREATE_TIME_HIS("");
		}
		
		
	}
	public String getAUDIT_FLAG(){
		return this.AUDIT_FLAG;
	}
	public void setAUDIT_FLAG(String AUDIT_FLAG){
		this.AUDIT_FLAG=AUDIT_FLAG;
	}
	public String getDELETE_FLAG(){
		return this.DELETE_FLAG;
	}
	public void setDELETE_FLAG(String DELETE_FLAG){
		this.DELETE_FLAG=DELETE_FLAG;
	}
}
