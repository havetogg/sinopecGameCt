SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS T_CHANGE;
DROP TABLE IF EXISTS T_CHANGE_GOLD;
DROP TABLE IF EXISTS T_CHANGE_ORDER;
DROP TABLE IF EXISTS T_COLLECTION;
DROP TABLE IF EXISTS T_GAME;
DROP TABLE IF EXISTS T_LEVEL;
DROP TABLE IF EXISTS T_MALL_USER;
DROP TABLE IF EXISTS T_RANK;
DROP TABLE IF EXISTS T_SYSMSG;
DROP TABLE IF EXISTS T1_USER_GAME;




/* Create Tables */

CREATE TABLE T_CHANGE
(
	ID int NOT NULL,
	-- 充值名
	CHANGE_NAME varchar(512) COMMENT '充值名',
	-- 钻石个数
	DIAMOND_NUMB int DEFAULT 0 COMMENT '钻石个数',
	-- 优惠FLAG
	-- 1: 有优惠
	YH_FLAG varchar(1) DEFAULT '0' COMMENT '优惠FLAG
1: 有优惠',
	-- 金额
	MONEY decimal(10,2) DEFAULT 0 COMMENT '金额',
	-- 需要支付金额
	PAY_MONEY decimal(10,2) DEFAULT 0 COMMENT '需要支付金额',
	-- 顺序
	SHOW_ORDER int DEFAULT 0 COMMENT '顺序',
	-- DRAW_TIME
	CREAT_TIME datetime COMMENT 'DRAW_TIME',
	-- 0:没有删除 1:已删除
	DELETE_FLAG varchar(1) DEFAULT '0' COMMENT '0:没有删除 1:已删除',
	PRIMARY KEY (ID),
	UNIQUE (ID)
);


CREATE TABLE T_CHANGE_GOLD
(
	ID int NOT NULL,
	-- 充值名
	CHANGE_NAME varchar(512) COMMENT '充值名',
	-- 金币
	GOLD int DEFAULT 0 COMMENT '金币',
	-- 优惠FLAG
	-- 1: 有优惠
	YH_FLAG varchar(1) DEFAULT '0' COMMENT '优惠FLAG
1: 有优惠',
	-- 钻石个数
	DIAMOND_NUMB int DEFAULT 0 COMMENT '钻石个数',
	-- 支付钻石个数
	PAY_DIAMOND_NUMB int DEFAULT 0 COMMENT '支付钻石个数',
	-- 顺序
	SHOW_ORDER int DEFAULT 0 COMMENT '顺序',
	-- DRAW_TIME
	CREAT_TIME datetime COMMENT 'DRAW_TIME',
	-- 0:没有删除 1:已删除
	DELETE_FLAG varchar(1) DEFAULT '0' COMMENT '0:没有删除 1:已删除',
	PRIMARY KEY (ID),
	UNIQUE (ID)
);


CREATE TABLE T_CHANGE_ORDER
(
	ID int NOT NULL AUTO_INCREMENT,
	USER_ID int NOT NULL,
	-- 充值初始表的ID
	CHANGE_ID int COMMENT '充值初始表的ID',
	ORDER_NO varchar(32),
	DIAMOND_NUMB int DEFAULT 0,
	-- 金额
	MONEY decimal(10,2) DEFAULT 0 COMMENT '金额',
	-- DRAW_TIME
	CREAT_TIME datetime COMMENT 'DRAW_TIME',
	-- 0:待支付   1: 已经支付
	PAYED_FLAG int DEFAULT 0 COMMENT '0:待支付   1: 已经支付',
	-- 需要支付金额
	PAY_MONEY decimal(10,2) DEFAULT 0 COMMENT '需要支付金额',
	PAYED_TIME datetime,
	-- 0:没有删除 1:已删除
	DELETE_FLAG varchar(1) DEFAULT '0' COMMENT '0:没有删除 1:已删除',
	PRIMARY KEY (ID),
	UNIQUE (ID)
);


CREATE TABLE T_COLLECTION
(
	ID int NOT NULL AUTO_INCREMENT,
	USER_ID int,
	GAME_ID int,
	-- DRAW_TIME
	CREAT_TIME datetime COMMENT 'DRAW_TIME',
	-- 0:没有删除 1:已删除
	DELETE_FLAG varchar(1) DEFAULT '0' COMMENT '0:没有删除 1:已删除',
	PRIMARY KEY (ID),
	UNIQUE (ID)
);


CREATE TABLE T_GAME
(
	ID int NOT NULL,
	-- 游戏名
	GAME_NAME varchar(512) COMMENT '游戏名',
	GAME_DETAIL varchar(512),
	-- 图片的URL
	GAME_IMG_URL varchar(128) COMMENT '图片的URL',
	-- 游戏链接地址
	GAME_URL varchar(128) COMMENT '游戏链接地址',
	-- DRAW_TIME
	CREAT_TIME datetime COMMENT 'DRAW_TIME',
	-- 游戏的顺序
	SHOW_ORDER int DEFAULT 0 COMMENT '游戏的顺序',
	-- 0:没有删除 1:已删除
	DELETE_FLAG varchar(1) DEFAULT '0' COMMENT '0:没有删除 1:已删除',
	PRIMARY KEY (ID),
	UNIQUE (ID)
);


