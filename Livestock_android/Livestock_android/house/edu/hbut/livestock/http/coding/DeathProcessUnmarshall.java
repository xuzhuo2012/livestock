package edu.hbut.livestock.http.coding;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.hbut.livestock.entity.Department;
import edu.hbut.livestock.entity.DeathProcess;
import edu.hbut.livestock.entity.Livestock;

/**
 * 
 * @author Hang
 * 
 */
public class DeathProcessUnmarshall extends BaseUnmarshall<DeathProcess> {

	@Override
	protected DeathProcess unmarshall(JSONObject jsonObject)
			throws JSONException, ParseException {

		DeathProcess process = unmarshallBaseProperty(jsonObject);

		Department department = unmarshallDepartment(jsonObject);
		process.setDepartment(department);

		Livestock livestock = unmarshallLivestock(jsonObject);
		process.setLivestock(livestock);

		return process;
	}

	/**
	 * 解码livestock属性
	 * 
	 * @param jsonObject
	 * @return
	 * @throws JSONException
	 * @throws ParseException
	 */
	private Livestock unmarshallLivestock(JSONObject jsonObject)
			throws JSONException, ParseException {
		JSONObject livestockobj = jsonObject
				.getJSONObject(DeathProcess.LIVESTOCK);
		Livestock livestock = new Livestock();
		if (livestockobj.getString(Livestock.CREATE_TIME) != "null") {
			livestock.setCreateTime(new java.sql.Date(FMT.parse(
					livestockobj.getString(Livestock.CREATE_TIME)).getTime()));
		}
		livestock.setLivestockid(livestockobj.getString(Livestock.LIVESTOCKID));
		livestock.setLivestockType(livestockobj.getString(Livestock.LIVESTOCK_TYPE));
		return livestock;
	}

	/**
	 * 解码Department属性
	 * 
	 * @param jsonObject
	 * @return
	 * @throws JSONException
	 */
	private Department unmarshallDepartment(JSONObject jsonObject)
			throws JSONException {
		Department department = new Department();
		JSONObject departmentObject = jsonObject
				.getJSONObject(DeathProcess.DEPARTMENT);
		department.setDepartmentName(departmentObject
				.getString(Department.DEPARTMENT_ID));
		department.setContactName(departmentObject
				.getString(Department.CONTACT_NAME));
		department.setDepartmentAddress(departmentObject
				.getString(Department.DEPARTMENT_ADDRESS));
		department.setTellphone(departmentObject
				.getString(Department.TELLPHONE));
		return department;
	}

	/**
	 * 解码普通属性
	 * 
	 * @param jsonObject
	 * @return
	 * @throws ParseException
	 * @throws JSONException
	 */
	private DeathProcess unmarshallBaseProperty(JSONObject jsonObject)
			throws ParseException, JSONException {
		DeathProcess process = new DeathProcess();
		process.setProcessDate(new Date(FMT.parse(
				jsonObject.getString(DeathProcess.PROCESS_DATE)).getTime()));
		process.setReason(jsonObject.getString(DeathProcess.REASON));
		process.setRemark(jsonObject.getString(DeathProcess.REMARK));
		return process;
	}

	@Override
	protected List<DeathProcess> getList(int size) {
		return new ArrayList<DeathProcess>(size);
	}

}
