package edu.hbut.livestock.http.coding;

import java.util.List;

/**
 * 解码接口，将远程请求返回的结果字符串解析出需要的结果
 * 
 * @author Hang
 * 
 */
public interface Unmarshall<T> {
	
	/**
	 * 返回结果的第一层键的名称
	 */
	public static final String BASE_RESULT_NAME = "result";

	/**
	 * 对于更新操作，解码出更新是否成功的信息
	 * 
	 * @param result
	 *            请求返回的结果
	 * @return 操作是否成功相关的信息
	 */
	String unmarshallInfo(String result) throws MarshallException;

	/**
	 * 将查询操作返回的单个对象解析出来
	 * 
	 * @param result
	 * @return
	 * @throws MarshallException
	 */
	T unmarshallObject(String result) throws MarshallException;

	/**
	 * 将查询操作返回的对象集合解析出来
	 * 
	 * @param result
	 * @return
	 * @throws MarshallException
	 */
	List<T> unmarshallList(String result) throws MarshallException;
}
