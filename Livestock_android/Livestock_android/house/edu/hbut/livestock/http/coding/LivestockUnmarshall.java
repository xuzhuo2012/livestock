package edu.hbut.livestock.http.coding;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.hbut.livestock.entity.Livestock;

/**
 * 
 * @author Hang
 * 
 */
public class LivestockUnmarshall extends BaseUnmarshall<Livestock> {

	@Override
	protected Livestock unmarshall(JSONObject jsonObject) throws JSONException,
			ParseException {
		Livestock livestock = new Livestock();
		livestock.setLivestockid(jsonObject.getString(Livestock.LIVESTOCKID));
		livestock.setLivestockType(jsonObject
				.getString(Livestock.LIVESTOCK_TYPE));
		livestock.setCreateTime(new java.sql.Date(FMT.parse(
				jsonObject.getString(Livestock.CREATE_TIME)).getTime()));
		if (jsonObject.getString(Livestock.HEADER_DATE) != null) {
			livestock.setHeaderDate(new java.sql.Date(FMT.parse(
					jsonObject.getString(Livestock.HEADER_DATE)).getTime()));
		}
		return livestock;
	}

	@Override
	protected List<Livestock> getList(int size) {
		return new ArrayList<Livestock>(size);
	}

}
