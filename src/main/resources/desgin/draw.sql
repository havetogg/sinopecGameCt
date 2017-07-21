SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS T_NJPRIZE_FETCH;
DROP TABLE IF EXISTS T_PRIZE;




/* Create Tables */

CREATE TABLE T_NJPRIZE_FETCH
(
	-- 奖项领取主键
	ID int NOT NULL AUTO_INCREMENT COMMENT '奖项领取主键',
	-- 用户表的主key
	USER_ID int NOT NULL COMMENT '用户表的主key',
	-- 奖项主键
	PRIZE_ID int NOT NULL COMMENT '奖项主键',
	-- 订单号,用于支付
	ORDER_NO varchar(56) COMMENT '订单号,用于支付',
	-- 中奖时间
	DRAW_TIME datetime NOT NULL COMMENT '中奖时间',
	-- 领奖时间
	FETC_TIME datetime COMMENT '领奖时间',
	-- 0:没有支付  1:已经支付
	PAY_STATU int DEFAULT 0 COMMENT '0:没有支付  1:已经支付',
	-- 支付时间
	PAY_TIME datetime COMMENT '支付时间',
	-- 兑换状态 0:未兑换 1 已经兑换
	CHANGE_TYPE int DEFAULT 0 COMMENT '兑换状态 0:未兑换 1 已经兑换',
	-- 兑换时间
	CHANGE_TIME datetime COMMENT '兑换时间',
	-- 启用状态
	STATUS varchar(1) NOT NULL COMMENT '启用状态',
	DELETE_FLAG varchar(1),
	PRIMARY KEY (ID),
	UNIQUE (ID)
);


CREATE TABLE T_PRIZE
(
	-- 奖项主键
	ID int NOT NULL AUTO_INCREMENT COMMENT '奖项主键',
	-- 奖项设置
	PRIZESET varchar(128) NOT NULL COMMENT '奖项设置',
	-- 奖品名称
	PRIZENAME varchar(128) NOT NULL COMMENT '奖品名称',
	-- 奖品数量
	PRIZENUM int NOT NULL COMMENT '奖品数量',
	-- TYPE1的中奖概率
	TYPE1_PRIZEPROBABILITY varchar(56) DEFAULT '0' NOT NULL COMMENT 'TYPE1的中奖概率',
	-- 类型2的中奖概率
	TYPE2_PRIZEPROBABILITY varchar(56) DEFAULT '0' NOT NULL COMMENT '类型2的中奖概率',
	-- TYPE3的中奖概率
	TYPE3_PRIZEPROBABILITY varchar(56) DEFAULT '0' NOT NULL COMMENT 'TYPE3的中奖概率',
	-- TYPE4的中奖概率
	TYPE4_PRIZEPROBABILITY varchar(56) DEFAULT '0' COMMENT 'TYPE4的中奖概率',
	-- TYPE5的中奖概率
	TYPE5_PRIZEPROBABILITY varchar(56) DEFAULT '0' NOT NULL COMMENT 'TYPE5的中奖概率',
	-- 已经领取数量
	FETCH_NUM int COMMENT '已经领取数量',
	-- 启用状态
	STATUS varchar(1) NOT NULL COMMENT '启用状态',
	-- 显示顺序
	SHOW_ORDER int NOT NULL COMMENT '显示顺序',
	-- 删除状态
	DELETE_FLAG varchar(1) NOT NULL COMMENT '删除状态',
	-- 领取方式
	FETCH_WAY varchar(1) COMMENT '领取方式',
	PRIMARY KEY (ID),
	UNIQUE (ID)
);

create table t_virtual_user (

	ID int NOT NULL AUTO_INCREMENT COMMENT '虚拟用户主键',
	NICK_NAME varchar(40) not null COMMENT '虚拟用户昵称',
	ACTION varchar(40)	COMMENT '虚拟用户行为'
);

