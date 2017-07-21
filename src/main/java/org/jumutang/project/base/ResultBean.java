package org.jumutang.project.base;


/**
 * JSON响应的RESULT信息
 * @author Developer
 *
 */
public class ResultBean
{	
	/**
	 * 是否成功的标志位
	 */
	private boolean success=true;
	/**
	 * 消息的内容
	 */
	private String message;
	
	public static ResultBean newSuccessResult(){
		return new ResultBean(true);
	}
	
	public static ResultBean newSuccessResult(String message){
		return new ResultBean(true,message);
	}

	public static ResultBean newErrorResult(String message){
		return new ResultBean(false,message);
	}
	
	public ResultBean(boolean success)
	{
		super();
		this.success = success;
	}

	public ResultBean(boolean success,String message)
	{
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
	
	
}
