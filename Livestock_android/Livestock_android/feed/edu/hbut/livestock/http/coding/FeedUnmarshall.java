package edu.hbut.livestock.http.coding;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.hbut.livestock.entity.Feed;

/**
 * Feed表的操作返回信息解码
 * 
 * @author Hang
 * 
 */
public class FeedUnmarshall extends BaseUnmarshall<Feed> {

	/**
	 * 将json对象转化成实体类对象
	 * 
	 * @param resultobj
	 * @return
	 * @throws JSONException
	 * @throws ParseException
	 */
	protected Feed unmarshall(JSONObject jsonObject) throws JSONException,
			ParseException {
		Feed feed = new Feed();
		feed.setBatchNumber(jsonObject.getString(Feed.BATCH_NUMBER));
		String counts = jsonObject.getString(Feed.CHANGE_COUNT);
		if (counts != "null") {
			feed.setChangeCount(jsonObject.getInt(Feed.CHANGE_COUNT));
		}
		feed.setChangeType(jsonObject.getString(Feed.CHANGE_TYPE));
		if (jsonObject.getString(Feed.CHANGE_DATE) != "null") {
			feed.setChangeDate(new Timestamp(FMT.parse(
					jsonObject.getString(Feed.CHANGE_DATE)).getTime()));
		}
		feed.setFactory(jsonObject.getString(Feed.FACTORY));
		feed.setFeedName(jsonObject.getString(Feed.FEED_NAME));
		feed.setRemark(jsonObject.getString(Feed.REMARK));
		/*
		 * headerDate，用于删除与更新时可指定表名
		 */
		if (jsonObject.getString(Feed.HEADER_DATE) != "null") {
			feed.setHeaderDate(new Date(FMT.parse(
					jsonObject.getString(Feed.HEADER_DATE)).getTime()));
		}
		return feed;
	}

	@Override
	protected List<Feed> getList(int size) {
		return new ArrayList<Feed>(size);
	}

}
