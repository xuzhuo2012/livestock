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
 * ���ϱ䶯��¼����
 * 
 * @author Hang
 * 
 */
public abstract class FeedOperateActivity extends RecordOperateActivity<Feed> {

	/**
	 * ����Feed����ļ�
	 */
	public static final String FEED_RESULT = "feed";

	/**
	 * �䶯ʱ�������
	 */
	protected EditText changeDateEditText;

	/**
	 * �䶯�����������
	 */
	protected EditText feedNameEditText;

	/**
	 * �������������
	 */
	protected EditText factoryEditText;

	/**
	 * ���������
	 */
	protected EditText batchNumberEditText;

	/**
	 * �䶯���������
	 */
	protected EditText changeTypeEditText;

	/**
	 * �䶯���������
	 */
	protected EditText changeCountEditText;

	/**
	 * ��ע�����
	 */
	protected EditText remarkEditText;

	/**
	 * �ύ���밴ť
	 */
	protected Button submitButton;

	/**
	 * ȡ����ť
	 */
	protected Button cancelButton;

	/**
	 * Զ�̵��ö���
	 */
	protected RemoteProcedureCall<Feed, Date> call;

	/**
	 * ���ڸ�ʽ��
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
		 * ��ʼ��Զ�̵���������
		 */
                call = (RemoteProcedureCall<Feed, Date>) ObjectConfigedFactory
				.getRemoteProcedureCallFactory().getBean(Feed.class);

		/**
		 * ��ȡ��ʱ����
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
	 * �����Ӱ�ť�󴥷����¼��������
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
		 * ��ȡ��������
		 * 
		 * @return ��Ӧ��ʵ�������
		 */
		private Feed getFeed() {
			/*
			 * �ύ����ǰ����֤���ݵĺϷ���
			 */
			String changeDate = changeDateEditText.getText().toString();
			if (changeDate == null || changeDate.trim().equals("")) {
				Toast.makeText(FeedOperateActivity.this, "�䶯ʱ�䲻��Ϊ��",
						Toast.LENGTH_LONG).show();
				return null;
			}
			String feedName = feedNameEditText.getText().toString();
			if (feedName == null || feedName.trim().equals("")) {
				Toast.makeText(FeedOperateActivity.this, "����������Ϊ��",
						Toast.LENGTH_LONG).show();
				return null;
			}
			String factory = factoryEditText.getText().toString();
			if (factory == null || factory.trim().equals("")) {
				Toast.makeText(FeedOperateActivity.this, "�������Ҳ���Ϊ��",
						Toast.LENGTH_LONG).show();
				return null;
			}
			String batchNumber = batchNumberEditText.getText().toString();
			if (batchNumber == null || batchNumber.trim().equals("")) {
				Toast.makeText(FeedOperateActivity.this, "���Ų���Ϊ��",
						Toast.LENGTH_LONG).show();
				return null;
			}
			String changeType = changeTypeEditText.getText().toString();
			if (changeType == null || changeType.trim().equals("")) {
				Toast.makeText(FeedOperateActivity.this, "�䶯���Ͳ���Ϊ��",
						Toast.LENGTH_LONG).show();
				return null;
			}
			String changeCount = changeCountEditText.getText().toString();
			if (changeCount == null || changeCount.equals("")) {
				Toast.makeText(FeedOperateActivity.this, "�䶯��������Ϊ��",
						Toast.LENGTH_LONG).show();
				return null;
			}
			String remark = remarkEditText.getText().toString();
			Feed t = new Feed();
			try {
				t.setChangeDate(new Timestamp(format.parse(changeDate).getTime()));
                                Log.v("changeDate", changeDate);
                        } catch (ParseException e) {
				Toast.makeText(FeedOperateActivity.this, "ʱ�����벻��ȷ",
						Toast.LENGTH_LONG).show();
				return null;
			}
			t.setBatchNumber(batchNumber);
			t.setChangeCount(Integer.parseInt(changeCount));
			t.setChangeType(changeType);
			t.setFactory(factory);
			t.setFeedName(feedName);
			/*
			 * ���ñ�ͷʱ����Ϣ����䶯ʱ����ͬ
			 */
			t.setHeaderDate(new Date(t.getChangeDate().getTime()));
			t.setRemark(remark);
			t.setBatchNumber(batchNumber);
			return t;
		}
	}
}
