package edu.hbut.livestock;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.hbut.livestock.entity.House;
import edu.hbut.livestock.entity.HouseId;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;

/**
 * 
 * @author Hang
 * 
 */
public abstract class HouseInfoOperateActivity extends RecordOperateActivity<House> {

	public static final String HOUSE_RESULT = "house";
	/**
	 * 圈舍编号输入框
	 */
	protected EditText houseIdEditText;

	/**
	 * 畜禽类型输入框
	 */
	protected EditText animalTypEditText;

	/**
	 * 畜禽数量输入框
	 */
	protected EditText livestockCounText;

	/**
	 * 提交按钮
	 */
	protected Button submitButton;

	/**
	 * 取消按钮
	 */
	protected Button cancelButton;

	/**
	 * 远程调用
	 */
	protected RemoteProcedureCall<House, HouseId> call;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResource());
		initWidget();

		call = (RemoteProcedureCall<House, HouseId>) ObjectConfigedFactory.getRemoteProcedureCallFactory().getBean(House.class);

		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				HouseInfoOperateActivity.this.finish();
			}
		});

		submitButton.setOnClickListener(new OnSubmitListener());
	}

	/**
	 * 提交事件监听器
	 * 
	 * @author Hang
	 * 
	 */
	private final class OnSubmitListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			House house = getHouse();
			if (house == null) {
				return;
			}
			request(house);
		}
		
		private House getHouse() {
			String houseid = houseIdEditText.getText().toString();
			if (houseid == null || houseid.trim().equals("")) {
				showLongToast("圈舍号不能为空");
				return null;
			}
			String animalType = animalTypEditText.getText().toString();
			if (animalType == null || animalType.trim().equals("")) {
				showLongToast("畜禽类型不能为空");
				return null;
			}
			String livestockCount = livestockCounText.getText().toString();
			if (livestockCount == null || livestockCount.trim().equals("")) {
				showLongToast("畜禽类型不能为空");
				return null;
			}
			House house = new House();
			HouseId id = new HouseId();
			id.setHouseid(Integer.parseInt(houseid));
			house.setId(id);
			house.setAnimalType(animalType);
			house.setLivestockCount(Integer.parseInt(livestockCount));
			return house;
		}
	}

}
