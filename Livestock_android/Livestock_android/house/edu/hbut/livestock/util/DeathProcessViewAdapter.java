package edu.hbut.livestock.util;

import java.util.List;

import android.content.Context;
import android.view.View;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.DeathProcess;

/**
 * 
 * @author Hang
 * 
 */
public class DeathProcessViewAdapter extends TableEntityViewAdapter<DeathProcess> {

	public DeathProcessViewAdapter() {
		super();
	}

	public DeathProcessViewAdapter(List<DeathProcess> entities, Context context) {
		super(entities, context);
	}

	@Override
	protected void setViewContent(DeathProcess t, View view) {
		setTextOnView(R.id.death_process_date_show,
				fmt.format(t.getProcessDate()), view);
		if (t.getDepartment() != null) {
			setTextOnView(R.id.death_process_department_show, t.getDepartment()
					.getDepartmentName(), view);
		}
		if (t.getLivestock() != null) {
			setTextOnView(R.id.death_process_livestock_show, t.getLivestock()
					.getLivestockid(), view);
		}
		setTextOnView(R.id.death_process_reason_show, t.getReason(), view);
		setTextOnView(R.id.death_process_remark_show, t.getRemark(), view);
	}

	@Override
	protected int getEntityViewLayoutResourceId() {
		return R.layout.deathprocess_view;
	}

}
