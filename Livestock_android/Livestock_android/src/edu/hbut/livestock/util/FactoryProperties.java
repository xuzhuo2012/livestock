package edu.hbut.livestock.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 用于读取properties文件并返回结果
 * 
 * @author Hang
 * 
 */
public final class FactoryProperties {

	/**
	 * 从文件中读取结果
	 * 
	 * @param name
	 *            src下文件的路径名
	 * @return 文件中的键值对
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
