package edu.hbut.livestock.http.coding;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.hbut.livestock.entity.Antiepidemic;
import edu.hbut.livestock.entity.AntiepidemicId;
import edu.hbut.livestock.entity.Department;
import edu.hbut.livestock.entity.House;
import edu.hbut.livestock.entity.HouseId;
import edu.hbut.livestock.entity.MonitorItem;
import edu.hbut.livestock.entity.User;

public class AntiepidemicUnMarshall extends BaseUnmarshall<Antiepidemic>{

	@Override
	protected Antiepidemic unmarshall(JSONObject jsonObject) throws JSONException, ParseException{
			Antiepidemic antiepidemic=unmarshallBaseProperty(jsonObject);  //解码普通属性
			
			AntiepidemicId antiepidemicId=unmarshallId(jsonObject); //解码主键
			antiepidemic.setId(antiepidemicId);
			
			User user = new User();
			user.setUserid(antiepidemicId.getUserid());
			antiepidemic.setUser(user);
			
			House house = new House();
			HouseId houseid = new HouseId();
			houseid.setHouseid(antiepidemicId.getHouseid());
			house.setId(houseid);
			antiepidemic.setHouse(house);
			
			MonitorItem item = new MonitorItem();
			item.setItemid(antiepidemicId.getMonitorItem());
			antiepidemic.setMonitorItem(item);
			
			return antiepidemic;
	}
	/**
	 * 解码主键
	 * @param jsonObject
	 * @return
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	private AntiepidemicId unmarshallId(JSONObject jsonObject) throws JSONException, ParseException {
		AntiepidemicId antiepidemicId=new AntiepidemicId();
		JSONObject antiepidemicObject=jsonObject.getJSONObject(Antiepidemic.ID);
		JSONObject itemObject=jsonObject.getJSONObject(AntiepidemicId.MONITOR_ITEM);
		antiepidemicId.setAntiepidemicDate(new java.sql.Date(FMT.parse(antiepidemicObject.getString(AntiepidemicId.ANTIEPIDEMIC_DATE)).getTime()));
		antiepidemicId.setHouseid(antiepidemicObject.getInt(AntiepidemicId.HOUSEID));
		antiepidemicId.setUserid(antiepidemicObject.getString(AntiepidemicId.USERID));
		antiepidemicId.setMonitorItem(itemObject.getString(MonitorItem.ID));
		return antiepidemicId;
	}

	
	/**
	 * 解码普通属性
	 * @param jsonObject
	 * @return
	 * @throws JSONException 
	 */
	private Antiepidemic unmarshallBaseProperty(JSONObject jsonObject) throws JSONException {
		Antiepidemic antiepidemic=new Antiepidemic();
		antiepidemic.setSampleCount(jsonObject.getInt(Antiepidemic.SAMPLE_COUNT));
		JSONObject json = new JSONObject(jsonObject.getString(Antiepidemic.DEPARTMENT));
		Department depart = new Department();
		depart.setDepartmentName(json.getString(Department.DEPARTMENT_ID));
		depart.setDepartmentAddress(json.getString(Department.DEPARTMENT_ADDRESS));
		depart.setContactName(json.getString(Department.CONTACT_NAME));
		depart.setTellphone(json.getString(Department.TELLPHONE));
		antiepidemic.setDepartment(depart);
		antiepidemic.setMonitorResult(jsonObject.getString(Antiepidemic.MONITOR_RESULT));
		antiepidemic.setDealResult(jsonObject.getString(Antiepidemic.DEAL_RESULT));
		return antiepidemic;
		
	}

	@Override
	protected List<Antiepidemic> getList(int size) {
		return new ArrayList<Antiepidemic>(size);
	}

}
