package edu.hbut.livestock.http;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * 
 * @author Hang
 * 
 * @param <T>
 *            ��ʾ�����ݶ�Ӧ��ʵ����
 * @param <ID>
 *            ʵ�����Ӧ�ı������
 */
public interface RemoteProcedureCall<T, ID extends Serializable> {

	/**
	 * ���ݲ�ѯĬ�Ϸ�ҳ��С
	 */
	public static final int DEFAULT_PAGE_SIZE = 20;

	/**
	 * ��ѯʱĬ�Ͽ�ʼ��
	 */
	public static final int DEFAULT_PAGE_START = 0;

	public static final String ERROR_TOAST = "���ݷ���ʧ��";

	public static final String EMPTY_DATA = "û������";

	/**
	 * ��ӣ�ͬ������
	 * 
	 * @param t
	 *            Ҫ��ӵ�����
	 */
	public String add(T t) throws RequestException;

	/**
	 * �첽���
	 * 
	 * @param t
	 *            Ҫ��ӵ�����
	 * @param action
	 *            ��ӳɹ��󷵻ؽ������
	 */
	public void add(T t, AsyncCallback<String> action) throws RequestException;

	/**
	 * ��������ɾ������
	 * 
	 * @param id
	 *            ����
	 * @param ʱ��
	 *            ������ȷʵ����ͷ�ı���
	 * @return ɾ�����
	 */
	public String delete(ID id, Date headerDate) throws RequestException;

	/**
	 * �첽�ķ�ʽɾ��
	 * 
	 * @param id
	 * @param action
	 *            �������
	 */
	public void delete(ID id, Date headerDate, AsyncCallback<String> action)
			throws RequestException;

	/**
	 * ������������
	 * 
	 * @param id
	 *            ����
	 * @return ���Һ�Ķ���
	 */
	public T findById(ID id, Date headerDate) throws RequestException;

	/**
	 * �첽����ĳ����¼
	 * 
	 * @param id
	 *            ����
	 * @param action
	 *            �������
	 */
	public void findById(ID id, Date headerDate, AsyncCallback<T> action)
			throws RequestException;

	/**
	 * �������Բ���
	 * 
	 * @param property
	 *            ������
	 * @param value
	 *            ����ֵ
	 * @param start
	 *            ��ʼ��
	 * @param count
	 *            ������������
	 * @return ���ҽ������
	 */
	public List<T> findByProperty(String property, Serializable value,
			int start, int count, Date headerDate) throws RequestException;

	/**
	 * ���첽�ķ�ʽ����
	 * 
	 * @param property
	 * @param value
	 * @param start
	 * @param count
	 * @param action
	 *            ���ҽ������
	 */
	public void findByProperty(String property, Serializable value, int start,
			int count, Date headerDate, AsyncCallback<List<T>> action)
			throws RequestException;

	/**
	 * ��������
	 * 
	 * @param t
	 *            ��Ҫ���в��ҵ�����
	 * @param start
	 * @param count
	 * @return
	 */
	public List<T> findByProperties(T t, int start, int count)
			throws RequestException;

	/**
	 * ���������첽����
	 * 
	 * @param t
	 * @param start
	 * @param count
	 * @param action
	 * @return
	 */
	public void findByProperties(T t, int start, int count,
			AsyncCallback<List<T>> action) throws RequestException;

	/**
	 * ��ҳ�������м�¼
	 * 
	 * @param start
	 * @param count
	 * @return
	 */
	public List<T> list(int start, int count, Date date)
			throws RequestException;

	/**
	 * �첽��ҳ�������м�¼
	 * 
	 * @param start
	 * @param count
	 * @param action
	 * @return
	 */
	public void list(int start, int count, Date headerDate,
			AsyncCallback<List<T>> action) throws RequestException;

	/**
	 * ����
	 * 
	 * @param t
	 * @return
	 */
	public String update(T t) throws RequestException;

	/**
	 * �첽����
	 * 
	 * @param t
	 * @param action
	 */
	public void update(T t, AsyncCallback<String> action)
			throws RequestException;

	/**
	 * ��Ӷ����¼
	 * 
	 * @param ts
	 * @return
	 */
	public String addAll(List<T> ts) throws RequestException;

	/**
	 * �첽��Ӷ����¼
	 * 
	 * @param ts
	 * @param action
	 */
	public void addAll(List<T> ts, AsyncCallback<String> action)
			throws RequestException;

	/**
	 * ��һҳ
	 * 
	 * @param action
	 */
	public void nextPage(Date headerdate, AsyncCallback<List<T>> action);

	/**
	 * ��һҳ
	 * 
	 * @param action
	 */
	public void prePage(Date headerdate, AsyncCallback<List<T>> action);

	/**
	 * ����currentPage������ֵ��һ������Ϊ1
	 */
	public void resetCurrentPage();
}
