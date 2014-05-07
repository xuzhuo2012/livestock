package edu.hbut.livestock.http.coding;

import java.sql.Date;

import edu.hbut.livestock.entity.Livestock;

/**
 * 
 * @author Hang
 * 
 */
public class LivestockMarshall extends BaseGETMarshall<Livestock, String> {

	@Override
	public String marshall(Livestock t) {
		String[] params = new String[] { Livestock.LIVESTOCKID,
				t.getLivestockid(),

				Livestock.CREATE_TIME, FMT.format(t.getCreateTime()),

				Livestock.LIVESTOCK_TYPE, t.getLivestockType() };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

	@Override
	public String marshallId(String id) {
		return toUri(Livestock.LIVESTOCKID, id);
	}

	@Override
	public String marshall(String id, Date headerDate, int start, int count) {
		String[] params = new String[] { Livestock.LIVESTOCKID, id,

		START_LINE, Integer.toString(start),

		MAX_COUNT, Integer.toString(count) };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(String id, Date headerDate) {
		StringBuilder sb = new StringBuilder(marshallId(id));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(Livestock t, int start, int count) {
		StringBuilder sb = new StringBuilder(marshall(t));
		sb.append('&');
		sb.append(toUri(START_LINE, Integer.toString(start), MAX_COUNT,
				Integer.toString(count)));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

}
