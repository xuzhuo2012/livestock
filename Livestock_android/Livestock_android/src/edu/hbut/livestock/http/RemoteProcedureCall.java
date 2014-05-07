package edu.hbut.livestock.http;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * 
 * @author Hang
 * 
 * @param <T>
 *            显示的数据对应的实体类
 * @param <ID>
 *            实体类对应的表的主键
 */
public interface RemoteProcedureCall<T, ID extends Serializable> {

	/**
	 * 数据查询默认分页大小
	 */
	public static final int DEFAULT_PAGE_SIZE = 20;

	/**
	 * 查询时默认开始行
	 */
	public static final int DEFAULT_PAGE_START = 0;

	public static final String ERROR_TOAST = "数据访问失败";

	public static final String EMPTY_DATA = "没有数据";

	/**
	 * 添加，同步任务
	 * 
	 * @param t
	 *            要添加的数据
	 */
	public String add(T t) throws RequestException;

	/**
	 * 异步添加
	 * 
	 * @param t
	 *            要添加的数据
	 * @param action
	 *            添加成功后返回结果处理
	 */
	public void add(T t, AsyncCallback<String> action) throws RequestException;

	/**
	 * 根据主键删除数据
	 * 
	 * @param id
	 *            主键
	 * @param 时间
	 *            ，用作确实还表头的表名
	 * @return 删除结果
	 */
	public String delete(ID id, Date headerDate) throws RequestException;

	/**
	 * 异步的方式删除
	 * 
	 * @param id
	 * @param action
	 *            结果处理
	 */
	public void delete(ID id, Date headerDate, AsyncCallback<String> action)
			throws RequestException;

	/**
	 * 根据主键查找
	 * 
	 * @param id
	 *            主键
	 * @return 查找后的对象
	 */
	public T findById(ID id, Date headerDate) throws RequestException;

	/**
	 * 异步查找某条记录
	 * 
	 * @param id
	 *            主键
	 * @param action
	 *            结果处理
	 */
	public void findById(ID id, Date headerDate, AsyncCallback<T> action)
			throws RequestException;

	/**
	 * 根据属性查找
	 * 
	 * @param property
	 *            属性名
	 * @param value
	 *            属性值
	 * @param start
	 *            起始行
	 * @param count
	 *            最多包含的行数
	 * @return 查找结果集合
	 */
	public List<T> findByProperty(String property, Serializable value,
			int start, int count, Date headerDate) throws RequestException;

	/**
	 * 以异步的方式查找
	 * 
	 * @param property
	 * @param value
	 * @param start
	 * @param count
	 * @param action
	 *            查找结果处理
	 */
	public void findByProperty(String property, Serializable value, int start,
			int count, Date headerDate, AsyncCallback<List<T>> action)
			throws RequestException;

	/**
	 * 条件查找
	 * 
	 * @param t
	 *            需要进行查找的属性
	 * @param start
	 * @param count
	 * @return
	 */
	public List<T> findByProperties(T t, int start, int count)
			throws RequestException;

	/**
	 * 根据条件异步查找
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
	 * 分页查找所有记录
	 * 
	 * @param start
	 * @param count
	 * @return
	 */
	public List<T> list(int start, int count, Date date)
			throws RequestException;

	/**
	 * 异步分页查找所有记录
	 * 
	 * @param start
	 * @param count
	 * @param action
	 * @return
	 */
	public void list(int start, int count, Date headerDate,
			AsyncCallback<List<T>> action) throws RequestException;

	/**
	 * 更新
	 * 
	 * @param t
	 * @return
	 */
	public String update(T t) throws RequestException;

	/**
	 * 异步更新
	 * 
	 * @param t
	 * @param action
	 */
	public void update(T t, AsyncCallback<String> action)
			throws RequestException;

	/**
	 * 添加多个记录
	 * 
	 * @param ts
	 * @return
	 */
	public String addAll(List<T> ts) throws RequestException;

	/**
	 * 异步添加多个记录
	 * 
	 * @param ts
	 * @param action
	 */
	public void addAll(List<T> ts, AsyncCallback<String> action)
			throws RequestException;

	/**
	 * 下一页
	 * 
	 * @param action
	 */
	public void nextPage(Date headerdate, AsyncCallback<List<T>> action);

	/**
	 * 上一页
	 * 
	 * @param action
	 */
	public void prePage(Date headerdate, AsyncCallback<List<T>> action);

	/**
	 * 重置currentPage变量的值，一般重置为1
	 */
	public void resetCurrentPage();
}
