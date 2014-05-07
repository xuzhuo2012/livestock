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
	 * 处理日期输入框
	 */
	protected EditText livestockidEditText;

	/**
	 * 处理部门输入框
	 */
	protected EditText createTimeEditText;

	/**
	 * 处理畜禽数量输入框
	 */
	protected EditText livestockTypeEditText;

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
	protected RemoteProcedureCall<Livestock, String> call;

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
	 * 提交事件监听器
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
				showLongToast("畜禽编号不能为空");
				return null;
			}
			String createTime = createTimeEditText.getText().toString();
			if (createTime == null || createTime.trim().equals("")) {
				showLongToast("入圈时间 ");
				return null;
			}
			String livestockType = livestockTypeEditText.getText().toString();
			if (livestockType == null || livestockType.trim().equals("")) {
				showLongToast("畜禽类型不能为空");
			}
			Livestock livestock = new Livestock();
			livestock.setLivestockid(livestockid);
			try {
				livestock.setCreateTime(new java.sql.Date(format.parse(createTime).getTime()));
			} catch (ParseException e) {
				showLongToast("日期格式输入不正确，格式：年/月/日");
				return null;
			}
			livestock.setLivestockType(livestockType);
			livestock.setHeaderDate(livestock.getCreateTime());
			return livestock;
		}
	}

}
