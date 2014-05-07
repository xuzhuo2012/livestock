package edu.hbut.livestock;

import android.app.Activity;
import android.widget.Toast;

/**
 * 对提示信息的支持
 * 
 * @author Hang
 * 
 */
public abstract class ToastActivity extends Activity {

	public ToastActivity() {
		super();
	}

	/**
	 * 显示较短时间的提示
	 * 
	 * @param text
	 *            提示内容
	 */
	public void showShortToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示较短时间的提示
	 * 
	 * @param text
	 *            提示内容
	 */
	public void showLongToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

}