package edu.hbut.livestock.http.coding;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import edu.hbut.livestock.entity.QuarantineApply;
import edu.hbut.livestock.entity.QuarantineApplyId;
import edu.hbut.livestock.entity.User;

public class QuarantineApplyUnMarshall extends BaseUnmarshall<QuarantineApply>{

	@Override
	protected QuarantineApply unmarshall(JSONObject jsonObject) throws JSONException, ParseException {

		QuarantineApply quarantineApply = unmarshallBaseProperty(jsonObject);
		QuarantineApplyId id=unmarshallQuarantineApplyId(jsonObject);
		quarantineApply.setId(id);
		User user=new User();
		user.setUserid(id.getUserid());
		quarantineApply.setUser(user);
		quarantineApply.setHeaderDate(id.getApplyDate());
		return quarantineApply;
	}
	
	/**
	 * 解码主键
	 * @param jsonObject
	 * @return
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	private QuarantineApplyId unmarshallQuarantineApplyId(JSONObject jsonObject) throws JSONException, ParseException {
		QuarantineApplyId id = new QuarantineApplyId();
		JSONObject idobj = jsonObject.getJSONObject(QuarantineApply.ID);
		Log.v("json", idobj.toString());
		if (idobj.getString(QuarantineApplyId.APPLY_DATE) != "null") {
			id.setApplyDate(new java.sql.Date(FMT.parse(
					idobj.getString(QuarantineApplyId.APPLY_DATE))
					.getTime()));
		}
		id.setUserid(idobj.getString(QuarantineApplyId.USERID));
		return id;
	}
	
	/**
	 * 解码普通属性
	 * 
	 * @param jsonObject
	 * @return
	 * @throws ParseException
	 * @throws JSONException
	 */
	private QuarantineApply unmarshallBaseProperty(JSONObject jsonObject) throws ParseException, JSONException {
		QuarantineApply quarantineApply = new QuarantineApply();
		quarantineApply.setCount(jsonObject.getInt(QuarantineApply.COUNT));
		if (jsonObject.getString(QuarantineApply.FLAG) != "null") {
			quarantineApply.setFlag(jsonObject.getString(QuarantineApply.FLAG));
		}
		if (jsonObject.getString(QuarantineApply.OPERATER) != "null") {
			quarantineApply.setOperator(jsonObject
					.getString(QuarantineApply.OPERATER));
		}
		quarantineApply.setResult(jsonObject.getString(QuarantineApply.RESULT));
		return quarantineApply;
	}

	@Override
	protected List<QuarantineApply> getList(int size) {
		return new ArrayList<QuarantineApply>(size);
	}

}
