drop database if exists proj;
create database proj DEFAULT CHARACTER SET  utf8;
use proj;

drop table if exists region;
create table region (
	REGIONID int key auto_increment,
	REGION_NAME varchar(20) not null
);

drop table if exists livestock_type;
create table livestock_type (
	LIVESTOCK_TYPE_ID int key auto_increment,
	LIVESTOCK_TYPE_NAME varchar(20) not null
);

drop table if exists statistics_product;
create table statistics_product(
  ID int auto_increment,
  TIME varchar(10),
  USERID varchar(20),
  ADDRESS varchar(255),
  REGION varchar(50),
  TYPE varchar(20),
  LIVECOUNT int,
  CALLIN int,
  CALLOUT int,
  DEATH int,
  primary key(ID)
)default charset=utf8 ,ENGINE=INNODB;

drop table if exists department;
create table department (
    DEPARTMENT_ID int auto_increment,
  DEPARTMENT_NAME varchar(50),
  DEPARTMENT_ADDRESS varchar(255) not null,
  TELLPHONE varchar(20) not null,
  CONTACT_NAME varchar(50) not null,
  primary key(DEPARTMENT_ID,DEPARTMENT_NAME),
  INDEX DEPARTMENT_INDEX (DEPARTMENT_NAME)
)default charset= utf8 ,ENGINE=INNODB;


drop table if exists apply_contact;
create table apply_contact(
  ID int auto_increment,
  CONTACT_NAME varchar(50) not null,
  SEX varchar(50) not null,
  PHONE varchar(20) not null,
  AREA varchar(30) not null,
  primary key(ID)
)default charset=utf8 ,ENGINE=INNODB;

drop table if exists forbidden_medicine;
create table forbidden_medicine(
  ID int,
  MEDICINE_NAME varchar(255) not null,
  ANIMAL varchar(20) not null,
  FORBIDDEN_USAGE varchar(50) not null,
  primary key(ID)
)default charset=utf8 ,ENGINE=INNODB;

drop table if exists user_info;
create table user_info(
  USERID varchar(20),
  USER_NAME varchar(50),
  SEX varchar(50),
  PHONE varchar(20),
  ADDRESS varchar(255),
  POSTCODE char(6),
  EMAIL varchar(50),
  REGION int,
  primary key(USERID)
)default charset=utf8 ,ENGINE=INNODB;



drop table if exists admin;
create table admin(
  USERID varchar(20),
  PASSWORD varchar(20),
  primary key(USERID)
)default charset=utf8 ,ENGINE=INNODB;



drop table if exists user;
create table user(
  USERID varchar(20),
  USER_PASSWORD varchar(20),
  GROUPID varchar(20),
  USER_STATE varchar(20) default "待审",
  USER_PERMISSION varchar(1024),
  DEPARTMENT_NAME varchar(50),
  primary key (USERID)
)default charset=utf8 ,ENGINE=INNODB;

drop table if exists sys_module;
create table sys_module(
  MODULEID varchar(50),
  DISCRIPTION varchar(255),
  PARENT_MODULEID varchar(50),
  primary key(MODULEID)
)default charset=utf8 ,ENGINE=INNODB;

drop table if exists monitor_item;
create table monitor_item(
   ID int auto_increment,
  ITEMID varchar(50) ,
  primary key(ID,ITEMID),
  INDEX ITEMID_INDEX (ITEMID)
)default charset=utf8 ,ENGINE=INNODB;

drop table if exists user_group;
create table user_group(
  GROUPID varchar(20) key
)default charset=utf8 ,ENGINE=INNODB;

drop table if exists group_permission;
create table group_permission(
  GROUPID varchar(20),
  MODULEID varchar(50),
  primary key(GROUPID,MODULEID),
  foreign key(GROUPID) references user_group(GROUPID),
  foreign key(MODULEID) references sys_module(MODULEID)
)default charset=utf8 ,ENGINE=INNODB;

