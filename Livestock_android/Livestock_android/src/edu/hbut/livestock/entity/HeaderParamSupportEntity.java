package edu.hbut.livestock.entity;

import java.sql.Date;

/**
 * ��ͷ���ı��Ӧ����ĳ���
 * 
 * @author Hang
 * 
 */
public abstract class HeaderParamSupportEntity implements HeaderParamSupport,java.io.Serializable {

	private static final long serialVersionUID = 5990537497788472714L;
	/**
	 * ���ڽ��ձ�ͷ����ʱ����ز��ֵĲ���
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
