package edu.hbut.livestock.http.coding;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * ���ڱ��빲ͬ����
 * 
 * @author Hang
 * 
 * @param <T>
 * @param <ID>
 */
public abstract class BaseGETMarshall<T, ID extends Serializable> implements
		Marshall<T, ID> {

	/**
	 * ���ڸ�ʽ������
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
	 * ͨ������������ֵ���������������
	 * 
	 * @param strs
	 *            ����������ֵ�����Ϊ������1������ֵ1������2������ֵ2...
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
	 * ����ͷ�е�ʱ����ӵ�url
	 * 
	 * @param headerDate
	 *            ��ͷ�е�ʱ��
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
