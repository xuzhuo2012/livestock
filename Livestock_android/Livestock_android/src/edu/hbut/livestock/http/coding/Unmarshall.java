package edu.hbut.livestock.http.coding;

import java.util.List;

/**
 * ����ӿڣ���Զ�����󷵻صĽ���ַ�����������Ҫ�Ľ��
 * 
 * @author Hang
 * 
 */
public interface Unmarshall<T> {
	
	/**
	 * ���ؽ���ĵ�һ���������
	 */
	public static final String BASE_RESULT_NAME = "result";

	/**
	 * ���ڸ��²���������������Ƿ�ɹ�����Ϣ
	 * 
	 * @param result
	 *            ���󷵻صĽ��
	 * @return �����Ƿ�ɹ���ص���Ϣ
	 */
	String unmarshallInfo(String result) throws MarshallException;

	/**
	 * ����ѯ�������صĵ��������������
	 * 
	 * @param result
	 * @return
	 * @throws MarshallException
	 */
	T unmarshallObject(String result) throws MarshallException;

	/**
	 * ����ѯ�������صĶ��󼯺Ͻ�������
	 * 
	 * @param result
	 * @return
	 * @throws MarshallException
	 */
	List<T> unmarshallList(String result) throws MarshallException;
}
