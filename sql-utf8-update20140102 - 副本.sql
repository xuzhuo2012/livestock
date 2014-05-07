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
			set @table_name_ = CONCAT('select sum(change_count) into @birth_flag from producing_record_',DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -1 DAY),'%Y_%c'),' where change_type=\'出生\' and houseid=',house_id,' and userid=\'',user_id,'\'');
			prepare stmt from @table_name_;
			execute stmt;
			deallocate prepare stmt;
			set birth_flag = @birth_flag;
			if birth_flag IS NOT NULL then
				set birth_count=birth_count+birth_flag;
				set birth_flag=0;
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
		select sum(LIVESTOCK_COUNT) into ho_count from house where USERID=user_id and ANIMAL_TYPE = ho_type;
		insert into statistics_product(TIME, USERID, ADDRESS, REGION, TYPE, LIVECOUNT, CALLIN, CALLOUT, DEATH, BIRTH) values (DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -1 DAY),'%Y%m'),user_id,u_address,u_region,ho_type,ho_count,callin_count,callout_count,death_count, birth_count);
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
			and statistics_product.TIME = DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -1 DAY),'%Y%m') 
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
		where report_count.TIME = DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -1 DAY),'%Y%m') 
		group by report_count.LIVESTOCK_TYPE;
end //
delimiter ;
