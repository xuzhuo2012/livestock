package edu.hbut.livestock.http;

/**
 * �첽�ص��ӿ�
 * 
 * @author Hang
 * 
 * @param <T>
 *            �ص�������������������
 */
public interface AsyncCallback<T> {

	/**
	 * ���н������
	 * 
	 * @param t
	 *            ������
	 */
	void call(T result);
}
