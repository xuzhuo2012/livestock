package edu.hbut.livestock.util;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * ������ʾFeedManager�е�����
 * 
 * @author Hang
 * 
 * @param <T>
 *            ��Ҫ��ʾ�����ݵ����ͣ�һ��Ϊʵ����
 */
public abstract class EntityViewAdapter<T> extends MyBaseAdapter {

	@SuppressLint("SimpleDateFormat")
	protected static final SimpleDateFormat fmt = new SimpleDateFormat(
			"yyyy-MM-dd");

	@SuppressLint("SimpleDateFormat")
	protected static final SimpleDateFormat FMT_TIME_DATE = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * ������Ҫ��ʾ������
	 */
	private List<T> entities;

	private Context context;
	
	private List<T> selectedItems = new LinkedList<T>();

	public EntityViewAdapter() {
	}

	public EntityViewAdapter(List<T> entities, Context context) {
		super();
		this.entities = entities;
		this.context = context;
		if (context != null) {
			Collections.reverse(entities);
		}
	}

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		if (entities == null) {
			return 0;
		}
		return entities.size();
	}

	@Override
	public View getView(int id, View view, ViewGroup parent) {
		View contentView;
		int viewid = getEntityViewLayoutResourceId();
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		contentView = null;
		if (view == null) {
			contentView = inflater.inflate(viewid, null);
		} else {
			contentView = view;
		}
		T t = getEntities().get(id);
		if (t == null) {
			return view;
		}
		setViewContent(t, contentView);
		return contentView;
	}

	/**
	 * ����View�ϵ�����
	 */
	protected abstract void setViewContent(T t, View view);

	/**
	 * ��ȡ������ͼ����Դid
	 * 
	 * @return
	 */
	protected abstract int getEntityViewLayoutResourceId();

	/**
	 * ���ø���TextView��Ҫ��ʾ������
	 * 
	 * @param id
	 *            TextView��id
	 * @param text
	 *            ��Ҫ��ʾ���ַ���
	 * @param parent
	 *            �����
	 */
	protected void setTextOnView(int id, String text, View parent) {
		TextView textView = (TextView) parent.findViewById(id);
		textView.setText(text);
	}

	/**
	 * ��ȡid��Ӧ��ʵ�������
	 * 
	 * @param id
	 *            Adapter��ָ��View��id
	 * @return
	 */
	public T dataAt(int id) {
		if (entities == null) {
			return null;
		}
		return entities.get(id);
	}

	public List<T> getSelectedItems() {
		return selectedItems;
	}

	public boolean select(T e) {
		return selectedItems.add(e);
	}

	public boolean deselect(Object o) {
		return selectedItems.remove(o);
	}

	public void clear() {
		selectedItems.clear();
	}
	
}
