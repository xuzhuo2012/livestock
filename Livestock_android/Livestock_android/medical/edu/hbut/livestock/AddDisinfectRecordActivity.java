package edu.hbut.livestock;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.hbut.livestock.entity.DisinfectRecord;
import edu.hbut.livestock.entity.DisinfectRecordId;
import edu.hbut.livestock.http.AsyncCallback;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
import edu.hbut.livestock.util.*;

/**
 * 
 * @author Hang
 *
 */
public class AddDisinfectRecordActivity extends ToastActivity {
	
	@SuppressWarnings("unchecked")
	private RemoteProcedureCall<DisinfectRecord, DisinfectRecordId> call = (RemoteProcedureCall<DisinfectRecord, DisinfectRecordId>) ObjectConfigedFactory.getRemoteProcedureCallFactory().getBean(DisinfectRecord.class);
	
	/**
	 * ����ʱ�������
	 */
	private EditText disinfectDateEditText;
	
	/**
	 * �����ص�
	 */
	private EditText disinfectPlaceEditText;
	
	/**
	 * ����ҩƷ��
	 */
	private EditText disinfectMedicineNameEditText;
	
	/**
	 * ҩƷ����
	 */
	private EditText medicineFactoryEditText;
	
	/**
	 * ҩƷ����
	 */
	private EditText dosageEditText;
	
	/**
	 * ��������
	 */
	private EditText methodEditText;
	
	/**
	 * ����Ա
	 */
	private EditText operatorEditText;
	
	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat format = new SimpleDateFormat(StaticDatas.DEFAULT_DATE_FORMAT);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_disinfect_record);
		
		disinfectDateEditText = (EditText) findViewById(R.id.add_disinfect_date_edit_text);
                disinfectDateEditText.setText(format.format(new java.util.Date()));
		disinfectPlaceEditText = (EditText) findViewById(R.id.add_disinfect_place_edit_text);
		disinfectMedicineNameEditText = (EditText) findViewById(R.id.add_disinfect_medicine_edit_text);
		medicineFactoryEditText = (EditText) findViewById(R.id.add_disinfect_medicine_factory_edit_text);
		dosageEditText = (EditText) findViewById(R.id.add_disinfect_dosage_edit_text);
		methodEditText = (EditText) findViewById(R.id.add_disinfect_method_edit_text);
		operatorEditText = (EditText) findViewById(R.id.add_disinfect_operator_edit_text);
		
		Button add = (Button) findViewById(R.id.add_disinfect_record_button);
		
		Button cancel = (Button) findViewById(R.id.cancel_disinfect_record_button);
		
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DisinfectRecord record = getDisinfectRecord();
				if (record == null) {
					return;
				}
				call.add(record, new AsyncCallback<String>() {

					@Override
					public void call(String result) {
						showLongToast(result);
					}
				});
				finish();
			}
			
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private DisinfectRecord getDisinfectRecord() {
		String dates = disinfectDateEditText.getText().toString();
		if (dates == null || dates.trim().equals("")) {
			showLongToast("����ʱ�䲻��Ϊ��");
			return null;
		}
		String disinfectPlace = disinfectPlaceEditText.getText().toString();
		if (disinfectPlace == null || disinfectPlace.trim().equals("")) {
			showLongToast("�����ص㲻��Ϊ��");
			return null;
		}
		String medicine = disinfectMedicineNameEditText.getText().toString();
		if (medicine == null || medicine.trim().equals("")) {
			showLongToast("����ҩƷ���ܲ���");
			return null;
		}
		String medicineFactory = medicineFactoryEditText.getText().toString();
		if (medicineFactory == null || medicineFactory.trim().equals("")) {
			showLongToast("ҩƷ���̲���Ϊ��");
			return null;
		}
		String dosage = dosageEditText.getText().toString();
		if (dosage == null || dosage.trim().equals("")) {
			showLongToast("ҩƷ��������Ϊ��");
			return null;
		}
		String method = methodEditText.getText().toString();
		if (method == null || method.trim().equals("")) {
			showLongToast("�����������ܲ���");
			return null;
		}
		String operator = operatorEditText.getText().toString();
		if (operator == null || operator.trim().equals("")) {
			showLongToast("����Ա���ܲ���");
			return null;
		}
		DisinfectRecord record = new DisinfectRecord();
		DisinfectRecordId id = new DisinfectRecordId();
		try {
			id.setDisinfectDate(new Date(format.parse(dates).getTime()));
		} catch (ParseException e) {
			showLongToast("���ڸ�ʽ����ȷ��2013/05/05");
			return null;
		}
		record.setId(id);
		record.setDisinfectMedicineName(medicine);
		record.setDisinfectPlace(disinfectPlace);
		record.setDosage(Integer.parseInt(dosage));
		record.setHeaderDate(id.getDisinfectDate());
		record.setMedicineFactory(medicineFactory);
		record.setMethod(method);
		record.setOperator(operator);
		return record;
	}
}
