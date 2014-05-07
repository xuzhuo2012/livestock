package edu.hbut.livestock;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import edu.hbut.livestock.entity.ProducingRecord;
import edu.hbut.livestock.entity.ProducingRecordId;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
import edu.hbut.livestock.util.StaticDatas;

/**
 * 
 * @author Hang
 * 
 */
public abstract class ProducingRecordOperateActivity extends RecordOperateActivity<ProducingRecord> {

	public static final String PRODUCING_RECORD_RESULT = "producing";

	private static final String[] TYPES = { "�Բ�", "����", "ʹ��", "���" };

	private ArrayAdapter<String> adapter;

	/**
	 * Ȧ��������
	 */
	protected EditText housidEditText;

	/**
	 * �䶯ʱ�������
	 */
	protected EditText changeDateEditText;

	/**
	 * �䶯����ѡ���
	 */
	protected Spinner changeTypeSpinner;

	/**
	 * �䶯���������
	 */
	protected EditText changeCounEditText;

	/**
	 * �ύ��ť
	 */
	protected Button submitButton;

	/**
	 * ȡ����ť
	 */
	protected Button cancelButton;

	@SuppressLint("SimpleDateFormat")
	protected SimpleDateFormat format = new SimpleDateFormat(StaticDatas.DEFAULT_DATE_TIME_FORMAT);

	protected RemoteProcedureCall<ProducingRecord, ProducingRecordId> call;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResource());
		initWidget();

		call = (RemoteProcedureCall<ProducingRecord, ProducingRecordId>) ObjectConfigedFactory
				.getRemoteProcedureCallFactory().getBean(ProducingRecord.class);

		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ProducingRecordOperateActivity.this.finish();
			}
		});

		submitButton.setOnClickListener(new OnSubmitListener());

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, TYPES);
		changeTypeSpinner.setAdapter(adapter);
	}

	/**
	 * �ύ�¼�������
	 * 
	 * @author Hang
	 * 
	 */
	private final class OnSubmitListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			ProducingRecord record = getProducingRecord();
			request(record);
			finish();
		}

		private ProducingRecord getProducingRecord() {
			String houseid = housidEditText.getText().toString();
			if (houseid == null || houseid.trim().equals("")) {
				showLongToast("Ȧ��Ų���Ϊ��");
				return null;
			}
			String changeDate = changeDateEditText.getText().toString();
			if (changeDate == null || changeDate.trim().equals("")) {
				showLongToast("�䶯���ڲ���Ϊ��");
				return null;
			}
			String changeType = null;
			try {
				changeType = new String(changeTypeSpinner.getSelectedItem().toString().getBytes(), "UTF-8");
				if (changeType == null || changeType.trim().equals("")) {
					showLongToast("�䶯���Ͳ���Ϊ��");
					return null;
				}
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			String changeCount = changeCounEditText.getText().toString();
			if (changeCount == null || changeCount.trim().equals("")) {
				showLongToast("�䶯��������Ϊ��");
				return null;
			}
			ProducingRecord record = new ProducingRecord();
			ProducingRecordId id = new ProducingRecordId();
			try {
				id.setChangeDate(new Timestamp(format.parse(changeDate).getTime()));
			} catch (ParseException e) {
				showLongToast("�������ڸ�ʽ����ȷ����ʽ��2013/01/01");
				return null;
			}
			id.setHouseid(Integer.parseInt(houseid));
			record.setId(id);
			record.setChangeType(changeType);
			record.setChangeCount(Integer.parseInt(changeCount));
			return record;
		}

	}

}
