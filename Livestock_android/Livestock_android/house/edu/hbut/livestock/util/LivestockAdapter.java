package edu.hbut.livestock.util;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.Livestock;

/**
 * 
 * @author Hang
 * 
 */
public class LivestockAdapter extends TableEntityViewAdapter<Livestock> {

	public LivestockAdapter() {
		super();
	}

	public LivestockAdapter(List<Livestock> entities, Context context) {
		super(entities, context);
	}

	@Override
	protected void setViewContent(final Livestock t, View view) {
		setTextOnView(R.id.livestock_id_show, t.getLivestockid(), view);
		setTextOnView(R.id.livestock_create_time_show,
				fmt.format(t.getCreateTime()), view);
		setTextOnView(R.id.livestock_type_show, t.getLivestockType(), view);
		
		CheckBox box = (CheckBox) view.findViewById(R.id.livestock_item_select);
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
		return R.layout.livestock_view;
	}

}
