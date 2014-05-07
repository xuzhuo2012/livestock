package edu.hbut.livestock.entity;

import java.sql.Date;

/**
 * 带头结点的表对应的类的超类
 * 
 * @author Hang
 * 
 */
public abstract class HeaderParamSupportEntity implements HeaderParamSupport,java.io.Serializable {

	private static final long serialVersionUID = 5990537497788472714L;
	/**
	 * 用于接收表头中与时间相关部分的参数
	 */
	private Date headerDate;
	
	public static final String HEADER_DATE = "headerDate";

	public Date getHeaderDate() {
		return headerDate;
	}

	public void setHeaderDate(Date headerDate) {
		this.headerDate = headerDate;
	}

}
