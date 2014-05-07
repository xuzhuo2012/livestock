package edu.hbut.livestock.util;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.Antiepidemic;

public class AntiepidemicViewAdapter extends TableEntityViewAdapter<Antiepidemic> {

	@Override
	protected void setViewContent(final Antiepidemic t, View view) {

		setTextOnView(R.id.antiepidemic_antiepidemicDate,fmt.format(t.getId().getAntiepidemicDate()), view);
		setTextOnView(R.id.antiepidemic_userid, t.getId().getUserid(), view);
		setTextOnView(R.id.antiepidemic_houseid,t.getId().getHouseid()+"", view);
		setTextOnView(R.id.antiepidemic_monitorItem,t.getMonitorItem().getItemid(), view);
		setTextOnView(R.id.antiepidemic_sampleCount, t.getSampleCount()+"", view);
		setTextOnView(R.id.antiepidemic_department, t.getDepartment().getDepartmentName(), view);
		setTextOnView(R.id.antiepidemic_monitorResult,t.getMonitorResult(), view);
		setTextOnView(R.id.antiepidemic_dealResult,t.getDealResult(), view);

		CheckBox box = (CheckBox) view.findViewById(R.id.antiepidemic_item_select);
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

		return R.layout.antiepidemic_view;
	}

}