drop table if exists house;
create table house(
  HOUSEID int,
  USERID varchar(20),
  ANIMAL_TYPE int,
  LIVESTOCK_COUNT int,
  primary key(HOUSEID, USERID, ANIMAL_TYPE)
)default charset=utf8 ,ENGINE=INNODB;

alter table admin add constraint foreign key(USERID) references user_info(USERID);
alter table user add constraint foreign key(USERID) references user_info(USERID);
alter table user add constraint foreign key(GROUPID) references user_group(GROUPID);
alter table user add constraint foreign key(DEPARTMENT_NAME) references department(DEPARTMENT_NAME);
alter table user_info add constraint foreign key(REGION) references region(REGIONID);
alter table sys_module add constraint foreign key(PARENT_MODULEID) references sys_module(MODULEID);
alter table house add constraint foreign key(USERID) references user(USERID);
alter table house add constraint foreign key(ANIMAL_TYPE) references livestock_type(LIVESTOCK_TYPE_ID);

drop procedure  if exists create_year_month_quarantine_apply;
delimiter //
create procedure create_year_month_quarantine_apply(in year_ int)
begin
declare month_ int;
set month_=1;
while month_ <= 12 do
  set @table_name_ = concat('create table quarantine_apply_',year_, '_', month_, '(APPLY_DATE date, FLAG varchar(255), OPERATOR varchar(20), RESULT varchar(255), COUNT int, USERID varchar(20), primary key (APPLY_DATE, USERID), foreign key (USERID) references user(USERID), foreign key (OPERATOR) references user(USERID))default charset=utf8 ,ENGINE=INNODB');
  prepare create_ from @table_name_;
  select @table_name_ as sql_statement;
  execute create_;
  deallocate prepare create_;
  set month_=month_+1;
end while;
end //
delimiter ;


drop procedure  if exists create_year_month_antiepidemic;
delimiter //
create procedure create_year_month_antiepidemic(in year_ int)
begin
declare month_ int;
set month_=1;
while month_ <= 12 do
  set @table_name_ = concat('create table antiepidemic_',
                year_, '_', month_, 
                '(ANTIEPIDEMIC_DATE date,
                 HOUSEID int, SAMPLE_COUNT int,
                 MONITOR_ITEM varchar(50),
                 MONITOR_DEPARTMENT varchar(50),
                 MONITOR_RESULT varchar(255),
                 DEAL_RESULT varchar(255), 
                 USERID varchar(20) , 
                 primary key (ANTIEPIDEMIC_DATE, HOUSEID, MONITOR_ITEM, USERID), 
                 foreign key(MONITOR_ITEM) references monitor_item(ITEMID), 
                 foreign key (MONITOR_DEPARTMENT) references department(DEPARTMENT_NAME), 
                 foreign key(HOUSEID, USERID) references house(HOUSEID, USERID))default charset=utf8 ,ENGINE=INNODB');
  prepare create_ from @table_name_;
  select @table_name_ as sql_statement;
  execute create_;
  deallocate prepare create_;
  set month_=month_+1;
end while;
end //
delimiter ;


drop procedure  if exists create_year_immunization;
delimiter //
create procedure create_year_immunization(in year_start int, in n int)
begin
declare year_ int;
set year_=year_start;
while year_ < year_start + n do
  set @table_name_ = concat('create table immunization_', year_, '(',
                'USERID varchar(20), ',
                'IMMUNIZATION_TIME date, ',
                'HOUSEID int, ',
				'ITEMID int, ',
                'TOTAL_COUNT int, ',
                'VACCINE varchar(50), ',
                'VACCINE_FACTORY varchar(50), ',
                'BATCH_NUMBER varchar(50), ',
                'DOSAGE int, ',
                'IMMUNIZATION_METHOD varchar(255),',
                'primary key(USERID, IMMUNIZATION_TIME, HOUSEID),',
                'foreign key(HOUSEID, USERID) references house(HOUSEID, USERID),',
				'foreign key(ITEMID) references monitor_item(ID))default charset=utf8 ,ENGINE=INNODB');
  prepare create_ from @table_name_;
  select @table_name_ as sql_statement;
  execute create_;
  deallocate prepare create_;
  set year_=year_+1;
