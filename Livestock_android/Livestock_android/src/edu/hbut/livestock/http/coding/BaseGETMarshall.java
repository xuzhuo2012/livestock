package edu.hbut.livestock.http.coding;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * 用于编码共同部分
 * 
 * @author Hang
 * 
 * @param <T>
 * @param <ID>
 */
public abstract class BaseGETMarshall<T, ID extends Serializable> implements
		Marshall<T, ID> {

	/**
	 * 日期格式化工具
	 */
	@SuppressLint("SimpleDateFormat")
	protected final static SimpleDateFormat FMT = new SimpleDateFormat("yyyy/MM/dd");

	@Override
	public String marshall(Date headerDate, int start, int count) {
		StringBuilder sb = new StringBuilder();
		sb.append(START_LINE);
		sb.append("=");
		sb.append(start);
		sb.append("&");
		sb.append(MAX_COUNT);
		sb.append("=");
		sb.append(count);
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	/**
	 * 通过属性与属性值生成请求参数序列
	 * 
	 * @param strs
	 *            属性与属性值，结果为：属性1，属性值1，属性2，属性值2...
	 * @return
	 */
	protected String toUri(String... strs) {
		if (strs == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strs.length; i += 2) {
			sb.append(strs[i]);
			sb.append("=");
			sb.append(strs[i + 1]);
			sb.append("&");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * 将表头中的时间添加到url
	 * 
	 * @param headerDate
	 *            表头中的时间
	 * @param sb
	 */
	protected static void appendHeaderDate(Date headerDate, StringBuilder sb) {
		if (headerDate != null) {
			sb.append('&');
			sb.append(HEADER_DATE);
			sb.append('=');
			sb.append(FMT.format(headerDate));
		}
	}

}
