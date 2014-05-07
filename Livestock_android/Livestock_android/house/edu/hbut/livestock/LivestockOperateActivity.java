package edu.hbut.livestock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.hbut.livestock.entity.Livestock;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
import edu.hbut.livestock.util.StaticDatas;

/**
 * 
 * @author Hang
 * 
 */
public abstract class LivestockOperateActivity extends RecordOperateActivity<Livestock> {

	public static final String LIVESTOCK_RESULT = "livestock";
	
	/**
	 * �������������
	 */
	protected EditText livestockidEditText;

	/**
	 * �����������
	 */
	protected EditText createTimeEditText;

	/**
	 * �����������������
	 */
	protected EditText livestockTypeEditText;

	/**
	 * �ύ��ť
	 */
	protected Button submitButton;

	/**
	 * ȡ����ť
	 */
	protected Button cancelButton;

	/**
	 * Զ�̵���
	 */
	protected RemoteProcedureCall<Livestock, String> call;

	/**
	 * ��ʽ��������ʾ
	 */
	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat format = new SimpleDateFormat(
			StaticDatas.DEFAULT_YEAR_MONTH_FORMAT);

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResource());
		initWidget();

		call = (RemoteProcedureCall<Livestock, String>) ObjectConfigedFactory
				.getRemoteProcedureCallFactory().getBean(Livestock.class);

		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				LivestockOperateActivity.this.finish();
			}
		});

		submitButton.setOnClickListener(new OnSubmitListener());
		
		createTimeEditText.setText(format.format(new Date()));
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
			Livestock livestock = getLivestock();
			if (livestock == null) {
				return;
			}
			request(livestock);
			finish();
		}

		private Livestock getLivestock() {
			String livestockid = livestockidEditText.getText().toString();
			if (livestockid == null || livestockid.trim().equals("")) {
				showLongToast("���ݱ�Ų���Ϊ��");
				return null;
			}
			String createTime = createTimeEditText.getText().toString();
			if (createTime == null || createTime.trim().equals("")) {
				showLongToast("��Ȧʱ�� ");
				return null;
			}
			String livestockType = livestockTypeEditText.getText().toString();
			if (livestockType == null || livestockType.trim().equals("")) {
				showLongToast("�������Ͳ���Ϊ��");
			}
			Livestock livestock = new Livestock();
			livestock.setLivestockid(livestockid);
			try {
				livestock.setCreateTime(new java.sql.Date(format.parse(createTime).getTime()));
			} catch (ParseException e) {
				showLongToast("���ڸ�ʽ���벻��ȷ����ʽ����/��/��");
				return null;
			}
			livestock.setLivestockType(livestockType);
			livestock.setHeaderDate(livestock.getCreateTime());
			return livestock;
		}
	}

}
