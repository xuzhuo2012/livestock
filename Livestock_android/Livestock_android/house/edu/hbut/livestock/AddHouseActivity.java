package edu.hbut.livestock;

import android.widget.Button;
import android.widget.EditText;
import edu.hbut.livestock.entity.House;
import edu.hbut.livestock.http.AsyncCallback;

/**
 * ÃÌº”»¶…·ΩÁ√Ê
 * @author Hang
 *
 */
public class AddHouseActivity extends HouseInfoOperateActivity {
	
	@Override
	protected void initWidget() {
		houseIdEditText = (EditText) findViewById(R.id.add_house_id_edit_text);
		animalTypEditText = (EditText) findViewById(R.id.add_house_animal_type_edit_text);
		livestockCounText = (EditText) findViewById(R.id.add_house_livestockCount_edit_text);
		
		submitButton = (Button) findViewById(R.id.add_house_submit_button);
		cancelButton = (Button) findViewById(R.id.add_house_cancel_button);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_add_house;
	}

	@Override
	protected void request(House entity) {
		call.add(entity, new AsyncCallback<String>() {
			
			@Override
			public void call(String result) {
				showLongToast(result);
			}
		});
	}

}
