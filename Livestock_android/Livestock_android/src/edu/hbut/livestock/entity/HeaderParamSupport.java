package edu.hbut.livestock.entity;

import java.sql.Date;

/**
 * �����ȡ��ͷ����ʱ����صĲ��֣��ⲿ����Ϊ��������
 * 
 * @author Hang
 * 
 */
public interface HeaderParamSupport {

	/**
	 * ��ȡ��ͷ����ʱ����صĲ���
	 * 
	 * @return
	 */
	Date getHeaderDate();
	
	void setHeaderDate(Date date);
}
