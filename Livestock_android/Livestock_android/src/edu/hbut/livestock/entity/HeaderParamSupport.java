package edu.hbut.livestock.entity;

import java.sql.Date;

/**
 * 定义获取表头中与时间相关的部分，这部分作为参数加入
 * 
 * @author Hang
 * 
 */
public interface HeaderParamSupport {

	/**
	 * 获取表头中与时间相关的部分
	 * 
	 * @return
	 */
	Date getHeaderDate();
	
	void setHeaderDate(Date date);
}
