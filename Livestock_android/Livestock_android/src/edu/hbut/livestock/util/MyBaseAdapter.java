package edu.hbut.livestock.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 基础适配器，实现父类中的getItem和getItemId方法
 * 
 * @author Hang
 * 
 */
public abstract class MyBaseAdapter extends BaseAdapter {

	@Override
	public Object getItem(int position) {
		return position;
	}

	/**
	 * 返回的id默认为数据的位置，一般可以为数组或者集合中的位置
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public abstract View getView(int id, View view, ViewGroup parent);

}
