SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS T1_ATTRIBUTE_BUY;
DROP TABLE IF EXISTS T1_GAME_RECORD;
DROP TABLE IF EXISTS T1_USER_GAMETYPE;
DROP TABLE IF EXISTS T1_WEEKRANK_RETURN;




/* Create Tables */

CREATE TABLE T1_ATTRIBUTE_BUY
(
	ID int NOT NULL AUTO_INCREMENT,
	-- 关联的游戏用户id
	USER_GAME_ID int NOT NULL COMMENT '关联的游戏用户id',
	-- 4:新用户 5:老用户
	USER_GAMETYPE int DEFAULT 0 COMMENT '4:新用户 5:老用户',
	-- 1:游戏次数 2: 车位 3 :加油机 4:新手已阅读 5:老手已阅读
	TYPE int NOT NULL COMMENT '1:游戏次数 2: 车位 3 :加油机 4:新手已阅读 5:老手已阅读',
	-- 购买时间
	BUY_TIME datetime NOT NULL COMMENT '购买时间',
	-- 支付的钻石
	PAYED_DIAMOND int DEFAULT 0 COMMENT '支付的钻石',
	-- 支付的金币
	PAYED_GOLD int DEFAULT 0 COMMENT '支付的金币',
	-- 删除状态
	DELETE_FLAG varchar(1) DEFAULT '0' NOT NULL COMMENT '删除状态',
	PRIMARY KEY (ID)
);


CREATE TABLE T1_GAME_RECORD
(
	ID int NOT NULL AUTO_INCREMENT,
	-- 关联的游戏用户id
	USER_GAME_ID int NOT NULL COMMENT '关联的游戏用户id',
	GET_DIAMOND int DEFAULT 0,
	-- 获得收益金币
	GET_GOLD int DEFAULT 0 COMMENT '获得收益金币',
	-- GAS_TIMES
	GAS_TIMES int DEFAULT 0 COMMENT 'GAS_TIMES',
	-- 完美加油次数
	OPER_NICE int COMMENT '完美加油次数',
	-- 加油不完美次数
	OPER_NOT_NICE int DEFAULT 0 COMMENT '加油不完美次数',
	SCORE int DEFAULT 0,
	-- 获得的等级分数
	LEVEL_SCORE int DEFAULT 0 COMMENT '获得的等级分数',
	-- 游戏时间
	GAME_TIME datetime NOT NULL COMMENT '游戏时间',
	-- 删除状态
	DELETE_FLAG varchar(1) DEFAULT '0' NOT NULL COMMENT '删除状态',
	-- 4:新手模式   5:老手模式
	TYPE int DEFAULT 4 COMMENT '4:新手模式   5:老手模式',
	PRIMARY KEY (ID)
);


CREATE TABLE T1_USER_GAMETYPE
(
	ID int NOT NULL AUTO_INCREMENT,
	-- 关联的游戏用户id
	USER_GAME_ID int NOT NULL COMMENT '关联的游戏用户id',
	-- 4:新手模式 5:老手模式
	TYPE int NOT NULL COMMENT '4:新手模式 5:老手模式',
	-- 每天免费游戏次数
	DAY_TIMES int DEFAULT 0 COMMENT '每天免费游戏次数',
	-- 游戏玩过的次数
	GAME_USED_TIMES int DEFAULT 0 COMMENT '游戏玩过的次数',
	-- 总的游戏次数
	GAME_ALL_TIMES int DEFAULT 0 COMMENT '总的游戏次数',
	-- 删除状态
	DELETE_FLAG varchar(1) DEFAULT '0' NOT NULL COMMENT '删除状态',
	PRIMARY KEY (ID)
);


CREATE TABLE T1_WEEKRANK_RETURN
(
	ID int NOT NULL AUTO_INCREMENT,
	-- 关联的游戏用户id
	USER_GAME_ID int NOT NULL COMMENT '关联的游戏用户id',
	-- 1:钻石 2:金币 3: 两都有
	TYPE int DEFAULT 0 COMMENT '1:钻石 2:金币 3: 两都有',
	RETURN_DIAMOND int DEFAULT 0,
	-- 返回的金币
	RETURN_GOLD int DEFAULT 0 COMMENT '返回的金币',
	-- 上周的排名
	LASTE_WEEK_RANK int DEFAULT 0 COMMENT '上周的排名',
	-- 返回时间
	RETURN_TIME datetime NOT NULL COMMENT '返回时间',
	-- 删除状态
	DELETE_FLAG varchar(1) DEFAULT '0' NOT NULL COMMENT '删除状态',
	PRIMARY KEY (ID)
);



