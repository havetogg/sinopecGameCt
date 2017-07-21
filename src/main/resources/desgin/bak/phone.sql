SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS T_REGISTCODE;




/* Create Tables */

CREATE TABLE T_REGISTCODE
(
	ID int NOT NULL AUTO_INCREMENT,
	MOBILE varchar(16) NOT NULL,
	-- 验证码
	CODE varchar(16) NOT NULL COMMENT '验证码',
	-- 有效果时间
	EFFECTIVE_TIME datetime NOT NULL COMMENT '有效果时间',
	CREATE_TIME datetime NOT NULL,
	PRIMARY KEY (ID)
);


