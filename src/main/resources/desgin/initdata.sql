
INSERT INTO t_sysuser (ID, LOGIN_NAME, PASSWORD, USER_TYPE)
VALUES (-1, 'admin', '123123', 1);


INSERT INTO t_sys_menu (ID, NAME, URL, PARENT_ID, SHOW_ORDER, USER_TYPE)
VALUES (50, '系统管理', null, null, 10, 1);
INSERT INTO t_sys_menu (ID, NAME, URL, PARENT_ID, SHOW_ORDER, USER_TYPE)
VALUES (5004, '焦点图管理', 'backMng/banner/list.htm', 50, 10, 1);


// 触发器
DROP TRIGGER IF EXISTS t_njprize_fetch11;
CREATE TRIGGER t_njprize_fetch11
AFTER INSERT ON t_njprize_fetch
FOR EACH ROW
BEGIN
declare c INT;
set c =(select COUNT(*) from t_njprize_fetch where to_days(DRAW_TIME)=to_days(now()));
if(new.prize_id >0) THEN 

set @VAR1=(SELECT NICK_NAME FROM t_virtual_user ORDER BY rand() LIMIT 1);
set @VAR2=(SELECT action FROM t_virtual_user ORDER BY rand() LIMIT 1);

INSERT INTO t_jauser_prize (NICK_NAME, PRIZENAME, DRAW_TIME) VALUES (@VAR1, @VAR2,  now());


end if;
end;

ALTER TABLE `t_prize` ADD COLUMN `TOATAL_NUM`  int(11) NULL DEFAULT 0 AFTER `FETCH_WAY`;

