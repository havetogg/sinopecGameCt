SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS T_SYSUSER;
DROP TABLE IF EXISTS T_SYS_MENU;




/* Create Tables */

-- 可管理后台系统的用户信息
CREATE TABLE T_SYSUSER
(
	ID int NOT NULL,
	LOGIN_NAME varchar(32) NOT NULL,
	PASSWORD varchar(32) NOT NULL,
	-- 1系统用户0普通用户
	USER_TYPE int NOT NULL COMMENT '1系统用户0普通用户',
	PRIMARY KEY (ID)
) COMMENT = '可管理后台系统的用户信息';


CREATE TABLE T_SYS_MENU
(
	ID int NOT NULL,
	NAME varchar(32) NOT NULL,
	URL varchar(256),
	PARENT_ID int,
	SHOW_ORDER int NOT NULL,
	USER_TYPE int NOT NULL,
	PRIMARY KEY (ID)
);


