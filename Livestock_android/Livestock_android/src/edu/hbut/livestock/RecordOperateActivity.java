package edu.hbut.livestock;

/**
 * ��¼�޸Ļ�����ӽ��棬������������
 * 
 * @author Hang
 * 
 */
public abstract class RecordOperateActivity<T> extends ToastActivity {

	public RecordOperateActivity() {
		super();
	}

	/**
	 * ��ʼ���ؼ�
	 */
	protected abstract void initWidget();

	/**
	 * ��ȡ������Դid
	 * 
	 * @return
	 */
	protected abstract int getLayoutResource();

	/**
	 * �ύ����ʱ��ȡ�Ĳ���
	 */
	protected abstract void request(T entity);

}