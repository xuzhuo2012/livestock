package edu.hbut.livestock;

/**
 * 记录修改或者添加界面，用于输入数据
 * 
 * @author Hang
 * 
 */
public abstract class RecordOperateActivity<T> extends ToastActivity {

	public RecordOperateActivity() {
		super();
	}

	/**
	 * 初始化控件
	 */
	protected abstract void initWidget();

	/**
	 * 获取布局资源id
	 * 
	 * @return
	 */
	protected abstract int getLayoutResource();

	/**
	 * 提交数据时采取的操作
	 */
	protected abstract void request(T entity);

}