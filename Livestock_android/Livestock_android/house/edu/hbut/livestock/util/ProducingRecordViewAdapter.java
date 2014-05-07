package edu.hbut.livestock.util;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.ProducingRecord;

/**
 * 
 * @author Hang
 * 
 */
public class ProducingRecordViewAdapter extends TableEntityViewAdapter<ProducingRecord> {

	public ProducingRecordViewAdapter() {
		super();
	}

	public ProducingRecordViewAdapter(List<ProducingRecord> entities, Context context) {
		super(entities, context);
	}

	@Override
	protected void setViewContent(final ProducingRecord t, View view) {
		setTextOnView(R.id.producing_record_changeCount_show,
				Integer.toString(t.getChangeCount()), view);
		if (t.getId().getChangeDate() != null) {
			setTextOnView(R.id.producing_record_changeDate_show, FMT_TIME_DATE.format(t.getId().getChangeDate()), view);
		}
		setTextOnView(R.id.producing_record_houseid_show, Integer.toString(t.getId().getHouseid()), view);
		setTextOnView(R.id.producing_record_changeType_show, t.getChangeType(), view);
		
		CheckBox box = (CheckBox) view.findViewById(R.id.producing_record_item_select);
		box.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton button, boolean checked) {
				if (checked) {
					select(t);
				} else {
					deselect(t);
				}
			}
		});
	}

	@Override
	protected int getEntityViewLayoutResourceId() {
		return R.layout.producing_record_view;
	}

}
