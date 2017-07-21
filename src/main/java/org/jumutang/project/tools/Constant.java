package org.jumutang.project.tools;

public class Constant {

	
	private Constant(){
	}
	// 订单的状态-------------start--------------
	public static final String TYPE_NOT_PAY = "0"; // 0:末支付

	/**
	 *  1已支付
	 */
	public static final String TYPE_PAYED = "1"; // 1已支付

	public static final String TYPE_USEED = "2"; // 2已消费

	public static final String TYPE_PAYRETURNBACK = "3"; // 3已退款
	
	public static final String TYPE_CANCEL = "4"; // 4已取消
	
	public static final String TYPE_GROUP = "5"; // 5拼团中
	// 订单的状态-------------end--------------

}
