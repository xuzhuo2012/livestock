package edu.hbut.livestock.http;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;
import edu.hbut.livestock.http.coding.Marshall;
import edu.hbut.livestock.http.coding.Unmarshall;
import edu.hbut.livestock.util.Tips;

/**
 * ����ͨ���࣬����ʵ�ֳ�����ϸ�ڵĲ�֮ͬ��Ĳ��֣�������Ƿ��̰߳�ȫ��
 * 
 * @author Hang
 * 
 * @param <T>
 * @param <ID>
 */
public abstract class BaseRemoteProcedureCall<T, ID extends Serializable> implements RemoteProcedureCall<T, ID> {

	/**
	 * ����ӿڣ���������ǰ������Ĳ���������뵽url
	 */
	private Marshall<T, ID> marshall;

	/**
	 * ����ӿڣ��������������õ��Ľ���ַ�����������Ҫ�Ķ���
	 */
	private Unmarshall<T> unmarshall;

	/**
	 * ͨ�Ź��ߣ������ύԶ�̷���
	 */
	private CommunicationCall call;

	private int currentPage;

	public BaseRemoteProcedureCall(Marshall<T, ID> marshall,
			Unmarshall<T> unmarshall, CommunicationCall call) {
		super();
		this.marshall = marshall;
		this.unmarshall = unmarshall;
		this.call = call;
	}

	@Override
	public String add(T t) throws RequestException {
		String uri = marshall.marshall(t);// �����URI
		try {
			String result = call.queryByUri(getAddSource(), uri);// ����
			return unmarshall.unmarshallInfo(result);// ������
		} catch (Exception e) {
			throw new RequestException(e);
		}
	}

	/**
	 * ��ȡ�����������Ҫ���ʵ�Action��·������Servlet��·��
	 * 
	 * @return
	 */
	protected abstract String getAddSource();

	@Override
	public void add(final T t, final AsyncCallback<String> action) {
		AsyncTask<String, Integer, String> task = new AsyncTask<String, Integer, String>() {

			@Override
			protected String doInBackground(String... params) {
				try {
					return add(t);
				} catch (RequestException e) {
					// return Tips.NETWORK_WRONG;
					throw e;
				}
			}

			@Override
			protected void onPostExecute(String result) {
				action.call(result);
			}

		};
		task.execute();
	}

	@Override
	public String delete(ID id, Date date) {
		String uri;
		if (date == null) {
			uri = marshall.marshallId(id);
		} else {
			uri = marshall.marshall(id, date);
		}
		try {
			String result = call.queryByUri(getDeleteSource(), uri);
			return unmarshall.unmarshallInfo(result);
		} catch (Exception e) {
			throw new RequestException(e);
		}
	}

	protected abstract String getDeleteSource();

	@Override
	public void delete(final ID id, final Date date,
			final AsyncCallback<String> action) {
		new AsyncTask<String, Integer, String>() {

			@Override
			protected String doInBackground(String... params) {
				try {
					return delete(id, date);
				} catch (Exception e) {
					return Tips.NETWORK_WRONG;
				}
			}

			@Override
			protected void onPostExecute(String result) {
				action.call(result);
			}

		}.execute();
	}

	@Override
	public List<T> findByProperties(T t, int start, int count) {
		String uri = marshall.marshall(t, start, count);
		try {
			String result = call.queryByUri(getFindByPropertiesSource(), uri);
			return unmarshall.unmarshallList(result);
		} catch (Exception e) {
			throw new RequestException(e);
		}
	}

	/**
	 * ��ȡ����������Ҫ���ʵ���Դ
	 * 
	 * @return
	 */
	protected abstract String getFindByPropertiesSource();

	@Override
	public void findByProperties(final T t, final int start, final int count,
			final AsyncCallback<List<T>> action) throws RequestException {
		new AsyncTask<Object, Integer, List<T>>() {

			@Override
			protected List<T> doInBackground(Object... params) {
				try {
					return findByProperties(t, start, count);
				} catch (Exception e) {
					return null;
				}
			}

			@Override
			protected void onPostExecute(List<T> result) {
				action.call(result);
			}

		}.execute();
	}

	@Override
	public List<T> list(int start, int count, Date date)
			throws RequestException {
		String uri = marshall.marshall(date, start, count);
		try {
			String result = call.queryByUri(getListSource(), uri);
			List<T> list = unmarshall.unmarshallList(result);
			if (list == null || list.size() == 0) {
				return list;
			}
			currentPage = start / RemoteProcedureCall.DEFAULT_PAGE_SIZE + 1;
			return list;
		} catch (Exception e) {
			throw new RequestException(e);
		}
	}

