package edu.hbut.livestock.util;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

/**
 * 用于显示FeedManager中的数据
 * 
 * @author Hang
 * 
 * @param <T>
 *            需要显示的数据的类型，一般为实体类
 */
public abstract class TableEntityViewAdapter<T> extends EntityViewAdapter<T> {
	
	public TableEntityViewAdapter() {
	}
	
	public TableEntityViewAdapter(List<T> entities, Context context) {
		super(entities, context);
	}

	@Override
	public View getView(int id, View view, ViewGroup parent) {
		View content = super.getView(id, view, parent);
		((TableLayout) content).setStretchAllColumns(true);
		return content;
	}

}
