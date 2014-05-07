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
 * 显示数据与数据操作所在的Activity的基础父类
 * 
 * @author Hang
 * 
 * @param <T>
 *            实体类
 * @param <ID>
 *            实体类对应的表的主键
 */
public abstract class DataActivity<T, ID extends Serializable> extends ToastActivity {
	/**
	 * 数据显示组件
	 */
	protected ListView listView;

	/**
	 * ListView的内容适配器
	 */
	protected EntityViewAdapter<T> adapter;

	/**
	 * 远程调用
	 */
	protected RemoteProcedureCall<T, ID> call;
	/**
	 * 删除或更新条目弹出的菜单
	 */
	protected ItemOperateWindow itemOperatorWindow;
	/**
	 * 添加数据请求码，用于启动AddFeedActivity时获取返回结果
	 */
	public static final int ADD_REQUEST_CODE = 0;
	/**
	 * 添加成功结果码
	 */
	public static final int ADD_SUCCESS_RESULT_CODE = 1;
	/**
	 * 添加失败结果码
	 */
	public static final int ADD_ERROR_RESULT_CODE = 2;
	/**
	 * 更新数据请求码，用于启动UpdateFeedActivity时获取返回结果
	 */
	public static final int UPDATE_REQUEST_CODE = 1;
	/**
	 * 更新成功结果码
	 */
	public static final int UPDATE_SUCCESS_RESULT_CODE = 1;
	/**
	 * 更新失败结果码
	 */
	public static final int UPDATE_ERROR_RESULT_CODE = 2;

	/**
	 * 实体类的信息，用于初始化部分数据
	 */
	private Class<T> c;

	/**
	 * ListView组件的资源id
	 */
	private int listViewId;

	/**
	 * 布局文件资源id
	 */
	private int layoutId;

	/**
	 * 构造器
	 * 
	 * @param c
	 *            实体类对应的Class类的对象
	 * @param listViewId
	 *            显示的列表的资源id
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
		 * 给listview添加长按事件
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
		 * 添加
		 */
		if (item.getTitle().equals(
				getResources().getString(R.string.action_add))) {
			onOptionAddItemSelected(item);
		}
		/*
		 * 更新
		 */
		if (item.getTitle().equals(
				getResources().getString(R.string.action_refresh))) {
			onOptionRefreshItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 初始化数据，默认显示当月数据第一页
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
						 * 更新显示
						 */
						adapter.setEntities(t);
						adapter.notifyDataSetChanged();
						listView.setAdapter(adapter);
						showLongToast(Tips.UPDATE_SUCCESS);
					}
				});
	}

	/**
	 * 添加菜单点击后触发，这个方法中需要启动添加界面， 默认什么都不做
	 * 
	 * @param item
	 */
	protected void onOptionAddItemSelected(MenuItem item) {
	}

	/**
	 * 刷新菜单点击后执行，默认重新初始化
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
	 * 添加之后结果处理
	 * 
	 * @param resultCode
	 *            添加结果码
	 * @param data
	 *            意图
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
	 * 添加成功后触发，默认什么都不做
	 * 
	 * @param data
	 *            意图
	 */
	protected void onAddSuccess(Intent data) {
	}

	/**
	 * 添加失败后触发，默认什么都不做
	 * 
	 * @param data
	 *            意图
	 */
	protected void onAddError(Intent data) {
	}

	/**
	 * 更新之后结果处理
	 * 
	 * @param resultCode
	 *            结果码
	 * @param data
	 *            意图
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
	 * 更新成功后调用，默认什么都不做
	 * 
	 * @param data
	 *            意图
	 */
	protected void onUpdateSuccess(Intent data) {
	}

	/**
	 * 更新失败后调用，默认什么都不做
	 * 
	 * @param data
	 *            意图
	 */
	protected void onUpdateError(Intent data) {
	}

	/**
	 * 长按ListView中的条目时弹出，用于选择删除或者更新
	 * 
	 * @author Hang
	 * 
	 */
	public class ItemOperateWindow extends PopupWindow {

		/**
		 * 删除命令按钮
		 */
		private Button deleteButton;

		/**
		 * 更新命令按钮
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
	 * 执行删除数据操作，默认什么也不做
	 * 
	 * @param t
	 *            要删除的数据对应的对象
	 */
	protected void onDelete(T t) {
	}

	/**
	 * 更新时候调用,用于启动更新界面并将参数t传递到更新界面，默认什么也不做
	 * 
	 * @param t
	 *            要执行的更新操作
	 */
	protected void startUpdateActivity(T t) {
	}

	/**
	 * 选择ListView中的条目时触发的监听器
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
	 * 更新界面
	 * 
	 * @param t
	 * @param result
	 */
	public void notifyDeleteChanged(T t, String result) {
		/*
		 * 更新显示的数据，把删除了的条目从界面上删除
		 */
		if (Tips.DELETE_SUCCESS.equals(result)) {
			adapter.getEntities().remove(t);
			adapter.notifyDataSetChanged();
		}
	}
}