CREATE TABLE T_LEVEL
(
	ID int NOT NULL,
	-- 等级名
	LEVEL_NAME varchar(512) COMMENT '等级名',
	-- 开始的分数
	LEVEL_FROM int COMMENT '开始的分数',
	-- 每周返回的金币
	LEVEL_TO int COMMENT '每周返回的金币',
	-- DRAW_TIME
	CREAT_TIME datetime COMMENT 'DRAW_TIME',
	-- 0:没有删除 1:已删除
	DELETE_FLAG varchar(1) DEFAULT '0' COMMENT '0:没有删除 1:已删除',
	PRIMARY KEY (ID),
	UNIQUE (ID)
);


CREATE TABLE T_MALL_USER
(
	ID int NOT NULL AUTO_INCREMENT,
	OPEN_ID varchar(32),
	NICK_NAME varchar(128),
	HEAD_IMG varchar(512),
	MOBILE varchar(16),
	NAME varchar(32),
	-- 绑定时间
	CREAT_TIME datetime COMMENT '绑定时间',
	USED_DIAMOND int DEFAULT 0,
	-- 所得到的钻石
	ALL_DIAMOND int DEFAULT 0 COMMENT '所得到的钻石',
	USED_GOLD int DEFAULT 0,
	-- 所得到的金币
	ALL_GOLD int DEFAULT 0 COMMENT '所得到的金币',
	-- 自己充值的钻石数
	SELF_CHANGED_ALL_DIAMOND int DEFAULT 0 COMMENT '自己充值的钻石数',
	-- 用户经验值
	USER_LEVEL_SCORE int DEFAULT 0 COMMENT '用户经验值',
	USER_RANK_ID int DEFAULT 0,
	USER_MAX_RANK_ID int DEFAULT 0,
	THIRD_PART_ID varchar(32),
	-- 0:没有删除 1:已删除
	DELETE_FLAG varchar(1) DEFAULT '0' COMMENT '0:没有删除 1:已删除',
	PRIMARY KEY (ID),
	UNIQUE (ID),
	UNIQUE (OPEN_ID)
);


CREATE TABLE T_RANK
(
	ID int NOT NULL,
	-- 等级名
	RANK_NAME varchar(512) COMMENT '等级名',
	-- 每周返回的钻石
	RETURN_DIAMOND int DEFAULT 0 COMMENT '每周返回的钻石',
	-- 每周返回的金币
	RETURN_GOLD int DEFAULT 0 COMMENT '每周返回的金币',
	-- DRAW_TIME
	CREAT_TIME datetime COMMENT 'DRAW_TIME',
	-- 0:没有删除 1:已删除
	DELETE_FLAG varchar(1) DEFAULT '0' COMMENT '0:没有删除 1:已删除',
	PRIMARY KEY (ID),
	UNIQUE (ID)
);


CREATE TABLE T_SYSMSG
(
	ID int NOT NULL AUTO_INCREMENT,
	USER_ID int,
	MSG_TITLE varchar(128),
	MSG_DETAIL varchar(512),
	-- DRAW_TIME
	CREAT_TIME datetime COMMENT 'DRAW_TIME',
	-- 1:钻石 2:金币 3: 两都有
	TYPE int COMMENT '1:钻石 2:金币 3: 两都有',
	DIAMOND int DEFAULT 0,
	GOLD int DEFAULT 0,
	-- DRAW_TIME
	GET_TIME datetime COMMENT 'DRAW_TIME',
	-- 1:已经读
	READ_FLAG int DEFAULT 0 COMMENT '1:已经读',
	-- 读取时间
	READ_TIME datetime COMMENT '读取时间',
	-- 0:没有删除 1:已删除
	DELETE_FLAG varchar(1) DEFAULT '0' COMMENT '0:没有删除 1:已删除',
	PRIMARY KEY (ID),
	UNIQUE (ID)
);


CREATE TABLE T1_USER_GAME
(
	ID int NOT NULL AUTO_INCREMENT,
	-- 关联的用户表id
	USER_ID int NOT NULL COMMENT '关联的用户表id',
	GAME_ID int NOT NULL,
	-- 绑定时间
	CREAT_TIME datetime COMMENT '绑定时间',
	DAY_TIMES int DEFAULT 0,
	GAME_USED_TIMES int DEFAULT 0,
	GAME_ALL_TIMES int DEFAULT 0,
	WEEK_SCORE int DEFAULT 0,
	WEEK_GET_GOLD int DEFAULT 0,
	-- 上周奖励的钻石
	WEEK_GET_DIAMOND int DEFAULT 0 COMMENT '上周奖励的钻石',
	-- 上周游戏时间
	WEEK_TIME datetime COMMENT '上周游戏时间',
	-- 所得到的总分
	ALL_SCORE int DEFAULT 0 COMMENT '所得到的总分',
	-- 最后游戏时间
	LAST_GAME_TIME datetime COMMENT '最后游戏时间',
	-- 游戏的相关信息:json
	NOTE varchar(512) COMMENT '游戏的相关信息:json',
	-- 0:没有删除 1:已删除
	DELETE_FLAG varchar(1) DEFAULT '0' COMMENT '0:没有删除 1:已删除',
	PRIMARY KEY (ID),
	UNIQUE (ID)
);