end while;
end //
delimiter ;


drop procedure  if exists create_year_disinfect_record;
delimiter //
create procedure create_year_disinfect_record(in year_start int, in n int)
begin
declare year_ int;
set year_=year_start;
while year_ < year_start + n do
  set @table_name_ = concat('create table disinfect_record_', year_, '(',
                'USERID varchar(20), ',
                'DISINFECT_PLACE varchar(255), ',
                'DISINFECT_MEDICINE_NAME varchar(50), ',
                'MEDICINE_FACTORY varchar(50), ',
                'DOSAGE int, ',
                'METHOD varchar(50), ',
                'OPERATOR varchar(50), ',
                'DISINFECT_DATE date, ',
                'primary key(USERID, DISINFECT_DATE),',
                'foreign key(USERID) references user(USERID))default charset=utf8 ,ENGINE=INNODB');
  prepare create_ from @table_name_;
  select @table_name_ as sql_statement;
  execute create_;
  deallocate prepare create_;
  set year_=year_+1;
end while;
end //
delimiter ;


drop procedure  if exists create_year_month_producing_record;
delimiter //
create procedure create_year_month_producing_record(in year_start int, in n int)
begin
declare year_ int;
declare month_ int;
set year_=year_start;
while year_ < year_start + n do
  set month_ = 1;
  while month_ <= 12 do
    set @table_name_ = concat('create table producing_record_', year_, '_', month_, '(',
                  'USERID varchar(20), ',
                  'HOUSEID int, ',
                  'CHANGE_TYPE varchar(10),',
                  'CHANGE_COUNT int,',
                  'CHANGE_DATE datetime,',
                  'primary key(USERID, HOUSEID, CHANGE_DATE),',
                  'foreign key(USERID) references user(USERID))default charset=utf8 ,ENGINE=INNODB');
    prepare create_ from @table_name_;
	select @table_name_ as sql_statement;
    execute create_;
	deallocate prepare create_;
    set month_ = month_ + 1;
  end while;
  set year_=year_+1;
end while;
end //
delimiter ;

drop procedure if exists drop_year_month_producing_record;
delimiter //
create procedure drop_year_month_producing_record(in year_start int, in n int)
begin
declare year_ int;
declare month_ int;
set year_=year_start;
while year_ < year_start + n do
  set month_ = 1;
  while month_ <= 12 do
    set @table_name_ = concat('drop table producing_record_', year_, '_', month_);
    prepare create_ from @table_name_;
	select @table_name_ as sql_statement;
    execute create_;
	deallocate prepare create_;
    set month_ = month_ + 1;
  end while;
  set year_=year_+1;
end while;
end //
delimiter ;

drop procedure if exists create_year_userid_feeding_record;
delimiter //
create procedure create_year_userid_feeding_record(in year_start int, in n int, in userid varchar(20))
begin
declare year_ int;
set year_ = year_start;
while year_ < year_start + n do
set @table_name_ = concat('create table feeding_record_', year_ , '_', userid, '(',
              'FEED_NAME varchar(50),',
              'FACTORY varchar(50),',
              'SELLER varchar(50),',
              'HOUSEID int,',
              'USERID varchar(20),',
              'START_TIME date,',
              'BATCH_NUMBER varchar(50),',
              'DOSAGE double,',
              'END_TIME date,',
              'primary key(FEED_NAME, FACTORY, HOUSEID, BATCH_NUMBER),',
              'foreign key(HOUSEID, USERID) references house(HOUSEID, USERID)',
              ')default charset=utf8 ,ENGINE=INNODB');
select @table_name_ as sql_statement;
prepare create_ from @table_name_;
execute create_;
deallocate prepare create_;
set year_ = year_ + 1;
end while;
end //
delimiter ;

