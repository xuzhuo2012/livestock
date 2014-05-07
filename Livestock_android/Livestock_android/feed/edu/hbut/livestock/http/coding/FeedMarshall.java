package edu.hbut.livestock.http.coding;

import java.sql.*;
import java.text.*;

import edu.hbut.livestock.entity.Feed;
import edu.hbut.livestock.http.coding.BaseGETMarshall;

/**
 * 将Feed类相关的参数编码
 * 
 * @author Hang
 * 
 */
public class FeedMarshall extends BaseGETMarshall<Feed, Timestamp> {

        protected final static SimpleDateFormat FMT = new SimpleDateFormat("yyyy/MM/dd:HH:mm:ss");

	@Override
	public String marshall(Feed t) {
		String preuri = toUri(Feed.CHANGE_DATE, FMT.format(t.getChangeDate()),
				Feed.FEED_NAME, t.getFeedName(), Feed.FACTORY, t.getFactory(),
				Feed.BATCH_NUMBER, t.getBatchNumber(), Feed.CHANGE_TYPE,
				t.getChangeType(), Feed.CHANGE_COUNT,
				Integer.toString(t.getChangeCount()), Feed.REMARK,
				t.getRemark());
		StringBuilder sb = new StringBuilder(preuri);
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

	@Override
	public String marshallId(Timestamp id) {
		return toUri(Feed.CHANGE_DATE, FMT.format(id));
	}

	@Override
	public String marshall(Timestamp id, Date headerDate, int start, int count) {
		StringBuilder sb = new StringBuilder(toUri(Feed.CHANGE_DATE,
				FMT.format(id), START_LINE, Integer.toString(start), MAX_COUNT,
				Integer.toString(count)));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(Timestamp id, Date headerDate) {
		StringBuilder sb = new StringBuilder(toUri(Feed.CHANGE_DATE,
				FMT.format(id)));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(Feed t, int start, int count) {
		StringBuilder sb = new StringBuilder(marshall(t));
		sb.append('&');
		sb.append(START_LINE);
		sb.append('=');
		sb.append(start);
		sb.append('&');
		sb.append(MAX_COUNT);
		sb.append('=');
		sb.append(count);
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

	public String marshallQueryTime(Date startTime, Date endTime, int start, int count) {
		String[] params = {
			"startTime", FMT.format(startTime), "endTime", FMT.format(endTime),
			START_LINE, Integer.toString(start),
			MAX_COUNT, Integer.toString(count)
		};
		return toUri(params);
	}
}
