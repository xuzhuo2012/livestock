package edu.hbut.livestock.util;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.DiagnosisRecord;

public class DiagnosisRecordViewAdapter extends TableEntityViewAdapter<DiagnosisRecord> {

	@Override
	protected void setViewContent(final DiagnosisRecord t, View view) {

		setTextOnView(R.id.diagnosis_diagnosisDate,fmt.format(t.getId().getDiagnosisDate()), view);
		setTextOnView(R.id.diagnosis_livestockid, t.getId().getLivestockid(), view);
		setTextOnView(R.id.diagnosis_age,t.getAge()+"", view);
		setTextOnView(R.id.diagnosis_reason,t.getReason(), view);
		setTextOnView(R.id.diagnosis_doctor, t.getDoctor(), view);
		setTextOnView(R.id.diagnosis_medicine, t.getMedicine(), view);
		setTextOnView(R.id.diagnosis_method,t.getMethod(), view);
		setTextOnView(R.id.diagnosis_result,t.getResult(), view);

		CheckBox box = (CheckBox) view.findViewById(R.id.diagnosis_item_select);
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

		return R.layout.diagnosis_view;
	}

}
