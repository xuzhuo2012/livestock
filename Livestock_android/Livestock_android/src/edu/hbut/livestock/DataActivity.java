package edu.hbut.livestock;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import edu.hbut.livestock.http.AsyncCallback;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
import edu.hbut.livestock.util.EntityViewAdapter;
import edu.hbut.livestock.util.Tips;

/**
 * ��ʾ���������ݲ������ڵ�Activity�Ļ�������
 * 
 * @author Hang
 * 
 * @param <T>
 *            ʵ����
 * @param <ID>
 *            ʵ�����Ӧ�ı������
 */
public abstract class DataActivity<T, ID extends Serializable> extends ToastActivity {
	/**
	 * ������ʾ���
	 */
	protected ListView listView;

	/**
	 * ListView������������
	 */
	protected EntityViewAdapter<T> adapter;

	/**
	 * Զ�̵���
	 */
	protected RemoteProcedureCall<T, ID> call;
	/**
	 * ɾ���������Ŀ�����Ĳ˵�
	 */
	protected ItemOperateWindow itemOperatorWindow;
	/**
	 * ������������룬��������AddFeedActivityʱ��ȡ���ؽ��
	 */
	public static final int ADD_REQUEST_CODE = 0;
	/**
	 * ��ӳɹ������
	 */
	public static final int ADD_SUCCESS_RESULT_CODE = 1;
	/**
	 * ���ʧ�ܽ����
	 */
	public static final int ADD_ERROR_RESULT_CODE = 2;
	/**
	 * �������������룬��������UpdateFeedActivityʱ��ȡ���ؽ��
	 */
	public static final int UPDATE_REQUEST_CODE = 1;
	/**
	 * ���³ɹ������
	 */
	public static final int UPDATE_SUCCESS_RESULT_CODE = 1;
	/**
	 * ����ʧ�ܽ����
	 */
	public static final int UPDATE_ERROR_RESULT_CODE = 2;

	/**
	 * ʵ�������Ϣ�����ڳ�ʼ����������
	 */
	private Class<T> c;

	/**
	 * ListView�������Դid
	 */
	private int listViewId;

	/**
	 * �����ļ���Դid
	 */
	private int layoutId;

