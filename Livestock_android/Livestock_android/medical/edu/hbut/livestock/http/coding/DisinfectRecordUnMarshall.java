package edu.hbut.livestock.http.coding;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.hbut.livestock.entity.DisinfectRecord;
import edu.hbut.livestock.entity.DisinfectRecordId;
import edu.hbut.livestock.entity.User;

public class DisinfectRecordUnMarshall extends BaseUnmarshall<DisinfectRecord>{

	@Override
	protected DisinfectRecord unmarshall(JSONObject jsonObject) throws JSONException, ParseException{
			DisinfectRecord disinfectRecord=unmarshallBaseProperty(jsonObject);  //������ͨ����
			
			DisinfectRecordId disinfectRecordId=unmarshallId(jsonObject); //��������
			disinfectRecord.setId(disinfectRecordId);
			
			User user=new User();
			user.setUserid(disinfectRecordId.getUserid());
			disinfectRecord.setUser(user);
			
			return disinfectRecord;
	}
	/**
	 * ��������
	 * @param jsonObject
	 * @return
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	private DisinfectRecordId unmarshallId(JSONObject jsonObject) throws JSONException, ParseException {
		DisinfectRecordId disinfectRecordId=new DisinfectRecordId();
		JSONObject disinfectrecordObject=jsonObject.getJSONObject(DisinfectRecord.ID);
		disinfectRecordId.setDisinfectDate(new java.sql.Date(FMT.parse(disinfectrecordObject.getString(DisinfectRecordId.DISINFECT_DATE)).getTime()));
		disinfectRecordId.setUserid(disinfectrecordObject.getString(DisinfectRecordId.USERID));
		return disinfectRecordId;
	}

	
	/**
	 * ������ͨ����
	 * @param jsonObject
	 * @return
	 * @throws JSONException 
	 */
	private DisinfectRecord unmarshallBaseProperty(JSONObject jsonObject) throws JSONException {
		DisinfectRecord disinfectRecord=new DisinfectRecord();
		disinfectRecord.setDisinfectMedicineName(jsonObject.getString(DisinfectRecord.DISINFECT_MEDICINE_NAME));
		disinfectRecord.setDisinfectPlace(jsonObject.getString(DisinfectRecord.DISINFECT_PLACE));
		disinfectRecord.setDosage(jsonObject.getInt(DisinfectRecord.DOSAGE));
		disinfectRecord.setMedicineFactory(jsonObject.getString(DisinfectRecord.MEDICINE_FACTORY));
		disinfectRecord.setMethod(jsonObject.getString(DisinfectRecord.METHOD));
		disinfectRecord.setOperator(jsonObject.getString(DisinfectRecord.OPERATOR));
		return disinfectRecord;
		
	}

	@Override
	protected List<DisinfectRecord> getList(int size) {
		return new ArrayList<DisinfectRecord>(size);
	}

}