	/**
	 * ��ѯ����������Դ
	 * 
	 * @return
	 */
	protected abstract String getListSource();

	@Override
	public T findById(ID id, Date date) throws RequestException {
		String uri;
		if (date == null) {
			uri = marshall.marshallId(id);
		} else {
			uri = marshall.marshall(id, date);
		}
		try {
			String result = call.queryByUri(getFindByIdSource(), uri);
			return unmarshall.unmarshallObject(result);
		} catch (Exception e) {
			throw new RequestException(e);
		}
	}

	/**
	 * ��ȡ��������·����
	 * 
	 * @return
	 */
	protected abstract String getFindByIdSource();

	@Override
	public void findById(final ID id, final Date date,
			final AsyncCallback<T> action) throws RequestException {
		new AsyncTask<Object, Integer, T>() {

			@Override
			protected T doInBackground(Object... params) {
				try {
					return findById(id, date);
				} catch (RequestException e) {
					return null;
				}
			}

			@Override
			protected void onPostExecute(T result) {
				action.call(result);
			}

		};
	}

	@Override
	public List<T> findByProperty(String property, Serializable value,
			int start, int count, Date date) throws RequestException {
		try {
			String result = call.queryByUri(getFindByPropertySource(), property
					+ "=" + value);
			Log.v("result", result);
			return unmarshall.unmarshallList(result);
		} catch (Exception e) {
			throw new RequestException(e);
		}
	}

	/**
	 * ��ȡ�������Բ��ҵ���Դ·��
	 * 
	 * @return
	 */
	protected abstract String getFindByPropertySource();

	@Override
	public void findByProperty(final String property, final Serializable value,
			final int start, final int count, final Date date,
			final AsyncCallback<List<T>> action) throws RequestException {
		new AsyncTask<Object, Integer, List<T>>() {

			@Override
			protected List<T> doInBackground(Object... params) {
				try {
					return findByProperty(property, value, start, count, date);
				} catch (RequestException e) {
					return null;
				}
			}

			@Override
			protected void onPostExecute(List<T> result) {
				action.call(result);
			}

		}.execute(new Object[] {});
	}

	@Override
	public void list(final int start, final int count, final Date date,
			final AsyncCallback<List<T>> action) {
		new AsyncTask<Object, Integer, List<T>>() {

			@Override
			protected List<T> doInBackground(Object... params) {
				try {
					return list(start, count, date);
				} catch (RequestException e) {
					return null;
					// throw e;
				}
			}

			@Override
			protected void onPostExecute(List<T> result) {
				action.call(result);
			}

		}.execute();
	}

	@Override
	public String update(T t) throws RequestException {
		String uri = marshall.marshall(t);
		try {
			String result = call.queryByUri(getUpdateSource(), uri);
			return unmarshall.unmarshallInfo(result);
		} catch (Exception e) {
			throw new RequestException(e);
		}
	}

	/**
	 * ��ȡ������Դ
	 * 
	 * @return
	 */
	protected abstract String getUpdateSource();

	@Override
	public void update(final T t, final AsyncCallback<String> action) {
		new AsyncTask<Object, Integer, String>() {

			@Override
			protected String doInBackground(Object... params) {
				try {
					return update(t);
				} catch (Exception e) {
					return Tips.NETWORK_WRONG;
				}
			}

			@Override
			protected void onPostExecute(String result) {
				action.call(result);
			}

		}.execute();
	}

	@Override
	public String addAll(List<T> ts) {
		return null;
	}

	@Override
	public void addAll(List<T> ts, AsyncCallback<String> action) {
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public void nextPage(Date headerdate, AsyncCallback<List<T>> action) {
		list(currentPage * DEFAULT_PAGE_SIZE, DEFAULT_PAGE_SIZE, headerdate,
				action);
	}

	@Override
	public void prePage(Date headerdate, AsyncCallback<List<T>> action) {
		if (currentPage <= 1) {
			action.call(new ArrayList<T>(0));
			return;
		}
		list((currentPage - 2) * DEFAULT_PAGE_SIZE, DEFAULT_PAGE_SIZE,
				headerdate, action);
	}

	@Override
	public void resetCurrentPage() {
		currentPage = 1;
	}

	public final Marshall<T, ID> getMarshall() {
		return marshall;
	}

	public final Unmarshall<T> getUnmarshall() {
		return unmarshall;
	}

	public final CommunicationCall getCall() {
		return call;
	}

}
