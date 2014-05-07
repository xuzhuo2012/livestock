package edu.hbut.livestock.http.coding;

import java.sql.Date;

import edu.hbut.livestock.entity.FeedingRecord;
import edu.hbut.livestock.entity.FeedingRecordId;

/**
 * »¶…·À«¡œπ‹¿Ì±‡¬Î∆˜
 * 
 * @author Hang
 * 
 */
public class FeedingMarshall extends
		BaseGETMarshall<FeedingRecord, FeedingRecordId> {

	@Override
	public String marshall(FeedingRecord t) {
		String[] params = new String[] { FeedingRecord.ANIMAL_TYPE,
				t.getAnimalType(),

				FeedingRecord.BATCH_NUMBER, t.getId().getBatchNumber(),

				FeedingRecord.DOSAGE, Integer.toString(t.getDosage()),

				FeedingRecord.ENND_TIME, FMT.format(t.getEndTime()),

				FeedingRecord.HOUSEID,
				Integer.toString(t.getId().getHouseid()),

				FeedingRecord.START_TIME, FMT.format(t.getStartTime()),

				FeedingRecord.ANIMAL_TYPE, t.getAnimalType(),

				FeedingRecord.FEED_NAME, t.getId().getFeedName(),

				FeedingRecord.FACTORY, t.getId().getFactory() };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

	@Override
	public String marshallId(FeedingRecordId id) {
		String[] params = new String[] { FeedingRecord.FEED_NAME,
				id.getFeedName(),

				FeedingRecord.FACTORY, id.getFactory(),

				FeedingRecord.HOUSEID, Integer.toString(id.getHouseid()),

				FeedingRecord.BATCH_NUMBER, id.getBatchNumber() };
		return toUri(params);
	}

	@Override
	public String marshall(FeedingRecordId id, Date headerDate, int start,
			int count) {
		StringBuilder sb = new StringBuilder(marshallId(id));
		sb.append("&");
		sb.append(toUri(START_LINE, Integer.toString(start), MAX_COUNT,
				Integer.toString(count)));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(FeedingRecordId id, Date headerDate) {
		StringBuilder sb = new StringBuilder(marshallId(id));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(FeedingRecord t, int start, int count) {
		StringBuilder sb = new StringBuilder(marshall(t));
		sb.append(toUri(START_LINE, Integer.toString(start), MAX_COUNT,
				Integer.toString(start)));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

	public String marshallHouse(Date headerDate, int houseid, int start, int count) {
		String[] params = new String[] {

		"houseid", Integer.toString(houseid),

		START_LINE, Integer.toString(start),

		MAX_COUNT, Integer.toString(count) };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}
}
