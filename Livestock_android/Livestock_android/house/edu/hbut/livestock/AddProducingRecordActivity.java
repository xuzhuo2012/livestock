package edu.hbut.livestock;

import java.sql.Date;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import edu.hbut.livestock.entity.ProducingRecord;
import edu.hbut.livestock.http.AsyncCallback;

/**
 * 添加生产记录界面
 * @author Hang
 *
 */
public class AddProducingRecordActivity extends ProducingRecordOperateActivity {

	@Override
	protected void initWidget() {
		housidEditText = (EditText) findViewById(R.id.add_producing_record_houseid_edit_text);
		
		changeDateEditText = (EditText) findViewById(R.id.add_producing_record_change_date_edit_text);
                changeDateEditText.setText(format.format(new java.util.Date()));

		changeTypeSpinner = (Spinner) findViewById(R.id.add_producing_record_change_type_spinner);

		changeCounEditText = (EditText) findViewById(R.id.add_producing_record_change_count_edit_text);
		
		submitButton = (Button) findViewById(R.id.add_producing_record_submit_button);
		
		cancelButton = (Button) findViewById(R.id.add_producing_record_cancel_button);

	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_add_producing_record;
	}

	@Override
	protected void request(ProducingRecord entity) {
		entity.setHeaderDate(new Date(entity.getId().getChangeDate().getTime()));
		call.add(entity, new AsyncCallback<String>() {
			
			@Override
			public void call(String result) {
				showLongToast(result);
			}
		});
	}
	
}
