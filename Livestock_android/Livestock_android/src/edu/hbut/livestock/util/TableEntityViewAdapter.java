package edu.hbut.livestock.util;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

/**
 * ������ʾFeedManager�е�����
 * 
 * @author Hang
 * 
 * @param <T>
 *            ��Ҫ��ʾ�����ݵ����ͣ�һ��Ϊʵ����
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
