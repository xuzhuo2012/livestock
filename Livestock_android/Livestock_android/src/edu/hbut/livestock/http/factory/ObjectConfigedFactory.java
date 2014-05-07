package edu.hbut.livestock.http.factory;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import edu.hbut.livestock.util.FactoryProperties;

/**
 * 定义工厂，用于通过属性文件配置的对象对应关系创建对象，
 * 工厂中对对象的创建使用单例，一个键对应的对象只有一个
 * 
 * @author Hang
 * 
 * @param <T>
 *            需要创建的对象的类型
 */
public abstract class ObjectConfigedFactory {

	/**
	 * 远程调用工厂
	 */
	private static ObjectConfigedFactory remoteProcedureCallFactory;

	/**
	 * 编码器工厂
	 */
	private static ObjectConfigedFactory marshallFactory;

	/**
	 * 解码器工厂
	 */
	private static ObjectConfigedFactory unmarshallFactory;

	/**
	 * 视图适配器工厂
	 */
	private static ObjectConfigedFactory viewAdapterFactory;

	/**
	 * 获取RemoteProcedureCallFactory
	 * 
	 * @return
	 */
	public static ObjectConfigedFactory getRemoteProcedureCallFactory() {
		if (remoteProcedureCallFactory == null) {
			synchronized (ObjectConfigedFactory.class) {
				if (remoteProcedureCallFactory == null) {
					remoteProcedureCallFactory = new RemoteProcedureCallFactory();
				}
			}
		}
		return remoteProcedureCallFactory;
	}

	/**
	 * 获取MarshallFactory
	 * 
	 * @return
	 */
	public static ObjectConfigedFactory getMarshallFactory() {
		if (marshallFactory == null) {
			synchronized (ObjectConfigedFactory.class) {
				if (marshallFactory == null) {
					marshallFactory = new MarshallFactory();
				}
			}
		}
		return marshallFactory;
	}

	/**
	 * 获取UnmarshallFactory
	 * 
	 * @return
	 */
	public static ObjectConfigedFactory getUnmarshallFactory() {
		if (unmarshallFactory == null) {
			synchronized (ObjectConfigedFactory.class) {
				if (unmarshallFactory == null) {
					unmarshallFactory = new UnmarshallFactory();
				}
			}
		}
		return unmarshallFactory;
	}
	
	public static ObjectConfigedFactory getVeiwAdapterFactory() {
		if (viewAdapterFactory == null) {
			synchronized (ObjectConfigedFactory.class) {
				if (viewAdapterFactory == null) {
					viewAdapterFactory = new ViewAdapterFactory();
				}
			}
		}
		return viewAdapterFactory;
	}

	/**
	 * 工厂方法，用于返回对象
	 * 
	 * @param c
	 *            需要创建的对象的键在属性文件中对应的类型
	 * @return
	 */
	public abstract Object getBean(Class<?> c);

	/**
	 * 外围工厂的默认实现
	 * 
	 * @author Hang
	 * 
	 * @param <T>
	 */
	static class DefaultConfigFactory extends ObjectConfigedFactory {

		/**
		 * 用于存放属性文件中的对应关系
		 */
		private Map<String, String> map;
		/**
		 * 用于存放已经创建的对象
		 */
		private Map<Class<?>, Object> objesm;

		/**
		 * 构造器
		 * 
		 * @param name
		 *            src下的文件名
		 */
		protected DefaultConfigFactory(String name) {
			map = FactoryProperties.config(name);
			objesm = new HashMap<Class<?>, Object>(map.size());
		}

		@Override
		public Object getBean(Class<?> c) {
			if (objesm.get(c) == null) {
				synchronized (this) {
					if (objesm.get(c) == null) {
						try {
							String name = map.get(c.getName());
							Log.v("name", name + "=============>" + c.getName());
							objesm.put(c, Class.forName(name).newInstance());
							map.remove(c.getName());
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return objesm.get(c);
		}

	}

	/**
	 * RemoteProcedureCallFactory工厂
	 * 
	 * @author Hang
	 * 
	 */
	private static final class RemoteProcedureCallFactory extends
			DefaultConfigFactory {

		RemoteProcedureCallFactory() {
			super("/RemoteCallFactory.properties");
		}

	}

	/**
	 * Marshall工厂
	 * 
	 * @author Hang
	 * 
	 */
	private static final class MarshallFactory extends DefaultConfigFactory {

		MarshallFactory() {
			super("/MarshallFactory.properties");
		}

	}

	/**
	 * Unmarshall接口工厂
	 * 
	 * @author Hang
	 * 
	 */
	private static final class UnmarshallFactory extends DefaultConfigFactory {

		protected UnmarshallFactory() {
			super("/UnmarshallFactory.properties");
		}

	}

	/**
	 * 视图适配器工厂
	 * 
	 * @author Hang
	 * 
	 */
	private static final class ViewAdapterFactory extends DefaultConfigFactory {

		protected ViewAdapterFactory() {
			super("/ViewAdapterFactory.properties");
		}

	}

}
