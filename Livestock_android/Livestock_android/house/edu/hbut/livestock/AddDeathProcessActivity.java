package edu.hbut.livestock;

import android.annotation.*;
import android.widget.Button;
import android.widget.EditText;
import edu.hbut.livestock.entity.DeathProcess;
import edu.hbut.livestock.http.AsyncCallback;
import edu.hbut.livestock.util.*;

import java.text.*;
import java.util.*;

/**
 * 
 * @author Hang
 * 
 */
public class AddDeathProcessActivity extends DeathProcessOperateActivity {

	@Override
	protected void initWidget() {
		processDateEditText = (EditText) findViewById(R.id.add_death_process_date_edit_text);

                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat f = new SimpleDateFormat(StaticDatas.DEFAULT_DATE_FORMAT);
                processDateEditText.setText(f.format(new Date()));

		departmentEditText = (EditText) findViewById(R.id.add_death_process_department_edit_text);

		livestockidEditText = (EditText) findViewById(R.id.add_death_process_livestock_edit_text);

		reasonEditText = (EditText) findViewById(R.id.add_death_process_reason_edit_text);

		remarkEditText = (EditText) findViewById(R.id.add_death_process_remark_edit_text);

		submitButton = (Button) findViewById(R.id.add_death_process_submit_button);

		cancelButton = (Button) findViewById(R.id.add_death_process_cancel_button);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_add_death_process;
	}

	@Override
	protected void request(DeathProcess entity) {
		call.add(entity, new AsyncCallback<String>() {

			@Override
			public void call(String result) {
				showLongToast(result);
			}
		});
	}

}
