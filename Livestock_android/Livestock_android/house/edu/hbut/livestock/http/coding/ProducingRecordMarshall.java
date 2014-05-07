package edu.hbut.livestock.http.coding;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import edu.hbut.livestock.entity.HouseId;
import edu.hbut.livestock.entity.ProducingRecord;
import edu.hbut.livestock.entity.ProducingRecordId;

/**
 * 
 * @author Hang
 * 
 */
public class ProducingRecordMarshall extends
		BaseGETMarshall<ProducingRecord, ProducingRecordId> {

	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy/MM/dd:HH:mm:ss");

	@Override
	public String marshall(ProducingRecord t) {
		String[] params = new String[] { ProducingRecord.CHANGE_COUNT,
				Integer.toString(t.getChangeCount()),

				ProducingRecord.CHANGE_DATE,
				format.format(t.getId().getChangeDate()),

				ProducingRecord.CHANGE_TYPE, t.getChangeType(),

				ProducingRecord.HOUSEID,
				Integer.toString(t.getId().getHouseid()),

				ProducingRecord.USERID, t.getId().getUserid() };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

	@Override
	public String marshallId(ProducingRecordId id) {
		return toUri(ProducingRecord.HOUSEID,
				Integer.toString(id.getHouseid()), ProducingRecord.USERID,
				id.getUserid(), ProducingRecord.CHANGE_DATE,
				format.format(id.getChangeDate()));
	}

	@Override
	public String marshall(ProducingRecordId id, Date headerDate, int start,
			int count) {
		String[] params = new String[] { ProducingRecord.HOUSEID,
				Integer.toString(id.getHouseid()),

				ProducingRecord.USERID, id.getUserid(),

				ProducingRecord.CHANGE_DATE, format.format(id.getChangeDate()),

				START_LINE, Integer.toString(start),

				MAX_COUNT, Integer.toString(count) };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(ProducingRecordId id, Date headerDate) {
		StringBuilder sb = new StringBuilder(marshallId(id));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(ProducingRecord t, int start, int count) {
		String[] params = new String[] { ProducingRecord.CHANGE_COUNT,
				Integer.toString(t.getChangeCount()),

				ProducingRecord.CHANGE_DATE,
				format.format(t.getId().getChangeDate()),

				ProducingRecord.CHANGE_TYPE, t.getChangeType(),

				ProducingRecord.HOUSEID,
				Integer.toString(t.getId().getHouseid()),

				ProducingRecord.USERID, t.getId().getUserid(),

				START_LINE, Integer.toString(start),

				MAX_COUNT, Integer.toString(count) };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

	public String marshallHouseQuery(int houseid, int start, int count,
			Date headerDate) {
		String[] params = new String[] { HouseId.HOUSEID,
				Integer.toString(houseid),

				START_LINE, Integer.toString(start),

				MAX_COUNT, Integer.toString(count) };
		
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

}
