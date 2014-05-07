package edu.hbut.livestock.util;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.Immunization;

public class ImmunizationViewAdapter extends TableEntityViewAdapter<Immunization> {

	@Override
	protected void setViewContent(final Immunization t, View view) {

		setTextOnView(R.id.immunization_immunizationTime,fmt.format(t.getId().getImmunizationTime()), view);
		setTextOnView(R.id.immunization_userid, t.getId().getUserid(), view);
		setTextOnView(R.id.immunization_houseid,t.getId().getHouseid()+"", view);
		setTextOnView(R.id.immunization_totalCount,t.getTotalCount()+"", view);
		setTextOnView(R.id.immunization_vaccine, t.getVaccine(), view);
		setTextOnView(R.id.immunization_vaccineFactory, t.getVaccineFactory(), view);
		setTextOnView(R.id.immunization_batchNumber,t.getBatchNumber(), view);
		setTextOnView(R.id.immunization_dosage,t.getDosage()+"", view);
		setTextOnView(R.id.immunization_immunizationMethod,t.getImmunizationMethod(), view);

		CheckBox box = (CheckBox) view.findViewById(R.id.immunization_item_select);
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

		return R.layout.immunization_view;
	}

}
