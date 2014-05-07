package edu.hbut.livestock.frag;

import android.app.Fragment;
import edu.hbut.livestock.util.ModuleId;

/**
 * 默认抽象实现
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
	 * 具体的切换操作
	 * 
	 * @param newFunction
	 */
	protected abstract void switchFrag(ModuleId newFunction, Fragment currentFragment);
}
