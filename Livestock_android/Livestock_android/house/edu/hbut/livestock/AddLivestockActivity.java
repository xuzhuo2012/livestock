package edu.hbut.livestock;

import android.widget.Button;
import android.widget.EditText;
import edu.hbut.livestock.entity.Livestock;
import edu.hbut.livestock.http.AsyncCallback;

/**
 * 
 * @author Hang
 *
 */
public class AddLivestockActivity extends LivestockOperateActivity {

	@Override
	protected void initWidget() {
		livestockidEditText = (EditText) findViewById(R.id.add_livestock_id_edit_text);

		createTimeEditText = (EditText) findViewById(R.id.add_livestock_create_time_edit_text);

		livestockTypeEditText = (EditText) findViewById(R.id.add_livestock_type_edit_text);
		
		submitButton = (Button) findViewById(R.id.add_livestock_submit_button);
		
		cancelButton = (Button) findViewById(R.id.add_livestock_cancel_button);

	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_add_livestock;
	}

	@Override
	protected void request(Livestock entity) {
		call.add(entity, new AsyncCallback<String>() {
			
			@Override
			public void call(String result) {
				showLongToast(result);
			}
		});
		finish();
	}

}
