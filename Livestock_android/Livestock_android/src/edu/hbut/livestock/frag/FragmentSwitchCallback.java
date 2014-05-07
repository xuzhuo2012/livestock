package edu.hbut.livestock.frag;

import android.app.Fragment;
import edu.hbut.livestock.util.ModuleId;

/**
 * ����Fragment��Activity�䴫���л���Ϣ��������Fragment���л���ǰ��Fragment
 * 
 * @author Hang
 * 
 */
public interface FragmentSwitchCallback {

	/**
	 * 
	 * @param newFunction
	 *            ��Ҫ�л����Ĺ���ģ����
	 * @param oldFunction
	 *            ������ʾ�Ĺ���ģ����
	 */
	void execute(ModuleId newFunction, ModuleId oldFunction, Fragment currentFragment);
}
