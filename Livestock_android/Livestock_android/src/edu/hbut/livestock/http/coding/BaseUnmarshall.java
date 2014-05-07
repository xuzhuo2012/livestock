package edu.hbut.livestock.http.coding;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Hang
 * 
 * @param <T>
 *            需要还原的对象的类型
 */
public abstract class BaseUnmarshall<T> implements Unmarshall<T> {

	@SuppressLint("SimpleDateFormat")
	protected static final SimpleDateFormat FMT = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss");

	@Override
	public String unmarshallInfo(String result) throws MarshallException {
		if (result == null) {
			return null;
		}
		try {
			JSONObject object = new JSONObject(result);
			return object.getString(BASE_RESULT_NAME);
		} catch (JSONException e) {
			throw new MarshallException(e);
		}
	}

	@Override
	public T unmarshallObject(String result) throws MarshallException {
		Log.v("json", result);
		if (result == null) {
			return null;
		}
		try {
			JSONObject resultObject = new JSONObject(result);
			resultObject = resultObject.getJSONObject(BASE_RESULT_NAME);
			if (resultObject == null) {
				return null;
			}
			return unmarshall(resultObject);
		} catch (Exception e) {
			throw new MarshallException(e);
		}
	}

	@Override
	public List<T> unmarshallList(String result) throws MarshallException {
		if (result == null) {
			return null;
		}
		try {
			JSONObject resultObject = new JSONObject(result);
			if (resultObject.getString(BASE_RESULT_NAME) == "null") {
				return new ArrayList<T>(0);
			}
			JSONArray jsonArray = resultObject.getJSONArray(BASE_RESULT_NAME);
			if (jsonArray == null) {
				return null;
			}
			List<T> list = getList(jsonArray.length());
			for (int i = jsonArray.length() - 1; i >= 0; i--) {
				list.add(unmarshall(jsonArray.getJSONObject(i)));
			}
			return list;
		} catch (Exception e) {
			throw new MarshallException(e);
		}
	}

	/**
	 * 通过json对象得到实体类的对象
	 * 
	 * @param jsonObject
	 * @return
	 * @throws ParseException
	 * @throws JSONException
	 */
	protected abstract T unmarshall(JSONObject jsonObject)
			throws JSONException, ParseException;

	/**
	 * 实例化泛型集合
	 * 
	 */
	protected abstract List<T> getList(int size);

}
