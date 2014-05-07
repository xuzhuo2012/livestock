package edu.hbut.livestock.util;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.MedicineRecord;

public class MedicineRecordViewAdapter extends TableEntityViewAdapter<MedicineRecord> {

	@Override
	protected void setViewContent(final MedicineRecord t, View view) {

		setTextOnView(R.id.medicine_startTime, fmt.format(t.getStartTime()), view);
		setTextOnView(R.id.medicine_productName, t.getProductName(), view);
		setTextOnView(R.id.medicine_medicineType,t.getMedicineType(), view);
		setTextOnView(R.id.medicine_standard,t.getStandard(), view);
		setTextOnView(R.id.medicine_validityPeriod, t.getValidityPeriod()+"", view);
		setTextOnView(R.id.medicine_factory, t.getFactory(), view);
		setTextOnView(R.id.medicine_batchNumber, t.getBatchNumber()+"", view);
		setTextOnView(R.id.medicine_dosage, t.getDosage()+"", view);
		setTextOnView(R.id.medicine_endTime,fmt.format(t.getEndTime()), view);

		CheckBox box = (CheckBox) view.findViewById(R.id.medicine_item_select);
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

		return R.layout.medicine_view;
	}

}