drop procedure if exists create_year_userid_livestock;
delimiter //
create procedure create_year_userid_livestock(in year_start int, in n int, in userid varchar(20))
begin
declare year_ int;
set year_ = year_start;
while year_ < year_start + n do
set @table_name_ = concat('create table livestock_', year_, '_', userid, '(',
              'LIVESTOCKID varchar(50),',
              'CREATE_TIME date,',
              'LIVESTOCK_TYPE int,',
              'primary key(LIVESTOCKID),',
			  'foreign key(LIVESTOCK_TYPE) references LIVESTOCK_TYPE(LIVESTOCK_TYPE_ID)',
              ')default charset=utf8 ,ENGINE=INNODB');
prepare create_ from @table_name_;
select @table_name_ as sql_statement;
execute create_;
deallocate prepare create_;
set year_ = year_ + 1;
end while;
end//
delimiter ;

drop procedure if exists create_year_userid_deth_process;
delimiter //
create procedure create_year_userid_deth_process(in year_start int, in n int, in userid varchar(20))
begin
declare year_ int;
set year_ = year_start;
while year_ < year_start + n do
set @table_name_ = concat('create table deth_process_', year_ , '_', userid, '(',
              'PROCESS_DATE date,',
              'REASON varchar(255),',
              'DEPARTMENT_NAME varchar(50),',
              'SUM int,',
              'PROCESSMETHOD varchar(255),',
              'REMARK varchar(255),',
              'primary key(PROCESS_DATE),',
              'foreign key(DEPARTMENT_NAME) references department(DEPARTMENT_NAME)',
              ')default charset=utf8 ,ENGINE=INNODB');
prepare create_ from @table_name_;
select @table_name_ as sql_statement;
execute create_;
deallocate prepare create_;
set year_ = year_ + 1;
end while;
end //
delimiter ;


drop procedure if exists create_year_userid_diagnosis_record;
delimiter //
create procedure create_year_userid_diagnosis_record(in year_start int, in n int, in userid varchar(20))
begin
declare year_ int;
set year_ = year_start;
while year_ < year_start + n do
set @table_name_ = concat('create table diagnosis_record_', year_ , '_', userid, '(',
              'LIVESTOCKID varchar(50),',
              'AGE int,',
              'REASON varchar(255),',
              'DOCTOR varchar(50),',
              'MEDICINE varchar(50),',
              'METHOD varchar(50),',
              'RESULT varchar(50),',
              'DIAGNOSIS_DATE date,',
              'primary key(LIVESTOCKID, DIAGNOSIS_DATE),',
              'foreign key(LIVESTOCKID) references livestock_', year_, '_', userid, '(LIVESTOCKID)',
              ')default charset=utf8 ,ENGINE=INNODB');
prepare create_ from @table_name_;
select @table_name_ as sql_statement;
execute create_;
deallocate prepare create_;
set year_ = year_ + 1;
end while;
end //
delimiter ;



drop procedure if exists create_year_userid_feed;
delimiter //
create procedure create_year_userid_feed(in year_start int, in n int, in userid varchar(20))
begin
declare year_ int;
set year_ = year_start;
while year_ < year_start + n do
set @table_name_ = concat('create table feed_', year_ , '_', userid, '(',
              'FEED_NAME varchar(50),',
              'FACTORY varchar(255),',
              'BATCH_NUMBER varchar(50),',
              'CHANGE_TYPE varchar(10),',
              'CHANGE_COUNT double,',
              'CHANGE_DATE datetime,',
              'REMARK varchar(255),',
              'primary key(CHANGE_DATE)',
              ')default charset=utf8 ,ENGINE=INNODB');
prepare create_ from @table_name_;
select @table_name_ as sql_statement;
execute create_;
deallocate prepare create_;
set year_ = year_ + 1;
end while;
end //
delimiter ;

