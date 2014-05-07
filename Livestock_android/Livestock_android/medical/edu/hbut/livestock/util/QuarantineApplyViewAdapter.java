package edu.hbut.livestock.util;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.QuarantineApply;

/**
 * 用于显示用户
 * 
 * @author Hang
 * 
 */
public class QuarantineApplyViewAdapter extends TableEntityViewAdapter<QuarantineApply> {

	public QuarantineApplyViewAdapter() {
		super();
	}

	public QuarantineApplyViewAdapter(List<QuarantineApply> entities, Context context) {
		super(entities, context);
	}

	@Override
	protected int getEntityViewLayoutResourceId() {
		return R.layout.quarantine_apply_view;
	}

	@Override
	protected void setViewContent(final QuarantineApply t, View view) {
		if (t.getId().getApplyDate() != null) {
			setTextOnView(R.id.quarantine_apply_date_show,
					fmt.format(t.getId().getApplyDate()), view);
		}
		setTextOnView(R.id.quarantine_apply_flag_show, t.getFlag(), view);
		setTextOnView(R.id.quarantine_count_show, Integer.toString(t.getCount()), view);
		setTextOnView(R.id.quarantine_apply_result_show, t.getResult(), view);
		setTextOnView(R.id.quarantine_apply_operator_show, t.getOperator(), view);
		
		CheckBox box = (CheckBox) view.findViewById(R.id.quarantine_apply_item_select);
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

}
