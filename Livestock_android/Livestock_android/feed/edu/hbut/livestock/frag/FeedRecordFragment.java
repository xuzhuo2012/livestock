package edu.hbut.livestock.frag;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import edu.hbut.livestock.AddFeedActivity;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.Feed;
import edu.hbut.livestock.http.AsyncCallback;
import edu.hbut.livestock.http.FeedRemoteProcedureCall;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
import edu.hbut.livestock.util.ModuleId;
import edu.hbut.livestock.util.StaticDatas;
import edu.hbut.livestock.util.Tips;

/**
 * 主功能模块视图
 * 
 * @author Hang
 * 
 */
public class FeedRecordFragment extends EntityListViewFragment<Feed, Timestamp> {

	/**
	 * 查询结果处理
	 * 
	 * @author Hang
	 * 
	 */
	private final class ListCallback implements AsyncCallback<List<Feed>> {
		@Override
		public void call(List<Feed> result) {
			Log.v("msg", "ok");
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

	private final class SubModuleAdapter extends BaseAdapter {
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView;
			if (convertView == null) {
				textView = new TextView(getAdapter().getContext());
			} else {
				textView = (TextView) convertView;
			}
			textView.setText(submodule[position]);
			return textView;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public Object getItem(int position) {
			return submodule[position];
		}

		@Override
		public int getCount() {
			return submodule.length;
		}
	}

	@SuppressWarnings("unchecked")
	private RemoteProcedureCall<Feed, Timestamp> call = (RemoteProcedureCall<Feed, Timestamp>) ObjectConfigedFactory
			.getRemoteProcedureCallFactory().getBean(Feed.class);

	private String[] submodule = { ModuleId.FEED.getModuleName(),
			ModuleId.FEEDING.getModuleName() };

	public FeedRecordFragment() {
		super.setCall(call);
	}

	private AsyncCallback<List<Feed>> action = new ListCallback();

	/**
	 * 年份选择器
	 */
	private Spinner yearSpinner;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflateView(inflater, container);

		setDateSelectSpinner(view);

		setRefreshButtonEvent(view);

		setAddButtonEvent(view);

		setSubmeduleSwitchSpinner(view);

		setDeleteButtonEvent(view);

		setPrePageButtonEvent(view);

		setNextPageButtonEvent(view);

		setQueryButtonEvent(view);

		return view;
	}

