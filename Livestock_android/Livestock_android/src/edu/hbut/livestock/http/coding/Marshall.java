package edu.hbut.livestock.http.coding;

import java.io.Serializable;
import java.sql.Date;

/**
 * 用于将请求对象数据编码成URI
 * 
 * @author Hang
 * 
 */
public interface Marshall<T, ID extends Serializable> {
	
	/**
	 * 查找到的第一行的参数名
	 */
	public static final String START_LINE = "startline";
	
	/**
	 * 最大查找的行数的参数名
	 */
	public static final String MAX_COUNT = "maxcount";
	
	/**
	 * 表头中与日期相关的信息的参数名
	 */
	public static final String HEADER_DATE = "headerDate";

	/**
	 * 编码
	 * 
	 * @return 编码结果
	 */
	String marshall(T t);

	/**
	 * 将主键信息编码
	 * 
	 * @param id
	 *            主键
	 * @return 编码结果
	 */
	String marshallId(ID id);

	/**
	 * 编码多个参数
	 * 
	 */
	String marshall(ID id, Date headerDate, int start, int count);
	
	String marshall(ID id, Date headerDate);

	/**
	 * 条件查找编码
	 * 
	 * @param t
	 * @param date
	 * @param is
	 * @return
	 */
	String marshall(T t, int start, int count);
	
	String marshall(Date headerDate, int start, int count);
}
