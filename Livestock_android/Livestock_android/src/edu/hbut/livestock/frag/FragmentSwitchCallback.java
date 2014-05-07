package edu.hbut.livestock.frag;

import android.app.Fragment;
import edu.hbut.livestock.util.ModuleId;

/**
 * 用于Fragment与Activity间传递切换消息，用于在Fragment中切换当前的Fragment
 * 
 * @author Hang
 * 
 */
public interface FragmentSwitchCallback {

	/**
	 * 
	 * @param newFunction
	 *            需要切换到的功能模块名
	 * @param oldFunction
	 *            现在显示的功能模块名
	 */
	void execute(ModuleId newFunction, ModuleId oldFunction, Fragment currentFragment);
}
