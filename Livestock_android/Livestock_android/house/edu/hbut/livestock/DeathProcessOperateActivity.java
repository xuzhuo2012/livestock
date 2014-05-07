package edu.hbut.livestock;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.hbut.livestock.entity.DeathProcess;
import edu.hbut.livestock.entity.Department;
import edu.hbut.livestock.entity.Livestock;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
import edu.hbut.livestock.util.StaticDatas;

/**
 * 
 * @author Hang
 * 
 */
public abstract class DeathProcessOperateActivity extends RecordOperateActivity<DeathProcess> {

	public static final String HOUSE_RESULT = "house";
	/**
	 * �������������
	 */
	protected EditText processDateEditText;

	/**
	 * �����������
	 */
	protected EditText departmentEditText;

	/**
	 * �����������������
	 */
	protected EditText livestockidEditText;

	/**
	 * ����ԭ�������
	 */
	protected EditText reasonEditText;

	/**
	 * ����ԭ�������
	 */
	protected EditText remarkEditText;

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
	protected RemoteProcedureCall<DeathProcess, Date> call;

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

		call = (RemoteProcedureCall<DeathProcess, Date>) ObjectConfigedFactory
				.getRemoteProcedureCallFactory().getBean(DeathProcess.class);

		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DeathProcessOperateActivity.this.finish();
			}
		});

		submitButton.setOnClickListener(new OnSubmitListener());
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
			DeathProcess process = getDeathProcess();
			if (process == null) {
				return;
			}
			request(process);
			finish();
		}

		private DeathProcess getDeathProcess() {
			String processDate = processDateEditText.getText().toString();
			if (processDate == null || processDate.trim().equals("")) {
				showLongToast("����ʱ�䲻��Ϊ��");
				return null;
			}
			String departmentName = departmentEditText.getText().toString();
			if (departmentName == null || departmentName.trim().equals("")) {
				showLongToast("�����Ų���Ϊ��");
				return null;
			}
			String livestockid = livestockidEditText.getText().toString();
			if (livestockid == null || livestockid.trim().equals("")) {
				showLongToast("�������Ͳ���Ϊ��");
				return null;
			}
			String reason = reasonEditText.getText().toString();
			if (reason == null || reason.trim().equals("")) {
				showLongToast("����ԭ����Ϊ��");
				return null;
			}
			String remark = remarkEditText.getText().toString();
			DeathProcess process = new DeathProcess();
			try {
				process.setProcessDate(new Date(format.parse(processDate)
						.getTime()));
			} catch (ParseException e) {
				showLongToast("�������ڸ�ʽ����ȷ");
				return null;
			}
			Department department = new Department();
			department.setDepartmentName(departmentName);
			process.setDepartment(department);
			process.setLivestock(new Livestock(livestockid));
			process.setReason(reason);
			process.setRemark(remark);
			process.setHeaderDate(process.getProcessDate());//��ͷʱ����Ϣ�봦��ʱ����ͬ
			return process;
		}
	}

}