drop procedure if exists create_year_userid_medicine_record;
delimiter //
create procedure create_year_userid_medicine_record(in year_start int, in n int, in userid varchar(20))
begin
declare year_ int;
set year_ = year_start;
while year_ < year_start + n do
set @table_name_ = concat('create table medicine_record_', year_ , '_', userid, '(',
              'START_TIME date,',
              'PRODUCT_NAME varchar(50),',
              'FACTORY varchar(50),',
              'MEDICINE_TYPE varchar(50),',
              'STANDARD varchar(50),',
              'VALIDITY_PERIOD varchar(20),',
              'BATCH_NUMBER int,',
              'DOSAGE double,',
              'END_TIME date,',
              'REMARK varchar(255),',
              'primary key(START_TIME)',
              ')default charset=utf8 ,ENGINE=INNODB');
prepare create_ from @table_name_;
select @table_name_ as sql_statement;
execute create_;
deallocate prepare create_;
set year_ = year_ + 1;
end while;
end //
delimiter ;


call create_year_month_quarantine_apply(2013);
call create_year_month_quarantine_apply(2014);
call create_year_month_quarantine_apply(2015);
call create_year_month_quarantine_apply(2016);
call create_year_month_quarantine_apply(2017);

call create_year_month_antiepidemic(2013);
call create_year_month_antiepidemic(2014);
call create_year_month_antiepidemic(2015);
call create_year_month_antiepidemic(2016);
call create_year_month_antiepidemic(2017);

call create_year_immunization(2013, 5);

call create_year_disinfect_record(2013, 5);

call create_year_month_producing_record(2013, 5);

