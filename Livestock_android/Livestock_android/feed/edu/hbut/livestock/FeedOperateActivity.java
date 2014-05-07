package edu.hbut.livestock;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import edu.hbut.livestock.entity.Feed;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;

/**
 * 饲料变动记录界面
 * 
 * @author Hang
 * 
 */
public abstract class FeedOperateActivity extends RecordOperateActivity<Feed> {

	/**
	 * 返回Feed对象的键
	 */
	public static final String FEED_RESULT = "feed";

	/**
	 * 变动时间输入框
	 */
	protected EditText changeDateEditText;

	/**
	 * 变动饲料名输入框
	 */
	protected EditText feedNameEditText;

	/**
	 * 生产产商输入框
	 */
	protected EditText factoryEditText;

	/**
	 * 批号输入框
	 */
	protected EditText batchNumberEditText;

	/**
	 * 变动类型输入框
	 */
	protected EditText changeTypeEditText;

	/**
	 * 变动数量输入框
	 */
	protected EditText changeCountEditText;

	/**
	 * 备注输入框
	 */
	protected EditText remarkEditText;

	/**
	 * 提交输入按钮
	 */
	protected Button submitButton;

	/**
	 * 取消按钮
	 */
	protected Button cancelButton;

	/**
	 * 远程调用对象
	 */
	protected RemoteProcedureCall<Feed, Date> call;

	/**
	 * 日期格式化
	 */
	protected SimpleDateFormat format;

	protected String fmt = "yyyy/MM/dd:HH:mm:ss";

	@SuppressLint("SimpleDateFormat")
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResource());
                format = new SimpleDateFormat(fmt);

                initWidget();

		/*
		 * 初始化远程调用类引用
		 */
                call = (RemoteProcedureCall<Feed, Date>) ObjectConfigedFactory
				.getRemoteProcedureCallFactory().getBean(Feed.class);

		/**
		 * 点取消时结束
		 */
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				FeedOperateActivity.this.finish();
			}

		});

		submitButton.setOnClickListener(new FeedSubmitListener());
	}

	/**
	 * 点击添加按钮后触发的事件处理程序
	 * 
	 * @author Hang
	 * 
	 */
	private final class FeedSubmitListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Feed t = getFeed();
			if (t == null) {
				return;
			}
			request(t);
		}

		/**
		 * 获取输入数据
		 * 
		 * @return 对应的实体类对象
		 */
		private Feed getFeed() {
			/*
			 * 提交数据前先验证数据的合法性
			 */
			String changeDate = changeDateEditText.getText().toString();
			if (changeDate == null || changeDate.trim().equals("")) {
				Toast.makeText(FeedOperateActivity.this, "变动时间不能为空",
						Toast.LENGTH_LONG).show();
				return null;
			}
			String feedName = feedNameEditText.getText().toString();
			if (feedName == null || feedName.trim().equals("")) {
				Toast.makeText(FeedOperateActivity.this, "饲料名不能为空",
						Toast.LENGTH_LONG).show();
				return null;
			}
			String factory = factoryEditText.getText().toString();
			if (factory == null || factory.trim().equals("")) {
				Toast.makeText(FeedOperateActivity.this, "生产产家不能为空",
						Toast.LENGTH_LONG).show();
				return null;
			}
			String batchNumber = batchNumberEditText.getText().toString();
			if (batchNumber == null || batchNumber.trim().equals("")) {
				Toast.makeText(FeedOperateActivity.this, "批号不能为空",
						Toast.LENGTH_LONG).show();
				return null;
			}
			String changeType = changeTypeEditText.getText().toString();
			if (changeType == null || changeType.trim().equals("")) {
				Toast.makeText(FeedOperateActivity.this, "变动类型不能为空",
						Toast.LENGTH_LONG).show();
				return null;
			}
			String changeCount = changeCountEditText.getText().toString();
			if (changeCount == null || changeCount.equals("")) {
				Toast.makeText(FeedOperateActivity.this, "变动数量不能为空",
						Toast.LENGTH_LONG).show();
				return null;
			}
			String remark = remarkEditText.getText().toString();
			Feed t = new Feed();
			try {
				t.setChangeDate(new Timestamp(format.parse(changeDate).getTime()));
                                Log.v("changeDate", changeDate);
                        } catch (ParseException e) {
				Toast.makeText(FeedOperateActivity.this, "时间输入不正确",
						Toast.LENGTH_LONG).show();
				return null;
			}
			t.setBatchNumber(batchNumber);
			t.setChangeCount(Integer.parseInt(changeCount));
			t.setChangeType(changeType);
			t.setFactory(factory);
			t.setFeedName(feedName);
			/*
			 * 设置表头时间信息，与变动时间相同
			 */
			t.setHeaderDate(new Date(t.getChangeDate().getTime()));
			t.setRemark(remark);
			t.setBatchNumber(batchNumber);
			return t;
		}
	}
}
