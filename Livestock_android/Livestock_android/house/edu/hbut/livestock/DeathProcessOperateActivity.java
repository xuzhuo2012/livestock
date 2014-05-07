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
	 * 处理日期输入框
	 */
	protected EditText processDateEditText;

	/**
	 * 处理部门输入框
	 */
	protected EditText departmentEditText;

	/**
	 * 处理畜禽数量输入框
	 */
	protected EditText livestockidEditText;

	/**
	 * 死亡原因输入框
	 */
	protected EditText reasonEditText;

	/**
	 * 死亡原因输入框
	 */
	protected EditText remarkEditText;

	/**
	 * 提交按钮
	 */
	protected Button submitButton;

	/**
	 * 取消按钮
	 */
	protected Button cancelButton;

	/**
	 * 远程调用
	 */
	protected RemoteProcedureCall<DeathProcess, Date> call;

	/**
	 * 格式化日期显示
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
	 * 提交事件监听器
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
				showLongToast("处理时间不能为空");
				return null;
			}
			String departmentName = departmentEditText.getText().toString();
			if (departmentName == null || departmentName.trim().equals("")) {
				showLongToast("处理部门不能为空");
				return null;
			}
			String livestockid = livestockidEditText.getText().toString();
			if (livestockid == null || livestockid.trim().equals("")) {
				showLongToast("畜禽类型不能为空");
				return null;
			}
			String reason = reasonEditText.getText().toString();
			if (reason == null || reason.trim().equals("")) {
				showLongToast("死亡原因不能为空");
				return null;
			}
			String remark = remarkEditText.getText().toString();
			DeathProcess process = new DeathProcess();
			try {
				process.setProcessDate(new Date(format.parse(processDate)
						.getTime()));
			} catch (ParseException e) {
				showLongToast("输入日期格式不正确");
				return null;
			}
			Department department = new Department();
			department.setDepartmentName(departmentName);
			process.setDepartment(department);
			process.setLivestock(new Livestock(livestockid));
			process.setReason(reason);
			process.setRemark(remark);
			process.setHeaderDate(process.getProcessDate());//表头时间信息与处理时间相同
			return process;
		}
	}

}
