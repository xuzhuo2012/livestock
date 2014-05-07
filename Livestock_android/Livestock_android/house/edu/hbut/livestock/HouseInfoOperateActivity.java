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
	 * Ȧ���������
	 */
	protected EditText houseIdEditText;

	/**
	 * �������������
	 */
	protected EditText animalTypEditText;

	/**
	 * �������������
	 */
	protected EditText livestockCounText;

	/**
	 * �ύ��ť
	 */
	protected Button submitButton;

	/**
	 * ȡ����ť
	 */
	protected Button cancelButton;

	/**
	 * Զ�̵���
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
	 * �ύ�¼�������
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
				showLongToast("Ȧ��Ų���Ϊ��");
				return null;
			}
			String animalType = animalTypEditText.getText().toString();
			if (animalType == null || animalType.trim().equals("")) {
				showLongToast("�������Ͳ���Ϊ��");
				return null;
			}
			String livestockCount = livestockCounText.getText().toString();
			if (livestockCount == null || livestockCount.trim().equals("")) {
				showLongToast("�������Ͳ���Ϊ��");
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
