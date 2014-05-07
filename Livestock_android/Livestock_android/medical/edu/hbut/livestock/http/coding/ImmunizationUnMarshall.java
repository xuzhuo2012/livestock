package edu.hbut.livestock.http.coding;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.hbut.livestock.entity.House;
import edu.hbut.livestock.entity.HouseId;
import edu.hbut.livestock.entity.Immunization;
import edu.hbut.livestock.entity.ImmunizationId;
import edu.hbut.livestock.entity.User;

public class ImmunizationUnMarshall extends BaseUnmarshall<Immunization>{

	@Override
	protected Immunization unmarshall(JSONObject jsonObject) throws JSONException, ParseException{
			Immunization immunization=unmarshallBaseProperty(jsonObject);  //解码普通属性
			
			ImmunizationId immunizationId=unmarshallId(jsonObject); //解码主键
			immunization.setId(immunizationId);
			
			User user=new User();
			user.setUserid(immunizationId.getUserid());
			immunization.setUser(user);
			
			House house = new House();
			HouseId houseid = new HouseId();
			houseid.setHouseid(immunizationId.getHouseid());
			house.setId(houseid);
			immunization.setHouse(house);
			
			return immunization;
	}
	/**
	 * 解码主键
	 * @param jsonObject
	 * @return
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	private ImmunizationId unmarshallId(JSONObject jsonObject) throws JSONException, ParseException {
		ImmunizationId immunizationId=new ImmunizationId();
		JSONObject immunizationObject=jsonObject.getJSONObject(Immunization.ID);
		immunizationId.setImmunizationTime(new java.sql.Date(FMT.parse(immunizationObject.getString(ImmunizationId.IMMUNIZATIONTIME)).getTime()));
		immunizationId.setUserid(immunizationObject.getString(ImmunizationId.USERID));
		immunizationId.setHouseid(immunizationObject.getInt(ImmunizationId.HOUSEID));
		return immunizationId;
	}

	
	/**
	 * 解码普通属性
	 * @param jsonObject
	 * @return
	 * @throws JSONException 
	 */
	private Immunization unmarshallBaseProperty(JSONObject jsonObject) throws JSONException {
		Immunization immunization=new Immunization();
		immunization.setTotalCount(jsonObject.getInt(Immunization.TOTALCOUNT));
		immunization.setVaccine(jsonObject.getString(Immunization.VACCINE));
		immunization.setVaccineFactory(jsonObject.getString(Immunization.VACCINEFACTORY));
		immunization.setBatchNumber(jsonObject.getString(Immunization.BATCHNUMBER));
		immunization.setDosage(jsonObject.getInt(Immunization.DOSAGE));
		immunization.setImmunizationMethod(jsonObject.getString(Immunization.IMMUNIZATIONMETHOD));
		return immunization;
		
	}

	@Override
	protected List<Immunization> getList(int size) {
		return new ArrayList<Immunization>(size);
	}

}