insert into sys_module (MODULEID)values('FEED_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('FEED', 'FEED_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('FEEDING', 'FEED_MANAGE');

insert into sys_module (MODULEID)values('HOUSE_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('HOUSE_INFO', 'HOUSE_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('DETH_PROCESSING', 'HOUSE_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('PRODUCING_RECORD', 'HOUSE_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('LIVESTOCK', 'HOUSE_MANAGE');

insert into sys_module (MODULEID)values('MEDICAL_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('QUARANTINE_APPLY', 'MEDICAL_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('MEDICINE_RECORD', 'MEDICAL_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('DISINFECT_RECORD', 'MEDICAL_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('IMMUNIZATION', 'MEDICAL_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('DIAGNOSIS_RECORD', 'MEDICAL_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('MONITOR_RECORD', 'MEDICAL_MANAGE');

insert into sys_module (MODULEID)values('USER_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('USER_INFO_MANAGE', 'USER_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('GROUP_MANAGE', 'USER_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('USER_PERMISSION', 'USER_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('GROUP_PERMISSION', 'USER_MANAGE');

insert into sys_module (MODULEID)values('BASE_DATA_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('APPLY_CONTACT', 'BASE_DATA_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('FORBIDDEN_MEDICINE', 'BASE_DATA_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('DEPARTMENT_MANAGE', 'BASE_DATA_MANAGE');
insert into sys_module (MODULEID, PARENT_MODULEID)values('MONITOR_ITEM_MANAGE', 'BASE_DATA_MANAGE');

drop procedure if exists on_register;
delimiter //
create procedure on_register(in year_start int, in n int, in userid varchar(20))
begin
call create_year_userid_feeding_record(year_start, n, userid);
call create_year_userid_livestock(year_start, n, userid);
call create_year_userid_deth_process(year_start, n, userid);
call create_year_userid_feed(year_start, n, userid);
call create_year_userid_medicine_record(year_start, n, userid);
call create_year_userid_diagnosis_record(year_start, n, userid);
end //
delimiter ;




insert into user_group values('ADMIN');
insert into user_group values('POWERUSER');
insert into user_group values('NORNALUSER');

drop procedure  if exists calculate_year_month_statistics;
delimiter //
create procedure calculate_year_month_statistics()
begin
declare u_address varchar(255);
declare u_region varchar(50);
declare h_house varchar(20);
DECLARE no_done,no_house INT DEFAULT 0;
DECLARE user_id VARCHAR(20);
DECLARE ho_type VARCHAR(20);
DECLARE ho_count,house_id,callin_count,callout_count,death_count, birth_count,callin_flag,callout_flag,death_flag, birth_flag int;
DECLARE cur_user CURSOR FOR select USERID from user;
DECLARE cur_house CURSOR FOR select distinct ANIMAL_TYPE from house where USERID=user_id;
DECLARE cur_houseid CURSOR FOR select houseid from house where USERID=user_id and ANIMAL_TYPE=ho_type;
DECLARE CONTINUE HANDLER FOR NOT FOUND  SET  no_done = 1;
OPEN cur_user;
user_loop:LOOP
	FETCH cur_user INTO user_id;
	if no_done=1 THEN
		LEAVE user_loop;
	end if;
	select address,region into u_address,u_region from user_info as ui where ui.userid=user_id;
	OPEN cur_house;
	house_loop:LOOP
		FETCH cur_house INTO ho_type;
		if no_done=1 THEN
			LEAVE house_loop;
		end if;
		set callin_count=0;
		set callout_count=0;
		set death_count=0;
		set birth_count = 0;
		OPEN cur_houseid;
		houseid_loop:LOOP
			FETCH cur_houseid INTO house_id;
			if no_done=1 THEN
				LEAVE houseid_loop;
			end if;
			select house_id,user_id;
			set @table_name_ = CONCAT('select sum(change_count) into @callin_flag from producing_record_',DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -1 DAY),'%Y_%c'),' where change_type=\'调入\' and houseid=',house_id,' and userid=\'',user_id,'\'');
			prepare stmt from @table_name_;
			execute stmt;
			deallocate prepare stmt;
			set callin_flag = @callin_flag;
			if callin_flag IS NOT NULL then
				set callin_count=callin_count+callin_flag;
				set callin_flag=0;
			end if;
			set @table_name_ = CONCAT('select sum(change_count) into @callin_flag from producing_record_',DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -1 DAY),'%Y_%c'),' where change_type=\'出生\' and houseid=',house_id,' and userid=\'',user_id,'\'');
			prepare stmt from @table_name_;
			execute stmt;
			deallocate prepare stmt;
			set death_flag = @callin_flag;
			if death_flag IS NOT NULL then
				set death_count=death_count+death_flag;
				set death_flag=0;
			end if;
			set @table_name_ = CONCAT('select sum(change_count) into @callout_flag from producing_record_',DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -1 DAY),'%Y_%c'),' where change_type=\'调出\' and houseid=',house_id,' and userid=\'',user_id,'\'');
			prepare stmt from @table_name_;
			execute stmt;
			deallocate prepare stmt;
			set callout_flag = @callout_flag;
			if callout_flag IS NOT NULL then
				set callout_count=callout_count+callout_flag;
				set callout_flag=0;
			end if;
			set @table_name_ = CONCAT('select sum(change_count) into @death_flag from producing_record_',DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -1 DAY),'%Y_%c'),' where change_type=\'死淘\' and houseid=',house_id,' and userid=\'',user_id,'\'');
			prepare stmt from @table_name_;
			execute stmt;
			deallocate prepare stmt;
			set death_flag = @death_flag;
			if death_flag IS NOT NULL then
				set death_count=death_count+death_flag;
				set death_flag=0;
			end if;
		END LOOP houseid_loop;
		CLOSE cur_houseid;
		select callin_count,callout_count,death_count;
		select sum(livestock_count) into ho_count from house where userid=user_id and animal_type=ho_type;
		insert into statistics_product(time,userid,address,region,type,livecount,callin,callout,death) values (DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -1 DAY),'%Y%m'),user_id,u_address,u_region,ho_type,ho_count,callin_count,callout_count,death_count);
		select ho_count;
		set no_done=0;
		set ho_type=null;
	END LOOP house_loop;
	CLOSE cur_house;
	set no_done=0;
END LOOP user_loop;
CLOSE cur_user;
end //
delimiter ;

CREATE EVENT EVENT_ADD_FOR20_ENOUGH
ON SCHEDULE EVERY 1 Month
STARTS TIMESTAMP '2013-10-01 02:00:00'
DO call calculate_year_month_statistics(); 

