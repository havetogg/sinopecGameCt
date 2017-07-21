package org.jumutang.project.sys.model;

/**
 * loginUserbean
 * @author Administrator
 *
 */
public class LoginMode {
	
	private String name;  // 部门名称。长度限制为1~64个字节，字符不能包括\:*?"<>｜  
	private String parentid;  // 父亲部门id。根部门id为1 

}
