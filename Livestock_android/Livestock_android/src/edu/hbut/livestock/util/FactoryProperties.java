package edu.hbut.livestock.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * ���ڶ�ȡproperties�ļ������ؽ��
 * 
 * @author Hang
 * 
 */
public final class FactoryProperties {

	/**
	 * ���ļ��ж�ȡ���
	 * 
	 * @param name
	 *            src���ļ���·����
	 * @return �ļ��еļ�ֵ��
	 */
	public static Map<String, String> config(String name) {
		Map<String, String> map = new HashMap<String, String>();
		Properties properties = new Properties();
		InputStream in = null;
		try {
			properties.load(in = FactoryProperties.class.getResourceAsStream(name));
			Set<Object> e = properties.keySet();
			for (Object object : e) {
				String s = object.toString();
				map.put(s, properties.getProperty(s));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
}