	/**
	 * 为查找按钮添加事件
	 * 
	 * @param view
	 */
	private void setQueryButtonEvent(View view) {
		Button query = (Button) view.findViewById(R.id.query_feed_button);
		query.setOnClickListener(new View.OnClickListener() {

			private Dialog queryDialog;

			/**
			 * 起始时间输入框
			 */
			private EditText startTime;

			/**
			 * 终止时间输入框
			 */
			private EditText endTime;

			/**
			 * 起始时间选择按钮
			 */
			private Button selectStartTime;

			/**
			 * 终止时间选择按钮
			 */
			private Button selectEndTime;

			/**
			 * 日期格式化
			 */
			@SuppressLint("SimpleDateFormat")
			private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

			public void onClick(View v) {
				// Intent intent = new Intent(getAdapter().getContext(),
				// FeedQueryActivity.class);
				// startActivityForResult(intent, QUERY_REQUEST_CODE);
				if (queryDialog == null) {
					LayoutInflater inflater = (LayoutInflater) getAdapter()
							.getContext().getSystemService(
									Context.LAYOUT_INFLATER_SERVICE);
					View view = inflater.inflate(R.layout.feed_query, null);
					queryDialog = new AlertDialog.Builder(
							FeedRecordFragment.this.getAdapter().getContext())
							.setView(view)
							.setTitle(R.string.query_condition)
							.setPositiveButton(R.string.ok,
									new OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialogInterface,
												int arg1) {
											String start = startTime.getText()
													.toString();
											if (start == null
													|| start.trim().equals("")) {
												showLongToast("起始时间不能为空");
												return;
											}
											String end = endTime.getText()
													.toString();
											if (end == null
													|| end.trim().equals("")) {
												showLongToast("终止时间不能为空");
												return;
											}
											Date stime;
											try {
												stime = new Date(format.parse(
														start).getTime());
											} catch (ParseException e) {
												showLongToast("起始时间格式不正确：yyyy/MM/dd");
												return;
											}
											Date etime;
											try {
												etime = new Date(format.parse(
														end).getTime());
											} catch (ParseException e) {
												showLongToast("终止时间格式不正确：yyyy/MM/dd");
												return;
											}
											((FeedRemoteProcedureCall) call)
													.findByTime(
															stime,
															etime,
															FeedRemoteProcedureCall.DEFAULT_PAGE_START,
															FeedRemoteProcedureCall.DEFAULT_PAGE_SIZE,
															new AsyncCallback<List<Feed>>() {

																@Override
																public void call(
																		List<Feed> result) {
																	action.call(result);
																	queryDialog
																			.cancel();
																}
															});

										}
									})
							.setNegativeButton(R.string.cancel,
									new OnClickListener() {

										@Override
										public void onClick(
												DialogInterface arg0, int arg1) {
											queryDialog.cancel();
										}
									}).create();

					startTime = (EditText) view
							.findViewById(R.id.query_feed_start_time);
					endTime = (EditText) view
							.findViewById(R.id.query_feed_end_time);

					selectStartTime = (Button) view
							.findViewById(R.id.query_feed_select_start_time);
					selectStartTime
							.setOnClickListener(new View.OnClickListener() {

								DatePickerDialog dialog;

								@Override
								public void onClick(View v) {
									Calendar calendar = Calendar.getInstance();
									if (dialog == null) {
										dialog = new DatePickerDialog(
												getAdapter().getContext(),
												new DatePickerDialog.OnDateSetListener() {

													@Override
													public void onDateSet(
															DatePicker view,
															int year,
															int monthOfYear,
															int dayOfMonth) {
														startTime
																.setText(year
																		+ "/"
																		+ (monthOfYear + 1)
																		+ '/'
																		+ dayOfMonth);
													}
												},
												calendar.get(Calendar.YEAR),
												calendar.get(Calendar.MONTH),
												calendar.get(Calendar.DAY_OF_MONTH));
									}
									dialog.show();
								}
							});
					selectEndTime = (Button) view
							.findViewById(R.id.query_feed_select_end_time);
					selectEndTime
							.setOnClickListener(new View.OnClickListener() {
								DatePickerDialog dialog;

								@Override
								public void onClick(View v) {
									Calendar calendar = Calendar.getInstance();
									if (dialog == null) {
										dialog = new DatePickerDialog(
												getAdapter().getContext(),
												new DatePickerDialog.OnDateSetListener() {

													@Override
													public void onDateSet(
															DatePicker view,
															int year,
															int monthOfYear,
															int dayOfMonth) {
														endTime.setText(year
																+ "/"
																+ (monthOfYear + 1)
																+ '/'
																+ dayOfMonth);
													}
												},
												calendar.get(Calendar.YEAR),
												calendar.get(Calendar.MONTH),
												calendar.get(Calendar.DAY_OF_MONTH));
									}
									dialog.show();
								}
							});

				}
				queryDialog.show();
			}

			private void showLongToast(String res) {
				Toast.makeText(getAdapter().getContext(), res,
						Toast.LENGTH_LONG).show();
			}
		});
	}

    /**
     * 年份选择器
     *
     * @param view
     */
	private void setDateSelectSpinner(View view) {
		yearSpinner = (Spinner) view
				.findViewById(R.id.feedrecord_view_year_select);
		final List<Integer> list = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		for (int i = StaticDatas.START_YEAR; i <= calendar.get(Calendar.YEAR); i++) {
			list.add(i);
		}
		yearSpinner.setAdapter(new BaseAdapter() {

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
		yearSpinner.setSelection(list.size() - 1);
		yearSpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					Calendar calendar = Calendar.getInstance();

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						calendar.set(Calendar.YEAR,
								(Integer) yearSpinner.getSelectedItem());
						initData(new Date(calendar.getTimeInMillis()));
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	private void setNextPageButtonEvent(View view) {
		Button nextPage = (Button) view.findViewById(R.id.next_feed_button);
		nextPage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				nextPage(getCurrentSelectedDate(yearSpinner), action);
			}
		});
	}

	private void setSubmeduleSwitchSpinner(View view) {
		final Spinner spinner = (Spinner) view
				.findViewById(R.id.feed_manager_submodule_select);
		spinner.setAdapter(new SubModuleAdapter());
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int id, long position) {
				if (spinner.getSelectedItem().equals(
						ModuleId.FEED.getModuleName())) {
					return;
				}
				if (getFragmentSwitchCallback() != null) {
					getFragmentSwitchCallback().execute(ModuleId.FEEDING,
							ModuleId.FEED, FeedRecordFragment.this);
					spinner.setSelection(0);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	private void setPrePageButtonEvent(View view) {
		Button prePage = (Button) view.findViewById(R.id.pre_feed_button);
		prePage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				prePage(getCurrentSelectedDate(yearSpinner), action);
			}
		});
	}

	private void setDeleteButtonEvent(View view) {
		Button delete = (Button) view.findViewById(R.id.delete_feed_button);
		delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				List<Feed> feeds = getAdapter().getSelectedItems();
				for (final Feed feed : feeds) {
					// 删除
					delete(feed.getChangeDate(), feed.getHeaderDate(),
							new AsyncCallback<String>() {

								@Override
								public void call(String result) {
									showText(result);
									if (Tips.DELETE_SUCCESS.equals(result)) {
										getAdapter().getEntities().remove(feed);
										getAdapter().deselect(feed);
										notifyDataSetChanged();
									}
								}
							});
				}
				if (getAdapter().getEntities() != null) {
					getAdapter().getEntities().removeAll(feeds);
				}
				feeds.clear();
			}
		});
	}

	private void setAddButtonEvent(View view) {
		Button add = (Button) view.findViewById(R.id.add_feed_button);
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getAdapter().getContext(),
						AddFeedActivity.class);
				startActivity(intent);
			}
		});
	}

	private void setRefreshButtonEvent(View view) {
		Button refresh = (Button) view.findViewById(R.id.feed_refresh_button);

		refresh.setOnClickListener(new RefreshOnClickListener(yearSpinner));
	}

	/**
	 * 主界面视图
	 * 
	 * @param inflater
	 * @param container
	 * @return
	 */
	private View inflateView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.feed_manager_fragment_view,
				container, false);
		if (this.getAdapter() != null) {
			ListView listView = (ListView) view
					.findViewById(R.id.feed_listview);
			if (listView != null) {
				listView.setAdapter(this.getAdapter());
			}
		}
		return view;
	}

	public void initData() {
		listData(RemoteProcedureCall.DEFAULT_PAGE_START,
				RemoteProcedureCall.DEFAULT_PAGE_SIZE);
		super.initData();
	}

	@Override
	public void initData(Date headerData) {
		list(RemoteProcedureCall.DEFAULT_PAGE_START,
				RemoteProcedureCall.DEFAULT_PAGE_SIZE, headerData, action);
		super.initData(headerData);
	}

}
