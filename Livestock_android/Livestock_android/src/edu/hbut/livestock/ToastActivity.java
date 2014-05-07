package edu.hbut.livestock;

import android.app.Activity;
import android.widget.Toast;

/**
 * ����ʾ��Ϣ��֧��
 * 
 * @author Hang
 * 
 */
public abstract class ToastActivity extends Activity {

	public ToastActivity() {
		super();
	}

	/**
	 * ��ʾ�϶�ʱ�����ʾ
	 * 
	 * @param text
	 *            ��ʾ����
	 */
	public void showShortToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	/**
	 * ��ʾ�϶�ʱ�����ʾ
	 * 
	 * @param text
	 *            ��ʾ����
	 */
	public void showLongToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

}