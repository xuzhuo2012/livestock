package edu.hbut.livestock.http.coding;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.hbut.livestock.entity.Feed;
import edu.hbut.livestock.entity.FeedingRecord;
import edu.hbut.livestock.entity.FeedingRecordId;

/**
 * 圈舍饲料使用记录管理解码器
 * 
 * @author Hang
 * 
 */
public class FeedingUnmarshall extends BaseUnmarshall<FeedingRecord> {

	/**
	 * 结果JSON对象到实体对象的转换
	 * 
	 * @param resultObject
	 * @return
	 * @throws JSONException
	 * @throws ParseException
	 */
	protected FeedingRecord unmarshall(JSONObject jsonObject)
			throws JSONException, ParseException {

		FeedingRecord record = unmarshallNomalProperty(jsonObject);

		FeedingRecordId id = unmarshallId(jsonObject);

		record.setId(id);
		return record;
	}

	/**
	 * 解码非主键
	 * 
	 * @param jsonObject
	 * @return
	 * @throws JSONException
	 * @throws ParseException
	 */
	private FeedingRecord unmarshallNomalProperty(JSONObject jsonObject)
			throws JSONException, ParseException {
		FeedingRecord record = new FeedingRecord();
		record.setAnimalType(jsonObject.getString(FeedingRecord.ANIMAL_TYPE));
		record.setDosage(jsonObject.getInt(FeedingRecord.DOSAGE));
		record.setEndTime(new java.sql.Date(FMT.parse(
				jsonObject.getString(FeedingRecord.ENND_TIME)).getTime()));
		record.setStartTime(new java.sql.Date(FMT.parse(
				jsonObject.getString(FeedingRecord.START_TIME)).getTime()));
		record.setUserid(jsonObject.getString(FeedingRecord.USERID));
		/*
		 * headerDate，用于删除与更新时可指定表名
		 */
		if (jsonObject.getString(Feed.HEADER_DATE) != "null") {
			record.setHeaderDate(new Date(FMT.parse(
					jsonObject.getString(FeedingRecord.HEADER_DATE)).getTime()));
		}
		return record;
	}

	/**
	 * 解码主键
	 * 
	 * @param jsonObject
	 * @return
	 * @throws JSONException
	 */
	private FeedingRecordId unmarshallId(JSONObject jsonObject)
			throws JSONException {
		jsonObject = jsonObject.getJSONObject(FeedingRecord.ID);

		FeedingRecordId id = new FeedingRecordId();

		id.setBatchNumber(jsonObject.getString(FeedingRecordId.BATCH_NUMBER));
		id.setFactory(jsonObject.getString(FeedingRecordId.FACTORY));
		id.setFeedName(jsonObject.getString(FeedingRecordId.FEED_NAME));
		id.setHouseid(jsonObject.getInt(FeedingRecordId.HOUSEID));
		return id;
	}

	@Override
	protected List<FeedingRecord> getList(int size) {
		return new ArrayList<FeedingRecord>(size);
	}

}
