package edu.hbut.livestock.util;

import java.util.List;

import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.House;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * 圈舍显示方式控制
 * 
 * @author Hang
 * 
 */
public class HouseViewAdapter extends EntityViewAdapter<House> {

	public HouseViewAdapter() {
		super();
	}

	public HouseViewAdapter(List<House> entities, Context context) {
		super(entities, context);
	}

	@Override
	protected void setViewContent(final House t, View view) {
		setTextOnView(R.id.house_base_info_house_id_show, Integer.toString(t.getId().getHouseid()), view);
		setTextOnView(R.id.house_base_info_animalType_show, t.getAnimalType(), view);
		setTextOnView(R.id.house_base_info_livestockCount_show, Integer.toString(t.getLivestockCount()), view);
		CheckBox box = (CheckBox) view.findViewById(R.id.house_base_info_item_select);
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
		return R.layout.house_view;
	}

}
