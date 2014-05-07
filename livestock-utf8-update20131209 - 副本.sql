alter table livestock_type add column PARENT_TYPE_NAME varchar(20);
alter table statistics_product add column BIRTH int not null default 0;

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
			  'PRODUCE_DATE date,',
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


drop view if exists statistics_product_query_view;
create view statistics_product_query_view as 
select statistics_product.TIME, 
		statistics_product.LIVECOUNT, 
		statistics_product.CALLIN, 
		statistics_product.CALLOUT, 
		statistics_product.DEATH, 
		statistics_product.BIRTH,
		user_info.USER_NAME, 
		user_info.ADDRESS, 
		region.REGION_NAME, 
		livestock_type.LIVESTOCK_TYPE_NAME,
		livestock_type.PARENT_TYPE_NAME as PARENT_TYPE_NAME
from statistics_product left outer join livestock_type on statistics_product.TYPE = livestock_type.LIVESTOCK_TYPE_ID 
						left outer join region on statistics_product.REGION = region.REGIONID 
						left outer join user_info on statistics_product.USERID = user_info.USERID;


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
			  'MEDICINE_ID int,',
              'STANDARD varchar(255) not null,',
			  'foreign key (MEDICINE_ID) references medicine_', userid, '(ID)',
              ')default charset=utf8 ,ENGINE=INNODB');
select @table_name_ as sql_statement;
prepare create_ from @table_name_;
execute create_;
deallocate prepare create_;
end //
delimiter ;

drop procedure if exists add_feed_and_medicine_name_dictionary_to_every_user;
delimiter //
create procedure add_feed_and_medicine_name_dictionary_to_every_user()
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

drop procedure if exists on_medicine_record_update;
delimiter //
create procedure on_medicine_record_update(in start_year int, in count_year int)
begin
	declare user_id varchar(20);
	declare exception_num int;
	declare year_ int;
	declare cur_user cursor for select USERID from user;
	declare continue handler for not found set exception_num = 0;
	set exception_num = 1;
	open cur_user;
	user_loop:loop
		fetch cur_user into user_id;
		if exception_num = 0 then
			leave user_loop;
		end if;
		set year_ = start_year;
		while year_ < start_year + count_year do
			set @table_name_ = concat('alter table medicine_record_', year_, '_', user_id, ' add column PRODUCE_DATE date');	
			select @table_name_ as sql_statement;
			prepare create_ from @table_name_;
			execute create_;
			deallocate prepare create_;
			set year_ = year_ + 1;
		end while;
	end loop user_loop;
	close cur_user;
end //
delimiter ;

drop procedure if exists on_producing_record_update;
delimiter //
create procedure on_producing_record_update(in start_year int, in count_year int)
begin
	declare year_ int;
	declare month_ int;
	set year_ = start_year;
	while year_ < start_year + count_year do
		set month_ = 1;
		while (month_ <= 12) do
			set @table_name_ = concat('alter table producing_record_', year_, '_', month_, ' add column LIVESTOCK_TYPE varchar(20)');	
			select @table_name_ as sql_statement;
			prepare create_ from @table_name_;
			execute create_;
			deallocate prepare create_;
			set month_ = month_ + 1;
		end while;
		set year_ = year_ + 1;
	end while;
end //
delimiter ;

drop procedure if exists on_update_database;
delimiter //
create procedure on_update_database(in start_year int, in count_year int)
begin
	call add_feed_and_medicine_name_dictionary_to_every_user();
	call on_medicine_record_update(start_year, count_year);
	call on_producing_record_update(start_year, count_year);
end //
delimiter ;

call on_update_database(2013, 5);

alter table user add column ALIAS varchar(20);

drop table if exists report_count;
create table report_count (
	ID int key auto_increment,
	TIME varchar(10),
	REGION_NAME varchar(20),
	LIVESTOCK_TYPE varchar(20),
	PARENT_TYPE_NAME varchar(20),
	LIVECOUNT int,
	CALLIN int,
	CALLOUT int,
	DEATH int,
	BIRTH int
);

