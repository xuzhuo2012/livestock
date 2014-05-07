package edu.hbut.livestock.http.coding;

import java.io.Serializable;
import java.sql.Date;

/**
 * ���ڽ�����������ݱ����URI
 * 
 * @author Hang
 * 
 */
public interface Marshall<T, ID extends Serializable> {
	
	/**
	 * ���ҵ��ĵ�һ�еĲ�����
	 */
	public static final String START_LINE = "startline";
	
	/**
	 * �����ҵ������Ĳ�����
	 */
	public static final String MAX_COUNT = "maxcount";
	
	/**
	 * ��ͷ����������ص���Ϣ�Ĳ�����
	 */
	public static final String HEADER_DATE = "headerDate";

	/**
	 * ����
	 * 
	 * @return ������
	 */
	String marshall(T t);

	/**
	 * ��������Ϣ����
	 * 
	 * @param id
	 *            ����
	 * @return ������
	 */
	String marshallId(ID id);

	/**
	 * ����������
	 * 
	 */
	String marshall(ID id, Date headerDate, int start, int count);
	
	String marshall(ID id, Date headerDate);

	/**
	 * �������ұ���
	 * 
	 * @param t
	 * @param date
	 * @param is
	 * @return
	 */
	String marshall(T t, int start, int count);
	
	String marshall(Date headerDate, int start, int count);
}