	/**
	 * ������
	 * 
	 * @param c
	 *            ʵ�����Ӧ��Class��Ķ���
	 * @param listViewId
	 *            ��ʾ���б����Դid
	 */
	public DataActivity(Class<T> c, int listViewId, int layoutId) {
		this.c = c;
		this.listViewId = listViewId;
		this.layoutId = layoutId;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layoutId);
		listView = (ListView) findViewById(listViewId);
		/*
		 * ��listview��ӳ����¼�
		 */
		adapter = (EntityViewAdapter<T>) ObjectConfigedFactory
				.getVeiwAdapterFactory().getBean(c);
		adapter.setContext(this);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new ListViewItemClickListener());

		itemOperatorWindow = new ItemOperateWindow();
		call = (RemoteProcedureCall<T, ID>) ObjectConfigedFactory
				.getRemoteProcedureCallFactory().getBean(c);
		initData();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*
		 * ���
		 */
		if (item.getTitle().equals(
				getResources().getString(R.string.action_add))) {
			onOptionAddItemSelected(item);
		}
		/*
		 * ����
		 */
		if (item.getTitle().equals(
				getResources().getString(R.string.action_refresh))) {
			onOptionRefreshItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * ��ʼ�����ݣ�Ĭ����ʾ�������ݵ�һҳ
	 */
	protected void initData() {
		call.list(0, 25, new Date(System.currentTimeMillis()),
				new AsyncCallback<List<T>>() {

					@Override
					public void call(List<T> t) {
						if (t == null) {
							showLongToast(Tips.NETWORK_WRONG);
							return;
						}
						if (t.size() == 0) {
							showLongToast(Tips.NO_DATA);
							return;
						}
						/*
						 * ������ʾ
						 */
						adapter.setEntities(t);
						adapter.notifyDataSetChanged();
						listView.setAdapter(adapter);
						showLongToast(Tips.UPDATE_SUCCESS);
					}
				});
	}

	/**
	 * ��Ӳ˵�����󴥷��������������Ҫ������ӽ��棬 Ĭ��ʲô������
	 * 
	 * @param item
	 */
	protected void onOptionAddItemSelected(MenuItem item) {
	}

	/**
	 * ˢ�²˵������ִ�У�Ĭ�����³�ʼ��
	 * 
	 * @param item
	 */
	protected void onOptionRefreshItemSelected(MenuItem item) {
		initData();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (itemOperatorWindow != null && itemOperatorWindow.isShowing()) {
			itemOperatorWindow.dismiss();
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case ADD_REQUEST_CODE:
			handleAddResult(resultCode, data);
			break;
		case UPDATE_REQUEST_CODE:
			handleUpdateResult(resultCode, data);
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * ���֮��������
	 * 
	 * @param resultCode
	 *            ��ӽ����
	 * @param data
	 *            ��ͼ
	 */
	protected void handleAddResult(int resultCode, Intent data) {
		switch (resultCode) {
		case ADD_SUCCESS_RESULT_CODE:
			onAddSuccess(data);
			break;
		case ADD_ERROR_RESULT_CODE:
			onAddError(data);
			break;
		default:
			break;
		}
	}

	/**
	 * ��ӳɹ��󴥷���Ĭ��ʲô������
	 * 
	 * @param data
	 *            ��ͼ
	 */
	protected void onAddSuccess(Intent data) {
	}

	/**
	 * ���ʧ�ܺ󴥷���Ĭ��ʲô������
	 * 
	 * @param data
	 *            ��ͼ
	 */
	protected void onAddError(Intent data) {
	}

	/**
	 * ����֮��������
	 * 
	 * @param resultCode
	 *            �����
	 * @param data
	 *            ��ͼ
	 */
	protected void handleUpdateResult(int resultCode, Intent data) {
		switch (resultCode) {
		case UPDATE_SUCCESS_RESULT_CODE:
			onUpdateSuccess(data);
			break;
		case UPDATE_ERROR_RESULT_CODE:
			onUpdateError(data);
			break;
		default:
			break;
		}
	}

	/**
	 * ���³ɹ�����ã�Ĭ��ʲô������
	 * 
	 * @param data
	 *            ��ͼ
	 */
	protected void onUpdateSuccess(Intent data) {
	}

	/**
	 * ����ʧ�ܺ���ã�Ĭ��ʲô������
	 * 
	 * @param data
	 *            ��ͼ
	 */
	protected void onUpdateError(Intent data) {
	}

	/**
	 * ����ListView�е���Ŀʱ����������ѡ��ɾ�����߸���
	 * 
	 * @author Hang
	 * 
	 */
	public class ItemOperateWindow extends PopupWindow {

		/**
		 * ɾ�����ť
		 */
		private Button deleteButton;

		/**
		 * �������ť
		 */
		private Button updateButton;

		private T t;

		public ItemOperateWindow() {
			super(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			View view;
			this.setContentView(view = getLayoutInflater().inflate(
					R.layout.item_selected_list_view, null));
			deleteButton = (Button) view.findViewById(R.id.action_delete);
			updateButton = (Button) view.findViewById(R.id.action_update);

			deleteButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					ItemOperateWindow.this.dismiss();
					if (t == null) {
						return;
					}
					onDelete(t);
				}
			});

			updateButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					ItemOperateWindow.this.dismiss();
					if (t == null) {
						return;
					}
					startUpdateActivity(t);
				}

			});
			this.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.pig_menu));
			this.setOutsideTouchable(true);
		}

		public void setCurrentData(T t) {
			this.t = t;
		}

	}

	/**
	 * ִ��ɾ�����ݲ�����Ĭ��ʲôҲ����
	 * 
	 * @param t
	 *            Ҫɾ�������ݶ�Ӧ�Ķ���
	 */
	protected void onDelete(T t) {
	}

	/**
	 * ����ʱ�����,�����������½��沢������t���ݵ����½��棬Ĭ��ʲôҲ����
	 * 
	 * @param t
	 *            Ҫִ�еĸ��²���
	 */
	protected void startUpdateActivity(T t) {
	}

	/**
	 * ѡ��ListView�е���Ŀʱ�����ļ�����
	 * 
	 * @author Hang
	 * 
	 */
	public class ListViewItemClickListener implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
			itemOperatorWindow.setCurrentData(adapter.dataAt((int) id));
			itemOperatorWindow.showAsDropDown(view);
		}

	}

	/**
	 * ���½���
	 * 
	 * @param t
	 * @param result
	 */
	public void notifyDeleteChanged(T t, String result) {
		/*
		 * ������ʾ�����ݣ���ɾ���˵���Ŀ�ӽ�����ɾ��
		 */
		if (Tips.DELETE_SUCCESS.equals(result)) {
			adapter.getEntities().remove(t);
			adapter.notifyDataSetChanged();
		}
	}
}