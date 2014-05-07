package edu.hbut.livestock.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 用于显示主模块列表的适配器
 * 
 * @author Hang
 * 
 */
public class WorkViewAdapter extends MyBaseAdapter {

	private Context context;

	private static ModuleId[] moduleIds = { ModuleId.FEED_MANAGE,
			ModuleId.HOUSE_MANAGE, ModuleId.MEDICAL_MANAGE,
			ModuleId.USER_MANAGE, ModuleId.BASE_DATA_MANAGE };

	public WorkViewAdapter(Context context) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return moduleIds.length;
	}

	@Override
	public View getView(int i, View v, ViewGroup vg) {
		Button button;
		if (v == null) { 
			button = new Button(context);
			button.setPadding(8, 8, 8, 8);
		} else {
			button = (Button) v;
		}
		button.setText(moduleIds[i].getModuleName());
		return button;
	}

	public ModuleId getModuleId(int i) {
		return moduleIds[i];
	}

}
