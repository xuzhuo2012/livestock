package edu.hbut.livestock.http;

/**
 * 异步回调接口
 * 
 * @author Hang
 * 
 * @param <T>
 *            回调方法参数的数据类型
 */
public interface AsyncCallback<T> {

	/**
	 * 进行结果处理
	 * 
	 * @param t
	 *            请求结果
	 */
	void call(T result);
}
