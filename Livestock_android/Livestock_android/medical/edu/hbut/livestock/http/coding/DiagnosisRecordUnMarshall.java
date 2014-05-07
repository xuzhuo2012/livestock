package edu.hbut.livestock.http.coding;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.hbut.livestock.entity.DiagnosisRecord;
import edu.hbut.livestock.entity.DiagnosisRecordId;
import edu.hbut.livestock.entity.Livestock;

public class DiagnosisRecordUnMarshall extends BaseUnmarshall<DiagnosisRecord>{

	@Override
	protected DiagnosisRecord unmarshall(JSONObject jsonObject) throws JSONException, ParseException{
			DiagnosisRecord diagnosisRecord=unmarshallBaseProperty(jsonObject);  //解码普通属性
			
			DiagnosisRecordId diagnosisRecordId=unmarshallId(jsonObject); //解码主键
			diagnosisRecord.setId(diagnosisRecordId);
			
			Livestock livestock = new Livestock();
			livestock.setLivestockid(diagnosisRecordId.getLivestockid());
			diagnosisRecord.setLivestock(livestock);
			
			return diagnosisRecord;
	}
	/**
	 * 解码主键
	 * @param jsonObject
	 * @return
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	private DiagnosisRecordId unmarshallId(JSONObject jsonObject) throws JSONException, ParseException {
		DiagnosisRecordId diagnosisRecordId=new DiagnosisRecordId();
		JSONObject diagnosisRecordObject=jsonObject.getJSONObject(DiagnosisRecord.ID);
		diagnosisRecordId.setDiagnosisDate(new java.sql.Date(FMT.parse(diagnosisRecordObject.getString(DiagnosisRecordId.DIAGNOSISDATE)).getTime()));
		diagnosisRecordId.setLivestockid(diagnosisRecordObject.getString(DiagnosisRecordId.LIVESTOCKID));
		return diagnosisRecordId;
	}

	
	/**
	 * 解码普通属性
	 * @param jsonObject
	 * @return
	 * @throws JSONException 
	 */
	private DiagnosisRecord unmarshallBaseProperty(JSONObject jsonObject) throws JSONException {
		DiagnosisRecord diagnosisRecord=new DiagnosisRecord();
		diagnosisRecord.setAge(jsonObject.getInt(DiagnosisRecord.AGE));
		diagnosisRecord.setReason(jsonObject.getString(DiagnosisRecord.REASON));
		diagnosisRecord.setDoctor(jsonObject.getString(DiagnosisRecord.DOCTOR));
		diagnosisRecord.setMedicine(jsonObject.getString(DiagnosisRecord.MEDICINE));
		diagnosisRecord.setMethod(jsonObject.getString(DiagnosisRecord.METHOD));
		diagnosisRecord.setResult(jsonObject.getString(DiagnosisRecord.RESULT));
		return diagnosisRecord;
		
	}

	@Override
	protected List<DiagnosisRecord> getList(int size) {
		return new ArrayList<DiagnosisRecord>(size);
	}

}
