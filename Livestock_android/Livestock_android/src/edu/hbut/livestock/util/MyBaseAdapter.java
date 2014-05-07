package edu.hbut.livestock.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * ������������ʵ�ָ����е�getItem��getItemId����
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
	 * ���ص�idĬ��Ϊ���ݵ�λ�ã�һ�����Ϊ������߼����е�λ��
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public abstract View getView(int id, View view, ViewGroup parent);

}
