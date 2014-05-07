package edu.hbut.livestock.http.coding;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.hbut.livestock.entity.MedicineRecord;

public class MedicineRecordUnMarshall extends BaseUnmarshall<MedicineRecord>{

	@Override
	protected MedicineRecord unmarshall(JSONObject jsonObject) throws JSONException, ParseException{
			MedicineRecord medicineRecord=unmarshallBaseProperty(jsonObject);  //解码普通属性
			
			
			return medicineRecord;
	}
	
	/**
	 * 解码普通属性
	 * @param jsonObject
	 * @return
	 * @throws JSONException 
	 */
	private MedicineRecord unmarshallBaseProperty(JSONObject jsonObject) throws JSONException,ParseException {
		MedicineRecord medicineRecord=new MedicineRecord();
		medicineRecord.setStartTime(new Date(FMT.parse(jsonObject.getString(MedicineRecord.STARTTIME)).getTime()));
		medicineRecord.setProductName(jsonObject.getString(MedicineRecord.PRODUCTNAME));
		medicineRecord.setMedicineType(jsonObject.getString(MedicineRecord.MEDICINETYPE));
		medicineRecord.setStandard(jsonObject.getString(MedicineRecord.STANDARD));
		medicineRecord.setValidityPeriod(Integer.parseInt(jsonObject.getString(MedicineRecord.VALIDITYPERIOD)));
		medicineRecord.setFactory(jsonObject.getString(MedicineRecord.FACTORY));
		medicineRecord.setBatchNumber(Integer.parseInt(jsonObject.getString(MedicineRecord.BATCHNUMBER)));
		medicineRecord.setDosage(jsonObject.getInt(MedicineRecord.DOSAGE));
		medicineRecord.setEndTime(new Date(FMT.parse(jsonObject.getString(MedicineRecord.ENDTIME)).getTime()));
		return medicineRecord;
		
	}

	@Override
	protected List<MedicineRecord> getList(int size) {
		return new ArrayList<MedicineRecord>(size);
	}

}
