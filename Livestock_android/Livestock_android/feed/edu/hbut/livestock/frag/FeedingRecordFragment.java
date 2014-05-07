package edu.hbut.livestock.frag;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import edu.hbut.livestock.AddFeedingActivity;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.FeedingRecord;
import edu.hbut.livestock.entity.FeedingRecordId;
import edu.hbut.livestock.http.AsyncCallback;
import edu.hbut.livestock.http.FeedingRemoteProcedureCall;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
import edu.hbut.livestock.util.ModuleId;
import edu.hbut.livestock.util.StaticDatas;
import edu.hbut.livestock.util.Tips;

/**
 * 
 * @author Hang
 * 
 */
public class FeedingRecordFragment extends
		EntityListViewFragment<FeedingRecord, FeedingRecordId> {

	private AsyncCallback<List<FeedingRecord>> action = new ListCallback();

	/**
	 * 查询结果处理
	 * 
	 * @author Hang
	 * 
	 */
	private final class ListCallback implements
			AsyncCallback<List<FeedingRecord>> {
		@Override
		public void call(List<FeedingRecord> result) {
			if (result == null) {
				showText(Tips.NETWORK_WRONG);
				return;
			}
			if (result.size() == 0) {
				showText("已经没有数据了");
				return;
			}
			setEntities(result);
			notifyDataSetChanged();
			showText(Tips.UPDATE_SUCCESS);
		}
	}

	@SuppressWarnings("unchecked")
	private RemoteProcedureCall<FeedingRecord, FeedingRecordId> call = (RemoteProcedureCall<FeedingRecord, FeedingRecordId>) ObjectConfigedFactory
			.getRemoteProcedureCallFactory().getBean(FeedingRecord.class);

	/**
	 * 年份选择器，用于查询
	 */
	private Spinner yearSelect;

	public FeedingRecordFragment() {
		super.setCall(call);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = buildView(inflater, container);

		buildModuleSelectSpinner(view);

		buildAddButtonEvent(view);

		buildRefreshButtonEvent(view);

		buildDeleteButtonEvent(view);

		buildPageButtonEvent(view);

		builderYearSelectSpinner(view);

		buildQueryButtonEvent(view);

		return view;
	}

	/**
	 * 查询按钮事件处理
	 * 
	 * @param view
	 */
	private void buildQueryButtonEvent(View view) {
		Button query = (Button) view.findViewById(R.id.query_feeding_button);
		query.setOnClickListener(new View.OnClickListener() {

			/**
			 * 查询条件输入对话框
			 */
			private Dialog queryDialog;

			private Calendar calendar;

			@Override
			public void onClick(View v) {
				if (queryDialog == null) {
					LayoutInflater inflater = (LayoutInflater) getAdapter()
							.getContext().getSystemService(
									Context.LAYOUT_INFLATER_SERVICE);
					View view = inflater.inflate(R.layout.feeding_query, null);
					final EditText in = (EditText) view
							.findViewById(R.id.query_feeding_house);
					queryDialog = new AlertDialog.Builder(getAdapter()
							.getContext())
							.setView(view)
							.setTitle(R.string.query_condition)
							.setPositiveButton(R.string.query,
									new OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											String i = in.getText().toString();
											if (i == null
													|| i.trim().equals("")) {
												showText("请输入圈舍号");
												return;
											}
											try {
												int no = Integer.parseInt(i);
												if (calendar == null) {
													calendar = Calendar
															.getInstance();
												}
												calendar.set(
														Calendar.YEAR,
														(Integer) yearSelect
																.getSelectedItem());
												((FeedingRemoteProcedureCall) call).findByHouse(
														new Date(
																calendar.getTimeInMillis()),
														no,
														RemoteProcedureCall.DEFAULT_PAGE_START,
														RemoteProcedureCall.DEFAULT_PAGE_SIZE,
														action);
											} catch (NumberFormatException e) {
												showText("输入有误，请输入正确的圈舍号");
												return;
											}
										}
									})
							.setNegativeButton(R.string.cancel,
									new OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
										}
									}).create();
				}
				queryDialog.show();
			}
		});
	}

	/**
	 * 初始化年份选择器，并添加事件处理
	 * 
	 * @param view
	 */
	private void builderYearSelectSpinner(View view) {
		yearSelect = (Spinner) view.findViewById(R.id.feeding_year_select);
		final List<Integer> list = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		for (int i = StaticDatas.START_YEAR; i <= calendar.get(Calendar.YEAR); i++) {
			list.add(i);
		}
		yearSelect.setAdapter(new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView textView;
				if (convertView == null) {
					textView = new TextView(getAdapter().getContext());
				} else {
					textView = (TextView) convertView;
				}
				textView.setText(Integer.toString(list.get(position)));
				return textView;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public Object getItem(int position) {
				return list.get(position);
			}

			@Override
			public int getCount() {
				return list.size();
			}
		});
		yearSelect.setSelection(list.size() - 1);
		yearSelect
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					Calendar calendar = Calendar.getInstance();

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						calendar.set(Calendar.YEAR,
								(Integer) yearSelect.getSelectedItem());
						initData(new Date(calendar.getTimeInMillis()));
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	/**
	 * 翻页按钮事件处理
	 * 
	 * @param view
	 */
	private void buildPageButtonEvent(View view) {
		Button next = (Button) view.findViewById(R.id.next_feeding_button);
		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				nextPage(new Date(System.currentTimeMillis()), action);
			}
		});

		Button pre = (Button) view.findViewById(R.id.pre_feeding_button);
		pre.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				prePage(new Date(System.currentTimeMillis()), action);
			}
		});
	}

	/**
	 * 删除按钮事件处理
	 * 
	 * @param view
	 */
	private void buildDeleteButtonEvent(View view) {
		Button delete = (Button) view
				.findViewById(R.id.delete_feedingrecord_button);
		delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				List<FeedingRecord> records = getAdapter().getSelectedItems();
				for (FeedingRecord feedingRecord : records) {
					delete(feedingRecord.getId(),
							feedingRecord.getHeaderDate(),
							new AsyncCallback<String>() {

								@Override
								public void call(String result) {
									showText(result);
								}
							});
				}
				if (getAdapter().getEntities() != null) {
					getAdapter().getEntities().removeAll(records);
				}
				records.clear();
				notifyDataSetChanged();
			}
		});
	}

	/**
	 * 更新按钮事件处理
	 * 
	 * @param view
	 */
	private void buildRefreshButtonEvent(View view) {
		Button refresh = (Button) view
				.findViewById(R.id.feeding_refresh_button);
		refresh.setOnClickListener(new RefreshOnClickListener(yearSelect));
	}

	/**
	 * 添加按钮事件处理
	 * 
	 * @param view
	 */
	private void buildAddButtonEvent(View view) {
		Button add = (Button) view.findViewById(R.id.add_feedingrecord_button);
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getAdapter().getContext(),
						AddFeedingActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * 功能切换选择器
	 * 
	 * @param view
	 */
	private void buildModuleSelectSpinner(View view) {
		final Spinner spinner = (Spinner) view
				.findViewById(R.id.feeding_manager_submodule_select);
		spinner.setAdapter(new BaseAdapter() {

			private ModuleId[] ids = { ModuleId.FEEDING, ModuleId.FEED };

			@Override
			public View getView(int position, View view, ViewGroup parent) {
				TextView textView;
				if (view == null) {
					textView = new TextView(getAdapter().getContext());
				} else {
					textView = (TextView) view;
				}
				textView.setText(ids[position].getModuleName());
				return textView;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public Object getItem(int position) {
				return ids[position].getModuleName();
			}

			@Override
			public int getCount() {
				return ids.length;
			}
		});
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int id, long position) {
				if (spinner.getSelectedItem().equals(
						ModuleId.FEEDING.getModuleName())) {
					return;
				}
				/*
				 * 更新界面
				 */
				if (getFragmentSwitchCallback() != null) {
					getFragmentSwitchCallback().execute(ModuleId.FEED,
							ModuleId.FEEDING, FeedingRecordFragment.this);
					spinner.setSelection(0);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	/**
	 * 主界面生成
	 * 
	 * @param inflater
	 * @param container
	 * @return
	 */
	private View buildView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.feeding_manager_fragment_view,
				container, false);
		if (this.getAdapter() != null) {
			ListView listView = (ListView) view
					.findViewById(R.id.feedingrecord_listview);
			if (listView != null) {
				listView.setAdapter(this.getAdapter());
			}
		}
		return view;
	}

	@Override
	public void initData() {
		Date date = new Date(System.currentTimeMillis());
		list(RemoteProcedureCall.DEFAULT_PAGE_START,
				RemoteProcedureCall.DEFAULT_PAGE_SIZE, date, action);
		super.initData();
	}

	// @Override
	// public void listData(int start, int count) {
	// list(start, count, new Date(System.currentTimeMillis()), action);
	// }

	@Override
	public void initData(Date headerData) {
		list(RemoteProcedureCall.DEFAULT_PAGE_START,
				RemoteProcedureCall.DEFAULT_PAGE_SIZE, headerData, action);
		super.initData(headerData);
	}

}
