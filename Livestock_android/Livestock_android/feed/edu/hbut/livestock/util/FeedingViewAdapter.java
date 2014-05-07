package edu.hbut.livestock.util;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.FeedingRecord;

/**
 * 
 * @author Hang
 * 
 */
public class FeedingViewAdapter extends TableEntityViewAdapter<FeedingRecord> {

	public FeedingViewAdapter() {
		super();
	}

	public FeedingViewAdapter(List<FeedingRecord> entities, Context context) {
		super(entities, context);
	}

	@Override
	protected void setViewContent(final FeedingRecord t, View view) {
		setTextOnView(R.id.feeding_animalType_show, t.getAnimalType(), view);
		setTextOnView(R.id.feeding_batchNumber_show,
				t.getId().getBatchNumber(), view);
		if (t.getEndTime() != null) {
			setTextOnView(R.id.feeding_endtime_show,
					fmt.format(t.getEndTime()), view);
		}
		setTextOnView(R.id.feeding_factory_show, t.getId().getFactory(), view);
		setTextOnView(R.id.feeding_dosage_show,
				Integer.toString(t.getDosage()), view);
		setTextOnView(R.id.feeding_feed_name_show, t.getId().getFeedName(),
				view);
		setTextOnView(R.id.feeding_houseid_show,
				Integer.toString(t.getId().getHouseid()), view);
		setTextOnView(R.id.feeding_starttime_show,
				fmt.format(t.getStartTime()), view);
		CheckBox box = (CheckBox) view.findViewById(R.id.feeding_item_select);
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
		return R.layout.feeding_view;
	}
}