insert into region (REGION_NAME)values('湖工');
insert into user_info (USERID, USER_NAME, SEX, REGION)VALUES("ADMIN", "ADMIN", "男", 1);
insert into user (USERID, USER_PASSWORD, GROUPID, USER_STATE) values ("ADMIN", "ADMIN", "ADMIN", "活动");
insert into ADMIN VALUES("ADMIN", "ADMIN");
call on_register(2013, 5, 'ADMIN');














drop view if exists statistics_product_query_view;
create view statistics_product_query_view as 
select statistics_product.TIME, statistics_product.LIVECOUNT, statistics_product.CALLIN, statistics_product.CALLOUT, statistics_product.DEATH, user_info.USER_NAME, user_info.ADDRESS, region.REGION_NAME, livestock_type.LIVESTOCK_TYPE_NAME from statistics_product left outer join livestock_type on statistics_product.TYPE = livestock_type.LIVESTOCK_TYPE_ID left outer join region on statistics_product.REGION = region.REGIONID left outer join user_info on statistics_product.USERID = user_info.USERID;


drop procedure if exists create_userid_feed_name_and_medicine_and_medicine_name;
delimiter //
create procedure create_userid_feed_name_and_medicine_and_medicine_name(in userid varchar(20))
begin
set @table_name_ = concat('drop table if exists feed_name_', userid);
select @table_name_ as sql_statement;
prepare create_ from @table_name_;
execute create_;
deallocate prepare create_;
set @table_name_ = concat('create table feed_name_', userid, '(',
              'ID int key auto_increment,',
              'FEED_NAME varchar(255) not null',
              ')default charset=utf8 ,ENGINE=INNODB');
select @table_name_ as sql_statement;
prepare create_ from @table_name_;
execute create_;
deallocate prepare create_;
set @table_name_ = concat('drop table if exists medicine_', userid);
select @table_name_ as sql_statement;
prepare create_ from @table_name_;
execute create_;
deallocate prepare create_;
set @table_name_ = concat('create table medicine_', userid, '(',
              'ID int key auto_increment,',
              'MEDICINE_NAME varchar(255) not null,',
			  'PRODUCER varchar(255),',
			  'MEDICINE_TYPE varchar(255)',
              ')default charset=utf8 ,ENGINE=INNODB');
select @table_name_ as sql_statement;
prepare create_ from @table_name_;
execute create_;
deallocate prepare create_;
set @table_name_ = concat('drop table if exists medicine_standard_', userid);
select @table_name_ as sql_statement;
prepare create_ from @table_name_;
execute create_;
deallocate prepare create_;
set @table_name_ = concat('create table medicine_standard_', userid, '(',
              'ID int key auto_increment,',
              'STANDARD varchar(255) not null',
              ')default charset=utf8 ,ENGINE=INNODB');
select @table_name_ as sql_statement;
prepare create_ from @table_name_;
execute create_;
deallocate prepare create_;
end //
delimiter ;

drop procedure if exists add_new_data_dictionary_to_every_user;
delimiter //
create procedure add_new_data_dictionary_to_every_user()
begin
	declare user_id varchar(20);
	declare exception_num int;
	declare cur_user cursor for select USERID from user;
	declare continue handler for not found set exception_num = 0;
	set exception_num = 1;
	open cur_user;
	user_loop:loop
		fetch cur_user into user_id;
		if exception_num = 0 then
			leave user_loop;
		end if;
		if exception_num != 0 then
			call create_userid_feed_name_and_medicine_and_medicine_name(user_id);			
		end if;
	end loop user_loop;
	close cur_user;
end //
delimiter ;

drop procedure if exists on_register;
delimiter //
create procedure on_register(in year_start int, in n int, in userid varchar(20))
begin
call create_year_userid_feeding_record(year_start, n, userid);
call create_year_userid_livestock(year_start, n, userid);
call create_year_userid_deth_process(year_start, n, userid);
call create_year_userid_feed(year_start, n, userid);
call create_year_userid_medicine_record(year_start, n, userid);
call create_year_userid_diagnosis_record(year_start, n, userid);
call create_userid_feed_name_and_medicine_and_medicine_name(userid);
end //
delimiter ;
