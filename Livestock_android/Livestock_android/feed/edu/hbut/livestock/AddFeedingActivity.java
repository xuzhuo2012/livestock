package edu.hbut.livestock;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import edu.hbut.livestock.entity.FeedingRecord;
import edu.hbut.livestock.http.AsyncCallback;

/**
 * 添加圈舍饲料使用记录界面
 * 
 * @author Hang
 * 
 */
public class AddFeedingActivity extends FeedingOperateActivity {

	@Override
	protected void initWidget() {
		feedNameEditText = (EditText) findViewById(R.id.add_feeding_feed_name_edit_text);
		factoryedtEditText = (EditText) findViewById(R.id.add_feeding_feed_factory_edit_text);
		houseIdEditText = (EditText) findViewById(R.id.add_feeding_house_id_edit_text);
		batchNumberEditText = (EditText) findViewById(R.id.add_feeding_feed_batchNumber_edit_text);
		startTimeEditText = (EditText) findViewById(R.id.add_feeding_start_time_edit_text);
		dosagEditText = (EditText) findViewById(R.id.add_feeding_dosage_edit_text);
		endTimEditText = (EditText) findViewById(R.id.add_feeding_end_time_edit_text);
		animalTypeEditText = (EditText) findViewById(R.id.add_feeding_animal_type_edit_text);
//		livestockCounText = (EditText) findViewById(R.id.add_feeding_livestock_count_edit_text);
		submitButton = (Button) findViewById(R.id.add_feeding_button);
		cancelButton = (Button) findViewById(R.id.cancel_add_feeding_button);
		monthSelectSpinner = (Spinner) findViewById(R.id.add_feeding_month_select);
		yearSelectSpinner = (Spinner) findViewById(R.id.add_feeding_year_select);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_add_feeding;
	}

	@Override
	protected void request(FeedingRecord entity) {
		call.add(entity, new AsyncCallback<String>() {
			
			@Override
			public void call(String result) {
				showLongToast(result);
			}
		});
	}

}
