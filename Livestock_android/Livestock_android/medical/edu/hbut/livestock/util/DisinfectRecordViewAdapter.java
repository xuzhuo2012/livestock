package edu.hbut.livestock.util;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.DisinfectRecord;

public class DisinfectRecordViewAdapter extends TableEntityViewAdapter<DisinfectRecord> {

	@Override
	protected void setViewContent(final DisinfectRecord t, View view) {

		setTextOnView(R.id.medicine_factory_show, t.getMedicineFactory(), view);
		setTextOnView(R.id.disinfect_method_show, t.getMethod(), view);
		setTextOnView(R.id.disinfect_medicine_show,
				t.getDisinfectMedicineName(), view);
		setTextOnView(R.id.disinfect_dosage_show,
				Integer.toString(t.getDosage()), view);
		setTextOnView(R.id.disinfect_operator_show, t.getOperator(), view);
		setTextOnView(R.id.disinfect_place_show, t.getDisinfectPlace(), view);
		setTextOnView(R.id.disinfect_date_show,
				fmt.format(t.getId().getDisinfectDate()), view);

		CheckBox box = (CheckBox) view.findViewById(R.id.disinfect_item_select);
		box.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					select(t);
				} else {
					deselect(t);
				}
			}
		});
	}

	@Override
	protected int getEntityViewLayoutResourceId() {

		return R.layout.disinfect_view;
	}

}