drop procedure if exists on_report_count;
delimiter //
create procedure on_report_count()
begin
	call calculate_year_month_statistics();
	insert into report_count(TIME, REGION_NAME, LIVESTOCK_TYPE, PARENT_TYPE_NAME, LIVECOUNT, CALLIN, CALLOUT, DEATH, BIRTH) 
		select statistics_product.TIME as TIME, 
			   region.REGION_NAME as REGION_NAME, 
			   livestock_type.LIVESTOCK_TYPE_NAME as LIVESTOCK_TYPE,
			   livestock_type.PARENT_TYPE_NAME as PARENT_TYPE_NAME,
			   sum(LIVECOUNT) as LIVECOUNT, 
			   sum(CALLIN) as CALLIN, 
			   sum(CALLOUT) as CALLOUT, 
			   sum(DEATH) as DEATH, 
			   sum(BIRTH) as BIRTH
		from statistics_product, region, livestock_type
		where region.REGIONID = statistics_product.region 
			and statistics_product.TYPE = livestock_type.LIVESTOCK_TYPE_ID 
			and statistics_product.TIME = DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -1 DAY),'%Y%c') 
		group by statistics_product.REGION, statistics_product.TYPE;
	insert into report_count(TIME, LIVESTOCK_TYPE, PARENT_TYPE_NAME, LIVECOUNT, CALLIN, CALLOUT, DEATH, BIRTH)
		select report_count.TIME as TIME, 
			   report_count.LIVESTOCK_TYPE as LIVESTOCK_TYPE,
			   report_count.PARENT_TYPE_NAME as PARENT_TYPE_NAME,
			   sum(LIVECOUNT) as LIVECOUNT, 
			   sum(CALLIN) as CALLIN, 
			   sum(CALLOUT) as CALLOUT, 
			   sum(DEATH) as DEATH, 
			   sum(BIRTH) as BIRTH
		from report_count
		where report_count.TIME = DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -1 DAY),'%Y%c') 
		group by report_count.LIVESTOCK_TYPE;
end //
delimiter ;


drop event if exists EVENT_ADD_FOR20_ENOUGH;
CREATE EVENT EVENT_ADD_FOR20_ENOUGH
ON SCHEDULE EVERY 1 Month
STARTS TIMESTAMP '2013-10-31 23:30:00'
DO call on_report_count(); 

drop procedure if exists create_time_tables;
delimiter //
create procedure create_time_tables(in year_start int, in year_count int)
begin
	declare year_ int;
	set year_ = year_start;
	while year_ < year_start + year_count do
		call create_year_month_quarantine_apply(year_);
		call create_year_month_antiepidemic(year_);
		set year_ = year_ + 1;
	end while;
	call create_year_immunization(year_start, year_count);
	call create_year_disinfect_record(year_start, year_count);
	call create_year_month_producing_record(year_start, year_count);	
end //
delimiter ;


drop procedure if exists create_user_tables;
delimiter //
create procedure create_user_tables()
begin
	declare year_ int;
	declare user_id varchar(20);
	declare ex_num int;
	declare date_now date;
	declare user_cursor cursor for select USERID from user;
	declare continue handler for not found set ex_num = 1;
	set date_now = NOW();
	set year_ = year(date_now);
	set ex_num = 0;
	
	open user_cursor;
	user_loop:loop
		fetch user_cursor into user_id;
		if ex_num = 1 then
			leave user_loop;
		end if;
		call create_year_userid_feeding_record(year_, 5, userid);
		call create_year_userid_livestock(year_, 5, userid);
		call create_year_userid_deth_process(year_, 5, userid);
		call create_year_userid_feed(year_, 5, userid);
		call create_year_userid_medicine_record(year_, 5, userid);
		call create_year_userid_diagnosis_record(year_, 5, userid);
	end loop user_loop;
	close user_cursor;
	call create_time_tables(year_, 5);
end //
delimiter ;

drop event if exists on_5_year_out;
CREATE EVENT on_5_year_out
ON SCHEDULE EVERY 5 Year
STARTS TIMESTAMP '2012-12-30 23:30:00'

DO call create_user_tables(); 


