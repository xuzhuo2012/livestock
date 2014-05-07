package edu.hbut.livestock.http.factory;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import edu.hbut.livestock.util.FactoryProperties;

/**
 * ���幤��������ͨ�������ļ����õĶ����Ӧ��ϵ��������
 * �����жԶ���Ĵ���ʹ�õ�����һ������Ӧ�Ķ���ֻ��һ��
 * 
 * @author Hang
 * 
 * @param <T>
 *            ��Ҫ�����Ķ��������
 */
public abstract class ObjectConfigedFactory {

	/**
	 * Զ�̵��ù���
	 */
	private static ObjectConfigedFactory remoteProcedureCallFactory;

	/**
	 * ����������
	 */
	private static ObjectConfigedFactory marshallFactory;

	/**
	 * ����������
	 */
	private static ObjectConfigedFactory unmarshallFactory;

	/**
	 * ��ͼ����������
	 */
	private static ObjectConfigedFactory viewAdapterFactory;

	/**
	 * ��ȡRemoteProcedureCallFactory
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
	 * ��ȡMarshallFactory
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
	 * ��ȡUnmarshallFactory
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
	 * �������������ڷ��ض���
	 * 
	 * @param c
	 *            ��Ҫ�����Ķ���ļ��������ļ��ж�Ӧ������
	 * @return
	 */
	public abstract Object getBean(Class<?> c);

	/**
	 * ��Χ������Ĭ��ʵ��
	 * 
	 * @author Hang
	 * 
	 * @param <T>
	 */
	static class DefaultConfigFactory extends ObjectConfigedFactory {

		/**
		 * ���ڴ�������ļ��еĶ�Ӧ��ϵ
		 */
		private Map<String, String> map;
		/**
		 * ���ڴ���Ѿ������Ķ���
		 */
		private Map<Class<?>, Object> objesm;

		/**
		 * ������
		 * 
		 * @param name
		 *            src�µ��ļ���
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
	 * RemoteProcedureCallFactory����
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
	 * Marshall����
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
	 * Unmarshall�ӿڹ���
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
	 * ��ͼ����������
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
