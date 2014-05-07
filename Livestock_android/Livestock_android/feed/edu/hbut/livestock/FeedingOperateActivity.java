package edu.hbut.livestock;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import edu.hbut.livestock.entity.FeedingRecord;
import edu.hbut.livestock.entity.FeedingRecordId;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
import edu.hbut.livestock.util.StaticDatas;

/**
 * 添加圈舍饲料使用记录界面
 * 
 * @author Hang
 * 
 */
public abstract class FeedingOperateActivity extends RecordOperateActivity<FeedingRecord> {

	/**
	 * 饲料名输入框
	 */
	protected EditText feedNameEditText;

	/**
	 * 生产产家输入框
	 */
	protected EditText factoryedtEditText;

	/**
	 * 圈舍号输入框
	 */
	protected EditText houseIdEditText;

	/**
	 * 批号输入框
	 */
	protected EditText batchNumberEditText;

	/**
	 * 开始使用时间输入框
	 */
	protected EditText startTimeEditText;

	/**
	 * 用量输入框
	 */
	protected EditText dosagEditText;

	/**
	 * 结束使用时间
	 */
	protected EditText endTimEditText;

	/**
	 * 畜禽类型输入框
	 */
	protected EditText animalTypeEditText;
//
//	/**
//	 * 畜禽数量输入框
//	 */
//	protected EditText livestockCounText;

	/**
	 * 月份选择
	 */
	protected Spinner monthSelectSpinner;

	/**
	 * 年份选择
	 */
	protected Spinner yearSelectSpinner;

	/**
	 * 返回FeedingRecord对象的键
	 */
	public static final String FEEDING_RESULT = "feed";

	protected RemoteProcedureCall<FeedingRecord, FeedingRecordId> call;

	/**
	 * 提交输入按钮
	 */
	protected Button submitButton;

	/**
	 * 取消按钮
	 */
	protected Button cancelButton;

	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResource());
		initWidget();
		call = (RemoteProcedureCall<FeedingRecord, FeedingRecordId>) ObjectConfigedFactory
				.getRemoteProcedureCallFactory().getBean(FeedingRecord.class);

		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				FeedingOperateActivity.this.finish();
			}

		});

		submitButton.setOnClickListener(new AddFeedingListener());

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		final int month = calendar.get(Calendar.MONTH) + 1;

		List<Integer> years = new ArrayList<Integer>();
		final List<Integer> months = new ArrayList<Integer>();

		/*
		 * 年
		 */
		for (int i = StaticDatas.START_YEAR; i <= year; i++) {
			years.add(i);
		}

		/*
		 * 月
		 */
		for (int i = 1; i <= 12; i++) {
			months.add(i);
		}

		final SpinnerAdapter yearAdapter = new SpinnerAdapter(years);
		final SpinnerAdapter monthAdapter = new SpinnerAdapter(months);
		yearSelectSpinner.setAdapter(yearAdapter);
		yearSelectSpinner.setSelection(years.size() - 1);
		monthSelectSpinner.setAdapter(monthAdapter);
		monthSelectSpinner.setSelection(month - 1);
		yearSelectSpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						/*
						 * 当前时间所在的年份
						 */
						if (position == yearAdapter.getData().size() - 1) {
							/*
							 * 月份只到当前时间所在的月
							 */
							monthAdapter.setData(months.subList(0, month));
						} else {
							monthAdapter.setData(months);
						}
						monthAdapter.notifyDataSetChanged();
						monthSelectSpinner.setSelection(month - 1);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	/**
	 * 
	 * @author Hang
	 * 
	 */
	private final class SpinnerAdapter extends BaseAdapter {

		private List<Integer> data;

		public SpinnerAdapter(List<Integer> data) {
			super();
			this.data = data;
		}

		@Override
		public int getCount() {
			if (data == null) {
				return 0;
			}
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			TextView textView = null;
			if (view == null) {
				textView = new TextView(FeedingOperateActivity.this);
			} else {
				textView = (TextView) view;
			}
			textView.setText(Integer.toString(data.get(position)));
			return textView;
		}

		public List<Integer> getData() {
			return data;
		}

		public void setData(List<Integer> data) {
			this.data = data;
		}

	}

	/**
	 * 监听器，用于添加
	 * 
	 * @author Hang
	 * 
	 */
	private final class AddFeedingListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			FeedingRecord record = getRecord();
			if (record == null) {
				return;
			}
			request(record);
		}

		private FeedingRecord getRecord() {
			String feedName = feedNameEditText.getText().toString();
			if (feedName == null || feedName.trim().equals("")) {
				showLongToast("饲料名不能为空");
				return null;
			}
			String factory = factoryedtEditText.getText().toString();
			if (factory == null || factory.trim().equals("")) {
				showLongToast("饲料生产产家不能为空");
				return null;
			}
			String houseid = houseIdEditText.getText().toString();
			if (houseid == null || houseid.trim().equals("")) {
				showLongToast("圈舍号不能为空");
				return null;
			}
			String batchNumber = batchNumberEditText.getText().toString();
			if (batchNumber == null || batchNumber.trim().equals("")) {
				showLongToast("批号不能为空");
				return null;
			}
			String startTime = startTimeEditText.getText().toString();
			if (startTime == null || startTime.trim().equals("")) {
				showLongToast("开始使用时间不能为空");
				return null;
			}
			String dosage = dosagEditText.getText().toString();
			if (dosage == null || dosage.trim().equals("")) {
				showLongToast("用量不能为空");
				return null;
			}
			String endTime = endTimEditText.getText().toString();
			String animalType = animalTypeEditText.getText().toString();
			if (animalType == null || animalType.trim().equals("")) {
				showLongToast("畜禽类型不能为空");
				return null;
			}
//			String livestockCount = livestockCounText.getText().toString();
//			if (livestockCount == null || livestockCount.trim().equals("")) {
//				showLongToast("畜禽数量不能为空");
//				return null;
//			}
			/*
			 * 设置数据
			 */
			FeedingRecord record = new FeedingRecord();
			FeedingRecordId id = new FeedingRecordId();
			id.setBatchNumber(batchNumber);
			id.setFactory(factory);
			id.setFeedName(feedName);
			try {
				id.setHouseid(Integer.parseInt(houseid));
			} catch (NumberFormatException e) {
				showLongToast("输入的数据有错，圈舍号应该为整数");
				return null;
			}
			record.setId(id);
			try {
				record.setDosage(Integer.parseInt(dosage));
			} catch (NumberFormatException e) {
				showLongToast("用量应该为整数");
				return null;
			}
			record.setAnimalType(animalType);
			if (endTime != null) {
				try {
					record.setEndTime(new Date(format.parse(endTime).getTime()));
				} catch (ParseException e) {
					showLongToast("日期输入格式不正确");
					return null;
				}
			}
//			try {
//				record.setLivestockCount(Integer.parseInt(livestockCount));
//			} catch (NumberFormatException e) {
//				showLongToast("畜禽数量输入不正确，应该为整数");
//				return null;
//			}
			try {
				record.setStartTime(new Date(format.parse(startTime).getTime()));
			} catch (ParseException e) {
				showLongToast("开始日期输入不正确");
				return null;
			}
			/*
			 * 表头信息
			 */
			SpinnerAdapter adapter = (SpinnerAdapter) yearSelectSpinner.getAdapter();
			int year = adapter.getData().get((Integer) yearSelectSpinner.getSelectedItem());
//			int month = (Integer) monthSelectSpinner.getSelectedItem();
			@SuppressWarnings("deprecation")
			Date date = new Date(year - 1900, 1, 1);
			record.setHeaderDate(date);
			return record;
		}
	}

}
