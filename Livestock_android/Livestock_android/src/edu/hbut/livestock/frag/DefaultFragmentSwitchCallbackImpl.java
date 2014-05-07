package edu.hbut.livestock.frag;

import android.app.Fragment;
import edu.hbut.livestock.util.ModuleId;

/**
 * Ĭ�ϳ���ʵ��
 * 
 * @author Hang
 * 
 */
public abstract class DefaultFragmentSwitchCallbackImpl implements FragmentSwitchCallback {

	@Override
	public void execute(ModuleId newFunction, ModuleId oldFunction, Fragment currentFragment) {
		if (newFunction != null && oldFunction != null && ! newFunction.equals(oldFunction)) {
			switchFrag(newFunction, currentFragment);
		}
	}

	/**
	 * ������л�����
	 * 
	 * @param newFunction
	 */
	protected abstract void switchFrag(ModuleId newFunction, Fragment currentFragment);
}
