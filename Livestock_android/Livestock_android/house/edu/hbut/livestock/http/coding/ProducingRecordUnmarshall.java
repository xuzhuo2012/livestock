package edu.hbut.livestock.http.coding;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.hbut.livestock.entity.ProducingRecord;
import edu.hbut.livestock.entity.ProducingRecordId;

/**
 * 
 * @author Hang
 * 
 */
public class ProducingRecordUnmarshall extends BaseUnmarshall<ProducingRecord> {

	@Override
	protected ProducingRecord unmarshall(JSONObject jsonObject)
			throws JSONException, ParseException {
		ProducingRecord record = unmarshallBaseProperties(jsonObject);
		ProducingRecordId id = unmarshallId(jsonObject
				.getJSONObject(ProducingRecord.ID));
		record.setId(id);
		return record;
	}

	/**
	 * 解码普通属性
	 * 
	 * @param jsonObject
	 * @return
	 * @throws JSONException
	 */
	private ProducingRecord unmarshallBaseProperties(JSONObject jsonObject)
			throws JSONException {
		ProducingRecord record = new ProducingRecord();
		if (jsonObject.getString(ProducingRecord.CHANGE_COUNT) != "null") {
			record.setChangeCount(jsonObject
					.getInt(ProducingRecord.CHANGE_COUNT));
		}
		record.setChangeType(jsonObject.getString(ProducingRecord.CHANGE_TYPE));
		return record;
	}

	/**
	 * 解码主键
	 * 
	 * @param jsonObject
	 * @return
	 * @throws ParseException
	 * @throws JSONException
	 */
	private ProducingRecordId unmarshallId(JSONObject jsonObject)
			throws ParseException, JSONException {
		ProducingRecordId id = new ProducingRecordId();
		id.setChangeDate(new java.sql.Timestamp(FMT.parse(
				jsonObject.getString(ProducingRecordId.CHANGE_DATE)).getTime()));
		id.setHouseid(jsonObject.getInt(ProducingRecordId.HOUSEID));
		id.setUserid(jsonObject.getString(ProducingRecordId.USERID));
		return id;
	}

	@Override
	protected List<ProducingRecord> getList(int size) {
		return new ArrayList<ProducingRecord>(size);
	}

}
