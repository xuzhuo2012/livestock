package edu.hbut.livestock.http.coding;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import edu.hbut.livestock.entity.House;
import edu.hbut.livestock.entity.HouseId;
import edu.hbut.livestock.entity.User;

/**
 * 
 * @author Hang
 * 
 */
public class HouseUnmarshall extends BaseUnmarshall<House> {

	@Override
	protected House unmarshall(JSONObject jsonObject) throws JSONException,
			ParseException {
		House house = unmarshallBaseProperties(jsonObject);
		HouseId id = unmarshallId(jsonObject);
		house.setId(id);
		User user = new User();
		user.setUserid(id.getUserid());
		house.setUser(user);
		return house;
	}

	/**
	 * 解码普通属性
	 * 
	 * @param jsonObject
	 * @return
	 * @throws JSONException
	 */
	private House unmarshallBaseProperties(JSONObject jsonObject) throws JSONException {
		House house = new House();
		house.setAnimalType(jsonObject.getString(House.ANIMAL_TYPE));
		if (jsonObject.getString(House.LIVESTOCK_COUNT) != "null") {
			house.setLivestockCount(jsonObject.getInt(House.LIVESTOCK_COUNT));
		}
		return house;
	}

	/**
	 * 解码主键
	 * 
	 * @param jsonObject
	 * @return
	 * @throws JSONException
	 */
	private HouseId unmarshallId(JSONObject jsonObject) throws JSONException {
		Log.v("json", jsonObject.toString());
		HouseId id = new HouseId();
		JSONObject idobj = jsonObject.getJSONObject(House.HOUSE);
		id.setHouseid(idobj.getInt(HouseId.HOUSEID));
		id.setUserid(idobj.getString(HouseId.USERID));
		return id;
	}

	@Override
	protected List<House> getList(int size) {
		return new ArrayList<House>(size);
	}

}
