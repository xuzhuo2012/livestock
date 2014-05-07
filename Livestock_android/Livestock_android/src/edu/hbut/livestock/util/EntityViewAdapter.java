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
 * 用于显示FeedManager中的数据
 * 
 * @author Hang
 * 
 * @param <T>
 *            需要显示的数据的类型，一般为实体类
 */
public abstract class EntityViewAdapter<T> extends MyBaseAdapter {

	@SuppressLint("SimpleDateFormat")
	protected static final SimpleDateFormat fmt = new SimpleDateFormat(
			"yyyy-MM-dd");

	@SuppressLint("SimpleDateFormat")
	protected static final SimpleDateFormat FMT_TIME_DATE = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 所有需要显示的数据
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
	 * 设置View上的内容
	 */
	protected abstract void setViewContent(T t, View view);

	/**
	 * 获取返回视图的资源id
	 * 
	 * @return
	 */
	protected abstract int getEntityViewLayoutResourceId();

	/**
	 * 设置各个TextView需要显示的数据
	 * 
	 * @param id
	 *            TextView的id
	 * @param text
	 *            需要显示的字符串
	 * @param parent
	 *            父组件
	 */
	protected void setTextOnView(int id, String text, View parent) {
		TextView textView = (TextView) parent.findViewById(id);
		textView.setText(text);
	}

	/**
	 * 获取id对应的实体类对象
	 * 
	 * @param id
	 *            Adapter中指定View的id
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
